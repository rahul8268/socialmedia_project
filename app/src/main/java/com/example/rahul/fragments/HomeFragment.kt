package com.example.rahul.fragments

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.contract.ActivityResultContracts

import com.example.rahul.activity.CreatePostActivity

import com.example.rahul.adapter.PostSeenAdapter
import com.example.rahul.databinding.FragmentHomeBinding
import com.example.rahul.modal.PostModal

import com.example.rahul.modal.Users
import com.example.rahul.retrofit.Builder

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.collections.forEach
import kotlin.toString


class HomeFragment(var ncontext: Context) : Fragment() {
lateinit var binding: FragmentHomeBinding
lateinit var postlist: ArrayList<PostModal>

lateinit var database: FirebaseDatabase
lateinit var auth: FirebaseAuth
lateinit var adapter: PostSeenAdapter

    @SuppressLint("SuspiciousIndentation")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
         binding= FragmentHomeBinding.inflate(layoutInflater,container,false)
        database= FirebaseDatabase.getInstance()
         auth= FirebaseAuth.getInstance()
        postlist= ArrayList<PostModal>()

        getPostData()
         createPost()
        getUsers()


        adapter=PostSeenAdapter(postlist,ncontext)
         binding.homeRecView.adapter=adapter


        return binding.root
    }




    fun getPostData(){

        Builder.instance.getPostData().enqueue(object: Callback<List<PostModal>>{
            @SuppressLint("NotifyDataSetChanged")
            override fun onResponse(
                call: Call<List<PostModal>?>,
                response: Response<List<PostModal>?>
            ) {

                var body=response.body()?.forEach{

                    var uid=it.uid.toString()
                    var image=it.image
                    var caption=it.caption.toString()
                    var user_name=it.userName.toString()

                  var modal= PostModal(uid,image,caption,user_name)

                    postlist.add(modal)
                }

                adapter.notifyDataSetChanged()
            }

            override fun onFailure(
                call: Call<List<PostModal>?>,
                t: Throwable
            ) {
               t.printStackTrace()
                Log.e("post",t.message.toString())
            }


        })
    }

    fun createPost() {

        var picImage = registerForActivityResult(ActivityResultContracts.GetContent(),
            ActivityResultCallback {

                var intent = Intent(context, CreatePostActivity::class.java)
                intent.putExtra("imgUri", it.toString())
                intent.putExtra("userName", binding.userName.text.toString())
                startActivity(intent)


            })


        binding.addData.setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View?) {

                picImage.launch("image/*")
            }


        })
    }


    fun getUsers(){

        Builder.instance.getUSers().enqueue(object : Callback<List<Users>>{
            override fun onResponse(
                call: Call<List<Users>?>,
                response: Response<List<Users>?>
            ){
                var authuid=auth.currentUser?.uid.toString()
                response.body()?.forEach {


                    var uid=it.uid.toString()
                    var userName=it.userName.toString()
                    Log.d("uid",uid.toString())

                    if (authuid==uid){
                        Log.d("uid2",uid.toString())
                        binding.userName.text=userName
                    }
                }

            }

            override fun onFailure(
                call: Call<List<Users>?>,
                t: Throwable
            ) {
                t.printStackTrace()
                Log.d("erore",t.message.toString())
            }


        })


    }



}




