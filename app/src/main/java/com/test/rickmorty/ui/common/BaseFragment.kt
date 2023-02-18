package com.test.rickmorty.ui.common

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

abstract class BaseFragment: Fragment() {

    fun findNavController(): NavigationController = activity as NavigationController

    fun initToolbar(title: String, showBackButton: Boolean = false) {
        (requireActivity() as? AppCompatActivity)?.supportActionBar?.title = title
        (requireActivity() as? AppCompatActivity)?.supportActionBar?.setDisplayHomeAsUpEnabled(showBackButton)
    }
}