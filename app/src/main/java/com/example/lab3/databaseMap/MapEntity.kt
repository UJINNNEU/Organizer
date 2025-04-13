package com.example.lab3.databaseMap

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "map")
data class MapEntity(
    @PrimaryKey(autoGenerate = true)
    var id:Int? = null,
    @ColumnInfo(name = "name")
    var name:String?,
    @ColumnInfo(name = "date")
    var date:Long?,
    @ColumnInfo(name = "country")
    var country:String?,
    @ColumnInfo(name = "city")
    var city:String?,
    @ColumnInfo(name = "street")
    var street:String?,
    @ColumnInfo(name = "house")
    var house:String?,
    @ColumnInfo(name = "dolgota")
    var dolgota:Long?,
    @ColumnInfo(name = "shirota")
    var shirota:Long?
)