package com.test.rickmorty.di

import com.test.rickmorty.data.repository.CharacterRepository
import com.test.rickmorty.data.repository.CharacterRepositoryImpl
import com.test.rickmorty.data.repository.EpisodeRepository
import com.test.rickmorty.data.repository.EpisodeRepositoryImpl
import com.test.rickmorty.ui.detail.CharacterDetailViewModel
import com.test.rickmorty.ui.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val characterModule = module {
    single<CharacterRepository> { CharacterRepositoryImpl(get()) }
    single<EpisodeRepository> { EpisodeRepositoryImpl(get()) }

    viewModel { HomeViewModel(get()) }
    viewModel { CharacterDetailViewModel(get()) }
}