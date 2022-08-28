package com.example.mygithubapp

import android.os.Bundle
import com.example.mygithubapp.view.DashboardFragment
import com.example.mygithubapp.base.BaseActivity

class MainActivity : BaseActivity() {
    override fun getContentViewId(): Int = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpFragment()
    }

    private fun setUpFragment() {
        val fragmentHome = DashboardFragment()
        pushFragment(
            R.id.parent_container,
            fragmentHome,
            addToBackStack = false,
            replace = true
        )
    }
}