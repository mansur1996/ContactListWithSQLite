package com.example.contactwithsql.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.contactwithsql.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()

    }

    private fun initViews(){
        val openContactList = findViewById<Button>(R.id.btn_contactList)
        val openAddContact = findViewById<Button>(R.id.btn_addContact)

        openContactList.setOnClickListener {
            openContactListActivity()
        }

        openAddContact.setOnClickListener {
            openAddContactActivity()
        }
    }

    private fun openContactListActivity() {
        val intent = Intent(this, ContactListActivity::class.java)
        startActivity(intent)
    }

    private fun openAddContactActivity() {
        val intent = Intent(this, AddContactActivity::class.java)
        startActivity(intent)
    }
}