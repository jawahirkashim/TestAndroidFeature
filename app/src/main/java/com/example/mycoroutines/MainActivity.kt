package com.example.mycoroutines

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.mysocketbasedapp.coroutine.Client
import com.example.mysocketbasedapp.coroutine.Server
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var textView: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textView = findViewById(R.id.textView)
        // Coroutine
        startServerClientInCoroutine()
    }

    private fun startServerClientInCoroutine() {
        // Start the server
        val server = Server()
        server.startServer()

        // Start the client
        val serverIP = "127.0.0.1" // Loopback address
        val serverPort = 8080 // Server port
        val messageToSend = "Hello from client!"
        val client = Client(serverIP, serverPort, messageToSend)
        client.startClient()

        GlobalScope.launch(Dispatchers.IO) {
            delay(2000)
            //server.stopServer()
        }
    }
}