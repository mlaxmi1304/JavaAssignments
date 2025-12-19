import java.util.Scanner;

public class MatrixOperations {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n Matrix Operations Menu ");
            System.out.println("1. Addition");
            System.out.println("2. Subtraction");
            System.out.println("3. Multiplication");
            System.out.println("4. Transpose");
            System.out.println("5. Check if Square Matrix");
            System.out.println("6. Check if Diagonal Matrix");
            System.out.println("7. Check if Identity Matrix");
            System.out.println("8. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1: performAddition(); break;
                case 2: performSubtraction(); break;
                case 3: performMultiplication(); break;
                case 4: performTranspose(); break;
                case 5: checkSquare(); break;
                case 6: checkDiagonal(); break;
                case 7: checkIdentity(); break;
                case 8: 
                    System.out.println("Exiting...");
                    return;
                default: System.out.println("Invalid choice.");
            }
        }
    }


    // Reads matrix 
    static int[][] readMatrix(String name) {
        // System.out.print("Enter rows for " + name + ": ");
        int rows = sc.nextInt();
        // System.out.print("Enter columns for " + name + ": ");
        int cols = sc.nextInt();
        int[][] matrix = new int[rows][cols];

        // System.out.println("Enter elements for " + name + ":");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = sc.nextInt();
            }
        }
        return matrix;
    }

    // Prints a matrix
    static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int val : row) {
                System.out.print(val + "\t");
            }
            System.out.println();
        }
    }

    // Operations 

    static void performAddition() {
        int[][] a = readMatrix("Matrix A");
        int[][] b = readMatrix("Matrix B");

        if (a.length != b.length || a[0].length != b[0].length) {
            System.out.println(" Dimensions must match for addition.");
            return;
        }

        int[][] result = new int[a.length][a[0].length];
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[0].length; j++) {
                result[i][j] = a[i][j] + b[i][j];
            }
        }
        System.out.println("Result of Addition:");
        printMatrix(result);
    }

    static void performSubtraction() {
        int[][] a = readMatrix("Matrix A");
        int[][] b = readMatrix("Matrix B");

        if (a.length != b.length || a[0].length != b[0].length) {
            System.out.println("Dimensions must match for subtraction.");
            return;
        }

        int[][] result = new int[a.length][a[0].length];
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[0].length; j++) {
                result[i][j] = a[i][j] - b[i][j];
            }
        }
        System.out.println("Result of Subtraction:");
        printMatrix(result);
    }

    static void performMultiplication() {
        int[][] a = readMatrix("Matrix A");
        int[][] b = readMatrix("Matrix B");

        
        if (a[0].length != b.length) {
            System.out.println(" Columns of A must match Rows of B.");
            return;
        }

        int[][] result = new int[a.length][b[0].length];

        for (int i = 0; i < a.length; i++) {      
            for (int j = 0; j < b[0].length; j++) { 
                for (int k = 0; k < a[0].length; k++) { 
                    result[i][j] += a[i][k] * b[k][j];
                }
            }
        }
        System.out.println("Result of Multiplication:");
        printMatrix(result);
    }

    static void performTranspose() {
        int[][] a = readMatrix("Matrix");
        int rows = a.length;
        int cols = a[0].length;
        
        // Transpose flips rows and cols
        int[][] result = new int[cols][rows]; 

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result[j][i] = a[i][j];
            }
        }
        System.out.println("Matrix Transpose: ");
        printMatrix(result);
    }

    static void checkSquare() {
        int[][] a = readMatrix("Matrix");
        if (a.length == a[0].length) {
            System.out.println("Square Matrix");
        } else {
            System.out.println(" NOT a Square Matrix");
        }
    }

    static void checkDiagonal() {
        int[][] a = readMatrix("Matrix");
       
        if (a.length != a[0].length) {
            System.out.println("No (Not a square matrix).");
            return;
        }

        boolean isDiag = true;
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[0].length; j++) {
                
                if (i != j && a[i][j] != 0) {
                    isDiag = false;
                    break;
                }
            }
        }
        if (isDiag) System.out.println("Yes, it is a Diagonal Matrix.");
        else System.out.println("No, it is NOT a Diagonal Matrix.");
    }

    static void checkIdentity() {
        int[][] a = readMatrix("Matrix");
        
        if (a.length != a[0].length) {
            System.out.println("No (Not a square matrix).");
            return;
        }

        boolean isIdentity = true;
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[0].length; j++) {
                if (i == j && a[i][j] != 1) { 
                    isIdentity = false; 
                } else if (i != j && a[i][j] != 0) { 
                    isIdentity = false;
                }
            }
        }
        if (isIdentity) System.out.println("Identity Matrix");
        else System.out.println("Identity Matrix");
    }
}

/*
1 0 0 
0 1 0
0 0 1

*/