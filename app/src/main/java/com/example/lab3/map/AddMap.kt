package com.example.lab3.map

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.lab3.R
import com.example.lab3.databinding.FragmentAddMapBinding
import com.yandex.mapkit.mapview.MapView
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.map.CameraPosition


class AddMap : Fragment() {
    private var _binding: FragmentAddMapBinding? = null
    private val binding get() = _binding!!

    private lateinit var mapView: MapView
       override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add_map, container, false)
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentAddMapBinding.bind(view)
        binding.toolbar.setNavigationIcon(R.drawable.back)
        binding.toolbar.setNavigationOnClickListener {
           //findNavController().navigate(R.id.action_map_to_mainFragment) // Закрываем фрагмент
            findNavController().navigate(R.id.action_addMap2_to_mapList)
        }
        mapView = view.findViewById(R.id.mapview)

   }
    val endPoint = Point(55.751225, 37.62954)
    val startPoint = Point(55.026301, 73.290780)
    override fun onStart() {
        MapKitFactory.getInstance().onStart()
        mapView.onStart()


        mapView.mapWindow.map.move(CameraPosition(
            startPoint,
            /* zoom = */ 17.0f,
            /* azimuth = */ 150.0f,
            /* tilt = */ 30.0f
        ))

        binding.editTVCity.setOnClickListener(){
            mapView.mapWindow.map.move(CameraPosition(
                endPoint,
                /* zoom = */ 17.0f,
                /* azimuth = */ 150.0f,
                /* tilt = */ 30.0f
            ))
        }

        binding.toolbar.setOnMenuItemClickListener(){

            when (it.itemId){
                R.id.getPosition ->{
                    Toast.makeText(requireContext(),"GetPosition",Toast.LENGTH_SHORT).show()

                    mapView.mapWindow.map.move(CameraPosition(endPoint,
                        /* zoom = */ 17.0f,
                        /* azimuth = */ 150.0f,
                        /* tilt = */ 30.0f
                    ))
                    true

                }
                else -> false

            }
        }

        super.onStart()
    }

    override fun onStop() {
        MapKitFactory.getInstance().onStop()
        mapView.onStop()
        super.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("MyLog","Fragment (Map) destroy")
    }


}