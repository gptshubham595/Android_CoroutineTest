package com.example.coroutinetest

object MyRunnableTask : Runnable {

    override fun run() {
        println("$blue MyRunnableTask started at thread: ${Thread.currentThread().name} working parallel with main thread")
        executeTask()
    }

    fun executeTask() {
        for (i in 1..10) {
            println("$blue Count: $i Printer3")
        }
    }
}