package com.example.lab3

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.lab3.databinding.FragmentContactsBinding
import com.example.lab3.databinding.FragmentTaskBinding


class Task : Fragment() {
    private var _binding: FragmentTaskBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_task, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentTaskBinding.bind(view)
        binding.taskToolbar.setNavigationIcon(R.drawable.back)
        binding.taskToolbar.setNavigationOnClickListener {
            findNavController().navigateUp() // Закрываем фрагмент
        }
        binding.taskToolbar.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.goToAddTask -> {
                    Log.d("Mylog","vhod")
                    findNavController().navigate(R.id.action_task_to_addTask)
                    true
                }
                else -> false
            }

        }
    }


}