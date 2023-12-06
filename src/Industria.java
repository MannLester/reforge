import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;

public class Industria extends City {
    public Industria(int xPos, int yPos) {
        super(xPos, yPos, "Industria");
    }

    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource()==menuButton){
            Industria.this.dispose();
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
                        if (!(x <= 70)) {
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
                        if (!(x <= 190)) {
                            x -= moveX;
                        }
                    } else if (area4) {
                        x -= moveX;

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
                        if(!((x == 160) && (y >= 560))) {
                            x += moveX;
                        }

                        // goes to area 2
                        if (x > 1420) {
                            x = 0;
                            city1_bg.setVisible(false);
                            Quadrant2();
                        }
                    } else if (area2) {
                        if (!((x >= 810) && (y <= 510))) {
                            if(!((x >= 555) && (y >= 520))) {
                                x += moveX;
                            }
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
                        if (x > 1420) {
                            SwingUtilities.invokeLater(()->{
                                Industria.this.dispose();
                                Skyport skyport = new Skyport(50,50);
                                skyport.Quadrant1();
                            });
                        }

                        if (!((x >= 810) && ((y <= 470) || (y >= 650)))) {
                            x += moveX;
                        }
                    }

                    currentSpriteIndex = (currentSpriteIndex + 1) % walkingRight.length;

                    player_bg.setIcon(walkingRight[currentSpriteIndex]);
                    player_bg.setBounds(x, y, width, height);
                    StoreEntrance();
                } else if (keyCode == KeyEvent.VK_UP) {
                    if (area1) {
                        if (!(y <= 40)) {
                            y -= moveY;
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
                        if (!(y <= 150)) {
                            y -= moveY;
                        }
                    } else if (area4) {
                        if (!(y <= 0)) {
                            if (!((y <= 480) && (x >= 825))) {
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
                        if (y >= 700) {
                            SwingUtilities.invokeLater(()->{
                                Industria.this.dispose();
                                Rosewood rosewood = new Rosewood(1350,20);
                                rosewood.Quadrant3();
                            });
                        }

                        if (!((y == 550) && (x >= 175))) {
                            y += moveY;
                        }
                    } else if (area2) {
                        if (!((y >= 550) && (x <= 570))) {
                            if (!((y >= 510) && (x >= 570))) {
                                y += moveY;
                            }
                        }
                    } else if (area3) {
                        if (!(y >= 650)) {
                            y += moveY;
                        }

                        // goes to area 1
                        if (y > 700) {
                            city3_bg.setVisible(false);
                            y = 0;
                            Quadrant1();
                        }
                    } else if (area4) {
                        if (!((y >= 640) && (x >= 825))) {
                            y += moveY;
                        }

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
                    if ("Toxic Cleanup".equals(bldg_label.getText())) {
                        SwingUtilities.invokeLater(() -> new ProgressPanel("Toxic Cleanup"));
                    } else if ("Environmental Education".equals(bldg_label.getText())) {
                        SwingUtilities.invokeLater(() -> new ProgressPanel("Environmental Education"));
                    } else if ("Resource Management".equals(bldg_label.getText())) {
                        SwingUtilities.invokeLater(() -> new ProgressPanel("Resource Management"));
                    } else if ("Quality Assurance".equals(bldg_label.getText())) {
                        SwingUtilities.invokeLater(() -> new ProgressPanel("Quality Assurance"));
                    } else if ("Boss Battle".equals(bldg_label.getText())) {
                        SwingUtilities.invokeLater(() -> new ItemPicking(root + "Backgrounds\\Industria_bg.jpg",root + "Bosses\\HydrotoxicOverlord.png"));
                    }
                } else if (keyCode == KeyEvent.VK_M) {
                    SwingUtilities.invokeLater(() -> {
                        if(isOwl){
                            Industria.this.dispose();
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
        if (area1 && (x >= 565 && x <= 1350) && y <= 430){
            setBannerText("Toxic Cleanup", 80);
        }else if (area1 && (x >= 115 && x <= 505) && y <= 320){
            setBannerText("Environmental Education", 45);
        }else if(area4 && (x >= 390 && x <= 780) && (y >= 180 && y <= 460)){
            setBannerText("Resource Management", 50);
        }else if(area4 && (x >= 0 && x <= 195) && y <= 300){
            setBannerText("Quality Assurance", 60);
        }else if(area3 && (x >= 1240 && x <= 1375) && y <= 300){
            setBannerText("Quality Assurance", 60);
        }else if(area3 && (x >= 190 && x <= 820) && y <= 340){
            setBannerText("Boss Battle", 80);
        }else{
            setBannerText("Industria", 80);
        }
    }
}
