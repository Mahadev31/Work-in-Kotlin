package com.mytrip.myindiatrip.model

 class ModelClass{

     var category_image:String?=null
     var category_name:String?=null
     var image:String?=null
     var name:String?=null
      var key:String?=null
      var child_key:String?=null
      var location:String?=null
      var rating:String?=null
    constructor(){

    }
    constructor(category_image:String?,category_name:String?,image:String?,name:String?,key:String?, child_key:String?,location:String?,rating:String?){

       this. category_image=category_image
       this. category_name=category_name
        this.image=image
        this.name=name
        this.key=key
        this.child_key=child_key
        this.location=location
        this.rating=rating
    }
}
