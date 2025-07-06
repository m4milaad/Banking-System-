# Modern Banking System

A comprehensive desktop banking application built with Java, featuring both modern Swing GUI and legacy AWT interfaces. This project demonstrates advanced object-oriented programming principles and provides a realistic simulation of banking operations.

## 🚀 Features

### 🎨 Modern UI (Java Swing)
- **Professional Design**: Clean, modern interface with custom color schemes
- **Responsive Layout**: Optimized for different screen sizes
- **Enhanced UX**: Hover effects, styled buttons, and intuitive navigation
- **Better Typography**: Improved fonts and spacing for better readability

### 👤 Customer Operations
- **Account Management**: Create new savings or current accounts
- **Transactions**: Deposit, withdraw, and check account balances
- **Fund Transfers**: Secure money transfers between accounts
- **Loan Applications**: Apply for personal or business loans with custom terms

### 🔐 Admin Panel
- **Secure Authentication**: Protected admin access with credentials
- **Loan Management**: Approve or reject pending loan applications
- **Account Oversight**: View all customer accounts and close accounts when needed
- **Reporting**: Generate comprehensive system reports
- **Real-time Monitoring**: Live updates of all banking operations

## 🧰 Technologies Used
- **Java SE**: Core application development
- **Java Swing**: Modern GUI framework with advanced styling
- **Java AWT**: Legacy GUI support (maintained for compatibility)
- **OOP Principles**: Encapsulation, inheritance, and polymorphism
- **Event-Driven Programming**: Responsive user interactions

## 🗂️ Project Structure
```plaintext
├── Main.java                    // Application launcher with UI selection
├── ModernBankingSystemGUI.java  // Modern Swing-based interface
├── BankingSystemGUI.java        // Legacy AWT interface
├── Bank.java                    // Core banking logic and account management
├── BankAccount.java             // Account operations and transactions
├── Customer.java                // Customer data and profile management
├── Loan.java                    // Loan processing and status tracking
└── README.md                    // Project documentation
```

## 🎨 Design Features

### Color Scheme
- **Primary**: Professional blue tones for trust and reliability
- **Success**: Green for positive actions and confirmations
- **Warning**: Orange for important notices and loan applications
- **Danger**: Red for critical actions and errors
- **Neutral**: Gray tones for balanced, professional appearance

### UI Components
- **Custom Styled Buttons**: Rounded corners with hover effects
- **Enhanced Forms**: Clean input fields with proper spacing
- **Professional Typography**: Segoe UI font family for modern appearance
- **Responsive Panels**: Adaptive layouts that work on different screen sizes

## 💡 How to Run

### Option 1: Modern Swing GUI (Recommended)
1. Clone the repository:
   ```bash
   git clone https://github.com/m4milaad/Banking-System-.git
   cd Banking-System-
   ```

2. Compile the project:
   ```bash
   javac *.java
   ```

3. Run the application:
   ```bash
   java Main
   ```

4. Select option **1** for the modern Swing interface

### Option 2: Legacy AWT GUI
- Follow the same steps but select option **2** for the legacy interface

### Option 3: Command Line Interface
- Select option **3** for the traditional CLI experience

## 🔐 Admin Credentials
- **Username**: `admin`
- **Password**: `admin3103`

## 📸 Interface Comparison

### Modern Swing GUI
- Clean, professional design with modern color schemes
- Enhanced user experience with hover effects and animations
- Better typography and spacing for improved readability
- Responsive layout that adapts to different screen sizes

### Legacy AWT GUI
- Traditional desktop application appearance
- Basic functionality with standard system components
- Maintained for compatibility and educational purposes

## ⚠️ Current Limitations
- **Data Persistence**: Information is stored in memory only (no database)
- **Customer Authentication**: Only admin login is implemented
- **Network Features**: Single-user desktop application

## 🔮 Future Enhancements

### Planned Features
- **Database Integration**: SQLite or MySQL for data persistence
- **Customer Login System**: Individual customer authentication
- **Transaction History**: Detailed transaction logs and statements
- **Advanced Security**: Password hashing and encryption
- **Web Interface**: Browser-based banking portal
- **Mobile App**: Companion mobile application
- **API Integration**: RESTful services for external integrations

### Technical Improvements
- **JavaFX Migration**: Modern UI framework for enhanced graphics
- **Spring Boot Backend**: Robust server-side architecture
- **Microservices**: Scalable, distributed system design
- **Cloud Deployment**: AWS/Azure hosting capabilities

## 👥 Development Team
- **Nimra Wani** - UI/UX Design & Frontend Development
- **Milad Ajaz Bhat** - Core Banking Logic & Architecture
- **Adnan Zargar** - Database Design & Integration
- **Mehdi Hafiz** - Security & Authentication Systems
- **Saliq Naqash** - Testing & Quality Assurance

## 📄 License
This project is developed as an academic assignment to demonstrate:
- Object-oriented programming principles in Java
- GUI development with both Swing and AWT
- Banking system logic and workflow management
- Professional software development practices

---

> **Note**: This is an educational project designed to showcase banking system functionality and modern Java GUI development. It is not intended for production use without additional security and data persistence implementations.

## 🤝 Contributing
We welcome contributions! Please feel free to submit issues, feature requests, or pull requests to help improve this educational banking system.

## 📞 Support
For questions or support regarding this project, please contact any of the development team members or create an issue in the repository.