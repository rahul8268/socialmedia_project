package com.example.rahul.fragments


import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.example.rahul.convert.Convert
import com.example.rahul.databinding.FragmentProfileBinding
import com.example.rahul.modal.ProfilePost
import com.example.rahul.retrofit.Builder
import com.google.firebase.auth.FirebaseAuth
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.example.rahul.R
import com.example.rahul.activity.SettingActivity
import com.example.rahul.adapter.ProfilePostAdapter
import com.example.rahul.modal.PostModal
import com.example.rahul.modal.Users


class ProfileFragment(var contextnew: Context) : Fragment() {
   lateinit var logout: Button
   lateinit var auth: FirebaseAuth
  lateinit var binding: FragmentProfileBinding
  lateinit var listPost: ArrayList<PostModal>
  lateinit var adapter: ProfilePostAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

         binding= FragmentProfileBinding.inflate(layoutInflater,container,false)
          auth= FirebaseAuth.getInstance()

         addProfile()
         getProfile(contextnew)
        chechPost()
        recViewWork()
        toolbarSetup()

        return binding.root
    }


    fun toolbarSetup(){

        binding.settting.setOnClickListener(object : View.OnClickListener{
            override fun onClick(p0: View?) {
            contextnew.startActivity(Intent(contextnew, SettingActivity::class.java))

            }


        })



        Builder.instance.getUSers().enqueue(object : Callback<List<Users>>{
            override fun onResponse(
                call: Call<List<Users>?>,
                response: Response<List<Users>?>
            ) {
                response.body()?.forEach {
                    var uid=it.uid
                    var name=it.userName

                    if (uid==auth.currentUser?.uid){
                      binding.profileUserName.text=name
                    }
                }
            }

            override fun onFailure(
                call: Call<List<Users>?>,
                t: Throwable
            ) {
                TODO("Not yet implemented")
            }


        })

    }


    fun recViewWork(){

        binding.recView.layoutManager= GridLayoutManager(contextnew,3)

        listPost= ArrayList<PostModal>()



        Builder.instance.getPostData().enqueue(object :Callback<List<PostModal>>{
            override fun onResponse(
                call: Call<List<PostModal>?>,
                response: Response<List<PostModal>?>
            ) {
                response.body()?.forEach{
                    var image=it.image
                    var uid=it.uid

                    if (uid==auth.currentUser?.uid)
                    {

                        var modal= PostModal(uid,image)
                        listPost.add(modal)
                    }
                    adapter.notifyDataSetChanged()
                }
            }

            override fun onFailure(
                call: Call<List<PostModal>?>,
                t: Throwable
            ) {
                TODO("Not yet implemented")
            }


        })

         adapter= ProfilePostAdapter(listPost,contextnew)
        binding.recView.adapter=adapter


    }

    fun checkFollower(){




    }


    fun chechPost(){
        Builder.instance.getPostData().enqueue(object : Callback<List<PostModal>>{
            override fun onResponse(
                call: Call<List<PostModal>?>,
                response: Response<List<PostModal>?>
            ) {
                var count=0
                response.body()?.forEach {
                    var uid=it.uid
                    if (uid==auth.currentUser?.uid) {
                        count++
                    }
                }
                binding.txtposts.text=count.toString()
            }

            override fun onFailure(
                call: Call<List<PostModal>?>,
                t: Throwable
            ) {
                TODO("Not yet implemented")
            }


        })
    }


    fun  getProfile(context: Context){

        Builder.instance.getProfile().enqueue(object : Callback<List<ProfilePost>>{
            override fun onResponse(
                call: Call<List<ProfilePost>?>,
                response: Response<List<ProfilePost>?>
            ) {
                response.body()?.forEach {
                    var uid=it.uid.toString()
                    var image=it.image
                    if (uid==auth.currentUser?.uid)
                  Glide.with(context).load(image).placeholder(R.drawable.profile).into(binding.profileImage)
                }
            }

            override fun onFailure(
                call: Call<List<ProfilePost>?>,
                t: Throwable
            ) {
                TODO("Not yet implemented")
            }


        })
    }

    fun addProfile(){

        var picImage=registerForActivityResult(ActivityResultContracts.GetContent(),
            ActivityResultCallback{
                var image=Uri.parse(it.toString())

                var imagebyte=Convert().uriToByteArray(context,image)
                var post= ProfilePost(imagebyte,auth.currentUser?.uid.toString())
                Builder.instance.insertProfile(post).enqueue(object :Callback<ResponseBody>{
                    override fun onResponse(
                        call: Call<ResponseBody?>,
                        response: Response<ResponseBody?>
                    ) {
                        if (response.isSuccessful) {
                            Toast.makeText(context, "data saved", Toast.LENGTH_LONG).show()

                            getProfile(contextnew)

                        }

                    }

                    override fun onFailure(
                        call: Call<ResponseBody?>,
                        t: Throwable
                    ) {
                      Log.d("erroor",t.message.toString())
                    }

                })

            })

        binding.addprofile.setOnClickListener(object : View.OnClickListener{
            override fun onClick(p0: View?) {

             picImage.launch("image/*")

            }


        })
    }

}