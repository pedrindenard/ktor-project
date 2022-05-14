package com.backend.plugins

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.defaultheaders.*
import java.time.Duration

fun Application.configureDefaultHeader() {
    install(DefaultHeaders) {
        val days: Long = 365
        val oneYearInSeconds = Duration.ofDays(days).seconds
        header(
            name = HttpHeaders.CacheControl,
            value = "public, max-age=${oneYearInSeconds}, immutable"
        )
    }
}