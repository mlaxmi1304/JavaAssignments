// package Assignment_3;
import java.util.*;

public class ArrayListDemo {
    public static void main(String[] args) {
        System.out.println("--- 1. ArrayList Constructors ---");

        // Constructor 1: Default constructor
        ArrayList<String> list1 = new ArrayList<>();
        
        // Constructor 2: Initial capacity
        ArrayList<String> list2 = new ArrayList<>(20);
        
        // Constructor 3: Collection constructor
        List<String> seedData = Arrays.asList("Alpha", "Beta");
        ArrayList<String> list3 = new ArrayList<>(seedData);

        System.out.println("List3 created from seed data: " + list3);

        System.out.println("\n--- 2. ArrayList Methods (15) ---");
        
        // 1. add(E e)
        list1.add("Apple");
        list1.add("Banana");
        list1.add("Cherry");
        System.out.println("1. Added elements: " + list1);

        // 2. add(int index, E element)
        list1.add(1, "Blueberry");
        System.out.println("2. Inserted at index 1: " + list1);

        // 3. addAll(Collection c)
        list1.addAll(list3);
        System.out.println("3. Added all from list3: " + list1);

        // 4. size()
        System.out.println("4. Size of list: " + list1.size());

        // 5. contains(Object o)
        System.out.println("5. Contains 'Apple'? " + list1.contains("Apple"));

        // 6. get(int index)
        System.out.println("6. Element at index 2: " + list1.get(2));

        // 7. set(int index, E element)
        list1.set(2, "Citrus");
        System.out.println("7. Set index 2 to Citrus: " + list1);

        // 8. indexOf(Object o)
        System.out.println("8. Index of 'Beta': " + list1.indexOf("Beta"));

        // 9. remove(int index)
        list1.remove(0);
        System.out.println("9. Removed element at index 0: " + list1);

        // 10. remove(Object o)
        list1.remove("Beta");
        System.out.println("10. Removed object 'Beta': " + list1);

        // 11. subList(int fromIndex, int toIndex)
        List<String> sub = list1.subList(0, 2);
        System.out.println("11. SubList (0 to 2): " + sub);

        // 12. isEmpty()
        System.out.println("12. Is list empty? " + list1.isEmpty());

        // 13. toArray()
        Object[] arr = list1.toArray();
        System.out.println("13. Converted to Array, length: " + arr.length);

        // 14. trimToSize() - Trims capacity to current size
        list1.trimToSize();
        System.out.println("14. Trimmed to size (internal capacity optimization).");

        // 15. clear()
        list1.clear();
        System.out.println("15. Cleared list: " + list1);
    }
}