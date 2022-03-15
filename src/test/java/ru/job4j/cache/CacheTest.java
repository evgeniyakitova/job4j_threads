package ru.job4j.cache;

import org.junit.Test;

import static org.junit.Assert.*;

public class CacheTest {

    @Test
    public void add() {
        Cache cache = new Cache();
        assertTrue(cache.add(new Base(1, 1, "first")));
        assertFalse(cache.add(new Base(1, 2, "second")));
        assertEquals(1, cache.size());
    }

    @Test
    public void update() {
        Cache cache = new Cache();
        cache.add(new Base(1, 1, "first"));
        cache.update(new Base(1, 1, "second"));
        assertEquals(new Base(1, 2, "second"), cache.get(1));
    }

    @Test(expected = OptimisticException.class)
    public void whenUpdateAnotherVersionThenException() {
        Cache cache = new Cache();
        cache.add(new Base(1, 1, "first"));
        cache.update(new Base(1, 2, "second"));
    }

    @Test
    public void delete() {
        Cache cache = new Cache();
        cache.add(new Base(1, 1, "first"));
        cache.delete(new Base(1, 2, "second"));
        assertEquals(1, cache.size());
        cache.delete(new Base(1, 1, "first"));
        assertEquals(0, cache.size());
    }
}