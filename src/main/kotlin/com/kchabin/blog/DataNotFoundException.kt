package com.kchabin.blog

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Post Not Found")
class DataNotFoundException(): RuntimeException(){
    private val serialVersionUID: Long = 1L

    fun DataNotFoundException(message: String) {
        super.message
    }
}