import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Database.TableUpdater;
import Database.loggedin_identifier;
import Login_Logout.DatabaseConnection;

public class Login extends JFrame implements ActionListener {
    TableUpdater tableupdate = new TableUpdater();
    loggedin_identifier logidentifier = new loggedin_identifier();
    private String user_name, pass_word,account_id;
    String account_loggedin;
    private JTextField usernameField;
    private JPasswordField passwordField;

    String root = "C:\\Users\\User\\Reforge2\\src\\assets\\";
    int width = 1005;
    int height = 725;
    ImageIcon login_img = new ImageIcon(root + "Registration\\login_bg.jpg");
    ImageIcon title = new ImageIcon(root + "Registration\\title.png");
    ImageIcon loginButton_pic = new ImageIcon(root + "Registration\\login_button.png");
    ImageIcon signupButton_pic = new ImageIcon(root + "Registration\\signup_button.png");
    JButton login_button, signup_button;
    JLabel login_label, title_label, username_label, password_label;
    Login(){
        this.setSize(width, height);
        this.setVisible(true);
        this.setTitle("Login");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.requestFocusInWindow();

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (dim.width - getWidth()) / 2;
        int  y = (dim.height - getHeight()) / 2;

        setLocation(x,y);

        setLayout(null);

        login_label = new JLabel();
        login_label.setIcon(login_img);
        login_label.setBounds(0,0,width,height);

        title_label = new JLabel();
        title_label.setIcon(title);
        title_label.setBounds(250,30,500,381);

        username_label = new JLabel();
        username_label.setBounds(310,400,100,20);
        username_label.setText("Username");
        username_label.setForeground(Color.WHITE);
        username_label.setFont(new Font("Arial", Font.BOLD,20));
        username_label.setOpaque(false);

        password_label = new JLabel();
        password_label.setBounds(310,500,100,20);
        password_label.setText("Password");
        password_label.setForeground(Color.WHITE);
        password_label.setFont(new Font("Arial", Font.BOLD, 20));
        password_label.setOpaque(false);

        login_button = new JButton();
        login_button.setIcon(loginButton_pic);
        login_button.setBounds(350,600,290,63);
        login_button.setContentAreaFilled(false);
        login_button.setBorderPainted(false);
        login_button.setFocusable(false);
        login_button.setVisible(true);
        login_button.addActionListener(this);

        signup_button = new JButton();
        signup_button.setIcon(signupButton_pic);
        signup_button.setBounds(760,20,200,43);
        signup_button.setContentAreaFilled(false);
        signup_button.setBorderPainted(false);
        signup_button.setFocusable(false);
        signup_button.setVisible(true);
        signup_button.addActionListener(this);

        this.add(signup_button);
        this.add(login_button);
        this.add(password_label);
        this.add(username_label);
        this.add(title_label);
        this.add(login_label);

        UserInput();
    }

    public void UserInput(){
        usernameField = new JTextField();
        usernameField.setBounds(309,430,380,40);
        usernameField.setForeground(Color.DARK_GRAY);
        usernameField.setHorizontalAlignment(JTextField.LEFT);
        usernameField.setOpaque(true);

        passwordField = new JPasswordField();
        passwordField.setBounds(309,530,380,40);
        passwordField.setForeground(Color.DARK_GRAY);
        passwordField.setHorizontalAlignment(JTextField.LEFT);
        passwordField.setOpaque(true);

        this.add(passwordField);
        this.add(usernameField);

        SwingUtilities.invokeLater(() -> {
            passwordField.requestFocusInWindow();
            usernameField.requestFocusInWindow();
        });
    }
    public boolean login(String username, String password) {
        user_name = username;
        pass_word = password;
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement statement = conn.prepareStatement(
                     "SELECT * FROM Accounts WHERE username = ? AND password = ?")) {
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next(); }
        catch (SQLException e) {
            return false;
        }
    }
    public String getAccountId(String username, char[] password) {
        account_id = ""; // Initialize the account_id to null
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement statement = conn.prepareStatement(
                     "SELECT Account_ID FROM Accounts WHERE username = ? AND password = ?")) {
            statement.setString(1, username);
            statement.setString(2, new String(password));
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) { // Retrieve the Account_ID if a row is found
                account_id = resultSet.getString("Account_ID");
            } else {
                System.out.println("No matching rows found for username and password: " + username);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception properly in your application
        } return account_id;
    }
    public String getUsername() {
        return user_name;
    }
    public String getPassword() {
        return pass_word;
    }
    public String getLoggedInAccount() {
        return account_loggedin;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == signup_button) {
            SwingUtilities.invokeLater(() -> {
                Login.this.dispose();
                new Signup();
            });
        } else {
            // Get the username and password entered by the user
            String enteredUsername = usernameField.getText();
            char[] enteredPassword = passwordField.getPassword();

            // Call your login method with the entered credentials
            boolean loginSuccessful = login(enteredUsername, new String(enteredPassword));

            // Check if login was successful and proceed accordingly
            if (loginSuccessful) {
                // Get the account ID for the logged-in user
                String accountId = getAccountId(enteredUsername, enteredPassword);

                // Print or use the username, password, and account ID as needed
                account_loggedin = accountId;

                System.out.println("Logged in as: " + enteredUsername);
                System.out.println("Account ID: " + accountId);
                System.out.println("Account logged in ID: " + account_loggedin);
                loggedin_identifier.updateTable("LOGGEDIN_IDENTIFIER","ACCOUNT_ID","ID","0",account_loggedin);

                // Open the menu or perform other actions
//                SwingUtilities.invokeLater(() -> {
                Login.this.dispose();
                new Menu(true);
//                });
            } else {
                // Handle failed login (e.g., show an error message)
                System.out.println("Login failed. Please check your credentials.");
                // You might want to show an error message or perform other actions here
            }
        }
    }


}