import java.util.Scanner;
import java.util.InputMismatchException;

// 1. Custom Exception Class 
class InsufficientFundsException extends Exception {
    public InsufficientFundsException(String message) {
        super(message);
    }
}

// 2. Interfaces 

// Interface for financial operations
interface BankingService {
    void deposit(double amount);
    void withdraw(double amount) throws InsufficientFundsException;
    double checkBalance();
}

//Customer Service Interface
interface CustomerService {
    void displayCustomerDetails();
    String getAccountHolder();
}

//  3. Implementation Class 


class SmartBankAccount implements BankingService, CustomerService {
    private String accountHolder;
    private String accountNumber;
    private double balance;

    public SmartBankAccount(String name, String accNum, double initialBalance) {
        this.accountHolder = name;
        this.accountNumber = accNum;
        this.balance = initialBalance;
    }

    // Implementing BankingService methods
    @Override
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Success: Deposited Rs." + amount);
        } else {
            System.out.println("Error: Amount must be positive.");
        }
    }

    @Override
    public void withdraw(double amount) throws InsufficientFundsException {
        if (amount <= 0) {
            System.out.println("Error: Amount must be positive.");
            return;
        }
        if (amount > balance) {
            throw new InsufficientFundsException("Required: Rs." + amount + " | Available: Rs." + balance);
        }
        balance -= amount;
        System.out.println("Success: Withdrawn Rs." + amount);
    }

    @Override
    public double checkBalance() {
        return balance;
    }

    // Implementing CustomerService methods
    @Override
    public void displayCustomerDetails() {
        System.out.println("\n Customer Profile ");
        System.out.println("Name: " + accountHolder);
        System.out.println("Account: " + accountNumber);
        System.out.println("Status: Active");
    }

    @Override
    public String getAccountHolder() {
        return accountHolder;
    }
}

// 4. Main Program 

public class InterfaceBankSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        SmartBankAccount account = null;

        while (true) {
            System.out.println("\nMulti-Interface Bank System");
            System.out.println("1. Open New Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Check Balance");
            System.out.println("5. Customer Profile");
            System.out.println("6. Exit");
            System.out.print("Select Operation: ");

            try {
                int choice = sc.nextInt();

                sc.nextLine(); 

                switch (choice) {
                    case 1:
                        System.out.print("Enter Name: ");
                        String name = sc.nextLine();
                        System.out.print("Enter Account Number: ");
                        String accNum = sc.nextLine();
                        System.out.print("Enter Initial Deposit: ");
                        double initBal = sc.nextDouble();
                        
                        account = new SmartBankAccount(name, accNum, initBal);
                        System.out.println("Account created for " + name);
                        break;

                    case 2:
                        if (account != null) {
                            System.out.print("Enter deposit amount: ");
                            account.deposit(sc.nextDouble());
                        } else System.out.println("Please open an account first.");
                        break;

                    case 3:
                        if (account != null) {
                            System.out.print("Enter withdrawal amount: ");
                            double amt = sc.nextDouble();
                            try {
                                account.withdraw(amt);
                            } catch (InsufficientFundsException e) {
                                System.out.println("Transaction Failed: " + e.getMessage());
                            }
                        } else System.out.println("Please open an account first.");
                        break;

                    case 4:
                        if (account != null) 
                            System.out.println("Current Balance: Rs." + account.checkBalance());
                        else System.out.println("Please open an account first.");
                        break;

                    case 5:
                        if (account != null) 
                            account.displayCustomerDetails();
                        else System.out.println("Please open an account first.");
                        break;

                    case 6:
                        System.out.println("Exiting System.");
                        sc.close();
                        System.exit(0);
                    
                    default:
                        System.out.println("Invalid option.");
                }

            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter numbers only for menu and amounts.");
                sc.nextLine(); 
            }
        }
    }
}