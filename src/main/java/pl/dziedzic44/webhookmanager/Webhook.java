package pl.dziedzic44.webhookmanager;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class Webhook {
    private final String name;
    private final URL url;

    public Webhook(String name, URL url) {
        this.name = name;
        this.url = url;
    }

    public void sendMessage(String message) {
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) this.url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setRequestProperty("Content-Type", "application/json");
            httpURLConnection.setDoOutput(true);
            String json = "{\"content\": \"" + message + "\"}";
            OutputStream outputStream = httpURLConnection.getOutputStream();
            outputStream.write(json.getBytes(StandardCharsets.UTF_8), 0, json.length());
            if (httpURLConnection.getResponseCode() != 204) System.out.println(">> failed to send message");
            httpURLConnection.disconnect();
        } catch (IOException exception) {
            System.out.println(">> failed to send message");
        }
    }

    public String getName() {
        return this.name;
    }
}