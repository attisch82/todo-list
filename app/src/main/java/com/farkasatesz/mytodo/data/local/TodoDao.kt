package com.farkasatesz.mytodo.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Upsert
import com.farkasatesz.mytodo.data.model.Todo
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoDao {
    @Query("SELECT * FROM todos WHERE isDone = 0 ORDER BY deadline ASC")
    fun getAllActiveTodos(): Flow<List<Todo>>
    @Query("SELECT * FROM todos WHERE isDone = 1")
    fun getAllCompletedTodos(): Flow<List<Todo>>
    @Upsert
    suspend fun upsertTodo(todo: Todo)
    @Delete
    suspend fun deleteTodo(todo: Todo)
}