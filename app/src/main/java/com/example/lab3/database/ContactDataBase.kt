package com.example.lab3.database

import android.content.Context
import androidx.lifecycle.ViewModelProvider.NewInstanceFactory.Companion.instance
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Contact::class], version = 1, exportSchema = false)
abstract class ContactDataBase:RoomDatabase() {
    abstract fun contactDao():ContactDao

    companion object{
        fun getDataBase(context: Context):ContactDataBase{
            return Room.databaseBuilder(
                context.applicationContext,
                ContactDataBase::class.java,
                name = "test.db"
            ).build()
        }
    }

}