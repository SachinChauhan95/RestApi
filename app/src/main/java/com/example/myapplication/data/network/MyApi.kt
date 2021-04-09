package com.example.myapplication.data.network

import com.example.myapplication.data.model.UserInfo
import retrofit2.Call
import okhttp3.ResponseBody
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface MyApi {

    // test API https://reqres.in/

    @FormUrlEncoded
    @POST("login")
    fun userLogin(
        @Field("email")email:String,
        @Field("password")password:String
    ): Call<ResponseBody>

    @Headers("Content-Type: application/json")
    @POST("users")
    fun addUser(@Body userData: UserInfo): Call<UserInfo>



    @GET("users?page=2")
    fun getList( ): Call<ResponseBody>

    companion object{
        operator fun invoke():MyApi{
            return Retrofit.Builder()
                .baseUrl("https://reqres.in/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MyApi::class.java)
        }
    }

}