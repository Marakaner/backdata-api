package net.alexander.backdata.network;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

public class Client extends Thread {

    private Socket client;

    private String username;
    private String password;
    private String ip;
    private int port;

    public Client() {
    }

    public void setCredentials(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public void connect(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }

    @Override
    public void run() {
        client = new Socket();
        try {
            client.connect(new InetSocketAddress(ip, port));
        } catch (IOException e) {
            e.printStackTrace();
        }



    }

}
