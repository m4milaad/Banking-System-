# Modern Banking System

A sophisticated desktop banking application built with Java Swing, featuring a modern, professional interface and comprehensive banking operations. This project demonstrates advanced object-oriented programming principles and provides a realistic simulation of banking operations with an intuitive user experience.

## ✨ Features

### 🎨 Modern User Interface
- **Professional Design**: Clean, modern interface with carefully crafted color schemes
- **Responsive Layout**: Optimized layouts that adapt to different screen sizes
- **Enhanced UX**: Smooth hover effects, styled buttons, and intuitive navigation
- **Premium Typography**: Segoe UI font family for a professional appearance
- **Visual Feedback**: Icons and emojis for better user experience

### 👤 Customer Operations
- **Account Management**: Create new savings or current accounts with auto-generated account numbers
- **Secure Transactions**: Deposit, withdraw, and check account balances with real-time validation
- **Fund Transfers**: Secure money transfers between accounts with balance verification
- **Loan Applications**: Apply for loans with customizable terms, interest rates, and repayment periods

### 🔐 Admin Panel
- **Secure Authentication**: Protected admin access with username/password authentication
- **Loan Management**: Approve or reject pending loan applications with instant processing
- **Account Oversight**: View all customer accounts, close accounts, and monitor activity
- **Comprehensive Reporting**: Generate detailed system reports with account summaries
- **Real-time Monitoring**: Live updates of all banking operations and transactions

### 🛡️ Security Features
- **Input Validation**: Comprehensive validation for all user inputs
- **Error Handling**: Robust error handling with user-friendly messages
- **Admin Protection**: Secure admin panel with credential verification
- **Transaction Safety**: Balance verification before all transactions

## 🧰 Technologies Used
- **Java SE 8+**: Core application development with modern Java features
- **Java Swing**: Advanced GUI framework with custom styling and components
- **Object-Oriented Design**: Encapsulation, inheritance, and polymorphism principles
- **Event-Driven Architecture**: Responsive user interactions and real-time updates
- **MVC Pattern**: Clean separation of concerns for maintainable code

## 🗂️ Project Structure
```
Banking-System/
├── Main.java                    # Application launcher with interface 
├── ModernBankingSystemGUI.java  # Complete modern Swing-based interface
├── Bank.java                    # Core banking logic and account management
├── BankAccount.java             # Account operations and transaction handling
├── Customer.java                # Customer data and profile management
├── Loan.java                    # Loan processing and status tracking
└── README.md                    # Project documentation
```

## 🎨 Design Philosophy

### Color Palette
- **Primary Blue** (`#2980B9`): Trust, reliability, and professionalism
- **Success Green** (`#27AE60`): Positive actions and confirmations
- **Warning Orange** (`#F39C12`): Important notices and loan applications
- **Danger Red** (`#E74C3C`): Critical actions and error states
- **Dark Gray** (`#34495E`): Professional text and admin sections
- **Light Gray** (`#ECF0F1`): Clean backgrounds and subtle elements

### UI Components
- **Custom Styled Buttons**: Rounded corners with smooth hover transitions
- **Enhanced Forms**: Clean input fields with proper spacing and tooltips
- **Professional Cards**: Shadow effects and rounded borders for form containers
- **Responsive Panels**: Adaptive layouts using GridBagLayout and BorderLayout
- **Consistent Spacing**: 8px grid system for visual harmony

## 🚀 Getting Started

### Prerequisites
- Java Development Kit (JDK) 8 or higher
- Any Java IDE (IntelliJ IDEA, Eclipse, VS Code) or command line

### Installation & Running

1. **Clone the repository:**
   ```bash
   git clone https://github.com/m4milaad/Banking-System.git
   cd Banking-System
   ```

2. **Compile the project:**
   ```bash
   javac *.java
   ```

3. **Run the application:**
   ```bash
   java Main
   ```

4. **Select interface:**
   - Choose **Option 1** for the Modern Swing GUI (Recommended)
   - Choose **Option 2** for Command Line Interface

### Quick Start Guide

1. **Launch the application** and select the Modern GUI
2. **Create an account** by clicking "Create Account" and filling in customer details
3. **Perform transactions** like deposits, withdrawals, and transfers
4. **Apply for loans** with custom terms and interest rates
5. **Access admin panel** using credentials: `admin` / `admin3103`

## 🔐 Admin Credentials
- **Username**: `admin`
- **Password**: `admin3103`

## 📱 User Interface Overview

### Main Dashboard
The main dashboard provides quick access to all banking operations with large, clearly labeled buttons and a professional header.

### Transaction Forms
All transaction forms feature:
- Auto-generated account numbers for new accounts
- Real-time input validation
- Clear error messages and success confirmations
- Tooltips for user guidance

### Admin Panel
The admin panel includes:
- Scrollable text area for operation logs
- Quick action buttons for loan management
- Account oversight tools
- Comprehensive reporting features

## 💡 Key Features Explained

### Account Management
- **Auto-incrementing account numbers** starting from 1000
- **Account type selection** (Savings/Current)
- **Customer information storage** with validation
- **Real-time balance tracking**

### Transaction Processing
- **Deposit operations** with positive amount validation
- **Withdrawal operations** with sufficient balance checks
- **Balance inquiries** with formatted currency display
- **Fund transfers** between valid accounts

### Loan System
- **Loan application submission** with custom terms
- **Admin approval/rejection workflow**
- **Automatic fund disbursement** upon approval
- **Loan tracking and status management**

## ⚠️ Current Limitations
- **Data Persistence**: Information is stored in memory only (resets on restart)
- **Single Session**: No multi-user support or session management
- **Basic Security**: Simple password authentication without encryption
- **No Database**: All data is stored in Java collections

## 🔮 Future Enhancements

### Planned Features
- **Database Integration**: MySQL/PostgreSQL for persistent data storage
- **Customer Authentication**: Individual customer login system
- **Transaction History**: Detailed transaction logs and account statements
- **Advanced Security**: Password hashing, encryption, and session management
- **Web Interface**: Browser-based banking portal using Spring Boot
- **Mobile App**: React Native companion application
- **API Integration**: RESTful services for external system integration

### Technical Improvements
- **JavaFX Migration**: Modern UI framework for enhanced graphics and animations
- **Microservices Architecture**: Scalable, distributed system design
- **Cloud Deployment**: AWS/Azure hosting with containerization
- **Real-time Notifications**: WebSocket-based live updates
- **Advanced Reporting**: PDF generation and data visualization
- **Audit Logging**: Comprehensive activity tracking and compliance

## 🧪 Testing

### Manual Testing Scenarios
1. **Account Creation**: Test with valid/invalid customer data
2. **Transaction Operations**: Test deposits, withdrawals with various amounts
3. **Transfer Operations**: Test between existing/non-existing accounts
4. **Loan Processing**: Test application, approval, and rejection workflows
5. **Admin Functions**: Test all admin panel operations
6. **Error Handling**: Test with invalid inputs and edge cases

### Recommended Test Cases
- Create multiple accounts and perform cross-account transfers
- Apply for loans with different terms and test approval process
- Test admin functions with various loan and account scenarios
- Verify input validation with edge cases (negative amounts, empty fields)

## 👥 Development Team
- Nimra Wani
- Milad Ajaz Bhat
- Adnan Zargar
- Saliq Naqash
- Mehdi Hafiz 

## 📄 License
This project is developed for educational purposes to demonstrate:
- Modern Java Swing GUI development
- Object-oriented programming principles
- Banking system logic and workflow management
- Professional software development practices
- Clean code architecture and design patterns

## 🤝 Contributing
Contributions are welcome! Please feel free to submit issues, feature requests, or pull requests to help improve this educational banking system.

### How to Contribute
1. Fork the repository
2. Create a feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## 📞 Support
For questions, suggestions, or support regarding this project:
- Create an issue in the GitHub repository
- Contact the development team
- Check the documentation for common solutions

## 🏆 Acknowledgments
- Java Swing documentation and community
- Modern UI/UX design principles
- Banking industry best practices
- Open source Java community contributions

---

> **Note**: This is an educational project designed to showcase banking system functionality and modern Java GUI development. It demonstrates professional software development practices but is not intended for production use without additional security implementations, data persistence, and regulatory compliance features.

## 📊 Project Statistics
- **Lines of Code**: ~1,500+
- **Classes**: 6 main classes
- **GUI Components**: 50+ styled components
- **Features**: 15+ banking operations
- **Design Elements**: Custom color scheme, typography, and layouts