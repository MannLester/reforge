package Login_Logout;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Login {
    private String user_name, pass_word,account_id;
    public boolean login(String username, String password) {
        user_name = username;
        pass_word = password;
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement statement = conn.prepareStatement(
                     "SELECT * FROM AccountsTable WHERE username = ? AND password = ?")) {
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            return false;
        }
    }
    public String getAccountId(String username, String password) {
        String account_id = "hi"; // Initialize the account_id to null

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement statement = conn.prepareStatement(
                     "SELECT Account_ID FROM AccountsTable WHERE username = ? AND password = ?")) {
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                // Retrieve the Account_ID if a row is found
                account_id = resultSet.getString("Account_ID");
            }
            else
            {
                System.out.println("No matching rows found for username and password: " + username);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception properly in your application
        }

        return account_id;
    }
    public String getUsername() {
        return user_name;
    }

    public String getPassword() {
        return pass_word;
    }

    public void loginInput(Scanner scanner) {
        System.out.print("Enter username: ");
        String loginUsername = scanner.next();
        System.out.print("Enter password: ");
        String loginPassword = scanner.next();

        System.out.println();

        if (login(loginUsername, loginPassword)) {
            System.out.println("Login_Logout.Login successful!");
            getAccountId(loginUsername, loginPassword);
        } else {
            System.out.println("Login_Logout.Login failed. Please check your credentials.");
        }
    }
}
