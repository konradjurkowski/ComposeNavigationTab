package com.konradjurkowski.composenavigationtab

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform