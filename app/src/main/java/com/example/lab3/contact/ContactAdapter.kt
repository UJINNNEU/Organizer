package com.example.lab3.contact

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.lab3.database.Contact
import com.example.lab3.databinding.ItemContactBinding
import com.example.lab3.tools.OnItemClickListener

class ContactAdapter(private val listener: OnItemClickListener) :RecyclerView.Adapter<ContactAdapter.MyViewHolder>() {
    private var contactList = emptyList<Contact>()
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemContactBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MyViewHolder(binding,listener) // Передаем listener в ViewHolder
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(contactList[position],holder)

    }





    override fun getItemCount(): Int = contactList.size

    fun addList(contacts: List<Contact>) {
        this.contactList = contacts
        notifyDataSetChanged()
    }
    class MyViewHolder(private val binding: ItemContactBinding,private val listener: OnItemClickListener) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(contact: Contact,holder: MyViewHolder) {
            binding.apply {
                contactName.text = contact.name
                contactPhone.text = contact.phone
                contactAddress.text = contact.address
                description.text = contact.description

                cardItem.setOnClickListener(){
                    listener.onItemClick(contact.id!!)
                }
            }
        }
    }

}
