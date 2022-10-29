package com.example.gitgit.viewmodel.factory

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.gitgit.repository.MainRepository
import com.example.gitgit.viewmodel.MainViewModel

class MainViewModelFactory(
    private val searchRepository: MainRepository,
    private val sharedPreferences: SharedPreferences,
    ) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(searchRepository, sharedPreferences) as T
    }
}