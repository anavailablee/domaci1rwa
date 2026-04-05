package http;

import app.QuoteRequestHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static final int TCP_PORT = 8080;

    public static void main(String[] args) {
        try {
            ServerSocket ss = new ServerSocket(TCP_PORT);
            System.out.println("Glavni servis pokrenut na portu " + TCP_PORT);
            while (true) {
                Socket sock = ss.accept();
                new Thread(new ServerThread(sock, new QuoteRequestHandler())).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
