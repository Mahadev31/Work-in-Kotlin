package com.demo.trialretrofit

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class SqLiteDatabase(context: Context) : SQLiteOpenHelper(context, "Database", null, 1) {
    override fun onCreate(db: SQLiteDatabase?) {
        val table =
            "create table databaseTb(id integer primary key autoincrement,itemName text,price text)"
        db?.execSQL(table)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }

    fun insertData(itemName: String, price: String) {
        var db = writableDatabase
        var c = ContentValues()

        c.put("itemName", itemName)
        c.put("price", price)

        db.insert("databaseTb", null, c)
        Log.e("TAG", "insertData: $itemName  $price")

    }

    fun displayData(): ArrayList<ModelClass> {
        var list = ArrayList<ModelClass>()
        list.clear()
        var db = readableDatabase
        var sql = "select * from  databaseTb"
        var cursor = db.rawQuery(sql, null)
        if (cursor.count > 0) {
            cursor.moveToFirst()
            do {
                var id = cursor.getInt(0)
                var itemName = cursor.getString(1)
                var price = cursor.getString(2)

                var model = ModelClass(id, itemName, price)
                list.add(model)
                Log.e("TAG", "displayData: $id $itemName $price", )
            } while (cursor.moveToNext())

        }

        return list
    }

}