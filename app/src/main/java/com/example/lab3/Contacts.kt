package com.example.lab3

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater

import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.asLiveData

import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lab3.database.ContactDataBase
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
        // RecyclerView
        val adapter = ContactAdapter()
        val recyclerView = binding.contactsRecyclerView
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())


        val db = ContactDataBase.getDataBase(requireContext())
        db.contactDao().getAllContacts().asLiveData().observe(viewLifecycleOwner){ list ->
            adapter.addList(list)
            binding.textView2.text = "всего контактов ${list.size}"
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("MyLog","Destroy cont")
    }

    override fun onStart() {
        super.onStart()

    }

}





