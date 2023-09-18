package com.example.firebasechatapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.firebasechatapp.databinding.ContactListItemBinding
import com.example.firebasechatapp.dataclass.Contact
import kotlinx.coroutines.flow.combine

class ContactAdapter(private val context: Context,
                     private val contacts: List<Contact>,
                     private val clickListenerCallback:(Contact) -> Unit
) :
    RecyclerView.Adapter<ContactAdapter.ContactViewHolder>() {


    class ContactViewHolder(private val binding: ContactListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val nameTextView: TextView = binding.contactNameTextView
        val numberTextView: TextView = binding.contactNumberTextView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ContactListItemBinding.inflate(layoutInflater, parent, false)
        return ContactViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        val contact = contacts[position]
        holder.nameTextView.text = contact.name
        holder.numberTextView.text = contact.number

        holder.itemView.setOnClickListener{
            clickListenerCallback(contact)
        }
    }

    override fun getItemCount(): Int {
        return contacts.size
    }
}
