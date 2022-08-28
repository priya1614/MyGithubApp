package com.example.mygithubapp.data.api

import com.example.mygithubapp.data.model.PullRequests
import retrofit2.http.GET

interface ApiService {

    @GET("pulls?state=closed")
    suspend fun getListOfPullRequests(): List<PullRequests>


}