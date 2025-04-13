package com.example.lab3.contact

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.lab3.R
import com.example.lab3.database.Contact
import com.example.lab3.database.ContactDataBase
import com.example.lab3.databinding.FragmentAddContactsBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class AddContacts : Fragment() {
    private var _binding: FragmentAddContactsBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add_contacts, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentAddContactsBinding.bind(view)
        UICreate()
        db = ContactDataBase.getDataBase(requireContext())

    }
   private lateinit var db:ContactDataBase
    private fun UICreate(){
        binding.toolbar.setNavigationIcon(R.drawable.back)
        binding.toolbar.setNavigationOnClickListener {
            findNavController().navigateUp() // Закрываем фрагмент
        }
        binding.imageView3.setImageResource(R.drawable.baseline_account_box_24)

        binding.Donebutton.setOnClickListener(){
            onClickDoneButton()
            findNavController().navigateUp()
        }
        binding.buttonPhoto.setOnClickListener(){
            onClickSelectPhotoButton()
        }
    }

   private fun onClickDoneButton()
   {
       val name:String = binding.nameET.text.toString()
       val numerPhone = binding.phoneET.text.toString()
       val address = binding.adressET.text.toString()
       val description = binding.description.text.toString()

       val contact = Contact(null,
           name,
           numerPhone,
           address,
           description)

       // Запускаем корутину
       CoroutineScope(Dispatchers.IO).launch {
           try {
               db.contactDao().insertContact(contact) // Теперь это безопасный вызов
               Log.d("MyLog", "Контакт добавлен")
           } catch (e: Exception) {
               Log.e("MyLog", "Ошибка добавления контакта", e)
           }
       }

   }
    override fun onDestroy() {
        super.onDestroy()
        Log.d("MyLog","Destroy add")
    }

    private fun onClickSelectPhotoButton()
    {
        Log.d("MyLog","ClickPhoto")


    }


}