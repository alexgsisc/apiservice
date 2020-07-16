package com.example.apiservice

import android.app.SearchManager
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import androidx.appcompat.widget.SearchView
import com.example.apiservice.user.model.Result
import com.example.apiservice.user.view.fragment.UserFragment
import com.google.gson.Gson

class MainActivity : AppCompatActivity(),
    UserFragment.ViewClickElement {

    val TAG = MainActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager!!
            .beginTransaction()
            .replace(R.id.root_layout, UserFragment.newInstance(), UserFragment.TAG)
            .commit()
    }

    override fun viewDetailUser(data: Result) {
        Log.e(TAG, "ID: ${data.id} ---- NAME: ${data.name} ")
        Log.e(TAG, "Data User: ${Gson().toJson(data)}")
    }

    /*override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        //return super.onCreateOptionsMenu(menu)

        menuInflater.inflate(R.menu.menu_search, menu)

        return true
    }*/
}