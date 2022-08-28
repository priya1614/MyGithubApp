package com.example.mygithubapp.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mygithubapp.data.api.ApiHelper
import com.example.mygithubapp.viewmodel.DashBoardViewModel



class ViewModelFactory(private val apiHelper: ApiHelper) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DashBoardViewModel::class.java)) {
            return DashBoardViewModel(
                apiHelper
            ) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }


}