package com.example.contactwithsql.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.contactwithsql.R
import com.example.contactwithsql.adapter.ContactAdapter
import com.example.contactwithsql.sqlite.helper.SQLiteHelper
import com.example.contactwithsql.sqlite.model.Contact

class ContactListActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var sqliteHelper : SQLiteHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_list)
        sqliteHelper = SQLiteHelper(this)
        initView()
    }

    private fun initView(){
        recyclerView = findViewById(R.id.rv_contactList)
        recyclerView.layoutManager = GridLayoutManager(this, 1)

        refreshAdapter(sqliteHelper.getAllContacts())
    }

    private fun refreshAdapter(contacts : ArrayList<Contact>) {
        val adapter = ContactAdapter(this, contacts)
        recyclerView.adapter = adapter
    }
}