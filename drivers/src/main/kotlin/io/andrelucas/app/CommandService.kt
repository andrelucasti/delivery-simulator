package io.andrelucas.app

interface CommandService<T> {
    fun execute(command: T)
}