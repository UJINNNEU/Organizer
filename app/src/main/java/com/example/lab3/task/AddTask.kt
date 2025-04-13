package com.example.lab3.task

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.lifecycle.asLiveData
import androidx.navigation.fragment.findNavController
import com.example.lab3.R
import com.example.lab3.database.Contact
import com.example.lab3.database.ContactDataBase
import com.example.lab3.databaseTask.TaskDataBase
import com.example.lab3.databaseTask.TaskEntity
import com.example.lab3.databinding.FragmentAddTaskBinding
import com.example.lab3.tools.ConventerTypes
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Calendar


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

    private lateinit var taskDao:TaskDataBase
    private lateinit var contactDao:ContactDataBase
    private val color = listOf("Красный","Оранжевый","Зеленый")

    private lateinit var contactNames: List<String>

    private var selectedContactName: String = "" // Выбранное имя контакта
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentAddTaskBinding.bind(view)
        binding.toolbar.setNavigationIcon(R.drawable.back)
        binding.toolbar.setNavigationOnClickListener {
            findNavController().navigate(R.id.action_addTask_to_task) // Закрываем фрагмент
        }

        taskDao = TaskDataBase.getDataBase(requireContext())
        contactDao = ContactDataBase.getDataBase(requireContext())

        // Загружаем список контактов
        contactDao.contactDao().getAllContactsNames().asLiveData().observe(viewLifecycleOwner) { contacts ->
            contactNames = contacts

            val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, contactNames)
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.contactSpinner.adapter = adapter
        }

        // Слушаем выбор пользователя
        binding.contactSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                selectedContactName = contactNames[position] // Сохраняем выбранное имя
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                selectedContactName = ""
            }
        }


        val adapter2 = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, color)
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.colorSpinner.adapter = adapter2


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


    private fun onClickDoneButton()
    {

        val date = converter.conventDateToLong(binding.tvDate.text.toString())

        val timeStart = converter.conventTimeToLong(binding.tvStart.text.toString())
        val timeEnd = converter.conventTimeToLong(binding.tvEnd.text.toString())

        val colorRand = color[2]
        val description = binding.textDescription.text.toString()

        val task = TaskEntity(null,"",
            date,timeStart,timeEnd,colorRand,description,selectedContactName

        )
        // Запускаем корутину
        CoroutineScope(Dispatchers.IO).launch {
            try {
                taskDao.TaskDao().insertTask(task) // Теперь это безопасный вызов
                Log.d("MyLog", "Задача добавлена")
            } catch (e: Exception) {
                Log.e("MyLog", "Ошибка добавления задачи", e)
            }
        }

    }





}