import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;

public class Eldoria extends City {
    public Eldoria(int xPos, int yPos) {
        super(xPos, yPos, "Eldoria");
    }

    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource()==menuButton){
            Eldoria.this.dispose();
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
                        if (x == 235) {
                            SwingUtilities.invokeLater(()->{
                                Eldoria.this.dispose();
                                Willowbrook willowbrook = new Willowbrook(1450,700);
                                willowbrook.Quadrant4();
                            });
                        }

                        // walls
                        if (!((x == 655) && (y >= -40))) {
                            x -= moveX;
                        }
                    } else if (area2) {
                        if (!((x == 0) && (y >= 400))) {
                            x -= moveX;
                        }

                        // goes to area 1
                        if (x < 0) {
                            x = 1420;
                            city2_bg.setVisible(false);
                            Quadrant1();
                        }
                    } else if (area3) {
                        if (x == 235) {
                            SwingUtilities.invokeLater(()->{
                                Eldoria.this.dispose();
                                Willowbrook willowbrook = new Willowbrook(1450,y);
                                willowbrook.Quadrant4();
                            });
                        }

                        // walls
                        if (!((x == 655) && (y <= 640))) {
                            x -= moveX;
                        }
                    } else if (area4) {
                        if (!((x == 0) && (y <= 200))) {
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
                        // walls
                        if (!((x == 105) && (y >= 400))) {
                            if (!((x == 840) && (y >= -40))) {
                                // gate
                                if (!((y == -50) && (x >= 1215))) {
                                    x += moveX;
                                }
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
                        // walls
                        if (!((x == 855) && (y <= 670))) {
                            if (!((x == 120) && (y <= 200))) {
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
                        if (y <= -90) {
                            city1_bg.setVisible(false);
                            y = 780;
                            Quadrant3();
                        }
                    } else if (area2) {
                        y -= moveY;

                        // goes to area 4
                        if (y <= -90) {
                            city2_bg.setVisible(false);
                            y = 780;
                            Quadrant4();
                        }
                    } else if (area3) {
                        // walls
                        if (!((y == 650) && (x <= 640))) {
                            if (!((y == 210) && (x >= 655))) {
                                y -= moveY;
                            }
                        }
                    } else if (area4) {
                        if (y <= 130) {
                            SwingUtilities.invokeLater(()->{
                                Eldoria.this.dispose();
                                Rosewood rosewood = new Rosewood(1450,800);
                                rosewood.Quadrant1();
                            });
                        }

                        // walls
                        if (!((y == 210) && (x >= 135))) {
                            if (!((y == 700) && (x >= 870))) {
                                // gate
                                if (!(x >= 1230)) {
                                    y -= moveY;
                                }
                            }
                        }
                    }

                    currentSpriteIndex = (currentSpriteIndex + 1) % walkingBack.length;

                    player_bg.setIcon(walkingBack[currentSpriteIndex]);
                    player_bg.setBounds(x, y, width, height);

                    StoreEntrance();
                } else if (keyCode == KeyEvent.VK_DOWN) {
                    if (area1) {
                        // walls
                        if (!(y == 390)) {
                            if (!((y == -50) && (x <= 640))) {
                                y += moveY;
                            }
                        }
                    } else if (area2) {
                        if (y == 480) {
                            SwingUtilities.invokeLater(()->{
                                Eldoria.this.dispose();
                                Stonewatch stonewatch = new Stonewatch(50,150);
                                stonewatch.Quadrant4();
                            });
                        }

                        if (!((y == 390) && (x >= 120))) {
                            if (!((y == -50) && (x >= 855))) {
                                y += moveY;
                            }
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
                    if ("Fund Fair Tax System".equals(bldg_label.getText())) {
                        SwingUtilities.invokeLater(()-> new ProgressPanel("Fund Fair Tax System"));
                    } else if ("Fund Social Programs".equals(bldg_label.getText())) {
                        SwingUtilities.invokeLater(()-> new ProgressPanel("Fund Social Programs"));
                    } else if ("Boss Battle".equals(bldg_label.getText())) {
                        SwingUtilities.invokeLater(()-> new ItemPicking(root + "Backgrounds\\Eldoria_bg.jpg", root + "Bosses\\KingBarbaros.png"));
                    }
                } else if (keyCode == KeyEvent.VK_M) {
                    SwingUtilities.invokeLater(() -> {
                        if(isOwl){
                            Eldoria.this.dispose();
                        }
                        new GameMap(isOwl);
                    });
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
        if (area2 && (x >= 170 && x <= 815) && y >= 230){
            setBannerText("Boss Battle", 80);
        }else if(area4 && (x >= 225 && x <= 840) && y <= 390){
            setBannerText("Fund Fair Tax System", 50);
        }else if(area3 && (x >= 700 && x <= 1375) && y <= 410){
            setBannerText("Fund Social Programs", 50);
        }else{
            setBannerText("Eldoria", 80);
        }
    }
}
