package com.example.sqllitedatabasehelper.forthprogram

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class SQliteHelperClass(context: Context) : SQLiteOpenHelper(context, "ResumeDB", null, 1) {
    var list = ArrayList<ModelClass>()
    override fun onCreate(db: SQLiteDatabase?) {
        val sql =
            "create table ResumeTB(name_ID integer Primary Key Autoincrement,name text,unit integer, price integer)"
        db?.execSQL(sql)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }

    fun insertData(name: String, unit: Int, price: Int) {
        var db = writableDatabase

        list.clear()

        val c = ContentValues()
        c.put("name", name)
        c.put("unit", unit)
        c.put("price", price)


        db.insert("ResumeTB", null, c)
    }

    fun DisplayData(): ArrayList<ModelClass> {
        val db = readableDatabase
        val sql = "select * from ResumeTB"
        val cursor = db.rawQuery(sql, null)
        if (cursor.count > 0) {
            cursor.moveToFirst()
            do {


                val id = cursor.getInt(0)
                val name = cursor.getString(1)
                val unit = cursor.getInt(2)
                val price = cursor.getInt(3)

                val total = unit * price

                val model = ModelClass(id, name, unit, price, total)

                list.add(model)
                Log.e("TAG", "DisplayData: " + name + " " + unit + " " + price)
            } while (cursor.moveToNext())
        } else {
            Log.e("TAG", "DisplayData: " + "No Data Found")
        }
        return list
    }

    fun updateRecord(){

    }
}