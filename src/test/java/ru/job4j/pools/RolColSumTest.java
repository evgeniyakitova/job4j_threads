package ru.job4j.pools;

import org.junit.Test;

import java.util.concurrent.ExecutionException;

import static org.junit.Assert.*;
import static ru.job4j.pools.RolColSum.*;

public class RolColSumTest {

    @Test
    public void whenAsyncSum() throws ExecutionException, InterruptedException {
        int[][] matrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        Sums[] expected = {new Sums(6, 12), new Sums(15, 15), new Sums(24, 18)};
        assertArrayEquals(expected, asyncSum(matrix));
    }

    @Test
    public void whenSyncSum() {
        int[][] matrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        Sums[] expected = {new Sums(6, 12), new Sums(15, 15), new Sums(24, 18)};
        assertArrayEquals(expected, syncSum(matrix));
    }
}