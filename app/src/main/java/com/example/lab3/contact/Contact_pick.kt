package com.example.lab3.contact

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.lab3.R
import com.example.lab3.database.Contact
import com.example.lab3.database.ContactDataBase
import com.example.lab3.databinding.FragmentContactPickBinding
import com.example.lab3.viewModel.ContactsViewModel
import kotlinx.coroutines.launch
import kotlin.properties.Delegates

class contact_pick : Fragment() {
    private var _binding: FragmentContactPickBinding? = null
    private val binding get() = _binding!!
  //  var IdContact by Delegates.notNull<Int>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentContactPickBinding.bind(view)
        binding.toolbar.setNavigationIcon(R.drawable.back)
        binding.toolbar.setNavigationOnClickListener {
            findNavController().navigate(R.id.action_contact_pick_to_contactsList) // Закрываем фрагмент
        }
        val viewModelContact: ContactsViewModel by activityViewModels()
        val IdContact = viewModelContact.selectedContactId

        //arguments?.getInt("IdContacts")?:1
        Log.d("MyLog","Fragment (Contact_Pick) id = $IdContact\n" +
                "viewModel = ${viewModelContact.selectedContactId}")

        val db = ContactDataBase.getDataBase(requireContext())
        lifecycleScope.launch {
            val contact: Contact = db.contactDao().getContactById(IdContact)

            if (contact.id != null) {
                Log.d("MyLog","${contact.name}")

                binding.nameET.setText(contact.name)
                binding.phoneET.setText(contact.phone)
                binding.adressET.setText(contact.address)
                binding.description.setText(contact.description)

            } else {
                Toast.makeText(requireContext(), "Контакт не найден", Toast.LENGTH_SHORT).show()
            }
        }

        binding.toolbar.setOnMenuItemClickListener(){
            when(it.itemId){
                R.id.editItem ->{
                   // Toast.makeText(requireContext(), "Контакт удален", Toast.LENGTH_SHORT).show()
                    findNavController().navigate(R.id.action_contact_pick_to_updateDeleteFragment)
                    true
                }
                else ->
                {
                    false
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_contact_pick, container, false)
    }



}