package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;


import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * A thread-safe MultiMap implementation that allows multiple values per key.
 *
 * @param <K> the type of keys maintained by this map
 * @param <V> the type of mapped values
 */
public class MultiMap<K, V> {
    private final Map<K, List<V>> map;

    /**
     * Constructs an empty MultiMap.
     */
    public MultiMap() {
        this.map = new ConcurrentHashMap<>();
    }

    /**
     * Returns a list of values to which the specified key is mapped,
     * or an empty list if this map contains no mapping for the key.
     *
     * @param key the key whose associated values are to be returned
     * @return a list of values to which the specified key is mapped
     */
    public List<V> get(K key) {
        return map.getOrDefault(key, Collections.emptyList());
    }

    /**
     * Associates the specified value with the specified key in this map.
     * If the map previously contained a mapping for the key, the new value
     * is appended to the list of existing values.
     *
     * @param key key with which the specified value is to be associated
     * @param value value to be associated with the specified key
     */
    public void put(K key, V value) {
        map.computeIfAbsent(key, k -> new CopyOnWriteArrayList<>()).add(value);
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
