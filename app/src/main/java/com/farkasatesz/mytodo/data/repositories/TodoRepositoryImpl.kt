package com.farkasatesz.mytodo.data.repositories

import android.util.Log
import com.farkasatesz.mytodo.data.local.TodoDao
import com.farkasatesz.mytodo.data.model.Todo
import kotlinx.coroutines.flow.catch
import javax.inject.Inject

//The implementation of the TodoRepository interface
class TodoRepositoryImpl @Inject constructor(
    private val dao: TodoDao
) : TodoRepository {
    //Using Flow.catch() extension function to handle exception
    override fun getActiveTodos() = dao.getAllActiveTodos().catch {
        Log.e("ActiveTodos", "getActiveTodos: ${it.message.toString()}")
        emit(emptyList())
    }

    override fun getCompletedTodos() = dao.getAllCompletedTodos().catch {
        Log.e("CompletedTodos", "getCompletedTodos: ${it.message.toString()}")
        emit(emptyList())
    }

    //Using runCatching which is the kotlin version of try-catch
    override suspend fun upsert(todo: Todo) {
        runCatching {
            dao.upsertTodo(todo)
        }.onFailure {
            Log.e("Upsert", "upsert: ${it.message.toString()}")
        }
    }

    override suspend fun setTodoToActive(todo: Todo) {
        val updatedTodo = todo.copy(isDone = false)
        runCatching {
           dao.upsertTodo(updatedTodo)
        }.onFailure {
            Log.e("UpsertToActive", "setTodoToActive: ${it.message.toString()}")
        }
    }

    override suspend fun setTodoToCompleted(todo: Todo) {
        val updatedTodo = todo.copy(isDone = true)
        runCatching {
            dao.upsertTodo(updatedTodo)
        }.onFailure {
            Log.e("UpsertToCompleted", "setTodoToCompleted: ${it.message.toString()}")
        }
    }

    override suspend fun deleteTodo(todo: Todo) {
        runCatching {
            dao.deleteTodo(todo)
        }.onFailure {
            Log.e("Delete", "deleteTodo: ${it.message.toString()}")
        }
    }

}