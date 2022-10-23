package com.example.gitgit.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.gitgit.repository.SearchRepository
import com.example.gitgit.repository.UserListRepository
import com.example.gitgit.viewmodel.SearchViewModel
import com.example.gitgit.viewmodel.UserListViewModel

class UserListViewModelFactory(private val userListRepository: UserListRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return UserListViewModel(userListRepository) as T
    }
}