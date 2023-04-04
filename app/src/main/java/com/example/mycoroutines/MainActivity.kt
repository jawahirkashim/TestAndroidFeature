package com.example.mycoroutines

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.yield

class MainActivity : AppCompatActivity() {
    private lateinit var textView:TextView
    private val TAG = MainActivity::class.java.simpleName
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textView = findViewById(R.id.textView)
        CoroutineScope(Dispatchers.Default).launch{
            task1()
        }
        CoroutineScope(Dispatchers.Default).launch {
            task2()
        }
    }


    suspend fun task1(){
        Log.d(TAG, "start task1")  // execute seq: 1
        yield() // suspension point: now thread run if any coroutine is present => task2 coroutine
        Log.d(TAG, "edn task1")  // execute seq: 3
    }
    suspend fun task2(){
        Log.d(TAG, "start task2")   // execute seq: 2
        yield() // suspension point:
        Log.d(TAG, "end task2")   // execute seq: 4
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