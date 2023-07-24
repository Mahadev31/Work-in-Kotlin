package com.demo.resumeusingcomponents

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class SQLiteDatabase(context: Context):SQLiteOpenHelper(context,"Database",null,1) {
    override fun onCreate(db: SQLiteDatabase?) {
        var userTable="create table userTb(userId integer PRIMARY KEY AUTOINCREMENT ,firstName text,lastName text,mobileNumber text,address text,dd text,mm text,yy text,gender text,email text, password text)"
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
        password: String?
    ) {

        val db=writableDatabase
        val c=ContentValues()

        c.put("firstName",firstName)
        c.put("lastName",lastName)
        c.put("firstName",mobileNumber)
        c.put("firstName",address)
        c.put("firstName",dd)
        c.put("firstName",mm)
        c.put("firstName",yy)
        c.put("firstName",gender)
        c.put("firstName",email)
        c.put("firstName",password)

        Log.e("TAG",
            "insertDatabase: $firstName  $lastName  $mobileNumber  $address  $dd $mm $yy  $gender  $email  $password"
        )
        db.insert("userTb",null,c)



    }


}