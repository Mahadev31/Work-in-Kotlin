package com.mytrip.myindiatrip.model

class SearchModelClass {

    var placeImage: String? =null
    var placeName: String? =null
    var placeLocation: String? =null
    var placeDescription: String? =null

    constructor(){

    }
    constructor(image:String?,placeName:String?,placeLocation:String?,placeDescription:String?){
        this.placeImage=placeImage
        this.placeName=placeName
        this.placeLocation=placeLocation
        this.placeDescription=placeDescription
    }
}