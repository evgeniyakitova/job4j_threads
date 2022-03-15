package ru.job4j.service;

import java.util.concurrent.ExecutorService;

public class EmailNotification {
    private final ExecutorService pool;

    public EmailNotification(ExecutorService pool) {
        this.pool = pool;
    }

    public void emailTo(User user) {
        pool.execute(() ->
            send(String.format("Notification %s to email %s", user.getUsername(), user.getEmail()),
                    String.format("Add a new event to %s", user.getUsername()),
                    user.getEmail()
            )
        );
    }

    public void send(String subject, String body, String email) {

    }

    public void close() {
        pool.shutdown();
        while (!pool.isTerminated()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
