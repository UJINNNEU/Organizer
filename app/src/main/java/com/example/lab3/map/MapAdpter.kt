package com.example.lab3.map

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.lab3.R
import com.example.lab3.databaseMap.MapEntity
import com.example.lab3.databinding.ItemMapBinding
import com.example.lab3.databinding.ItemTaskBinding
import com.example.lab3.tools.ConventerTypes
import com.example.lab3.tools.OnItemClickListener

class MapAdpter(private val listener: OnItemClickListener):
    RecyclerView.Adapter<MapAdpter.MyViewHolder>() {

    private var mapList = emptyList<MapEntity>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemMapBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MyViewHolder(binding, listener) // Передаем listener в ViewHolder
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(mapList[position],holder)
    }

    override fun getItemCount(): Int = mapList.size

    fun addList(map: List<MapEntity>) {
        this.mapList = map
        notifyDataSetChanged()
    }

    class MyViewHolder(private val binding: ItemMapBinding, private val listener:
    OnItemClickListener
    ):RecyclerView.ViewHolder(binding.root)
    {
        val conventer = ConventerTypes()
        fun bind(map: MapEntity, holder: MyViewHolder) {
            binding.apply {
                TVName.text = map.name

                TVDate.text = "${map.date?.let { conventer.conventDateToString(it) }}"

                cardView.setOnClickListener(){
                    listener.onItemClick(map.id!!)
                }
            }
        }

    }
}