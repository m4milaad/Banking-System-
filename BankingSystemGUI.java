import java.awt.*;
import java.awt.event.*;

public class BankingSystemGUI extends Frame implements ActionListener {
    private Bank bank;
    private Panel mainPanel;
    private CardLayout cardLayout;


    // Login components
    private TextField usernameField, passwordField;
    private Button loginButton, backToMainButton;

    // Main menu components
    private Button adminLoginBtn, createAccountBtn, depositBtn, withdrawBtn,
            checkBalanceBtn, transferBtn, applyLoanBtn, exitBtn;

    // Account creation components
    private static int nextAccountNumber = 1000;
    private TextField accNumField, accTypeField, nameField, emailField, phoneField;
    private Button createAccBtn, backFromCreateBtn;

    // Transaction components
    private TextField accountNumField, amountField;
    private Button executeBtn, backFromTransactionBtn;

    // Transfer components
    private TextField sourceAccField, targetAccField, transferAmountField;
    private Button transferExecuteBtn, backFromTransferBtn;

    // Loan application components
    private TextField loanAccField, loanAmountField, interestRateField, loanTermField;
    private Button applyLoanExecuteBtn, backFromLoanBtn;

    // Admin components
    private TextArea adminTextArea;
    private TextField loanIdField, closeAccField;
    ;
    private Button approveBtn, rejectBtn, listLoansBtn, listAccountsBtn,
            closeAccountBtn, generateReportBtn, logoutBtn;

    // Result display
    private TextArea resultArea;
    private Button backFromResultBtn;

    private static final String ADMIN_USERNAME = "milad";
    private static final String ADMIN_PASSWORD = "milad3103";

    public BankingSystemGUI() {
        bank = new Bank();
        setupGUI();
    }

    private void setupGUI() {
        setTitle("Banking System");
        setSize(800, 600);
        setLayout(new BorderLayout());

        cardLayout = new CardLayout();
        mainPanel = new Panel(cardLayout);

        createMainMenu();
        createLoginPanel();
        createAccountCreationPanel();
        createTransactionPanel();
        createTransferPanel();
        createLoanApplicationPanel();
        createAdminPanel();
        createResultPanel();

        add(mainPanel, BorderLayout.CENTER);

        // Window closing event
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        setVisible(true);
    }

    private void createMainMenu() {
        Panel panel = new Panel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        Label titleLabel = new Label("Banking System", Label.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(20, 0, 30, 0);
        panel.add(titleLabel, gbc);

        gbc.gridwidth = 1;
        gbc.insets = new Insets(5, 10, 5, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        adminLoginBtn = new Button("Admin Login");
        adminLoginBtn.addActionListener(this);
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(adminLoginBtn, gbc);

        createAccountBtn = new Button("Create Account");
        createAccountBtn.addActionListener(this);
        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(createAccountBtn, gbc);

        depositBtn = new Button("Deposit");
        depositBtn.addActionListener(this);
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(depositBtn, gbc);

        withdrawBtn = new Button("Withdraw");
        withdrawBtn.addActionListener(this);
        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(withdrawBtn, gbc);

        checkBalanceBtn = new Button("Check Balance");
        checkBalanceBtn.addActionListener(this);
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(checkBalanceBtn, gbc);

        transferBtn = new Button("Transfer Funds");
        transferBtn.addActionListener(this);
        gbc.gridx = 1;
        gbc.gridy = 3;
        panel.add(transferBtn, gbc);

        applyLoanBtn = new Button("Apply for Loan");
        applyLoanBtn.addActionListener(this);
        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(applyLoanBtn, gbc);

        exitBtn = new Button("Exit");
        exitBtn.addActionListener(this);
        gbc.gridx = 1;
        gbc.gridy = 4;
        panel.add(exitBtn, gbc);

        mainPanel.add(panel, "MAIN");
    }

    private void createLoginPanel() {
        Panel panel = new Panel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        Label titleLabel = new Label("Admin Login", Label.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(-70, 0, 30, 0);
        panel.add(titleLabel, gbc);

        gbc.gridwidth = 1;
        gbc.insets = new Insets(5, 10, 5, 10);

        panel.add(new Label("Username:"), gbc);
        gbc.gridx = 1;
        usernameField = new TextField(20);
        panel.add(usernameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(new Label("Password:"), gbc);
        gbc.gridx = 1;
        passwordField = new TextField(20);
        passwordField.setEchoChar('*');
        panel.add(passwordField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(20, 10, 5, 10);
        loginButton = new Button("Login");
        loginButton.addActionListener(this);
        panel.add(loginButton, gbc);

        gbc.gridy = 4;
        backToMainButton = new Button("Back to Main Menu");
        backToMainButton.addActionListener(this);
        panel.add(backToMainButton, gbc);

        mainPanel.add(panel, "LOGIN");
    }

    private void createAccountCreationPanel() {
        Panel panel = new Panel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        Label titleLabel = new Label("Create Account", Label.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(20, 0, 20, 0);
        panel.add(titleLabel, gbc);

        gbc.gridwidth = 1;
        gbc.insets = new Insets(5, 10, 5, 10);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new Label("Account Number:"), gbc);
        gbc.gridx = 1;
        accNumField = new TextField(20);
        accNumField.setEditable(true);
        accNumField.setText(String.valueOf(nextAccountNumber));
        accNumField.setEditable(false);
        panel.add(accNumField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(new Label("Account Type:"), gbc);
        gbc.gridx = 1;
        accTypeField = new TextField(20);
        panel.add(accTypeField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(new Label("Customer Name:"), gbc);
        gbc.gridx = 1;
        nameField = new TextField(20);
        panel.add(nameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(new Label("Email:"), gbc);
        gbc.gridx = 1;
        emailField = new TextField(20);
        panel.add(emailField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        panel.add(new Label("Phone:"), gbc);
        gbc.gridx = 1;
        phoneField = new TextField(20);
        panel.add(phoneField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(20, 10, 5, 10);
        createAccBtn = new Button("Create Account");
        createAccBtn.addActionListener(this);
        panel.add(createAccBtn, gbc);

        gbc.gridy = 7;
        backFromCreateBtn = new Button("Back to Main Menu");
        backFromCreateBtn.addActionListener(this);
        panel.add(backFromCreateBtn, gbc);

        mainPanel.add(panel, "CREATE_ACCOUNT");
    }

    private void createTransactionPanel() {
        Panel panel = new Panel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        Label titleLabel = new Label("Transaction", Label.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(20, 0, 30, 0);
        panel.add(titleLabel, gbc);

        gbc.gridwidth = 1;
        gbc.insets = new Insets(5, 10, 5, 10);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new Label("Account Number:"), gbc);
        gbc.gridx = 1;
        accountNumField = new TextField(20);
        panel.add(accountNumField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(new Label("Amount:"), gbc);
        gbc.gridx = 1;
        amountField = new TextField(20);
        panel.add(amountField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(20, 10, 5, 10);
        executeBtn = new Button("Execute");
        executeBtn.addActionListener(this);
        panel.add(executeBtn, gbc);

        gbc.gridy = 4;
        backFromTransactionBtn = new Button("Back to Main Menu");
        backFromTransactionBtn.addActionListener(this);
        panel.add(backFromTransactionBtn, gbc);

        mainPanel.add(panel, "TRANSACTION");
    }

    private void createTransferPanel() {
        Panel panel = new Panel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        Label titleLabel = new Label("Transfer Funds", Label.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(20, 0, 20, 0);
        panel.add(titleLabel, gbc);

        gbc.gridwidth = 1;
        gbc.insets = new Insets(5, 10, 5, 10);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new Label("Source Account:"), gbc);
        gbc.gridx = 1;
        sourceAccField = new TextField(20);
        panel.add(sourceAccField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(new Label("Target Account:"), gbc);
        gbc.gridx = 1;
        targetAccField = new TextField(20);
        panel.add(targetAccField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(new Label("Amount:"), gbc);
        gbc.gridx = 1;
        transferAmountField = new TextField(20);
        panel.add(transferAmountField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(20, 10, 5, 10);
        transferExecuteBtn = new Button("Transfer");
        transferExecuteBtn.addActionListener(this);
        panel.add(transferExecuteBtn, gbc);

        gbc.gridy = 5;
        backFromTransferBtn = new Button("Back to Main Menu");
        backFromTransferBtn.addActionListener(this);
        panel.add(backFromTransferBtn, gbc);

        mainPanel.add(panel, "TRANSFER");
    }

    private void createLoanApplicationPanel() {
        Panel panel = new Panel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        Label titleLabel = new Label("Apply for Loan", Label.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(20, 0, 20, 0);
        panel.add(titleLabel, gbc);

        gbc.gridwidth = 1;
        gbc.insets = new Insets(5, 10, 5, 10);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new Label("Account Number:"), gbc);
        gbc.gridx = 1;
        loanAccField = new TextField(20);
        panel.add(loanAccField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(new Label("Loan Amount:"), gbc);
        gbc.gridx = 1;
        loanAmountField = new TextField(20);
        panel.add(loanAmountField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(new Label("Interest Rate (%):"), gbc);
        gbc.gridx = 1;
        interestRateField = new TextField(20);
        panel.add(interestRateField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(new Label("Loan Term (months):"), gbc);
        gbc.gridx = 1;
        loanTermField = new TextField(20);
        panel.add(loanTermField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(20, 10, 5, 10);
        applyLoanExecuteBtn = new Button("Apply for Loan");
        applyLoanExecuteBtn.addActionListener(this);
        panel.add(applyLoanExecuteBtn, gbc);

        gbc.gridy = 6;
        backFromLoanBtn = new Button("Back to Main Menu");
        backFromLoanBtn.addActionListener(this);
        panel.add(backFromLoanBtn, gbc);

        mainPanel.add(panel, "LOAN_APPLICATION");
    }

    private void createAdminPanel() {
        Panel panel = new Panel(new BorderLayout());

        Label titleLabel = new Label("Admin Panel", Label.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        panel.add(titleLabel, BorderLayout.NORTH);

        Panel centerPanel = new Panel(new BorderLayout());

        adminTextArea = new TextArea(15, 50);
        adminTextArea.setEditable(false);
        centerPanel.add(adminTextArea, BorderLayout.CENTER);

        Panel inputPanel = new Panel(new FlowLayout());
        inputPanel.add(new Label("Loan ID:"));
        loanIdField = new TextField(10);
        inputPanel.add(loanIdField);

        inputPanel.add(new Label("Account Number :"));
        closeAccField = new TextField(10);
        inputPanel.add(closeAccField);

        Panel buttonPanel = new Panel(new GridLayout(3, 3, 5, 5));

        approveBtn = new Button("Approve Loan");
        approveBtn.addActionListener(this);
        buttonPanel.add(approveBtn);

        rejectBtn = new Button("Reject Loan");
        rejectBtn.addActionListener(this);
        buttonPanel.add(rejectBtn);

        listLoansBtn = new Button("List All Loans");
        listLoansBtn.addActionListener(this);
        buttonPanel.add(listLoansBtn);

        listAccountsBtn = new Button("List All Accounts");
        listAccountsBtn.addActionListener(this);
        buttonPanel.add(listAccountsBtn);

        closeAccountBtn = new Button("Close Account");
        closeAccountBtn.addActionListener(this);
        buttonPanel.add(closeAccountBtn);

        generateReportBtn = new Button("Generate Report");
        generateReportBtn.addActionListener(this);
        buttonPanel.add(generateReportBtn);

        logoutBtn = new Button("Logout");
        logoutBtn.addActionListener(this);
        buttonPanel.add(logoutBtn);

        Panel southPanel = new Panel(new BorderLayout());
        southPanel.add(inputPanel, BorderLayout.NORTH);
        southPanel.add(buttonPanel, BorderLayout.CENTER);

        centerPanel.add(southPanel, BorderLayout.SOUTH);
        panel.add(centerPanel, BorderLayout.CENTER);

        mainPanel.add(panel, "ADMIN");
    }

    private void createResultPanel() {
        Panel panel = new Panel(new BorderLayout());

        Label titleLabel = new Label("Result", Label.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        panel.add(titleLabel, BorderLayout.NORTH);

        resultArea = new TextArea(20, 60);
        resultArea.setEditable(false);
        panel.add(resultArea, BorderLayout.CENTER);

        backFromResultBtn = new Button("Back to Main Menu");
        backFromResultBtn.addActionListener(this);
        panel.add(backFromResultBtn, BorderLayout.SOUTH);

        mainPanel.add(panel, "RESULT");
    }

    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        try {
            if (e.getSource() == adminLoginBtn) {
                cardLayout.show(mainPanel, "LOGIN");
            } else if (e.getSource() == loginButton) {
                handleLogin();
            } else if (e.getSource() == createAccountBtn) {
                cardLayout.show(mainPanel, "CREATE_ACCOUNT");
            } else if (e.getSource() == createAccBtn) {
                handleCreateAccount();
            } else if (e.getSource() == depositBtn) {
                setupTransactionPanel("Deposit");
                cardLayout.show(mainPanel, "TRANSACTION");
            } else if (e.getSource() == withdrawBtn) {
                setupTransactionPanel("Withdraw");
                cardLayout.show(mainPanel, "TRANSACTION");
            } else if (e.getSource() == checkBalanceBtn) {
                setupTransactionPanel("Check Balance");
                cardLayout.show(mainPanel, "TRANSACTION");
            } else if (e.getSource() == executeBtn) {
                handleTransaction();
            } else if (e.getSource() == transferBtn) {
                cardLayout.show(mainPanel, "TRANSFER");
            } else if (e.getSource() == transferExecuteBtn) {
                handleTransfer();
            } else if (e.getSource() == applyLoanBtn) {
                cardLayout.show(mainPanel, "LOAN_APPLICATION");
            } else if (e.getSource() == applyLoanExecuteBtn) {
                handleLoanApplication();
            } else if (e.getSource() == approveBtn) {
                handleApproveLoan();
            } else if (e.getSource() == rejectBtn) {
                handleRejectLoan();
            } else if (e.getSource() == listLoansBtn) {
                handleListLoans();
            } else if (e.getSource() == listAccountsBtn) {
                handleListAccounts();
            } else if (e.getSource() == closeAccountBtn) {
                handleCloseAccount();
            } else if (e.getSource() == generateReportBtn) {
                handleGenerateReport();
            } else if (e.getSource() == logoutBtn) {
                cardLayout.show(mainPanel, "MAIN");
            } else if (e.getSource() == exitBtn) {
                System.exit(0);
            } else if (command.equals("Back to Main Menu") || e.getSource() == backToMainButton ||
                    e.getSource() == backFromCreateBtn || e.getSource() == backFromTransactionBtn ||
                    e.getSource() == backFromTransferBtn || e.getSource() == backFromLoanBtn ||
                    e.getSource() == backFromResultBtn) {
                cardLayout.show(mainPanel, "MAIN");
                clearFields();
            }
        } catch (Exception ex) {
            showResult("Error: " + ex.getMessage());
        }
    }

    private void handleLogin() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (ADMIN_USERNAME.equals(username) && ADMIN_PASSWORD.equals(password)) {
            adminTextArea.setText("Login successful! Welcome to Admin Panel.\n");
            cardLayout.show(mainPanel, "ADMIN");
            clearFields();
        } else {
            showResult("Invalid username or password.");
        }
    }

    private void handleCreateAccount() {
        try {
            int accNum = nextAccountNumber++;
            accNumField.setText(String.valueOf(accNum));
            String accType = accTypeField.getText();
            String name = nameField.getText();
            String email = emailField.getText();
            String phone = phoneField.getText();

            Customer newCustomer = new Customer(accNum, name, email, phone);
            bank.addCustomer(newCustomer);

            BankAccount newAccount = new BankAccount(accNum, accType, newCustomer);
            bank.addAccount(newAccount);

            showResult("Account created successfully for: " + name +
                    "\nAccount Number: " + accNum);
            clearFields();
            accNumField.setText(String.valueOf(nextAccountNumber));
        } catch (NumberFormatException e) {
            showResult("Please enter valid details.");
        }
    }


    private String currentTransactionType;

    private void setupTransactionPanel(String type) {
        currentTransactionType = type;
        Label titleLabel = (Label) ((Panel) mainPanel.getComponent(3)).getComponent(0);
        titleLabel.setText(type);

        if (type.equals("Check Balance")) {
            amountField.setVisible(false);
            ((Panel) mainPanel.getComponent(3)).getComponent(4).setVisible(false); // Amount label
        } else {
            amountField.setVisible(true);
            ((Panel) mainPanel.getComponent(3)).getComponent(4).setVisible(true); // Amount label
        }
    }

    private void handleTransaction() {
        try {
            int accountNum = Integer.parseInt(accountNumField.getText());
            BankAccount account = bank.findAccount(accountNum);

            if (account == null) {
                showResult("Account not found!");
                return;
            }

            String result = "";

            if (currentTransactionType.equals("Deposit")) {
                double amount = Double.parseDouble(amountField.getText());
                if (amount > 0) {
                    account.deposit(amount);
                    result = "Deposited: $" + amount + "\nNew Balance: $" + account.checkBalance();
                } else {
                    result = "Deposit amount must be positive.";
                }
            } else if (currentTransactionType.equals("Withdraw")) {
                double amount = Double.parseDouble(amountField.getText());
                if (amount > 0) {
                    double oldBalance = account.checkBalance();
                    if (amount <= oldBalance) {
                        account.withdraw(amount);
                        result = "Withdrawn: $" + amount + "\nNew Balance: $" + account.checkBalance();
                    } else {
                        result = "Insufficient balance! Current balance: $" + oldBalance;
                    }
                } else {
                    result = "Withdrawal amount must be positive.";
                }
            } else if (currentTransactionType.equals("Check Balance")) {
                result = "Current Balance: $" + account.checkBalance();
            }

            showResult(result);
            clearFields();
        } catch (NumberFormatException e) {
            showResult("Please enter valid numbers.");
        }
    }

    private void handleTransfer() {
        try {
            int sourceAccNum = Integer.parseInt(sourceAccField.getText());
            int targetAccNum = Integer.parseInt(targetAccField.getText());
            double amount = Double.parseDouble(transferAmountField.getText());

            BankAccount sourceAccount = bank.findAccount(sourceAccNum);
            BankAccount targetAccount = bank.findAccount(targetAccNum);

            if (sourceAccount == null) {
                showResult("Source account not found!");
                return;
            }

            if (targetAccount == null) {
                showResult("Target account not found!");
                return;
            }

            if (amount > 0) {
                if (amount <= sourceAccount.checkBalance()) {
                    sourceAccount.transferFunds(targetAccount, amount);
                    showResult("Transfer successful!\nTransferred: $" + amount +
                            "\nFrom Account: " + sourceAccNum +
                            "\nTo Account: " + targetAccNum);
                } else {
                    showResult("Insufficient funds in source account!");
                }
            } else {
                showResult("Transfer amount must be positive.");
            }

            clearFields();
        } catch (NumberFormatException e) {
            showResult("Please enter valid numbers.");
        }
    }

    private void handleLoanApplication() {
        try {
            int accountNum = Integer.parseInt(loanAccField.getText());
            double loanAmount = Double.parseDouble(loanAmountField.getText());
            double interestRate = Double.parseDouble(interestRateField.getText());
            int loanTerm = Integer.parseInt(loanTermField.getText());

            BankAccount account = bank.findAccount(accountNum);
            if (account == null) {
                showResult("Account not found!");
                return;
            }

            Loan newLoan = new Loan(account, loanAmount, interestRate, loanTerm);
            bank.addLoans(newLoan);

            showResult("Loan application submitted successfully!\n" +
                    "Loan ID: " + newLoan.getLoanId() + "\n" +
                    "Amount: $" + loanAmount + "\n" +
                    "Interest Rate: " + interestRate + "%\n" +
                    "Term: " + loanTerm + " months\n" +
                    "Status: Pending");
            clearFields();
        } catch (NumberFormatException e) {
            showResult("Please enter valid numbers.");
        }
    }

    private void handleApproveLoan() {
        try {
            int loanId = Integer.parseInt(loanIdField.getText());
            Loan loan = bank.findLoan(loanId);

            if (loan != null) {
                bank.approveLoan(loan);
                adminTextArea.append("Loan " + loanId + " approved successfully.\n");
            } else {
                adminTextArea.append("Loan " + loanId + " not found!\n");
            }
            loanIdField.setText("");
        } catch (NumberFormatException e) {
            adminTextArea.append("Please enter a valid loan ID.\n");
        }
    }

    private void handleRejectLoan() {
        try {
            int loanId = Integer.parseInt(loanIdField.getText());
            Loan loan = bank.findLoan(loanId);

            if (loan != null) {
                bank.rejectLoan(loan);
                adminTextArea.append("Loan " + loanId + " rejected successfully.\n");
            } else {
                adminTextArea.append("Loan " + loanId + " not found!\n");
            }
            loanIdField.setText("");
        } catch (NumberFormatException e) {
            adminTextArea.append("Please enter a valid loan ID.\n");
        }
    }

    private void handleListLoans() {
        adminTextArea.setText("");
        // Capture the output from bank.listAllLoans()
        java.io.ByteArrayOutputStream baos = new java.io.ByteArrayOutputStream();
        java.io.PrintStream ps = new java.io.PrintStream(baos);
        java.io.PrintStream old = System.out;
        System.setOut(ps);

        bank.listAllLoans();

        System.out.flush();
        System.setOut(old);
        adminTextArea.setText(baos.toString());
    }

    private void handleListAccounts() {
        adminTextArea.setText("");
        java.io.ByteArrayOutputStream baos = new java.io.ByteArrayOutputStream();
        java.io.PrintStream ps = new java.io.PrintStream(baos);
        java.io.PrintStream old = System.out;
        System.setOut(ps);

        bank.listAllAccounts();

        System.out.flush();
        System.setOut(old);
        adminTextArea.setText(baos.toString());
    }

    private void handleCloseAccount() {
        try {
            int accountNum = Integer.parseInt(closeAccField.getText());
            BankAccount account = bank.findAccount(accountNum);

            if (account != null) {
                account.closeAccount();
                bank.removeAccount(accountNum);
                adminTextArea.append("Account " + accountNum + " closed successfully.\n");
            } else {
                adminTextArea.append("Account " + accountNum + " not found!\n");
            }
            closeAccField.setText("");
        } catch (NumberFormatException e) {
            adminTextArea.append("Please enter a valid account number.\n");
        }
    }


    private void handleGenerateReport() {
        java.io.ByteArrayOutputStream baos = new java.io.ByteArrayOutputStream();
        java.io.PrintStream ps = new java.io.PrintStream(baos);
        java.io.PrintStream old = System.out;
        System.setOut(ps);

        bank.generateReport();

        System.out.flush();
        System.setOut(old);
        adminTextArea.setText(baos.toString());
    }

    private void showResult(String message) {
        resultArea.setText(message);
        cardLayout.show(mainPanel, "RESULT");
    }

    private void clearFields() {
        // Clear login fields
        usernameField.setText("");
        passwordField.setText("");

        // Clear account creation fields
        accNumField.setText("");
        accTypeField.setText("");
        nameField.setText("");
        emailField.setText("");
        phoneField.setText("");

        // Clear transaction fields
        accountNumField.setText("");
        amountField.setText("");

        // Clear transfer fields
        sourceAccField.setText("");
        targetAccField.setText("");
        transferAmountField.setText("");

        // Clear loan fields
        loanAccField.setText("");
        loanAmountField.setText("");
        interestRateField.setText("");
        loanTermField.setText("");

        // Clear admin fields
        loanIdField.setText("");
    }

    public static void main(String[] args) {
        new BankingSystemGUI();
    }
}