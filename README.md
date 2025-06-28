# Banking System

A simple yet powerful desktop banking application written in Java using AWT. This project simulates the core functionalities of a real-world banking system, including account management, transactions, and loan processing.

## 🚀 Features

### 👤 Customer Side
- Create new bank accounts
- Deposit and withdraw money
- Check account balance
- Transfer funds to other accounts
- Apply for personal or business loans

### 🔐 Admin Panel
- Login with secure credentials
- Approve or reject pending loan applications
- List all customers and loans
- Close user accounts
- Generate system reports

## 🧰 Technologies Used
- Java SE (Standard Edition)
- AWT (Abstract Window Toolkit)
- OOP (Object-Oriented Programming) principles

## 🗂️ Project Structure
```plaintext
├── Main.java               // Launches the application
├── BankingSystemGUI.java   // Complete GUI layout and logic integration
├── Bank.java               // Manages accounts, loans, and overall logic
├── BankAccount.java        // Encapsulates account operations
├── Customer.java           // Represents user/customer info
├── Loan.java               // Handles loan objects and statuses
```

## 📸 Screenshots
*Coming soon*

## 💡 How to Run
1. Clone the repository:
   ```bash
   git clone https://github.com/m4milaad/Banking-System-.git
   ```
2. Compile the project:
   ```bash
   javac *.java
   ```
3. Run the application:
   ```bash
   java Main
   ```

## ⚠️ Limitations
- Data is stored in memory; no database persistence.
- No customer login system (only admin authentication).
- Limited styling due to AWT constraints.

## 🔮 Future Enhancements
- Integrate with database (SQLite/MySQL)
- Replace AWT with JavaFX or Swing for modern UI
- Add login sessions for customers
- Track transaction history
- Secure admin login with password hashing

## 👥 Authors
- Milad Ajaz Bhat
- Nimra Wani
- Adnan Zargar
- Mehdi Hafiz
- Saliq Naqash

---

> "This project is built as part of an academic assignment to demonstrate core banking logic and GUI handling in Java."

