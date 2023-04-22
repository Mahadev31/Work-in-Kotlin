package com.example.sqllitedatabasehelper.secondprogram

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class SqLiteHelper2(context: Context) :SQLiteOpenHelper(context,"CompanyDB",null,1) {

    override fun onCreate(db: SQLiteDatabase?) {
     val sql="create table CompanyTB(Employ_Id integer Primary Key Autoincrement,name text,mobil text)"
        db?.execSQL(sql)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }

    fun insertRecord(name: String, mobil: String) {
        var db=writableDatabase

        val c=ContentValues()
        c.put("name",name)
        c.put("mobil",mobil)
        db.insert("CompanyTB",null,c)

    }

    fun displayRecord(){
        val db=readableDatabase
        val sql="select * from CompanyTB"
        val cursor=db.rawQuery(sql,null)
        if (cursor.count > 0){
            cursor.moveToFirst()
            do {
                val name=cursor.getString(1)
                val mobil=cursor.getString(2)

                Log.e("TAG", "displayRecord: $name $mobil")

            }while (cursor.moveToNext())

        }
        else{
            Log.e("TAG", "no data found:- " )
        }

    }
}