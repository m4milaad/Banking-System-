import java.util.ArrayList;
import java.util.List;

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
