import java.util.*;

public class HashMapDemo {
    public static void main(String[] args) {
        System.out.println(" 1. HashMap Constructors ");

        // Constructor 1: Default (Capacity 16, Load Factor 0.75)
        HashMap<Integer, String> map1 = new HashMap<>();

        // Constructor 2: Initial Capacity
        HashMap<Integer, String> map2 = new HashMap<>(20);

        // Constructor 3: Initial Capacity and Load Factor
        HashMap<Integer, String> map3 = new HashMap<>(20, 0.8f);

        // Constructor 4: From another Map
        Map<Integer, String> seed = Collections.singletonMap(1, "Start");
        HashMap<Integer, String> map4 = new HashMap<>(seed);

        System.out.println("Map4 created from seed: " + map4);

        System.out.println("\n--- 2. HashMap Methods (15) ---");

        // 1. put(K key, V value)
        map1.put(101, "John");
        map1.put(102, "Jane");
        map1.put(103, "Doe");
        System.out.println("1. Put entries: " + map1);

        // 2. get(Object key)
        System.out.println("2. Get key 102: " + map1.get(102));

        // 3. containsKey(Object key)
        System.out.println("3. Contains Key 101? " + map1.containsKey(101));

        // 4. containsValue(Object value)
        System.out.println("4. Contains Value 'Doe'? " + map1.containsValue("Doe"));

        // 5. putIfAbsent(K key, V value)
        map1.putIfAbsent(104, "Smith");
        map1.putIfAbsent(101, "Overwritten?"); // Won't happen
        System.out.println("5. PutIfAbsent result: " + map1);

        // 6. size()
        System.out.println("6. Size: " + map1.size());

        // 7. remove(Object key)
        map1.remove(103);
        System.out.println("7. Removed 103: " + map1);

        // 8. remove(Object key, Object value) - Conditional remove
        map1.remove(104, "WrongValue"); // Fail
        map1.remove(104, "Smith");      // Success
        System.out.println("8. Conditional remove 104: " + map1);

        // 9. replace(K key, V oldValue, V newValue)
        map1.replace(101, "John", "Johnny");
        System.out.println("9. Replaced John with Johnny: " + map1);

        // 10. keySet()
        System.out.println("10. KeySet: " + map1.keySet());

        // 11. values()
        System.out.println("11. Values: " + map1.values());

        // 12. entrySet()
        System.out.println("12. EntrySet: " + map1.entrySet());

        // 13. putAll(Map m)
        map1.putAll(map4);
        System.out.println("13. PutAll from map4: " + map1);

        // 14. getOrDefault(Object key, V defaultValue)
        System.out.println("14. GetOrDefault (999): " + map1.getOrDefault(999, "Not Found"));

        // 15. clear()
        map1.clear();
        System.out.println("15. Cleared map: " + map1);
    }
}