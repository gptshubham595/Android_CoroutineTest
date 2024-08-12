package com.example.coroutinetest

object MyThreadTask : Thread() {

    override fun start() {
        println("$green MyThreadTask started: ${Thread.currentThread().name}")
        super.start()
    }

    override fun run() {
        println("$green MyThreadTask started: ${Thread.currentThread().name} working parallel with main thread")
        executeTask()
    }

    fun executeTask() {
        for (i in 1..10) {
            println("$green Count: $i Printer2")
        }
    }
}

