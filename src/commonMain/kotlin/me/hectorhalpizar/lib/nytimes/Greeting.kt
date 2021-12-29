package me.hectorhalpizar.lib.nytimes


class Greeting {
    fun greeting(): String {
        return "Hello, ${Platform().platform}!"
    }
}
