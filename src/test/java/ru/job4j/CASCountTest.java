package ru.job4j;

import org.junit.Test;

import static org.junit.Assert.*;

public class CASCountTest {

    @Test
    public void increment() throws InterruptedException {
        CASCount casCount = new CASCount();
        Runnable action = () -> {
            for (int i = 1; i <= 50; i++) {
                casCount.increment();
            }
        };
        Thread first = new Thread(action);
        Thread second = new Thread(action);
        first.start();
        second.start();
        second.join();
        first.join();
        assertEquals(100, casCount.get());
    }
}