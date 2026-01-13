import java.util.*;

public class TreeMapDemo {
    public static void main(String[] args) {
        System.out.println("--- 1. TreeMap Constructors ---");

        // Constructor 1: Default (Natural Ordering)
        TreeMap<Integer, String> tm1 = new TreeMap<>();

        // Constructor 2: Custom Comparator (Reverse Order)
        TreeMap<Integer, String> tm2 = new TreeMap<>(Collections.reverseOrder());

        // Constructor 3: From Map
        Map<Integer, String> seed = new HashMap<>();
        seed.put(5, "Five");
        seed.put(1, "One");
        TreeMap<Integer, String> tm3 = new TreeMap<>(seed);

        // Constructor 4: From SortedMap
        SortedMap<Integer, String> sortedSeed = new TreeMap<>(seed);
        TreeMap<Integer, String> tm4 = new TreeMap<>(sortedSeed);

        System.out.println("TM3 (Sorted automatically): " + tm3);

        System.out.println("\n--- 2. TreeMap Methods (15) ---");

        // Filling tm1
        tm1.put(10, "Ten");
        tm1.put(20, "Twenty");
        tm1.put(30, "Thirty");
        tm1.put(40, "Forty");

        // 1. put(K key, V value)
        tm1.put(50, "Fifty");
        System.out.println("1. Tree Map content: " + tm1);

        // 2. firstKey()
        System.out.println("2. First Key: " + tm1.firstKey());

        // 3. lastKey()
        System.out.println("3. Last Key: " + tm1.lastKey());

        // 4. firstEntry()
        System.out.println("4. First Entry: " + tm1.firstEntry());

        // 5. lastEntry()
        System.out.println("5. Last Entry: " + tm1.lastEntry());

        // 6. ceilingKey(K key) - Least key >= given key
        System.out.println("6. Ceiling Key of 25: " + tm1.ceilingKey(25));

        // 7. floorKey(K key) - Greatest key <= given key
        System.out.println("7. Floor Key of 25: " + tm1.floorKey(25));

        // 8. headMap(K toKey) - View of portion < toKey
        System.out.println("8. HeadMap < 30: " + tm1.headMap(30));

        // 9. tailMap(K fromKey) - View of portion >= fromKey
        System.out.println("9. TailMap >= 30: " + tm1.tailMap(30));

        // 10. subMap(K from, K to) - Range [from, to)
        System.out.println("10. SubMap 20-40: " + tm1.subMap(20, 40));

        // 11. containsKey(Object key)
        System.out.println("11. Contains Key 10? " + tm1.containsKey(10));

        // 12. pollFirstEntry() - Retrieves and removes first
        System.out.println("12. Polled First: " + tm1.pollFirstEntry());

        // 13. pollLastEntry() - Retrieves and removes last
        System.out.println("13. Polled Last: " + tm1.pollLastEntry());

        // 14. descendingMap()
        System.out.println("14. Descending Map View: " + tm1.descendingMap());

        // 15. clear()
        tm1.clear();
        System.out.println("15. Cleared: " + tm1);
    }
}