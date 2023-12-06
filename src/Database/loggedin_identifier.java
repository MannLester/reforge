package Database;

import Login_Logout.DatabaseConnection;

import java.sql.*;

public class loggedin_identifier {
    String value = null;

    public String readValue(String tableName, String valueColumn) {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement statement = conn.prepareStatement(
                     "SELECT " + valueColumn + " FROM " + tableName)) {
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                value = resultSet.getString(valueColumn);
            } else {
                System.out.println("aw1 No data located for URL: ");

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return value;
    }
    private static final String JDBC_URL = "jdbc:mariadb://localhost:3306/DB1"; // Replace with your actual database URL
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";
    public static void updateTable(String tableName, String column_to_update, String column_identifier, String identifier, String value) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
            // Prepare the SQL statement
            String sql = "UPDATE " + tableName + " SET " + column_to_update + " = ? WHERE "+ column_identifier +" = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                // Set the new value and identifier
                preparedStatement.setString(1, value);
                preparedStatement.setString(2, identifier);

                // Execute the update
                int rowsAffected = preparedStatement.executeUpdate();
                System.out.println(identifier + " is identifier.");
                System.out.println(rowsAffected + " row(s) updated successfully.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public String getLoggedInAccount() {
        return value;
    }
}
