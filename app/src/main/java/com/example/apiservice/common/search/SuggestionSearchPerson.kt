package com.example.apiservice.common.search

import android.content.SearchRecentSuggestionsProvider


class SuggestionSearchPerson : SearchRecentSuggestionsProvider() {
    init {
        setupSuggestions(AUTHORITY, MODE)
    }

    companion object {
        const val AUTHORITY = "com.example.apiservice.common.search.SuggestionSearchPerson"
        const val MODE: Int = DATABASE_MODE_QUERIES
    }
}