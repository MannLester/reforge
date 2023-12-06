import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JFrame implements ActionListener {
    String root = "C:\\Users\\User\\Reforge2\\src\\assets\\";
    int width = 1520;
    int height = 820;
    ImageIcon menu_img = new ImageIcon(root + "Menu\\mainMenu_bg.jpg");
    ImageIcon menu_title = new ImageIcon(root + "Menu\\titleMenu.png");
    ImageIcon menu_design  = new ImageIcon(root + "Menu\\menuDesign.png");
    ImageIcon menu_design2 = new ImageIcon(root + "Menu\\menuDesign2.png");
    ImageIcon startButton_pic = new ImageIcon(root + "Menu\\start_button.png");
    ImageIcon continueButton_pic = new ImageIcon(root + "Menu\\continue_button.png");
    ImageIcon quitButton_pic = new ImageIcon(root + "Menu\\quit_button.png");
    JLabel menu_label, menu_label2, menuDesign1, menuDesign2;
    JButton start_button, continue_button, quit_button;
    Menu(boolean fromLogin) {
        this.setSize(width, height);
        this.setVisible(true);
        this.setTitle("Main Menu");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.requestFocusInWindow();

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (dim.width - getWidth()) / 2;

        setLocation(x, 0);

        setLayout(null);

        menu_label = new JLabel();
        menu_label.setIcon(menu_img);
        menu_label.setBounds(0, 0, width, height);

        menu_label2 = new JLabel();
        menu_label2.setIcon(menu_title);
        menu_label2.setBounds(375, 0, 800, 400);

        menuDesign1 = new JLabel();
        menuDesign1.setIcon(menu_design);
        menuDesign1.setBounds(0,0,183,810);

        menuDesign2 = new JLabel();
        menuDesign2.setIcon(menu_design2);
        menuDesign2.setBounds(1325,0,183,810);

        start_button = new JButton();
        start_button.setIcon(startButton_pic);
        start_button.setBounds(615,450,290,63);
        start_button.setContentAreaFilled(false);
        start_button.setBorderPainted(false);
        start_button.setOpaque(false);
        start_button.setVisible(true);
        start_button.addActionListener(this);

        continue_button = new JButton();
        continue_button.setIcon(continueButton_pic);
        continue_button.setBounds(615, 563, 290,63);
        continue_button.setContentAreaFilled(false);
        continue_button.setBorderPainted(false);
        continue_button.setOpaque(false);
        continue_button.setVisible(true);
        continue_button.addActionListener(this);

        quit_button = new JButton();
        quit_button.setIcon(quitButton_pic);
        quit_button.setBounds(615,676,290,63);
        quit_button.setContentAreaFilled(false);
        quit_button.setBorderPainted(false);
        quit_button.setOpaque(false);
        quit_button.setVisible(true);
        quit_button.addActionListener(this);

        if (fromLogin) {
            continue_button.setEnabled(true);
            start_button.setEnabled(false);
        } else {
            continue_button.setEnabled(false);
            start_button.setEnabled(true);
        }

        this.add(start_button);
        this.add(continue_button);
        this.add(quit_button);
        this.add(menuDesign2);
        this.add(menuDesign1);
        this.add(menu_label2);
        this.add(menu_label);
    }

    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource()==start_button || e.getSource()==continue_button){
            SwingUtilities.invokeLater(()->{
                Menu.this.dispose();
                Valoria valoria = new Valoria(550,420);
                valoria.Quadrant1();
            });
        }else{
            System.out.println("You Quit!");
        }
    }
}