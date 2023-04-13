package com.example.mycoroutines

import android.util.Log
import android.widget.TextView
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.flow

private val TAG = "ta"
fun producer() = flow<Int> {
    val list = listOf (1,2,3,4,5,6,7,8,9)
    list.forEach{
        delay(1000)
        emit(it)
    }
}

fun consumer(textView: TextView, s: String) = CoroutineScope(Dispatchers.Default).launch {
    val data = producer()
    data.collect{ intVal ->
        Log.d(TAG, "consumer: $intVal  $s")
        withContext(Dispatchers.Main){
            textView.text = "consumer: $intVal  $s"
        }

    }
}