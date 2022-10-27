package com.example.gitgit.remote

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.contentValuesOf
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.gitgit.R
import com.example.gitgit.databinding.ListFavoriteBinding
import com.example.gitgit.model.FavoriteData

class FavoriteAdapter(private val arrayList:ArrayList<FavoriteData>) : RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder>() {
    class FavoriteViewHolder(val binding : ListFavoriteBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(favoriteData: FavoriteData){
            binding.favoriteData = favoriteData
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val binding = DataBindingUtil.inflate<ListFavoriteBinding>(LayoutInflater.from(parent.context), R.layout.list_favorite, parent, false)
        return FavoriteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        holder.bind(arrayList[position])
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }
}