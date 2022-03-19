package ru.job4j.search;

import java.util.Objects;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class IndexSearcher<T> extends RecursiveTask<Integer> {
    private final T[] array;
    private final int begin;
    private final int end;
    private final T obj;

    public IndexSearcher(T[] array, int begin, int end, T obj) {
        this.array = array;
        this.begin = begin;
        this.end = end;
        this.obj = obj;
    }

    public static <T> int indexOf(T[] array, T obj) {
        ForkJoinPool pool = new ForkJoinPool();
        IndexSearcher<T> searcher = new IndexSearcher<>(array, 0, array.length - 1, obj);
        return pool.invoke(searcher);
    }

    @Override
    protected Integer compute() {
        if ((end - begin) <= 9) {
            int result = -1;
            for (int i = begin; i <= end; i++) {
                if (Objects.equals(array[i], obj)) {
                    result = i;
                    break;
                }
            }
            return result;
        }
        int mid = (begin + end) / 2;
        IndexSearcher<T> searcher1 = new IndexSearcher<>(array, begin, mid, obj);
        IndexSearcher<T> searcher2 = new IndexSearcher<>(array, mid + 1, end, obj);
        searcher1.fork();
        searcher2.fork();
        return Math.max(searcher2.join(), searcher1.join());
    }
}
