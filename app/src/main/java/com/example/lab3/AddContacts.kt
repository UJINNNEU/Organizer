package com.example.lab3

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.lab3.databinding.FragmentAddContactsBinding
import com.example.lab3.databinding.FragmentContactsBinding


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

    }

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

       Log.d("MyLog","$name $numerPhone $address $description")
       Log.d("MyLog","click")

   }

    private fun onClickSelectPhotoButton()
    {
        Log.d("MyLog","ClickPhoto")
    }


}