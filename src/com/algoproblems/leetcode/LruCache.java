package src;

import java.util.*;

public class LruCache {

    private static int capacity = 3;
    private static Map<String, String> lruCache = new HashMap<>(capacity);
    private static List<String> usedKey = new LinkedList<>();

    final public static String add(String key, String value) {
        Objects.requireNonNull(key);
        if (lruCache.size() + 1 > capacity) {
            removeLeastUsed();
        }
        usedKey.add(key);
        return lruCache.put(key, value);
    }

    public static String get(String key) {
        Objects.requireNonNull(key);
        usedKey.remove(key);
        usedKey.add(key);
        return lruCache.get(key);
    }

    private static String removeLeastUsed() {
        return lruCache.remove(usedKey.get(0));
    }

    public static void main(String[] args) {
        LruCache.add("1", "O");
        LruCache.add("2", "A");
        LruCache.add("3", "E");
        LruCache.get("1");
        LruCache.get("2");
        LruCache.add("4", "D");
        lruCache.forEach((k, v) -> System.out.println(k + ", " + v));
    }

}
