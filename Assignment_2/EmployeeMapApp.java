package Assignment_2;

import java.util.*;
import java.util.regex.*;


class Employee {
    private String id;
    private String name;

    public Employee(String id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "[ID: " + id + ", Name: " + name + "]";
    }
}


interface EmployeeOperations {
    void addEmployee(String id, String name);
    void removeEmployee(String id);
    void searchEmployee(String id);
    void displayEmployees();
}

class EmployeeManager implements EmployeeOperations {
    
    
    private Map<String, Employee> employeeMap;

    // Regex Constants
    private static final String ID_REGEX = "^[0-9]{3,}$"; // Numeric, at least 3 digits
    private static final String NAME_REGEX = "^[a-zA-Z\\s]+$"; // Letters only

    public EmployeeManager() {
        
        this.employeeMap = new HashMap<>();
    }

    @Override
    public void addEmployee(String id, String name) {
        try {
            // Regex Validation
            if (!Pattern.matches(ID_REGEX, id)) throw new IllegalArgumentException("ID must be numeric and at least 3 digits.");
            if (!Pattern.matches(NAME_REGEX, name)) throw new IllegalArgumentException("Name must contain letters only.");

            if (employeeMap.containsKey(id)) {
                System.out.println("Error: Employee ID already exists.");
            } else {
                employeeMap.put(id, new Employee(id, name));
                System.out.println("Success: Employee added.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Validation Error: " + e.getMessage());
        }
    }

    @Override
    public void removeEmployee(String id) {
        if (employeeMap.containsKey(id)) {
            employeeMap.remove(id);
            System.out.println("Success: Employee removed.");
        } else {
            System.out.println("Error: Employee ID not found.");
        }
    }

    @Override
    public void searchEmployee(String id) {
        if (employeeMap.containsKey(id)) {

            System.out.println("Found: " + employeeMap.get(id));
        } else {
            System.out.println("Error: Employee not found.");
        }
    }

    @Override
    public void displayEmployees() {
        if (employeeMap.isEmpty()) {
            System.out.println("No records found.");
            return;
        }
        System.out.println("\n--- All Employees ---");
        
        for (Map.Entry<String, Employee> entry : employeeMap.entrySet()) {
            System.out.println(entry.getValue());
        }
    }

    
    public void demonstrateNullSupport() {
        System.out.println("\n--- NULL SUPPORT DEMONSTRATION ---");
        
        //1. Hashmap
        System.out.print("1. HashMap: ");
        try {
            HashMap<String, String> hm = new HashMap<>();
            hm.put(null, "Null Value"); 
            hm.put("Key", null);        
            System.out.println("PASS (Allows null keys and values)");
        } catch (Exception e) {
            System.out.println("FAIL: " + e);
        }

        // 2. Hashtable 
        System.out.print("2. Hashtable: ");
        try {
            Hashtable<String, String> ht = new Hashtable<>();
            ht.put(null, "Value"); 
        } catch (NullPointerException e) {
            System.out.println("Caught Expected Error: Hashtable does NOT allow null keys.");
        }

        // 3. TreeMap:
        System.out.print("3. TreeMap: ");
        try {
            TreeMap<String, String> tm = new TreeMap<>();
            tm.put(null, "Value"); // throw NullPointerException 
        } catch (NullPointerException e) {
            System.out.println("Caught Expected Error: TreeMap does NOT allow null keys.");
        }
    }
}

// Main Menu
public class EmployeeMapApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        EmployeeManager manager = new EmployeeManager();
        boolean running = true;

        System.out.println("--- Employee Management (Map Based) ---");

        while (running) {
            try {
                System.out.println("\n1. Add Employee");
                System.out.println("2. Display All");
                System.out.println("3. Remove Employee");
                System.out.println("4. Search Employee");
                System.out.println("5. Demonstrate Null Support (HashMap vs Hashtable vs TreeMap)");
                System.out.println("6. Exit");
                System.out.print("Enter Choice: ");

                int choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1:
                        System.out.print("Enter ID (3+ digits): ");
                        String id = scanner.nextLine();
                        System.out.print("Enter Name: ");
                        String name = scanner.nextLine();
                        manager.addEmployee(id, name);
                        break;
                    case 2:
                        manager.displayEmployees();
                        break;
                    case 3:
                        System.out.print("Enter ID to remove: ");
                        String remId = scanner.nextLine();
                        manager.removeEmployee(remId);
                        break;
                    case 4:
                        System.out.print("Enter ID to search: ");
                        String searchId = scanner.nextLine();
                        manager.searchEmployee(searchId);
                        break;
                    case 5:
                        manager.demonstrateNullSupport();
                        break;
                    case 6:
                        running = false;
                        System.out.println("Exit");

                        break;
                    default:
                        System.out.println("This is an Invalid choice");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Please enter a numeric choice.");
            }
        }
        scanner.close();
    }
} 

