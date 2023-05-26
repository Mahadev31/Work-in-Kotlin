package com.mytrip.myindiatrip.model

 class CategoryModelClass{

     var category_image:String?=null
     var category_name:String?=null
     var place_image:String?=null
     var place_name:String?=null
     lateinit var key:String
    constructor(){

    }
    constructor(category_image:String?,category_name:String?,place_image:String?,place_name:String?,key:String){

       this. category_image=category_image
       this. category_name=category_name
        this.place_image=place_image
        this.place_name=place_name
        this.key=key
    }
}

