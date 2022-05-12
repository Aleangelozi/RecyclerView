package com.aleangelozi.recyclerview

import android.content.Context
import android.content.res.Configuration
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

private const val TAG = "ContactAdapter"

class ContactAdapter(
    private val context: Context,
    private val contacts: List<ContactModel>
) : RecyclerView.Adapter<ContactAdapter.ViewHolder>() {

    // Usually involves inflating a layout from XML and returning the holder - THIS IS EXPANSIVE
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        Log.i(TAG, "onCreateViewHolder")
        val view = LayoutInflater.from(context).inflate(R.layout.item_contact, parent, false)
        return ViewHolder(view)
    }

    //  Involves populating data into the item through holder - NOT expensive
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Log.i(TAG, "onBindViewHolder at position $position")
        val contact = contacts[position]
        holder.bind(contact)
    }

    // Returns the total count of item in the list
    override fun getItemCount(): Int {
        return contacts.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(contact: ContactModel) {
            itemView.findViewById<TextView>(R.id.tv_name).text = contact.name
            itemView.findViewById<TextView>(R.id.tv_age).text = "Age ${contact.age}"
            var imageUrl = contact.imageUrl
            if (context.resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
                imageUrl = contact.landScapeImageUrl
            }
            Glide.with(context).load(imageUrl).into(
                itemView.findViewById(R.id.iv_profile_image)
            )

        }
    }
}