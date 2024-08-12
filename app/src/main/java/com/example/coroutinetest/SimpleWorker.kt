package com.example.coroutinetest

import android.util.Log
import kotlinx.coroutines.delay
import java.util.concurrent.ConcurrentLinkedQueue
import java.util.concurrent.atomic.AtomicBoolean

class SimpleWorker : Thread() {
    private val TAG = "SimpleWorker"
    private val alive = AtomicBoolean(true)
    private val queue: ConcurrentLinkedQueue<Runnable> = ConcurrentLinkedQueue() //message queue

    fun startThread() {
        start()
    }

    override fun run() {
        super.run()
        while (alive.get()) { //looper
            sleep(2000)
            Log.d(TAG, "Running ...")
            val task: Runnable? = queue.poll()
            if(task != null) {
                task.run()
            }
        }
        Log.d(TAG, "Stopped")
    }

    fun execute(task: Runnable): SimpleWorker {
        queue.add(task)
        return this
    }

    fun quit() {
        alive.set(false)
    }
}