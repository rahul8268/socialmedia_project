package com.example.rahul.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.rahul.modal.PostModal


class ProfilePostAdapter(var list: ArrayList<PostModal>, var context: Context) : RecyclerView.Adapter<ProfilePostAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProfilePostAdapter.ViewHolder {
       return ViewHolder(LayoutInflater.from(parent.context).inflate(com.example.rahul.R.layout.profilepostseen,parent,false))
    }

    override fun onBindViewHolder(holder: ProfilePostAdapter.ViewHolder, position: Int) {
        var list=list[position]
        Glide.with(context).load(list.image).placeholder(com.example.rahul.R.drawable.manimage).into(holder.image)
    }

    override fun getItemCount(): Int {
        return  list.size
    }

    class ViewHolder(view:View): RecyclerView.ViewHolder(view){
        var image=view.findViewById<ImageView>(com.example.rahul.R.id.imageView4)
    }
}