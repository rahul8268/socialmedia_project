package com.example.rahul.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Builder {



        var baseurl = "http://192.168.3.204:9090/"
        //   192.168.197.


        val instance: Service by lazy {
            Retrofit.Builder()
                .baseUrl(baseurl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(Service::class.java)
        }



}