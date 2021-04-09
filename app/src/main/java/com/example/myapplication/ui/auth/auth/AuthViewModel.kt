package com.example.myapplication.ui.auth.auth

import android.view.View
import androidx.lifecycle.ViewModel
import com.example.myapplication.data.repository.UserRepository

class AuthViewModel  : ViewModel() {
    var email :String? =null
    var password :String? =null
    var authListener : AuthListener?= null

    fun onLoginButtonClick(view: View){
         authListener?.onStarted()
        if(email.isNullOrEmpty() || password.isNullOrEmpty()){
            authListener?.onFailure("Invalid email or Password !!")
            return
        }

        val loginResponse = UserRepository().addUser()
        authListener?.onSuccess(loginResponse)
    }
}