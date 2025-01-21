import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class AccountManager {

    private Connection connection;
    private Scanner scanner;

    public AccountManager(Connection connection, Scanner scanner){
        this.connection = connection;
        this.scanner = scanner;
    }


    public void credit_money(long account_number) throws SQLException {
        scanner.nextLine();
        System.out.println("Enter Amount : ");
        double amount = scanner.nextDouble();
        scanner.nextLine();
        System.out.println("Enter Security Pin : ");
        String security_pin = scanner.nextLine();
        try {
            connection.setAutoCommit(false);
            if (account_number!=0){
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM accounts WHERE account_number = ? AND security_pin = ? ;");
                preparedStatement.setLong(1, account_number);
                preparedStatement.setString(2,security_pin);
                ResultSet resultSet = preparedStatement.executeQuery();

                if (resultSet.next()){
                        String credit_query = "UPDATE accounts SET balance = balance + ? WHERE account_number = ? ;";
                        PreparedStatement preparedStatement1 = connection.prepareStatement(credit_query);
                        preparedStatement1.setDouble(1, amount);
                        preparedStatement1.setLong(2, account_number);
                        int affectedRows = preparedStatement1.executeUpdate();
                        if (affectedRows>0){
                            System.out.println("Rs."+amount+" Credited Successfully.");
                            connection.commit();
                            connection.setAutoCommit(true);
                            try {
                                String credit_set = "INSERT INTO credit (account_number, amount) values (?,?);";
                                PreparedStatement preparedStatement2 = connection.prepareStatement(credit_set);
                                preparedStatement2.setLong(1, account_number);
                                preparedStatement2.setDouble(2, amount);
                                preparedStatement2.executeUpdate();
                            }catch (SQLException e){
                                e.printStackTrace();
                            }
                            return;
                        }
                        else {
                            System.out.println("Transaction Failed.");
                            connection.rollback();
                            connection.setAutoCommit(true);
                        }
                }
                else {
                    System.out.println("Invalid Pin!");
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        connection.setAutoCommit(true);
    }



    public void debit_money(long account_number) throws SQLException {
        scanner.nextLine();
        System.out.println("Enter Amount : ");
        double amount = scanner.nextDouble();
        scanner.nextLine();
        System.out.println("Enter Security Pin : ");
        String security_pin = scanner.nextLine();
        try {
            connection.setAutoCommit(false);
            if (account_number!=0){
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM accounts WHERE account_number = ? AND security_pin = ? ;");
                preparedStatement.setLong(1, account_number);
                preparedStatement.setString(2,security_pin);
                ResultSet resultSet = preparedStatement.executeQuery();

                if (resultSet.next()){
                    double current_balance = resultSet.getDouble("balance");
                    if (amount<=current_balance){
                        String debit_query = "UPDATE accounts SET balance = balance - ? WHERE account_number = ? ;";
                        PreparedStatement preparedStatement1 = connection.prepareStatement(debit_query);
                        preparedStatement1.setDouble(1, amount);
                        preparedStatement1.setLong(2, account_number);
                        int affectedRows = preparedStatement1.executeUpdate();
                        if (affectedRows>0){
                            System.out.println("Rs."+amount+" debited Successfully.");
                            connection.commit();
                            connection.setAutoCommit(true);

                            try {
                                String debit_set = "INSERT INTO debit (account_number, amount) values (?,?);";
                                PreparedStatement preparedStatement2 = connection.prepareStatement(debit_set);
                                preparedStatement2.setLong(1, account_number);
                                preparedStatement2.setDouble(2, amount);
                                preparedStatement2.executeUpdate();
                            }catch (SQLException e){
                                e.printStackTrace();
                            }

                            return;
                        }
                        else {
                            System.out.println("Transaction Failed.");
                            connection.rollback();
                            connection.setAutoCommit(true);
                        }
                    }
                    else {
                        System.out.println("Insufficient Balance!");
                    }
                }
                else {
                    System.out.println("Invalid Pin!");
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        connection.setAutoCommit(true);
    }


    public void transfer_money(long sender_account_number)throws SQLException{
        scanner.nextLine();
        System.out.println("Enter Receiver Account Number : ");
        long receiver_account_number = scanner.nextLong();
        System.out.println("Enter Amount : ");
        double amount = scanner.nextDouble();
        scanner.nextLine();
        System.out.println("Enter Security Pin : ");
        String security_pin = scanner.nextLine();

        try {
            connection.setAutoCommit(false);
            if (sender_account_number!=0 && receiver_account_number!=0){
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM accounts WHERE account_number = ? AND security_pin = ? ;");
                preparedStatement.setLong(1, sender_account_number);
                preparedStatement.setString(2, security_pin);
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()){
                    double current_balance = resultSet.getDouble("balance");
                    if (amount<=current_balance) {
                        String debit_query = "UPDATE accounts SET balance = balance - ? WHERE account_number = ? ;";
                        String credit_query = "UPDATE accounts SET balance = balance + ? WHERE account_number = ? ;";

                        PreparedStatement debit_preparedStatement = connection.prepareStatement(debit_query);
                        PreparedStatement credit_preparedStatement = connection.prepareStatement(credit_query);
                        debit_preparedStatement.setDouble(1, amount);
                        debit_preparedStatement.setLong(2, sender_account_number);

                        credit_preparedStatement.setDouble(1, amount);
                        credit_preparedStatement.setLong(2, receiver_account_number);

                        int rowAffected1 = debit_preparedStatement.executeUpdate();
                        int rowAffected2 = credit_preparedStatement.executeUpdate();

                        if (rowAffected1>0 && rowAffected2>0){
                            System.out.println("Transcation Successfull!");
                            System.out.println("Rs." +amount+ " Transferred Successfully.");
                            connection.commit();
                            connection.setAutoCommit(true);
                            try {
                                String transfer_money = "INSERT INTO transfer_money (sender_account_number, reciever_account_number, amount) values (?,?,?);";
                                PreparedStatement preparedStatement2 = connection.prepareStatement(transfer_money);
                                preparedStatement2.setLong(1, sender_account_number);
                                preparedStatement2.setLong(2, receiver_account_number);
                                preparedStatement2.setDouble(3, amount);
                                preparedStatement2.executeUpdate();
                            }catch (SQLException e){
                                e.printStackTrace();
                            }
                        }
                        else {
                            System.out.println("Transcation Failed.");
                            connection.rollback();
                            connection.setAutoCommit(true);
                        }
                    }
                    else {
                        System.out.println("Insufficient Balance.");
                    }
                }
                else {
                    System.out.println("Invalid Security Pin.");
                }

            }
            else {
                System.out.println("Invalid account number.");
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        connection.setAutoCommit(true);
    }

    public void getBalance(long account_number){
        scanner.nextLine();
        System.out.println("Enter Security Pin : ");
        String security_pin = scanner.nextLine();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT balance FROM accounts WHERE account_number = ? AND security_pin = ? ;");
            preparedStatement.setLong(1, account_number);
            preparedStatement.setString(2, security_pin);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                double balance = resultSet.getDouble("balance");
                System.out.println("Balance : " + balance);
            }
            else {
                System.out.println("Invalid Pin.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



}
