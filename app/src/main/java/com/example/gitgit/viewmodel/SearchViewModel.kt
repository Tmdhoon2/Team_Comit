package com.example.gitgit.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gitgit.repository.SearchRepository
import com.example.gitgit.model.UserResponse
import kotlinx.coroutines.launch
import retrofit2.Response

class SearchViewModel(private val searchRepository: SearchRepository) : ViewModel() {
    private val _userResponse: MutableLiveData<Response<UserResponse>> = MutableLiveData()
    val userResponse: LiveData<Response<UserResponse>> = _userResponse

    private val _createdAt : MutableLiveData<String> = MutableLiveData()
    val createdAt : LiveData<String> = _createdAt

    fun search(userId: String) {
        viewModelScope.launch {
            kotlin.runCatching {

            }.onSuccess {
                val response = searchRepository.search(userId)
                if(response.isSuccessful){
                    _userResponse.value = response
                    Log.d("TEST", response.code().toString())
                    _createdAt.value = response.body()?.created_at?.split("T")?.get(0)
                }
            }.onFailure {exception ->
                Log.d("TEST", exception.message.toString())

            }
        }
    }

}