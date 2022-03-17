package ru.job4j.search;

import ru.job4j.storage.User;

import java.util.concurrent.ForkJoinPool;

public class Search {
    public static void main(String[] args) {
        Object[] array = new Object[20];
        for (int i = 0; i < 20; i++) {
            array[i] = new User(i, 500);
        }
        System.out.println(indexOf(array, new User(15, 500)));
   }

   public static int indexOf(Object[] array, Object obj) {
       ForkJoinPool pool = new ForkJoinPool();
       return pool.invoke(new IndexSearcher(array, 0, array.length - 1, obj));
   }
}
