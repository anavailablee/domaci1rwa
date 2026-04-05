package app;

import http.ServerThread;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class QuoteOfTheDayServer {

    public static final int TCP_PORT = 8081;

    public static void main(String[] args) {
        try {
            ServerSocket ss = new ServerSocket(TCP_PORT);
            System.out.println("Pomocni servis pokrenut na portu " + TCP_PORT);
            while (true) {
                Socket sock = ss.accept();
                new Thread(new ServerThread(sock, new QuoteOfTheDayRequestHandler())).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
