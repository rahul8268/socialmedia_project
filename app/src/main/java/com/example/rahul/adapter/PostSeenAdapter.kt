package com.example.rahul.adapter

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener

import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.rahul.activity.ChatActivity
import com.example.rahul.activity.ProfileActivity
import com.example.rahul.databinding.HomeRecLayoutBinding
import com.example.rahul.modal.FollowModal
import com.example.rahul.modal.PostModal
import com.example.rahul.modal.ProfilePost
import com.example.rahul.retrofit.Builder
import com.google.firebase.auth.FirebaseAuth
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.collections.forEach


class PostSeenAdapter(
    var postlist: ArrayList<PostModal>,
    var context: Context
):
    RecyclerView.Adapter<PostSeenAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PostSeenAdapter.ViewHolder {
        var binding= HomeRecLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)


        return ViewHolder(binding)
    }

    @SuppressLint("SetTextI18n", "ClickableViewAccessibility")
    override fun onBindViewHolder(holder: PostSeenAdapter.ViewHolder, @SuppressLint("RecyclerView") position: Int) {
       var list=postlist[position]
        var count=0L;
        var check=true;
       var auth= FirebaseAuth.getInstance()
        var authuid=auth.currentUser?.uid.toString()


           var nuid=list.uid
            var userName=list.userName

                checkFollow(nuid, holder, context, userName,authuid)




        holder.binding.homemsg.setOnClickListener{
            var intent=Intent(context, ChatActivity::class.java)
            intent.putExtra("uid",list.uid)
            intent.putExtra("name",list.userName)
            context.startActivity(intent)
            Activity().finish()
        }


        holder.binding.homeuserName.text=postlist.get(position).userName
        Glide.with(context).load(list.image).placeholder(com.example.rahul.R.drawable.manimage).into(holder.binding.homeImgPost)
        holder.binding.homecaption.text=list.caption
        if (list.uid.toString()==authuid){
            holder.binding.homefollow.visibility=View.INVISIBLE

        }

       // fun profile() {
            Builder.instance.getProfile().enqueue(object : Callback<List<ProfilePost>> {
                override fun onResponse(
                    call: Call<List<ProfilePost>?>,
                    response: Response<List<ProfilePost>?>
                ) {
                    response.body()?.forEach {
                        var prouid = it.uid
                        var proimage = it.image
                        var uid = list.uid
                        if (uid == prouid) {
                            Log.d("uidpro", proimage.toString())
                            Glide.with(context).load(proimage)
                                .placeholder(com.example.rahul.R.drawable.profile)
                                .into(holder.binding.homeProfileImage)

                        }

                    }
                }

                override fun onFailure(
                    call: Call<List<ProfilePost>?>,
                    t: Throwable
                ) {
                    TODO("Not yet implemented")
                }


            })



      //  fun homeProfile(){

            holder.binding.homeProfileImage.setOnClickListener(object:OnClickListener{
                override fun onClick(p0: View?) {
                    var intent=Intent(p0?.context, ProfileActivity::class.java)
                    intent.putExtra("name",list.userName)
                    intent.putExtra("image",list.image)
                    intent.putExtra("uid",list.uid)
                    context.startActivity(intent)
                }


            })







        //fun homeLike() {

            holder.binding.homelike.setOnClickListener(object : View.OnClickListener{
                override fun onClick(p0: View?) {

                    if (check) {
                        holder.binding.homelike.setColorFilter(Color.RED)
                        Log.d("press","if")
                        check=false
                    }
                    else {
                        holder.binding.homelike.setColorFilter(Color.BLACK)
                        check=true
                        Log.d("press","elseif")
                    }
                }


            })




      // fun homeFollow(){

            holder.binding.homefollow.setOnClickListener{

                holder.binding.homefollow.text="following"
                holder.binding.homefollow.setBackgroundColor(Color.BLACK)

                var uid=list.uid
                var userName=list.userName
                var followModal= FollowModal(uid,userName,authuid)
                Builder.instance.createFollowes(followModal).enqueue(object : Callback<ResponseBody>{
                    override fun onResponse(
                        call: Call<ResponseBody?>,
                        response: Response<ResponseBody?>
                    ) {
                      onBindViewHolder(holder,position)
                    }

                    override fun onFailure(
                        call: Call<ResponseBody?>,
                        t: Throwable
                    ) {
                        TODO("Not yet implemented")
                    }


                })

            }








    }
    fun checkFollow(useruid:String, holder: ViewHolder, context: Context, userName: String,authuid: String)
    {

        Builder.instance.getFollow().enqueue(object : Callback<List<FollowModal>>{
            override fun onResponse(
                call: Call<List<FollowModal>?>,
                response: Response<List<FollowModal>?>
            ) {
                response.body()?.forEach {

                    var uid = it.uid
                    var name = it.userName
                    var auid=it.followUser

                    if (uid ==useruid && name==userName &&authuid==auid ) {
                        holder.binding.homefollow.text = "following"
                        holder.binding.homefollow.setBackgroundColor(Color.BLACK)
                    }else{

                        holder.binding.homefollow.text="follow"
                        holder.binding.homefollow.setBackgroundColor(Color.BLUE)

                    }
                }

            }

            override fun onFailure(
                call: Call<List<FollowModal>?>,
                t: Throwable
            ) {
                TODO("Not yet implemented")
            }


        })

    }


    override fun getItemCount(): Int {
        return postlist.size
    }

    class ViewHolder(var binding: HomeRecLayoutBinding): RecyclerView.ViewHolder(binding.root){



    }
}


