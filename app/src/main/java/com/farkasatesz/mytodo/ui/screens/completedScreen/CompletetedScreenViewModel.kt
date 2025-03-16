package com.farkasatesz.mytodo.ui.screens.completedScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.farkasatesz.mytodo.data.model.Todo
import com.farkasatesz.mytodo.data.repositories.TodoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CompletetedScreenViewModel @Inject constructor(
    private val repository: TodoRepository
) : ViewModel() {

    init {
        getCompletedTodos()
    }

    var todos = MutableStateFlow<List<Todo>>(emptyList())
        private set

    private fun getCompletedTodos(){
        viewModelScope.launch {
            repository.getCompletedTodos().distinctUntilChanged().collect{
                todos.value = it
            }
        }
    }

    fun deleteTodo(todo: Todo){
        viewModelScope.launch {
            repository.deleteTodo(todo)
        }
    }

    fun setTodoToActive(todo: Todo) {
        viewModelScope.launch {
            repository.setTodoToActive(todo)
        }
    }
}