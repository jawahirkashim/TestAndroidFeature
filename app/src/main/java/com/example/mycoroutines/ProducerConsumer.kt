package com.example.mycoroutines

import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

private val TAG = "Producer_Consumer"
fun producer() = flow<Int> {
    val list = listOf (1,2,3,4,5,6,7,8,9)
    list.forEach{
        delay(1000)
        emit(it)
    }
}

fun consumer() = CoroutineScope(Dispatchers.Default).launch {
    val data = producer()
    data.collect{ intVal ->
        Log.d(TAG, "consumer: $intVal")
    }
}