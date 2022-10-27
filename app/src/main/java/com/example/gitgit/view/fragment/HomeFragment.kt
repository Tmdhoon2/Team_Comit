package com.example.gitgit.view.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gitgit.R
import com.example.gitgit.base.BaseFragment
import com.example.gitgit.databinding.FragmentHomeBinding
import com.example.gitgit.model.UserListResponse
import com.example.gitgit.remote.UserListAdapter
import com.example.gitgit.repository.UserListRepository
import com.example.gitgit.viewmodel.UserListViewModel
import com.example.gitgit.viewmodel.factory.UserListViewModelFactory

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

    fun initSinceButton(){
        val since = binding.etHomeSince.text
        hideKey()
        if(since.isNotEmpty()){
            userListViewModel.userList(Integer.parseInt(since.toString()))
        }
    }

    fun initRecyclerView(){
        binding.rvHomeUserList.adapter = UserListAdapter(userList)
        binding.rvHomeUserList.layoutManager = LinearLayoutManager(this.requireContext())
    }

    fun hideKey(){
        val imm : InputMethodManager = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view?.windowToken, 0)
    }

    private fun initSinceObserve(){
        userListViewModel.userListResponse.observe(viewLifecycleOwner, Observer {
            when(it.code()){
                200 -> {
                    initRecyclerView()
                    userList.clear()
                    userList.addAll(it.body()!!)
                }
            }
        })
    }

}