package pl.dziedzic44.webhookmanager;

import java.util.ArrayList;
import java.util.Scanner;

public class Application {
    private final ArrayList<Webhook> webhooks = new ArrayList<>();
    private int selectedWebhook = -1;

    public int displayMenu() {
        System.out.println("\nmenu");
        System.out.println("[1] connect webhook");
        System.out.println("[2] disconnect the webhook");
        System.out.println("[3] list of webhooks");
        System.out.println("[4] choose a webhook");
        System.out.println("[5] open chat");
        System.out.println("[6] exit");
        System.out.print("\nenter your choice: ");
        return new Scanner(System.in).nextInt();
    }

    public void addWebhook(Webhook webhook) {
        this.webhooks.add(webhook);
    }

    public void removeWebhook(int index) {
        this.webhooks.remove(index);
    }

    public ArrayList<Webhook> getWebhooks() {
        return this.webhooks;
    }

    public void selectWebhook(int index) {
        this.selectedWebhook = index;
    }

    public int getSelectedWebhook() {
        return this.selectedWebhook;
    }
}