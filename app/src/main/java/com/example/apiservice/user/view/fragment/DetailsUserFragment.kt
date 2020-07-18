package com.example.apiservice.user.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.apiservice.R
import com.example.apiservice.user.model.Result
import com.google.gson.Gson
import kotlinx.android.synthetic.main.details_user_fragment.*
import kotlinx.android.synthetic.main.details_user_fragment.tool_bar_person
import kotlinx.android.synthetic.main.list_user_fragment.*

class DetailsUserFragment : Fragment() {
    private var dataTemp: Result? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.details_user_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity!! as AppCompatActivity).setSupportActionBar(tool_bar_person)

        if (!arguments!!.getString("DATA_INFO").isNullOrEmpty()) {
            dataTemp = Gson().fromJson(arguments!!.getString("DATA_INFO"), Result::class.java)
        }

        title_bar.text = dataTemp!!.name
        Glide.with(this.context!!)
            .load(dataTemp!!.image)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .placeholder(android.R.drawable.progress_horizontal)
            .into(img_avatar)

        title_person.text = "Origen: ${dataTemp!!.origin.name}"
        title_origin.text = "Ultima Ubicación: ${dataTemp!!.location.name}"
        chip.text = "Estatus: ${dataTemp!!.status}"
        chip2.text = "Genero: ${dataTemp!!.gender}"



        val adapter = ArrayAdapter(this.context!!, android.R.layout.simple_list_item_1, dataTemp!!.episode)

        list_location.adapter = adapter


    }


    companion object {
        val TAG = DetailsUserFragment::class.java.canonicalName
        fun newInstance() =
            DetailsUserFragment()
    }

}