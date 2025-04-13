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
import com.example.lab3.databinding.FragmentUpdateDeleteContactBinding
import com.example.lab3.viewModel.ContactsViewModel
import kotlinx.coroutines.launch
import kotlin.properties.Delegates

class updateDeleteContactFragment : Fragment() {

    private var _binding: FragmentUpdateDeleteContactBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_update_delete_contact, container, false)
    }
       override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentUpdateDeleteContactBinding.bind(view)
        UICreate()

        val viewModelContact: ContactsViewModel by activityViewModels()
        val IdContact = viewModelContact.selectedContactId

        Log.d("MyLog","Fragment (UpdateDeleteContact) id = $IdContact\n" +
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
                R.id.deleteItem ->{
                    lifecycleScope.launch {
                        val contact: Contact = db.contactDao().getContactById(IdContact)
                        db.contactDao().deleteContact(contact)
                    }

                    Toast.makeText(requireContext(), "Контакт удален", Toast.LENGTH_SHORT).show()

                    findNavController().navigate(R.id.action_updateDeleteFragment_to_contactsList)
                    true
                }
                else ->
                {
                    lifecycleScope.launch {
                        val contact: Contact = db.contactDao().getContactById(IdContact)
                        db.contactDao().updateContact(updateContact(contact))
                    }
                    Toast.makeText(requireContext(), "Контакт обновлен", Toast.LENGTH_SHORT).show()
                    findNavController().navigate(R.id.action_updateDeleteFragment_to_contactsList)
                    true
                }
            }
        }
        binding.toolbar.setOnClickListener(){
            findNavController().navigate(R.id.action_updateDeleteFragment_to_contactsList) // Закрываем фрагмент

        }

    }
    private fun updateContact(contact: Contact): Contact {

        contact.name = binding.nameET.text.toString()
        contact.phone = binding.phoneET.text.toString()
        contact.address = binding.adressET.text.toString()
        contact.description = binding.description.text.toString()

        return contact
    }

    private fun UICreate(){
        binding.toolbar.setNavigationIcon(R.drawable.back)
        binding.toolbar.setNavigationOnClickListener {
            findNavController().navigate(R.id.contactsList) // Закрываем фрагмент
        }
        binding.imageView3.setImageResource(R.drawable.baseline_account_box_24)

        binding.buttonPhoto.setOnClickListener(){
            findNavController().navigateUp()
        }

       // binding.toolbar.menu.


    }




}