package com.example.appdate.data

import java.text.SimpleDateFormat
import java.util.*

class Users {
    private var userOne : User
    private var userTow : User
    private var date : SimpleDateFormat

    constructor() {
        this.userOne = User()
        this.userTow = User()
        this.date = SimpleDateFormat("dd/MM/yyyy")
    }

    constructor(userOne: User, userTow: User, date: SimpleDateFormat) {
        this.userOne = userOne
        this.userTow = userTow
        this.date = date
    }

    fun getUser1() : User{
        return userOne
    }

    fun getUser2() : User{
        return userTow
    }

    fun getDate() : SimpleDateFormat{
        return date
    }

    fun setDate(value : SimpleDateFormat){
        this.date = value
    }


}