package com.example.gitgit.viewmodel

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gitgit.R
import com.example.gitgit.getPref
import com.example.gitgit.repository.MainRepository
import com.example.gitgit.model.UserResponse
import com.example.gitgit.putPref
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel(
    private val searchRepository: MainRepository,
    private val pref: SharedPreferences,
) : ViewModel() {
    private val _userResponse: MutableLiveData<Response<UserResponse>> = MutableLiveData()
    val userResponse: LiveData<Response<UserResponse>> = _userResponse

    private val _createdAt: MutableLiveData<String> = MutableLiveData()
    val createdAt: LiveData<String> = _createdAt

    private val _favorite: MutableLiveData<Int> = MutableLiveData()
    val favorite: LiveData<Int> = _favorite

    private lateinit var name: String

    fun search(userId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = searchRepository.search(userId)
            if (response.isSuccessful) {
                _userResponse.postValue(response)
                _createdAt.postValue(response.body()?.created_at?.split("T")?.get(0))

                putPref(pref.edit(), "name", response.body()!!.login)
                putPref(pref.edit(), "url", response.body()!!.avatar_url)
                putPref(pref.edit(), "profile_url", response.body()!!.html_url)

                name = getPref(pref, "name", "").toString()

                if (getPref(pref, name, false) as Boolean) {
                    _favorite.postValue(R.drawable.ic_star_yellow)
                } else {
                    _favorite.postValue(R.drawable.ic_star)
                }
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