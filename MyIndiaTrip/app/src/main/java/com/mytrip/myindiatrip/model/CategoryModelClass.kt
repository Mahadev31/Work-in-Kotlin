package com.mytrip.myindiatrip.model

 class CategoryModelClass{
    lateinit var categoryImage:String
    lateinit var categoryName:String


    constructor(){

    }
    constructor(image:String,name:String){
        categoryImage=image
        categoryName=name
    }
}

