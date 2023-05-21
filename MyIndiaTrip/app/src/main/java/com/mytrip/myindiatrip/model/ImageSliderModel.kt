package com.mytrip.myindiatrip.model

class ImageSliderModel {

    lateinit var  image:String
    lateinit var  name:String

    constructor(){ }

    constructor(image:String,name:String){
        this.image=image
        this.name=name
    }
}