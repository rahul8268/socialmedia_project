package com.example.rahul.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rahul.databinding.ChatshowlayoutBinding
import com.example.rahul.modal.ChatModal

class ChatAdapter(var list: ArrayList<ChatModal>): RecyclerView.Adapter<ChatAdapter.ViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
    var binding= ChatshowlayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
       var list=list[position]
        var binding=holder.binding

        binding.authtext.text=list.msg
    }

    override fun getItemCount(): Int {
    return list.size
    }

    class ViewHolder(var binding: ChatshowlayoutBinding) : RecyclerView.ViewHolder(binding.root){


    }
}