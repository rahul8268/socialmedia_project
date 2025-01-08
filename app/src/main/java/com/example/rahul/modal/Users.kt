package com.example.rahul.modal

data class Users(
    var uid: String="", var userName: String="", var email:String="", var password:String="")
{
    constructor(uid: String,userName: String):this(uid,userName,"")


}

