package com.ingloriousmind.countrieskmm

actual object L {
    actual fun d(msg: String) = println("\uD83D\uDC9A $msg")

    actual fun e(msg: String, throwable: Throwable?) = println("❤️ $msg")
        .also { throwable?.printStackTrace() }
}
