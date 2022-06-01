package com.backend.routes

import com.backend.models.HeroResponse
import com.backend.repository.HeroRepository
import com.backend.repository.HeroRepositoryDynamic
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Route.getAllHeroes() {

    val repository: HeroRepository by inject()
    val repositoryDynamic: HeroRepositoryDynamic by inject()

    get(path = "/api/v1/heroes") {
        try {
            val page = call.request.queryParameters["page"]?.toInt() ?: 1

            require(page in 1..5)

            call.respond(
                message = repository.getAllHeroes(page = page),
                status = HttpStatusCode.OK
            )
        } catch (e: NumberFormatException) {
            call.respond(
                message = HeroResponse(success = false, message = "Only numbers allowed."),
                status = HttpStatusCode.BadRequest
            )
        } catch (e: IllegalArgumentException) {
            call.respond(
                message = HeroResponse(success = false, message = "Current page not found."),
                status = HttpStatusCode.NotFound
            )
        }
    }

    get(path = "/api/v2/heroes") {
        try {
            val page = call.request.queryParameters["page"]?.toInt() ?: 1
            val limit = call.request.queryParameters["limit"]?.toInt() ?: 4

            require(page in 1..5)

            call.respond(
                message = repositoryDynamic.getAllHeroes(page = page, limit = limit),
                status = HttpStatusCode.OK
            )
        } catch (e: NumberFormatException) {
            call.respond(
                message = HeroResponse(success = false, message = "Only numbers allowed."),
                status = HttpStatusCode.BadRequest
            )
        } catch (e: IllegalArgumentException) {
            call.respond(
                message = HeroResponse(success = false, message = "Current page not found."),
                status = HttpStatusCode.NotFound
            )
        }
    }
}