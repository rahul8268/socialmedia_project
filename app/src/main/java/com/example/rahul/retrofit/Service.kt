package com.example.rahul.retrofit

import com.example.rahul.modal.FollowModal
import com.example.rahul.modal.PostModal
import com.example.rahul.modal.ProfilePost
import com.example.rahul.modal.Users
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface Service  {
    @GET("users")
    fun getUSers(): Call<List<Users>>

    @POST("users")
    fun creatUsers(@Body users: Users): Call<ResponseBody>

    @GET("post")
    fun getPostData(): Call<List<PostModal>>

    @POST("post")
    fun createPost(@Body postModal: PostModal): Call<ResponseBody>

    @GET("follow")
    fun getFollow(): Call<List<FollowModal>>

    @POST("follow")
    fun createFollowes(@Body followModal: FollowModal): Call<ResponseBody>

    @GET("data")
    fun getProfile(): Call<List<ProfilePost>>

    @POST("data")
    fun insertProfile(@Body profilemodal: ProfilePost): Call<ResponseBody>
}