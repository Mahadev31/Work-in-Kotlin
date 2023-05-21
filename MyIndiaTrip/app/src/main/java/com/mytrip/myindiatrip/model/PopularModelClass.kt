package com.mytrip.myindiatrip.model

 class PopularModelClass{
    lateinit var popularImage:String
    lateinit var popularName:String


    constructor(){

    }
    constructor(image:String,name:String){
        popularImage=image
        popularName=name
    }
}

