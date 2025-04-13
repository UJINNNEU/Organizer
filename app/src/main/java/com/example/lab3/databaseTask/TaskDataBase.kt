package com.example.lab3.databaseTask

import android.content.Context
import androidx.lifecycle.ViewModelProvider.NewInstanceFactory.Companion.instance
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [TaskEntity::class], version = 1, exportSchema = false)
abstract class TaskDataBase:RoomDatabase() {
    abstract fun TaskDao(): TaskDao

    companion object{
        fun getDataBase(context: Context):TaskDataBase{
            return Room.databaseBuilder(
                context.applicationContext,
                TaskDataBase::class.java,
                name = "task.db"
            ).build()
        }
    }

}




