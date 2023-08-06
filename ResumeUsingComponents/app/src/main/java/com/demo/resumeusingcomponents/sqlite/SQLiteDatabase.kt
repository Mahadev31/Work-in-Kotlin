package com.demo.resumeusingcomponents.sqlite

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.demo.resumeusingcomponents.modelclass.ModelClass

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

    fun checkUserDatabase(email: String?, password: String?): Boolean {
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

    fun resumeDataDisplay(): ArrayList<ModelClass> {
        var list = ArrayList<ModelClass>()
        list.clear()

        var db = readableDatabase
        var sql = "select * from userTb"
        var cursor = db.rawQuery(sql, null)
        if (cursor.count > 0) {
            cursor.moveToFirst()
            do {
                var id = cursor.getInt(0)
                var firstName = cursor.getString(1)
                var lastName = cursor.getString(2)
                var mobileNumber = cursor.getString(3)
                var address = cursor.getString(4)
                var dd = cursor.getString(5)
                var mm = cursor.getString(6)
                var yy = cursor.getString(7)
                var gender = cursor.getString(8)
                var emailId = cursor.getString(9)
                var skill = cursor.getString(10)

                var model =   ModelClass(
                    id,
                    firstName,
                    lastName,
                    mobileNumber,
                    address,
                    dd,
                    mm,
                    yy,
                    gender,
                    emailId,
                    skill
                )


                Log.e("resumeDataDisplay", "f name: "+firstName )
                Log.e("resumeDataDisplay", "l name: "+lastName )
                Log.e("resumeDataDisplay", "mobil number: "+mobileNumber )
                Log.e("resumeDataDisplay", "address: "+address )
                Log.e("resumeDataDisplay", "gender: "+gender )
                Log.e("resumeDataDisplay", "skill: "+skill )
                Log.e("resumeDataDisplay", "dd: "+dd )
                Log.e("resumeDataDisplay", "mm: "+mm )
                Log.e("resumeDataDisplay", "yy: "+yy )

                list.add(model)
            } while (cursor.moveToNext())
        }
        return list
    }

    fun deleteData(id: Int) {
        var db = writableDatabase
        var delete = "delete from resumeTb where resume_Id='$id' "
        db.execSQL(delete)
    }

}


