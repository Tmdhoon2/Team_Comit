package com.example.gitgit.view.fragment

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gitgit.R
import com.example.gitgit.base.BaseFragment
import com.example.gitgit.databinding.FragmentFavoriteBinding
import com.example.gitgit.model.FavoriteData
import com.example.gitgit.remote.FavoriteAdapter

class FavoriteFragment : BaseFragment<FragmentFavoriteBinding>(R.layout.fragment_favorite) {

    private val arrayList : ArrayList<FavoriteData> by lazy {
        ArrayList()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
    }

    private fun initRecyclerView() {
        binding.rvFavoriteList.adapter = FavoriteAdapter(arrayList)
        binding.rvFavoriteList.layoutManager = LinearLayoutManager(this.requireContext())
    }
}