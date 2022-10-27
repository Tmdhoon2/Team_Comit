package com.example.gitgit.view.fragment

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gitgit.R
import com.example.gitgit.base.BaseFragment
import com.example.gitgit.databinding.FragmentSearchBinding
import com.example.gitgit.getPref
import com.example.gitgit.model.FavoriteData
import com.example.gitgit.remote.FavoriteAdapter
import com.example.gitgit.repository.SearchRepository
import com.example.gitgit.viewmodel.SearchViewModel
import com.example.gitgit.viewmodel.factory.SearchViewModelFactory

class SearchFragment : BaseFragment<FragmentSearchBinding>(R.layout.fragment_search) {

    private val searchRepository : SearchRepository by lazy {
        SearchRepository()
    }

    private val searchViewModelFactory : SearchViewModelFactory by lazy {
        SearchViewModelFactory(searchRepository, pref)
    }

    private val searchViewModel : SearchViewModel by lazy {
        ViewModelProvider(this, searchViewModelFactory).get(SearchViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.searchFragment = this
        binding.mainViewModel = searchViewModel
        observeSearch()
    }

    fun hideKey(){
        val imm : InputMethodManager = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view?.windowToken, 0)
    }

    fun initSearchButton(){
        val userId = binding.etMainUserId.text.toString()
        hideKey()
        if(userId.isNotEmpty()){
            searchViewModel.search(userId)
        }
    }

    fun observeSearch(){
        binding.imgMainBlock.setImageResource(R.drawable.img_block)
        searchViewModel.userResponse.observe(viewLifecycleOwner, Observer {
            when(it.code()){
                200 -> binding.imgMainBlock.setImageResource(0)
            }
        })
    }

    fun link(){
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(getPref(pref, "profile_url", "").toString()))
        startActivity(intent)
    }
}