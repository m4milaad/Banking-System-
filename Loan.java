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
