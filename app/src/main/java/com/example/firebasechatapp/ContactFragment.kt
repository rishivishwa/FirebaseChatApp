package com.example.firebasechatapp

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.firebasechatapp.databinding.FragmentContactBinding
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.firebasechatapp.adapter.ContactAdapter
import com.example.firebasechatapp.dataclass.Contact
import com.example.firebasechatapp.viewModel.ContactViewModel

class ContactFragment : Fragment() {
    private lateinit var binding: FragmentContactBinding
    private lateinit var contactViewModel: ContactViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentContactBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (hasReadContactsPermission()) {
            fetchContacts()
        } else {
            requestContactsPermission()
        }

    }

    private fun hasReadContactsPermission(): Boolean {
        return ContextCompat.checkSelfPermission(
            requireContext(),
            Manifest.permission.READ_CONTACTS
        ) == PackageManager.PERMISSION_GRANTED
    }

    private fun requestContactsPermission() {
        ActivityCompat.requestPermissions(
            requireActivity(),
            arrayOf(Manifest.permission.READ_CONTACTS),
            12
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        if (requestCode == 12) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                fetchContacts()
            } else {
                // Handle permission denied
            }
        }
    }

    private fun fetchContacts() {
        contactViewModel = ViewModelProvider(requireActivity()).get(ContactViewModel::class.java)
        contactViewModel.contactList.observe(viewLifecycleOwner, Observer { contacts ->
            Log.d("#CONTACT", contacts.toString())
            val recyclerView = binding.recyclerView
            val contactAdapter = ContactAdapter(requireContext(), contacts,::onSelectContact)
            recyclerView.layoutManager = LinearLayoutManager(requireContext())
            recyclerView.adapter = contactAdapter
        })
    }
    private fun onSelectContact(contact : Contact){
        Log.d("#CONTACT", contact.toString())
        var bundle = Bundle()
        bundle.putParcelable("contactData", contact)
        findNavController().navigate(R.id.action_contactFragment_to_chatFragment,bundle)
    }

}