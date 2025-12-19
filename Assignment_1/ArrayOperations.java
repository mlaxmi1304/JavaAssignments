
import java.util.Scanner;

public class ArrayOperations {
    
    static final int MAX_SIZE = 50;
    static int[] arr = new int[MAX_SIZE];
    static int n = 0; 
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n MENU: One Dimensional Array ");
            System.out.println("1. Insert Element");
            System.out.println("2. Delete Element");
            System.out.println("3. Linear Search");
            System.out.println("4. Binary Search (Requires Sorted Array)");
            System.out.println("5. Find Maximum Value");
            System.out.println("6. Count Even/Odd Numbers");
            System.out.println("7. Perform Insertion Sort");
            System.out.println("8. Display Array");
            System.out.println("9. Exit");
            System.out.print("Choose an operation: ");

            int choice = sc.nextInt();

            switch (choice) {
                case 1: insert(); break;
                case 2: delete(); break;
                case 3: linearSearch(); break;
                case 4: binarySearch(); break;
                case 5: findMax(); break;
                case 6: countEvenOdd(); break;
                case 7: insertionSort(); break;
                case 8: display(); break;
                case 9: 
                    System.out.println("Exiting...");
                    System.exit(0);
                default: 
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }

    // 1 Insertion 
    public static void insert() {
        if (n >= MAX_SIZE) {
            System.out.println("Array is full.");
            return;
        }
        System.out.print("Enter number to insert: ");
        int val = sc.nextInt();
        System.out.print("Enter position (0 to " + n + "): ");
        int pos = sc.nextInt();

        if (pos < 0 || pos > n) {
            System.out.println("Invalid position.");
            return;
        }

        
        for (int i = n; i > pos; i--) {
            arr[i] = arr[i - 1];
        }
        arr[pos] = val;
        n++;
        
    }

    //  2 Deletion 
    public static void delete() {
        if (n == 0) {
            System.out.println("Array is empty.");
            return;
        }
        System.out.print("Enter value to delete: ");
        int val = sc.nextInt();
        int pos = -1;

        
        for (int i = 0; i < n; i++) {
            if (arr[i] == val) {
                pos = i;
                break;
            }
        }

        if (pos == -1) {
            System.out.println("Value not found.");
            return;
        }

       
        for (int i = pos; i < n - 1; i++) {
            arr[i] = arr[i + 1];
        }
        n--;
        System.out.println("Deleted " + val);
    }

    //  3 Linear Search 
    public static void linearSearch() {
        System.out.print("Enter value to search: ");
        int key = sc.nextInt();
        boolean found = false;
        
        for (int i = 0; i < n; i++) {
            if (arr[i] == key) {
                System.out.println("Found at index: " + i);
                found = true;
                break;
            }
        }
        if (!found) System.out.println("Not found.");
    }

    // 4 Binary Search 
    public static void binarySearch() {
        
        System.out.print("Enter value to search: ");
        int key = sc.nextInt();
        
        int left = 0, right = n - 1;
        boolean found = false;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (arr[mid] == key) {
                System.out.println("Found at index: " + mid);
                found = true;
                break;
            }
            if (arr[mid] < key) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        if (!found) System.out.println("Not found.");
        
    }

    // 5. Find Maximum 
    public static void findMax() {
        if (n == 0) {
            System.out.println("Array is empty.");
            return;
        }
        int max = arr[0];
        for (int i = 1; i < n; i++) {
            if (arr[i] > max) max = arr[i];
        }
        System.out.println("Maximum value: " + max);
    }

    //  6. Count Even/Odd numbers
    public static void countEvenOdd() {
        int even = 0, odd = 0;
        for (int i = 0; i < n; i++) {
            if (arr[i] % 2 == 0) even++;
            else odd++;
        }
        System.out.println("Even numbers: " + even);
        System.out.println("Odd numbers: " + odd);
    }

    //  7. Insertion Sort 
    public static void insertionSort() {
        if (n < 2) {
            System.out.println("Not enough elements to sort.");
            return;
        }
        for (int i = 1; i < n; i++) {
            int key = arr[i];
            int j = i - 1;

            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j = j - 1;
            }
            arr[j + 1] = key;
        }
        System.out.println("Array sorted successfully.");
    }

    //  8. Display 
    public static void display() {
        if (n == 0) {
            System.out.println("Array is empty.");
            return;
        }
        System.out.print("Current Array: ");
        for (int i = 0; i < n; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
}