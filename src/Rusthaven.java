import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;

public class Rusthaven extends City {
    public Rusthaven(int xPos, int yPos) {
        super(xPos, yPos, "Rusthaven");
    }

    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource()==menuButton){
            Rusthaven.this.dispose();
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
                        if (!(x <= 205)) {
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
                        if ((x <= -20) && (y >= 280) && (y <= 400)) {
                            SwingUtilities.invokeLater(()->{
                                Rusthaven.this.dispose();
                                Backwarden backwarden = new Backwarden(1470,350);
                                backwarden.Quadrant4();
                            });
                        } else if (!(x <= -20)) {
                            if (!((x <= 700) && (y <= 150))) {
                                x -= moveX;
                            }
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
                        x += moveX;

                        // goes to area 2
                        if (x > 1420) {
                            x = 0;
                            city1_bg.setVisible(false);
                            Quadrant2();
                        }
                    } else if (area2) {
                        if (!(x >= 1230)) {
                            x += moveX;
                        }
                    } else if (area3) {
                        if (!(x >= 1440)) {
                            x += moveX;
                        }

                        // goes to area 4
                        if (x > 1420) {
                            x = 0;
                            city3_bg.setVisible(false);
                            Quadrant4();
                        }
                    } else if (area4) {
                        if ((x >= 1435) && (y >= 290) && (y <= 400)) {
                            SwingUtilities.invokeLater(()->{
                                Rusthaven.this.dispose();
                                Ironspire ironspire = new Ironspire(50, 100);
                                ironspire.Quadrant1();
                            });
                        } else if (!((x >= 630) && (y <= 150))) {
                            if (!(x >= 1435)) {
                                x += moveX;
                            }
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
                        if (!(y <= 40)) {
                            if (!((y <= 160) && (x <= 685))) {
                                y -= moveY;
                            }
                        }
                    } else if (area4) {
                        if (!((y <= 160) && (x >= 645))) {
                            if (!(y <= 40)) {
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
                        if (!(y >= 700)) {
                            y += moveY;
                        }
                    } else if (area2) {
                        if (!(y >= 700)) {
                            y += moveY;
                        }
                    } else if (area3) {
                        if (!((y >= 700) && (x <= 190))) {
                            y += moveY;
                        }

                        // goes to area 1
                        if (y > 700) {
                            city3_bg.setVisible(false);
                            y = 0;
                            Quadrant1();
                        }
                    } else if (area4) {
                        if (!((y >= 700) && (x >= 1245))) {
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
                    if ("Labor Training".equals(bldg_label.getText())) {
                        SwingUtilities.invokeLater(() -> new ProgressPanel("Labor Training"));
                    } else if ("Tech Education".equals(bldg_label.getText())) {
                        SwingUtilities.invokeLater(() -> new ProgressPanel("Tech Education"));
                    } else if ("Boss Battle".equals(bldg_label.getText())) {
                        SwingUtilities.invokeLater(() -> new ItemPicking(root + "Backgrounds\\Rusthaven_bg.jpg",root + "Bosses\\TechnoOverlord.png"));
                    }
                } else if (keyCode == KeyEvent.VK_M) {
                    SwingUtilities.invokeLater(() -> {
                        if(isOwl){
                            Rusthaven.this.dispose();
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
        if (area2 && (x >= 580 && x <= 1150) && (y >= 0 && y <= 630)) {
            setBannerText("Labor Training", 70);
        } else if (area1 && (x >= 265 && x <= 820) && (y >= 20 && y <= 600)) {
            setBannerText("Tech Education", 70);
        } else if (area4 && (x >= 0 && x <= 225) && (y >= 40 && y <= 350)) {
            setBannerText("Boss Battle", 70);
        } else if (area3 && (x >= 1105 && x <= 1420) && (y >= 50 && y <= 350)){
            setBannerText("Boss Battle", 70);
        }else {
            setBannerText("Rusthaven", 80);
        }
    }
}
