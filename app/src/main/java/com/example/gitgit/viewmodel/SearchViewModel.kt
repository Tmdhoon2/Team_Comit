package com.example.gitgit.viewmodel

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gitgit.R
import com.example.gitgit.getPref
import com.example.gitgit.repository.SearchRepository
import com.example.gitgit.model.UserResponse
import com.example.gitgit.putPref
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class SearchViewModel(
    private val searchRepository: SearchRepository,
    private val pref: SharedPreferences,
) : ViewModel() {
    private val _userResponse: MutableLiveData<Response<UserResponse>> = MutableLiveData()
    val userResponse: LiveData<Response<UserResponse>> = _userResponse

    private val _createdAt: MutableLiveData<String> = MutableLiveData()
    val createdAt: LiveData<String> = _createdAt

    private val _favorite: MutableLiveData<Int> = MutableLiveData()
    val favorite: LiveData<Int> = _favorite

    private lateinit var name : String

    fun search(userId: String) {
        viewModelScope.launch {
            kotlin.runCatching {

            }.onSuccess {
                val response = searchRepository.search(userId)
                if (response.isSuccessful) {
                    _userResponse.value = response
                    _createdAt.value = response.body()?.created_at?.split("T")?.get(0)

                    putPref(pref.edit(), "name", response.body()!!.login)
                    putPref(pref.edit(), "url", response.body()!!.avatar_url)

                    name = getPref(pref, "name", "").toString()
                    if (getPref(pref, name, false) as Boolean) {
                        _favorite.value = R.drawable.ic_star_yellow
                    } else {
                        _favorite.value = R.drawable.ic_star
                    }
                }
            }.onFailure {

            }
        }
    }

    fun favorite() {
        if (getPref(pref, name, false) as Boolean) {
            _favorite.value = R.drawable.ic_star
            putPref(pref.edit(), name, false)
        } else {
            _favorite.value = R.drawable.ic_star_yellow
            putPref(pref.edit(), name, true)
        }

    }
}