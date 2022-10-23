package com.example.gitgit.view.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gitgit.R
import com.example.gitgit.base.BaseFragment
import com.example.gitgit.databinding.FragmentHomeBinding
import com.example.gitgit.model.UserListResponse
import com.example.gitgit.model.UserResponse
import com.example.gitgit.remote.UserListAdapter
import com.example.gitgit.repository.UserListRepository
import com.example.gitgit.viewmodel.UserListViewModel
import com.example.gitgit.viewmodel.factory.UserListViewModelFactory
import kotlin.math.sin

class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {

    private val userList : ArrayList<UserListResponse> by lazy {
        ArrayList()
    }

    private val userListRepository : UserListRepository by lazy {
        UserListRepository()
    }

    private val userListViewModelFactory : UserListViewModelFactory by lazy {
        UserListViewModelFactory(userListRepository)
    }

    private val userListViewModel : UserListViewModel by lazy {
        ViewModelProvider(this, userListViewModelFactory).get(UserListViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.homeFragment = this
        initSinceButton()
        initRecyclerView()
        initSinceObserve()
    }

    override fun onPause() {
        super.onPause()
        Log.d("TEST", "onPause")
    }

    fun initSinceButton(){
        val since = binding.etHomeSince.text
        if(since.isNotEmpty()){
            userListViewModel.userList(Integer.parseInt(since.toString()))
            Log.d("TEST", "onClick")
        }
    }

    fun initRecyclerView(){
        binding.rvHomeUserList.adapter = UserListAdapter(userList)
        binding.rvHomeUserList.layoutManager = LinearLayoutManager(this.requireContext())
    }

    private fun initSinceObserve(){
        userListViewModel.userListResponse.observe(viewLifecycleOwner, Observer {
            when(it.code()){
                200 -> userList.addAll(it.body()!!)
            }
        })
    }

}