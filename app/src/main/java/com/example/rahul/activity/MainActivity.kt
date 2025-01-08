package com.example.rahul.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.rahul.R
import com.example.rahul.databinding.ActivityMainBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class MainActivity : AppCompatActivity() {
     lateinit var binding: ActivityMainBinding
     lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }



        binding.btnLogin.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {

            startActivity(Intent(applicationContext, LoginActivity::class.java))


            }


        })



        binding.btnRegister.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {

                startActivity(Intent(applicationContext, RegisterActivity::class.java))


            }


        })
auth= FirebaseAuth.getInstance()

        if (auth.currentUser!=null){
            startActivity(Intent(applicationContext, HomeActivity::class.java))
            finishAffinity()
        }

        }


    }



