package com.mytrip.myindiatrip.model

 class CategoryModelClass{

     var category_image:String?=null
     var category_name:String?=null
     var heritage_image:String?=null
     var heritage_name:String?=null
     var key:String?=null

    constructor(){

    }
    constructor(category_image:String?,category_name:String?,heritage_name:String?,heritage_image:String?,key:String?){

       this. category_image=category_image
       this. category_name=category_name
        this.heritage_name=heritage_name
        this.heritage_image=heritage_image
        this.key=key
    }
}

