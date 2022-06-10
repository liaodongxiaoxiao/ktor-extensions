package com.karl.coder.ktor.tools

import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import java.io.File
import java.lang.StringBuilder

object JSONUtils {

    fun getTextFromFile(path: String, fileName: String): String? {
        val file = File(path, fileName)
        if (!file.exists() || !file.isFile) {
            return null
        }
        file.useLines {
            val builder = StringBuilder()
            it.forEach { msg ->
                builder.append(msg)
            }
            return builder.toString()
        }
    }

    fun getJsonObjectFromFile(path: String, fileName: String): JsonObject? {
        val text = getTextFromFile(path, fileName)
        if (text != null) {
            val gson = Gson()
            return gson.fromJson(text, JsonObject::class.java)
        }

        return null
    }

    fun getJsonArrayFromFile(path: String, fileName: String): JsonArray? {
        val text = getTextFromFile(path, fileName)
        if (text != null) {
            val gson = Gson()
            return gson.fromJson(text, JsonArray::class.java)
        }

        return null
    }
}