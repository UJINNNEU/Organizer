package com.example.lab3

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.navigateUp
import com.example.lab3.databinding.FragmentContactsBinding


class Contacts : Fragment() {
    private var _binding: FragmentContactsBinding? = null
    private val binding get() = _binding!!
        override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
            return inflater.inflate(R.layout.fragment_contacts, container, false)
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentContactsBinding.bind(view)
        binding.toolbar.setNavigationIcon(R.drawable.back)
        binding.toolbar.setNavigationOnClickListener {
            findNavController().navigateUp() // Закрываем фрагмент
        }
        binding.toolbar.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.addContact -> {
                    findNavController().navigate(R.id.action_contacts2_to_addContacts)
                    true
                }
                else -> false
            }

        }
    }
}





