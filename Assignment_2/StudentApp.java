package Assignment_2;
import java.util.*;
import java.util.regex.*;


class Student {
    private String name;
    private String rollNo;

    public Student(String name, String rollNo) {
        this.name = name;
        this.rollNo = rollNo;
    }

    public String getRollNo() { return rollNo; }

    @Override
    public String toString() {
        return "Roll No: " + rollNo + " | Name: " + name;
    }
}


interface StudentOperations {
    void addStudent(String name, String rollNo);
    void displayStudents();
    void removeStudent(String rollNo);
    void searchStudent(String rollNo);
}


class StudentManager implements StudentOperations {
    
    
    private List<Student> students;

    // Regex Constants
    private static final String NAME_REGEX = "^[a-zA-Z\\s]+$"; // Letters and spaces only
    private static final String ROLL_REGEX = "^[0-9]+$";        // Numbers only

    public StudentManager() {
        // Initializing with ArrayList 
        this.students = new ArrayList<>(); 
    }

    @Override
    public void addStudent(String name, String rollNo) {
        try {
            // Validation using Regex
            if (!Pattern.matches(NAME_REGEX, name)) {
                throw new IllegalArgumentException("Invalid Name! Use letters only.");
            }
            if (!Pattern.matches(ROLL_REGEX, rollNo)) {
                throw new IllegalArgumentException("Invalid Roll No! Use digits only.");
            }

            // Checking for duplicates
            for(Student s : students) {
                if(s.getRollNo().equals(rollNo)) {
                    System.out.println("Error: Student with this Roll No already exists.");
                    return;
                }
            }

            students.add(new Student(name, rollNo));
            System.out.println("Success: Student added.");

        } catch (IllegalArgumentException e) {
            System.out.println("Validation Error: " + e.getMessage());
        }
    }

    @Override
    public void displayStudents() {
        if (students.isEmpty()) {
            System.out.println("No records found.");
        } else {
            System.out.println("\n--- Student List ---");
            for (Student s : students) {
                System.out.println(s);
            }
        }
    }


    @Override
    public void removeStudent(String rollNo) {
        Iterator<Student> iterator = students.iterator();
        boolean found = false;
        while (iterator.hasNext()) {
            Student s = iterator.next();
            if (s.getRollNo().equals(rollNo)) {
                iterator.remove();
                System.out.println("Success: Student removed.");
                found = true;

                break;
            }
        }
        if (!found) 
            System.out.println("Error: Student with Roll No " + rollNo + " not found.");
    }

    @Override
    public void searchStudent(String rollNo) {
        boolean found = false;
        for (Student s : students) {
            if (s.getRollNo().equals(rollNo)) {
                System.out.println("Student Found: " + s);
                found = true;
                break;
            }
        }
        if (!found) System.out.println("Error: Student not found.");
    }
}


public class StudentApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StudentOperations manager = new StudentManager(); // Polymorphism
        boolean running = true;

        System.out.println(" Student Record Management ");

        while (running) {
            try {
                System.out.println("\n1. Add Student");
                System.out.println("2. Display All Students");
                System.out.println("3. Remove Student");
                System.out.println("4. Search Student");
                System.out.println("5. Exit");
                System.out.print("Enter Choice: ");

                int choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1:
                        System.out.print("Enter Name: ");
                        String name = scanner.nextLine();
                        System.out.print("Enter Roll No: ");
                        String rollNo = scanner.nextLine();
                        
                        manager.addStudent(name, rollNo);
                        break;
                    case 2:
                        manager.displayStudents();
                        break;
                    case 3:
                        System.out.print("Enter Roll No to remove: ");
                        String removeRoll = scanner.nextLine();
                        manager.removeStudent(removeRoll);
                        break;
                    case 4:
                        System.out.print("Enter Roll No to search: ");
                        String searchRoll = scanner.nextLine();
                        manager.searchStudent(searchRoll);
                        break;
                    case 5:
                        System.out.println("Exiting...");
                        running = false;
                        break;
                    default:
                        System.out.println("Invalid choice. Please enter 1-5.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Input Error: Please enter a number for the menu.");
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
            }
        }
        scanner.close();
    }
}