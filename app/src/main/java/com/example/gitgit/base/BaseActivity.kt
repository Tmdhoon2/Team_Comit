package com.example.gitgit.base

import android.content.SharedPreferences
import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.example.gitgit.initPref

abstract class BaseActivity<V : ViewDataBinding>(
    @LayoutRes private val layoutId : Int
) : AppCompatActivity() {

    protected val binding : V by lazy {
        DataBindingUtil.setContentView(this, layoutId)
    }

    protected val pref : SharedPreferences by lazy {
        initPref(applicationContext, MODE_PRIVATE)
    }

    protected val editor : SharedPreferences.Editor by lazy {
        pref.edit()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.lifecycleOwner = this
    }

}