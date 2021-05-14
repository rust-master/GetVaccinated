package com.miti.getvaccinated.Model

class ApplicationModel {
    var cnic: String? = null
    var city: String? = null
    var phoneno: String? = null

    constructor() {}
    constructor(cnic: String?, city: String?, phoneno: String?) {
        this.cnic = cnic
        this.city = city
        this.phoneno = phoneno
    }


}