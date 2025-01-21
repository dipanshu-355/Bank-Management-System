# Banking-management-sysytem

Overview
The Banking Management System is a Java-based application that provides essential banking functionalities. Built using Java and JDBC (Java Database Connectivity), this project demonstrates how to manage banking operations such as account creation, transactions, and statement generation in a structured and efficient manner.



 Features

The system includes the following core features:

1. User Registration and Login

   - Secure user registration.
   - Authentication using login credentials.

2. Account Management

   - Open a new bank account.

3. Banking Transactions

   - Debit: Withdraw funds from an account.
   - Credit: Deposit funds into an account.
   - Transfer Money: Transfer funds between accounts.

4. Statement Generation

   - Debit Statement: View a history of all debit transactions.
   - Credit Statement: View a history of all credit transactions.
   - Transfer Statement: View a history of all money transfers.

5. Additional Functions

   - Check Balance: View the current balance of your account.
   - Logout: Securely log out of the system.



 Technologies Used

- Programming Language: Java
- Database Connectivity: JDBC (Java Database Connectivity)
- Database: MySQL
- IDE: Any Java-supported IDE (e.g., IntelliJ IDEA, Eclipse, NetBeans)



 Prerequisites

1. Java Development Kit (JDK)

   - Ensure Java 8 or later is installed.

2. Database Setup

   - Install and configure a relational database.
   - Create necessary tables (refer to the `schema.sql` file in the repository).

3. JDBC Driver

   - Include the JDBC driver for your database in the project classpath.

4. IDE Setup

   - Import the project into your preferred IDE.



 Installation and Setup

1. Clone the Repository

   
   git clone https://github.com/your-repo/banking-management-system.git
  

2. Database Configuration

   - Update the `db.properties` file with your database connection details:
     
     db.url=jdbc:mysql://localhost:3306/banking_system
     db.user=root
     db.password=password
     

3. Build and Run the Application

   - Compile the project using your IDE or the command line:
  
     javac -cp .:path-to-jdbc-driver.jar *.java
     
   - Run the application:
     
     java -cp .:path-to-jdbc-driver.jar MainClass
   



 Usage

1. Start the Application

   - Launch the application and navigate through the menu-driven interface.

2. Register and Login

   - New users must register to create an account.
   - Existing users can log in using their credentials.

3. Perform Banking Operations

   - Use the menu options to:
     - Open a bank account.
     - Perform debit, credit, or transfer transactions.
     - Generate and view statements.
     - Check account balance.
     - Logout securely.


 Future Enhancements

- Integration with a front-end interface (e.g., web or mobile application).
- Enhanced security features such as encryption for sensitive data.
- Support for multiple currencies and international transactions.



 Contributing

Contributions are welcome! Please fork the repository and submit a pull request with your changes.



 License

This project is licensed under the MIT License. See the `LICENSE` file for details.


 Contact

For any questions or feedback, feel free to contact:

- Name: Dipanshu sahu 
- Email: dipanshusahu355@gmail.com
- GitHub:https://github.com/dipanshu-355
