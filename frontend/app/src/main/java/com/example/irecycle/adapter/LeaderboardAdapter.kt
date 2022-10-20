package com.example.irecycle.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.irecycle.R
import com.example.irecycle.databinding.LeaderboardItemListBinding
import com.example.irecycle.model.LeaderboardUser

class LeaderboardAdapter : RecyclerView.Adapter<LeaderboardAdapter.ViewHolder>() {

    var userList = mutableListOf<LeaderboardUser>()
    fun updateLeaderboard(list: List<LeaderboardUser>) {
        userList = list.toMutableList()
        notifyDataSetChanged()
    }

    class ViewHolder(val binding: LeaderboardItemListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(user: LeaderboardUser) {
            binding.user = user
            when(user.rank){
                1 -> binding.rank.background = binding.root.resources.getDrawable(R.drawable.first_place)
                2 -> binding.rank.background = binding.root.resources.getDrawable(R.drawable.second_place)
                3 -> binding.rank.background = binding.root.resources.getDrawable(R.drawable.third_place)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: LeaderboardItemListBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.leaderboard_item_list, parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(userList[position])
    }

    override fun getItemCount(): Int {
        return userList.size
    }
}