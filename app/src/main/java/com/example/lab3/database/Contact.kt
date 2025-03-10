package com.example.lab3.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "contacts")
data class Contact(
    @PrimaryKey(autoGenerate = true)
    var id:Int? = null,
    @ColumnInfo(name = "name")
    var name:String,
    @ColumnInfo(name = "phone")
    var phone:String,
    @ColumnInfo(name = "address")
    var address:String,
    @ColumnInfo(name = "description")
    var description:String
)
