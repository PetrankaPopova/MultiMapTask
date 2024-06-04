package org.example;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class MultiMapTest {

    @Test
    public void testPutAndGet() {
        MultiMap<String, Integer> multiMap = new MultiMap<>();
        multiMap.put("name", 6);
        multiMap.put("name", 7);

        List<Integer> values = multiMap.get("name");
        assertEquals(2, values.size());
        assertEquals(List.of(6, 7), values);
    }

    @Test
    public void testGetNonExistentKey() {
        MultiMap<String, Integer> multiMap = new MultiMap<>();
        assertTrue(multiMap.get("nonExistingKey").isEmpty());
    }

    @Test
    public void testMultipleKeys() {
        MultiMap<String, Integer> multiMap = new MultiMap<>();
        multiMap.put("person1", 1);
        multiMap.put("person2", 2);
        multiMap.put("person1", 3);

        List<Integer> person1Values = multiMap.get("person1");
        List<Integer> person2Values = multiMap.get("person2");

        assertEquals(2, person1Values.size());
        assertEquals(List.of(1, 3), person1Values);
        assertEquals(1, person2Values.size());
        assertEquals(List.of(2), person2Values);
    }

    @Test
    public void testPutNullKey_throwsException() {
        try {
            MultiMap<String, Integer> multiMap = new MultiMap<>();
            multiMap.put(null, 5);
            fail("Expected NullPointerException for null key");
        } catch (NullPointerException expected) {
        }
    }

    @Test
    public void testPutNullValue() {
        MultiMap<String, Integer> multiMap = new MultiMap<>();
        multiMap.put("key", null);

        List<Integer> values = multiMap.get("key");
        assertEquals(1, values.size());
        assertTrue(values.contains(null));
    }

    @Test
    public void testPutMultipleNullValues() {
        MultiMap<String, Integer> multiMap = new MultiMap<>();
        multiMap.put("key", null);
        multiMap.put("key", null);

        List<Integer> values = multiMap.get("key");
        assertEquals(2, values.size());
        assertNull(values.get(0));
        assertNull(values.get(1));
    }

    @Test
    public void testEmptyMultiMap() {
        MultiMap<String, Integer> multiMap = new MultiMap<>();
        assertTrue(multiMap.get("anyKey").isEmpty());
    }

    @Test
    public void testToString() {
        MultiMap<String, Integer> multiMap = new MultiMap<>();
        multiMap.put("Pepi", 6);
        multiMap.put("Pepi", 7);
        multiMap.put("Alice", 1);

        String expected = "MultiMap{map={Alice=[1], Pepi=[6, 7]}}";
        assertEquals(expected, multiMap.toString());
    }

    @Test
    public void testEqualsAndHashCode() {
        MultiMap<String, Integer> multiMap1 = new MultiMap<>();
        MultiMap<String, Integer> multiMap2 = new MultiMap<>();

        multiMap1.put("Pepi", 6);
        multiMap1.put("Pepi", 7);

        multiMap2.put("Pepi", 6);
        multiMap2.put("Pepi", 7);

        assertEquals(multiMap1, multiMap2);
        assertEquals(multiMap1.hashCode(), multiMap2.hashCode());

        multiMap2.put("Alice", 1);
        assertNotEquals(multiMap1, multiMap2);
        assertNotEquals(multiMap1.hashCode(), multiMap2.hashCode());
    }
}
