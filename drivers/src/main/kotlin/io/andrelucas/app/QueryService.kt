package io.andrelucas.app

interface QueryService<T> {
    fun execute(query: T): T
}