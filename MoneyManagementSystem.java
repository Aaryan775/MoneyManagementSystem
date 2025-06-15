import java.util.*;

// Base class for Money Management
class Transaction {
    double amount;
    String description;

    // Constructor to initialize transaction details
    Transaction(double amount, String description) {
        this.amount = amount;
        this.description = description;
    }

    // Method to display transaction details
    void displayTransaction() {
        System.out.println("Amount: " + amount);
        System.out.println("Description: " + description);
    }
}

// Derived class for managing deposits
class Deposit extends Transaction {
    Deposit(double amount, String description) {
        super(amount, description);
    }

    void displayDeposit() {
        System.out.println("Deposit Transaction:");
        displayTransaction();
    }
}

// Derived class for managing withdrawals
class Withdrawal extends Transaction {
    Withdrawal(double amount, String description) {
        super(amount, description);
    }

    void displayWithdrawal() {
        System.out.println("Withdrawal Transaction:");
        displayTransaction();
    }
}

// User class for login and registration
class User {
    String username;
    String password;
    double balance = 0;
    double budget = 0;
    double savingsPlan = 0;

    // Constructor
    User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // Method to update balance
    void updateBalance(double amount) {
        balance += amount;
    }

    // Method to set budget
    void setBudget(double budget) {
        this.budget = budget;
    }

    // Method to set savings future plan
    void setSavingsPlan(double savingsPlan) {
        this.savingsPlan = savingsPlan;
    }

    // Method to check balance
    double getBalance() {
        return balance;
    }

    // Method to view summary
    void viewSummary() {
        System.out.println("Balance: " + balance);
        System.out.println("Budget: " + budget);
        System.out.println("Saving Future Plan: " + savingsPlan);
    }
}

// Main class to test the Money Management System
public class MoneyManagementSystem {
    static User currentUser = null;
    static Scanner sc = new Scanner(System.in);

    // Method for user login
    static boolean login(String username, String password) {
        return currentUser != null && currentUser.username.equals(username) && currentUser.password.equals(password);
    }

    // Register a new user
    static void register() {
        System.out.print("Enter username: ");
        String username = sc.nextLine();
        System.out.print("Enter password: ");
        String password = sc.nextLine();
        currentUser = new User(username, password);
        System.out.println("User registered successfully.");
    }

    // Method for adding income
    static void addIncome() {
        System.out.print("Enter income amount: ");
        double amount = sc.nextDouble();
        sc.nextLine(); // consume the newline

        if (amount <= 0) {
            System.out.println("Income amount must be a positive number.");
            return;
        }

        System.out.print("Enter income description: ");
        String description = sc.nextLine();
        Deposit income = new Deposit(amount, description);
        currentUser.updateBalance(amount);
        income.displayDeposit();
    }

    // Method for adding expense
    static void addExpense() {
        System.out.print("Enter expense amount: ");
        double amount = sc.nextDouble();
        sc.nextLine(); // consume the newline

        if (amount <= 0) {
            System.out.println("Expense amount must be a positive number.");
            return;
        }

        if (amount > currentUser.getBalance()) {
            System.out.println("Insufficient balance! You cannot spend more than your current balance.");
            return;
        }

        System.out.print("Enter expense description: ");
        String description = sc.nextLine();
        Withdrawal expense = new Withdrawal(amount, description);
        currentUser.updateBalance(-amount);
        expense.displayWithdrawal();
    }

    // Method to set budget
    static void setBudget() {
        System.out.print("Enter your budget: ");
        double budget = sc.nextDouble();

        if (budget <= 0) {
            System.out.println("Budget must be a positive number.");
            return;
        }

        currentUser.setBudget(budget);
    }

    // Method to set savings future plan
    static void setSavingsPlan() {
        System.out.print("Enter your savings future plan amount: ");
        double savings = sc.nextDouble();

        if (savings <= 0) {
            System.out.println("Savings amount must be a positive number.");
            return;
        }

        currentUser.setSavingsPlan(savings);
    }

    // Method to check balance
    static void checkBalance() {
        System.out.println("Current balance: " + currentUser.getBalance());
    }

    // Method to display summary
    static void viewSummary() {
        currentUser.viewSummary();
    }

    public static void main(String[] args) {
        boolean loggedIn = false;

        while (true) {
            if (!loggedIn) {
                System.out.println("1. Register\n2. Login\n3. Exit");
                System.out.print("Choose an option: ");
                int choice = sc.nextInt();
                sc.nextLine(); // consume the newline

                switch (choice) {
                    case 1:
                        register();
                        break;
                    case 2:
                        System.out.print("Enter username: ");
                        String username = sc.nextLine();
                        System.out.print("Enter password: ");
                        String password = sc.nextLine();
                        if (login(username, password)) {
                            System.out.println("Login successful.");
                            loggedIn = true;
                        } else {
                            System.out.println("Invalid username or password.");
                        }
                        break;
                    case 3:
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Invalid option.");
                }
            } else {
                System.out.println("\nMoney Management System:");
                System.out.println("1. Add Income");
                System.out.println("2. Add Expense");
                System.out.println("3. Set Budget");
                System.out.println("4. Set Future Saving Plan");
                System.out.println("5. Check Balance");
                System.out.println("6. View Summary");
                System.out.println("7. Exit");

                System.out.print("Choose an option: ");
                int option = sc.nextInt();
                sc.nextLine(); // consume newline

                switch (option) {
                    case 1:
                        addIncome();
                        break;
                    case 2:
                        addExpense();
                        break;
                    case 3:
                        setBudget();
                        break;
                    case 4:
                        setSavingsPlan();
                        break;
                    case 5:
                        checkBalance();
                        break;
                    case 6:
                        viewSummary();
                        break;
                    case 7:
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Invalid option.");
                }
            }
        }
    }
}
