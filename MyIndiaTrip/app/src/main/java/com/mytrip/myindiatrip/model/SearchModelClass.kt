package com.mytrip.myindiatrip.model

class SearchModelClass {

    var key: String? = null
    var placeImage: String? = null
    var placeName: String? = null
    var placeLocation: String? = null
    var placeDescription: String? = null
    var placeRating: String? = null

    constructor() {

    }

    constructor(
        key: String?,
        placeImage: String?,
        placeName: String?,
        placeLocation: String?,
        placeDescription: String?,
        placeRating: String?
    ) {
        this.key = key
        this.placeImage = placeImage
        this.placeName = placeName
        this.placeLocation = placeLocation
        this.placeDescription = placeDescription
        this.placeRating = placeRating
    }
}