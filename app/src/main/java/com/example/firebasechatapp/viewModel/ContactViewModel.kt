package com.example.firebasechatapp.viewModel

import android.app.Application
import android.database.Cursor
import android.provider.ContactsContract
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.firebasechatapp.dataclass.Contact
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ContactViewModel(application: Application) : AndroidViewModel(application) {

    private val _contactList = MutableLiveData<List<Contact>>()
    val contactList: LiveData<List<Contact>>
        get() = _contactList

    init {
        fetchContacts()
    }

    private fun fetchContacts() {
        GlobalScope.launch(Dispatchers.IO) {
            val contacts = mutableListOf<Contact>()
            val cursor: Cursor? = getApplication<Application>().contentResolver.query(
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                null,
                null,
                null,
                null
            )

            cursor?.use {
                while (it.moveToNext()) {
                    val name =
                        it.getString(it.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME))
                    val number =
                        it.getString(it.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
                    contacts.add(Contact(name, number))
                }
            }

            _contactList.postValue(contacts)
        }
    }
}
