package com.mycompany.webfish;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class NotificationStore {

    private static final List<String> messages = new CopyOnWriteArrayList<>();

    private NotificationStore() {
    }

    public static void addMessage(String message) {
        messages.add(message);
    }

    public static List<String> getMessages() {
        return messages;
    }
}
