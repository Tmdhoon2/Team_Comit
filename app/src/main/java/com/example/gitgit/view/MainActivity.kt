package com.example.gitgit.view

import android.animation.ObjectAnimator
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.animation.AnticipateInterpolator
import android.view.inputmethod.InputMethodManager
import androidx.core.animation.doOnEnd
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.gitgit.R
import com.example.gitgit.base.BaseActivity
import com.example.gitgit.databinding.ActivityMainBinding
import com.example.gitgit.getPref
import com.example.gitgit.repository.MainRepository
import com.example.gitgit.viewmodel.MainViewModel
import com.example.gitgit.viewmodel.factory.MainViewModelFactory

class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    private val mainRepository : MainRepository by lazy {
        MainRepository()
    }

    private val mainViewModelFactory : MainViewModelFactory by lazy {
        MainViewModelFactory(mainRepository, pref)
    }

    private val mainViewModel : MainViewModel by lazy {
        ViewModelProvider(this, mainViewModelFactory).get(MainViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        initSplashScreen()
        binding.mainActivity = this
        binding.mainViewModel = mainViewModel
        observeSearch()
    }

    fun hideKey(){
        val imm : InputMethodManager = this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(binding.etMainUserId.windowToken, 0)
    }

    private fun initSplashScreen() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.S){
            splashScreen.setOnExitAnimationListener { splashScreenView ->
                ObjectAnimator.ofFloat(splashScreenView, View.ALPHA, 1f, 0f).run {
                    interpolator = AnticipateInterpolator()
                    duration = 1500L
                    doOnEnd { splashScreenView.remove() }
                    start()
                }
            }
        }
    }

    fun initSearchButton(){
        val userId = binding.etMainUserId.text.toString()
        hideKey()
        if(userId.isNotEmpty()){
            mainViewModel.search(userId)
        }
    }

    fun observeSearch(){
        binding.imgMainBlock.setImageResource(R.drawable.img_block)
        mainViewModel.userResponse.observe(this, Observer {
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