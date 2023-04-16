package me.fulln.alg;

import java.util.LinkedHashMap;

public class LRUMap extends LinkedHashMap<Integer, Integer> {

    private int capacity;

    public LRUMap(int capacity) {
        super(capacity, 0.75f, true);
        this.capacity = capacity;
    }

    public Integer get(int key) {
        return super.get(key);
    }

    @Override
    protected boolean removeEldestEntry(java.util.Map.Entry<Integer, Integer> eldest) {
        return size() > capacity;
    }
}
