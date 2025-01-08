package com.example.rahul.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rahul.R
import com.example.rahul.adapter.ChatUserListAdapter
import com.example.rahul.modal.ChatModal


class MsgFragment(var ncontext: Context) : Fragment() {

   lateinit var list: ArrayList<ChatModal>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view= inflater.inflate(R.layout.fragment_msg, container, false)

        list= ArrayList<ChatModal>()
        var recView=view.findViewById<RecyclerView>(R.id.recviewmsg)
        recView.layoutManager= LinearLayoutManager(context)

        list.add(ChatModal("rahul",null,"",""))
        list.add(ChatModal("rahul",null,"",""))
        list.add(ChatModal("rahul",null,"",""))
        list.add(ChatModal("rahul",null,"",""))
        list.add(ChatModal("rahul",null,"",""))
        list.add(ChatModal("rahul",null,"",""))



        var adapter= ChatUserListAdapter(list,ncontext)

        recView.adapter=adapter

        return view
    }


}