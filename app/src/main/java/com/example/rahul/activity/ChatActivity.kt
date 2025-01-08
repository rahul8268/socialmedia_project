package com.example.rahul.activity

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.rahul.adapter.ChatAdapter
import com.example.rahul.databinding.ActivityChatBinding
import com.example.rahul.modal.ChatModal
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class ChatActivity : AppCompatActivity() {
     lateinit var binding: ActivityChatBinding
     lateinit var auth:FirebaseAuth
     lateinit var database: FirebaseDatabase
     lateinit var adapter: ChatAdapter
     lateinit var list: ArrayList<ChatModal>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityChatBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth= FirebaseAuth.getInstance()
        database= FirebaseDatabase.getInstance()
        list= ArrayList<ChatModal>()

        sendMsg()
        getmsg()

        adapter= ChatAdapter(list)
        binding.recviewchat.adapter=adapter

    }

    fun getmsg(){

        database.reference.child("chat").addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                for (snapshot: DataSnapshot in snapshot.children){
                    var data=snapshot.getValue<ChatModal>(ChatModal::class.java)
                    var msg=data?.msg
                    var uid=snapshot.value
                    Log.d("uidvalue",uid.toString())

                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })

    }

    fun sendMsg(){

        binding.sendmsg.setOnClickListener{
            var msg=binding.txtmsg.text.toString()
            var uid=intent.getStringExtra("uid")
            var name=intent.getStringExtra("name")
            var authuid=auth.currentUser?.uid
            var modal= ChatModal(name.toString(), uid.toString(),msg.toString())


            database.reference.child("chat").child(authuid+uid).setValue(modal)
                .addOnCompleteListener{

                    if (it.isSuccessful){
                        binding.txtmsg.setText("")
                    }
                }

            database.reference.child("chat").child(uid+authuid).setValue(modal)
                .addOnCompleteListener{

                    if (it.isSuccessful){

                    }
                }






        }

    }
}