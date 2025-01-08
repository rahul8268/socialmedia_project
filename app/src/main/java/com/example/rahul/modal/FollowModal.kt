package com.example.rahul.modal

data class FollowModal(
    var id: Long? =0,
    var count: Int =0,
    var uid: String ="",
    var userName: String ="",
    var followUser: String ="")

{
    constructor(count: Int,uid: String,userName: String,followUser: String):this(null,count,uid,userName,followUser)
    constructor(uid: String,userName: String,followUser: String):this(null,0,uid,userName,followUser)

}