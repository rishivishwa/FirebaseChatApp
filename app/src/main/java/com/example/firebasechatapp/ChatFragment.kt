package com.example.firebasechatapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.firebasechatapp.databinding.ContactListItemBinding
import com.example.firebasechatapp.databinding.FragmentChatBinding
import com.example.firebasechatapp.dataclass.Contact


class ChatFragment : Fragment() {

    private lateinit var binding: FragmentChatBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentChatBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val contactData = arguments?.getParcelable<Contact>("contactData")
        binding.toolbar.title = contactData?.name
    }


}