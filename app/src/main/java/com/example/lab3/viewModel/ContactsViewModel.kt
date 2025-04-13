package com.example.lab3.viewModel

import androidx.lifecycle.ViewModel

class ContactsViewModel():ViewModel(){

    private var IdSelectedContact:Int = -1

    // Публичное свойство только для чтения
    val selectedContactId: Int
        get() = IdSelectedContact

    // Метод для установки нового значения
    fun setSelectedContactId(id: Int) {
        IdSelectedContact = id
    }
}
