package com.farkasatesz.mytodo.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.farkasatesz.mytodo.data.converter.DateConverter
import com.farkasatesz.mytodo.data.model.Todo

@Database(
    entities = [Todo::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(DateConverter::class)
abstract class TodoDatabase : RoomDatabase() {
    abstract fun todoDao() : TodoDao
}