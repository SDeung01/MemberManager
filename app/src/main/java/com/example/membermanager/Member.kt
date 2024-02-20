package com.example.membermanager

import android.content.ContentValues

data class Member(var name: String, var age: String, var mobile:String){
    var memberId: String? = null
    constructor(name: String, age: String, mobile:String, memberId:String): this(name, age, mobile){
        this.name = name
        this.age = age
        this.mobile = mobile
        this.memberId = memberId
    }
    fun values(): ContentValues{
        val values = ContentValues()
        values.put("name", name)
        values.put("age", age)
        values.put("mobile", mobile)
        return values
    }
}
