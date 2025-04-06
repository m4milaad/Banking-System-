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
