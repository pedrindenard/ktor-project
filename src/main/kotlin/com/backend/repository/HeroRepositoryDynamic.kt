package com.backend.repository

import com.backend.models.Hero
import com.backend.models.HeroResponse

interface HeroRepositoryDynamic {

    val heroes: List<Hero>

    suspend fun getAllHeroes(page: Int = 1, limit: Int = 4): HeroResponse
    suspend fun searchHeroes(query: String?): HeroResponse
}