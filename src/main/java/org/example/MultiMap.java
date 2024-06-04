package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class MultiMap<K, V> {
    private final HashMap<K, List<V>> map;

    public MultiMap() {
        this.map = new HashMap<>();
    }

    public List<V> get(K key) {
        return map.getOrDefault(key, Collections.emptyList());
    }

    public void put(K key, V value) {
        map.computeIfAbsent(key, k -> new ArrayList<>()).add(value);
    }
    @Override
    public String toString() {
        return "MultiMap{" +
                "map=" + map +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MultiMap<?, ?> multiMap = (MultiMap<?, ?>) o;

        return map.equals(multiMap.map);
    }

    @Override
    public int hashCode() {
        return map.hashCode();
    }
}



