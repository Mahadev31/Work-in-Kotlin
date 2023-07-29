package com.demo.resumeusingcomponents

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class SQLiteDatabase(context: Context) : SQLiteOpenHelper(context, "Database", null, 1) {
    override fun onCreate(db: SQLiteDatabase?) {
        var userTable =
            "create table userTb(userId integer PRIMARY KEY AUTOINCREMENT ,firstName text,lastName text,mobileNumber text,address text,dd text,mm text,yy text,gender text,email text, password text, skills text)"
        db?.execSQL(userTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }

    fun insertDatabase(
        firstName: String?,
        lastName: String?,
        mobileNumber: String?,
        address: String?,
        dd: String?,
        mm: String?,
        yy: String?,
        gender: String,
        email: String?,
        password: String?,
        skills: String?
    ) {

        val db = writableDatabase
        val c = ContentValues()

        c.put("firstName", firstName)
        c.put("lastName", lastName)
        c.put("mobileNumber", mobileNumber)
        c.put("address", address)
        c.put("dd", dd)
        c.put("mm", mm)
        c.put("yy", yy)
        c.put("gender", gender)
        c.put("email", email)
        c.put("password", password)
        c.put("skills", skills)

        Log.e(
            "TAG",
            "insertDatabase: $firstName  $lastName  $mobileNumber  $address  $dd $mm $yy  $gender  $email  $password  $skills"
        )
        db.insert("userTb", null, c)


    }

    fun displayDatabase(email: String?, password: String?): Boolean {
        val db = writableDatabase
        var query = "Select * from userTb  where email='$email' and password='$password'"
        var cursor = db.rawQuery(query, null)
        if (cursor.count <= 0) {
            cursor.close()
            return false
        }
        cursor.close()
        return true
    }


}