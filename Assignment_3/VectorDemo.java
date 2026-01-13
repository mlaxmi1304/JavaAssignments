
import java.util.*;

public class VectorDemo {
    public static void main(String[] args) {
        System.out.println("--- 1. Vector Constructors ---");

        // Constructor 1: Default
        Vector<Integer> v1 = new Vector<>();
        
        // Constructor 2: Initial capacity
        Vector<Integer> v2 = new Vector<>(10);
        
        // Constructor 3: Initial capacity and capacity increment
        Vector<Integer> v3 = new Vector<>(5, 2);
        
        // Constructor 4: Collection
        Vector<Integer> v4 = new Vector<>(Arrays.asList(100, 200));

        System.out.println("Vector v3 created with capacity 5, increment 2.");

        System.out.println("\n--- 2. Vector Methods (15) ---");

        // 1. addElement(E obj) - Legacy method specific to Vector
        v3.addElement(1);
        v3.addElement(2);
        v3.addElement(3);
        System.out.println("1. Added elements (Legacy): " + v3);

        // 2. add(E e) - Standard List method
        v3.add(4);
        System.out.println("2. Added element (Standard): " + v3);

        // 3. capacity() - Specific to Vector
        System.out.println("3. Current Capacity: " + v3.capacity());

        // 4. firstElement()
        System.out.println("4. First Element: " + v3.firstElement());

        // 5. lastElement()
        System.out.println("5. Last Element: " + v3.lastElement());

        // 6. elementAt(int index)
        System.out.println("6. Element at index 1: " + v3.elementAt(1));

        // 7. insertElementAt(E obj, int index)
        v3.insertElementAt(99, 1);
        System.out.println("7. Inserted 99 at index 1: " + v3);

        // 8. isEmpty()
        System.out.println("8. Is empty? " + v3.isEmpty());

        // 9. contains(Object o)
        System.out.println("9. Contains 99? " + v3.contains(99));

        
        // 10. removeElement(Object obj)
        v3.removeElement(99);
        System.out.println("10. Removed 99: " + v3);

        // 11. removeAllElements()
        v3.removeAllElements();
        System.out.println("11. Removed all elements: " + v3);

        // 12. setSize(int newSize)
        v3.setSize(2);
        System.out.println("12. Set size to 2 (padded with nulls): " + v3);

        // 13. copyInto(Object[] anArray)
        // Refill for demo
        v3.clear(); v3.add(10); v3.add(20);
        Integer[] arr = new Integer[2];
        v3.copyInto(arr);
        System.out.println("13. Copied into array: " + Arrays.toString(arr));

        // 14. elements() - Returns an Enumeration (Legacy)
        Enumeration<Integer> e = v3.elements();
        System.out.print("14. Enumeration: ");
        while(e.hasMoreElements()) System.out.print(e.nextElement() + " ");
        System.out.println();

        // 15. clone()
        Object vClone = v3.clone();
        System.out.println("15. Cloned vector: " + vClone);
    }
} 
