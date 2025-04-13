package com.example.lab3.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface ContactDao {
        //CRUD
        @Query("Select * from contacts order by id desc")
        fun getAllContacts(): Flow<List<Contact>>

        @Query("Select name from contacts")
        fun getAllContactsNames(): Flow<List<String>>

        @Query("Select * from contacts where id = :idContact limit 1")
        suspend fun getContactById(idContact:Int): Contact

        @Insert(onConflict = OnConflictStrategy.IGNORE)
        suspend fun insertContact(contact:Contact):Long

        @Update
        suspend fun updateContact(contact: Contact):Int

        @Delete
        suspend fun deleteContact(contact: Contact):Int
}