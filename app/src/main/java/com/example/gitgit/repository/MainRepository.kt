package com.example.gitgit.repository

import com.example.gitgit.api.ApiProvider
import com.example.gitgit.model.UserResponse
import retrofit2.Response

class MainRepository() {
    suspend fun search(userId: String): Response<UserResponse> {
        return ApiProvider.retrofit.search(userId)
    }
}
