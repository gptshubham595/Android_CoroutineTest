package com.example.coroutinetest

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.plus
import java.util.concurrent.Executors
import java.util.concurrent.atomic.AtomicInteger

val red = "\u001b[31m"
val yellow = "\u001b[33m"
val green = "\u001b[32m"
val blue = "\u001b[34m"

object MainThread {
    val i = AtomicInteger(0)

    @JvmStatic
    fun main(args: Array<String>) {


        println("$red Main Thread started: ${Thread.currentThread().name}")
        // Thread always execute in a sequence
//
//        //Job2
//        for (i in 1..10) {
//            println("$red Count: $i Printer1")
//        }

        //Job3
        // Till Job2 is not completed, Job3 will not start, so jobs will be waiting and not get executed
        // If Job2 is long running task, in such case OS/JVM will kill the app as it is not responding
        // So we need to a thread
        // Here I am creating a thread and executing the task

//        MyThreadTask.start()
//
//        //Job3
//        val runnableTask = MyRunnableTask
//        val thread = Thread(runnableTask)
//        thread.start()


        //Job5
        println("$red App finished ${Thread.currentThread().name}")

        val thread = Executors.newFixedThreadPool(2)
        val dispatcher = thread.asCoroutineDispatcher()

        val scope = CoroutineScope(dispatcher) + SupervisorJob()
        val a= scope.launch {
            while(true){
                delay(1000)
                println("$red 1")
            }
        }
        val b=scope.launch {
            var i =0
            while(i<10){
                delay(1000)
                println("$yellow 2")
                i += 1
            }
            throw Exception("error")
        }

//        CoroutineScope(dispatcher).launch {
//            var colour = red
//            if (Thread.currentThread().name.contains("main")) colour = red
//            if (Thread.currentThread().name.contains("1")) colour = yellow
//            if (Thread.currentThread().name.contains("2")) colour = green
//
//            println("$colour Coroutine1 started using thread = ${Thread.currentThread().name}")
//            suspendedTask(colour)
//            println("$colour Coroutine1 finished")
//        }
//
//        CoroutineScope(dispatcher).launch {
//            var colour = red
//            if (Thread.currentThread().name.contains("main")) colour = red
//            if (Thread.currentThread().name.contains("1")) colour = yellow
//            if (Thread.currentThread().name.contains("2")) colour = green
//
//            println("$green Coroutine2 started using thread = ${Thread.currentThread().name}")
//            notSuspendedTask(colour)
//            println("$green Coroutine2 finished")
//        }
//        CoroutineScope(dispatcher).launch {
//            var colour = red
//            if (Thread.currentThread().name.contains("main")) colour = red
//            if (Thread.currentThread().name.contains("1")) colour = yellow
//            if (Thread.currentThread().name.contains("2")) colour = green
//
//
//            println("$colour Coroutine3 started using thread = ${Thread.currentThread().name}")
//            notSuspendedTask(colour)
//            println("$colour Coroutine3 finished")
//        }

//        CoroutineScope(dispatcher).launch{
//            suspendedTask2()
//        }

    }

    suspend fun suspendedTask2() {
        while(true){
            print("hi")
        }
    }

    suspend fun suspendedTask(colour: String) {
        while (i.get() < 10) {
            if (i.get() == 5) delay(5000)
            println(
                "$colour Count: $i Printer4 using thread = ${Thread.currentThread().name} ${System.currentTimeMillis()} ${
                    System.identityHashCode(
                        i
                    )
                }"
            )
            i.incrementAndGet()
        }
    }

    //suspend fun notSuspendedTask() {
//    for (i in 1..10) {
//        if(i== 4) delay(1000)
//        println("$green Count: $i Printer5 using thread = ${Thread.currentThread().name} ${System.currentTimeMillis()}")
//    }
//}
    fun notSuspendedTask(colour: String) {
        for (i in 1..10) {
            println("$colour Count: $i Printer5 using thread = ${Thread.currentThread().name} ${System.currentTimeMillis()}")
        }
    }
}




