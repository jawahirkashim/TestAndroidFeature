package com.example.mysocketbasedapp.coroutine

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.io.PrintWriter
import java.net.Socket

class Client(
    private val serverIP: String,
    private val serverPort: Int,
    private val messageToSend: String
) {
    fun startClient() {
        GlobalScope.launch(Dispatchers.IO) {
            try {
                println("Client started")
                val socket = Socket(serverIP, serverPort)

                // Input stream to receive data from server
                val `in` = BufferedReader(InputStreamReader(socket.getInputStream()))
                // Output stream to send data to server
                val out = PrintWriter(socket.getOutputStream(), true)

                // Send message to server
                out.println(messageToSend)

                // Read response from server
                val response = `in`.readLine()
                println("Received from server: $response")

                // Close input stream, output stream, and socket
                `in`.close()
                out.close()
                socket.close()

            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }
}
