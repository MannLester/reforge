package Login_Logout;

import Login_Logout.DatabaseConnection;

import java.sql.*;
import java.util.Scanner;

public class Register {
    private String accountId, currencyId;
    public boolean register(String username, String password) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            conn.setAutoCommit(false);

            try {
                // First, get the row count from the Accounts table
                try (Statement statement = conn.createStatement()) {
                    ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) AS rowCount FROM Accounts");
                    int rowCount = 0;

                    if (resultSet.next()) {
                        rowCount = resultSet.getInt("rowCount");
                    }

                    accountId = "ACC-" + (rowCount + 1);
                    currencyId = "CURRENCY-" + (rowCount + 1);
                }

                // Now, insert into the Accounts table
                try (PreparedStatement insertStatementAccounts = conn.prepareStatement(
                        "INSERT INTO Accounts (Account_ID, username, password) VALUES (?, ?, ?)")) {
                    insertStatementAccounts.setString(1, accountId);
                    insertStatementAccounts.setString(2, username);
                    insertStatementAccounts.setString(3, password);
                    insertStatementAccounts.executeUpdate();
                }

                // Insert into the Currency table
                try (PreparedStatement insertStatementCurrency = conn.prepareStatement(
                        "INSERT INTO Currency (currency_id, GOLD_COUNT, IRON_COUNT, HERB_COUNT, BISCUIT_COUNT, DIAMOND_COUNT) VALUES (?, 1000, 1000, 1000, 1000, 1)")) {
                    insertStatementCurrency.setString(1, currencyId);
                    // Set other values for Currency table
                    insertStatementCurrency.executeUpdate();
                }
                try (PreparedStatement insertStatementAccounts = conn.prepareStatement(
                        "INSERT INTO players (Account_ID, WEAPONS_FORGED, PLAYER_HP, PLAYER_DMG, BOSSES_KILLED, PETS_TAMED, CURRENCY_ID, SHIELDS_BOUGHT, POTIONS_BREWED, PROGRESS_COMPLETED) VALUES (?, 5, 100, 30, 0, 0, ?, 0,0,0)")) {
                    insertStatementAccounts.setString(1, accountId);
                    insertStatementAccounts.setString(2, currencyId);
                    insertStatementAccounts.executeUpdate();
                }
                try (PreparedStatement insertStatementAccounts = conn.prepareStatement(
                        "INSERT INTO INVENTORY (Account_ID, ITEM_ID, ITEMTYPE) VALUES (?, ?, ?)")){
                    insertStatementAccounts.setString(1, accountId);
                    insertStatementAccounts.setString(2, "ITEM_01");
                    insertStatementAccounts.setString(3, "Weapon");
                    insertStatementAccounts.executeUpdate();

                }
                try (PreparedStatement insertStatementAccounts = conn.prepareStatement(
                        "INSERT INTO INVENTORY (Account_ID, ITEM_ID, ITEMTYPE) VALUES (?, ?, ?)")){
                    insertStatementAccounts.setString(1, accountId);
                    insertStatementAccounts.setString(2, "ITEM_02");
                    insertStatementAccounts.setString(3, "Weapon");
                    insertStatementAccounts.executeUpdate();

                }
                try (PreparedStatement insertStatementAccounts = conn.prepareStatement(
                        "INSERT INTO INVENTORY (Account_ID, ITEM_ID, ITEMTYPE) VALUES (?, ?, ?)")){
                    insertStatementAccounts.setString(1, accountId);
                    insertStatementAccounts.setString(2, "ITEM_11");
                    insertStatementAccounts.setString(3, "Artifact");
                    insertStatementAccounts.executeUpdate();

                }
                try (PreparedStatement insertStatementAccounts = conn.prepareStatement(
                        "INSERT INTO INVENTORY (Account_ID, ITEM_ID, ITEMTYPE) VALUES (?, ?, ?)")){
                    insertStatementAccounts.setString(1, accountId);
                    insertStatementAccounts.setString(2, "ITEM_12");
                    insertStatementAccounts.setString(3, "Artifact");
                    insertStatementAccounts.executeUpdate();

                }
                try (PreparedStatement insertStatementAccounts = conn.prepareStatement(
                        "INSERT INTO INVENTORY (Account_ID, ITEM_ID, ITEMTYPE) VALUES (?, ?, ?)")){
                    insertStatementAccounts.setString(1, accountId);
                    insertStatementAccounts.setString(2, "ITEM_21");
                    insertStatementAccounts.setString(3, "Potion");
                    insertStatementAccounts.executeUpdate();

                }
                try (PreparedStatement insertStatementAccounts = conn.prepareStatement(
                        "INSERT INTO INVENTORY (Account_ID, ITEM_ID, ITEMTYPE) VALUES (?, ?, ?)")){
                    insertStatementAccounts.setString(1, accountId);
                    insertStatementAccounts.setString(2, "ITEM_26");
                    insertStatementAccounts.setString(3, "Pet");
                    insertStatementAccounts.executeUpdate();

                }
                // Insert into another table, for example, if needed

                // Commit the transaction
                conn.commit();

                return true;
            } catch (SQLException e) {
                // Rollback the transaction if an exception occurs
                conn.rollback();
                System.out.println("Error while registering user: " + e.getMessage());
                return false;
            } finally {
                // Set auto-commit back to true when done
                conn.setAutoCommit(true);
            }
        } catch (SQLException e) {
            System.out.println("Error while registering user: " + e.getMessage());
            return false;
        }

    }

    public String get_accountId()
    {
        return accountId;
    }

//    public void registerInput(Scanner scanner) {
//        System.out.print("Enter username: ");
//        String username = scanner.next();
//        System.out.print("Enter password: ");
//        String password = scanner.next();
//
//        User newUser = new User();
//        newUser.setUsername(username);
//        newUser.setPassword(password);
//
//        System.out.println();
//
//        if (register(newUser)) {
//            System.out.println("Registration successful!");
//        } else {
//            System.out.println("Registration failed.");
//        }
//    }
}
