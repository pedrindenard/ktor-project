package com.backend.plugins

import com.backend.di.repositoryModule
import io.ktor.server.application.*
import org.koin.ktor.plugin.Koin

fun Application.configureKoin() {
    install(Koin) {
        modules(listOf(repositoryModule))
    }
}