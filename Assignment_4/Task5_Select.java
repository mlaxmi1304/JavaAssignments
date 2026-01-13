package Assignment_4;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Task5_Select {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/college_db";
        String user = "root";
        String password = "my_DB_password";

        try (Connection conn = DriverManager.getConnection(url, user, password)) {

            // SQL to select specific records
            String selectSQL = "SELECT * FROM Students WHERE semester = ? AND branch = ?";
            
            PreparedStatement pstmt = conn.prepareStatement(selectSQL);
            pstmt.setInt(1, 7);
            pstmt.setString(2, "EC");

            ResultSet rs = pstmt.executeQuery();

            System.out.println("--- Students in Sem 7, Branch EC ---");
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                double pct = rs.getDouble("percentage");
                
                System.out.println("ID: " + id + ", Name: " + name + ", Percentage: " + pct + "%");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}