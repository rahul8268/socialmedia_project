package com.example.rahul.fragments

import android.content.Context
import android.graphics.Color
import android.os.Bundle

import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import android.widget.EditText

import android.widget.TextView

import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.example.rahul.R
import com.example.rahul.adapter.SearchAdapter
import com.example.rahul.modal.StoryModal
import com.example.rahul.modal.Users
import com.example.rahul.retrofit.Builder

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.Locale


class SearchFragment(var supportActionBar: Toolbar,var contextgive: Context) : Fragment() {
        lateinit var search: SearchView
        lateinit var  listView: RecyclerView
      lateinit var list: ArrayList<StoryModal>
      lateinit var nodata: TextView
      lateinit var adapter: SearchAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View?
    {
        var view= inflater.inflate(R.layout.fragment_search, container, false)

       search=view.findViewById<SearchView>(R.id.searchView)
        listView=view.findViewById<RecyclerView>(R.id.recView)
        nodata=view.findViewById<TextView>(R.id.nodata)

        supportActionBar.visibility= View.GONE

        listView.visibility=View.INVISIBLE

        var searchEditText = search.findViewById<EditText>(androidx.appcompat.R.id.search_src_text);
        searchEditText.setTextColor(Color.WHITE)
        searchEditText.setHintTextColor(Color.WHITE)
        searchEditText.setHint("Search here")
        search.onActionViewExpanded()


        list= ArrayList<StoryModal>()
       fatchUsers()
        adapter= SearchAdapter(list,contextgive)
       listView.layoutManager= LinearLayoutManager(context)
        listView.adapter=adapter


        search.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
     override fun onQueryTextSubmit(query: String?): Boolean {
         return false
     }

     override fun onQueryTextChange(newText: String?): Boolean {
       filterList(newText.toString())

         listView.visibility=View.VISIBLE
     return true
     }

            private fun filterList(query: String) {

                if (query!=null){

                    val filterLIst= ArrayList<StoryModal>()
                    for (i in list){
                        if (i.userName.lowercase(Locale.ROOT).contains(query)){

                            filterLIst.add(i)
                        }
                    }

                    if (filterLIst==null){
                        nodata.visibility=View.VISIBLE
                    }else{
                        adapter.filteredList(filterLIst)
                    }
                }

            }


        })
  

        return  view
    }

    private fun fatchUsers() {


        Builder.instance.getUSers().enqueue(object : Callback<List<Users>>{
            override fun onResponse(
                call: Call<List<Users>?>,
                response: Response<List<Users>?>
            ) {
                response.body()?.forEach {
                    var userName=it.userName.toString()
                    var uid=it.uid.toString()
                    var modal= StoryModal(userName,uid)
                    list.add(modal)

                }

                adapter.notifyDataSetChanged()
            }

            override fun onFailure(
                call: Call<List<Users>?>,
                t: Throwable
            ) {
                TODO("Not yet implemented")
            }


        })



    }


}