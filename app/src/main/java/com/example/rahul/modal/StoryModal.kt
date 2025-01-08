package com.example.rahul.modal

import java.sql.Time

data class StoryModal(var profilePic:String="",var userName:String="",var uid: String="")
{
    constructor(userName: String,uid: String):this("",userName,uid)
}
