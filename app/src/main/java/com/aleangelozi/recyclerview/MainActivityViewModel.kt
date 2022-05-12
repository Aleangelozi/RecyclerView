package com.aleangelozi.recyclerview

import android.os.Handler
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

private const val TAG = "MainActivityViewModel"

class MainActivityViewModel : ViewModel() {
    private val contactsLiveData: MutableLiveData<MutableList<ContactModel>>
    private val isRefreshingLiveData: MutableLiveData<Boolean>

    init {
        Log.i(TAG, "init")
        contactsLiveData = MutableLiveData()
        contactsLiveData.value = createContacts()
        isRefreshingLiveData = MutableLiveData()
        isRefreshingLiveData.value = false
    }

    private fun createContacts(): MutableList<ContactModel> {
        val contacts = mutableListOf<ContactModel>()
        for (i in 1..50) {
            contacts.add(ContactModel("Person #$i", i))
        }
        return contacts
    }

    fun getContacts(): LiveData<MutableList<ContactModel>> {
        return contactsLiveData
    }

    fun isRefreshingLiveData(): LiveData<Boolean> {
        return isRefreshingLiveData
    }

    fun fetchNewContact() {
        Log.i(TAG, "fetchNewContact")
        isRefreshingLiveData.value = true
        Handler().postDelayed(Runnable {
            val contacts = contactsLiveData.value
            contacts?.add(0, ContactModel("Alessandro Ribeiro", 36))
            contactsLiveData.value = contacts
            isRefreshingLiveData.value = false
        }, 1_000)


    }

}