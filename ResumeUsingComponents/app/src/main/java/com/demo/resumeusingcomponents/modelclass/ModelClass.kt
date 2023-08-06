package com.demo.resumeusingcomponents.modelclass

class ModelClass {
    var id: Int = 0
    var firstName: String? = null
    var lastName: String? = null
    var mobileNumber: String? = null
    var address: String? = null
    var dd: String? = null
    var mm: String? = null
    var yy: String? = null
    var gender: String? = null
    var emailId: String? = null
    var skill: String? = null

    constructor(
        id: Int,
        firstName: String?,
        lastName: String?,
        mobileNumber: String?,
        address: String?,
        dd: String?,
        mm: String?,
        yy: String?,
        gender: String?,
        emailId: String?,
        skill: String?
    ) {
        this.id = id
        this.firstName = firstName
        this.lastName = lastName
        this.mobileNumber = mobileNumber
        this.address = address
        this.dd = dd
        this.mm = mm
        this.yy = yy
        this.gender = gender
        this.emailId = emailId
        this.skill = skill
    }

    constructor() {}
}