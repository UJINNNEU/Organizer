package com.example.lab3.databaseMap

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.lab3.databaseMap.MapDao
import com.example.lab3.databaseMap.MapEntity

@Database(entities = [MapEntity::class], version = 1, exportSchema = false)
abstract class MapDataBase: RoomDatabase() {
    abstract fun MapDao(): MapDao
    companion object{
        fun getDataBase(context: Context):MapDataBase{
            return Room.databaseBuilder(
                context.applicationContext,
                MapDataBase::class.java,
                name = "map.db"
            ).build()
        }
    }

}