package com.example.contactwithsql.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.contactwithsql.R
import com.example.contactwithsql.sqlite.model.Contact

class ContactAdapter(context: Context, val items : ArrayList<Contact>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_contact_list, parent, false)
        return ContactViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
       val item = items[position]
        if(holder is ContactViewHolder){
            holder.apply {
                name.text = item.name
                number.text = item.phoneNumber
            }
        }
    }

    override fun getItemCount(): Int = items.size

    class ContactViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val name: TextView = view.findViewById<TextView>(R.id.tv_name)
        val number: TextView = view.findViewById<TextView>(R.id.tv_number)
    }
}