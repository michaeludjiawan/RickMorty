package com.test.rickmorty

import android.os.Bundle
import com.test.rickmorty.databinding.ActivityMainBinding
import com.test.rickmorty.ui.common.BaseActivity
import com.test.rickmorty.ui.home.HomeFragment

class MainActivity : BaseActivity() {

    override fun getParentLayoutId(): Int = R.id.fl_main_container

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        navigateToPage(HomeFragment(), false)
    }
}