package Assignment_4;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class Task4_Delete {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/college_db";
        String user = "root";
        String password = "my_password";

        try (Connection conn = DriverManager.getConnection(url, user, password)) {

            // SQL to delete Civil students from batch 2024
            String deleteSQL = "DELETE FROM Students WHERE branch = ? AND year_of_passing = ?";
            
            PreparedStatement pstmt = conn.prepareStatement(deleteSQL);
            pstmt.setString(1, "Civil");
            pstmt.setInt(2, 2024);

            int rows = pstmt.executeUpdate();
            System.out.println(rows + " records deleted (Civil branch, Year 2024).");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}