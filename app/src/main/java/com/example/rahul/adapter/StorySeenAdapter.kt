package com.example.rahul.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.rahul.databinding.StorySeenLayoutBinding
import com.example.rahul.modal.StoryModal


class StorySeenAdapter(var storyList: ArrayList<StoryModal>,var context: Context) : RecyclerView.Adapter<StorySeenAdapter.ViewHolder>() {
    override fun onCreateViewHolder (parent: ViewGroup, viewType: Int): ViewHolder {


        var view= LayoutInflater.from(parent.context).inflate(com.example.rahul.R.layout.story_seen_layout,parent,false)
        var viewHolder= ViewHolder(view);
        return viewHolder;
    }

    override fun onBindViewHolder(holder: ViewHolder,position: Int) {
     var items=storyList[position]
        Glide.with(context).load(items.profilePic).placeholder(com.example.rahul.R.drawable.profile).into(holder.profileimg)
        holder.userName.text=items.userName
    }

    override fun getItemCount(): Int {
       return storyList.size
    }

    class ViewHolder(view:View) : RecyclerView.ViewHolder(view) {
          var profileimg=view.findViewById<ImageView>(com.example.rahul.R.id.profileStory)
        var userName=view.findViewById<TextView>(com.example.rahul.R.id.userStory)

    }

}

