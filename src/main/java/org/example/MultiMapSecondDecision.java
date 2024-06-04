package org.example;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;


    public class MultiMapSecondDecision<K, V> {

        /**
         * Internal map used to store key-value associations. This uses `AtomicReference` to ensure thread safety
         * for concurrent access to the map. The reference holds a `Map<K, List<V>>` which stores the actual key-value pairs.
         */
        private  final AtomicReference<Map<K, List<V>>> mapRef;

        /**
         * Constructs an empty MultiMap. Initializes the internal map using a new `HashMap`.
         */
        public MultiMapSecondDecision() {
            this.mapRef = new AtomicReference<>(new HashMap<>());
        }

        public MultiMapSecondDecision(AtomicReference<Map<K, List<V>>> mapRef) {
            this.mapRef = mapRef;
        }

        /**
         * Retrieves the list of values associated with a specific key.
         * If the key doesn't exist in the map, an empty list is returned.
         *
         * @param key the key to search for (of type `K`)
         * @return a list of values (of type `V`) associated with the key, or an empty list if the key doesn't exist
         */
        public List<V> get(K key) {
            /**
             * This method retrieves the list of values associated with a specific key.
             * If the key doesn't exist in the map, an empty list is returned to avoid null pointer exceptions.
             */
            return mapRef.get().getOrDefault(key, Collections.emptyList());
        }

        /**
         * Associates a new value with a specific key in the MultiMap.
         * If the key already exists, the new value is added to the existing list.
         * If the key doesn't exist, a new list is created with the value added to it.
         * This method uses a loop to ensure thread safety when updating the map.
         *
         * @param key the key to associate the value with (of type `K`)
         * @param value the value to be stored (of type `V`)
         */
        public void put(K key, V value) {
            /**
             * This method associates a new value with a specific key in the MultiMap.
             * If the key already exists, the new value is appended to the existing list of values.
             * If the key doesn't exist, a new list is created and the value is added to it.
             * This ensures that multiple values can be associated with a single key.
             */
            while (true) {
                Map<K, List<V>> currentMap = mapRef.get();
                List<V> values = currentMap.getOrDefault(key, new ArrayList<>());
                values.add(value);
                Map<K, List<V>> newMap = new HashMap<>(currentMap);
                newMap.put(key, values);
                if (mapRef.compareAndSet(currentMap, newMap)) {
                    return;
                }
            }
        }

        @Override
        public String toString() {
            return "MultiMap{" +
                    "map=" + mapRef.get() +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            MultiMapSecondDecision<?, ?> multiMap = (MultiMapSecondDecision<?, ?>) o;

            return mapRef.get().equals(multiMap.mapRef.get());
        }

        @Override
        public int hashCode() {
            return mapRef.get().hashCode();
        }
    }

