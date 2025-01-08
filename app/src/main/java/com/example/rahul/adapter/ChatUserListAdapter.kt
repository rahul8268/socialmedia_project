package com.example.rahul.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.rahul.R
import com.example.rahul.databinding.UsermsglistBinding
import com.example.rahul.modal.ChatModal

class ChatUserListAdapter(var list: ArrayList<ChatModal>,var context: Context) : RecyclerView.Adapter<ChatUserListAdapter.ViewHoler>() {
   // lateinit var binding: UsermsglistBinding
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHoler {
      var binding= UsermsglistBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHoler(binding)
    }

    override fun onBindViewHolder(
        holder: ViewHoler,
        position: Int
    ) {
        var binding=holder.binding
        var list=list[position]
        binding.userName.text=list.name
        binding.lastmsg.text=list.lastMsg
        Glide.with(context).load(list.image).placeholder(R.drawable.profile).into(binding.profileimg)


    }

    override fun getItemCount(): Int {
        return list.size
    }

    class  ViewHoler(var binding: UsermsglistBinding) : RecyclerView.ViewHolder(binding.root){

    }
}