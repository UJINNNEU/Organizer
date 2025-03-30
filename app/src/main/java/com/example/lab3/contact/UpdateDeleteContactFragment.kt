package com.example.lab3

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.navigateUp
import com.example.lab3.database.Contact
import com.example.lab3.database.ContactDataBase
import com.example.lab3.databinding.FragmentUpdateDeleteContactBinding
import kotlinx.coroutines.launch
import kotlin.properties.Delegates

class updateDeleteContactFragment : Fragment() {

    private var _binding: FragmentUpdateDeleteContactBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_update_delete_contact, container, false)
    }
    var IdContact by Delegates.notNull<Int>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentUpdateDeleteContactBinding.bind(view)
        UICreate()
        IdContact = arguments?.getInt("IdContacts")?:0
        Log.d("MyLog","id = $IdContact")

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
                    Toast.makeText(requireContext(), "Контакт удален", Toast.LENGTH_SHORT).show()
                    findNavController().navigateUp()
                }
                else ->
                {
                    Toast.makeText(requireContext(), "Контакт обновлен", Toast.LENGTH_SHORT).show()
                    findNavController().navigateUp()
                }
            }
        }

    }

    private fun UICreate(){
        binding.toolbar.setNavigationIcon(R.drawable.back)
        binding.toolbar.setNavigationOnClickListener {
            findNavController().navigateUp() // Закрываем фрагмент
        }
        binding.imageView3.setImageResource(R.drawable.baseline_account_box_24)

        binding.buttonPhoto.setOnClickListener(){
            findNavController().navigateUp()
        }

       // binding.toolbar.menu.


    }




}