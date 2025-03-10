package com.example.lab3

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.lab3.database.ContactDataBase
import com.example.lab3.databinding.FragmentAddContactsBinding

class updateDeleteContactFragment : Fragment() {

    private var _binding: FragmentAddContactsBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_update_delete_contact, container, false)
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


        binding.buttonPhoto.setOnClickListener(){
            findNavController().navigateUp()
        }
    }


}