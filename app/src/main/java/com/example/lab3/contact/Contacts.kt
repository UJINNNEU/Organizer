package com.example.lab3.contact

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
import com.example.lab3.R
import com.example.lab3.database.ContactDataBase
import com.example.lab3.databinding.FragmentContactsBinding
import com.example.lab3.tools.OnItemClickListener
import com.example.lab3.viewModel.ContactsViewModel


class Contacts : Fragment(){
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
            findNavController().navigate(R.id.action_contactsList_to_mainFragment) // Закрываем фрагмент
        }
        binding.toolbar.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.addContact -> {
                    //findNavController().navigate(R.id.action_contactsList_to_updateDeleteFragment)
                    findNavController().navigate(R.id.action_contacts2_to_addContacts)
                    true
                }
                else -> false
            }

        }
        //val adapter = ContactAdapter()
        val adapter = ContactAdapter(object : OnItemClickListener {
            override fun onItemClick(position: Int) {
                //Log.d("MyLog","click $position")

                val viewModel: ContactsViewModel by activityViewModels()
                viewModel.setSelectedContactId(position)
                Log.d("MyLog","Fragment (Contacts) position =  $position \n " +
                        "viewModel item = ${viewModel.selectedContactId}")

                // Изменить!!!
                findNavController().navigate(R.id.action_contactsList_to_contact_pick)
            }
        })
        val recyclerView = binding.contactsRecyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter



        val db = ContactDataBase.getDataBase(requireContext())
        db.contactDao().getAllContacts().asLiveData().observe(viewLifecycleOwner){ list ->
            adapter.addList(list)
            binding.textView2.text = "всего контактов ${list.size}"
            list.forEach(){
                Log.d("MyLog","Fragment(Contact) id = ${it.id}")
            }
        }

    }


    override fun onDestroy() {
        super.onDestroy()
       // Log.d("MyLog","Destroy cont")
    }

}





