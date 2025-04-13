package com.example.lab3.tools

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class ConventerTypes {

    private val months = listOf(
        "Января", "Февраля", "Марта", "Апреля", "Мая", "Июня",
        "Июля", "Августа", "Сентября", "Октября", "Ноября", "Декабря"
    )
    val calendar = Calendar.getInstance()

    fun conventDateToLong(date:String):Long
    {
        var intMonth = months.indexOf(date.substringAfter(".").substringBeforeLast("."))
        var intDay = date.substringBefore(".").toInt()
        var intYear = date.substringAfterLast(".").toInt()
        calendar.set(intYear,intMonth,intDay,0,0,0)
        calendar.set(Calendar.MILLISECOND, 0)  // Обнуляем миллисекунды для точности
        return calendar.timeInMillis

    }

    fun conventDateToString(date:Long):String
    {
        calendar.timeInMillis = date

        val day = calendar.get(Calendar.DAY_OF_MONTH)
        val month = calendar.get(Calendar.MONTH) //+ 1  // Месяцы в Calendar начинаются с 0
        val year = calendar.get(Calendar.YEAR)

        return "$day"+"."+"${months.get(month)}"+"."+"$year"
    }

    fun conventTimeToString(time:Long):String
    {
        val format = SimpleDateFormat("HH:mm", Locale.getDefault())  // Формат времени
        return format.format(Date(time))
    }

    fun conventTimeToLong(time:String):Long
    {
        val format = SimpleDateFormat("HH:mm", Locale.getDefault())
        val date = format.parse(time) ?: return 0L
        return date.time

    }
}