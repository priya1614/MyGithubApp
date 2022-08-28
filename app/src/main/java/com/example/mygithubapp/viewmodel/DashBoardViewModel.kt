package com.example.mygithubapp.viewmodel


import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mygithubapp.data.api.ApiHelper
import com.example.mygithubapp.data.model.PullRequests
import com.example.mygithubapp.utils.DateUtils
import com.example.mygithubapp.utils.Resource
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class DashBoardViewModel(
    private val apiHelper: ApiHelper

) : ViewModel() {
    private val pullRequests = MutableLiveData<Resource<List<PullRequests>>>()

    init {
        fetchPrList()
    }

    private fun fetchPrList() {
        GlobalScope.launch {
            pullRequests.postValue(Resource.loading(null))
            try {
                Log.d(ContentValues.TAG, "before coroutine ")
                val usersFromApi = apiHelper.getListOfPullRequests()
                val allUsersFromApi = mutableListOf<PullRequests>()
                allUsersFromApi.addAll(usersFromApi)
                pullRequests.postValue(Resource.success(allUsersFromApi))
                Log.d(ContentValues.TAG, "before coroutine ")
            } catch (e: Exception) {
                pullRequests.postValue(Resource.error("Something Went Wrong", null))
            }
        }
    }

    fun getNews(): LiveData<Resource<List<PullRequests>>> {
        return pullRequests
    }


}