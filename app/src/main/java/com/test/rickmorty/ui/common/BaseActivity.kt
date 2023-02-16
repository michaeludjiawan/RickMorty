package com.test.rickmorty.ui.common

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

abstract class BaseActivity: AppCompatActivity(), NavigationController {

    override fun navigateToPage(fragment: Fragment, addToBackStack: Boolean, navigationType: NavigationType) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()

        val currentFragment = getCurrentFragment()

        if (navigationType == NavigationType.ADD && currentFragment != null) {
            fragmentTransaction.hide(currentFragment)
            fragmentTransaction.add(getParentLayoutId(), fragment)
        } else {
            fragmentTransaction.replace(getParentLayoutId(), fragment)
        }

        if (addToBackStack) fragmentTransaction.addToBackStack(null)

        fragmentTransaction.commit()
    }

    fun getCurrentFragment() = supportFragmentManager.findFragmentById(getParentLayoutId())

    open fun getParentLayoutId(): Int = -1
}