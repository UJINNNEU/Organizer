package com.example.lab3.databaseMap

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.lab3.databaseMap.MapEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MapDao {
    //CRUD
    @Query("Select * from map order by id desc")
    fun getAllMap(): Flow<List<MapEntity>>

    @Query("Select * from map where id = :idMap limit 1")
    suspend fun getMapById(idMap:Int): MapEntity

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertMap(map: MapEntity):Long
    @Update
    suspend fun updateMap(map: MapEntity):Int
    @Delete
    suspend fun deleteMap(map: MapEntity):Int
}