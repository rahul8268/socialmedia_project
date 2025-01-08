package com.example.rahul.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.rahul.R
import com.example.rahul.convert.Convert

import com.example.rahul.databinding.ActivityCreatePostBinding
import com.example.rahul.modal.PostModal
import com.example.rahul.retrofit.Builder
import com.google.firebase.auth.FirebaseAuth
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.ByteArrayOutputStream

class CreatePostActivity : AppCompatActivity() {
    lateinit var binding: ActivityCreatePostBinding

    lateinit var auth: FirebaseAuth
    lateinit var rprogll: LinearLayout
    lateinit var txtprogress: TextView

    @SuppressLint("SuspiciousIndentation")
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityCreatePostBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = FirebaseAuth.getInstance()

        var action = supportActionBar
        action?.setDisplayHomeAsUpEnabled(true)
        rprogll = findViewById<LinearLayout>(R.id.rprogll)
        txtprogress = findViewById<TextView>(R.id.txtprogress)

        shareData()
       // createPost()



    }
    fun createPost(){

    binding.btnShareCP.setOnClickListener(object : View.OnClickListener{
        @SuppressLint("SuspiciousIndentation")
        override fun onClick(p0: View?) {
          setProgressbar(1)
//            var image=binding.imgPost.toString()
//            var iamgeuri=Uri.parse(image)
            var imagestring=intent.extras?.getString("imgUri")
            var imageuri=Uri.parse(imagestring)
            var caption =binding.txtCaptionCP.text.toString()
            var userName=intent.getStringExtra("userName")
         var image= Convert().uriToByteArray(this@CreatePostActivity,imageuri)
            var uid=auth.currentUser?.uid.toString()
        var postModal= PostModal(uid,image,caption,userName.toString())
          var post= Builder.instance.createPost(postModal).isExecuted

            if (post){
                Toast.makeText(this@CreatePostActivity,"post uploaded", Toast.LENGTH_LONG).show()
                setProgressbar(0)
                startActivity(Intent(this@CreatePostActivity, HomeActivity::class.java))
                finish()
            }else{
                Toast.makeText(this@CreatePostActivity,"please try again", Toast.LENGTH_LONG).show()
                setProgressbar(0)
            }
        }


    })



}

 fun shareData(){

     var imgString=intent.getStringExtra("imgUri")
     var bitmap=intent.extras?.get("imgUri")
     var userName=intent.getStringExtra("userName")
     var stream = ByteArrayOutputStream();

     var byteArray = stream.toByteArray();
     var imgUri= Uri.parse(imgString)
     Glide.with(this).load(imgUri).into(binding.imgPost)
     var convet= Convert()
     var count=0;
     binding.btnShareCP.setOnClickListener(object:View.OnClickListener {
         @SuppressLint("SuspiciousIndentation")
         override fun onClick(p0: View?) {
             setProgressbar(1)

             var caption = binding.txtCaptionCP.text.toString()

             //var imgarr=convet.stringToByteArray(imgString.toString())
             var imgarr = convet.uriToByteArray(this@CreatePostActivity, imgUri)
             var uid = auth.currentUser?.uid.toString()

              Log.d("username",userName.toString())
             var postModal= PostModal(uid,imgarr,caption,userName.toString())
             var post= Builder.instance.createPost(postModal).enqueue(object : Callback<ResponseBody>{
                 override fun onResponse(
                     call: Call<ResponseBody?>,
                     response: Response<ResponseBody?>
                 ) {
                     Log.d("post","post created")
                     setProgressbar(0)
                     startActivity(Intent(this@CreatePostActivity, HomeActivity::class.java))
                     finish()
                 }

                 override fun onFailure(
                     call: Call<ResponseBody?>,
                     t: Throwable
                 ) {
                     Log.d("post","post not created"+t.message)
                     setProgressbar(0)
                 }


             })


         }
     })
 }


         @SuppressLint("SetTextI18n")
         fun setProgressbar(value: Int) {
             //set progress bar for login
             txtprogress.text = "Please wait!! Sharing your post"
             if (value == 1) {

                 rprogll.visibility = View.VISIBLE


             } else {
                 if (value == 0) {
                     rprogll.visibility = View.INVISIBLE

                 }
             }
         }
     }





