import java.util.Scanner;

public class MathProblems {

    // Method - Addition
    public static double add(double num1, double num2) {
        return num1 + num2;
    }

    // Method - Subtraction
    public static double subtract(double num1, double num2) {
        return num1 - num2;
    }

    // Method- Multiplication
    public static double multiply(double num1, double num2) {
        return num1 * num2;
    }

    // Method - Division
    public static double divide(double num1, double num2) {
        if (num2 == 0) {
            System.out.println("Cannot divide by zero.");
            return 0;
        }    
        return num1 / num2;
    }

    // Method to calculate Remainder
    public static double remainder(double num1, double num2) {
        return num1 % num2;
    }

    // Method to calculate Square
    public static double square(double num) {
        return num * num;
    }

    // Method to calculate Cube
    public static double cube(double num) {
        return num * num * num;
    }

    // Method to calculate Absolute value
    public static double absolute(double num) {
        if (num < 0) {
            return -num;
        }
        return num;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        
        System.out.print("Enter first number: ");
        double num1 = sc.nextDouble();
        
        System.out.print("Enter second number for basic ops: ");
        double num2 = sc.nextDouble();

        
        System.out.println("Addition: " + add(num1, num2));
        System.out.println("Subtraction: " + subtract(num1, num2));
        System.out.println("Multiplication: " + multiply(num1, num2));
        System.out.println("Division: " + divide(num1, num2));
        System.out.println("Remainder: " + remainder(num1, num2));
        sc.close();
    }
}