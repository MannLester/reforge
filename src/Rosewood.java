import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;

public class Rosewood extends City {
    public Rosewood(int xPos, int yPos) {
        super(xPos, yPos, "Rosewood");
    }

    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource()==menuButton){
            Rosewood.this.dispose();
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
                        if (!((x == 475))) {
                            if (!((x == 1360) && (y >= 420))) {
                                if (!(x <= -20)) {
                                    x -= moveX;
                                }
                            }
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
                            if (!((x == 475) && (y <= 580))) {
                                if (!((x == 910) && (y <= 160))) {
                                    x -= moveX;
                                }
                            }
                        }
                    } else if (area4) {
                        if (!((x == 855) && (y <= 160))) {
                            if (!((x == 945) && (y <= 100))) {
                                if (!((x == 1020) && (y <= 30))) {
                                    x -= moveX;
                                }
                            }
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
                        // goes to area 2
                        x += moveX;

                        if (x > 1420) {
                            x = 0;
                            city1_bg.setVisible(false);
                            Quadrant2();
                        }
                    } else if (area2) {
                        if (!((x == 330) && (y >= 400))) {
                            if (!(x == 1005)) {
                                x += moveX;
                            }
                        }
                    } else if (area3) {
                        if (!((x == 1420) && (y <= 160))) {
                            x += moveX;
                        }

                        // goes to area 4
                        if (x > 1420) {
                            x = 0;
                            city3_bg.setVisible(false);
                            Quadrant4();
                        }
                    } else if (area4) {
                        if (!(x >= 1440)) {
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
                        if (y < 0) {
                            SwingUtilities.invokeLater(()->{
                                Rosewood.this.dispose();
                                Industria industria = new Industria(150,700);
                                industria.Quadrant1();
                            });
                        }

                        if (!((y == 590) && (x <= 460))) {
                            if (!((y == 170) && (x <= 895))) {
                                y -= moveY;
                            }
                        }
                    } else if (area4) {
                        if (!((y == 170) && (x <= 840))) {
                            if (!((y == 110) && (x <= 930))) {
                                if (!((y == 40) && (x <= 1005))) {
                                    if (!(y == 0)) {
                                        y -= moveY;
                                    }
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
                        if (y >= 700) {
                            SwingUtilities.invokeLater(()->{
                                Rosewood.this.dispose();
                                Eldoria eldoria = new Eldoria(50,100);
                                eldoria.Quadrant4();
                            });
                        }

                        if (!((y == 410) && (x <= 1345))) {
                            if (!((y == -30) && (x <= 460))) {
                                if (!(y >= 700)) {
                                    y += moveY;
                                }
                            }
                        }
                    } else if (area2) {
                        if ((y >= 700) && (x <= 90)) {
                            SwingUtilities.invokeLater(()->{
                                Rosewood.this.dispose();
                                Eldoria eldoria = new Eldoria(50,100);
                                eldoria.Quadrant4();
                            });
                        }

                        if (!(y >= 700)) {
                            if (!((y == 390) && (x >= 345))) {
                                y += moveY;
                            }
                        }
                    } else if (area3) {
                        y += moveY;

                        // goes to area 1
                        if (y > 700) {
                            city3_bg.setVisible(false);
                            y = -80;
                            Quadrant1();
                        }
                    } else if (area4) {
                        if (!((y == 700) && (x >= 1020))) {
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
                    if ("Deforestation Mitigation".equals(bldg_label.getText())) {
                        SwingUtilities.invokeLater(() -> new ProgressPanel("Deforestation Mitigation"));
                    } else if ("Sustainable Logging".equals(bldg_label.getText())) {
                        SwingUtilities.invokeLater(() -> new ProgressPanel("Sustainable Logging"));
                    } else if ("Wildlife Protection".equals(bldg_label.getText())) {
                        SwingUtilities.invokeLater(() -> new ProgressPanel("Wildlife Protection"));
                    } else if ("Community Collaboration".equals(bldg_label.getText())) {
                        SwingUtilities.invokeLater(() -> new ProgressPanel("Community Collaboration"));
                    }else if ("Boss Battle".equals(bldg_label.getText())) {
                        SwingUtilities.invokeLater(() -> new ItemPicking(root + "Backgrounds\\Rosewood_bg.jpg",root + "Bosses\\ForestSpiritAvenger.png"));
                    }
                } else if (keyCode == KeyEvent.VK_M) {
                    SwingUtilities.invokeLater(() -> {
                        if(isOwl){
                            Rosewood.this.dispose();
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
        if (area3 && (x >= 680 && x <= 980) && (y <= 480 && y >= 260)){
            setBannerText("Deforestation Mitigation", 45);
        }else if(area4 && (x >= 195 && x <= 480) && (y <= 460 && y >= 190)){
            setBannerText("Sustainable Logging", 50);
        }else if(area2 && (x >= 570 && x <= 885) && y <= 210){
            setBannerText("Wildlife Protection", 60);
        }else if(area1 && (x >= 1105 && x <= 1390) && (y <= 350 && y >= 60)){
            setBannerText("Community Collaboration", 45);
        }else if(area1 && (x >= 610 && x <= 970) && (y <= 310 && y >= 60)){
            setBannerText("Boss Battle", 80);
        }else{
            setBannerText("Rosewood", 80);
        }
    }
}
