package com.example.rahul.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.rahul.activity.ProfileActivity
import com.example.rahul.modal.StoryModal

class SearchAdapter(var list: ArrayList<StoryModal>,var context: Context) : RecyclerView.Adapter<SearchAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
var view= LayoutInflater.from(parent.context).inflate(com.example.rahul.R.layout.search_layout,parent,false)
    return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var list=list[position]
       holder.userNameSearch.text=list.userName.toString()
        Glide.with(context).load(list.profilePic).placeholder(com.example.rahul.R.drawable.profile).into(holder.userProfile)

        holder.itemView.setOnClickListener(object :View.OnClickListener{
            override fun onClick(p0: View?) {

                var intent=Intent(p0?.context, ProfileActivity::class.java)
                intent.putExtra("name",list.userName)
                intent.putExtra("image",list.profilePic)
                intent.putExtra("uid",list.uid)
                context.startActivity(intent)

            }


        })
    }

    fun filteredList(list: ArrayList<StoryModal>){
        this.list=list
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int{
        return list.size

    }


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        var userProfile=view.findViewById<ImageView>(com.example.rahul.R.id.searchUserProfile)
        var userNameSearch=view.findViewById<TextView>(com.example.rahul.R.id.userSearch)

}
}