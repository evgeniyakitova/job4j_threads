package ru.job4j.search;

import org.junit.Test;
import ru.job4j.storage.User;

import static org.junit.Assert.*;

public class IndexSearcherTest {

    @Test
    public void whenSearchingForUserThenIndexIs20() {
        User[] array = new User[50];
        for (int i = 0; i < 50; i++) {
            array[i] = new User(i, 500);
        }
        assertEquals(IndexSearcher.indexOf(array, new User(20, 500)), 20);
    }

    @Test
    public void whenNotFoundObjectThenResultIsNegativeValue() {
        String[] array = new String[50];
        for (int i = 0; i < 50; i++) {
            array[i] = String.valueOf(i);
        }
        assertEquals(IndexSearcher.indexOf(array, "100"), -1);
    }
}