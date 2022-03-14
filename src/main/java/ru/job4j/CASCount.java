package ru.job4j;

import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.atomic.AtomicReference;

@ThreadSafe
public class CASCount {
    private final AtomicReference<Integer> count = new AtomicReference<>(0);

    public void increment() {
        int expected;
        int newValue;
        do {
            expected = count.get();
            newValue = expected + 1;
        } while (!count.compareAndSet(expected, newValue));
    }

    public int get() {
        return count.get();
    }
}
