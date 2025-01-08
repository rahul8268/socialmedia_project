package com.example.rahul.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.rahul.adapter.PostSeenAdapter
import com.example.rahul.adapter.ProfilePostAdapter
import com.example.rahul.databinding.ActivityProfileBinding
import com.example.rahul.modal.PostModal
import com.example.rahul.modal.ProfilePost
import com.example.rahul.retrofit.Builder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfileActivity : AppCompatActivity() {
    lateinit var binding: ActivityProfileBinding
    lateinit var list: ArrayList<PostModal>
    lateinit var adapter: ProfilePostAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
      binding= ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        list= ArrayList<PostModal>()

      var name=intent.getStringExtra("name")

      binding.toolbar.title=name

        var layoutManager= GridLayoutManager(this,3)
        binding.recView.layoutManager=layoutManager

       var count=0;
        Builder.instance.getPostData().enqueue(object :Callback<List<PostModal>>{
            override fun onResponse(
                call: Call<List<PostModal>?>,
                response: Response<List<PostModal>?>
            ) {
                response.body()?.forEach {
                    var curruid=intent.getStringExtra("uid")
                    var currname=intent.getStringExtra("name")
                //uid    var currimage=intent.g
                    //
                    //etStringExtra("image")
                    var uid=it.uid
                    var userName=it.userName
                    var image=it.image

                    if (uid==curruid &&userName==currname){
                        count++
                        var modal= PostModal(uid,image)
                        list.add(modal)
                     }
                 adapter.notifyDataSetChanged()
                }
                binding.txtposts.text=""+count
            }

            override fun onFailure(
                call: Call<List<PostModal>?>,
                t: Throwable
            ) {
                TODO("Not yet implemented")
            }


        })


         adapter= ProfilePostAdapter(list,this)
        binding.recView.adapter=adapter


    }
}