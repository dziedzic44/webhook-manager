package pl.dziedzic44.webhookmanager;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Application application = new Application();
        Scanner scanner = new Scanner(System.in);
        System.out.println("welcome to discord webhook manager by dziedzic44");
        while (true) {
            switch (application.displayMenu()) {
                case 1 -> {
                    System.out.print("enter the name of your webhook: ");
                    String name = scanner.next();
                    System.out.print("enter your webhook url: ");
                    try {
                        URL url = new URL(scanner.next());
                        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                        httpURLConnection.setRequestMethod("HEAD");
                        httpURLConnection.connect();
                        if (httpURLConnection.getResponseCode() == 200) application.addWebhook(new Webhook(name, url));
                        else System.out.println("failed to connect to the webhook");
                        httpURLConnection.disconnect();
                    } catch (IOException exception) {
                        System.out.println("failed to connect to the webhook");
                    }
                }
                case 2 -> {
                    System.out.print("enter the index of the webhook you want to remove: ");
                    int index = scanner.nextInt();
                    if (index >= 0 && index <= application.getWebhooks().size()) application.removeWebhook(index);
                    else System.out.println("the specified index is out of range");
                }
                case 3 -> {
                    for (int i = 0; i < application.getWebhooks().size(); i++) {
                        System.out.println(i + ". " + application.getWebhooks().get(i).getName());
                    }
                }
                case 4 -> {
                    System.out.print("enter the index of the webhook you want to select: ");
                    int index = scanner.nextInt();
                    if (index >= 0 && index <= application.getWebhooks().size()) application.selectWebhook(index);
                    else System.out.println("the specified index is out of range");
                }
                case 5 -> {
                    if (application.getSelectedWebhook() == -1 || application.getSelectedWebhook() > application.getWebhooks().size()) {
                        System.out.println("to open the chat you must first select the webhook");
                        break;
                    }
                    Webhook webhook = application.getWebhooks().get(application.getSelectedWebhook());
                    System.out.print(">> ");
                    String message = scanner.next();
                    while (!(message = scanner.nextLine()).equalsIgnoreCase("/exit")) {
                        System.out.print(">> ");
                        if (!message.isBlank()) webhook.sendMessage(message);
                    }
                }
                case 6 -> System.exit(0);
            }
        }
    }
}