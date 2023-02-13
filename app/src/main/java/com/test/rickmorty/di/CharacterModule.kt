package com.test.rickmorty.di

import com.test.rickmorty.data.repository.CharacterRepository
import com.test.rickmorty.data.repository.CharacterRepositoryImpl
import org.koin.dsl.module

val characterModule = module {
    single<CharacterRepository> { CharacterRepositoryImpl(get()) }
}