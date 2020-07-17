package com.example.apiservice.common.search

import android.app.SearchManager
import android.content.Context
import android.database.Cursor
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cursoradapter.widget.CursorAdapter
import com.example.apiservice.R

class SearchSuggestionAdapter(
    context: Context?,
    c: Cursor?,
    flags: Int
) : CursorAdapter(context, c, flags) {
    override fun newView(
        context: Context,
        cursor: Cursor,
        parent: ViewGroup
    ): View {
        val view = LayoutInflater.from(context).inflate(
            R.layout.search_suggestion_item, parent, false
        )
        val viewHolder =
            ViewHolder(view)
        view.tag = viewHolder
        return view
    }

    override fun bindView(
        view: View,
        context: Context,
        cursor: Cursor
    ) {
        val viewHolder =
            view.tag as ViewHolder
        viewHolder.mTitle.text =
            cursor.getString(cursor.getColumnIndex(SearchManager.SUGGEST_COLUMN_TEXT_1))
    }

    fun getSuggestionText(position: Int): String? {
        if (position >= 0 && position < cursor.count) {
            val cursor = cursor
            cursor.moveToPosition(position)
            return cursor.getString(cursor.getColumnIndex(SearchManager.SUGGEST_COLUMN_TEXT_1))
        }
        return null
    }

    class ViewHolder(view: View) {
        var mIcon: ImageView
        var mTitle: TextView

        init {
            mIcon =
                view.findViewById<View>(R.id.iv_suggestion_item_icon) as ImageView
            mTitle = view.findViewById<View>(R.id.tv_suggestion_item_title) as TextView
        }
    }

    companion object {
        private val LOG_TAG = SearchSuggestionAdapter::class.java.simpleName
    }
}