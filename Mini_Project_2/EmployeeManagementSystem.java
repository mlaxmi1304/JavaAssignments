package Mini_Project_2;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

// 1. Custom Exception for Validation
class ValidationException extends Exception {
    public ValidationException(String message) {
        super(message);
    }
}


// 2. Employee Model
class Employee implements Serializable, Comparable<Employee> {
    private int id;
    private String name;
    private double salary;
    private String department;

    public Employee(int id, String name, double salary, String department) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.department = department;
    }

    // Getters and Setters
    public int getId() { return id; }
    public String getName() { return name; }
    public double getSalary() { return salary; }
    public void setSalary(double salary) { this.salary = salary; }
    public String getDepartment() { return department; }

    @Override
    public String toString() {
        return String.format("ID: %d | Name: %-15s | Salary: $%-10.2f | Dept: %s", 
                             id, name, salary, department);
    }

    // For file saving (CSV format)
    public String toCSV() {
        return id + "," + name + "," + salary + "," + department;
    }

    // Sorting logic (Sort by ID)
    @Override
    public int compareTo(Employee other) {
        return Integer.compare(this.id, other.id);
    }
}

// 3. System Logic (CRUD + File Handling)
class EmployeeManager {
    private static final String FILE_NAME = "employees.txt";
    private Map<Integer, Employee> employeeMap;

    public EmployeeManager() {
        employeeMap = new HashMap<>();
        loadDataFromFile();
    }

    // FILE HANDLING: Load data
    private void loadDataFromFile() {
        File file = new File(FILE_NAME);
        if (!file.exists()) return;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    int id = Integer.parseInt(parts[0]);
                    String name = parts[1];
                    double salary = Double.parseDouble(parts[2]);
                    String dept = parts[3];
                    employeeMap.put(id, new Employee(id, name, salary, dept));
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading data: " + e.getMessage());
        }
    }

    // FILE HANDLING: Save data (Called after every change)
    private void saveDataToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Employee emp : employeeMap.values()) {
                writer.write(emp.toCSV());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }

    // 1. Add Employee
    public void addEmployee(int id, String name, double salary, String dept) throws ValidationException {
        if (employeeMap.containsKey(id)) {
            throw new ValidationException("Employee ID " + id + " already exists!");
        }
        if (salary <= 0) {
            throw new ValidationException("Salary must be positive!");
        }
        if (dept == null || dept.trim().isEmpty()) {
            throw new ValidationException("Department cannot be empty!");
        }

        Employee newEmp = new Employee(id, name, salary, dept);
        employeeMap.put(id, newEmp);
        saveDataToFile();
        System.out.println(">> Employee added successfully.");
    }

    // 2. Display All
    public void displayAll() {
        if (employeeMap.isEmpty()) {
            System.out.println("No records found.");
            return;
        }
        System.out.println("\n--- All Employees ---");
        for (Employee emp : employeeMap.values()) {
            System.out.println(emp);
        }
    }

    // 3. Search by ID
    public void searchEmployee(int id) throws ValidationException {
        if (!employeeMap.containsKey(id)) {
            throw new ValidationException("Employee with ID " + id + " not found.");
        }
        System.out.println(">> Found: " + employeeMap.get(id));
    }

    // 4. Update Salary
    public void updateSalary(int id, double newSalary) throws ValidationException {
        if (!employeeMap.containsKey(id)) {
            throw new ValidationException("Employee with ID " + id + " not found.");
        }
        if (newSalary <= 0) {
            throw new ValidationException("Salary must be positive!");
        }
        
        Employee emp = employeeMap.get(id);
        emp.setSalary(newSalary);
        saveDataToFile();
        System.out.println(">> Salary updated successfully.");
    }

    // 5. Delete Employee
    public void deleteEmployee(int id) throws ValidationException {
        if (!employeeMap.containsKey(id)) {
            throw new ValidationException("Employee with ID " + id + " not found.");
        }
        employeeMap.remove(id);
        saveDataToFile();
        System.out.println(">> Employee deleted successfully.");
    }

    // 6. Display Sorted (by ID)
    public void displaySorted() {
        if (employeeMap.isEmpty()) {
            System.out.println("No records found.");
            return;
        }
        System.out.println("\n--- Sorted Employees (by ID) ---");
        List<Employee> sortedList = new ArrayList<>(employeeMap.values());
        Collections.sort(sortedList); // Uses Comparable
        for (Employee emp : sortedList) {
            System.out.println(emp);
        }
    }

    // 7. Display Departments
    public void displayDepartments() {
        System.out.println("\n--- Departments ---");
        Set<String> departments = employeeMap.values().stream()
                .map(Employee::getDepartment)
                .collect(Collectors.toSet());
        
        if(departments.isEmpty()) System.out.println("No departments found.");
        else departments.forEach(System.out::println);
    }
}

// 4. Main Class with Login and Menu
public class EmployeeManagementSystem {
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        // --- Login System ---
        System.out.println("=== LOGIN ===");
        System.out.print("Username: ");
        String user = sc.nextLine();
        System.out.print("Password: ");
        String pass = sc.nextLine();

        // Simple hardcoded credentials
        if (!user.equals("admin") || !pass.equals("admin123")) {
            System.out.println("Invalid Credentials! Exiting...");
            return;
        }

        System.out.println("Login Successful!\n");

        EmployeeManager manager = new EmployeeManager();
        boolean running = true;

        while (running) {
            System.out.println("\n=== Employee Management System ===");
            System.out.println("1. Add Employee");
            System.out.println("2. Display All Employees");
            System.out.println("3. Search Employee by ID");
            System.out.println("4. Update Employee Salary");
            System.out.println("5. Delete Employee");
            System.out.println("6. Display Sorted Employees");
            System.out.println("7. Display Departments");
            System.out.println("8. Exit");
            System.out.print("Enter Choice: ");

            int choice;
            try {
                choice = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a number.");
                continue;
            }

            try {
                switch (choice) {
                    case 1:
                        System.out.print("Enter ID: ");
                        int id = Integer.parseInt(sc.nextLine());
                        System.out.print("Enter Name: ");
                        String name = sc.nextLine();
                        System.out.print("Enter Salary: ");
                        double salary = Double.parseDouble(sc.nextLine());
                        System.out.print("Enter Department: ");
                        String dept = sc.nextLine();
                        manager.addEmployee(id, name, salary, dept);
                        break;
                    case 2:
                        manager.displayAll();
                        break;
                    case 3:
                        System.out.print("Enter ID to Search: ");
                        int searchId = Integer.parseInt(sc.nextLine());
                        manager.searchEmployee(searchId);
                        break;
                    case 4:
                        System.out.print("Enter ID to Update: ");
                        int updateId = Integer.parseInt(sc.nextLine());
                        System.out.print("Enter New Salary: ");
                        double newSalary = Double.parseDouble(sc.nextLine());
                        manager.updateSalary(updateId, newSalary);
                        break;
                    case 5:
                        System.out.print("Enter ID to Delete: ");
                        int deleteId = Integer.parseInt(sc.nextLine());
                        manager.deleteEmployee(deleteId);
                        break;
                    case 6:
                        manager.displaySorted();
                        break;
                    case 7:
                        manager.displayDepartments();
                        break;
                    case 8:
                        running = false;
                        System.out.println("Exiting System. Goodbye!");
                        break;
                    default:
                        System.out.println("Invalid Choice. Try again.");
                }
            } catch (ValidationException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (NumberFormatException e) {
                System.out.println("Error: Please enter valid numbers for ID/Salary.");
            } catch (Exception e) {
                System.out.println("Unexpected Error: " + e.getMessage());
            }
        }
        sc.close();
    }
}
