package com.backend.di

import com.backend.repository.HeroRepository
import com.backend.repository.HeroRepositoryDynamic
import com.backend.repository.HeroRepositoryDynamicImpl
import com.backend.repository.HeroRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    single<HeroRepository> {
        HeroRepositoryImpl()
    }

    single<HeroRepositoryDynamic> {
        HeroRepositoryDynamicImpl()
    }
}