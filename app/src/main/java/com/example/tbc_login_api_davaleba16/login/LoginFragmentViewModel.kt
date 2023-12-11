package com.example.tbc_login_api_davaleba16.login

import android.util.Log.d
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tbc_login_api_davaleba16.model.User
import com.example.tbc_login_api_davaleba16.network.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

class LoginFragmentViewModel : ViewModel() {
    var myResponse: MutableLiveData<Response<User>> = MutableLiveData()

    fun loginUser(user: User){
        viewModelScope.launch {
            try {
                val response = Repository().loginUser(user)
                myResponse.value = response
            }catch (e: Exception){
                d("Response", "${e.message}")
            }
        }
    }
}