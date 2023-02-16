package com.test.rickmorty.ui.common

import androidx.fragment.app.Fragment

abstract class BaseFragment: Fragment() {

    fun findNavController(): NavigationController = activity as NavigationController

}