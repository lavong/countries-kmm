package com.ingloriousmind.countrieskmm

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform