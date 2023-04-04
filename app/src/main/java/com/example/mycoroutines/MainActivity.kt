package com.example.mycoroutines

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {
    private lateinit var textView:TextView
    private val TAG = MainActivity::class.java.simpleName
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textView = findViewById(R.id.textView)
        CoroutineScope(Dispatchers.IO).launch {
            printFollowers()
        }

    }

    suspend fun printFollowers(){
        var follower = 0
        val job = CoroutineScope(Dispatchers.IO).launch {
            follower = getTwitterFollowers() // it launch parallel in separate thread and took 1sec.
        }
        job.join()
        Log.d(TAG, "printFollowers: $follower")  // follower: 0 , since this variable will get updated after 1 sec.
    }
    suspend fun getTwitterFollowers():Int{
        delay(1000)
        return 29
    }
    fun doExecute(view: View) {
        Log.d(TAG, "doExecute: thread: "+Thread.currentThread().name)

        for (i in 0..1000_000_000_00){

        }
    }

    fun doCounter(view: View) {

        Log.d(TAG, "doCounter: thread: ${Thread.currentThread().name}")
         textView.text = "${textView.text.toString().toInt()+1}"
    }
}