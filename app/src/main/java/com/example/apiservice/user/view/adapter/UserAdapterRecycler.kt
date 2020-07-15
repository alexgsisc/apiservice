package com.example.apiservice.user.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.apiservice.R
import com.example.apiservice.common.entity.TypeData
import com.example.apiservice.common.entity.TypeDataView
import com.example.apiservice.user.model.Result

class UserAdapterRecycler(
    private val listData: List<TypeDataView>,
    private val context: Context,
    views: UserAdapterRecyclerView
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var viewsUserAdapter: UserAdapterRecyclerView = views

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            TypeData.PERSON_RESULT.value -> {
                ViewDataUser(
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.item_personaje, parent, false)
                )
            }
            else -> {
                ViewDataUser(
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.item_personaje, parent, false)
                )
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder.itemViewType) {
            TypeData.PERSON_RESULT.value -> {
                val personage = listData[position] as Result
                val viewTemp = holder as ViewDataUser

                Glide.with(context)
                    .load(personage.image)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(R.mipmap.test_image)
                    .into(viewTemp.imgAvatar)

                viewTemp.tvName.text = personage.name
                viewTemp.tvSpace.text = personage.species
                viewTemp.tvStatus.text = personage.status

                holder.itemView.setOnClickListener {
                    Toast.makeText(context, personage.name, Toast.LENGTH_SHORT).show()
                    viewsUserAdapter.viewsClickUser(personage)
                }

            }
            else -> {

            }
        }
    }


    override fun getItemCount(): Int {
        return listData.size
    }

    override fun getItemViewType(position: Int): Int {
        return listData[0].getDataType
    }


    class ViewDataUser(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvName: TextView = itemView.findViewById(R.id.tv_name)
        var tvSpace: TextView = itemView.findViewById(R.id.tv_space)
        var tvStatus: TextView = itemView.findViewById(R.id.tv_status)
        var imgAvatar: ImageView = itemView.findViewById(R.id.img_avatar)
    }

    interface UserAdapterRecyclerView {
        fun viewsClickUser(result: Result)
    }
}