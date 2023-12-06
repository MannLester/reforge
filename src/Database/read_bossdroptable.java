package Database;

import Login_Logout.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class read_bossdroptable {
    public static String[] readjoindropValues(String drop_id, String[] valueColumns, String table1, String table2, String indicator, String limit) {
        String[] values = null;
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement statement = conn.prepareStatement(
                     "SELECT " + String.join(", ", valueColumns) + " FROM "+ table1 + " RIGHT JOIN " + table2 + " ON " + table1 + "." + indicator +"=" + table2 + "." + indicator + " WHERE " +  table1 +"." + indicator+ "=? " + limit)) {
            statement.setString(1, drop_id);

            ResultSet resultSet = statement.executeQuery();

            int rowCount = 0;
            while (resultSet.next()) {
                rowCount++;
            }

            if (rowCount > 0) {
                values = new String[rowCount * valueColumns.length];
                resultSet.beforeFirst(); // Reset the cursor position
                int i = 0;
                System.out.println("DATABASE HERE");
                while (resultSet.next()) {
                    for (String column : valueColumns) {
                        values[i] = resultSet.getString(column);
                        System.out.println(column + ": " + values[i]);
                        i++;
                    }
                }
            } else {
                System.out.println("No data located for DROP_ID: " + drop_id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return values;
    }
}
