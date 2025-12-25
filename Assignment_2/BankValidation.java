package Assignment_2;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class BankValidation {

    
    
    // Mobile - Exactly 10 digits
    private static final String MOBILE_PATTERN = "^[0-9]{10}$";
    
    // Email - Standard alphanumeric + @ + domain + . + extension
    private static final String EMAIL_PATTERN = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[a-zA-z]{2,6}$";
    
    // Username -Alphanumeric and underscore, 5 to 15 characters long
    private static final String USERNAME_PATTERN = "^[a-zA-Z0-9_]{5,15}$";
    
    // Password: Min 8 chars, at least one letter and one number
    private static final String PASSWORD_PATTERN = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d@$!%*?&]{8,}$";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        

        while (running) {
            try {
                
                System.out.println("\nSelect an option:");
                System.out.println("1. Validate Mobile Number");
                System.out.println("2. Validate Email ID");
                System.out.println("3. Validate Username");
                System.out.println("4. Validate Password");
                System.out.println("5. Exit");
                System.out.print("Enter choice: ");

               
                int choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1:
                        System.out.print("Enter Mobile Number (10 digits): ");
                        String mobile = scanner.nextLine();
                        validateInput(mobile, MOBILE_PATTERN, "Mobile Number");
                        break;

                    case 2:
                        System.out.print("Enter Email ID: ");
                        String email = scanner.nextLine();
                        validateInput(email, EMAIL_PATTERN, "Email ID");
                        break;

                    case 3:
                        System.out.print("Enter Username (5-15 chars, letters/numbers/_): ");
                        String username = scanner.nextLine();
                        validateInput(username, USERNAME_PATTERN, "Username");
                        break;

                    case 4:
                        System.out.print("Enter Password (Min 8 chars, 1 letter, 1 number): ");
                        String password = scanner.nextLine();
                        validateInput(password, PASSWORD_PATTERN, "Password");
                        break;

                    case 5:
                        System.out.println("Exiting application. Goodbye!");
                        running = false;
                        break;

                    default:
                        System.out.println("Invalid choice! Please select 1-5.");
                }

            } catch (NumberFormatException e) {
                // Exception handling if user types text instead of a number for the menu
                System.out.println("Error: Please enter a valid number for the menu choice.");
            } catch (Exception e) {
               
                System.out.println("An unexpected error occurred: " + e.getMessage());
            }
        }
        scanner.close();
    }

    
    public static void validateInput(String input, String regex, String fieldName) {
        // Compile the pattern
        Pattern pattern = Pattern.compile(regex);
        // Create the matcher
        Matcher matcher = pattern.matcher(input);

        
        if (matcher.matches()) {
            System.out.println("SUCCESS: Welcome! Your " + fieldName + " is valid.");
        } else {
            System.out.println("FAILURE: Invalid " + fieldName + " format.");
        }
    }
}