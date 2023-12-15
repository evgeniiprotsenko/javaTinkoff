package edu.hw8.Task1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ServerTest {

    @Test
    void ServerClientTest() {
        new Thread(() -> {
            Server server = new Server();
            server.start();
        }).start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException ignored) {
        }

        new Thread(() -> {
            String userRequest1 = "оскорбления";
            String expected = "Если твои противники перешли на личные оскорбления, "
                + "будь уверена — твоя победа не за горами";
            Client client1 = new Client();
            String response1 = client1.runClient(userRequest1);
            assertEquals(response1, expected);
        }).start();

        new Thread(() -> {
            String userRequest2 = "личности";
            String expected = "Не переходи на личности там, где их нет";
            Client client2 = new Client();
            String response2 = client2.runClient(userRequest2);
            assertEquals(response2, expected);
        }).start();
    }

}

