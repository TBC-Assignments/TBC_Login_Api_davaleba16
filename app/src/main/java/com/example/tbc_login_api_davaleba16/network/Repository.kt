package com.example.tbc_login_api_davaleba16.network

import com.example.tbc_login_api_davaleba16.model.User
import retrofit2.Response

class Repository {

    suspend fun registerUser(user: User): Response<User>{
        return RetrofitInstance.api.registerUser(user)
    }

    suspend fun loginUser(user: User): Response<User>{
        return RetrofitInstance.api.loginUser(user)
    }
}