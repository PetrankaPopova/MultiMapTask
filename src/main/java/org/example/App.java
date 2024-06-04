package org.example;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        MultiMap multiMap = new MultiMap();
        multiMap.put("Pepi", 6);
        System.out.println(multiMap.get("Pepi"));


    }
}
