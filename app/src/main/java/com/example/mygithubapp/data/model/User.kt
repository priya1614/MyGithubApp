package com.example.mygithubapp.data.model

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("login")
    val name: String,
    @SerializedName("avatar_url")
    val avatar_url: String?
)
