package com.example.rahul.modal

data class ChatModal(
    var name: String ="", var image: ByteArray?, var uid: String ="", var lastMsg: String ="",
    var msg: String ="")
{
    constructor(name: String,uid: String,msg: String):this(name,null,uid,"",msg)

}