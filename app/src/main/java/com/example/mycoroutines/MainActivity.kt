package com.example.mycoroutines

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import kotlinx.coroutines.*
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    private lateinit var textView:TextView
    private val TAG: String = MainActivity::class.java.simpleName
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textView = findViewById(R.id.textView)

        consumer()
    }


    fun doExecute(view: View) {
        CoroutineScope(Dispatchers.IO).launch {
            Log.d(TAG, "1- doExecute: ${Thread.currentThread().name}")
            executeLongRunningTask()
        }

        MainScope().launch(Dispatchers.Main) {
            Log.i(TAG, "2 - doExecute: ${Thread.currentThread().name}")
            //executeLongRunningTask()
        }

        GlobalScope.launch(Dispatchers.Default) {
            Log.v(TAG, "3 - doExecute: ${Thread.currentThread().name}")
            executeLongRunningTask()
        }

    }

    private fun executeLongRunningTask() {
        Log.d(TAG, "doExecute: thread: "+Thread.currentThread().name)

        for (i in 0..1000_000_000_000L){

        }
    }

    fun doCounter(view: View) {

        Log.d(TAG, "doCounter: thread: ${Thread.currentThread().name}")
         textView.text = "${textView.text.toString().toInt()+1}"
    }
}