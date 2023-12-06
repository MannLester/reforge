import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;

public class Stonewatch extends City {
    public Stonewatch(int xPos, int yPos) {
        super(xPos, yPos, "Stonewatch");
    }

    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource()==menuButton){
            Stonewatch.this.dispose();
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
                        if (!(x <= -20)) {
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
                        x += moveX;

                        // goes to area 2
                        if (x > 1420) {
                            x = 0;
                            city1_bg.setVisible(false);
                            Quadrant2();
                        }
                    } else if (area2) {
                        if (!(x >= 1290)) {
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
                        if (!(x >= 1275)) {
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

                        // goes to area 3
                        if (y < 0) {
                            city1_bg.setVisible(false);
                            y = 700;
                            Quadrant3();
                        }
                    } else if (area2) {
                        if (!(y <= 40)) {
                            y -= moveY;
                        }

                        // goes to area 4
                        if (y < 0) {
                            city2_bg.setVisible(false);
                            y = 700;
                            Quadrant4();
                        }
                    } else if (area3) {
                        if (!(y <= 0)) {
                            y -= moveY;
                        }
                    } else if (area4) {
                        if ((y <= 80) && ( x >= 0) && (x <= 105)) {
                            SwingUtilities.invokeLater(()->{
                                Stonewatch.this.dispose();
                                Eldoria eldoria = new Eldoria(50,700);
                                eldoria.Quadrant2();
                            });
                        }

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
                        if (!(y >= 700)) {
                            y += moveY;
                        }
                    } else if (area3) {
                        if (!(y >= 480)) {
                            y += moveY;
                        }

                        // goes to area 1
                        if (y > 700) {
                            city3_bg.setVisible(false);
                            y = 0;
                            Quadrant1();
                        }
                    } else if (area4) {
                        if (!(y >= 480)) {
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
                    if ("Demolish the Stone Wall".equals(bldg_label.getText())) {
                        SwingUtilities.invokeLater(() -> new ProgressPanel("Demolish the Stone Wall"));
                    } else if ("Boss Battle".equals(bldg_label.getText())) {
                        SwingUtilities.invokeLater(() -> new ItemPicking(root + "Backgrounds\\Stonewatch_bg.jpg",root + "Bosses\\HarmonyCatalyst.png"));
                    }
                } else if (keyCode == KeyEvent.VK_M) {
                    SwingUtilities.invokeLater(() -> {
                        Stonewatch.this.dispose();
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
        if (area3 && (x >= 0 && x <= 1390) && (y >= 100 && y <= 280)) {
            setBannerText("Boss Battle", 80);
        } else if (area4 && (x >= 185 && x <= 1265) && (y >= 100 && y <= 280)){
            setBannerText("Boss Battle", 80);
        }else if (area2 && (x >= 0 && x <= 1420) && (y <= 40)) {
            setBannerText("Demolish the Stone Wall", 60);
        } else if (area1 && (x >= 0 && x <= 1420) && (y <= 40)) {
            setBannerText("Demolish the Stone Wall", 60);
        } else {
            setBannerText("Stonewatch", 80);
        }
    }
}
