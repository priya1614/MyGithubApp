package com.example.mygithubapp.data.api

class ApiHelperImpl(val apiService: ApiService) : ApiHelper {
    override suspend fun getListOfPullRequests() = apiService.getListOfPullRequests()

}