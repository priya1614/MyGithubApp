package com.example.mygithubapp.data.model

import com.example.mygithubapp.utils.DateUtils
import com.google.gson.annotations.SerializedName

data class PullRequests(

    val title: String?,
    @SerializedName("created_at")
    val created_date: String?,
    @SerializedName("closed_at")
    val closed_date: String?,

    @SerializedName("user")
    val user: User,

    ) {
    fun date(date: String): String {
        return DateUtils.getDateFormat(date)
    }
}




