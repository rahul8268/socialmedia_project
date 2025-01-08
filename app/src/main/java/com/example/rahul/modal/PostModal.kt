package com.example.rahul.modal

data class PostModal(
    var id: Int?,
    var uid: String ="", var image: ByteArray?=null, var caption: String ="",var userName: String=""
)
{
    constructor(uid: String,image: ByteArray?,caption: String,userName: String):this(null,uid,image,caption,userName)
    constructor( uid:String="", image: ByteArray?,caption: String="",userName: String="",profileImage: String) : this(null,uid,image,caption,userName)
   constructor(image: ByteArray?, caption: String, userName: String):this(null,"",image,caption,userName)
    constructor(uid: String,image: ByteArray?):this(null,uid,image,"","")

}
