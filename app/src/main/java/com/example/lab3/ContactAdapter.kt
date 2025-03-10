package com.example.lab3


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.lab3.database.Contact
import com.example.lab3.databinding.ItemContactBinding

class ContactAdapter:RecyclerView.Adapter<ContactAdapter.MyViewHolder>() {

    private var contactList = emptyList<Contact>()

    class MyViewHolder(private val binding: ItemContactBinding):RecyclerView.ViewHolder(binding.root)  {
            fun bind(contact: Contact) {
                binding.apply {
                    contactName.text = contact.name
                    contactPhone.text = contact.phone
                    contactAddress.text = contact.address
                    description.text = contact.description
                }
            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactAdapter.MyViewHolder {
        val binding = ItemContactBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(contactList[position])
    }

    override fun getItemCount(): Int = contactList.size

    fun addList(contacts: List<Contact>) {
        this.contactList = contacts
        notifyDataSetChanged()
    }
}


