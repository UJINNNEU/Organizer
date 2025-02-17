package com.example.lab3

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController

class MainFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View,savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)
        val buttonGoToContacts = view.findViewById<Button>(R.id.goToFragmentContact)
        val buttonGoToTasks = view.findViewById<Button>(R.id.goToFragmentTask)

        val controller = findNavController()

        buttonGoToContacts.setOnClickListener(){
              controller.navigate(R.id.action_mainFragment_to_contacts2)
        }


        buttonGoToTasks.setOnClickListener(){
            controller.navigate(R.id.action_mainFragment_to_task)
        }


    }


}