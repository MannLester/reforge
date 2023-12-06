package Database;

import Login_Logout.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class read_table {
    public String readValue(String tableName, String idColumn, String url, String valueColumn) {
        String value = null;
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement statement = conn.prepareStatement(
                     "SELECT " + valueColumn + " FROM " + tableName + " WHERE " + idColumn + " = ?")) {
            statement.setString(1, url);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                value = resultSet.getString(valueColumn);
            } else {
                System.out.println("aw1 No data located for URL: " + url);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return value;
    }

}

