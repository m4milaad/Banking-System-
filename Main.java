import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static String adminUsername = "milad";
    private static String adminPassword = "milad3103"; // In a real application, use hashed passwords

    public static void main(String[] args) {
        Bank bank = new Bank();
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("\n===== Banking System Menu =====");
            System.out.println("1. Login as Admin");
            System.out.println("2. Create Account");
            System.out.println("3. Deposit");
            System.out.println("4. Withdraw");
            System.out.println("5. Check Balance");
            System.out.println("6. Transfer Funds");
            System.out.println("7. Apply for Loan");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    if (adminLogin(scanner)) {
                        boolean adminExit = false;
                        while (!adminExit) {
                            System.out.println("\n===== Admin Menu =====");
                            System.out.println("1. Approve Loan");
                            System.out.println("2. Reject Loan");
                            System.out.println("3. List All Loans");
                            System.out.println("4. List All Accounts");
                            System.out.println("5. Close Account");
                            System.out.println("6. Generate Report");
                            System.out.println("7. Logout");
                            System.out.println("8. Exit");
                            System.out.print("Enter your choice: ");

                            int adminChoice = scanner.nextInt();

                            switch (adminChoice) {
                                case 1:
                                    approveLoan(bank, scanner);
                                    break;
                                case 2:
                                    rejectLoan(bank, scanner);
                                    break;
                                case 3:
                                    bank.listAllLoans();
                                    break;
                                case 4:
                                    bank.listAllAccounts();
                                    break;
                                case 5:
                                    closeAccount(bank, scanner);
                                    break;
                                case 6:
                                    bank.generateReport();
                                    break;
                                case 7:
                                    adminExit = true;
                                    break;
                                case 8:
                                    adminExit = true;
                                    exit = true;
                                    System.out.println("Thank you for using the banking system!");
                                    break;
                                default:
                                    System.out.println("Invalid choice. Please try again.");
                            }
                        }
                    }
                    break;
                case 2:
                    createAccount(bank, scanner);
                    break;
                case 3:
                    deposit(bank, scanner);
                    break;
                case 4:
                    withdraw(bank, scanner);
                    break;
                case 5:
                    checkBalance(bank, scanner);
                    break;
                case 6:
                    transferFunds(bank, scanner);
                    break;
                case 7:
                    applyForLoan(bank, scanner);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
        scanner.close();
    }

    private static boolean adminLogin(Scanner scanner) {
        System.out.print("Enter admin username: ");
        String username = scanner.next();
        System.out.print("Enter admin password: ");
        String password = scanner.next();

        if (username.equals(adminUsername) && password.equals(adminPassword)) {
            System.out.println("Login successful!");
            return true;
        } else {
            System.out.println("Invalid username or password.");
            return false;
        }
    }

    private static void createAccount(Bank bank, Scanner scanner) {
        System.out.print("Enter account number: ");
        int accNum = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter account type: ");
        String accType = scanner.nextLine();
        System.out.print("Enter customer name: ");
        String name = scanner.nextLine();
        System.out.print("Enter customer email: ");
        String email = scanner.nextLine();
        System.out.print("Enter customer phone: ");
        String phone = scanner.nextLine();

        Customer newCustomer = new Customer(accNum, name, email, phone);
        bank.addCustomer(newCustomer);

        BankAccount newAccount = new BankAccount(accNum, accType, newCustomer);
        bank.addAccount(newAccount);
        newAccount.createAccount();
    }

    private static void deposit(Bank bank, Scanner scanner) {
        System.out.print("Enter account number: ");
        int depositAcc = scanner.nextInt();
        BankAccount depAccount = bank.findAccount(depositAcc);
        if (depAccount != null) {
            System.out.print("Enter amount to deposit: ");
            double depAmount = scanner.nextDouble();
            if (depAmount > 0) {
                depAccount.deposit(depAmount);
            } else {
                System.out.println("Deposit amount must be positive.");
            }
        }
    }

    private static void withdraw(Bank bank, Scanner scanner) {
        System.out.print("Enter account number: ");
        int withdrawAcc = scanner.nextInt();
        BankAccount withAccount = bank.findAccount(withdrawAcc);
        if (withAccount != null) {
            System.out.println("Current balance is: " + withAccount.checkBalance());
            System.out.print("Enter amount to withdraw: ");
            double withAmount = scanner.nextDouble();
            if (withAmount > 0) {
                withAccount.withdraw(withAmount);
                System.out.println("Remaining balance is: " + withAccount.checkBalance());
            } else {
                System.out.println("Withdrawal amount must be positive.");
            }
        }
    }

    private static void checkBalance(Bank bank, Scanner scanner) {
        System.out.print("Enter account number: ");
        int balanceAcc = scanner.nextInt();
        BankAccount balAccount = bank.findAccount(balanceAcc);
        if (balAccount != null) {
            System.out.println("Balance: " + balAccount.checkBalance());
        }
    }

    private static void transferFunds(Bank bank, Scanner scanner) {
        System.out.print("Enter source account number: ");
        int sourceAcc = scanner.nextInt();
        System.out.print("Enter target account number: ");
        int targetAcc = scanner.nextInt();
        System.out.print("Enter amount to transfer: ");
        double transferAmount = scanner.nextDouble();

        BankAccount sourceAccount = bank.findAccount(sourceAcc);
        BankAccount targetAccount = bank.findAccount(targetAcc);

        if (sourceAccount != null && targetAccount != null) {
            if (transferAmount > 0) {
                sourceAccount.transferFunds(targetAccount, transferAmount);
            } else {
                System.out.println("Transfer amount must be positive.");
            }
        }
    }

    private static void closeAccount(Bank bank, Scanner scanner) {
        System.out.print("Enter account number to close: ");
        int closeAcc = scanner.nextInt();
        BankAccount closeAccount = bank.findAccount(closeAcc);
        if (closeAccount != null) {
            closeAccount.closeAccount();
            bank.removeAccount(closeAcc);
        }
    }

    private static void approveLoan(Bank bank, Scanner scanner) {
        System.out.print("Enter loan ID to approve: ");
        int loanId = scanner.nextInt();
        Loan loan = bank.findLoan(loanId);
        if (loan != null) {
            bank.approveLoan(loan);
        } else {
            System.out.println("Loan not found!");
        }
    }

    private static void rejectLoan(Bank bank, Scanner scanner) {
        System.out.print("Enter loan ID to reject: ");
        int loanId = scanner.nextInt();
        Loan loan = bank.findLoan(loanId);
        if (loan != null) {
            bank.rejectLoan(loan);
        } else {
            System.out.println("Loan not found!");
        }
    }

    private static void applyForLoan(Bank bank, Scanner scanner) {
        System.out.print("Enter your account number: ");
        int accountNumber = scanner.nextInt();
        BankAccount account = bank.findAccount(accountNumber);
        if (account != null) {
            System.out.print("Enter loan amount: ");
            double loanAmount = scanner.nextDouble();
            System.out.print("Enter interest rate: ");
            double interestRate = scanner.nextDouble();
            System.out.print("Enter loan term (in months): ");
            int loanTerm = scanner.nextInt();

            Loan newLoan = new Loan(account, loanAmount, interestRate, loanTerm);
            newLoan.applyForLoan();
            bank.addLoans(newLoan);
        } else {
            System.out.println("Account not found!");
        }
    }
}

class Bank {
    private List<BankAccount> accounts = new ArrayList<>();
    private List<Customer> customers = new ArrayList<>();
    private List<Loan> approvedLoans = new ArrayList<>();
    private List<Loan> pendingLoans = new ArrayList<>();

    public void addLoans(Loan loan) {
        pendingLoans.add(loan);
        System.out.println("Loan added successfully to pending loans.");
    }

    public void approveLoan(Loan loan) {
        if (pendingLoans.remove(loan)) {
            approvedLoans.add(loan);
            loan.approveLoan(); // This will deposit the loan amount into the borrower's account
            System.out.println("Loan approved successfully.");
        } else {
            System.out.println("Loan not found in pending loans.");
        }
    }

    public void rejectLoan(Loan loan) {
        if (pendingLoans.remove(loan)) {
            System.out.println("Loan rejected successfully.");
        } else {
            System.out.println("Loan not found in pending loans.");
        }
    }

    public void listAllLoans() {
        System.out.println("Pending Loans:");
        if (pendingLoans.isEmpty()) {
            System.out.println("No pending loans.");
        } else {
            for (Loan loan : pendingLoans) {
                loan.viewLoanDetails();
                System.out.println("----------------------");
            }
        }

        System.out.println("Approved Loans:");
        if (approvedLoans.isEmpty()) {
            System.out.println("No approved loans.");
        } else {
            for (Loan loan : approvedLoans) {
                loan.viewLoanDetails();
                System.out.println("----------------------");
            }
        }
    }

    public void addAccount(BankAccount account) {
        accounts.add(account);
        System.out.println("Account added successfully!");
    }

    public void removeAccount(int accountNumber) {
        accounts.removeIf(account -> account.getAccountNumber() == accountNumber);
        System.out.println("Account removed successfully!");
    }

    public BankAccount findAccount(int accountNumber) {
        for (BankAccount account : accounts) {
            if (account.getAccountNumber() == accountNumber) {
                return account;
            }
        }
        System.out.println("Account not found!");
        return null;
    }

    public Loan findLoan(int loanId) {
        for (Loan loan : pendingLoans) {
            if (loan.getLoanId() == loanId) {
                return loan;
            }
        }
        for (Loan loan : approvedLoans) {
            if (loan.getLoanId() == loanId) {
                return loan;
            }
        }
        return null;
    }

    public void listAllAccounts() {
        for (BankAccount account : accounts) {
            account.getAccountDetails();
            System.out.println("----------------------");
        }
    }

    public void generateReport() {
        System.out.println("Bank Report: Total Accounts = " + accounts.size());
        double totalMoney = 0;
        for (BankAccount account : accounts) {
            totalMoney += account.checkBalance();
        }
        System.out.println("Bank Report: Total money = " + totalMoney);
    }

    public void addCustomer(Customer customer) {
        customers.add(customer);
        System.out.println("Customer added successfully!");
    }
}

class Customer {
    private final int customerId;
    private String name;
    private String email;
    private String phone;

    public Customer(int customerId, String name, String email, String phone) {
        this.customerId = customerId;
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    public void registerCustomer() {
        System.out.println("Customer Registered: " + name);
    }

    public void updateCustomerDetails(String name, String email, String phone) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        System.out.println("Customer details updated!");
    }

    public void getCustomerDetails() {
        System.out.println("Customer ID: " + customerId);
        System.out.println("Name: " + name);
        System.out.println("Email: " + email);
        System.out.println("Phone: " + phone);
    }

    public String getName() {
        return name;
    }
}

class BankAccount {
    private int accountNumber;
    private double balance;
    private String accountType;
    private Customer customer;

    public BankAccount(int accountNumber, String accountType, Customer customer) {
        this.accountNumber = accountNumber;
        this.accountType = accountType;
        this.customer = customer;
        this.balance = 0.0;
    }

    public void createAccount() {
        System.out.println("Account created for: " + customer.getName());
    }

    public void deposit(double amount) {
        balance += amount;
        System.out.println("Deposited: " + amount + " to the account of " + customer.getName());
    }

    public void withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
            System.out.println("Withdrawn: " + amount);
        } else {
            System.out.println("Insufficient balance!");
        }
    }

    public double checkBalance() {
        return balance;
    }

    public void getAccountDetails() {
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Account Type: " + accountType);
        System.out.println("Balance: " + balance);
        customer.getCustomerDetails();
    }

    public void transferFunds(BankAccount targetAccount, double amount) {
        if (amount <= balance) {
            this.withdraw(amount);
            targetAccount.deposit(amount);
            System.out.println("Transferred: " + amount + " to Account Number: " + targetAccount.accountNumber);
        } else {
            System.out.println("Insufficient funds for transfer!");
        }
    }

    public void closeAccount() {
        balance = 0;
        System.out.println("Account closed for: " + customer.getName());
    }

    public int getAccountNumber() {
        return accountNumber;
    }
}

class Loan {
    private static int loanCounter = 0; // Static counter to generate unique loan IDs
    private int loanId;
    private BankAccount borrowerAccount;
    private double loanAmount;
    private double interestRate;
    private int loanTerm; // in months
    private boolean isApproved;

    public Loan(BankAccount borrowerAccount, double loanAmount, double interestRate, int loanTerm) {
        this.loanId = ++loanCounter; // Increment loan ID for each new loan
        this.borrowerAccount = borrowerAccount;
        this.loanAmount = loanAmount;
        this.interestRate = interestRate;
        this.loanTerm = loanTerm;
        this.isApproved = false;
    }

    public void applyForLoan() {
        System.out.println("Loan Application Submitted for Account: " + borrowerAccount.getAccountNumber());
    }

    public void approveLoan() {
        if (!isApproved) {
            isApproved = true;
            borrowerAccount.deposit(loanAmount);
            System.out.println("Loan Approved! Amount " + loanAmount + " deposited to account " + borrowerAccount.getAccountNumber());
        } else {
            System.out.println("Loan already approved.");
        }
    }

    public void rejectLoan() {
        if (!isApproved) {
            System.out.println("Loan Rejected for account: " + borrowerAccount.getAccountNumber());
        } else {
            System.out.println("Loan already approved, cannot reject now.");
        }
    }

    public void viewLoanDetails() {
        System.out.println("Loan ID: " + loanId);
        System.out.println("Borrower Account: " + borrowerAccount.getAccountNumber());
        System.out.println("Loan Amount: " + loanAmount);
        System.out.println("Interest Rate: " + interestRate + "%");
        System.out.println("Loan Term: " + loanTerm + " months");
        System.out.println("Status: " + (isApproved ? "Approved" : "Pending"));
    }

    public int getLoanId() {
        return loanId;
    }

    public boolean isApproved() {
        return isApproved;
    }
}
