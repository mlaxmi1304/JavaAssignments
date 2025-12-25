package Assignment_2;

import java.util.*;
import java.util.regex.*;

// Student class
class Student {
    String id;
    String name;
    String course;
    int marks;

    public Student(String id, String name, String course, int marks) {
        this.id = id;
        this.name = name;
        this.course = course;
        this.marks = marks;
    }

    @Override
    public String toString() {
        return "ID: " + id + " | Name: " + name + " | Course: " + course + " | Marks: " + marks;
    }
}


interface UniversityOperations {
    void addStudent(String id, String name, String course, int marks);
    void displayAllStudents();
    void removeStudent(String id);
    void searchStudent(String id);
    void sortStudentsByMarks();
    void showMapConversion(); 

    void countStudentsPerCourse();
    void displayUniqueCourses();
}


public class UniversitySystem implements UniversityOperations {   
    // ArrayList:
    ArrayList<Student> studentList = new ArrayList<>();
    
    // HashMap:
    HashMap<String, Student> studentMap = new HashMap<>();
    
    // HashSet: 
    HashSet<String> uniqueCourses = new HashSet<>();
    
    // Vector: 
    Vector<String> logVector = new Vector<>();
    
    // Stack:
    Stack<String> recentActions = new Stack<>();

   
    Scanner sc = new Scanner(System.in);
    @Override
    public void addStudent(String id, String name, String course, int marks) {
        try {

            // Regex Validation
            if (!id.matches("^[0-9]+$")) {
                System.out.println("Error: ID must be numeric.");
                return;
            }
            if (!name.matches("^[a-zA-Z\\s]+$")) {
                System.out.println("Error: Name must only contain letters.");
                return;
            }

            
            if (studentMap.containsKey(id)) {
                System.out.println("Error: Student ID already exists!");
                return;
            }

            
            Student s = new Student(id, name, course, marks);

            
            studentList.add(s);           
            studentMap.put(id, s);        
            uniqueCourses.add(course);    
            
            recentActions.push("Added ID: " + id);
            logVector.add("Log: Student " + name + " joined " + course);

            System.out.println("Success: Student Added.");

        } catch (Exception e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    @Override
    public void displayAllStudents() {
        System.out.println("\n--- All Students (ArrayList) ---");
        if (studentList.isEmpty()) {
            System.out.println("No students found.");
        } else {
            for (Student s : studentList) {
                System.out.println(s);
            }
        }
    }

    @Override
    public void removeStudent(String id) {
        if (studentMap.containsKey(id)) {
            Student s = studentMap.get(id);
            
            studentMap.remove(id);
            studentList.remove(s);
            
            recentActions.push("Removed ID: " + id);
            System.out.println("Success: Student removed.");
        } else {
            System.out.println("Error: ID not found.");
        }
    }

    @Override
    public void searchStudent(String id) {
    
        if (studentMap.containsKey(id)) {
            System.out.println("Found: " + studentMap.get(id));
        } else {
            System.out.println("Student not found.");
        }
    }

    @Override
    public void sortStudentsByMarks() {
        Collections.sort(studentList, new Comparator<Student>() {
            @Override
            public int compare(Student s1, Student s2) {
                
                return s2.marks - s1.marks;
            }
        });
        System.out.println("Success: Students sorted by marks. (Select Display to view)");
    }

    @Override
    public void showMapConversion() {
        System.out.println("\nConverting HashMap to TreeMap ");
     
        TreeMap<String, Student> treeMap = new TreeMap<>(studentMap);
        
        for (Map.Entry<String, Student> entry : treeMap.entrySet()) {
            System.out.println(entry.getValue());
        }
        System.out.println("(Notice how IDs are now sorted!)");
    }

    @Override
    public void countStudentsPerCourse() {
        
        Hashtable<String, Integer> courseCounts = new Hashtable<>();

        for (Student s : studentList) {
            String c = s.course;
            if (courseCounts.containsKey(c)) {
                courseCounts.put(c, courseCounts.get(c) + 1);
            } else {
                courseCounts.put(c, 1);
            }
        }

        System.out.println("\n Course Wise Count ");
        System.out.println(courseCounts);
    }

    @Override
    public void displayUniqueCourses() {
        System.out.println("\nUnique Courses (Using HashSet) ");
        
    }
    
    
    public void showRecentActivity() {
        System.out.println("\n--- Recent Activity (Stack) ---");
        if (recentActions.isEmpty()) System.out.println("No history.");
        else {
           
            System.out.println("Last Action: " + recentActions.peek()); 
        }
    }

    public static void main(String[] args) {
        UniversitySystem sys = new UniversitySystem();
        Scanner input = new Scanner(System.in);
        boolean running = true;

        System.out.println("=== UNIVERSITY MANAGEMENT SYSTEM ===");

        while (running) {
            try {
                System.out.println("\n1. Add Student");
                System.out.println("2. Display All");
                System.out.println("3. Search by ID");
                System.out.println("4. Remove by ID");
                System.out.println("5. Sort by Marks");
                System.out.println("6. Show Unique Courses");
                System.out.println("7. Count Students per Course");
                System.out.println("8. Convert HashMap to TreeMap (Sort by ID)");
                System.out.println("9. Show Last Action (Stack Demo)");
                System.out.println("10. Exit");
                System.out.print("Choice: ");

                int choice = Integer.parseInt(input.nextLine());

                switch (choice) {
                    case 1:
                        System.out.print("ID: ");
                        String id = input.nextLine();
                        System.out.print("Name: ");
                        String name = input.nextLine();
                        System.out.print("Course: ");
                        String course = input.nextLine();
                        System.out.print("Marks: ");
                        int marks = Integer.parseInt(input.nextLine());
                        sys.addStudent(id, name, course, marks);
                        break;
                    case 2:
                        sys.displayAllStudents();
                        break;
                    case 3:
                        System.out.print("Enter ID: ");
                        sys.searchStudent(input.nextLine());
                        break;
                    case 4:
                        System.out.print("Enter ID: ");
                        sys.removeStudent(input.nextLine());
                        break;
                    case 5:
                        sys.sortStudentsByMarks();
                        break;
                    case 6:
                        sys.displayUniqueCourses();
                        break;
                    case 7:
                        sys.countStudentsPerCourse();
                        break;
                    case 8:
                        sys.showMapConversion();
                        break;
                    case 9:
                        sys.showRecentActivity();
                        break;
                    case 10:
                        running = false;
                        System.out.println("Exiting...");
                        break;
                    default:
                        System.out.println("Invalid option.");
                }
            } catch (NumberFormatException e) {

                System.out.println("Please enter valid numbers.");
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}