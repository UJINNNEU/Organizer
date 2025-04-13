package com.example.lab3.map

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.asLiveData
import androidx.navigation.fragment.findNavController
import com.example.lab3.R
import com.example.lab3.database.ContactDataBase
import com.example.lab3.databaseMap.MapDataBase
import com.example.lab3.databaseMap.MapEntity
import com.example.lab3.databinding.FragmentAddMapBinding
import com.example.lab3.databinding.FragmentMapListBinding
import kotlin.math.log


class MapList : Fragment() {
    private var _binding: FragmentMapListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_map_list, container, false)
    }
    lateinit var listMap:List<MapEntity>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentMapListBinding.bind(view)
        binding.toolbarMap.setNavigationIcon(R.drawable.back)
        binding.toolbarMap.setNavigationOnClickListener {
           // findNavController().navigate(R.id.action_map_to_mainFragment) // Закрываем фрагмент
            findNavController().navigate(R.id.action_mapList_to_mainFragment)
        }

        val db = MapDataBase.getDataBase(requireContext())
        db.MapDao().getAllMap().asLiveData().observe(viewLifecycleOwner){ list ->


            try {
                listMap = list
                binding.TVAllMap.text = "всего контактов ${list.size}"
                list.forEach(){
                    Log.d("MyLog","Fragment(Contact) id = ${it.id}")
                }
            }catch (e:Exception){
                Log.d("MyLog","Error $e")
                Toast.makeText(requireContext(),"Ошибка загрузки из БД",Toast.LENGTH_SHORT).show()
            }

        }



    }

    override fun onStart()
    {
        binding.toolbarMap.setOnMenuItemClickListener(){

            when(it.itemId){
                R.id.addContact -> {
                    Toast.makeText(requireContext(),"fdsf",Toast.LENGTH_SHORT).show()
                    findNavController().navigate(R.id.action_mapList_to_addMap2)
                    true
                }
                else-> false
            }
        }

        //adapter.addList(list)



        super.onStart()

    }



}