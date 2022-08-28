package com.example.mygithubapp.adapter


import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mygithubapp.data.model.PullRequests
import com.example.mygithubapp.databinding.ItemPrListLayoutBinding


class DashboardAdapter(
    private val users: ArrayList<PullRequests>
) : RecyclerView.Adapter<DashboardAdapter.DataViewHolder>() {

    private lateinit var binding: ItemPrListLayoutBinding

    class DataViewHolder(
        val binding: ItemPrListLayoutBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(pullRequests: PullRequests) {
            binding.pullRequest = pullRequests
            //    binding.createdDate.text= pullRequests.created_date?.let { DateUtils.getDateFormat(it) }
            if (!TextUtils.isEmpty(pullRequests.user.avatar_url)) {
                binding.imageView.visibility = View.VISIBLE
                Glide.with(binding.root.context).load(pullRequests.user.avatar_url)
                    .into(binding.imageView)
            } else {
                binding.imageView.visibility = View.GONE
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        binding =
            ItemPrListLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DataViewHolder(binding)
    }


    override fun getItemCount(): Int = users.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(users[position])
    }

    fun addData(list: List<PullRequests>) {
        users.addAll(list)
    }

}