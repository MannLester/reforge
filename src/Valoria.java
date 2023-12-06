import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Valoria extends JFrame implements ActionListener {
    int width = 100;
    int height = 100;
    int moveX = 15;
    int moveY = 10;
    boolean area1 = true;
    boolean area2 = false;
    boolean area3 = false;
    boolean area4 = false;
    boolean isOwl = true;
    ImageIcon[] walkingLeft;
    ImageIcon[] walkingRight;
    ImageIcon[] walkingFront;
    ImageIcon[] walkingBack;
    int currentSpriteIndex = 0;
    int x, y;
    String root = "C:\\Users\\User\\Reforge2\\src\\assets\\";
    String valoriaBg = root + "Backgrounds\\Valoria_bg.jpg";
    ImageIcon quadrant1 = new ImageIcon(root + "Cities\\Valoria\\valoria1.jpg");
    ImageIcon quadrant2 = new ImageIcon(root + "Cities\\Valoria\\valoria2.jpg");
    ImageIcon quadrant3 = new ImageIcon(root + "Cities\\Valoria\\valoria3.jpg");
    ImageIcon quadrant4 = new ImageIcon(root + "Cities\\Valoria\\valoria4.jpg");
    ImageIcon player = new ImageIcon(root + "characterSprites\\Stand.png");
    ImageIcon menuButton_pic = new ImageIcon(root + "Buttons\\misc\\menuButton.png");
    String bossPass_pic  = root + "Bosses\\TrainingDummy.png";
    JLabel valoria1_bg, valoria2_bg, valoria3_bg, valoria4_bg, player_bg, bldg_label, bldg_label_bg;
    JButton menuButton;

    Valoria(int xPos, int yPos) {
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

        menuButton = new JButton();
        menuButton.setIcon(menuButton_pic);
        menuButton.setContentAreaFilled(false);
        menuButton.setBorderPainted(false);
        menuButton.setOpaque(false);
        menuButton.setBounds(10,10,100,100);
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
        setBannerText("Valoria", 80);

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

    public void Animation() {
        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();
                if (keyCode == KeyEvent.VK_LEFT) {
                    if (area1) {
                        // trees
                        if (x > 355) {
                            x -= moveX;
                        }
                    } else if (area2) {
                        // stone walls
                        if (!((x == 795) && (y >= 120) && (y <= 360))) {
                            if (!((x == 120) && (y >= 160) && (y <= 320))) {
                                if (!((x == 150) && (y >= 330) && (y <= 360))) {

                                    // trees
                                    if (!((x == 750) && (y > 0) && (y <= 120))) {
                                        x -= moveX;
                                    }
                                }
                            }
                        }

                        // goes to area 1
                        if (x < 0) {
                            x = 1420;
                            valoria2_bg.setVisible(false);
                            Quadrant1();
                        }
                    } else if (area3) {
                        // trees
                        if (!((x == 670) && (y >= 410) && (y <= 610))) {
                            if (!((x == 1375) && (y >= 410))) {
                                if (!((x == 355) && (y >= 210))) {
                                    if (!((x == 235) && (y >= 40))) {
                                        x -= moveX;
                                    }
                                }
                            }
                        }
                    } else if (area4) {
                        // tree
                        if (!((x == 480) && (y >= 570))) {
                            x -= moveX;
                        }

                        // goes to area 3
                        if (x < 0) {
                            x = 1420;
                            valoria4_bg.setVisible(false);
                            Quadrant3();
                        }
                    }

                    currentSpriteIndex = (currentSpriteIndex + 1) % walkingLeft.length;

                    player_bg.setIcon(walkingLeft[currentSpriteIndex]);
                    player_bg.setBounds(x, y, width, height);
                    StoreEntrance();
                } else if (keyCode == KeyEvent.VK_RIGHT) {
                    if (area1) {
                        // goes to area 2
                        x += moveX;

                        if (x > 1420) {
                            x = 0;
                            valoria1_bg.setVisible(false);
                            Quadrant2();
                        }
                    } else if (area2) {
                        // stone walls
                        if (!((x == 60) && (y > 0) && (y <= 360))) {
                            if (!((x == 735) && (y >= 160) && (y <= 320))) {
                                if (!((x == 690) && (y >= 330) && (y <= 360))) {
                                    // trees
                                    if (((x < 1065) && (y <= 40)) || (x < 945)) {
                                        x += moveX;
                                    }
                                }
                            }
                        }
                    } else if (area3) {
                        // trees
                        if (!((x == 490) && (y >= 410) && (y <= 610))) {
                            if (!((x == 1195) && (y >= 410) && (y <= 610))) {
                                //stone walls
                                if (!((x == 1315) && (y >= 620) && (y < 800))) {
                                    x += moveX;
                                }
                            }
                        }

                        // goes to area 4
                        if (x > 1420) {
                            x = 0;
                            valoria3_bg.setVisible(false);
                            Quadrant4();
                        }
                    } else if (area4) {
                        // trees
                        if (!((x == 945) && (y >= 170))) {
                            if (!((x == 810) && (y >= 30) && (y <= 170))) {
                                if (!((x == 315) && (y >= 570))) {
                                    x += moveX;
                                }
                            }
                        }
                    }

                    currentSpriteIndex = (currentSpriteIndex + 1) % walkingRight.length;

                    player_bg.setIcon(walkingRight[currentSpriteIndex]);
                    player_bg.setBounds(x, y, width, height);
                    StoreEntrance();
                } else if (keyCode == KeyEvent.VK_UP) {
                    if (area1) {
                        // goes to area 3
                        if (x >= 1375 && y <= 0) {
                            valoria1_bg.setVisible(false);
                            y = 700;
                            Quadrant3();
                        } else if (y != 0) { // stone walls
                            y -= moveY;
                        }
                    } else if (area2) {
                        // trees
                        if (!(x > 945 && y <= 0)) {
                            if (!((x > 315) && (x < 480) && y <= 15)) {
                                // stone walls
                                if (!((y == 370) && (x <= 150) && (x >= 75))) {
                                    if (!((y == 370) && (x <= 795) && (x >= 705))) {
                                        if (!((y == 160) && (x <= 735) && (x >= 120))) {
                                            y -= moveY;

                                            // goes to area 4
                                            if (x < 945 && y <= 0) {
                                                valoria2_bg.setVisible(false);
                                                y = 700;
                                                Quadrant4();
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    } else if (area3) {
                        if (!((y == 620) && (x >= 505) && (x <= 655))) {
                            if (!((y == 620) && (x >= 1210) && (x <= 1360))) {
                                if (y >= 50) {
                                    y -= moveY;
                                }
                            }
                        }
                    } else if (area4) {
                        if (!((y == 180) && (x >= 825) && (x <= 945))) {
                            if (y >= 50) {
                                y -= moveY;
                            }
                        }
                    }

                    currentSpriteIndex = (currentSpriteIndex + 1) % walkingBack.length;

                    player_bg.setIcon(walkingBack[currentSpriteIndex]);
                    player_bg.setBounds(x, y, width, height);

                    StoreEntrance();
                } else if (keyCode == KeyEvent.VK_DOWN) {
                    if (area1) {
                        // trees
                        if (y < 520) {
                            y += moveY;
                        }
                    } else if (area2) {
                        // trees
                        if ((x > 945 && y <= 30) || (x < 945 && y < 520)) {
                            if (!((y == 0) && (x >= 75) && (x <= 735))) {
                                // stone walls
                                if (!((y == 110) && (x >= 750) && (x <= 780))) {
                                    if (!((y == 320) && (x >= 120) && (x <= 150))) {
                                        if (!((y == 320) && (x >= 705) && (x <= 735))) {
                                            y += moveY;
                                        }
                                    }
                                }
                            }
                        }
                    } else if (area3) {
                        // trees
                        if (!((y == 200) && (x >= 235) && (x <= 350))) {
                            if (!((y == 400) && (x >= 1210) && (x <= 1360))) {
                                if (!((y == 400) && (x >= 505) && (x <= 655))) {
                                    // stone walls
                                    if (!((y == 700) && (x >= 355) && (x <= 1315))) {
                                        y += moveY;
                                    }
                                }
                            }
                        }

                        // goes to area 1
                        if (y > 700) {
                            valoria3_bg.setVisible(false);
                            y = 0;
                            Quadrant1();
                        }
                    } else if (area4) {
                        if (!((y == 560) && (x >= 300) && (x <= 465))) {
                            y += moveY;
                        }

                        // goes to area 2
                        if (y > 700) {
                            valoria4_bg.setVisible(false);
                            y = 0;
                            Quadrant2();
                        }
                    }

                    currentSpriteIndex = (currentSpriteIndex + 1) % walkingFront.length;

                    player_bg.setIcon(walkingFront[currentSpriteIndex]);
                    player_bg.setBounds(x, y, width, height);
                    StoreEntrance();
                } else if (keyCode == KeyEvent.VK_ENTER) {
                    if ("Merchant".equals(bldg_label.getText())) {
                        SwingUtilities.invokeLater(Merchant::new);
                    } else if ("Pet Sanctuary".equals(bldg_label.getText())) {
                        SwingUtilities.invokeLater(PetSanctuary::new);
                    } else if ("Brewing".equals(bldg_label.getText())) {
                        SwingUtilities.invokeLater(Brewing::new);
                    } else if ("Blacksmith".equals(bldg_label.getText())) {
                        SwingUtilities.invokeLater(Blacksmith::new);
                    } else if ("Training Area".equals(bldg_label.getText())) {
                     SwingUtilities.invokeLater(()->{
                         new ItemPicking(valoriaBg, bossPass_pic);
                     });
                    }
                } else if (keyCode == KeyEvent.VK_M){
                    SwingUtilities.invokeLater(()->{
                        if(isOwl){
                            Valoria.this.dispose();
                        }
                        new GameMap(isOwl);
                    });
                } else if (keyCode == KeyEvent.VK_P){
                    SwingUtilities.invokeLater(PlayerProfile::new);
                }

            }

            @Override
            public void keyReleased(KeyEvent e) {
                // Not used in this example
            }
        });

        // Make the frame focusable to receive key events
        this.setFocusable(true);
        this.requestFocusInWindow();
    }

    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource()==menuButton){
            Valoria.this.dispose();
            new Menu(true);
        }
    }
    public void Quadrant1() {
        area1 = true;
        area2 = false;
        area3 = false;
        area4 = false;

        valoria1_bg = new JLabel();
        valoria1_bg.setBounds(0, 0, 1520, 820);
        valoria1_bg.setVisible(true);
        valoria1_bg.setIcon(quadrant1);

        this.add(valoria1_bg);

        player_bg.setBounds(x, y, width, height);
    }

    public void Quadrant2() {
        area1 = false;
        area2 = true;
        area3 = false;
        area4 = false;

        valoria2_bg = new JLabel();
        valoria2_bg.setBounds(0, 0, 1520, 820);
        valoria2_bg.setIcon(quadrant2);
        valoria2_bg.setVisible(true);

        this.add(valoria2_bg);
    }

    public void Quadrant3() {
        area1 = false;
        area2 = false;
        area3 = true;
        area4 = false;

        valoria3_bg = new JLabel();
        valoria3_bg.setBounds(0, 0, 1520, 820);
        valoria3_bg.setIcon(quadrant3);
        valoria3_bg.setVisible(true);

        this.add(valoria3_bg);
    }

    public void Quadrant4() {
        area1 = false;
        area2 = false;
        area3 = false;
        area4 = true;

        valoria4_bg = new JLabel();
        valoria4_bg.setBounds(0, 0, 1520, 820);
        valoria4_bg.setIcon(quadrant4);
        valoria4_bg.setVisible(true);

        player_bg.setBounds(x, y, width, height);

        this.add(valoria4_bg);
    }

    public void StoreEntrance() {
        if (area2 && (x >= 100) && (x <= 600) && (y >= 100) && (y <= 300)) {
            setBannerText("Pet Sanctuary", 70);
        } else if (area1 && (x >= 1000) && (x <= 1200) && (y <= 220)) {
            setBannerText("Merchant", 80);
        } else if (area3 && (x >= 730) && (x <= 1420) && (y <= 240)) {
            setBannerText("Training Area", 70);
        } else if (area4 && (x >= 0) && (x <= 195) && y <= 150) {
            setBannerText("Brewing", 80);
        } else if (area4 && (x >= 450) && (x <= 810) && (y >= 210) && (y <= 440)) {
            setBannerText("Blacksmith", 80);
        } else {
            setBannerText("Valoria", 80);
        }
    }

    public void setBannerText(String text, int size) {
        bldg_label.setText(text);
        bldg_label.setFont(new Font("Brush Script MT", Font.PLAIN, size));
    }

    // Other methods and QuadrantX functions remain unchanged
}