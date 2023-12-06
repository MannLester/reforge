import javax.swing.*;
import java.awt.*;

public class GameNotif extends JFrame {
    String root = "C:\\Users\\User\\Reforge2\\src\\assets\\";
    int width = 535;
    int height = 188;
    JLabel notifBg, notifText;
    ImageIcon bg_for_notif = new ImageIcon(root + "Battlefield\\profile_panel.png");
    GameNotif(String text){
        this.setVisible(true);
        this.setSize(width, height);
        this.setTitle("Notification");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setResizable(false);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (dim.width - getWidth()) / 2;
        int y = (dim.height - getHeight()) / 2;

        setLocation(x, y);
        this.setLayout(null);

        notifBg = new JLabel();
        notifBg.setBounds(0,0,520,150);
        notifBg.setIcon(bg_for_notif);

        notifText = new JLabel();
        notifText.setFont(new Font("Arial", Font.BOLD, 15));
        notifText.setHorizontalAlignment(JLabel.CENTER);
        notifText.setBounds(20,50, 450,50);
        notifText.setText(text);

        this.add(notifText);
        this.add(notifBg);
    }
}
