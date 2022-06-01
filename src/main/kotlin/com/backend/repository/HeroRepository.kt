package com.backend.repository

import com.backend.models.Hero
import com.backend.models.HeroResponse

interface HeroRepository {

    val heroes: Map<Int, List<Hero>>

    val page1: List<Hero>
    val page2: List<Hero>
    val page3: List<Hero>
    val page4: List<Hero>
    val page5: List<Hero>

    suspend fun getAllHeroes(page: Int = 1): HeroResponse
    suspend fun searchHeroes(query: String?): HeroResponse
}