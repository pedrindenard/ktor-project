package com.backend.repository

import com.backend.models.Hero
import com.backend.models.HeroResponse
import com.backend.utils.Constants
import com.backend.utils.Mocks

class HeroRepositoryImpl : HeroRepository {

    override val heroes: Map<Int, List<Hero>> by lazy {
        mapOf(
            1 to page1,
            2 to page2,
            3 to page3,
            4 to page4,
            5 to page5
        )
    }

    override val page1 = Mocks.page1
    override val page2 = Mocks.page2
    override val page3 = Mocks.page3
    override val page4 = Mocks.page4
    override val page5 = Mocks.page5

    override suspend fun getAllHeroes(page: Int): HeroResponse {
        return HeroResponse(
            success = true,
            message = "OK",
            prevPage = getCurrentPage(page = page)[Constants.PREV_PAGE],
            nextPage = getCurrentPage(page = page)[Constants.NEXT_PAGE],
            heroes = heroes[page]!!,
        )
    }

    override suspend fun searchHeroes(query: String?): HeroResponse {
        return HeroResponse(
            success = true,
            message = "OK",
            heroes = getHeroes(query = query),
        )
    }

    private fun getCurrentPage(page: Int): Map<String, Int?> {
        val previousPage = if (page in 2..5) page.minus(other = 1) else null
        val nextPage = if (page in 1..4) page.plus(other = 1) else null

        return mapOf(
            Constants.PREV_PAGE to previousPage,
            Constants.NEXT_PAGE to nextPage
        )
    }

    private fun getHeroes(query: String?): List<Hero> {
        val founded = mutableListOf<Hero>()
        return if (!query.isNullOrEmpty()) {
            heroes.forEach { (_, heroes) ->
                heroes.forEach { hero ->
                    if (hero.name.lowercase().contains(query.lowercase())) {
                        founded.add(hero)
                    }
                }
            }
            founded
        } else {
            emptyList()
        }
    }
}