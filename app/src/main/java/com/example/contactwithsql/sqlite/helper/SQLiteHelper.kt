package com.example.contactwithsql.sqlite.helper

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.contactwithsql.sqlite.model.Contact

class SQLiteHelper(context: Context) :SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION){

    companion object{
        private const val DATABASE_NAME = "myContact.db"
        private const val DATABASE_VERSION = 1
        private const val TABLE_NAME = "contact"
        private const val ID = "id"
        private const val NAME = "name"
        private const val PHONE_NUMBER = "phone_number"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTableContact = ("CREATE TABLE " + TABLE_NAME + "("
                + ID + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " + NAME + " TEXT NOT NULL, "
                + PHONE_NUMBER + " TEXT NOT NULL )")
        db?.execSQL(createTableContact)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    fun insertContact(contact: Contact) : Long{
        val db = this.writableDatabase

        val contentValue = ContentValues()
        contentValue.put(NAME, contact.name)
        contentValue.put(PHONE_NUMBER, contact.phoneNumber)

        val success = db.insert(TABLE_NAME, null, contentValue)
        db.close()
        return success
    }

    @SuppressLint("Range")
    fun getAllContacts() : ArrayList<Contact>{
        val contactList : ArrayList<Contact> = ArrayList()
        val selectQuery = "SELECT * FROM $TABLE_NAME"
        val db = this.readableDatabase

        val cursor : Cursor?

        try {
            cursor = db.rawQuery(selectQuery, null)
        }catch (e : Exception){
            e.printStackTrace()
            db.execSQL(selectQuery)
            return ArrayList()
        }

        var id : Int
        var name : String
        var phoneNumber : String

        if(cursor.moveToFirst()){
            do{
                id = cursor.getInt(cursor.getColumnIndex("id"))
                name = cursor.getString(cursor.getColumnIndex("name"))
                phoneNumber = cursor.getString(cursor.getColumnIndex("phone_number"))


                val contact = Contact(id = id, name = name, phoneNumber = phoneNumber)
                contactList.add(contact)
            }while (cursor.moveToNext())
        }
        return contactList
    }

}