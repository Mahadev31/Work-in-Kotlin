package com.demo.billingdemo

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class SqliteDatabaseHelper(context: Context) : SQLiteOpenHelper(context, "Database", null, 1) {
    var categoryList = ArrayList<CategoryModelClass>()
    var customerList = ArrayList<CustomerModelClass>()
    var invoiceList = ArrayList<InvoiceModelClass>()
    override fun onCreate(db: SQLiteDatabase?) {
        var categoryTable =
            "create table categoryTB(category_ID integer Primary Key Autoincrement,itemName text,costPrice text,salePrice text)"
        db?.execSQL(categoryTable)
        var customerTable =
            "create table customerTB(customer_ID integer Primary Key Autoincrement,shopName text,customerName text,mobileNumber text)"
        db?.execSQL(customerTable)

        var invoiceItemTable =
            "create table invoiceItemTB(invoiceItem_ID integer Primary Key Autoincrement,itemName text,qty text,salePrice text,total text)"
        db?.execSQL(invoiceItemTable)

        var invoiceTable =
            "create table invoiceTB(invoiceID integer Primary Key Autoincrement,date text,customerName TEXT,itemName text,qty text,salePrice text,total text)"
        db?.execSQL(invoiceTable)

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }

    fun insertCategory(itemName: String, costPrice: String, salePrice: String) {
        var db = writableDatabase
        var contentValues = ContentValues()

        contentValues.put("itemName", itemName)
        contentValues.put("costPrice", costPrice)
        contentValues.put("salePrice", salePrice)
        db.insert("categoryTB", null, contentValues)
        Log.e("TAG", "insertCategory: $itemName   $costPrice  $salePrice")

    }

    fun displayCategory(): ArrayList<CategoryModelClass> {
        categoryList.clear()

        var db = readableDatabase
        var sql = "select * from categoryTB"
        var cursor = db.rawQuery(sql, null)
        if (cursor.moveToFirst()) {

            do {
                var id = cursor.getInt(0)
                var itemName = cursor.getString(1)
                var costName = cursor.getString(2)
                var salePrice = cursor.getString(3)

                var model = CategoryModelClass(id, itemName, costName, salePrice)
                Log.e("TAG", "displayCategory: $itemName$costName$salePrice")
                categoryList.add(model)
            } while (cursor.moveToNext())
        } else {
            Log.e("TAG", "No data found ")
        }
        return categoryList
    }

    fun updateRecord(itemName: String, costPrice: String, salePrice: String, idNumber: Int) {
        var update = writableDatabase
        val updateSql =
            "update categoryTB set itemName ='$itemName',costPrice='$costPrice',salePrice='$salePrice' where category_ID='$idNumber'"
        update.execSQL(updateSql)
    }

    fun deleteRecord(idNumber: Int) {
        val delete = writableDatabase
        val deleteSql = "delete from categoryTB where category_ID='$idNumber'"
        delete.execSQL(deleteSql)
    }

    fun insertInvoiceItemFun(itemName: String, qty: String, salePrice: String, total: String) {
        val db = writableDatabase
        val insertContent = ContentValues()
        insertContent.put("itemName", itemName)
        insertContent.put("qty", qty)
        insertContent.put("salePrice", salePrice)
        insertContent.put("total", total)

        db.insert("invoiceItemTB", null, insertContent)

        Log.e("TAG", "insertInvoiceFun:  $itemName  $qty  $salePrice  $total")
    }

    fun insertCustomerData(shopName: String, customerName: String, mobileNumber: String) {
        val db = writableDatabase
        val insertCustomer = ContentValues()
        insertCustomer.put("shopName", shopName)
        insertCustomer.put("customerName", customerName)
        insertCustomer.put("mobileNumber", mobileNumber)

        db.insert("customerTB", null, insertCustomer)

        Log.e("TAG", "insertCustomerData: $shopName  $customerName $mobileNumber")
    }

    fun displayCustomerData(): ArrayList<CustomerModelClass> {
        customerList.clear()

        var db = readableDatabase
        var sql = "select * from customerTB"
        var cursor = db.rawQuery(sql, null)
        if (cursor.moveToFirst()) {
            do {
                var id = cursor.getInt(0)
                var shopName = cursor.getString(1)
                var customerName = cursor.getString(2)
                var mobileNumber = cursor.getString(3)

                var model = CustomerModelClass(id, shopName, customerName, mobileNumber)

                customerList.add(model)
            } while (cursor.moveToNext())
        } else {
            Log.e("displayCustomerData: ", "No data found")
        }
        return customerList

    }

    fun updateCustomerData(
        shopName: String,
        customerName: String,
        mobileNumber: String,
        id: Int
    ) {
        var update = writableDatabase
        var updateSql =
            "update customerTB set shopName='$shopName',customerName='$customerName',mobileNumber='$mobileNumber' where customer_ID='$id'"
        update.execSQL(updateSql)


    }

    fun deleteCustomerData(idNumber: Int) {
        val delete = writableDatabase
        val deleteSql = "delete from customerTB where customer_ID='$idNumber'"
        delete.execSQL(deleteSql)
    }

    fun insertInvoiceData(
        date: String?,
        selectedShopName: String?,
        itemName: String,
        qty: String,
        price: String,
        total: String
    ) {
        val db = writableDatabase
        val insertCustomer = ContentValues()
        insertCustomer.put("date", date)
        insertCustomer.put("selectedShopName", selectedShopName)
        insertCustomer.put("itemName", itemName)
        insertCustomer.put("qty", qty)
        insertCustomer.put("price", price)
        insertCustomer.put("total", total)

        db.insert("invoiceTB", null, insertCustomer)

        Log.e("TAG", "insertInvoiceData: $date  $selectedShopName  $itemName  $qty  $price  $total" , )
    }
    fun displayInvoiceData(): ArrayList<InvoiceModelClass> {
        invoiceList.clear()

        var db = readableDatabase
        var sql = "select * from invoiceTB"
        var cursor = db.rawQuery(sql, null)
        if (cursor.moveToFirst()) {
            do {
                var id = cursor.getInt(0)
                var date = cursor.getString(1)
                var shopName = cursor.getString(2)

                var model = InvoiceModelClass(id, date, shopName)

                invoiceList.add(model)
            } while (cursor.moveToNext())
        } else {
            Log.e("displayCustomerData: ", "No data found")
        }
        return invoiceList

    }

}


