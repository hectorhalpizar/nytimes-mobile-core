package me.hectorhalpizar.core.nytimes


class Greeting {
    fun greeting(): String {
        return "Hello, ${Platform().platform}!"
    }
}
