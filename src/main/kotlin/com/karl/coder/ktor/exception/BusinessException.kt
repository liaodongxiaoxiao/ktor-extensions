package com.karl.coder.ktor.exception

class BusinessException(val errorMessage: String, val errorCode: Int = 205) : Exception(errorMessage)