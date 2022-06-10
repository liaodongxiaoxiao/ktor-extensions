package com.karl.coder.ktor.extensions

import io.ktor.http.*

fun Headers.print() {
    val set = this.names()
    for (key in set) {
        println("key->${key}: value->${this[key]}")
    }
}