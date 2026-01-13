package Assignment_4;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Task2_CreateInsert {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/college_db";
        String user = "root";
        String password = "password";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             Statement stmt = conn.createStatement()) {

            // 1. Create Table SQL
            String createTableSQL = "CREATE TABLE IF NOT EXISTS Students (" +
                    "id INT PRIMARY KEY, " +
                    "name VARCHAR(50), " +
                    "branch VARCHAR(20), " +
                    "percentage DOUBLE, " +
                    "year_of_passing INT, " +
                    "semester INT)";
            
            stmt.execute(createTableSQL);
            System.out.println("Table 'Students' created successfully.");

            // 2. Insert Records SQL
            String insertSQL = "INSERT INTO Students VALUES " +
                    "(1, 'Amit', 'CSE', 75.5, 2025, 5), " +
                    "(2, 'Priya', 'EC', 82.0, 2025, 7), " +
                    "(3, 'Rahul', 'Civil', 60.0, 2024, 8), " +
                    "(4, 'Sneha', 'CSE', 70.0, 2026, 3), " +
                    "(5, 'Vikram', 'Civil', 65.5, 2024, 8), " +
                    "(6, 'Rohan', 'EC', 78.0, 2025, 7)";

            int rowsAffected = stmt.executeUpdate(insertSQL);
            System.out.println(rowsAffected + " records inserted successfully.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}