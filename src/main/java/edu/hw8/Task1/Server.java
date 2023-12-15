package edu.hw8.Task1;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private static final int PORT = 8001;
    private static final int MAX_CONNECTIONS = 3;
    private static final int BYTE_BUFFER_CAPACITY = 1024;
    private static final ExecutorService EXECUTOR = Executors.newFixedThreadPool(MAX_CONNECTIONS);

    public void start() {
        try (ServerSocketChannel serverSocketChannel = ServerSocketChannel.open()) {
            serverSocketChannel.bind(new InetSocketAddress(PORT));

            while (true) {
                SocketChannel clientChannel = serverSocketChannel.accept();
                EXECUTOR.submit(() -> handleClient(clientChannel));
            }
        } catch (IOException ignored) {
        }
    }

    private static void handleClient(SocketChannel clientChannel) {
        try {
            ByteBuffer buffer = ByteBuffer.allocate(BYTE_BUFFER_CAPACITY);

            int bytesRead = clientChannel.read(buffer);
            if (bytesRead == -1) {
                clientChannel.close();
                return;
            }

            buffer.flip();
            String clientRequest = new String(buffer.array(), 0, bytesRead);

            String response = getQuoteByKeyword(clientRequest);
            ByteBuffer responseBuffer = ByteBuffer.wrap(response.getBytes());
            clientChannel.write(responseBuffer);

            clientChannel.close();
        } catch (IOException ignored) {
        }
    }

    private static String getQuoteByKeyword(String keyword) {
        return switch (keyword.toLowerCase()) {
            case "личности" -> "Не переходи на личности там, где их нет";
            case "оскорбления" ->
                "Если твои противники перешли на личные оскорбления, будь уверена — твоя победа не за горами";
            case "глупый" -> "А я тебе говорил, что ты глупый? Так вот, я забираю свои слова обратно... "
                + "Ты просто бог идиотизма.";
            case "интеллект" -> "Чем ниже интеллект, тем громче оскорбления";
            default -> "Извините, я не понял ваш запрос";
        };
    }
}
