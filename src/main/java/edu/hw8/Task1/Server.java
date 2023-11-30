package edu.hw8.Task1;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void runServer() {
        try (ServerSocket server = new ServerSocket(8000)) {
            while (true) {
                new Thread(() ->
                {
                    try (Socket socket = server.accept()) {
                        BufferedWriter writer = new BufferedWriter(
                            new OutputStreamWriter(
                                socket.getOutputStream()));

                        BufferedReader reader = new BufferedReader(
                            new InputStreamReader(
                                socket.getInputStream()));

                    } catch (NullPointerException | IOException ignored) {
                    }



                }
                ).start();
            }
        } catch (IOException ignored) {
        }
    }
}
