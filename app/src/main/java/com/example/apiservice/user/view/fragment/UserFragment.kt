package com.example.apiservice.user.view.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.apiservice.R
import com.example.apiservice.common.entity.TypeDataView
import com.example.apiservice.user.model.Result
import com.example.apiservice.user.presenter.UserPresenter
import com.example.apiservice.user.view.adapter.UserAdapterRecycler
import com.example.apiservice.user.view.UserView
import kotlinx.android.synthetic.main.list_user_fragment.*

class UserFragment : Fragment(), UserView,
    UserAdapterRecycler.UserAdapterRecyclerView {

    lateinit var userPresenter: UserPresenter
    private var recyclerviewAdapter: UserAdapterRecycler? = null
    var mCallback: ViewClickElement? = null
    val TAG = UserFragment::class.java.simpleName

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.list_user_fragment, container, false)
    }

    //Interaccion con usuario
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        userPresenter = UserPresenter(this, this.context!!)
        rcv_data.layoutManager = LinearLayoutManager(this!!.context)

    }

    override fun onResume() {
        super.onResume()
        userPresenter.getPersonSerie()

    }

    companion object {
        val TAG = UserFragment::class.java.canonicalName
        fun newInstance() =
            UserFragment()
    }

    override fun setPaintData(data: List<TypeDataView>) {
        recyclerviewAdapter =
            UserAdapterRecycler(
                data,
                this!!.context!!,
                this
            )
        rcv_data.adapter = recyclerviewAdapter
        recyclerviewAdapter!!.notifyDataSetChanged()
    }

    override fun viewsClickUser(result: Result) {
        mCallback!!.viewDetailUser(result)
    }

    override fun loadView() {
    }

    override fun formView() {
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            mCallback = activity as ViewClickElement
        } catch (e: ClassCastException) {
            Log.e(TAG, e.message, e.cause)
        }
    }


    interface ViewClickElement {
        fun viewDetailUser(data: Result)
    }


}