import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class BankingApp {
//        Database Url
        private static final String url = "jdbc:mysql://localhost:3306/banking_system";
//        Database Credentials
        private static final String username = "root";
        private static final String password = "Aman@?12";

    public static void main(String[] args) throws ClassNotFoundException, SQLException{
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Drivers loaded successfully!!!");
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }

//        Establish the connection
        try{
            Connection connection = DriverManager.getConnection(url, username, password);
            System.out.println("Connected to the Database : ");
//            Perform Database Operation Here
            Scanner scanner = new Scanner(System.in);
            User user = new User(connection, scanner);
            Accounts accounts = new Accounts(connection, scanner);
            AccountManager accountManager = new AccountManager(connection, scanner);
            Statement statement = new Statement(connection, scanner);

            String email;
            long account_number;

            while (true){
                System.out.println("*** Welcome to Banking System ***");
                System.out.println();
                System.out.println("1. Register");
                System.out.println("2. Login");
                System.out.println("3. Exit");
                System.out.println("Enter your choice : ");
                int choice1 = scanner.nextInt();
                switch (choice1){
                    case 1:
                        user.register();
                        break;
                    case 2:
                        email = user.login();
                        if (email!=null){
                            System.out.println();
                            System.out.println("User Logged In!");
                            if (!accounts.account_exists(email)) {
                                System.out.println();
                                System.out.println("1. Open a new Bank Account");
                                System.out.println("2. Exit");
                                if (scanner.nextInt() == 1) {
                                    account_number = accounts.open_account(email);
                                    System.out.println("Accounts Created Successfully.");
                                    System.out.println("Your Account Number is : " + account_number);
                                } else {
                                    break;
                                }
                            }
                                account_number = accounts.getAccount_number(email);
                                int choice2 = 0;
                                while (choice2!=8){
                                    System.out.println();
                                    System.out.println("1. Debit Money");
                                    System.out.println("2. Credit Money");
                                    System.out.println("3. Transfer Money");
                                    System.out.println("4. Check Balance");
                                    System.out.println("5. Credit Statement");
                                    System.out.println("6. Debit Statement");
                                    System.out.println("7. Transfer Statement");
                                    System.out.println("8. Log Out");
                                    System.out.println("Enter your choice : ");
                                    choice2 = scanner.nextInt();
                                    switch (choice2){
                                        case 1:
                                            accountManager.debit_money(account_number);
                                            break;
                                        case 2:
                                            accountManager.credit_money(account_number);
                                            break;
                                        case 3:
                                            accountManager.transfer_money(account_number);
                                            break;
                                        case 4:
                                            accountManager.getBalance(account_number);
                                            break;
                                        case 5:
                                            statement.credit_statement(account_number);
                                            break;
                                        case 6:
                                            statement.debit_statement(account_number);
                                            break;
                                        case 7:
                                            statement.transfer_statement(account_number);
                                            break;
                                        default:
                                            System.out.println("Enter valid choice.");
                                            break;
                                    }
                                }
                        }
                        else {
                            System.out.println("Incorrect email or password!");
                        }

                    case 3:
                        System.out.println("THANK YOU FOR USING BANKING SYSTEM!!!");
                        System.out.println("Existing System!");
                        return;

                    default:
                        System.out.println("Enter valid choice.");
                        break;
                }
            }



        }
        catch (SQLException e) {
            System.err.println("Failed Connection : " + e.getMessage());
        }
    }
}


