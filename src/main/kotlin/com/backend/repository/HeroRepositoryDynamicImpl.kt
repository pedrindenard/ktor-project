package com.backend.repository

import com.backend.models.Hero
import com.backend.models.HeroResponse
import com.backend.utils.Constants
import com.backend.utils.Mocks

class HeroRepositoryDynamicImpl : HeroRepositoryDynamic {

    override val heroes = Mocks.heroes

    override suspend fun getAllHeroes(page: Int, limit: Int): HeroResponse {
        return HeroResponse(
            success = true,
            message = "OK",
            prevPage = getCurrentPage(
                heroes = heroes,
                page = page,
                limit = limit
            )[Constants.PREV_PAGE],
            nextPage = getCurrentPage(
                heroes = heroes,
                page = page,
                limit = limit
            )[Constants.NEXT_PAGE],
            heroes = providerHeroes(
                heroes = heroes,
                page = page,
                limit = limit
            ),
        )
    }

    override suspend fun searchHeroes(query: String?): HeroResponse {
        return HeroResponse(
            success = true,
            message = "OK",
            heroes = getHeroes(query = query)
        )
    }

    private fun getCurrentPage(
        heroes: List<Hero>,
        page: Int,
        limit: Int
    ): Map<String, Int?> {
        val allHeroes = heroes.windowed(
            size = limit,
            step = limit,
            partialWindows = true
        )

        require(page <= allHeroes.size)

        val prevPage = if (page == 1) null else page - 1
        val nextPage = if (page == allHeroes.size) null else page + 1

        return mapOf(
            Constants.PREV_PAGE to prevPage,
            Constants.NEXT_PAGE to nextPage
        )
    }

    private fun providerHeroes(
        heroes: List<Hero>,
        page: Int,
        limit: Int
    ): List<Hero> {
        val allHeroes = heroes.windowed(
            size = limit,
            step = limit,
            partialWindows = true
        )

        require(page > 0 && page <= allHeroes.size)

        return allHeroes[page - 1]
    }

    private fun getHeroes(query: String?): List<Hero> {
        val founded = mutableListOf<Hero>()
        return if (!query.isNullOrEmpty()) {
            heroes.forEach { hero ->
                if (hero.name.lowercase().contains(query.lowercase())) {
                    founded.add(hero)
                }
            }
            founded
        } else {
            emptyList()
        }
    }
}