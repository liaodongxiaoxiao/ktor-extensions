package com.karl.coder.ktor.extensions

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*

/**
 * Respond k
 *
 * @param block
 * @receiver
 */
suspend inline fun ApplicationCall.respondK(block: KRespondEntity.Builder.() -> Unit) {
    this.respond(HttpStatusCode.OK, KRespondEntity.Builder().apply(block).build())
}

/**
 * K respond entity
 *
 * @property status
 * @property message
 * @property data
 * @constructor Create empty K respond entity
 */
data class KRespondEntity(val status: Int = 200, val message: String = "request success.", val data: Any? = null) {
    private constructor(builder: Builder) : this(
        builder.status,
        builder.message,
        builder.data
    )

    /**
     * Builder
     *
     * @constructor Create empty Builder
     */
    class Builder {
        var status: Int = 200
        var message: String = "success."
        var data: Any? = null

        /**
         * Build
         *
         */
        fun build() = KRespondEntity(this)
    }
}