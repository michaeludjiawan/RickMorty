package com.test.rickmorty.ui.common

import androidx.fragment.app.Fragment

interface NavigationController {
    fun navigateToPage(fragment: Fragment, addToBackStack: Boolean = true, navigationType: NavigationType = NavigationType.REPLACE)
}

enum class NavigationType {
    ADD, REPLACE
}