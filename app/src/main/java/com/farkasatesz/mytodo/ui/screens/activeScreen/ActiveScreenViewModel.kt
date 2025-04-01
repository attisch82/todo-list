package com.farkasatesz.mytodo.ui.screens.activeScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.farkasatesz.mytodo.data.converter.DateConverter
import com.farkasatesz.mytodo.data.model.Todo
import com.farkasatesz.mytodo.data.repositories.TodoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import javax.inject.Inject

@HiltViewModel
class ActiveScreenViewModel @Inject constructor(
    private val repository: TodoRepository
) : ViewModel() {

    init {
        getActiveTodos()
    }

    //Using private set to shorten syntax
    var todos = MutableStateFlow<List<Todo>>(emptyList())
        private set

    var title = MutableStateFlow("")
        private set

    var description = MutableStateFlow("")
        private set

    var deadline = MutableStateFlow(LocalDate.now())
        private set

    var selectedTodo = MutableStateFlow<Todo?>(null)
        private set

    var showTodoDialog = MutableStateFlow(false)
        private set

    var showTodoDatePicker = MutableStateFlow(false)
        private set

    fun setTitle(text: String){
        title.value = text
    }

    fun setDescription(text: String) {
        description.value = text
    }

    fun setDeadline(timestamp: Long){
        deadline.value = Instant.ofEpochMilli(timestamp).atZone(ZoneId.systemDefault()).toLocalDate()
    }

    fun showDatePicker(){
        showTodoDatePicker.value = true
    }

    fun hideDatePicker() {
        showTodoDatePicker.value = false
    }

    fun showDialog(){
        showTodoDialog.value = true
    }

    fun hideDialog() {
        showTodoDialog.value = false
    }

    fun getDeadLineAsLong(): Long{
       return deadline.value.atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli()
    }

    private fun copyTodo(){
        selectedTodo.value?.let {
            setTitle(it.title)
            setDescription(it.description)
            deadline.value = it.deadline
        }
    }

    fun getDeadline(): String {
        return DateConverter().dateToText(deadline.value)
    }

    fun selectTodo(todo: Todo){
        selectedTodo.value = todo
        copyTodo()
    }

    fun reset(){
        setTitle("")
        setDescription("")
        deadline.value = LocalDate.now()
        selectedTodo.value = null
    }

    private fun getActiveTodos(){
        viewModelScope.launch {
            repository.getActiveTodos()
                .distinctUntilChanged()
                .collect {
                todos.value = it
            }
        }
    }

    fun upsertTodo(todo: Todo){
        viewModelScope.launch{
            repository.upsert(todo)
        }
    }

    fun setTodoToCompleted(todo: Todo){
        viewModelScope.launch{
            repository.setTodoToCompleted(todo)
        }
    }

    fun deleteTodo(todo: Todo){
        viewModelScope.launch{
            repository.deleteTodo(todo)
        }
    }

}