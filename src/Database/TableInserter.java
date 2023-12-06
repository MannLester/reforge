package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TableInserter {

    private static final String JDBC_URL = "jdbc:mariadb://localhost:3306/DB1"; // Replace with your actual database URL
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";

    public static void insertIntoTable(String tableName, String[] columns, String[] values) {
        if (columns.length != values.length) {
            throw new IllegalArgumentException("Columns and values arrays must have the same length");
        }

        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
            // Prepare the SQL statement
            StringBuilder sqlBuilder = new StringBuilder("INSERT INTO " + tableName + " (");
            for (String column : columns) {
                sqlBuilder.append(column).append(",");
            }
            sqlBuilder.deleteCharAt(sqlBuilder.length() - 1); // Remove the trailing comma
            sqlBuilder.append(") VALUES (");
            for (int i = 0; i < values.length; i++) {
                sqlBuilder.append("?,");
            }
            sqlBuilder.deleteCharAt(sqlBuilder.length() - 1); // Remove the trailing comma
            sqlBuilder.append(")");

            try (PreparedStatement preparedStatement = connection.prepareStatement(sqlBuilder.toString())) {
                // Set values for each parameter
                for (int i = 0; i < values.length; i++) {
                    preparedStatement.setObject(i + 1, values[i]);
                }

                // Execute the insert
                int rowsAffected = preparedStatement.executeUpdate();
                System.out.println(rowsAffected + " row(s) inserted successfully.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
