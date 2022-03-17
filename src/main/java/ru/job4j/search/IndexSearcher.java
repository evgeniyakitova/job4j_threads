package ru.job4j.search;

import java.util.Objects;
import java.util.concurrent.RecursiveTask;

public class IndexSearcher extends RecursiveTask<Integer> {
    private final Object[] array;
    private final int begin;
    private final int end;
    private final Object obj;

    public IndexSearcher(Object[] array, int begin, int end, Object obj) {
        this.array = array;
        this.begin = begin;
        this.end = end;
        this.obj = obj;
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
        IndexSearcher searcher1 = new IndexSearcher(array, begin, mid, obj);
        IndexSearcher searcher2 = new IndexSearcher(array, mid + 1, end, obj);
        searcher1.fork();
        searcher2.fork();
        return Math.max(searcher2.join(), searcher1.join());
    }
}
