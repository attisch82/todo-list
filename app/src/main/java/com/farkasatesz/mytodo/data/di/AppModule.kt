package com.farkasatesz.mytodo.data.di

import android.content.Context
import androidx.room.Room
import com.farkasatesz.mytodo.data.local.TodoDao
import com.farkasatesz.mytodo.data.local.TodoDatabase
import com.farkasatesz.mytodo.data.repositories.TodoRepository
import com.farkasatesz.mytodo.data.repositories.TodoRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideTodoDatabase(@ApplicationContext context: Context) = Room.databaseBuilder(
        context,
        TodoDatabase::class.java,
        "todo_db"
    ).fallbackToDestructiveMigration().build()

    @Singleton
    @Provides
    fun provideTodoDao(todoDatabase: TodoDatabase) = todoDatabase.todoDao()

    //Provides repository which will be injected into viewmodel
    @Singleton
    @Provides
    fun provideTodoRepository(dao: TodoDao) : TodoRepository {
        return TodoRepositoryImpl(dao)
    }
    
}