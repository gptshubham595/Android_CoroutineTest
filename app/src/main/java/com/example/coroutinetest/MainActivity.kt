package com.example.coroutinetest

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private val simpleWorker by lazy { SimpleWorker() }
    private val handler by lazy {
        object : Handler(Looper.getMainLooper()) {
            override fun handleMessage(msg: Message) {
                super.handleMessage(msg)
                Log.d("MainActivity", "Message: ${msg.obj} Received at ${Thread.currentThread().name}")
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        simpleWorker.startThread()

        simpleWorker.execute {
            try {
                Thread.sleep(1000)
                Log.d("SimpleWorker", "Task 1 ${Thread.currentThread().name}")

                val message = Message.obtain()
                message.obj = "Task 1 Sent from ${Thread.currentThread().name}"
                handler.sendMessage(message)

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }.execute {
            try {
                Thread.sleep(1000)
                Log.d("SimpleWorker", "Task 2 ${Thread.currentThread().name}")

                val message = Message.obtain()
                message.obj = "Task 2 Sent from ${Thread.currentThread().name}"
                handler.sendMessage(message)

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }.execute {
            try {
                Thread.sleep(1000)
                Log.d("SimpleWorker", "Task 3 ${Thread.currentThread().name}")

                val message = Message.obtain()
                message.obj = "Task 3 Sent from ${Thread.currentThread().name}"
                handler.sendMessage(message)

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        simpleWorker.quit()
    }
}

