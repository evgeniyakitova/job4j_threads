package ru.job4j.pool;

import ru.job4j.SimpleBlockingQueue;

import java.util.LinkedList;
import java.util.List;

public class ThreadPool {
    private final List<Thread> threads = new LinkedList<>();
    private final int size;
    private final SimpleBlockingQueue<Runnable> tasks;

    public ThreadPool() {
        size = Runtime.getRuntime().availableProcessors();
        tasks = new SimpleBlockingQueue<>(size);
        for (int i = 0; i < size; i++) {
            threads.add(new Thread(() -> {
                Runnable task;
                while (!Thread.currentThread().isInterrupted()) {
                    try {
                        task = tasks.poll();
                        task.run();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            }));
        }
    }

    public void work(Runnable job) throws InterruptedException {
            tasks.offer(job);
    }

    public void shutdown() {
        threads.forEach(Thread::interrupt);
    }
}
