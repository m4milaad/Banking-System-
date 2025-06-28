// Banking System Data
let customers = [];
let accounts = [];
let loans = [];
let loanIdCounter = 1;

// Admin credentials
const ADMIN_USERNAME = "milad";
const ADMIN_PASSWORD = "milad3103";

// Classes
class Customer {
    constructor(id, name, email, phone) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
    }
}

class BankAccount {
    constructor(accountNumber, accountType, customer) {
        this.accountNumber = accountNumber;
        this.accountType = accountType;
        this.customer = customer;
        this.balance = 0;
        this.isActive = true;
    }

    deposit(amount) {
        if (amount > 0) {
            this.balance += amount;
            return true;
        }
        return false;
    }

    withdraw(amount) {
        if (amount > 0 && amount <= this.balance) {
            this.balance -= amount;
            return true;
        }
        return false;
    }

    checkBalance() {
        return this.balance;
    }

    transferFunds(targetAccount, amount) {
        if (this.withdraw(amount)) {
            targetAccount.deposit(amount);
            return true;
        }
        return false;
    }

    closeAccount() {
        this.isActive = false;
    }
}

class Loan {
    constructor(account, amount, interestRate, termMonths) {
        this.loanId = loanIdCounter++;
        this.account = account;
        this.amount = amount;
        this.interestRate = interestRate;
        this.termMonths = termMonths;
        this.status = "Pending";
    }

    approve() {
        this.status = "Approved";
        this.account.deposit(this.amount);
    }

    reject() {
        this.status = "Rejected";
    }
}

// Utility Functions
function showPanel(panelId) {
    // Hide all panels
    const panels = document.querySelectorAll('.panel');
    panels.forEach(panel => panel.classList.remove('active'));
    
    // Show selected panel
    document.getElementById(panelId).classList.add('active');
    
    // Clear forms
    clearForms();
}

function clearForms() {
    const forms = document.querySelectorAll('form');
    forms.forEach(form => form.reset());
}

function showResult(message, isError = false) {
    const resultOutput = document.getElementById('result-output');
    resultOutput.value = message;
    resultOutput.className = isError ? 'error' : 'success';
    showPanel('result-panel');
}

function findAccount(accountNumber) {
    return accounts.find(account => account.accountNumber === parseInt(accountNumber));
}

function findLoan(loanId) {
    return loans.find(loan => loan.loanId === parseInt(loanId));
}

// Event Handlers
function handleLogin(event) {
    event.preventDefault();
    
    const username = document.getElementById('username').value;
    const password = document.getElementById('password').value;
    
    if (username === ADMIN_USERNAME && password === ADMIN_PASSWORD) {
        const adminOutput = document.getElementById('admin-output');
        adminOutput.value = "Login successful! Welcome to Admin Panel.\n";
        showPanel('admin-panel');
    } else {
        showResult("Invalid username or password.", true);
    }
}

function handleCreateAccount(event) {
    event.preventDefault();
    
    try {
        const accNum = parseInt(document.getElementById('acc-number').value);
        const accType = document.getElementById('acc-type').value;
        const name = document.getElementById('customer-name').value;
        const email = document.getElementById('email').value;
        const phone = document.getElementById('phone').value;
        
        // Check if account already exists
        if (findAccount(accNum)) {
            showResult("Account number already exists!", true);
            return;
        }
        
        const newCustomer = new Customer(accNum, name, email, phone);
        customers.push(newCustomer);
        
        const newAccount = new BankAccount(accNum, accType, newCustomer);
        accounts.push(newAccount);
        
        showResult(`Account created successfully for: ${name}\nAccount Number: ${accNum}`);
    } catch (error) {
        showResult("Please enter valid information.", true);
    }
}

function handleTransaction(event, type) {
    event.preventDefault();
    
    try {
        let accountNum, amount, account;
        
        if (type === 'deposit') {
            accountNum = parseInt(document.getElementById('transaction-account').value);
            amount = parseFloat(document.getElementById('transaction-amount').value);
        } else if (type === 'withdraw') {
            accountNum = parseInt(document.getElementById('withdraw-account').value);
            amount = parseFloat(document.getElementById('withdraw-amount').value);
        } else if (type === 'check-balance') {
            accountNum = parseInt(document.getElementById('balance-account').value);
        }
        
        account = findAccount(accountNum);
        
        if (!account) {
            showResult("Account not found!", true);
            return;
        }
        
        if (!account.isActive) {
            showResult("Account is closed!", true);
            return;
        }
        
        let result = "";
        
        if (type === 'deposit') {
            if (amount > 0) {
                account.deposit(amount);
                result = `Deposited: $${amount.toFixed(2)}\nNew Balance: $${account.checkBalance().toFixed(2)}`;
            } else {
                showResult("Deposit amount must be positive.", true);
                return;
            }
        } else if (type === 'withdraw') {
            if (amount > 0) {
                const oldBalance = account.checkBalance();
                if (amount <= oldBalance) {
                    account.withdraw(amount);
                    result = `Withdrawn: $${amount.toFixed(2)}\nNew Balance: $${account.checkBalance().toFixed(2)}`;
                } else {
                    showResult(`Insufficient balance! Current balance: $${oldBalance.toFixed(2)}`, true);
                    return;
                }
            } else {
                showResult("Withdrawal amount must be positive.", true);
                return;
            }
        } else if (type === 'check-balance') {
            result = `Current Balance: $${account.checkBalance().toFixed(2)}`;
        }
        
        showResult(result);
    } catch (error) {
        showResult("Please enter valid numbers.", true);
    }
}

function handleTransfer(event) {
    event.preventDefault();
    
    try {
        const sourceAccNum = parseInt(document.getElementById('source-account').value);
        const targetAccNum = parseInt(document.getElementById('target-account').value);
        const amount = parseFloat(document.getElementById('transfer-amount').value);
        
        const sourceAccount = findAccount(sourceAccNum);
        const targetAccount = findAccount(targetAccNum);
        
        if (!sourceAccount) {
            showResult("Source account not found!", true);
            return;
        }
        
        if (!targetAccount) {
            showResult("Target account not found!", true);
            return;
        }
        
        if (!sourceAccount.isActive || !targetAccount.isActive) {
            showResult("One or both accounts are closed!", true);
            return;
        }
        
        if (amount > 0) {
            if (amount <= sourceAccount.checkBalance()) {
                sourceAccount.transferFunds(targetAccount, amount);
                showResult(`Transfer successful!\nTransferred: $${amount.toFixed(2)}\nFrom Account: ${sourceAccNum}\nTo Account: ${targetAccNum}`);
            } else {
                showResult("Insufficient funds in source account!", true);
            }
        } else {
            showResult("Transfer amount must be positive.", true);
        }
    } catch (error) {
        showResult("Please enter valid numbers.", true);
    }
}

function handleLoanApplication(event) {
    event.preventDefault();
    
    try {
        const accountNum = parseInt(document.getElementById('loan-account').value);
        const loanAmount = parseFloat(document.getElementById('loan-amount').value);
        const interestRate = parseFloat(document.getElementById('interest-rate').value);
        const loanTerm = parseInt(document.getElementById('loan-term').value);
        
        const account = findAccount(accountNum);
        if (!account) {
            showResult("Account not found!", true);
            return;
        }
        
        if (!account.isActive) {
            showResult("Account is closed!", true);
            return;
        }
        
        const newLoan = new Loan(account, loanAmount, interestRate, loanTerm);
        loans.push(newLoan);
        
        showResult(`Loan application submitted successfully!\nLoan ID: ${newLoan.loanId}\nAmount: $${loanAmount.toFixed(2)}\nInterest Rate: ${interestRate}%\nTerm: ${loanTerm} months\nStatus: Pending`);
    } catch (error) {
        showResult("Please enter valid numbers.", true);
    }
}

// Admin Functions
function approveLoan() {
    try {
        const loanId = parseInt(document.getElementById('loan-id').value);
        const loan = findLoan(loanId);
        
        const adminOutput = document.getElementById('admin-output');
        
        if (loan) {
            loan.approve();
            adminOutput.value += `Loan ${loanId} approved successfully.\n`;
        } else {
            adminOutput.value += `Loan ${loanId} not found!\n`;
        }
        
        document.getElementById('loan-id').value = '';
    } catch (error) {
        document.getElementById('admin-output').value += "Please enter a valid loan ID.\n";
    }
}

function rejectLoan() {
    try {
        const loanId = parseInt(document.getElementById('loan-id').value);
        const loan = findLoan(loanId);
        
        const adminOutput = document.getElementById('admin-output');
        
        if (loan) {
            loan.reject();
            adminOutput.value += `Loan ${loanId} rejected successfully.\n`;
        } else {
            adminOutput.value += `Loan ${loanId} not found!\n`;
        }
        
        document.getElementById('loan-id').value = '';
    } catch (error) {
        document.getElementById('admin-output').value += "Please enter a valid loan ID.\n";
    }
}

function listLoans() {
    const adminOutput = document.getElementById('admin-output');
    adminOutput.value = "=== ALL LOANS ===\n";
    
    if (loans.length === 0) {
        adminOutput.value += "No loans found.\n";
        return;
    }
    
    loans.forEach(loan => {
        adminOutput.value += `Loan ID: ${loan.loanId}\n`;
        adminOutput.value += `Account: ${loan.account.accountNumber}\n`;
        adminOutput.value += `Customer: ${loan.account.customer.name}\n`;
        adminOutput.value += `Amount: $${loan.amount.toFixed(2)}\n`;
        adminOutput.value += `Interest Rate: ${loan.interestRate}%\n`;
        adminOutput.value += `Term: ${loan.termMonths} months\n`;
        adminOutput.value += `Status: ${loan.status}\n`;
        adminOutput.value += "------------------------\n";
    });
}

function listAccounts() {
    const adminOutput = document.getElementById('admin-output');
    adminOutput.value = "=== ALL ACCOUNTS ===\n";
    
    if (accounts.length === 0) {
        adminOutput.value += "No accounts found.\n";
        return;
    }
    
    accounts.forEach(account => {
        adminOutput.value += `Account Number: ${account.accountNumber}\n`;
        adminOutput.value += `Type: ${account.accountType}\n`;
        adminOutput.value += `Customer: ${account.customer.name}\n`;
        adminOutput.value += `Email: ${account.customer.email}\n`;
        adminOutput.value += `Phone: ${account.customer.phone}\n`;
        adminOutput.value += `Balance: $${account.balance.toFixed(2)}\n`;
        adminOutput.value += `Status: ${account.isActive ? 'Active' : 'Closed'}\n`;
        adminOutput.value += "------------------------\n";
    });
}

function closeAccount() {
    try {
        const accountNum = parseInt(document.getElementById('loan-id').value);
        const account = findAccount(accountNum);
        
        const adminOutput = document.getElementById('admin-output');
        
        if (account) {
            account.closeAccount();
            adminOutput.value += `Account ${accountNum} closed successfully.\n`;
        } else {
            adminOutput.value += `Account ${accountNum} not found!\n`;
        }
        
        document.getElementById('loan-id').value = '';
    } catch (error) {
        document.getElementById('admin-output').value += "Please enter a valid account number.\n";
    }
}

function generateReport() {
    const adminOutput = document.getElementById('admin-output');
    adminOutput.value = "=== BANKING SYSTEM REPORT ===\n\n";
    
    // Account statistics
    const totalAccounts = accounts.length;
    const activeAccounts = accounts.filter(acc => acc.isActive).length;
    const totalBalance = accounts.reduce((sum, acc) => sum + acc.balance, 0);
    
    adminOutput.value += `Total Accounts: ${totalAccounts}\n`;
    adminOutput.value += `Active Accounts: ${activeAccounts}\n`;
    adminOutput.value += `Total System Balance: $${totalBalance.toFixed(2)}\n\n`;
    
    // Loan statistics
    const totalLoans = loans.length;
    const approvedLoans = loans.filter(loan => loan.status === 'Approved').length;
    const pendingLoans = loans.filter(loan => loan.status === 'Pending').length;
    const rejectedLoans = loans.filter(loan => loan.status === 'Rejected').length;
    const totalLoanAmount = loans.reduce((sum, loan) => sum + loan.amount, 0);
    
    adminOutput.value += `Total Loans: ${totalLoans}\n`;
    adminOutput.value += `Approved Loans: ${approvedLoans}\n`;
    adminOutput.value += `Pending Loans: ${pendingLoans}\n`;
    adminOutput.value += `Rejected Loans: ${rejectedLoans}\n`;
    adminOutput.value += `Total Loan Amount: $${totalLoanAmount.toFixed(2)}\n`;
}

function logout() {
    showPanel('main-menu');
}

function exitApp() {
    if (confirm('Are you sure you want to exit?')) {
        window.close();
    }
}

// Initialize the application
document.addEventListener('DOMContentLoaded', function() {
    showPanel('main-menu');
});