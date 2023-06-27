package com.demo.billingdemo

data class CategoryModelClass(
    val id: Int,
    var itemName: String,
    var costPrice: String,
    var salePrice: String
)

data class AddItemModelClass(

    var id: Int,
    var itemName: String,
    var qty: String,
    var price: String,
    var total: String

)

data class ProductModel(var ProductList: ArrayList<AddItemModelClass>)
data class CustomerModelClass(

    var id: Int,
    var shopName: String,
    var customerName: String,
    var mobileNumber: String


)

data class InvoiceModelClass(

    var id: Int,
    var date: String,
    var customerName: String,


)