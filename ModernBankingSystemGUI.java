import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;

public class ModernBankingSystemGUI extends JFrame {
    private Bank bank;
    private CardLayout cardLayout;
    private JPanel mainPanel;

    private static final Color PRIMARY_COLOR = new Color(41, 128, 185);
    private static final Color SECONDARY_COLOR = new Color(52, 152, 219);
    private static final Color SUCCESS_COLOR = new Color(39, 174, 96);
    private static final Color DANGER_COLOR = new Color(231, 76, 60);
    private static final Color WARNING_COLOR = new Color(243, 156, 18);
    private static final Color LIGHT_GRAY = new Color(236, 240, 241);
    private static final Color DARK_GRAY = new Color(52, 73, 94);
    private static final Color WHITE = Color.WHITE;
    private static final Color TEXT_COLOR = new Color(44, 62, 80);

    private JTextField usernameField;
    private JPasswordField passwordField;

    private static int nextAccountNumber = 1000;

    private JTextField accNumField, nameField, emailField, phoneField;
    private JComboBox<String> accTypeChoice;

    private JTextField accountNumField, amountField;
    private String currentTransactionType;
    private JLabel transactionTitleLabel;
    private JLabel amountLabel;

    private JTextField sourceAccField, targetAccField, transferAmountField;

    private JTextField loanAccField, loanAmountField, interestRateField, loanTermField;

    private JTextArea adminTextArea;
    private JTextField loanIdField, closeAccField;

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

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            UIManager.put("Button.font", new Font("Segoe UI", Font.BOLD, 14));
            UIManager.put("Label.font", new Font("Segoe UI", Font.PLAIN, 14));
            UIManager.put("TextField.font", new Font("Segoe UI", Font.PLAIN, 14));
            UIManager.put("PasswordField.font", new Font("Segoe UI", Font.PLAIN, 14));
            UIManager.put("TextArea.font", new Font("Consolas", Font.PLAIN, 13));
            UIManager.put("ComboBox.font", new Font("Segoe UI", Font.PLAIN, 14));
            UIManager.put("Panel.background", LIGHT_GRAY);
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

        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(PRIMARY_COLOR);
        headerPanel.setPreferredSize(new Dimension(0, 100));
        headerPanel.setLayout(new GridBagLayout());

        JLabel titleLabel = new JLabel("Modern Banking System", JLabel.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 36));
        titleLabel.setForeground(WHITE);
        headerPanel.add(titleLabel);

        JPanel contentPanel = new JPanel(new GridBagLayout());
        contentPanel.setBackground(LIGHT_GRAY);
        contentPanel.setBorder(new EmptyBorder(40, 60, 40, 60));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15);
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;

        Map<String, Color> buttonColors = new HashMap<>();
        buttonColors.put("Admin Login", PRIMARY_COLOR);
        buttonColors.put("Create Account", SUCCESS_COLOR);
        buttonColors.put("Deposit", new Color(46, 204, 113));
        buttonColors.put("Withdraw", WARNING_COLOR);
        buttonColors.put("Check Balance", new Color(155, 89, 182));
        buttonColors.put("Transfer Funds", SECONDARY_COLOR);
        buttonColors.put("Apply for Loan", new Color(230, 126, 34));
        buttonColors.put("Exit", DANGER_COLOR);

        JButton adminLoginBtn = createStyledButton("Admin Login", buttonColors.get("Admin Login"));
        JButton createAccountBtn = createStyledButton("Create Account", buttonColors.get("Create Account"));
        JButton depositBtn = createStyledButton("Deposit", buttonColors.get("Deposit"));
        JButton withdrawBtn = createStyledButton("Withdraw", buttonColors.get("Withdraw"));
        JButton checkBalanceBtn = createStyledButton("Check Balance", buttonColors.get("Check Balance"));
        JButton transferBtn = createStyledButton("Transfer Funds", buttonColors.get("Transfer Funds"));
        JButton applyLoanBtn = createStyledButton("Apply for Loan", buttonColors.get("Apply for Loan"));
        JButton exitBtn = createStyledButton("Exit", DANGER_COLOR);

        adminLoginBtn.addActionListener(e -> cardLayout.show(mainPanel, "LOGIN"));
        createAccountBtn.addActionListener(e -> {
            cardLayout.show(mainPanel, "CREATE_ACCOUNT");
            accNumField.setText(String.valueOf(nextAccountNumber));
        });
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

        gbc.gridx = 0; gbc.gridy = 0; contentPanel.add(adminLoginBtn, gbc);
        gbc.gridx = 1; gbc.gridy = 0; contentPanel.add(createAccountBtn, gbc);

        gbc.gridx = 0; gbc.gridy = 1; contentPanel.add(depositBtn, gbc);
        gbc.gridx = 1; gbc.gridy = 1; contentPanel.add(withdrawBtn, gbc);

        gbc.gridx = 0; gbc.gridy = 2; contentPanel.add(checkBalanceBtn, gbc);
        gbc.gridx = 1; gbc.gridy = 2; contentPanel.add(transferBtn, gbc);

        gbc.gridx = 0; gbc.gridy = 3; contentPanel.add(applyLoanBtn, gbc);
        gbc.gridx = 1; gbc.gridy = 3; contentPanel.add(exitBtn, gbc);

        panel.add(headerPanel, BorderLayout.NORTH);
        panel.add(contentPanel, BorderLayout.CENTER);

        mainPanel.add(panel, "MAIN");
    }

    private void createLoginPanel() {
        JPanel panel = createFormPanel("Admin Login");

        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(WHITE);
        formPanel.setBorder(createShadowBorder());
        formPanel.setPreferredSize(new Dimension(450, 350));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 25, 15, 25);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;

        gbc.gridx = 0; gbc.gridy = 0;
        formPanel.add(createLabel("Username:"), gbc);
        gbc.gridx = 1;
        usernameField = createStyledTextField(20, "Enter admin username");
        formPanel.add(usernameField, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        formPanel.add(createLabel("Password:"), gbc);
        gbc.gridx = 1;
        passwordField = new JPasswordField(20);
        styleTextField(passwordField);
        passwordField.setToolTipText("Enter admin password");
        formPanel.add(passwordField, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(30, 25, 10, 25);

        JButton loginButton = createStyledButton("Login", SUCCESS_COLOR);
        loginButton.addActionListener(this::handleLogin);
        formPanel.add(loginButton, gbc);

        gbc.gridy = 3;
        gbc.insets = new Insets(10, 25, 25, 25);
        JButton backButton = createStyledButton("Back to Main Menu", DARK_GRAY);
        backButton.addActionListener(e -> {
            cardLayout.show(mainPanel, "MAIN");
            clearFields();
        });
        formPanel.add(backButton, gbc);

        JPanel contentWrapper = (JPanel) panel.getComponent(1);
        contentWrapper.add(formPanel, new GridBagConstraints());
        mainPanel.add(panel, "LOGIN");
    }

    private void createAccountCreationPanel() {
        JPanel panel = createFormPanel("Create New Account");

        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(WHITE);
        formPanel.setBorder(createShadowBorder());
        formPanel.setPreferredSize(new Dimension(500, 450));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 25, 10, 25);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;

        gbc.gridx = 0; gbc.gridy = 0;
        formPanel.add(createLabel("Account Number:"), gbc);
        gbc.gridx = 1;
        accNumField = createStyledTextField(20, "Auto-generated account number");
        accNumField.setEditable(false);
        formPanel.add(accNumField, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        formPanel.add(createLabel("Account Type:"), gbc);
        gbc.gridx = 1;
        accTypeChoice = new JComboBox<>(new String[]{"Savings", "Current"});
        styleComboBox(accTypeChoice);
        accTypeChoice.setToolTipText("Select account type");
        formPanel.add(accTypeChoice, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        formPanel.add(createLabel("Customer Name:"), gbc);
        gbc.gridx = 1;
        nameField = createStyledTextField(20, "Enter customer's full name");
        formPanel.add(nameField, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        formPanel.add(createLabel("Email:"), gbc);
        gbc.gridx = 1;
        emailField = createStyledTextField(20, "Enter customer's email address");
        formPanel.add(emailField, gbc);

        gbc.gridx = 0; gbc.gridy = 4;
        formPanel.add(createLabel("Phone:"), gbc);
        gbc.gridx = 1;
        phoneField = createStyledTextField(20, "Enter customer's phone number");
        formPanel.add(phoneField, gbc);

        gbc.gridx = 0; gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(30, 25, 10, 25);

        JButton createBtn = createStyledButton("Create Account", SUCCESS_COLOR);
        createBtn.addActionListener(this::handleCreateAccount);
        formPanel.add(createBtn, gbc);

        gbc.gridy = 6;
        gbc.insets = new Insets(10, 25, 25, 25);
        JButton backButton = createStyledButton("Back to Main Menu", DARK_GRAY);
        backButton.addActionListener(e -> {
            cardLayout.show(mainPanel, "MAIN");
            clearFields();
        });
        formPanel.add(backButton, gbc);

        JPanel contentWrapper = (JPanel) panel.getComponent(1);
        contentWrapper.add(formPanel, new GridBagConstraints());
        mainPanel.add(panel, "CREATE_ACCOUNT");
    }

    private void createTransactionPanel() {
        JPanel panel = createFormPanel("Transaction");

        JPanel headerPanel = (JPanel) panel.getComponent(0);
        transactionTitleLabel = (JLabel) headerPanel.getComponent(0);


        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(WHITE);
        formPanel.setBorder(createShadowBorder());
        formPanel.setPreferredSize(new Dimension(450, 350));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 25, 15, 25);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;

        gbc.gridx = 0; gbc.gridy = 0;
        formPanel.add(createLabel("Account Number:"), gbc);
        gbc.gridx = 1;
        accountNumField = createStyledTextField(20, "Enter account number");
        formPanel.add(accountNumField, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        amountLabel = createLabel("Amount:");
        formPanel.add(amountLabel, gbc);
        gbc.gridx = 1;
        amountField = createStyledTextField(20, "Enter amount");
        formPanel.add(amountField, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(30, 25, 10, 25);

        JButton executeBtn = createStyledButton("Execute", PRIMARY_COLOR);
        executeBtn.addActionListener(this::handleTransaction);
        formPanel.add(executeBtn, gbc);

        gbc.gridy = 3;
        gbc.insets = new Insets(10, 25, 25, 25);
        JButton backButton = createStyledButton("Back to Main Menu", DARK_GRAY);
        backButton.addActionListener(e -> {
            cardLayout.show(mainPanel, "MAIN");
            clearFields();
        });
        formPanel.add(backButton, gbc);

        JPanel contentWrapper = (JPanel) panel.getComponent(1);
        contentWrapper.add(formPanel, new GridBagConstraints());
        mainPanel.add(panel, "TRANSACTION");
    }

    private void setupTransactionPanel(String type) {
        currentTransactionType = type;
        if (transactionTitleLabel != null) {
            transactionTitleLabel.setText(type);
        }

        boolean showAmount = !type.equals("Check Balance");
        amountField.setVisible(showAmount);
        if (amountLabel != null) {
            amountLabel.setVisible(showAmount);
        }

        mainPanel.revalidate();
        mainPanel.repaint();
    }

    private void createTransferPanel() {
        JPanel panel = createFormPanel("Transfer Funds");

        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(WHITE);
        formPanel.setBorder(createShadowBorder());
        formPanel.setPreferredSize(new Dimension(500, 400));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 25, 15, 25);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;

        gbc.gridx = 0; gbc.gridy = 0;
        formPanel.add(createLabel("Source Account:"), gbc);
        gbc.gridx = 1;
        sourceAccField = createStyledTextField(20, "Enter your account number");
        formPanel.add(sourceAccField, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        formPanel.add(createLabel("Target Account:"), gbc);
        gbc.gridx = 1;
        targetAccField = createStyledTextField(20, "Enter recipient's account number");
        formPanel.add(targetAccField, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        formPanel.add(createLabel("Amount:"), gbc);
        gbc.gridx = 1;
        transferAmountField = createStyledTextField(20, "Enter amount to transfer");
        formPanel.add(transferAmountField, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(30, 25, 10, 25);

        JButton transferBtn = createStyledButton("Transfer", SUCCESS_COLOR);
        transferBtn.addActionListener(this::handleTransfer);
        formPanel.add(transferBtn, gbc);

        gbc.gridy = 4;
        gbc.insets = new Insets(10, 25, 25, 25);
        JButton backButton = createStyledButton("Back to Main Menu", DARK_GRAY);
        backButton.addActionListener(e -> {
            cardLayout.show(mainPanel, "MAIN");
            clearFields();
        });
        formPanel.add(backButton, gbc);

        JPanel contentWrapper = (JPanel) panel.getComponent(1);
        contentWrapper.add(formPanel, new GridBagConstraints());
        mainPanel.add(panel, "TRANSFER");
    }

    private void createLoanApplicationPanel() {
        JPanel panel = createFormPanel("Apply for Loan");

        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(WHITE);
        formPanel.setBorder(createShadowBorder());
        formPanel.setPreferredSize(new Dimension(500, 450));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 25, 10, 25);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;

        gbc.gridx = 0; gbc.gridy = 0;
        formPanel.add(createLabel("Account Number:"), gbc);
        gbc.gridx = 1;
        loanAccField = createStyledTextField(20, "Enter your account number");
        formPanel.add(loanAccField, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        formPanel.add(createLabel("Loan Amount:"), gbc);
        gbc.gridx = 1;
        loanAmountField = createStyledTextField(20, "Enter desired loan amount");
        formPanel.add(loanAmountField, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        formPanel.add(createLabel("Interest Rate (%):"), gbc);
        gbc.gridx = 1;
        interestRateField = createStyledTextField(20, "Enter annual interest rate (e.g., 5.0)");
        formPanel.add(interestRateField, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        formPanel.add(createLabel("Loan Term (months):"), gbc);
        gbc.gridx = 1;
        loanTermField = createStyledTextField(20, "Enter loan term in months (e.g., 60)");
        formPanel.add(loanTermField, gbc);

        gbc.gridx = 0; gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(30, 25, 10, 25);

        JButton applyBtn = createStyledButton("Apply for Loan", WARNING_COLOR);
        applyBtn.addActionListener(this::handleLoanApplication);
        formPanel.add(applyBtn, gbc);

        gbc.gridy = 5;
        gbc.insets = new Insets(10, 25, 25, 25);
        JButton backButton = createStyledButton("Back to Main Menu", DARK_GRAY);
        backButton.addActionListener(e -> {
            cardLayout.show(mainPanel, "MAIN");
            clearFields();
        });
        formPanel.add(backButton, gbc);

        JPanel contentWrapper = (JPanel) panel.getComponent(1);
        contentWrapper.add(formPanel, new GridBagConstraints());
        mainPanel.add(panel, "LOAN_APPLICATION");
    }

    private void createAdminPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(LIGHT_GRAY);

        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(DARK_GRAY);
        headerPanel.setPreferredSize(new Dimension(0, 80));
        headerPanel.setLayout(new GridBagLayout());

        JLabel titleLabel = new JLabel("Admin Panel", JLabel.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 28));
        titleLabel.setForeground(WHITE);
        headerPanel.add(titleLabel);

        JPanel contentPanel = new JPanel(new BorderLayout(20, 20));
        contentPanel.setBorder(new EmptyBorder(20, 30, 20, 30));
        contentPanel.setBackground(LIGHT_GRAY);

        adminTextArea = new JTextArea(15, 50);
        adminTextArea.setEditable(false);
        adminTextArea.setFont(new Font("Consolas", Font.PLAIN, 13));
        adminTextArea.setBackground(new Color(248, 248, 248));
        adminTextArea.setForeground(TEXT_COLOR);
        adminTextArea.setBorder(new EmptyBorder(15, 15, 15, 15));
        adminTextArea.setLineWrap(true);
        adminTextArea.setWrapStyleWord(true);

        JScrollPane scrollPane = new JScrollPane(adminTextArea);
        scrollPane.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        inputPanel.setBackground(LIGHT_GRAY);
        inputPanel.setBorder(new EmptyBorder(10, 0, 10, 0));

        inputPanel.add(createLabel("Loan ID:"));
        loanIdField = createStyledTextField(10, "Enter loan ID");
        inputPanel.add(loanIdField);

        inputPanel.add(createLabel("Account No:"));
        closeAccField = createStyledTextField(10, "Enter account number");
        inputPanel.add(closeAccField);

        JPanel buttonPanel = new JPanel(new GridLayout(3, 3, 15, 15));
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
        logoutBtn.addActionListener(e -> {
            cardLayout.show(mainPanel, "MAIN");
            clearFields();
            adminTextArea.setText("");
        });
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

        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(SUCCESS_COLOR);
        headerPanel.setPreferredSize(new Dimension(0, 80));
        headerPanel.setLayout(new GridBagLayout());

        JLabel titleLabel = new JLabel("Transaction Result", JLabel.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 28));
        titleLabel.setForeground(WHITE);
        headerPanel.add(titleLabel);

        JPanel contentPanel = new JPanel(new BorderLayout(20, 20));
        contentPanel.setBorder(new EmptyBorder(30, 50, 30, 50));
        contentPanel.setBackground(LIGHT_GRAY);

        resultArea = new JTextArea(20, 60);
        resultArea.setEditable(false);
        resultArea.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        resultArea.setBackground(new Color(248, 248, 248));
        resultArea.setForeground(TEXT_COLOR);
        resultArea.setBorder(new EmptyBorder(20, 20, 20, 20));
        resultArea.setLineWrap(true);
        resultArea.setWrapStyleWord(true);

        JScrollPane scrollPane = new JScrollPane(resultArea);
        scrollPane.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        JButton backButton = createStyledButton("Back to Main Menu", PRIMARY_COLOR);
        backButton.addActionListener(e -> {
            cardLayout.show(mainPanel, "MAIN");
            clearFields();
        });

        contentPanel.add(scrollPane, BorderLayout.CENTER);
        contentPanel.add(Box.createVerticalStrut(20), BorderLayout.NORTH);
        contentPanel.add(backButton, BorderLayout.SOUTH);

        panel.add(headerPanel, BorderLayout.NORTH);
        panel.add(contentPanel, BorderLayout.CENTER);

        mainPanel.add(panel, "RESULT");
    }

    private JPanel createFormPanel(String title) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(LIGHT_GRAY);

        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(PRIMARY_COLOR);
        headerPanel.setPreferredSize(new Dimension(0, 80));
        headerPanel.setLayout(new GridBagLayout());

        JLabel titleLabel = new JLabel(title, JLabel.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 28));
        titleLabel.setForeground(WHITE);
        headerPanel.add(titleLabel);

        JPanel contentWrapper = new JPanel(new GridBagLayout());
        contentWrapper.setBackground(LIGHT_GRAY);
        contentWrapper.setBorder(new EmptyBorder(40, 40, 40, 40));

        panel.add(headerPanel, BorderLayout.NORTH);
        panel.add(contentWrapper, BorderLayout.CENTER);

        return panel;
    }

    private JButton createStyledButton(String text, Color backgroundColor) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.BOLD, 15));
        button.setBackground(backgroundColor);
        button.setForeground(Color.BLACK);
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(backgroundColor.darker().darker(), 1),
                BorderFactory.createEmptyBorder(12, 24, 12, 24)
        ));
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setPreferredSize(new Dimension(220, 50));

        button.setBorder(new CompoundBorder(
                BorderFactory.createLineBorder(backgroundColor.darker().darker(), 1),
                new EmptyBorder(10, 20, 10, 20)
        ));
        button.putClientProperty("JButton.buttonType", "roundRect");

        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(backgroundColor.darker());
                button.setBorder(new CompoundBorder(
                        BorderFactory.createLineBorder(backgroundColor.darker().darker().darker(), 1),
                        new EmptyBorder(10, 20, 10, 20)
                ));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(backgroundColor);
                button.setBorder(new CompoundBorder(
                        BorderFactory.createLineBorder(backgroundColor.darker().darker(), 1),
                        new EmptyBorder(10, 20, 10, 20)
                ));
            }
        });

        return button;
    }

    private JTextField createStyledTextField() {
        return createStyledTextField(20, null);
    }

    private JTextField createStyledTextField(int columns, String tooltipText) {
        JTextField field = new JTextField(columns);
        styleTextField(field);
        if (tooltipText != null) {
            field.setToolTipText(tooltipText);
        }
        return field;
    }

    private void styleTextField(JTextField field) {
        field.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        field.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(189, 195, 199), 1),
            BorderFactory.createEmptyBorder(10, 15, 10, 15)
        ));
        field.setPreferredSize(new Dimension(field.getPreferredSize().width, 40));
        field.setBackground(WHITE);
        field.setForeground(TEXT_COLOR);
        field.setCaretColor(PRIMARY_COLOR);
    }

    private void styleComboBox(JComboBox<String> comboBox) {
        comboBox.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        comboBox.setPreferredSize(new Dimension(220, 40));
        comboBox.setBackground(WHITE);
        comboBox.setForeground(TEXT_COLOR);
        comboBox.setBorder(BorderFactory.createLineBorder(new Color(189, 195, 199), 1));
    }

    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Segoe UI", Font.BOLD, 15));
        label.setForeground(DARK_GRAY);
        return label;
    }

    private Border createShadowBorder() {
        Border line = BorderFactory.createLineBorder(new Color(200, 200, 200), 1);
        Border empty = BorderFactory.createEmptyBorder(15, 15, 15, 15);
        return BorderFactory.createCompoundBorder(line, empty);
    }

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
        if (usernameField != null) usernameField.setText("");
        if (passwordField != null) passwordField.setText("");

        if (accTypeChoice != null) accTypeChoice.setSelectedIndex(0);
        if (nameField != null) nameField.setText("");
        if (emailField != null) emailField.setText("");
        if (phoneField != null) phoneField.setText("");

        if (accountNumField != null) accountNumField.setText("");
        if (amountField != null) amountField.setText("");

        if (sourceAccField != null) sourceAccField.setText("");
        if (targetAccField != null) targetAccField.setText("");
        if (transferAmountField != null) transferAmountField.setText("");

        if (loanAccField != null) loanAccField.setText("");
        if (loanAmountField != null) loanAmountField.setText("");
        if (interestRateField != null) interestRateField.setText("");
        if (loanTermField != null) loanTermField.setText("");

        if (loanIdField != null) loanIdField.setText("");
        if (closeAccField != null) closeAccField.setText("");
    }

    static class Bank {
        private Map<Integer, BankAccount> accounts = new HashMap<>();
        private Map<Integer, Loan> loans = new HashMap<>();
        private int loanIdCounter = 1;

        public void addCustomer(Customer customer) {
            System.out.println("Customer " + customer.getName() + " added.");
        }

        public void addAccount(BankAccount account) {
            accounts.put(account.getAccountNumber(), account);
            System.out.println("Account " + account.getAccountNumber() + " added.");
        }

        public BankAccount findAccount(int accountNumber) {
            return accounts.get(accountNumber);
        }

        public void removeAccount(int accountNumber) {
            accounts.remove(accountNumber);
        }

        public void addLoans(Loan loan) {
            loan.setLoanId(loanIdCounter++);
            loans.put(loan.getLoanId(), loan);
            System.out.println("Loan " + loan.getLoanId() + " applied.");
        }

        public Loan findLoan(int loanId) {
            return loans.get(loanId);
        }

        public void approveLoan(Loan loan) {
            loan.approve();
            loan.getAccount().deposit(loan.getLoanAmount());
        }

        public void rejectLoan(Loan loan) {
            loan.reject();
        }

        public void listAllLoans() {
            System.out.println("\n--- All Loans ---");
            if (loans.isEmpty()) {
                System.out.println("No loans to display.");
                return;
            }
            loans.values().forEach(System.out::println);
            System.out.println("-----------------");
        }

        public void listAllAccounts() {
            System.out.println("\n--- All Accounts ---");
            if (accounts.isEmpty()) {
                System.out.println("No accounts to display.");
                return;
            }
            accounts.values().forEach(System.out::println);
            System.out.println("--------------------");
        }

        public void generateReport() {
            System.out.println("\n--- Bank Report ---");
            System.out.println("Total Accounts: " + accounts.size());
            double totalBalance = accounts.values().stream().mapToDouble(BankAccount::checkBalance).sum();
            System.out.println("Total Balance Across All Accounts: $" + String.format("%.2f", totalBalance));
            System.out.println("Total Loans: " + loans.size());
            long approvedLoans = loans.values().stream().filter(l -> "Approved".equals(l.getStatus())).count();
            System.out.println("Approved Loans: " + approvedLoans);
            long pendingLoans = loans.values().stream().filter(l -> "Pending".equals(l.getStatus())).count();
            System.out.println("Pending Loans: " + pendingLoans);
            System.out.println("-------------------");
        }
    }

    static class BankAccount {
        private int accountNumber;
        private String accountType;
        private double balance;
        private Customer customer;
        private boolean isActive;

        public BankAccount(int accountNumber, String accountType, Customer customer) {
            this.accountNumber = accountNumber;
            this.accountType = accountType;
            this.customer = customer;
            this.balance = 0.0;
            this.isActive = true;
        }

        public int getAccountNumber() {
            return accountNumber;
        }

        public String getAccountType() {
            return accountType;
        }

        public double checkBalance() {
            return balance;
        }

        public void deposit(double amount) {
            if (isActive) {
                balance += amount;
            } else {
                System.out.println("Account is closed.");
            }
        }

        public void withdraw(double amount) {
            if (isActive) {
                if (balance >= amount) {
                    balance -= amount;
                } else {
                    System.out.println("Insufficient funds.");
                }
            } else {
                System.out.println("Account is closed.");
            }
        }

        public void transferFunds(BankAccount targetAccount, double amount) {
            if (isActive && targetAccount.isActive) {
                if (this.balance >= amount) {
                    this.withdraw(amount);
                    targetAccount.deposit(amount);
                    System.out.println("Transfer successful.");
                } else {
                    System.out.println("Insufficient funds for transfer.");
                }
            } else {
                System.out.println("One or both accounts are closed.");
            }
        }

        public void closeAccount() {
            this.isActive = false;
            this.balance = 0;
            System.out.println("Account " + accountNumber + " closed.");
        }

        @Override
        public String toString() {
            return "Account No: " + accountNumber + ", Type: " + accountType +
                   ", Balance: $" + String.format("%.2f", balance) + ", Status: " + (isActive ? "Active" : "Closed") +
                   ", Customer: " + customer.getName();
        }
    }

    static class Customer {
        private int customerId;
        private String name;
        private String email;
        private String phone;
        private static int nextCustomerId = 1;

        public Customer(int accountNumber, String name, String email, String phone) {
            this.customerId = nextCustomerId++;
            this.name = name;
            this.email = email;
            this.phone = phone;
        }

        public int getCustomerId() {
            return customerId;
        }

        public String getName() {
            return name;
        }

        public String getEmail() {
            return email;
        }

        public String getPhone() {
            return phone;
        }

        @Override
        public String toString() {
            return "Customer ID: " + customerId + ", Name: " + name + ", Email: " + email + ", Phone: " + phone;
        }
    }

    static class Loan {
        private int loanId;
        private BankAccount account;
        private double loanAmount;
        private double interestRate;
        private int loanTermMonths;
        private String status;

        public Loan(BankAccount account, double loanAmount, double interestRate, int loanTermMonths) {
            this.account = account;
            this.loanAmount = loanAmount;
            this.interestRate = interestRate;
            this.loanTermMonths = loanTermMonths;
            this.status = "Pending";
        }

        public int getLoanId() {
            return loanId;
        }

        public void setLoanId(int loanId) {
            this.loanId = loanId;
        }

        public BankAccount getAccount() {
            return account;
        }

        public double getLoanAmount() {
            return loanAmount;
        }

        public double getInterestRate() {
            return interestRate;
        }

        public int getLoanTermMonths() {
            return loanTermMonths;
        }

        public String getStatus() {
            return status;
        }

        public void approve() {
            this.status = "Approved";
        }

        public void reject() {
            this.status = "Rejected";
        }

        @Override
        public String toString() {
            return "Loan ID: " + loanId + ", Account No: " + account.getAccountNumber() +
                   ", Amount: $" + String.format("%.2f", loanAmount) + ", Rate: " + interestRate + "%" +
                   ", Term: " + loanTermMonths + " months, Status: " + status;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ModernBankingSystemGUI());
    }
}