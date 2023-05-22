package com.mytrip.myindiatrip.model

 class HotelSearchModelClass{
     lateinit var  image:String
     lateinit var  hotelName:String
     lateinit var  hotelRent:String
     lateinit var  hotelRating:String

     constructor(){ }

     constructor(image:String,hotelName:String, hotelRent:String, hotelRating:String){
         this.image=image
         this.hotelName=hotelName
         this.hotelRent=hotelRent
         this.hotelRating=hotelRating
     }
}

