package edu.hw8.Task1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

public class Client {
    private static final String SERVER_IP = "localhost";
    private static final int SERVER_PORT = 8001;
    private static final String ERROR_MSG = "Ошибка при отправке сообщения";

    public String runClient(String userRequest) {
        try (Socket socket = new Socket(SERVER_IP, SERVER_PORT);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        ) {
            return sendMessage(socket, userRequest);
        } catch (Exception e) {
            return ERROR_MSG;
        }
    }

    private static String sendMessage(Socket socket, String message) {
        try {
            socket.getOutputStream().write(message.getBytes());

            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            return in.readLine();
        } catch (Exception e) {
            return ERROR_MSG;
        }
    }
}
