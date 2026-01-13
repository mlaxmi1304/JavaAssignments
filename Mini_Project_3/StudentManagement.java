package Mini_Project_3;


import java.util.*;

public class StudentManagement {


    static ArrayList<Student> students = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println(" *********** STUDENT MANAGEMENT SYSTEM LOGIN **************");
        
        // --- Login System ---
        System.out.print("Enter Username: ");
        String user = scanner.next();
        System.out.print("Enter Password: ");
        String pass = scanner.next();

        // Credentials
        if (!user.equals("admin") || !pass.equals("1234")) {
            System.out.println("Access Denied: Invalid Credentials");
            return; // Exit program
        }

        System.out.println("Login Successful!\n");

        // --- Main Menu Loop ---
        while (true) {
            System.out.println("\n--- MAIN MENU ---");
            System.out.println("1. Add Student");
            System.out.println("2. Display All Students");
            System.out.println("3. Search Student by Eno");
            System.out.println("4. Update Student Branch");
            System.out.println("5. Delete Student by Eno");
            System.out.println("6. Display Sorted Students");
            System.out.println("7. Exit");
            System.out.print("Enter choice: ");

            int choice = 0;
            try {
                choice = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Error: Please enter a number.");
                scanner.next(); // Clear buffer
                continue;
            }

            switch (choice) {
                case 1: addStudent(); break;
                case 2: displayAll(); break;
                case 3: searchStudent(); break;
                case 4: updateBranch(); break;
                case 5: deleteStudent(); break;
                case 6: displaySorted(); break;
                case 7: 
                    System.out.println("Exiting... Goodbye!"); 
                    return;
                default: System.out.println("Invalid choice. Try again.");
            }
        }
    }

    // --- 1. Add Student with Validation ---
    static void addStudent() {
        try {
            System.out.print("Enter Eno: ");
            int eno = scanner.nextInt();

            // Validation: Unique Eno
            for (Student s : students) {
                if (s.getEno() == eno) {
                    throw new Exception("Duplicate Eno! Student already exists.");
                }
            }

            System.out.print("Enter Name: ");
            String name = scanner.next();

            System.out.print("Enter Branch: ");
            String branch = scanner.next();
            // Validation: Empty Branch
            if (branch.trim().isEmpty()) throw new Exception("Branch cannot be empty.");

            System.out.print("Enter Percentage: ");
            double pct = scanner.nextDouble();
            // Validation: Positive Percentage
            if (pct < 0 || pct > 100) throw new Exception("Percentage must be between 0 and 100.");

            System.out.print("Enter Semester: ");
            int sem = scanner.nextInt();
            // Validation: Semester
            if (sem <= 0) throw new Exception("Semester must be positive.");

            students.add(new Student(eno, name, branch, pct, sem));
            System.out.println("Student added successfully!");

        } catch (InputMismatchException e) {
            System.out.println("Error: Invalid input format.");
            scanner.next(); // clear buffer
        } catch (Exception e) {
            System.out.println("Validation Error: " + e.getMessage());
        }
    }

    // --- 2. Display All ---
    static void displayAll() {
        if (students.isEmpty()) {
            System.out.println("No students found.");
        } else {
            for (Student s : students) System.out.println(s);
        }
    }

    // --- 3. Search by Eno ---
    static void searchStudent() {
        System.out.print("Enter Eno to search: ");
        int eno = scanner.nextInt();
        boolean found = false;
        for (Student s : students) {
            if (s.getEno() == eno) {
                System.out.println("Found: " + s);
                found = true;
                break;
            }
        }
        if (!found) System.out.println("Student not found.");
    }

    // --- 4. Update Branch ---
    static void updateBranch() {
        System.out.print("Enter Eno to update: ");
        int eno = scanner.nextInt();
        boolean found = false;
        
        for (Student s : students) {
            if (s.getEno() == eno) {
                System.out.print("Enter new Branch: ");
                String newBranch = scanner.next();
                if (newBranch.trim().isEmpty()) {
                    System.out.println("Branch cannot be empty.");
                } else {
                    s.setBranch(newBranch);
                    System.out.println("Branch updated successfully.");
                }
                found = true;
                break;
            }
        }
        if (!found) System.out.println("Student not found.");
    }

    // --- 5. Delete by Eno ---
    static void deleteStudent() {
        System.out.print("Enter Eno to delete: ");
        int eno = scanner.nextInt();
        boolean removed = students.removeIf(s -> s.getEno() == eno);
        
        if (removed) System.out.println("Student deleted.");
        else System.out.println("Student not found.");
    }

    // --- 6. Display Sorted ---
    static void displaySorted() {
        if (students.isEmpty()) {
            System.out.println("No students to sort.");
            return;
        }
        // Uses the compareTo method in Student class
        Collections.sort(students);
        System.out.println("--- Students Sorted by Eno ---");
        displayAll();
    }
}