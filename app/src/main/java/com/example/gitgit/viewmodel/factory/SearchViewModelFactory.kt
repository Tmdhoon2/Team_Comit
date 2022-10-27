package com.example.gitgit.viewmodel.factory

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.gitgit.repository.SearchRepository
import com.example.gitgit.viewmodel.SearchViewModel

class SearchViewModelFactory(
    private val searchRepository: SearchRepository,
    private val sharedPreferences: SharedPreferences,
    ) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SearchViewModel(searchRepository, sharedPreferences) as T
    }
}