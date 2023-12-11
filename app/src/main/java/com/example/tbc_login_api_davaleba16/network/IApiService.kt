package com.example.tbc_login_api_davaleba16.network

import com.example.tbc_login_api_davaleba16.model.User
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface IApiService {

    @POST("/api/register")
    suspend fun registerUser(@Body user: User): Response<User>

    @POST("/api/login")
    suspend fun loginUser(@Body user: User): Response<User>
}