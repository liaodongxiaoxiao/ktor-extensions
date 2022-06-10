package com.karl.coder.ktor.db

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.transactions.transaction

fun installMySQLConnection(host: String, db: String, user: String, password: String) {


    val url = "jdbc:mysql://$host/$db?characterEncoding=utf-8&serverTimezone=UTC"
    Database.connect(
        url = url,
        driver = "com.mysql.jdbc.Driver",
        user = user,
        password = password
    )
}

suspend fun <T> dbQuery(block: () -> T): T =
    withContext(Dispatchers.IO) {
        transaction { block() }
    }