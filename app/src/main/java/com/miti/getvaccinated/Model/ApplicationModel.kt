package com.miti.getvaccinated.Model

class ApplicationModel {
    var cnic: String? = null
    var city: String? = null
    var phoneno: String? = null
    var displayName: String? = null
    var email: String? = null

    constructor() {}
    constructor(
        cnic: String?,
        city: String?,
        phoneno: String?,
        displayName: String?,
        email: String?
    ) {
        this.cnic = cnic
        this.city = city
        this.phoneno = phoneno
        this.displayName = displayName
        this.email = email
    }


}