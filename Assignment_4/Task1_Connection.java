package Assignment_4;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Task1_Connection {
    public static void main(String[] args) {
        // Database credentials
        String url = "jdbc:mysql://localhost:3306/college_db";
        String user = "root";
        String password = "myMySQLpassword"; 

        try {

            // Attempt to establish connection
            Connection conn = DriverManager.getConnection(url, user, password);
            
            if (conn != null) {
                System.out.println("Connection Successful");
                conn.close();
            }
        } catch (SQLException e) {
            System.out.println("Unable to connect");
            e.printStackTrace();
        }
    }
}