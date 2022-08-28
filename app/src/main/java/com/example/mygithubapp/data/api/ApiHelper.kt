package com.example.mygithubapp.data.api

import com.example.mygithubapp.data.model.PullRequests

interface ApiHelper {

    suspend fun getListOfPullRequests(): List<PullRequests>


}