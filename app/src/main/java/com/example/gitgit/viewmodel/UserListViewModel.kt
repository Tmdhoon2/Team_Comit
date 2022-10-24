package com.example.gitgit.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gitgit.model.UserListResponse
import com.example.gitgit.repository.UserListRepository
import kotlinx.coroutines.launch
import retrofit2.Response

class UserListViewModel(private val userListRepository: UserListRepository) : ViewModel() {

    private val _userListResponse : MutableLiveData<Response<List<UserListResponse>>> = MutableLiveData()
    val userListResponse : LiveData<Response<List<UserListResponse>>> = _userListResponse

    fun userList(since : Int){
        viewModelScope.launch {
            val response = userListRepository.userList(since)
            _userListResponse.value = response
        }
    }
}