package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.myapplication.data.model.UserInfo
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.ui.auth.auth.AuthListener
import com.example.myapplication.ui.auth.auth.AuthViewModel

class MainActivity : AppCompatActivity() , AuthListener{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val loginViewModel = ViewModelProviders.of(this).get(AuthViewModel::class.java)

        binding.authViewModel=loginViewModel
        loginViewModel.authListener = this
    }

    override fun onStarted() {
        Toast.makeText(this,"Login Started", Toast.LENGTH_LONG).show()
    }

    override fun onSuccess(loginResponse: LiveData<String>) {
        loginResponse.observe(this, Observer {
            Toast.makeText(this,""+it, Toast.LENGTH_LONG).show()
        })
    }

    override fun onFailure(message: String) {
        Toast.makeText(this,""+message, Toast.LENGTH_LONG).show()
    }


}