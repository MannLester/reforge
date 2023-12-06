import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

abstract class City extends JFrame implements ActionListener {
    int width = 100;
    int height = 100;
    int moveX = 15;
    int moveY = 10;
    boolean area1 = true, area2 = false, area3 = false, area4 = false;
    boolean isOwl = true;
    ImageIcon[] walkingLeft, walkingRight, walkingFront, walkingBack;
    int currentSpriteIndex = 0;
    int x, y;
    String root = "C:\\Users\\User\\Reforge2\\src\\assets\\";
    ImageIcon quadrant1, quadrant2, quadrant3, quadrant4;
    ImageIcon player;
    ImageIcon menuButton_pic;
    JLabel city1_bg, city2_bg, city3_bg, city4_bg, player_bg, bldg_label, bldg_label_bg;
    JButton menuButton;

    City(int xPos, int yPos, String cityName) {
        this.setVisible(true);
        this.setSize(1520, 820);
        this.setTitle("");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLayout(null);

        walkingLeft = new ImageIcon[]{
                new ImageIcon(root + "characterSprites\\Left.png"),
                new ImageIcon(root + "characterSprites\\Lwalk1.png"),
                new ImageIcon(root + "characterSprites\\Lwalk2.png")
        };

        walkingRight = new ImageIcon[]{
                new ImageIcon(root + "characterSprites\\Right.png"),
                new ImageIcon(root + "characterSprites\\Rwalk1.png"),
                new ImageIcon(root + "characterSprites\\Rwalk2.png")
        };

        walkingFront = new ImageIcon[]{
                new ImageIcon(root + "characterSprites\\Stand.png"),
                new ImageIcon(root + "characterSprites\\Fwalk1.png"),
                new ImageIcon(root + "characterSprites\\Fwalk2.png")
        };

        walkingBack = new ImageIcon[]{
                new ImageIcon(root + "characterSprites\\Back.png"),
                new ImageIcon(root + "characterSprites\\Bwalk1.png"),
                new ImageIcon(root + "characterSprites\\Bwalk2.png")
        };

        x = xPos;
        y = yPos;

        quadrant1 = new ImageIcon(root + "Cities\\" + cityName + "\\" + cityName.toLowerCase() + "1.jpg");
        quadrant2 = new ImageIcon(root + "Cities\\" + cityName + "\\" + cityName.toLowerCase() + "2.jpg");
        quadrant3 = new ImageIcon(root + "Cities\\" + cityName + "\\" + cityName.toLowerCase() + "3.jpg");
        quadrant4 = new ImageIcon(root + "Cities\\" + cityName + "\\" + cityName.toLowerCase() + "4.jpg");
        player = new ImageIcon(root + "characterSprites\\Stand.png");
        menuButton_pic = new ImageIcon(root + "Buttons\\misc\\menuButton.png");

        menuButton = new JButton();
        menuButton.setIcon(menuButton_pic);
        menuButton.setContentAreaFilled(false);
        menuButton.setBorderPainted(false);
        menuButton.setOpaque(false);
        menuButton.setBounds(10, 10, 100, 100);
        menuButton.addActionListener(this);

        player_bg = new JLabel(walkingLeft[currentSpriteIndex]);
        player_bg.setBounds(x, y, width, height);
        player_bg.setIcon(player);

        ImageIcon label_pic = new ImageIcon(root + "Cities\\names.png");
        bldg_label_bg = new JLabel();
        bldg_label_bg.setIcon(label_pic);
        bldg_label_bg.setBounds(1000, 530, 854, 292);

        bldg_label = new JLabel();
        bldg_label.setBounds(1045, 620, 400, 80);
        bldg_label.setOpaque(false);
        bldg_label.setHorizontalAlignment(JLabel.CENTER);
        setBannerText(cityName, 80);

        Instructions instructions = new Instructions();

        this.add(menuButton);
        this.add(player_bg);
        this.add(bldg_label);
        this.add(bldg_label_bg);
        this.add(instructions.getProfileIcon());
        this.add(instructions.getProfile());
        this.add(instructions.getMapIcon());
        this.add(instructions.getMap());
        this.add(instructions.getArrowIcon());
        this.add(instructions.getArrow());
        this.add(instructions.getEnterIcon());
        this.add(instructions.getEnter());

        Animation();
    }

    public void Quadrant1() {
        area1 = true;
        area2 = false;
        area3 = false;
        area4 = false;

        city1_bg = new JLabel();
        city1_bg.setBounds(0, 0, 1520, 820);
        city1_bg.setVisible(true);
        city1_bg.setIcon(quadrant1);

        this.add(city1_bg);
    }

    public void Quadrant2() {
        area1 = false;
        area2 = true;
        area3 = false;
        area4 = false;

        city2_bg = new JLabel();
        city2_bg.setBounds(0, 0, 1520, 820);
        city2_bg.setIcon(quadrant2);
        city2_bg.setVisible(true);

        this.add(city2_bg);
    }

    public void Quadrant3() {
        area1 = false;
        area2 = false;
        area3 = true;
        area4 = false;

        city3_bg = new JLabel();
        city3_bg.setBounds(0, 0, 1520, 820);
        city3_bg.setIcon(quadrant3);
        city3_bg.setVisible(true);

        this.add(city3_bg);
    }

    public void Quadrant4() {
        area1 = false;
        area2 = false;
        area3 = false;
        area4 = true;

        city4_bg = new JLabel();
        city4_bg.setBounds(0, 0, 1520, 820);
        city4_bg.setIcon(quadrant4);
        city4_bg.setVisible(true);

        this.add(city4_bg);
    }

    public void setBannerText(String text, int size) {
        bldg_label.setText(text);
        bldg_label.setFont(new Font("Brush Script MT", Font.PLAIN, size));
    }

    @Override
    public abstract void actionPerformed(ActionEvent e);

    public abstract void Animation();
}
