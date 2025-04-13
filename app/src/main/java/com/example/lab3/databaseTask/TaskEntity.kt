package com.example.lab3.databaseTask
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "task")
data class TaskEntity(
    @PrimaryKey(autoGenerate = true)
    val id:Int? = null,
    @ColumnInfo(name = "name")
    var name:String,
    @ColumnInfo(name = "date")
    val date: Long?,
    @ColumnInfo(name = "startEvent")
    val startEvent: Long?,
    @ColumnInfo(name = "endEvent")
    val endEvent:Long?,
    @ColumnInfo(name = "colour")
    val colour: String,
    @ColumnInfo(name = "description")
    val description: String,
    @ColumnInfo(name = "nameContact")
    val nameContact: String?





)



