package com.example.lab3.databaseTask

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
@Dao
interface TaskDao {

    //CRUD
    @Query("Select * from task order by id desc")
    fun getAllTask(): Flow<List<TaskEntity>>

    @Query("Select * from task where id = :idTask limit 1")
    suspend fun getTaskById(idTask:Int): TaskEntity

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertTask(task: TaskEntity):Long

    @Update
    suspend fun updateTask(task: TaskEntity):Int

    @Delete
    suspend fun deleteTask(task: TaskEntity):Int
}


