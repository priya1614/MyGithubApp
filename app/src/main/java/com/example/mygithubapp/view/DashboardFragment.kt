package com.example.mygithubapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mygithubapp.R
import com.example.mygithubapp.adapter.DashboardAdapter
import com.example.mygithubapp.base.BaseFragment
import com.example.mygithubapp.data.api.ApiHelperImpl
import com.example.mygithubapp.data.api.RetrofitBuilder
import com.example.mygithubapp.data.model.PullRequests
import com.example.mygithubapp.databinding.FragmentDashboardBinding
import com.example.mygithubapp.utils.Status
import com.example.mygithubapp.utils.ViewModelFactory
import com.example.mygithubapp.viewmodel.DashBoardViewModel


class DashboardFragment: BaseFragment() {
    override fun layoutId(): Int = R.layout.fragment_dashboard

    private lateinit var viewModel: DashBoardViewModel
    private lateinit var adapter: DashboardAdapter
    private lateinit var binding : FragmentDashboardBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDashboardBinding.inflate(inflater,container,false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
        setupViewModel()
        setupObserver()
    }
    /**
     * binding view
     */
    private fun setupUI() {
        binding.newsListRv.layoutManager = LinearLayoutManager(context)
        adapter =
            DashboardAdapter(
                arrayListOf()
            )
        binding.newsListRv.addItemDecoration(
            DividerItemDecoration(
                binding.newsListRv.context,
                (binding.newsListRv.layoutManager as LinearLayoutManager).orientation
            )
        )
        binding.newsListRv.adapter = adapter
    }

    /**
     * observing network call
     */
    private fun setupObserver() {
        viewModel.getNews().observe(viewLifecycleOwner, Observer {
            when (it?.status) {
                Status.SUCCESS -> {
                    binding.progressBar.visibility = View.GONE
                    it.data?.let { users -> renderList(users) }
                    binding.newsListRv.visibility = View.VISIBLE
                }
                Status.LOADING -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.newsListRv.visibility = View.GONE
                }
                Status.ERROR -> {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(context, it.message, Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    private fun renderList(users: List<PullRequests>) {
        adapter.addData(users)
        adapter.notifyDataSetChanged()
    }

    /**
     * setting up viewModel
     */
    private fun setupViewModel() {
        viewModel = ViewModelProvider(
            this,
            ViewModelFactory(
                ApiHelperImpl(RetrofitBuilder.apiService)
            )
        ).get(DashBoardViewModel::class.java)
    }

}