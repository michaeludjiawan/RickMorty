package com.test.rickmorty.ui.home

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.test.rickmorty.FakeCharacterRepository
import com.test.rickmorty.R
import com.test.rickmorty.data.repository.CharacterRepository
import org.hamcrest.CoreMatchers
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

@RunWith(AndroidJUnit4::class)
class HomeFragmentTest {

    companion object {
        private val fakeRepository = FakeCharacterRepository()

        @BeforeClass
        @JvmStatic
        fun injectModules() = loadModules
        private val loadModules by lazy {
            loadKoinModules(
                module {
                    viewModel { HomeViewModel(get()) }
                    single<CharacterRepository> { fakeRepository }
                }
            )
        }
    }

    @Before
    fun setup() {
        fakeRepository.setReturnError(false)
    }

    @Test
    fun load_showSuccess() {
        launchFragment()

        checkSuccessDisplayed()
    }

    private fun launchFragment() {
        launchFragmentInContainer<HomeFragment>()
    }

    private fun checkSuccessDisplayed() {
        onView(withId(R.id.rv_character_list))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.pb_home_loading))
            .check(ViewAssertions.matches(CoreMatchers.not(ViewMatchers.isDisplayed())))
    }
}