package com.farkasatesz.mytodo.data.repositories

import com.farkasatesz.mytodo.data.model.Todo
import kotlinx.coroutines.flow.Flow

//Creating functions to handle specific database operations for both viewModels in ui/screens
interface TodoRepository {
    fun getActiveTodos() : Flow<List<Todo>>
    fun getCompletedTodos() : Flow<List<Todo>>
    suspend fun upsert(todo: Todo)
    suspend fun setTodoToActive(todo: Todo)
    suspend fun setTodoToCompleted(todo: Todo)
    suspend fun deleteTodo(todo: Todo)
}