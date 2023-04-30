package com.ingloriousmind.countrieskmm

import timber.log.Timber

actual object L {
    actual fun d(msg: String) = Timber.d(msg)
    actual fun e(msg: String, throwable: Throwable?) = Timber.e(throwable, msg)
}
