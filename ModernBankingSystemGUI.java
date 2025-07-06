import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class ModernBankingSystemGUI extends JFrame {
    private Bank bank;
    private CardLayout cardLayout;
    private JPanel mainPanel;
    
    // Color scheme
    private static final Color PRIMARY_COLOR = new Color(41, 128, 185);
    private static final Color SECONDARY_COLOR = new Color(52, 152, 219);
    private static final Color SUCCESS_COLOR = new Color(39, 174, 96);
    private static final Color DANGER_COLOR = new Color(231, 76, 60);
    private static final Color WARNING_COLOR = new Color(243, 156, 18);
    private static final Color LIGHT_GRAY = new Color(236, 240, 241);
    private static final Color DARK_GRAY = new Color(52, 73, 94);
    private static final Color WHITE = Color.WHITE;
    
    // Login components
    private JTextField usernameField;
    private JPasswordField passwordField;
    
    // Main menu components
    private static int nextAccountNumber = 1000;
    
    // Account creation components
    private JTextField accNumField, nameField, emailField, phoneField;
    private JComboBox<String> accTypeChoice;
    
    // Transaction components
    private JTextField accountNumField, amountField;
    private String currentTransactionType;
    
    // Transfer components
    private JTextField sourceAccField, targetAccField, transferAmountField;
    
    // Loan application components
    private JTextField loanAccField, loanAmountField, interestRateField, loanTermField;
    
    // Admin components
    private JTextArea adminTextArea;
    private JTextField loanIdField, closeAccField;
    
    // Result display
    private JTextArea resultArea;
    
    private static final String ADMIN_USERNAME = "admin";
    private static final String ADMIN_PASSWORD = "admin3103";
    
    public ModernBankingSystemGUI() {
        bank = new Bank();
        setupGUI();
    }
    
    private void setupGUI() {
        setTitle("Modern Banking System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 700);
        setLocationRelativeTo(null);
        
        // Set look and feel
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeel());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        
        createMainMenu();
        createLoginPanel();
        createAccountCreationPanel();
        createTransactionPanel();
        createTransferPanel();
        createLoanApplicationPanel();
        createAdminPanel();
        createResultPanel();
        
        add(mainPanel);
        setVisible(true);
    }
    
    private void createMainMenu() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(LIGHT_GRAY);
        
        // Header
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(PRIMARY_COLOR);
        headerPanel.setPreferredSize(new Dimension(0, 100));
        
        JLabel titleLabel = new JLabel("Modern Banking System", JLabel.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 32));
        titleLabel.setForeground(WHITE);
        headerPanel.add(titleLabel);
        
        // Main content
        JPanel contentPanel = new JPanel(new GridBagLayout());
        contentPanel.setBackground(LIGHT_GRAY);
        contentPanel.setBorder(new EmptyBorder(40, 40, 40, 40));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        // Create buttons with modern styling
        JButton adminLoginBtn = createStyledButton("Admin Login", PRIMARY_COLOR);
        JButton createAccountBtn = createStyledButton("Create Account", SUCCESS_COLOR);
        JButton depositBtn = createStyledButton("Deposit", new Color(46, 204, 113));
        JButton withdrawBtn = createStyledButton("Withdraw", WARNING_COLOR);
        JButton checkBalanceBtn = createStyledButton("Check Balance", new Color(155, 89, 182));
        JButton transferBtn = createStyledButton("Transfer Funds", new Color(52, 152, 219));
        JButton applyLoanBtn = createStyledButton("Apply for Loan", new Color(230, 126, 34));
        JButton exitBtn = createStyledButton("Exit", DANGER_COLOR);
        
        // Add action listeners
        adminLoginBtn.addActionListener(e -> cardLayout.show(mainPanel, "LOGIN"));
        createAccountBtn.addActionListener(e -> cardLayout.show(mainPanel, "CREATE_ACCOUNT"));
        depositBtn.addActionListener(e -> {
            setupTransactionPanel("Deposit");
            cardLayout.show(mainPanel, "TRANSACTION");
        });
        withdrawBtn.addActionListener(e -> {
            setupTransactionPanel("Withdraw");
            cardLayout.show(mainPanel, "TRANSACTION");
        });
        checkBalanceBtn.addActionListener(e -> {
            setupTransactionPanel("Check Balance");
            cardLayout.show(mainPanel, "TRANSACTION");
        });
        transferBtn.addActionListener(e -> cardLayout.show(mainPanel, "TRANSFER"));
        applyLoanBtn.addActionListener(e -> cardLayout.show(mainPanel, "LOAN_APPLICATION"));
        exitBtn.addActionListener(e -> System.exit(0));
        
        // Layout buttons in grid
        gbc.gridx = 0; gbc.gridy = 0;
        contentPanel.add(adminLoginBtn, gbc);
        gbc.gridx = 1;
        contentPanel.add(createAccountBtn, gbc);
        
        gbc.gridx = 0; gbc.gridy = 1;
        contentPanel.add(depositBtn, gbc);
        gbc.gridx = 1;
        contentPanel.add(withdrawBtn, gbc);
        
        gbc.gridx = 0; gbc.gridy = 2;
        contentPanel.add(checkBalanceBtn, gbc);
        gbc.gridx = 1;
        contentPanel.add(transferBtn, gbc);
        
        gbc.gridx = 0; gbc.gridy = 3;
        contentPanel.add(applyLoanBtn, gbc);
        gbc.gridx = 1;
        contentPanel.add(exitBtn, gbc);
        
        panel.add(headerPanel, BorderLayout.NORTH);
        panel.add(contentPanel, BorderLayout.CENTER);
        
        mainPanel.add(panel, "MAIN");
    }
    
    private void createLoginPanel() {
        JPanel panel = createFormPanel("Admin Login");
        
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(WHITE);
        formPanel.setBorder(createRoundedBorder());
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 20, 15, 20);
        gbc.anchor = GridBagConstraints.WEST;
        
        // Username
        gbc.gridx = 0; gbc.gridy = 0;
        formPanel.add(createLabel("Username:"), gbc);
        gbc.gridx = 1;
        usernameField = createStyledTextField();
        formPanel.add(usernameField, gbc);
        
        // Password
        gbc.gridx = 0; gbc.gridy = 1;
        formPanel.add(createLabel("Password:"), gbc);
        gbc.gridx = 1;
        passwordField = new JPasswordField(20);
        styleTextField(passwordField);
        formPanel.add(passwordField, gbc);
        
        // Buttons
        gbc.gridx = 0; gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(25, 20, 10, 20);
        
        JButton loginButton = createStyledButton("Login", SUCCESS_COLOR);
        loginButton.addActionListener(this::handleLogin);
        formPanel.add(loginButton, gbc);
        
        gbc.gridy = 3;
        gbc.insets = new Insets(10, 20, 20, 20);
        JButton backButton = createStyledButton("Back to Main Menu", DARK_GRAY);
        backButton.addActionListener(e -> {
            cardLayout.show(mainPanel, "MAIN");
            clearFields();
        });
        formPanel.add(backButton, gbc);
        
        panel.add(formPanel, BorderLayout.CENTER);
        mainPanel.add(panel, "LOGIN");
    }
    
    private void createAccountCreationPanel() {
        JPanel panel = createFormPanel("Create New Account");
        
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(WHITE);
        formPanel.setBorder(createRoundedBorder());
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 20, 10, 20);
        gbc.anchor = GridBagConstraints.WEST;
        
        // Account Number
        gbc.gridx = 0; gbc.gridy = 0;
        formPanel.add(createLabel("Account Number:"), gbc);
        gbc.gridx = 1;
        accNumField = createStyledTextField();
        accNumField.setText(String.valueOf(nextAccountNumber));
        accNumField.setEditable(false);
        formPanel.add(accNumField, gbc);
        
        // Account Type
        gbc.gridx = 0; gbc.gridy = 1;
        formPanel.add(createLabel("Account Type:"), gbc);
        gbc.gridx = 1;
        accTypeChoice = new JComboBox<>(new String[]{"Savings", "Current"});
        styleComboBox(accTypeChoice);
        formPanel.add(accTypeChoice, gbc);
        
        // Customer Name
        gbc.gridx = 0; gbc.gridy = 2;
        formPanel.add(createLabel("Customer Name:"), gbc);
        gbc.gridx = 1;
        nameField = createStyledTextField();
        formPanel.add(nameField, gbc);
        
        // Email
        gbc.gridx = 0; gbc.gridy = 3;
        formPanel.add(createLabel("Email:"), gbc);
        gbc.gridx = 1;
        emailField = createStyledTextField();
        formPanel.add(emailField, gbc);
        
        // Phone
        gbc.gridx = 0; gbc.gridy = 4;
        formPanel.add(createLabel("Phone:"), gbc);
        gbc.gridx = 1;
        phoneField = createStyledTextField();
        formPanel.add(phoneField, gbc);
        
        // Buttons
        gbc.gridx = 0; gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(25, 20, 10, 20);
        
        JButton createBtn = createStyledButton("Create Account", SUCCESS_COLOR);
        createBtn.addActionListener(this::handleCreateAccount);
        formPanel.add(createBtn, gbc);
        
        gbc.gridy = 6;
        gbc.insets = new Insets(10, 20, 20, 20);
        JButton backButton = createStyledButton("Back to Main Menu", DARK_GRAY);
        backButton.addActionListener(e -> {
            cardLayout.show(mainPanel, "MAIN");
            clearFields();
        });
        formPanel.add(backButton, gbc);
        
        panel.add(formPanel, BorderLayout.CENTER);
        mainPanel.add(panel, "CREATE_ACCOUNT");
    }
    
    private void createTransactionPanel() {
        JPanel panel = createFormPanel("Transaction");
        
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(WHITE);
        formPanel.setBorder(createRoundedBorder());
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 20, 15, 20);
        gbc.anchor = GridBagConstraints.WEST;
        
        // Account Number
        gbc.gridx = 0; gbc.gridy = 0;
        formPanel.add(createLabel("Account Number:"), gbc);
        gbc.gridx = 1;
        accountNumField = createStyledTextField();
        formPanel.add(accountNumField, gbc);
        
        // Amount
        gbc.gridx = 0; gbc.gridy = 1;
        JLabel amountLabel = createLabel("Amount:");
        formPanel.add(amountLabel, gbc);
        gbc.gridx = 1;
        amountField = createStyledTextField();
        formPanel.add(amountField, gbc);
        
        // Buttons
        gbc.gridx = 0; gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(25, 20, 10, 20);
        
        JButton executeBtn = createStyledButton("Execute", PRIMARY_COLOR);
        executeBtn.addActionListener(this::handleTransaction);
        formPanel.add(executeBtn, gbc);
        
        gbc.gridy = 3;
        gbc.insets = new Insets(10, 20, 20, 20);
        JButton backButton = createStyledButton("Back to Main Menu", DARK_GRAY);
        backButton.addActionListener(e -> {
            cardLayout.show(mainPanel, "MAIN");
            clearFields();
        });
        formPanel.add(backButton, gbc);
        
        panel.add(formPanel, BorderLayout.CENTER);
        mainPanel.add(panel, "TRANSACTION");
    }
    
    private void createTransferPanel() {
        JPanel panel = createFormPanel("Transfer Funds");
        
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(WHITE);
        formPanel.setBorder(createRoundedBorder());
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 20, 15, 20);
        gbc.anchor = GridBagConstraints.WEST;
        
        // Source Account
        gbc.gridx = 0; gbc.gridy = 0;
        formPanel.add(createLabel("Source Account:"), gbc);
        gbc.gridx = 1;
        sourceAccField = createStyledTextField();
        formPanel.add(sourceAccField, gbc);
        
        // Target Account
        gbc.gridx = 0; gbc.gridy = 1;
        formPanel.add(createLabel("Target Account:"), gbc);
        gbc.gridx = 1;
        targetAccField = createStyledTextField();
        formPanel.add(targetAccField, gbc);
        
        // Amount
        gbc.gridx = 0; gbc.gridy = 2;
        formPanel.add(createLabel("Amount:"), gbc);
        gbc.gridx = 1;
        transferAmountField = createStyledTextField();
        formPanel.add(transferAmountField, gbc);
        
        // Buttons
        gbc.gridx = 0; gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(25, 20, 10, 20);
        
        JButton transferBtn = createStyledButton("Transfer", SUCCESS_COLOR);
        transferBtn.addActionListener(this::handleTransfer);
        formPanel.add(transferBtn, gbc);
        
        gbc.gridy = 4;
        gbc.insets = new Insets(10, 20, 20, 20);
        JButton backButton = createStyledButton("Back to Main Menu", DARK_GRAY);
        backButton.addActionListener(e -> {
            cardLayout.show(mainPanel, "MAIN");
            clearFields();
        });
        formPanel.add(backButton, gbc);
        
        panel.add(formPanel, BorderLayout.CENTER);
        mainPanel.add(panel, "TRANSFER");
    }
    
    private void createLoanApplicationPanel() {
        JPanel panel = createFormPanel("Apply for Loan");
        
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(WHITE);
        formPanel.setBorder(createRoundedBorder());
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 20, 10, 20);
        gbc.anchor = GridBagConstraints.WEST;
        
        // Account Number
        gbc.gridx = 0; gbc.gridy = 0;
        formPanel.add(createLabel("Account Number:"), gbc);
        gbc.gridx = 1;
        loanAccField = createStyledTextField();
        formPanel.add(loanAccField, gbc);
        
        // Loan Amount
        gbc.gridx = 0; gbc.gridy = 1;
        formPanel.add(createLabel("Loan Amount:"), gbc);
        gbc.gridx = 1;
        loanAmountField = createStyledTextField();
        formPanel.add(loanAmountField, gbc);
        
        // Interest Rate
        gbc.gridx = 0; gbc.gridy = 2;
        formPanel.add(createLabel("Interest Rate (%):"), gbc);
        gbc.gridx = 1;
        interestRateField = createStyledTextField();
        formPanel.add(interestRateField, gbc);
        
        // Loan Term
        gbc.gridx = 0; gbc.gridy = 3;
        formPanel.add(createLabel("Loan Term (months):"), gbc);
        gbc.gridx = 1;
        loanTermField = createStyledTextField();
        formPanel.add(loanTermField, gbc);
        
        // Buttons
        gbc.gridx = 0; gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(25, 20, 10, 20);
        
        JButton applyBtn = createStyledButton("Apply for Loan", WARNING_COLOR);
        applyBtn.addActionListener(this::handleLoanApplication);
        formPanel.add(applyBtn, gbc);
        
        gbc.gridy = 5;
        gbc.insets = new Insets(10, 20, 20, 20);
        JButton backButton = createStyledButton("Back to Main Menu", DARK_GRAY);
        backButton.addActionListener(e -> {
            cardLayout.show(mainPanel, "MAIN");
            clearFields();
        });
        formPanel.add(backButton, gbc);
        
        panel.add(formPanel, BorderLayout.CENTER);
        mainPanel.add(panel, "LOAN_APPLICATION");
    }
    
    private void createAdminPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(LIGHT_GRAY);
        
        // Header
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(DARK_GRAY);
        headerPanel.setPreferredSize(new Dimension(0, 80));
        
        JLabel titleLabel = new JLabel("Admin Panel", JLabel.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titleLabel.setForeground(WHITE);
        headerPanel.add(titleLabel);
        
        // Main content
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        contentPanel.setBackground(LIGHT_GRAY);
        
        // Text area for output
        adminTextArea = new JTextArea(15, 50);
        adminTextArea.setEditable(false);
        adminTextArea.setFont(new Font("Consolas", Font.PLAIN, 12));
        adminTextArea.setBackground(WHITE);
        adminTextArea.setBorder(new EmptyBorder(10, 10, 10, 10));
        
        JScrollPane scrollPane = new JScrollPane(adminTextArea);
        scrollPane.setBorder(createRoundedBorder());
        
        // Input panel
        JPanel inputPanel = new JPanel(new FlowLayout());
        inputPanel.setBackground(LIGHT_GRAY);
        
        inputPanel.add(createLabel("Loan ID:"));
        loanIdField = createStyledTextField(10);
        inputPanel.add(loanIdField);
        
        inputPanel.add(createLabel("Account Number:"));
        closeAccField = createStyledTextField(10);
        inputPanel.add(closeAccField);
        
        // Button panel
        JPanel buttonPanel = new JPanel(new GridLayout(3, 3, 10, 10));
        buttonPanel.setBackground(LIGHT_GRAY);
        buttonPanel.setBorder(new EmptyBorder(20, 0, 0, 0));
        
        JButton approveBtn = createStyledButton("Approve Loan", SUCCESS_COLOR);
        approveBtn.addActionListener(this::handleApproveLoan);
        buttonPanel.add(approveBtn);
        
        JButton rejectBtn = createStyledButton("Reject Loan", DANGER_COLOR);
        rejectBtn.addActionListener(this::handleRejectLoan);
        buttonPanel.add(rejectBtn);
        
        JButton listLoansBtn = createStyledButton("List All Loans", PRIMARY_COLOR);
        listLoansBtn.addActionListener(e -> handleListLoans());
        buttonPanel.add(listLoansBtn);
        
        JButton listAccountsBtn = createStyledButton("List All Accounts", SECONDARY_COLOR);
        listAccountsBtn.addActionListener(e -> handleListAccounts());
        buttonPanel.add(listAccountsBtn);
        
        JButton closeAccountBtn = createStyledButton("Close Account", WARNING_COLOR);
        closeAccountBtn.addActionListener(this::handleCloseAccount);
        buttonPanel.add(closeAccountBtn);
        
        JButton generateReportBtn = createStyledButton("Generate Report", new Color(155, 89, 182));
        generateReportBtn.addActionListener(e -> handleGenerateReport());
        buttonPanel.add(generateReportBtn);
        
        JButton logoutBtn = createStyledButton("Logout", DARK_GRAY);
        logoutBtn.addActionListener(e -> cardLayout.show(mainPanel, "MAIN"));
        buttonPanel.add(logoutBtn);
        
        contentPanel.add(scrollPane, BorderLayout.CENTER);
        
        JPanel southPanel = new JPanel(new BorderLayout());
        southPanel.setBackground(LIGHT_GRAY);
        southPanel.add(inputPanel, BorderLayout.NORTH);
        southPanel.add(buttonPanel, BorderLayout.CENTER);
        
        contentPanel.add(southPanel, BorderLayout.SOUTH);
        
        panel.add(headerPanel, BorderLayout.NORTH);
        panel.add(contentPanel, BorderLayout.CENTER);
        
        mainPanel.add(panel, "ADMIN");
    }
    
    private void createResultPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(LIGHT_GRAY);
        
        // Header
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(SUCCESS_COLOR);
        headerPanel.setPreferredSize(new Dimension(0, 80));
        
        JLabel titleLabel = new JLabel("Transaction Result", JLabel.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titleLabel.setForeground(WHITE);
        headerPanel.add(titleLabel);
        
        // Content
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(30, 30, 30, 30));
        contentPanel.setBackground(LIGHT_GRAY);
        
        resultArea = new JTextArea(20, 60);
        resultArea.setEditable(false);
        resultArea.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        resultArea.setBackground(WHITE);
        resultArea.setBorder(new EmptyBorder(20, 20, 20, 20));
        
        JScrollPane scrollPane = new JScrollPane(resultArea);
        scrollPane.setBorder(createRoundedBorder());
        
        JButton backButton = createStyledButton("Back to Main Menu", PRIMARY_COLOR);
        backButton.addActionListener(e -> cardLayout.show(mainPanel, "MAIN"));
        
        contentPanel.add(scrollPane, BorderLayout.CENTER);
        contentPanel.add(backButton, BorderLayout.SOUTH);
        
        panel.add(headerPanel, BorderLayout.NORTH);
        panel.add(contentPanel, BorderLayout.CENTER);
        
        mainPanel.add(panel, "RESULT");
    }
    
    // Helper methods for styling
    private JPanel createFormPanel(String title) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(LIGHT_GRAY);
        
        // Header
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(PRIMARY_COLOR);
        headerPanel.setPreferredSize(new Dimension(0, 80));
        
        JLabel titleLabel = new JLabel(title, JLabel.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titleLabel.setForeground(WHITE);
        headerPanel.add(titleLabel);
        
        // Content wrapper
        JPanel contentWrapper = new JPanel(new GridBagLayout());
        contentWrapper.setBackground(LIGHT_GRAY);
        contentWrapper.setBorder(new EmptyBorder(40, 40, 40, 40));
        
        panel.add(headerPanel, BorderLayout.NORTH);
        panel.add(contentWrapper, BorderLayout.CENTER);
        
        return panel;
    }
    
    private JButton createStyledButton(String text, Color backgroundColor) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setBackground(backgroundColor);
        button.setForeground(WHITE);
        button.setBorder(BorderFactory.createEmptyBorder(12, 24, 12, 24));
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setPreferredSize(new Dimension(200, 45));
        
        // Hover effect
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(backgroundColor.darker());
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(backgroundColor);
            }
        });
        
        return button;
    }
    
    private JTextField createStyledTextField() {
        return createStyledTextField(20);
    }
    
    private JTextField createStyledTextField(int columns) {
        JTextField field = new JTextField(columns);
        styleTextField(field);
        return field;
    }
    
    private void styleTextField(JTextField field) {
        field.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        field.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(189, 195, 199), 1),
            BorderFactory.createEmptyBorder(8, 12, 8, 12)
        ));
        field.setPreferredSize(new Dimension(field.getPreferredSize().width, 35));
    }
    
    private void styleComboBox(JComboBox<String> comboBox) {
        comboBox.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        comboBox.setPreferredSize(new Dimension(200, 35));
        comboBox.setBackground(WHITE);
    }
    
    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Segoe UI", Font.BOLD, 14));
        label.setForeground(DARK_GRAY);
        return label;
    }
    
    private Border createRoundedBorder() {
        return BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(189, 195, 199), 1),
            BorderFactory.createEmptyBorder(20, 20, 20, 20)
        );
    }
    
    // Event handlers
    private void handleLogin(ActionEvent e) {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());
        
        if (ADMIN_USERNAME.equals(username) && ADMIN_PASSWORD.equals(password)) {
            adminTextArea.setText("Login successful! Welcome to Admin Panel.\n");
            cardLayout.show(mainPanel, "ADMIN");
            clearFields();
        } else {
            showResult("Invalid username or password.");
        }
    }
    
    private void handleCreateAccount(ActionEvent e) {
        try {
            int accNum = nextAccountNumber++;
            String accType = (String) accTypeChoice.getSelectedItem();
            String name = nameField.getText().trim();
            String email = emailField.getText().trim();
            String phone = phoneField.getText().trim();
            
            if (name.isEmpty() || email.isEmpty() || phone.isEmpty()) {
                showResult("Please fill in all fields.");
                return;
            }
            
            Customer newCustomer = new Customer(accNum, name, email, phone);
            bank.addCustomer(newCustomer);
            
            BankAccount newAccount = new BankAccount(accNum, accType, newCustomer);
            bank.addAccount(newAccount);
            
            showResult("✅ Account created successfully!\n\n" +
                    "Customer: " + name + "\n" +
                    "Account Number: " + accNum + "\n" +
                    "Account Type: " + accType + "\n" +
                    "Email: " + email + "\n" +
                    "Phone: " + phone);
            clearFields();
            accNumField.setText(String.valueOf(nextAccountNumber));
        } catch (Exception ex) {
            showResult("Error creating account: " + ex.getMessage());
        }
    }
    
    private void setupTransactionPanel(String type) {
        currentTransactionType = type;
        // Update the title dynamically if needed
    }
    
    private void handleTransaction(ActionEvent e) {
        try {
            int accountNum = Integer.parseInt(accountNumField.getText());
            BankAccount account = bank.findAccount(accountNum);
            
            if (account == null) {
                showResult("❌ Account not found!");
                return;
            }
            
            String result = "";
            
            if (currentTransactionType.equals("Deposit")) {
                double amount = Double.parseDouble(amountField.getText());
                if (amount > 0) {
                    account.deposit(amount);
                    result = "✅ Deposit Successful!\n\n" +
                            "Amount Deposited: $" + String.format("%.2f", amount) + "\n" +
                            "New Balance: $" + String.format("%.2f", account.checkBalance());
                } else {
                    result = "❌ Deposit amount must be positive.";
                }
            } else if (currentTransactionType.equals("Withdraw")) {
                double amount = Double.parseDouble(amountField.getText());
                if (amount > 0) {
                    double oldBalance = account.checkBalance();
                    if (amount <= oldBalance) {
                        account.withdraw(amount);
                        result = "✅ Withdrawal Successful!\n\n" +
                                "Amount Withdrawn: $" + String.format("%.2f", amount) + "\n" +
                                "New Balance: $" + String.format("%.2f", account.checkBalance());
                    } else {
                        result = "❌ Insufficient balance!\n\n" +
                                "Current Balance: $" + String.format("%.2f", oldBalance) + "\n" +
                                "Requested Amount: $" + String.format("%.2f", amount);
                    }
                } else {
                    result = "❌ Withdrawal amount must be positive.";
                }
            } else if (currentTransactionType.equals("Check Balance")) {
                result = "💰 Account Balance\n\n" +
                        "Account Number: " + accountNum + "\n" +
                        "Current Balance: $" + String.format("%.2f", account.checkBalance());
            }
            
            showResult(result);
            clearFields();
        } catch (NumberFormatException ex) {
            showResult("❌ Please enter valid numbers.");
        }
    }
    
    private void handleTransfer(ActionEvent e) {
        try {
            int sourceAccNum = Integer.parseInt(sourceAccField.getText());
            int targetAccNum = Integer.parseInt(targetAccField.getText());
            double amount = Double.parseDouble(transferAmountField.getText());
            
            BankAccount sourceAccount = bank.findAccount(sourceAccNum);
            BankAccount targetAccount = bank.findAccount(targetAccNum);
            
            if (sourceAccount == null) {
                showResult("❌ Source account not found!");
                return;
            }
            
            if (targetAccount == null) {
                showResult("❌ Target account not found!");
                return;
            }
            
            if (amount > 0) {
                if (amount <= sourceAccount.checkBalance()) {
                    sourceAccount.transferFunds(targetAccount, amount);
                    showResult("✅ Transfer Successful!\n\n" +
                            "Amount Transferred: $" + String.format("%.2f", amount) + "\n" +
                            "From Account: " + sourceAccNum + "\n" +
                            "To Account: " + targetAccNum + "\n\n" +
                            "Source Account Balance: $" + String.format("%.2f", sourceAccount.checkBalance()) + "\n" +
                            "Target Account Balance: $" + String.format("%.2f", targetAccount.checkBalance()));
                } else {
                    showResult("❌ Insufficient funds in source account!\n\n" +
                            "Available Balance: $" + String.format("%.2f", sourceAccount.checkBalance()) + "\n" +
                            "Requested Amount: $" + String.format("%.2f", amount));
                }
            } else {
                showResult("❌ Transfer amount must be positive.");
            }
            
            clearFields();
        } catch (NumberFormatException ex) {
            showResult("❌ Please enter valid numbers.");
        }
    }
    
    private void handleLoanApplication(ActionEvent e) {
        try {
            int accountNum = Integer.parseInt(loanAccField.getText());
            double loanAmount = Double.parseDouble(loanAmountField.getText());
            double interestRate = Double.parseDouble(interestRateField.getText());
            int loanTerm = Integer.parseInt(loanTermField.getText());
            
            BankAccount account = bank.findAccount(accountNum);
            if (account == null) {
                showResult("❌ Account not found!");
                return;
            }
            
            Loan newLoan = new Loan(account, loanAmount, interestRate, loanTerm);
            bank.addLoans(newLoan);
            
            showResult("✅ Loan Application Submitted!\n\n" +
                    "Loan ID: " + newLoan.getLoanId() + "\n" +
                    "Account Number: " + accountNum + "\n" +
                    "Loan Amount: $" + String.format("%.2f", loanAmount) + "\n" +
                    "Interest Rate: " + interestRate + "%\n" +
                    "Loan Term: " + loanTerm + " months\n" +
                    "Status: Pending Approval\n\n" +
                    "Your loan application has been submitted for review.");
            clearFields();
        } catch (NumberFormatException ex) {
            showResult("❌ Please enter valid numbers.");
        }
    }
    
    private void handleApproveLoan(ActionEvent e) {
        try {
            int loanId = Integer.parseInt(loanIdField.getText());
            Loan loan = bank.findLoan(loanId);
            
            if (loan != null) {
                bank.approveLoan(loan);
                adminTextArea.append("✅ Loan " + loanId + " approved successfully.\n");
            } else {
                adminTextArea.append("❌ Loan " + loanId + " not found!\n");
            }
            loanIdField.setText("");
        } catch (NumberFormatException ex) {
            adminTextArea.append("❌ Please enter a valid loan ID.\n");
        }
    }
    
    private void handleRejectLoan(ActionEvent e) {
        try {
            int loanId = Integer.parseInt(loanIdField.getText());
            Loan loan = bank.findLoan(loanId);
            
            if (loan != null) {
                bank.rejectLoan(loan);
                adminTextArea.append("❌ Loan " + loanId + " rejected successfully.\n");
            } else {
                adminTextArea.append("❌ Loan " + loanId + " not found!\n");
            }
            loanIdField.setText("");
        } catch (NumberFormatException ex) {
            adminTextArea.append("❌ Please enter a valid loan ID.\n");
        }
    }
    
    private void handleListLoans() {
        adminTextArea.setText("");
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        PrintStream old = System.out;
        System.setOut(ps);
        
        bank.listAllLoans();
        
        System.out.flush();
        System.setOut(old);
        adminTextArea.setText(baos.toString());
    }
    
    private void handleListAccounts() {
        adminTextArea.setText("");
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        PrintStream old = System.out;
        System.setOut(ps);
        
        bank.listAllAccounts();
        
        System.out.flush();
        System.setOut(old);
        adminTextArea.setText(baos.toString());
    }
    
    private void handleCloseAccount(ActionEvent e) {
        try {
            int accountNum = Integer.parseInt(closeAccField.getText());
            BankAccount account = bank.findAccount(accountNum);
            
            if (account != null) {
                account.closeAccount();
                bank.removeAccount(accountNum);
                adminTextArea.append("✅ Account " + accountNum + " closed successfully.\n");
            } else {
                adminTextArea.append("❌ Account " + accountNum + " not found!\n");
            }
            closeAccField.setText("");
        } catch (NumberFormatException ex) {
            adminTextArea.append("❌ Please enter a valid account number.\n");
        }
    }
    
    private void handleGenerateReport() {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        PrintStream old = System.out;
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
        if (usernameField != null) usernameField.setText("");
        if (passwordField != null) passwordField.setText("");
        
        // Clear account creation fields
        if (accTypeChoice != null) accTypeChoice.setSelectedIndex(0);
        if (nameField != null) nameField.setText("");
        if (emailField != null) emailField.setText("");
        if (phoneField != null) phoneField.setText("");
        
        // Clear transaction fields
        if (accountNumField != null) accountNumField.setText("");
        if (amountField != null) amountField.setText("");
        
        // Clear transfer fields
        if (sourceAccField != null) sourceAccField.setText("");
        if (targetAccField != null) targetAccField.setText("");
        if (transferAmountField != null) transferAmountField.setText("");
        
        // Clear loan fields
        if (loanAccField != null) loanAccField.setText("");
        if (loanAmountField != null) loanAmountField.setText("");
        if (interestRateField != null) interestRateField.setText("");
        if (loanTermField != null) loanTermField.setText("");
        
        // Clear admin fields
        if (loanIdField != null) loanIdField.setText("");
        if (closeAccField != null) closeAccField.setText("");
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ModernBankingSystemGUI());
    }
}