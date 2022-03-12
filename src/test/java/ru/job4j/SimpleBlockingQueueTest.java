package ru.job4j;

import org.junit.Test;

import static org.junit.Assert.*;

public class SimpleBlockingQueueTest {

    @Test
    public void whenOffer15AndPoll5Then10() throws InterruptedException {
        SimpleBlockingQueue<Integer> blockingQueue = new SimpleBlockingQueue<>(10);
        Thread producer = new Thread(() -> {
                for (int i = 0; i < 15; i++) {
                    try {
                        blockingQueue.offer(i);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
        });
        Thread consumer = new Thread(() -> {
                for (int i = 0; i < 5; i++) {
                    try {
                        blockingQueue.poll();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
        });
        consumer.start();
        producer.start();
        producer.join();
        consumer.join();
        assertEquals(blockingQueue.size(), 10);
    }
}