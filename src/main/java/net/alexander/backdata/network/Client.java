package net.alexander.backdata.network;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import net.alexander.backdata.database.IEntry;
import net.alexander.backdata.util.Document;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.HashMap;
import java.util.UUID;

public class Client extends Thread {

    private boolean alive;

    private Socket client;

    private HashMap<UUID, IEntry> waiting;

    private String username;
    private String password;

    private String ip;
    private int port;

    public Client() {
        alive = true;
        waiting = new HashMap<>();
        setDaemon(true);
    }

    public void setCredentials(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public void connect(String ip, int port) {
        this.ip = ip;
        this.port = port;
        start();
    }

    @Override
    public void run() {
        client = new Socket();
        try {
            client.connect(new InetSocketAddress(ip, port));
        } catch (IOException e) {
            e.printStackTrace();
        }

        write(new Document().addString("username", username).addString("password", password).create());

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
            JsonObject response = (JsonObject) new JsonParser().parse(reader.readLine());

            if(response.get("response").getAsBoolean()) {
                String message;

                while((message = reader.readLine()) != null) {

                }
            } else {
                alive = false;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public IEntry sendRequest(String query) {
        return null;
    }

    private void write(JsonObject object) {
        try {
            PrintStream printStream = new PrintStream(client.getOutputStream());
            printStream.println(object.toString());
            printStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
