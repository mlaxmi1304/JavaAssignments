package Assignment_4;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class Task3_Update {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/college_db";
        String user = "root";
        String password = "my_MySQL_password";

        try (Connection conn = DriverManager.getConnection(url, user, password)) {

            // SQL to increase percentage by 5 where branch is CSE
            String updateSQL = "UPDATE Students SET percentage = percentage + 5 WHERE branch = ?";
            
            PreparedStatement pstmt = conn.prepareStatement(updateSQL);
            pstmt.setString(1, "CSE");

            int rows = pstmt.executeUpdate();
            System.out.println(rows + " records updated (CSE students received 5% hike).");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}