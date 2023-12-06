import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;

public class Havenbrook extends City {
    public Havenbrook(int xPos, int yPos) {
        super(xPos, yPos, "Havenbrook");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == menuButton) {
            Havenbrook.this.dispose();
            new Menu(true);
        }
    }

    @Override
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
                        if (x <= 100 && (y >= 10 && y <= 560)) {
                            x -= 0;
                        } else if (x <= 745 && (y >= 560 && y <= 820)) {
                            x -= 0;
                        } else {
                            x -= moveX;
                        }
                    } else if (area2) {
                        x -= moveX;

                        if (x <= 10) {
                            city2_bg.setVisible(false);
                            x = 1500;
                            Quadrant1();
                        }
                    } else if (area3) {
                        if (x <= 90 && (y >= 100 && y <= 550)) {
                            x -= 0;
                        } else {
                            x -= moveX;
                        }
                    } else if (area4) {
                        if (x <= 10) {
                            city4_bg.setVisible(false);
                            x = 1500;
                            Quadrant3();
                        }
                        x -= moveX;
                    }

                    currentSpriteIndex = (currentSpriteIndex + 1) % walkingLeft.length;

                    player_bg.setIcon(walkingLeft[currentSpriteIndex]);
                    player_bg.setBounds(x, y, width, height);
                    StoreEntrance();
                } else if (keyCode == KeyEvent.VK_RIGHT) {
                    if (area1) {
                        if (x >= 190 && y >= 560) {
                            x += 0;
                        } else {
                            x += moveX;
                        }

                        if (x >= 1480) {
                            city1_bg.setVisible(false);
                            x = 50;
                            Quadrant2();
                        }
                    } else if (area2) {
                        if (x >= 275 && y >= 600) {
                            x += 0;
                        } else if (x >= 1340 && (y >= 70 && y <= 570)) {
                            x += 0;
                        } else {
                            x += moveX;
                        }

                        if (x >= 1430 && y <= 20) {
                            SwingUtilities.invokeLater(() -> {
                                Havenbrook.this.dispose();
                                Amberdale amberdale = new Amberdale(10, 100);
                                amberdale.Quadrant1();
                            });
                        }
                    } else if (area3) {
                        x += moveX;

                        if (x >= 1480) {
                            city3_bg.setVisible(false);
                            x = 20;
                            Quadrant4();
                        }
                    } else if (area4) {
                        if (x >= 1340 && (y >= 110 && y <= 660)) {
                            x += 0;
                        } else {
                            x += moveX;
                        }
                    }

                    currentSpriteIndex = (currentSpriteIndex + 1) % walkingRight.length;

                    player_bg.setIcon(walkingRight[currentSpriteIndex]);
                    player_bg.setBounds(x, y, width, height);
                    StoreEntrance();
                } else if (keyCode == KeyEvent.VK_UP) {
                    if (area1) {
                        if (y <= 60 && (x >= 400 && x <= 1450)) {
                            y -= 0;
                        } else {
                            y -= moveY;
                        }

                        if (y <= 10) {
                            city1_bg.setVisible(false);
                            y = 790;
                            Quadrant3();
                        }
                    } else if (area2) {
                        y -= moveY;

                        if (y <= 10) {
                            city2_bg.setVisible(false);
                            y = 790;
                            Quadrant4();
                        }
                    } else if (area3) {
                        if (y <= 90 && (x >= 90 && x <= 1290)) {
                            y -= 0;
                        } else {
                            y -= moveY;
                        }

                        if (x >= 1350 && y <= 0) {
                            SwingUtilities.invokeLater(() -> {
                                Havenbrook.this.dispose();
                                Backwarden backwarden = new Backwarden(300, 780);
                                backwarden.Quadrant2();
                            });
                        }
                    } else if (area4) {
                        if (y <= 110 && (x >= 80 && x <= 1340)) {
                            y -= 0;
                        } else {
                            y -= moveY;
                        }

                        if ((x >= 5 && x <= 50) && y <= 0) {
                            SwingUtilities.invokeLater(() -> {
                                Havenbrook.this.dispose();
                                Backwarden backwarden = new Backwarden(300, 780);
                                backwarden.Quadrant2();
                            });
                        }
                    }

                    currentSpriteIndex = (currentSpriteIndex + 1) % walkingBack.length;

                    player_bg.setIcon(walkingBack[currentSpriteIndex]);
                    player_bg.setBounds(x, y, width, height);

                    StoreEntrance();
                } else if (keyCode == KeyEvent.VK_DOWN) {
                    if (area1) {
                        if (x >= 220 && y >= 520) {
                            y += 0;
                        } else if (x >= 100 && y >= 560) {
                            y += 0;
                        } else {
                            y += moveY;
                        }
                    } else if (area2) {
                        if (y >= 700) {
                            y += 0;
                        } else {
                            y += moveY;
                        }
                    } else if (area3) {
                        if (y >= 550 && (x >= 90 && x <= 390)) {
                            y += 0;
                        } else {
                            y += moveY;
                        }

                        if (y >= 790) {
                            city3_bg.setVisible(false);
                            y = 20;
                            Quadrant1();
                        }
                    } else if (area4) {
                        if (y >= 660 && (x >= 485 && x <= 1340)) {
                            y += 0;
                        } else {
                            y += moveY;
                        }

                        if (y >= 790) {
                            city4_bg.setVisible(false);
                            y = 20;
                            Quadrant2();
                        }
                    }
                    currentSpriteIndex = (currentSpriteIndex + 1) % walkingFront.length;

                    player_bg.setIcon(walkingFront[currentSpriteIndex]);
                    player_bg.setBounds(x, y, width, height);
                    StoreEntrance();
                } else if (keyCode == KeyEvent.VK_ENTER) {
                    if ("Farmer's Aid".equals(bldg_label.getText())) {
                        SwingUtilities.invokeLater(() -> new ProgressPanel("Farmer's Aid"));
                    } else if ("Pest Control".equals(bldg_label.getText())) {
                        SwingUtilities.invokeLater(() -> new ProgressPanel("Pest Control"));
                    } else if ("Disaster Preparedness".equals(bldg_label.getText())) {
                        SwingUtilities.invokeLater(() -> new ProgressPanel("Disaster Preparedness"));
                    } else if ("Invest in Agriculture".equals(bldg_label.getText())) {
                        SwingUtilities.invokeLater(() -> new ProgressPanel("Invest in Agriculture"));
                    } else if ("Boss Battle".equals(bldg_label.getText())) {
                        SwingUtilities.invokeLater(() -> new ItemPicking(root + "Backgrounds\\Havenbrook_bg.jpg", root + "Bosses\\ChancellorGilbert.png"));
                    }
                } else if (keyCode == KeyEvent.VK_M) {
                    SwingUtilities.invokeLater(() -> {
                        if (isOwl) {
                            Havenbrook.this.dispose();
                        }
                        new GameMap(isOwl);
                    });
                } else if (keyCode == KeyEvent.VK_P) {
                    SwingUtilities.invokeLater(PlayerProfile::new);
                }
                System.out.println(x + ", " + y);
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

        // Make the frame focusable to receive key events
        this.setFocusable(true);
        this.requestFocusInWindow();
    }

    public void StoreEntrance() {
        if (area1 && (x >= 375 && x <= 735) && (y >= 180 && y <= 420)){
            setBannerText("Farmer's Aid", 80);
        }else if(area1 && (x >= 1065 && x <= 1320) && (y >= 240 && y <= 380)){
            setBannerText("Pest Control", 80);
        }else if(area2 && (x >= 20 && x <= 275) && (y >= 275 && y <= 390)){
            setBannerText("Disaster Preparedness", 50);
        }else if(area4 && (x >= 80 && x <= 440) && (y >= 400 && y <= 610)){
            setBannerText("Invest in Agriculture", 53);
        }else if(area3 && (x >= 570 && x <= 765) && (y >= 380 && y <= 570)){
            setBannerText("Boss Battle", 80);
        }else{
            setBannerText("Havenbrook", 80);
        }
    }
}
