package com.example.lab3.task

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.lab3.R
import com.example.lab3.database.Contact
import com.example.lab3.database.ContactDataBase
import com.example.lab3.databaseTask.TaskDataBase
import com.example.lab3.databaseTask.TaskEntity
import com.example.lab3.databinding.FragmentTaskPickBinding
import com.example.lab3.databinding.FragmentUpdateDeleteContactBinding
import com.example.lab3.tools.ConventerTypes
import com.example.lab3.viewModel.TaskViewModel
import kotlinx.coroutines.launch
import kotlin.properties.Delegates


class task_pick : Fragment() {
    private var _binding: FragmentTaskPickBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_task_pick, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _binding =  FragmentTaskPickBinding.bind(view)
        super.onViewCreated(view, savedInstanceState)
        onCreateUI()
        val taskViewModel:TaskViewModel by activityViewModels()
        Log.d("MyLog","Fragment (TaskPick) id = ${taskViewModel.selectedTaskId}\n")

        val converterTypes = ConventerTypes()
        val db = TaskDataBase.getDataBase(requireContext())
        lifecycleScope.launch {
            val task: TaskEntity = db.TaskDao().getTaskById(taskViewModel.selectedTaskId)

            if (task.id != null) {
                Log.d("MyLog","Fragment (TaskPick) ${task.name}")

                binding.tvDate.setText(task.date?.let { converterTypes.conventDateToString(it) })
                binding.tvStart.setText(task.startEvent?.let { converterTypes.conventDateToString(it) })
                binding.tvEnd.setText(task.endEvent?.let { converterTypes.conventDateToString(it) })
                binding.ContactTv.setText(task.nameContact)
                binding.textDescription.setText(task.description)

            } else {
                Toast.makeText(requireContext(), "Задача не найдена", Toast.LENGTH_SHORT).show()
            }
        }

        binding.toolbar.setOnMenuItemClickListener(){
            when(it.itemId){
                R.id.editItem ->{
                    // Toast.makeText(requireContext(), "Контакт удален", Toast.LENGTH_SHORT).show()
                    findNavController().navigate(R.id.action_task_pick_to_update_delete_task)
                    true
                }
                else ->
                {
                    false
                }
            }
        }


    }
    fun onCreateUI(){
        binding.toolbar.setNavigationIcon(R.drawable.back)
        binding.toolbar.setNavigationOnClickListener {
            findNavController().navigate(R.id.action_task_pick_to_task) // Закрываем фрагмент
        }
        //binding.imageView3.setImageResource(R.drawable.baseline_account_box_24)

       // binding.buttonPhoto.setOnClickListener(){
       //     findNavController().navigateUp()
       // }
    }


}