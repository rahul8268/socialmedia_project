package com.example.rahul.activity

import android.annotation.SuppressLint

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

import com.example.rahul.R

import com.example.rahul.databinding.ActivityRegisterBinding
import com.example.rahul.modal.Users
import com.example.rahul.retrofit.Builder
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response

class RegisterActivity : AppCompatActivity() {
    lateinit var binding: ActivityRegisterBinding
    lateinit var auth: FirebaseAuth
    lateinit var database: FirebaseDatabase
    lateinit var rprogll: LinearLayout
    lateinit var progress:ProgressBar

    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

            binding = ActivityRegisterBinding.inflate(layoutInflater)
            setContentView(binding.root)
       // include progress layout id
        rprogll=findViewById<LinearLayout>(R.id.rprogll)
        progress=findViewById<ProgressBar>(R.id.progress)


            onclickBtn()
            addUser()



        }

    fun setUsers(){


    }

    fun setProgressbar(value:Int){
  // set progress dialog in register time

        if (value==1){

            rprogll.visibility= View.VISIBLE
            binding.rdatall.visibility= View.INVISIBLE
            progress.visibility= View.VISIBLE


        }else{
            if (value==0){
                rprogll.visibility= View.INVISIBLE
                binding.rdatall.visibility= View.VISIBLE
                progress.visibility= View.INVISIBLE
            }
        }
    }

     fun addUser(){
         // Register user in firebase
         auth= FirebaseAuth.getInstance()
         database= FirebaseDatabase.getInstance()


         binding.rbtnRegister.setOnClickListener(object : View.OnClickListener{
             override fun onClick(p0: View?) {
                 setProgressbar(1)

                 var name=binding.rName.text.toString()
                 var email=binding.rEmail.text.toString()
                 var password=binding.rPassword.text.toString()




                 if (name!=""&&email!=""&&password!=""){

                     var users= Users("",name,email,password)
                     Log.d("data",users.uid + "name"+users.userName+ "pass"+users.password+ "email"+users.email)
                     auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener({
                        if (it.isSuccessful){
                            var users= Users(auth.uid.toString(),name,email,password)
                           var check= Builder.instance.creatUsers(users).enqueue(object:retrofit2.Callback<ResponseBody>{
                               override fun onResponse(
                                   call: Call<ResponseBody?>,
                                   response: Response<ResponseBody?>
                               ) {
                                   Toast.makeText(this@RegisterActivity,"user inserted", Toast.LENGTH_LONG).show()

                               }

                               override fun onFailure(
                                   call: Call<ResponseBody?>,
                                   t: Throwable
                               ) {
                                   Log.d("error",t.message.toString())
                               }


                           })


                          database.reference.child("Users").child(auth.uid.toString()).setValue(users).addOnCompleteListener({

                              if (it.isSuccessful){

                                      Toast.makeText(
                                          applicationContext, "account created! Please Login",
                                          Toast.LENGTH_LONG
                                      ).show()

                                      startActivity(
                                          Intent(
                                              applicationContext,
                                              LoginActivity::class.java
                                          )
                                      )
                                      setProgressbar(0);
                                      finishAffinity()

                              }else{
                                  Log.d("db",it.exception?.message.toString())
                                  setProgressbar(0);
                              }
                          })


                        }else{
                            Toast.makeText(applicationContext," ${it.exception?.message}", Toast.LENGTH_LONG).show()
                            setProgressbar(0);
                        }

                     })

                 }else{
                     Toast.makeText(applicationContext,"Please fill the blanks", Toast.LENGTH_LONG).show()
                     setProgressbar(0);
                 }




             }

         })





     }
    fun onclickBtn(){
       //click buttons
        binding.rAllready.setOnClickListener(object :View.OnClickListener{
            override fun onClick(p0: View?) {

        startActivity(Intent(applicationContext, MainActivity::class.java))
        finish()

            }
        })

    }

}
