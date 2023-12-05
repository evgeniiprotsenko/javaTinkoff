package edu.hw6;

import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("MagicNumber")
public class Task6 {

    private static final int MIN_PORT = 0;
    private static final int MAX_PORT = 49151;

    public Map<Integer, String> scanPorts() {
        Map<Integer, String> result = new HashMap<>();

        for (int port = MIN_PORT; port <= MAX_PORT; port++) {
            String service = getService(port);
            if (!service.isEmpty()) {
                String protocol = getOpenProtocol(port);
                result.put(port, String.format("%-6s %-5d %s", protocol, port, service));
            }
        }
        return result;
    }

    private String getOpenProtocol(int port) {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            return "TCP";
        } catch (Exception e) {
            try (DatagramSocket datagramSocket = new DatagramSocket(port)) {
                return "UDP";
            } catch (Exception ex) {
                return "";
            }
        }
    }

    private String getService(int port) {
        return switch (port) {
            case 80 -> "HTTP (HyperText Transfer Protocol)";
            case 21 -> "FTP (File Transfer Protocol)";
            case 25 -> "SMTP (Simple Mail Transfer Protocol)";
            case 22 -> "SSH (Secure Shell)";
            case 443 -> "HTTPS (HyperText Transfer Protocol Secure)";
            case 53 -> "DNS (Domain Name System)";
            case 3306 -> "MySQL Database";
            case 135 -> "EPMAP";
            case 137 -> "Служба имен NetBIOS";
            case 138 -> "Служба датаграмм NetBIOS";
            case 139 -> "Служба сеансов NetBIOS";
            case 445 -> "Microsoft-DS Active Directory";
            case 843 -> "Adobe Flash";
            case 1900 -> "Simple Service Discovery Protocol (SSDP)";
            case 3702 -> "Динамическое обнаружение веб-служб";
            default -> "";
        };
    }
}
