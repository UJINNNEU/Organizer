package com.example.lab3.task

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.asLiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.lab3.tools.OnItemClickListener
import com.example.lab3.R
import com.example.lab3.databaseTask.TaskDataBase
import com.example.lab3.databaseTask.TaskEntity
import com.example.lab3.databinding.ItemTaskBinding
import com.example.lab3.tools.ConventerTypes

class TaskAdapter(private val listener: OnItemClickListener):RecyclerView.Adapter<TaskAdapter.MyViewHolder>() {
    private var taskList = emptyList<TaskEntity>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemTaskBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MyViewHolder(binding, listener) // Передаем listener в ViewHolder
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(taskList[position],holder)
    }

    override fun getItemCount(): Int = taskList.size

    fun addList(task: List<TaskEntity>) {
        this.taskList = task
        notifyDataSetChanged()
    }



    class MyViewHolder(private val binding: ItemTaskBinding, private val listener:
    OnItemClickListener
    ):RecyclerView.ViewHolder(binding.root)
    {
        val conventer = ConventerTypes()
        fun bind(task: TaskEntity,holder: MyViewHolder) {
            binding.apply {

                myContactTV.text = task.nameContact
                dateTV.text = task.date?.let { conventer.conventDateToString(it) }

                startEventTV.text = "начало:" + " ${task.startEvent?.let { conventer.conventTimeToString(it) }}"
                endEventTV.text = " конец " + "${task.endEvent?.let { conventer.conventTimeToString(it) }}"

                descriptionTV.text = task.description
                ColorPhoto.setImageResource(R.drawable.red)


                cardView.setOnClickListener(){
                    listener.onItemClick(task.id!!)
                }
            }
        }

    }

}