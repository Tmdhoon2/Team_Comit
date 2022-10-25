package com.example.gitgit.remote

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.gitgit.R
import com.example.gitgit.databinding.ListUserBinding
import com.example.gitgit.model.UserListResponse

class UserListAdapter(private val arrayList : ArrayList<UserListResponse>) :
    RecyclerView.Adapter<UserListAdapter.UserListViewHolder>() {
    class UserListViewHolder(val binding : ListUserBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(userListResponse: UserListResponse){
            binding.userListResponse = userListResponse
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserListViewHolder {
        val binding = DataBindingUtil.inflate<ListUserBinding>(LayoutInflater.from(parent.context), R.layout.list_user, parent, false)
        return UserListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserListViewHolder, position: Int) {
        holder.bind(arrayList[position])
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }
}