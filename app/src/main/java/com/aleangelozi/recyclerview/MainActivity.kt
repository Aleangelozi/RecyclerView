package com.aleangelozi.recyclerview

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.aleangelozi.recyclerview.databinding.ActivityMainBinding

private const val TAG = "MainActivity"
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        Log.i(TAG, "onCreate")

        val contacts = mutableListOf<ContactModel>()
        val adapter = ContactAdapter(this, contacts)
        binding.rvContacts.adapter = adapter
        binding.rvContacts.layoutManager = LinearLayoutManager(this)

        val viewModel = ViewModelProviders.of(this)[MainActivityViewModel::class.java]
        viewModel.getContacts().observe(this, Observer { contactsSnapshot ->
            Log.i(TAG, "Received contacts from view model")
            contacts.clear()
            contacts.addAll(contactsSnapshot)
            adapter.notifyDataSetChanged()
        })

        viewModel.isRefreshingLiveData().observe(this, Observer { isRefreshing ->
            binding.swipeContainer.isRefreshing = isRefreshing
        })

        binding.swipeContainer.setOnRefreshListener {
            viewModel.fetchNewContact()
        }
    }
}