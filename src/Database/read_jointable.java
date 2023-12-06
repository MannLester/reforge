package Database;

//import Login_Logout.DatabaseConnection;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
//public class read_jointable {
//    public String readjoinValue(  String account, String valueColumn) {
//        String value = null;
//        try (Connection conn = DatabaseConnection.getConnection();
//             PreparedStatement statement = conn.prepareStatement(
//                     "SELECT " + valueColumn + " FROM INVENTORY JOIN ITEM ON INVENTORY.ITEM_ID=ITEM.ITEM_ID WHERE ACCOUNT_ID = " + "?")) {
//            statement.setString(1, account);
//            ResultSet resultSet = statement.executeQuery();
//
//            if (resultSet.next()) {
//                value = resultSet.getString(valueColumn);
//            } else {
//                System.out.println("aw No data located for account: " + account);
//
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return value;
//    }
//}

import Login_Logout.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class read_jointable {
    public static String[] readjoinValues(String account, String valueColumn) {
        String[] values = null;
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement statement = conn.prepareStatement(
                     "SELECT " + valueColumn + " FROM INVENTORY JOIN ITEM ON INVENTORY.ITEM_ID=ITEM.ITEM_ID WHERE ACCOUNT_ID = ?")) {
            statement.setString(1, account);
            ResultSet resultSet = statement.executeQuery();

            int rowCount = 0;
            while (resultSet.next()) {
                rowCount++;
            }

            if (rowCount > 0) {
                values = new String[rowCount];
                resultSet.beforeFirst(); // Reset the cursor position
                int i = 0;
                while (resultSet.next()) {
                    values[i] = resultSet.getString(valueColumn);
                    i++;
                }
            } else {
                System.out.println("No data located for account: " + account);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return values;
    }
}
