package com.example.lab3

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.example.lab3.database.ContactDataBase
import com.example.lab3.databaseTask.TaskDataBase
import com.example.lab3.databaseTask.TaskEntity
import com.example.lab3.databinding.FragmentAddTaskBinding
import com.example.lab3.databinding.FragmentContactsBinding
import com.example.lab3.tools.ConventerTypes
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.time.Year
import java.util.Calendar
import java.util.Locale
import kotlin.random.Random


class AddTask : Fragment() {
    private var _binding: FragmentAddTaskBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_task, container, false)
    }

    val calendar = Calendar.getInstance()
    val year = calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH)
    val day = calendar.get(Calendar.DAY_OF_MONTH)
    val hour = calendar.get(Calendar.HOUR_OF_DAY)
    val minute = calendar.get(Calendar.MINUTE)

    private lateinit var db:TaskDataBase
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        db = TaskDataBase.getDataBase(requireContext())

        _binding = FragmentAddTaskBinding.bind(view)
        binding.toolbar.setNavigationIcon(R.drawable.back)
        binding.toolbar.setNavigationOnClickListener {
            findNavController().navigateUp() // Закрываем фрагмент
        }

       // Раскоментить, если нужно чтоб писалось сразу
       // val selectedDate = "${day}.${months[month]}.${year}"
       // val selectedTime = String.format("%02d:%02d", hour, minute)

        //binding.tvDate.text = "$selectedDate"
        //binding.tvStart.text ="$selectedTime"

        binding.tvDate.setOnClickListener(){
            pickDate(year,month,day)
        }

        binding.tvStart.setOnClickListener(){
           pickStartTime(hour,minute,binding.tvStart)
        }

        binding.tvEnd.setOnClickListener(){
            pickStartTime(hour,minute,binding.tvEnd)
        }
        binding.DoneButton.setOnClickListener(){
            onClickDoneButton()
        }


    }

    val months = listOf(
        "Января", "Февраля", "Марта", "Апреля", "Мая", "Июня",
        "Июля", "Августа", "Сентября", "Октября", "Ноября", "Декабря"
    )
    private fun pickDate(year: Int,month:Int,day:Int)
    {
        val datePickerDialog = DatePickerDialog(
            requireContext(),
            { _, selectedYear, selectedMonth, selectedDay ->
                // Обработка выбранной даты
                val selectedDate = "${selectedDay}.${months[selectedMonth]}.${selectedYear}"
                binding.tvDate.text = "$selectedDate"
            },
            year,
            month,
            day
        )
        datePickerDialog.show()
    }

    private fun pickStartTime(hour:Int, minute:Int,textView: TextView)
    {
        val timePickerDialog = TimePickerDialog(
            requireContext(),
            { _, selectedHour, selectedMinute ->
                // Обработка выбранного времени
                val selectedTime = String.format("%02d:%02d", selectedHour, selectedMinute)
                textView.text = "$selectedTime"
            },
            hour,
            minute,
            true // true для 24-часового формата, false для 12-часового
        )
        timePickerDialog.show()
    }

    private val converter = ConventerTypes()
    private val color = listOf("Красный","Оранжевый","Зеленый")

    private fun onClickDoneButton()
    {

        val date = converter.conventDateToLong(binding.tvDate.text.toString())

        val timeStart = converter.conventTimeToLong(binding.tvStart.text.toString())
        val timeEnd = converter.conventTimeToLong(binding.tvEnd.text.toString())

        val colorRand = color[2]
        val description = binding.textDescription.text.toString()

        val task = TaskEntity(null,"danil",
            date,timeStart,timeEnd,colorRand,description,"Danil"

        )
        // Запускаем корутину
        CoroutineScope(Dispatchers.IO).launch {
            try {
                db.TaskDao().insertTask(task) // Теперь это безопасный вызов
                Log.d("MyLog", "Задача добавлена")
            } catch (e: Exception) {
                Log.e("MyLog", "Ошибка добавления задачи", e)
            }
        }

        //Log.d("MyLog","Long $time\n"
        //+" String ${converter.conventTimeToString(time)}")
    }




}