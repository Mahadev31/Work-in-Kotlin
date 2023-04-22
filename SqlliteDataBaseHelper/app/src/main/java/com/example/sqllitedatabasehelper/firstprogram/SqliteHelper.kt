package com.example.sqllitedatabasehelper.firstprogram

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class SqliteHelper(context : Context) : SQLiteOpenHelper(context,"studentDb",null,1) {
    override fun onCreate(db: SQLiteDatabase?) {
        val sql="create table studentTb (student_Id integer primary key Autoincrement,name text,mobil text,gender text, fees integer)"
       db?.execSQL(sql)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }
    fun info(){
        val db=writableDatabase
        val s= "insert into studentTb (name,mobil,gender,fees) values('vijay','7895638478','Male',20000)"
        val s1= "insert into studentTb (name,mobil,gender,fees) values('jay','22444','Male',30000)"
        val s2= "insert into studentTb (name,mobil,gender,fees) values('raj','4444425','Male',50000)"
        val s3= "insert into studentTb (name,mobil,gender,fees) values('kiran','242424','Male',40000)"
        val s4= "insert into studentTb (name,mobil,gender,fees) values('vinay','2242','Male',10000)"
        db.execSQL(s)
        db.execSQL(s1)
        db.execSQL(s2)
        db.execSQL(s3)
        db.execSQL(s4)
    }
}