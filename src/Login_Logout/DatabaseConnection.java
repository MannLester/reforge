package Login_Logout;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    /*
    CREATE DATABASE DB;
    USE DB;
    CREATE TABLE AccountsTable (Account_ID VARCHAR(16) PRIMARY KEY , Username VARCHAR(16) UNIQUE, Password VARCHAR(16));
    */

    // IntelliJ IDEA > File > Project Structure > Modules > Dependencies > Add > JARs or Directories > Select file

    private static final String URL = "jdbc:mariadb://localhost:3306/DB1";
    private static final String USERNAME = "root"; // yourUsername
    private static final String PASSWORD = ""; // yourPassword

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }
}
