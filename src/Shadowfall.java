import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;

public class Shadowfall extends City {
    public Shadowfall(int xPos, int yPos) {
        super(xPos, yPos, "Shadowfall");
    }

    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource()==menuButton){
            Shadowfall.this.dispose();
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
                        if(!(x <= 595 && y <= 320)) {
                            x -= moveX;
                        }
                    } else if (area2) {
                        x -= moveX;
                        if (x < 0) {
                            x = 1420;
                            city2_bg.setVisible(false);
                            Quadrant1();
                        }
                    } else if (area3) {
                        if(!(x <= 595)){
                            x -= moveX;
                        }
                    } else if (area4) {
                        x -= moveX;
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

                        if (x > 1420) {
                            x = 0;
                            city1_bg.setVisible(false);
                            Quadrant2();
                        }
                    } else if (area2) {
                        if(!(x >= 1335)) {
                            x += moveX;
                        }
                    } else if (area3) {
                        x += moveX;
                        if (x > 1420) {
                            x = 0;
                            city3_bg.setVisible(false);
                            Quadrant4();
                        }
                    } else if (area4) {
                        if(!(x >= 1335)) {
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
                        if (y <= 0) {
                            city1_bg.setVisible(false);
                            y = 700;
                            Quadrant3();
                        }
                    } else if (area2) {
                        y -= moveY;
                        if (y <= 0) {
                            city2_bg.setVisible(false);
                            y = 700;
                            Quadrant4();
                        }
                    } else if (area3) {
                        if(!(y <= 20)) {
                            y -= moveY;
                        }
                    } else if (area4) {
                        if(!(y <= 20)) {
                            y -= moveY;
                        }
                    }

                    currentSpriteIndex = (currentSpriteIndex + 1) % walkingBack.length;

                    player_bg.setIcon(walkingBack[currentSpriteIndex]);
                    player_bg.setBounds(x, y, width, height);

                    StoreEntrance();
                } else if (keyCode == KeyEvent.VK_DOWN) {
                    if (area1) {
                        if(!(x >= 745 && y >= 320)) {
                            y += moveY;
                        }
                    } else if (area2) {
                        if(!(x <= 1335 && y >= 320)) {
                            y += moveY;
                        }
                    } else if (area3) {
                        y += moveY;
                        if (y > 700) {
                            city3_bg.setVisible(false);
                            y = 0;
                            Quadrant1();
                        }
                    } else if (area4) {
                        y += moveY;
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
                    if ("Renewable Energy".equals(bldg_label.getText())) {
                        SwingUtilities.invokeLater(() -> new ProgressPanel("Renewable Energy"));
                    } else if ("Energy Access Negotiation".equals(bldg_label.getText())) {
                        SwingUtilities.invokeLater(() -> new ProgressPanel("Energy Access Negotiation"));
                    } else if ("Sustainable Energy Practices".equals(bldg_label.getText())) {
                        SwingUtilities.invokeLater(() -> new ProgressPanel("Sustainable Energy Practices"));
                    } else if ("Community Development".equals(bldg_label.getText())) {
                        SwingUtilities.invokeLater(() -> new ProgressPanel("Community Development"));
                    }
                    else if ("Boss Battle".equals(bldg_label.getText())) {
                        SwingUtilities.invokeLater(() -> new ItemPicking(root + "Backgrounds\\Shadowfall_bg.jpg",root + "Bosses\\KingsEnforcer.png"));
                    }
                } else if (keyCode == KeyEvent.VK_M){
                    SwingUtilities.invokeLater(()->{
                        Shadowfall.this.dispose();
                        new GameMap(false);
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
        if (area2 && (x >= 870 && x <= 1215) && (y >= 0 && y <= 260)) {
            setBannerText("Renewable Energy", 60);
        } else if (area1 && (x >= 670 && x <= 1270) && (y >= 0 && y <= 280)) {
            setBannerText("Energy Access Negotiation", 45);
        } else if (area3 && (x >= 745 && x <= 1090) && (y >= 0 && y <= 370)) {
            setBannerText("Sustainable Energy Practices", 40);
        } else if (area4 && (x >= 840 && x <= 1260) && (y >= 70 && y <= 460)) {
            setBannerText("Community Development", 50);
        } else if (area4 && (x >= 30 && x <= 330) && (y >= 150 && y <= 660)) {
            setBannerText("Boss Battle", 80);
        } else {
            setBannerText("Shadowfall", 80);
        }
    }
}
