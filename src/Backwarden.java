import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;

public class Backwarden extends City {
    public Backwarden(int xPos, int yPos) {
        super(xPos, yPos, "Backwarden");
    }

    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource()==menuButton){
            Backwarden.this.dispose();
            new Menu(true);
        }
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
                        if (!(x <= -20)) {
                            x -= moveX;
                        }
                    } else if (area2) {
                        x -= moveX;

                        // goes to area 1
                        if (x < 0) {
                            x = 1420;
                            city2_bg.setVisible(false);
                            Quadrant1();
                        }
                    } else if (area3) {
                        x -= moveX;
                    } else if (area4) {
                        if (!(x <= -20)) {
                            x -= moveX;
                        }

                        // goes to area 3
                        if (x < 0) {
                            x = 1420;
                            city4_bg.setVisible(false);
                            Quadrant3();
                        }
                    }

                    currentSpriteIndex = (currentSpriteIndex + 1) % walkingLeft.length;

                    player_bg.setIcon(walkingLeft[currentSpriteIndex]);
                    player_bg.setBounds(x, y, width, height);
                    StoreEntrance();
                } else if (keyCode == KeyEvent.VK_RIGHT) {
                    if (area1) {
                        x += moveX;

                        // goes to area 2
                        if (x > 1420) {
                            x = 0;
                            city1_bg.setVisible(false);
                            Quadrant2();
                        }
                    } else if (area2) {
                        if (!(x >= 1435)) {
                            x += moveX;
                        }
                    } else if (area3) {
                        x += moveX;

                        // goes to area 4
                        if (x > 1420) {
                            x = 0;
                            city3_bg.setVisible(false);
                            Quadrant4();
                        }
                    } else if (area4) {
                        if ((x >= 1435) && (y >= 290) && (y <= 420)) {
                            SwingUtilities.invokeLater(()->{
                                Backwarden.this.dispose();
                                Rusthaven rusthaven = new Rusthaven(50, 350);
                                rusthaven.Quadrant3();
                            });
                        }

                        if (!(x >= 1435)) {
                            x += moveX;
                        }
                    }

                    currentSpriteIndex = (currentSpriteIndex + 1) % walkingRight.length;

                    player_bg.setIcon(walkingRight[currentSpriteIndex]);
                    player_bg.setBounds(x, y, width, height);
                    StoreEntrance();
                } else if (keyCode == KeyEvent.VK_UP) {
                    if (area1) {
                        y -= moveY;

                        // goes to area 3
                        if (y < 0) {
                            city1_bg.setVisible(false);
                            y = 700;
                            Quadrant3();
                        }
                    } else if (area2) {
                        y -= moveY;

                        // goes to area 4
                        if (y < 0) {
                            city2_bg.setVisible(false);
                            y = 700;
                            Quadrant4();
                        }
                    } else if (area3) {
                        if ((y <= 0) && (x >= 190) && (x <= 565)) {
                            SwingUtilities.invokeLater(()->{
                                Backwarden.this.dispose();
                                Ghosthaven ghosthaven = new Ghosthaven(1470, 700);
                                ghosthaven.Quadrant4();
                            });
                        } else if (!(y <= 0)) {
                            y -= moveY;
                        }
                    } else if (area4) {
                        if (!(y <= 0)) {
                            y -= moveY;
                        }
                    }

                    currentSpriteIndex = (currentSpriteIndex + 1) % walkingBack.length;

                    player_bg.setIcon(walkingBack[currentSpriteIndex]);
                    player_bg.setBounds(x, y, width, height);

                    StoreEntrance();
                } else if (keyCode == KeyEvent.VK_DOWN) {
                    if (area1) {
                        if (!(y >= 700)) {
                            y += moveY;
                        }
                    } else if (area2) {
                        if ((y >= 700) && (x >= 90) && (x <= 315)) {
                            SwingUtilities.invokeLater(()->{
                                Backwarden.this.dispose();
                                Havenbrook havenbrook = new Havenbrook(20,20);
                                havenbrook.Quadrant4();
                            });
                        } else if (!(y >= 700)) {
                            y += moveY;

                        }
                    } else if (area3) {
                        y += moveY;

                        // goes to area 1
                        if (y > 700) {
                            city3_bg.setVisible(false);
                            y = 0;
                            Quadrant1();
                        }
                    } else if (area4) {
                        y += moveY;

                        // goes to area 2
                        if (y > 700) {
                            city4_bg.setVisible(false);
                            y = 0;
                            Quadrant2();
                        }
                    }

                    currentSpriteIndex = (currentSpriteIndex + 1) % walkingFront.length;

                    player_bg.setIcon(walkingFront[currentSpriteIndex]);
                    player_bg.setBounds(x, y, width, height);
                    StoreEntrance();
                } else if (keyCode == KeyEvent.VK_ENTER) {
                    if ("Innovation and Research".equals(bldg_label.getText())) {
                        SwingUtilities.invokeLater(()-> new ProgressPanel("Innovation and Research"));
                    } else if ("Mine Modernization".equals(bldg_label.getText())) {
                        SwingUtilities.invokeLater(()-> new ProgressPanel("Mine Modernization"));
                    } else if ("Workforce Training".equals(bldg_label.getText())) {
                        SwingUtilities.invokeLater(()-> new ProgressPanel("Workforce Training"));
                    } else if ("Resource Management".equals(bldg_label.getText())) {
                        SwingUtilities.invokeLater(()-> new ProgressPanel("Resource Management"));
                    } else if ("Boss Battle".equals(bldg_label.getText())) {
                        SwingUtilities.invokeLater(()-> new ItemPicking(root + "Backgrounds\\Backwarden_bg.jpg", root + "Bosses\\MayorStagnus.png"));
                    }
                } else if (keyCode == KeyEvent.VK_M) {
                    SwingUtilities.invokeLater(() -> {
                        if(isOwl){
                            Backwarden.this.dispose();
                        }
                        new GameMap(isOwl);
                    });
                }else if (keyCode == KeyEvent.VK_P){
                    SwingUtilities.invokeLater(PlayerProfile::new);
                }
                System.out.println(x + ", " + y);
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

    public void StoreEntrance() {
        if (area2 && (x >= 225 && x <= 525) && y <= 420){
            setBannerText("Innovation and Research", 45);
        }else if (area1 && (x >= 1225 && x <= 1400) && y <= 160){
            setBannerText("Mine Modernization", 60);
        }else if (area3 && (x >= 880 && x <= 1375) && y <= 310){
            setBannerText("Workforce Training", 60);
        }else if (area4 && (x >= 15 && x <= 210) && y >= 430){
            setBannerText("Resource Management", 50);
        }else if (area4 && (x >= 345 && x <= 720) && y <= 370){
            setBannerText("Boss Battle", 80);
        }else{
            setBannerText("Backwarden", 80);
        }
    }
}
