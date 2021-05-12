package com.miti.getvaccinated.Model

class ApplicationModel {
    var cnic: String? = null
    var city: String? = null


    constructor() {}
    constructor(cnic: String?, city: String?) {
        this.cnic = cnic
        this.city = city
    }


}