import java.io.*;
import java.util.Scanner;

//1. ABSTRACTION & ENCAPSULATION 
abstract class BankAccount {

    // Encapsulation: Private variables (Access Specifiers)
    private String accName;
    private String accNumber;
    protected double balance; 

    
    public BankAccount(String accName, String accNumber, double balance) {
        this.accName = accName;
        this.accNumber = accNumber;
        this.balance = balance;
    }

    
    public abstract void showAccountType();

    
    public void displayDetails() {
        System.out.println("\n--- Account Details ---");
        System.out.println("Holder Name: " + accName);
        System.out.println("Account No : " + accNumber);
        System.out.println("Balance    : $" + balance);
    }

   
    public String getAccName() { return accName; }
    public String getAccNumber() { return accNumber; }
    public double getBalance() { return balance; }

    // POLYMORPHISM: METHOD OVERLOADING
    // Method 1: Simple Deposit
    public void deposit(double amount) {
        balance += amount;
        System.out.println("Deposited: $" + amount);
        System.out.println("New Balance: $" + balance);
    }

    // Method 2: Deposit with a note - overloaded
    public void deposit(double amount, String note) {
        balance += amount;
        System.out.println("Deposited: $" + amount + " (Note: " + note + ")");
        System.out.println("New Balance: $" + balance);
    }

    // Virtual method for overriding
    public void withdraw(double amount) {
        
        if (balance >= amount) {
            balance -= amount;
            System.out.println("Withdrawn: $" + amount);
        } else {
            System.out.println("Insufficient Balance.");
        }
    }
}

// 2. HIERARCHICAL INHERITANCE 

// Child Class 1: Savings Account
class SavingsAccount extends BankAccount {
    double interestRate = 0.05; 

    public SavingsAccount(String name, String accNum, double bal) {
        super(name, accNum, bal); }
    

    @Override
    public void showAccountType() {
        System.out.println("Type: Savings Account");
    }

    // POLYMORPHISM: METHOD OVERRIDING
    @Override
    public void withdraw(double amount) {
        if (balance - amount < 500) { 
            System.out.println("Error: Cannot withdraw. Minimum balance of $500 required.");
        } else {
            balance -= amount;
            System.out.println("Success: Withdrawn $" + amount + " from Savings.");
        }
    }
}

// Child Class 2: Current Account
class CurrentAccount extends BankAccount {
    
    public CurrentAccount(String name, String accNum, double bal) {
        super(name, accNum, bal);
    }

    @Override
    public void showAccountType() {
        System.out.println("Type: Current Account");
    }

    @Override
    public void withdraw(double amount) {
        
        if (balance >= amount) {
            balance -= amount;
            System.out.println("Success: Withdrawn $" + amount + " from Current Account.");
        } else {
            System.out.println("Error: Insufficient funds.");
        }
    }
}


public class BankSystem {

    
    public static void saveAccountToFile(BankAccount acc) {
        try {
            
            FileWriter writer = new FileWriter("bank_records.txt", true);
            writer.write("Name: " + acc.getAccName() + " | AccNo: " + acc.getAccNumber() + " | Balance: " + acc.getBalance() + "\n");
            writer.close();
            System.out.println("System Log: Record saved to 'bank_records.txt'.");
        } catch (IOException e) {
            System.out.println("File Error: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BankAccount myAccount = null; // RUNTIME POLYMORPHISM holder
        boolean running = true;

        System.out.println(" SIMPLE BANK MANAGEMENT SYSTEM ");

        while (running) {
            System.out.println("\n1.Create Account");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. Check Balance & Details");
            System.out.println("5. Save & Exit");
            System.out.print("Enter choice: ");
            
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter Name: ");
                    String name = scanner.next();
                    System.out.print("Enter Account Number: ");
                    String accNum = scanner.next();
                    System.out.print("Enter Initial Balance: ");
                    double bal = scanner.nextDouble();

                    System.out.println("Select Type: 1. Savings  2. Current");
                    int type = scanner.nextInt();

                    if (type == 1) {

                        // Runtime Polymorphism: Parent ref = Child object
                        myAccount = new SavingsAccount(name, accNum, bal);
                    } else {
                        myAccount = new CurrentAccount(name, accNum, bal);
                    }
                    System.out.println("Account Created Successfully!");
                    break;

                case 2:
                    if (myAccount == null) {
                        System.out.println("Please create an account first.");
                    } else {
                        System.out.print("Enter Amount to Deposit: ");
                        double depAmt = scanner.nextDouble();
                        
                        System.out.print("Add a note? (y/n): ");
                        String hasNote = scanner.next();
                        if(hasNote.equalsIgnoreCase("y")) {
                            System.out.print("Enter note: ");
                            String note = scanner.next();
                            myAccount.deposit(depAmt, note); // Overloaded method
                        } else {
                            myAccount.deposit(depAmt); // Standard method
                        }
                    }
                    break;

                case 3:
                    if (myAccount == null) {
                        System.out.println("Please create an account first.");
                    } else {
                        System.out.print("Enter Amount to Withdraw: ");
                        double withAmt = scanner.nextDouble();
                        
                        myAccount.withdraw(withAmt); 
                    }
                    break;

                case 4:
                    if (myAccount == null) {
                        System.out.println("Please create an account first.");
                    } else {
                        myAccount.displayDetails();
                        myAccount.showAccountType(); 
                    }
                    break;

                case 5:
                    if (myAccount != null) {
                        saveAccountToFile(myAccount);
                    }
                    System.out.println("Thank you..!");
                    running = false;
                    break;

                default:
                    System.out.println("Invalid Choice.");
            }
        }
        scanner.close();
    }
}