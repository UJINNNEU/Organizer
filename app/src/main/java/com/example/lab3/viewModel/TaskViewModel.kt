package com.example.lab3.viewModel

import androidx.lifecycle.ViewModel

class TaskViewModel():ViewModel() {

    private var IdSelectedTask:Int = -1

    // Публичное свойство только для чтения
    val selectedTaskId: Int
        get() = IdSelectedTask

    // Метод для установки нового значения
    fun setSelectedTaskId(id: Int) {
        IdSelectedTask = id
    }

}