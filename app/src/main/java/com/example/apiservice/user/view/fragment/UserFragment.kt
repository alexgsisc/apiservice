package com.example.apiservice.user.view.fragment

import android.app.SearchManager
import android.content.ContentResolver
import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.provider.SearchRecentSuggestions
import android.util.Log
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.apiservice.R
import com.example.apiservice.common.dialog.ProgressDialogFragment
import com.example.apiservice.common.entity.TypeDataView
import com.example.apiservice.common.search.SearchSuggestionAdapter
import com.example.apiservice.common.search.SuggestionSearchPerson
import com.example.apiservice.user.model.Result
import com.example.apiservice.user.presenter.UserPresenter
import com.example.apiservice.user.view.UserView
import com.example.apiservice.user.view.adapter.UserAdapterRecycler
import kotlinx.android.synthetic.main.list_user_fragment.*


class UserFragment : Fragment(), UserView,
    UserAdapterRecycler.UserAdapterRecyclerView {

    private lateinit var userPresenter: UserPresenter
    private var recyclerviewAdapter: UserAdapterRecycler? = null
    private var mCallback: ViewClickElement? = null
    private val progressTemp = ProgressDialogFragment()
    var nextPage = 0
    private var listAddPerson: MutableList<TypeDataView> = mutableListOf()
    var searActive = false

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
        setHasOptionsMenu(true)
        (activity!! as AppCompatActivity).setSupportActionBar(tool_bar_person)
        (activity!! as AppCompatActivity).supportActionBar!!.title = "Pesonajes"

        //Guardar Datos de consultas
        loadView()


    }

    override fun onResume() {
        super.onResume()
        userPresenter.getPersonSerie(nextPage)

    }

    companion object {
        val TAG = UserFragment::class.java.canonicalName
        fun newInstance() =
            UserFragment()
    }

    override fun setPaintData(data: List<TypeDataView>, nextPage: Int) {
        listAddPerson.addAll(data)
        this.nextPage = nextPage
        recyclerviewAdapter =
            UserAdapterRecycler(
                listAddPerson,
                this!!.context!!,
                this
            )
        rcv_data.adapter = recyclerviewAdapter
        recyclerviewAdapter!!.notifyDataSetChanged()
        progress_circular.visibility = View.GONE
    }

    override fun viewsClickUser(result: Result) {
        mCallback!!.viewDetailUser(result)
    }

    override fun onRequestedLastItem() {
        if (!this.searActive) {
            progress_circular.visibility = View.VISIBLE
            userPresenter.getPersonSerie(this.nextPage)
        }
    }

    override fun loadView() {
        progressTemp.show(this.activity!!.supportFragmentManager, TAG)
    }

    override fun formView() {
        progressTemp.dismiss()
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

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        activity!!.menuInflater.inflate(R.menu.menu_search, menu)

        var mSuggestionAdapter = SearchSuggestionAdapter(this.context!!, null, 0)
        val searchManager: SearchManager =
            activity!!.getSystemService(Context.SEARCH_SERVICE) as SearchManager
        var searchView: SearchView = menu.findItem(R.id.search_person).actionView as SearchView
        searchView.setSearchableInfo(searchManager.getSearchableInfo(activity!!.componentName))
        searchView.isQueryRefinementEnabled = true
        searchView.suggestionsAdapter = mSuggestionAdapter
        searchView.maxWidth = Integer.MAX_VALUE
        searchView.setOnCloseListener {
            Log.e(":::CLOSE:::", "::__-__-__TRUE::")
            this.searActive = false
            false
        }

        searchView.setOnQueryTextListener(object :
            SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String): Boolean {
                this@UserFragment.searActive = true
                Log.e(TAG, "1.- $query")
                SearchRecentSuggestions(
                    context,
                    SuggestionSearchPerson.AUTHORITY,
                    SuggestionSearchPerson.MODE
                )
                    .saveRecentQuery(query, null)

                recyclerviewAdapter!!.filter.filter(query)

                return true
            }

            override fun onQueryTextChange(query: String): Boolean {
                Log.e(TAG, "2.- $query")
                this@UserFragment.searActive = true
                val cursor = getRecentSuggestions(query)
                mSuggestionAdapter.swapCursor(cursor)

                recyclerviewAdapter!!.filter.filter(query)
                return false
            }
        })

        //Click in sugerencia
        searchView.setOnSuggestionListener(object : SearchView.OnSuggestionListener {
            override fun onSuggestionSelect(position: Int): Boolean {
                return false
            }

            override fun onSuggestionClick(position: Int): Boolean {
                searchView.setQuery(mSuggestionAdapter.getSuggestionText(position), true);
                return true
            }

        })

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        var id: Int = item.itemId
        if (id == R.id.search_person) {
            return true
        }
        return onOptionsItemSelected(item)
    }

    fun getRecentSuggestions(query: String): Cursor? {
        val uriBuilder: Uri.Builder = Uri.Builder()
            .scheme(ContentResolver.SCHEME_CONTENT)
            .authority(SuggestionSearchPerson.AUTHORITY)
        uriBuilder.appendPath(SearchManager.SUGGEST_URI_PATH_QUERY)
        val selection = " ?"
        val selArgs = arrayOf(query)
        val uri: Uri = uriBuilder.build()
        return activity!!.contentResolver.query(uri, null, selection, selArgs, null)
    }


}