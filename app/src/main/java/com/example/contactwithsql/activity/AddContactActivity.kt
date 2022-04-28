package com.example.contactwithsql.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.contactwithsql.R
import com.example.contactwithsql.sqlite.helper.SQLiteHelper
import com.example.contactwithsql.sqlite.model.Contact

class AddContactActivity : AppCompatActivity() {
    private lateinit var nameET : EditText
    private lateinit var numberET : EditText
    private lateinit var sqliteHelper : SQLiteHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_contact)
        sqliteHelper = SQLiteHelper(this)
        initViews()
    }

    private fun initViews(){
        nameET = findViewById(R.id.et_name)
        numberET = findViewById(R.id.et_phoneNumber)
        val addContactBtn = findViewById<Button>(R.id.btn_addContact)
        addContactBtn.setOnClickListener {
            addContactToDB()
        }
    }

    private fun addContactToDB() {
        val name = nameET.text.toString().trim()
        val number = numberET.text.toString().trim()

        if(name.isEmpty() || number.isEmpty()){
            Toast.makeText(this, "Please enter required field", Toast.LENGTH_SHORT).show()
        }else{
            val contact = Contact(name = name, phoneNumber = number)
            val status = sqliteHelper.insertContact(contact)
            //check insert success or not success

            if(status > -1){
                Toast.makeText(this, "Contact added...", Toast.LENGTH_SHORT).show()
                clearEditTexts()
            }else{
                Toast.makeText(this, "Record not saved", Toast.LENGTH_SHORT).show()
            }

        }
    }

    private fun clearEditTexts() {
        nameET.setText("")
        numberET.setText("")
    }
}