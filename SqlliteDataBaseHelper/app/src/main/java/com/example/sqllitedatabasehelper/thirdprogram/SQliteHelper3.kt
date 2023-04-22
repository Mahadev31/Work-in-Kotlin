package com.example.sqllitedatabasehelper.thirdprogram

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class SQliteHelper3(context: Context) : SQLiteOpenHelper(context, "ResumeDB", null, 1) {
    var list = ArrayList<Modelclass>()
    override fun onCreate(db: SQLiteDatabase?) {
        val sql =
            "create table ResumeTB(name_ID integer Primary Key Autoincrement,name text,mobile text, address text,email text)"
        db?.execSQL(sql)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }

    fun insertData(name: String, mobile: String, address: String) {
        var db = writableDatabase

        list.clear()

        val c = ContentValues()
        c.put("name", name)
        c.put("mobile", mobile)
        c.put("address", address)

        db.insert("ResumeTB", null, c)
    }

    fun DisplayData(): ArrayList<Modelclass> {
        val db = readableDatabase
        val sql = "select * from ResumeTB where name='raj' or address='mumbai'"
        val cursor = db.rawQuery(sql, null)
        if (cursor.count > 0) {
            cursor.moveToFirst()
            do {


                val id = cursor.getInt(0)
                val name = cursor.getString(1)
                val mobile = cursor.getString(2)
                val address = cursor.getString(3)


                val model = Modelclass(id, name, mobile, address)

                list.add(model)
                Log.e("TAG", "DisplayData: "+id + name + " " + mobile + " " + address)
            } while (cursor.moveToNext())
        } else {
            Log.e("TAG", "DisplayData: " + "No Data Found")
        }
        return list
    }
}