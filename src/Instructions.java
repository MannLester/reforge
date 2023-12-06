import javax.swing.*;
import java.awt.*;

public class Instructions extends JFrame {
    String root = "C:\\Users\\User\\Reforge2\\src\\assets\\";
    ImageIcon iconMap = new ImageIcon(root + "Buttons\\misc\\m_icon.png");
    ImageIcon iconProfile = new ImageIcon(root + "Buttons\\misc\\p_icon.png");
    ImageIcon iconArrows = new ImageIcon(root + "Buttons\\misc\\arrows_icon.png");
    ImageIcon iconEnter = new ImageIcon(root + "Buttons\\misc\\enter_icon.png");
    JButton menuButton;
    JLabel profile, profile_icon, map, map_icon, arrow_icon, arrow, enter, enter_icon;
    Instructions(){
    }

    public JLabel getProfileIcon(){
        profile_icon = new JLabel();
        profile_icon.setIcon(iconProfile);
        profile_icon.setBounds(20, 720,50,50);

        return profile_icon;
    }
    public JLabel getProfile(){
        profile = new JLabel();
        profile.setFont(new Font("Arial", Font.BOLD, 25));
        profile.setForeground(Color.WHITE);
        profile.setText("Profile");
        profile.setBounds(90,720,200,50);

        return  profile;
    }
    public JLabel getMapIcon(){
        map_icon = new JLabel();
        map_icon.setIcon(iconMap);
        map_icon.setBounds(200, 720, 50, 50);

        return map_icon;
    }
    public JLabel getMap(){
        map = new JLabel();
        map.setFont(new Font("Arial", Font.BOLD, 25));
        map.setForeground(Color.WHITE);
        map.setText("Map");
        map.setBounds(270,720,200,50);

        return map;
    }
    public JLabel getArrowIcon(){
        arrow_icon = new JLabel();
        arrow_icon.setIcon(iconArrows);
        arrow_icon.setBounds(360, 720, 200,50);

        return arrow_icon;
    }
    public JLabel getArrow(){
        arrow = new JLabel();
        arrow.setFont(new Font("Arial", Font.BOLD, 25));
        arrow.setForeground(Color.WHITE);
        arrow.setText("Move");
        arrow.setBounds(570, 720, 200,50);

        return arrow;
    }
    public JLabel getEnterIcon(){
        enter_icon = new JLabel();
        enter_icon.setIcon(iconEnter);
        enter_icon.setBounds(670, 720, 50,50);

        return enter_icon;
    }
    public JLabel getEnter(){
        enter = new JLabel();
        enter.setFont(new Font("Arial", Font.BOLD, 25));
        enter.setForeground(Color.WHITE);
        enter.setText("Enter");
        enter.setBounds(740,720, 200,50);

        return enter;
    }

}
