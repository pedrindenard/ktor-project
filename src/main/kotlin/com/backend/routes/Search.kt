package com.backend.routes

import com.backend.repository.HeroRepository
import com.backend.repository.HeroRepositoryDynamic
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Route.getSearchedHero() {

    val repository: HeroRepository by inject()
    val repositoryDynamic: HeroRepositoryDynamic by inject()

    get(path = "/api/v1/search") {
        val name = call.request.queryParameters["name"]
        val apiResponse = repository.searchHeroes(query = name)
        call.respond(
            message = apiResponse,
            status = HttpStatusCode.OK
        )
    }

    get(path = "/api/v2/search") {
        val name = call.request.queryParameters["name"]
        val apiResponse = repositoryDynamic.searchHeroes(query = name)
        call.respond(
            message = apiResponse,
            status = HttpStatusCode.OK
        )
    }
}