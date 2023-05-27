package com.mytrip.myindiatrip.model

class ImageSliderModel {

    lateinit var  key:String
    lateinit var  image:String
    lateinit var  name:String

    constructor(){ }

    constructor(key:String,image:String,name:String){
        this.key=key
        this.image=image
        this.name=name
    }
}