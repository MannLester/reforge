import Login_Logout.Register;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Signup extends JFrame implements ActionListener {
    Register register = new Register();
    private JTextField usernameField;
    private JPasswordField passwordField;

    String root = "C:\\Users\\User\\Reforge2\\src\\assets\\";
    int width = 1005;
    int height = 725;
    ImageIcon signup_img = new ImageIcon(root + "Registration\\login_bg.jpg");
    ImageIcon title = new ImageIcon(root + "Registration\\title.png");
    ImageIcon signupButton_pic = new ImageIcon(root + "Registration\\signup_button2.png");
    JButton signup_button;
    JLabel signup_label, title_label, username_label, password_label;

    Signup(){
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

        signup_label = new JLabel();
        signup_label.setIcon(signup_img);
        signup_label.setBounds(0,0,width,height);

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

        signup_button = new JButton();
        signup_button.setIcon(signupButton_pic);
        signup_button.setBounds(350,600,290,63);
        signup_button.setContentAreaFilled(false);
        signup_button.setBorderPainted(false);
        signup_button.setFocusable(false);
        signup_button.setVisible(true);
        signup_button.addActionListener(this);

        this.add(signup_button);
        this.add(password_label);
        this.add(username_label);
        this.add(title_label);
        this.add(signup_label);

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

    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource()==signup_button){
            register.register(usernameField.getText(), passwordField.getText());
            SwingUtilities.invokeLater(()-> new GameNotif("You are Signed up!"));
            Signup.this.dispose();
            new Login();
        }
    }
}