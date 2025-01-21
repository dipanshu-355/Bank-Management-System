import java.sql.*;
import java.util.Scanner;

public class Statement {
    private Connection connection;
    private Scanner scanner;

    public Statement(Connection connection, Scanner scanner) {
        this.connection = connection;
        this.scanner = scanner;
    }
    public void credit_statement(long account_number) throws SQLException {
        scanner.nextLine();
        System.out.println("Enter Security Pin : ");
        String security_pin = scanner.nextLine();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM accounts WHERE account_number = ? AND security_pin = ? ;");
            preparedStatement.setLong(1, account_number);
            preparedStatement.setString(2,security_pin);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()){
                PreparedStatement preparedStatement2 = connection.prepareStatement("SELECT * FROM credit WHERE account_number = ?;");
                preparedStatement2.setLong(1, account_number);
                ResultSet resultSet2 = preparedStatement2.executeQuery();
                System.out.println("Credit Statement : ");
                System.out.println("+-------------------+----------------+--------------------+-----------------------+");
                System.out.println("|  Transaction ID   | Account Number |   Credit Amount     |          Date         |");
                System.out.println("+-------------------+----------------+--------------------+-----------------------+");

                while(resultSet2.next()){
                    int Id = resultSet2.getInt("id");
                    long accountNumber = Long.parseLong(String.valueOf(resultSet2.getLong("account_number")));
                    double amount = resultSet2.getDouble("amount");
                    String Date = resultSet2.getTimestamp("date").toString();
//                      Format and display the reservation date in a table like-format
                    System.out.printf("| %-17d | %-14d | %-18f | %-18s |\n",
                            Id, accountNumber, amount, Date);
                }

            }
            else {
                System.out.println("Invalid Security Pin.");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void debit_statement(long account_number) throws SQLException {
        scanner.nextLine();
        System.out.println("Enter Security Pin : ");
        String security_pin = scanner.nextLine();
        try {
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM accounts WHERE account_number = ? AND security_pin = ? ;");
                preparedStatement.setLong(1, account_number);
                preparedStatement.setString(2,security_pin);
                ResultSet resultSet = preparedStatement.executeQuery();

                if (resultSet.next()){
                    PreparedStatement preparedStatement2 = connection.prepareStatement("SELECT * FROM debit WHERE account_number = ?;");
                    preparedStatement2.setLong(1, account_number);
                    ResultSet resultSet2 = preparedStatement2.executeQuery();
                    System.out.println("Debit Statement : ");
                    System.out.println("+-------------------+----------------+--------------------+-----------------------+");
                    System.out.println("|  Transaction ID   | Account Number |   Debit Amount     |          Date         |");
                    System.out.println("+-------------------+----------------+--------------------+-----------------------+");

                    while(resultSet2.next()){
                        int Id = resultSet2.getInt("id");
                        long accountNumber = Long.parseLong(String.valueOf(resultSet2.getLong("account_number")));
                        double amount = resultSet2.getDouble("amount");
                        String Date = resultSet2.getTimestamp("date").toString();
//                      Format and display the reservation date in a table like-format
                        System.out.printf("| %-17d | %-14d | %-18f | %-18s |\n",
                                Id, accountNumber, amount, Date);
                    }

                }
                    else {
                        System.out.println("Invalid Security Pin.");
                    }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void transfer_statement(long account_number) throws SQLException {
        scanner.nextLine();
        System.out.println("Enter Security Pin : ");
        String security_pin = scanner.nextLine();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM accounts WHERE account_number = ? AND security_pin = ? ;");
            preparedStatement.setLong(1, account_number);
            preparedStatement.setString(2,security_pin);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()){
                PreparedStatement preparedStatement2 = connection.prepareStatement("SELECT * FROM transfer_money WHERE sender_account_number = ?;");
                preparedStatement2.setLong(1, account_number);
                ResultSet resultSet2 = preparedStatement2.executeQuery();
                System.out.println("Transfer Statement : ");
                System.out.println("+-------------------+------------------------+----------------------------+----------------------+-----------------------+");
                System.out.println("|  Transaction ID   | Sending Account Number |  Receiving Account Number  |  Transfer Amount     |          Date         |");
                System.out.println("+-------------------+------------------------+----------------------------+----------------------+-----------------------+");

                while(resultSet2.next()){
                    int Id = resultSet2.getInt("id");
                    long senderAccountNumber = Long.parseLong(String.valueOf(resultSet2.getLong("sender_account_number")));
                    long receiverAccountNumber = Long.parseLong(String.valueOf(resultSet2.getLong("reciever_account_number")));
                    double amount = resultSet2.getDouble("amount");
                    String Date = resultSet2.getTimestamp("date").toString();
//                      Format and display the reservation date in a table like-format
                    System.out.printf("| %-17d | %-22d | %-26d | %-20f | %-18s |\n",
                            Id, senderAccountNumber, receiverAccountNumber, amount, Date);
                }

            }
            else {
                System.out.println("Invalid Security Pin.");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

}