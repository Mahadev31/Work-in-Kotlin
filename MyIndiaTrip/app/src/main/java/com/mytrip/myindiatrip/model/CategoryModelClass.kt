package com.mytrip.myindiatrip.model

 class CategoryModelClass{

     var category_image:String?=null
     var category_name:String?=null
     var image:String?=null
     var name:String?=null
      var key:String?=null
      var child_key:String?=null
    constructor(){

    }
    constructor(category_image:String?,category_name:String?,image:String?,name:String?,key:String?, child_key:String?){

       this. category_image=category_image
       this. category_name=category_name
        this.image=image
        this.name=name
        this.key=key
        this.child_key=child_key
    }
}

