package ru.job4j.cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Cache {
    private final Map<Integer, Base> memory = new ConcurrentHashMap<>();

    public boolean add(Base model) {
        return memory.putIfAbsent(model.getId(), model) == null;
    }

    public boolean update(Base model) {
        return memory.computeIfPresent(model.getId(), (key, value) -> {
            int currentVersion = value.getVersion();
            if (model.getVersion() != currentVersion) {
                throw new OptimisticException("Versions are not equal");
            }
            return new Base(value.getId(), currentVersion + 1, model.getName());
        }) != null;
    }

    public void delete(Base model) {
        memory.remove(model.getId(), model);
    }

    public Base get(int id) {
        return memory.get(id);
    }

    public int size() {
        return memory.size();
    }
 }
