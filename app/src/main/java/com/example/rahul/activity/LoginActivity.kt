package com.example.rahul.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.rahul.R
import com.example.rahul.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth
import java.io.DataOutputStream

class LoginActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding
    lateinit var auth: FirebaseAuth
    lateinit var rprogll: LinearLayout
    lateinit var txtprogress: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        txtprogress = findViewById<TextView>(R.id.txtprogress)

        //include progress bar layout id
        rprogll = findViewById<LinearLayout>(R.id.rprogll)
        // click create accoumt button
        onclickBtn()
        // login user
        loginUser()



    }
    fun setProgressbar(value: Int){
      //set progress bar for login
        txtprogress.text= getString(R.string.login_your)
        if (value==1){

            rprogll.visibility= View.VISIBLE
            binding.ldatall.visibility= View.INVISIBLE

        }else{
            if (value==0){
                rprogll.visibility= View.INVISIBLE
                binding.ldatall.visibility= View.VISIBLE
            }
        }
    }

    fun loginUser(){
        // login user with email and password

        auth= FirebaseAuth.getInstance()


        binding.lLogin.setOnClickListener(object : View.OnClickListener{
            override fun onClick(p0: View?) {
                setProgressbar(1)
                var email=binding.lEmail.text.toString()
                var password=binding.lpassword.text.toString()

                if (email!=""&&password!=""){

                    auth.signInWithEmailAndPassword(email,password).addOnCompleteListener({
                        if (it.isSuccessful){

                            startActivity(Intent(applicationContext, HomeActivity::class.java))
                            setProgressbar(0)
                            Toast.makeText(applicationContext,"welcome", Toast.LENGTH_LONG).show()
                            finishAffinity()
                        }else{
                            Toast.makeText(applicationContext,"${it.exception?.message}", Toast.LENGTH_LONG).show()
                                setProgressbar(0)
                        }

                    })
                }else{
                    Toast.makeText(applicationContext,"please fill the blanks", Toast.LENGTH_LONG).show()
                    setProgressbar(0)
                }



            }


        })




    }

    fun onclickBtn(){
     //click buttons for create new account
        binding.lCreate.setOnClickListener(object :View.OnClickListener{
            override fun onClick(p0: View?) {

                startActivity(Intent(applicationContext, MainActivity::class.java))
                finish()

            }
        })

    }
}