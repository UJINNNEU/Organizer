package com.example.lab3.task

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.asLiveData
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lab3.tools.OnItemClickListener
import com.example.lab3.R
import com.example.lab3.databaseTask.TaskDataBase
import com.example.lab3.databinding.FragmentTaskBinding
import com.example.lab3.viewModel.TaskViewModel
class Task : Fragment() {
    private var _binding: FragmentTaskBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_task, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentTaskBinding.bind(view)
        binding.taskToolbar.setNavigationIcon(R.drawable.back)
        binding.taskToolbar.setNavigationOnClickListener {
            findNavController().navigate(R.id.action_task_to_mainFragment) // Закрываем фрагмент
        }
        binding.taskToolbar.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.goToAddTask -> {
                    Log.d("Mylog","Fragment(Task) vhod goToAddTask")
                    findNavController().navigate(R.id.action_task_to_addTask)
                    true
                }
                else -> false
            }

        }


        val adapter = TaskAdapter(object : OnItemClickListener {
            override fun onItemClick(position: Int) {
                 Log.d("MyLog","Fragment(Task) select $position to ViewModel")
                 val viewModel:TaskViewModel by activityViewModels()
                 viewModel.setSelectedTaskId(position)
                 findNavController().navigate(R.id.action_task_to_task_pick)
            }
        })

        val recyclerView = binding.recycleViewTask
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter


        val db = TaskDataBase.getDataBase(requireContext())
        db.TaskDao().getAllTask().asLiveData().observe(viewLifecycleOwner){ list ->
            adapter.addList(list)
            binding.taskTextView.text = "всего задач ${list.size}"
        }


    }


}