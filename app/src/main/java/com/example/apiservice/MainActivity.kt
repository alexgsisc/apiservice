package com.example.apiservice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.apiservice.user.model.Result
import com.example.apiservice.user.view.fragment.DetailsUserFragment
import com.example.apiservice.user.view.fragment.UserFragment
import com.google.gson.Gson

class MainActivity : AppCompatActivity(),
    UserFragment.ViewClickElement {

    private val TAG = MainActivity::class.java.simpleName

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

        val bundle = Bundle()
        bundle.putString("DATA_INFO", Gson().toJson(data))
        val detailUserFragment = DetailsUserFragment.newInstance()
        detailUserFragment.arguments = bundle
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.root_layout, detailUserFragment, DetailsUserFragment.TAG)
            .addToBackStack(DetailsUserFragment.TAG)
            .commit()
    }

}