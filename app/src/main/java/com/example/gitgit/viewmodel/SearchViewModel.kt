package com.example.gitgit.viewmodel

import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gitgit.R
import com.example.gitgit.getPref
import com.example.gitgit.repository.SearchRepository
import com.example.gitgit.model.UserResponse
import com.example.gitgit.putPref
import kotlinx.coroutines.launch
import retrofit2.Response

class SearchViewModel(private val searchRepository: SearchRepository, val pref: SharedPreferences) : ViewModel() {
    private val _userResponse: MutableLiveData<Response<UserResponse>> = MutableLiveData()
    val userResponse: LiveData<Response<UserResponse>> = _userResponse

    private val _createdAt : MutableLiveData<String> = MutableLiveData()
    val createdAt : LiveData<String> = _createdAt

    private val _favorite : MutableLiveData<Int> = MutableLiveData()
    val favorite : LiveData<Int> = _favorite

    private lateinit var login : String

    fun search(userId: String) {
        viewModelScope.launch {
            kotlin.runCatching {

            }.onSuccess {
                val response = searchRepository.search(userId)
                if(response.isSuccessful){
                    _userResponse.value = response
                    _createdAt.value = response.body()?.created_at?.split("T")?.get(0)
                    login = response.body()!!.login
                    Log.d("TEST", getPref(pref, login, false).toString())
                    if(getPref(pref, login, false) as Boolean){
                        _favorite.value = R.drawable.ic_star_yellow
                    }else{
                        _favorite.value = R.drawable.ic_star
                    }
                }
            }.onFailure {

            }
        }
    }

    fun favorite(){
        Log.d("TEST", getPref(pref, login, false).toString())
        if(getPref(pref, login, false) as Boolean){
            _favorite.value = R.drawable.ic_star
            putPref(pref.edit(), login, false)
        }else{
            _favorite.value = R.drawable.ic_star_yellow
            putPref(pref.edit(), login, true)
        }
    }

}