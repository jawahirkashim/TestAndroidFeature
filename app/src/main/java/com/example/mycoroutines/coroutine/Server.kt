package com.example.mysocketbasedapp.coroutine

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.io.PrintWriter
import java.net.ServerSocket

class Server {
    private lateinit var serverSocket: ServerSocket
    private val PORT = 8080 // Server port

    fun startServer() {
        GlobalScope.launch(Dispatchers.IO) {
            try {
                serverSocket = ServerSocket(PORT)
                println("Server started on port $PORT")

                while (true) {
                    val clientSocket = serverSocket.accept()
                    println("Client connected: ${clientSocket.inetAddress.hostAddress}")

                    // Input stream to receive data from client
                    val `in` = BufferedReader(InputStreamReader(clientSocket.getInputStream()))
                    // Output stream to send data to client
                    val out = PrintWriter(clientSocket.getOutputStream(), true)

                    var inputLine: String?
                    while (`in`.readLine().also { inputLine = it } != null) {
                        println("Received from client: $inputLine")
                        // Echo the received message back to the client
                        out.println("Server: $inputLine")
                    }

                    // Close input stream, output stream, and client socket
                    `in`.close()
                    out.close()
                    clientSocket.close()

                }

            } catch (e: IOException) {
                e.printStackTrace()
            } finally {
                try {
                    serverSocket.close()
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
        }
    }

    fun stopServer() {
        GlobalScope.launch(Dispatchers.IO) {
            serverSocket.close()
        }
    }
}
