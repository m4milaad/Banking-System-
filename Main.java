import java.util.Scanner;

public class Main {
    private static String adminUsername = "milad";
    private static String adminPassword = "milad3103";

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


