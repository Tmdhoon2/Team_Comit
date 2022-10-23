package com.example.gitgit.repository

import android.util.Log
import com.example.gitgit.api.ApiProvider
import com.example.gitgit.model.UserListResponse
import com.example.gitgit.util.ACCESS_TOKEN
import retrofit2.Response

class UserListRepository {

    suspend fun userList(since : Int) : Response<List<UserListResponse>> {
        return ApiProvider.retrofit.userList(ACCESS_TOKEN, since)
    }
}