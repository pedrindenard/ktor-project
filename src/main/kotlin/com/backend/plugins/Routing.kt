package com.backend.plugins

import com.backend.routes.getAllHeroes
import com.backend.routes.getSearchedHero
import com.backend.routes.root
import io.ktor.server.application.*
import io.ktor.server.http.content.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        root()
        getAllHeroes()
        getSearchedHero()

        static(remotePath = "/images") {
            resources(resourcePackage = "images")
        }
    }
}