package Mini_Project_3;

public class Student implements Comparable<Student> {
    private int eno;
    private String name;
    private String branch;
    private double percentage;
    private int semester;

    // Constructor
    public Student(int eno, String name, String branch, double percentage, int semester) {
        this.eno = eno;
        this.name = name;
        this.branch = branch;
        this.percentage = percentage;
        this.semester = semester;
    }

    // Getters and Setters
    public int getEno() { return eno; }
    public String getName() { return name; }
    public String getBranch() { return branch; }
    public void setBranch(String branch) { this.branch = branch; }
    public double getPercentage() { return percentage; }
    public int getSemester() { return semester; }

    // Sorting logic (Sort by Eno)
    @Override
    public int compareTo(Student other) {
        return this.eno - other.eno;
    }

    // Display format
    @Override
    public String toString() {
        return "Eno: " + eno + " | Name: " + name + " | Branch: " + branch + 
               " | Sem: " + semester + " | Percentage: " + percentage + "%";
    }
}