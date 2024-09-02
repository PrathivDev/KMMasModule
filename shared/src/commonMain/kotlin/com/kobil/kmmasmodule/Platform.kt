package com.kobil.kmmasmodule

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform