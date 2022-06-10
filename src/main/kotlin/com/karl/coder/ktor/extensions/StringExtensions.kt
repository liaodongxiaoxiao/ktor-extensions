package com.karl.coder.ktor.extensions

fun String?.exception(default: String = "未知错误"): String {
    return if (this == null) {
        default
    } else {
        when {
            this.contains("PRIMARY") -> "主键冲突错误"
            else -> default
        }
    }
}