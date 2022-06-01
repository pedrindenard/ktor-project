package com.backend.plugins

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.response.*
import javax.naming.AuthenticationException

fun Application.configureStatusPages() {
    install(StatusPages) {
        status(HttpStatusCode.NotFound) { call, _ ->
            call.respond(
                message = "Request does not match any route!",
                status = HttpStatusCode.NotFound
            )
        }

        exception<AuthenticationException> { call, cause ->
            call.respond(
                message = cause,
                status = HttpStatusCode.Unauthorized
            )
        }

        exception<Exception> { call, cause ->
            call.respond(
                message = cause,
                status = HttpStatusCode.BadRequest
            )
        }

        exception<Throwable> { call, cause ->
            call.respond(
                message = cause,
                status = HttpStatusCode.InternalServerError
            )
        }
    }
}