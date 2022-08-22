package com.example.appdate.data

import android.media.Image
import android.net.Uri
import java.text.SimpleDateFormat

class User {
    private var name : String
    private var birthday : SimpleDateFormat

    constructor() {
        this.name = "Name"
        this.birthday = SimpleDateFormat("dd/MM/yyyy")
    }

    constructor(name: String, birthday: SimpleDateFormat) {
        this.name = name
        this.birthday = birthday
    }


    fun getName() : String{
        return name
    }

    fun getBirthday() : SimpleDateFormat{
        return birthday
    }

    fun setName(value: String){
        name = value
    }

    fun setBirthday(value: SimpleDateFormat){
        birthday = value
    }

}