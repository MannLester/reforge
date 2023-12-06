import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;

public class Amberdale extends City {
    public Amberdale(int xPos, int yPos) {
        super(xPos, yPos, "Amberdale");
    }

    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource()==menuButton){
            Amberdale.this.dispose();
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
                        if (x < -20) {
                            SwingUtilities.invokeLater(()->{
                                Amberdale.this.dispose();
                                Havenbrook havenbrook = new Havenbrook(1430,20);
                                havenbrook.Quadrant2();
                            });
                        }

                        // trees
                        if (!((x == 190) && ((y >= 120) || ((y >= -90) && (y <= 10))))) {
                            if (!((x == 1285) && (y >= 420))) {
                                // fence
                                if (!((x == 805) && (y > 0) && (y <= 50))) {
                                    x -= moveX;
                                }
                            }
                        }
                    } else if (area2) {
                        // pool
                        if (!((x == 0) && (y >= -20) && (y <= 80))) {
                            // trees
                            if (!((x == 1200) && (y >= 90) && (y <= 330))) {
                                if ((!((x == 1050) && (y >= 420) && (y <= 530)))) {
                                    x -= moveX;
                                }
                            }
                        }

                        // goes to area 1
                        if (x < 0) {
                            x = 1420;
                            city2_bg.setVisible(false);
                            Quadrant1();
                        }
                    } else if (area3) {
                        // trees
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
                        // fence
                        if (!((x == 460) && (y > 0) && (y <= 50))) {
                            // pool
                            if (!((x == 1150) && (y <= 80))) {
                                // trees
                                if (!((x == 1015) && (y >= 420))) {
                                    if (!((x == 1420) && ((y >= 570) || ((y >= 420) && (y <= 530))))) {
                                        x += moveX;
                                    }
                                }
                            }
                        }

                        // goes to area 2
                        if (x > 1420) {
                            x = 0;
                            city1_bg.setVisible(false);
                            Quadrant2();
                        }
                    } else if (area2) {
                        // trees
                        if (!(x >= 1245)) {
                            if (!((x == 1140) && (y >= 500))) {
                                if (!((x == 1035) && (y >= 90) && (y <= 330))) {
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
                        // trees
                        if (!(x >= 1035)) {
                            if (!((x == 60) && (y <= 230))) {
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
                        // trees
                        if (!((y == 20) && (x >= -20) && (x <= 175))) {
                            // fence
                            if (!((y == 60) && (x >= 475) && (x <= 805))) {
                                // pool
                                if (!((y == 90) && (x >= 1165))) {
                                    y -= moveY;
                                }
                            }
                        }

                        // goes to area 3
                        if (y < 0) {
                            city1_bg.setVisible(false);
                            y = 700;
                            Quadrant3();
                        }
                    } else if (area2) {
                        // fence
                        if (!((y == 90) && ((x <= 570) || (x >= 660)))) {
                            // trees
                            if (!((y == 540) && (x <= 1035))) {
                                if (!((y == 340) && (x >= 1050) && (x <= 1185))) {
                                    y -= moveY;
                                }
                            }
                        }

                        // goes to area 4
                        if (y < 0) {
                            city2_bg.setVisible(false);
                            y = 700;
                            Quadrant4();
                        }
                    } else if (area3) {
                        // trees
                        if (!(y <= 100)) {
                            y -= moveY;
                        }
                    } else if (area4) {
                        if (y < -10) {
                            SwingUtilities.invokeLater(()->{
                                Amberdale.this.dispose();
                                Ironspire ironspire = new Ironspire(280,750);
                                ironspire.Quadrant2();
                            });
                        }

                        // fence
                        if (!(y == 680)) {
                            if (!((y == 240) && (x >= 75))) {
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
                        if (y > 700) {
                            SwingUtilities.invokeLater(()->{
                                Amberdale.this.dispose();
                                Willowbrook willowbrook = new Willowbrook(50, 30);
                                willowbrook.Quadrant4();
                            });
                        }

                        // trees
                        if (!((y == 410) && (x >= 1030) && (x <= 1270))) {
                            if (!((y == 110) && (x >= -20) && (x <= 175))) {
                                if (!((y >= 550) && (x >= 190) && (x <= 1015))) {
                                    // pool
                                    if (!((y == -30) && (x >= 1165))) {
                                        // fence
                                        if (!((y == 0) && (x >= 475) && (x <= 805))) {
                                            y += moveY;
                                        }
                                    }
                                }
                            }
                        }
                    } else if (area2) {
                        // trees
                        if (!((y == 490) && (x >= 1155))) {
                            if (!((y == 410) && (x <= 1035))) {
                                if (!(y >= 560)) {
                                    // fence
                                    if (!((y == 40) && ((x <= 570) || (x >= 660)))) {
                                        y += moveY;
                                    }
                                }
                            }
                        }
                    } else if (area3) {
                        y += moveY;

                        // goes to area 1
                        if (y > 700) {
                            city3_bg.setVisible(false);
                            y = -90;
                            Quadrant1();
                        }
                    } else if (area4) {
                        if (!(y == 630)) {
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
                    if ("Medical Supplies".equals(bldg_label.getText())) {
                        SwingUtilities.invokeLater(()-> new ProgressPanel("Medical Supplies"));
                    } else if ("Health Education".equals(bldg_label.getText())) {
                        SwingUtilities.invokeLater(()-> new ProgressPanel("Health Education"));
                    } else if ("Recruit Skilled Workers".equals(bldg_label.getText())) {
                        SwingUtilities.invokeLater(()-> new ProgressPanel("Recruit Skilled Workers"));
                    } else if ("Healthcare Policy Reforms".equals(bldg_label.getText())) {
                        SwingUtilities.invokeLater(()-> new ProgressPanel("Healthcare Policy Reforms"));
                    } else if ("Boss Battle".equals(bldg_label.getText())){
                        SwingUtilities.invokeLater(()-> new ItemPicking(root + "Backgrounds\\Amberdale_bg.jpg", root + "Bosses\\MayorVenalix.png"));
                    }
                } else if (keyCode == KeyEvent.VK_M) {
                    SwingUtilities.invokeLater(() -> {
                        if(isOwl){
                            Amberdale.this.dispose();
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
        if (area1 && (x >= 170 && x <= 410) && y >= 140){
            setBannerText("Medical Supplies", 60);
        }else if(area1 && (x >= 530 && x <= 845) && y >= 160){
            setBannerText("Health Education", 60);
        }else if(area3 && (x >= 215 && x <= 365) && y <= 490){
            setBannerText("Recruit Skilled Workers", 45);
        }else if(area3 && (x >= 425 && x <= 830) && y <= 620){
            setBannerText("Healthcare Policy Reforms", 45);
        }else if(area4 && (x >= 765 && x <= 1035) && y <= 480){
            setBannerText("Boss Battle", 80);
        }else{
            setBannerText("Amberdale", 80);
        }
    }
}
