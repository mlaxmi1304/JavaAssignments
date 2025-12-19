
import java.util.Scanner;
import java.util.InputMismatchException;


class BankAccount {
    private String accountHolder;
    private String accountNumber;
    private double balance;


    public BankAccount(String name, String accNum, double initialBalance) {
        this.accountHolder = name;
        this.accountNumber = accNum;
        this.balance = initialBalance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Successfully deposited: $" + amount);
        } else {
            System.out.println("Error: Deposit amount must be positive.");
        }
    }

    public void withdraw(double amount) throws Exception {
        if (amount <= 0) {
            throw new Exception("Withdrawal amount must be positive.");
        }
        if (amount > balance) {
            throw new Exception("Insufficient Funds! Your balance is only $" + balance);
        }
        balance -= amount;
        System.out.println("Successfully withdrew: $" + amount);
    }

    public void checkBalance() {
        System.out.println("Current Balance: $" + balance);
    }

    public void displayDetails() {
        System.out.println("\n--- Account Details ---");
        System.out.println("Holder Name: " + accountHolder);
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Current Balance: $" + balance);
    }
}

// Main class containing the menu and user interface
public class BankManagementSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BankAccount account = null; 

        while (true) {
            System.out.println("\n--- Bank Management Menu ---");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. Balance Enquiry");
            System.out.println("5. Display Account Details");
            System.out.println("6. Exit");
            System.out.print("Enter choice: ");

            try {
                int choice = sc.nextInt();
                sc.nextLine();

                switch (choice) {
                    case 1:
                        System.out.print("Enter Account Holder Name: ");
                        String name = sc.nextLine();
                        System.out.print("Enter Account Number: ");
                        String accNum = sc.nextLine();
                        System.out.print("Enter Initial Balance: ");
                        double initialBal = sc.nextDouble();
                        
                        account = new BankAccount(name, accNum, initialBal);
                        System.out.println("Account created successfully!");
                        break;

                    case 2:
                        if (account != null) {
                            System.out.print("Enter amount to deposit: ");
                            double depAmount = sc.nextDouble();
                            account.deposit(depAmount);
                        } else {
                            System.out.println("No account found. Please create one first.");
                        }
                        break;

                    case 3:
                        if (account != null) {
                            System.out.print("Enter amount to withdraw: ");
                            double withAmount = sc.nextDouble();
                            try {
                                account.withdraw(withAmount);
                            } catch (Exception e) {
                                // Handling logic error (Insufficient funds)
                                System.out.println("Transaction Failed: " + e.getMessage());
                            }
                        } else {
                            System.out.println("No account found. Please create one first.");
                        }
                        break;

                    case 4:
                        if (account != null) account.checkBalance();
                        else System.out.println("No account found.");
                        break;

                    case 5:
                        if (account != null) account.displayDetails();
                        else System.out.println("No account found.");
                        break;

                    case 6:
                        System.out.println("Thank you for banking with us.");
                        sc.close();
                        System.exit(0);

                    default:
                        System.out.println("Invalid choice. Please enter 1-6.");
                }

            } catch (InputMismatchException e) {
                
                System.out.println("Invalid input! Please enter numeric values only.");
                sc.nextLine(); 
            }
        }
    }
}