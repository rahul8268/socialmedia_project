package com.example.rahul.activity

import android.os.Bundle
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.example.rahul.R

import com.example.rahul.databinding.ActivityHomeBinding
import com.example.rahul.fragments.HomeFragment
import com.example.rahul.fragments.MsgFragment
import com.example.rahul.fragments.ProfileFragment
import com.example.rahul.fragments.ReelsFragment
import com.example.rahul.fragments.SearchFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth

class HomeActivity : AppCompatActivity() {
    lateinit var binding: ActivityHomeBinding

    lateinit var auth:FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding= ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
    auth= FirebaseAuth.getInstance()
    var toolbar= Toolbar(this)

    fragmentWork(toolbar)

    }

    fun fragmentWork(toolbar: Toolbar) {

        laodFragment(HomeFragment(this@HomeActivity),true)
        binding.bottomNavigationView.setOnItemSelectedListener(object :BottomNavigationView.OnNavigationItemSelectedListener{
            override fun onNavigationItemSelected(item: MenuItem): Boolean {
                var id=item.itemId

                if (id==R.id.home){
                    laodFragment(HomeFragment(this@HomeActivity),false)
                }else if(id==R.id.search){
                    laodFragment(SearchFragment(toolbar,this@HomeActivity),false)
                }else if(id==R.id.reels){
                    laodFragment(ReelsFragment(),false)

                }else if(id==R.id.msg){
                    laodFragment(MsgFragment(this@HomeActivity),false)
                }else{
                    laodFragment(ProfileFragment(this@HomeActivity),false)

                }


                return  true;
            }


        })


    }

//    fun passData(fragment: Fragment,key: String,value: String){
//
//
//        var bundle= Bundle()
//        bundle.putString(key,value)
//        fragment.arguments=bundle
//
//        var ft=supportFragmentManager.beginTransaction()
//
//            ft.replace(R.id.flFragment,fragment)
//
//        ft.commit()
//    }

    fun laodFragment(fragment: Fragment,check: Boolean){

             var ft=supportFragmentManager.beginTransaction()

        if (check){
            ft.add(R.id.flFragment,fragment)
        }else{
            ft.replace(R.id.flFragment,fragment)
        }
        ft.commit()
    }



}


