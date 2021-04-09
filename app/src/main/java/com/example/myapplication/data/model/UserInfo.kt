package com.example.myapplication.data.model

import com.google.gson.annotations.SerializedName

data class UserInfo(
    @SerializedName("name") val name: String?,
    @SerializedName("job") val job: String?,
    @SerializedName("id") val id: String?,
    @SerializedName("createdAt") val createdAt: String?
)

