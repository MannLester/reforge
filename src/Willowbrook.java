import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;

public class Willowbrook extends City {
    public Willowbrook(int xPos, int yPos) {
        super(xPos, yPos, "Willowbrook");
    }

    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource()==menuButton){
            Willowbrook.this.dispose();
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
                        if (!(x <= 370)) {
                            if (!((x <= 865) && (y >= 540))) {
                                if (!((x <= 475) && (y <= 30))) {
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
                        if (!(x <= 415)) {
                            if (!((x <= 1300) && (y <= 120))) {
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
                        if (!(x >= 1065)) {
                            if (!((x >= 555) && (y >= 500))) {
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
                        if (x >= 1440) {
                            SwingUtilities.invokeLater(()->{
                                Willowbrook.this.dispose();
                                Eldoria eldoria = new Eldoria(100,700);
                                eldoria.Quadrant3();
                            });
                        }

                        if (!((x >= 1065) && (y <= 650))) {
                            if (!((x >= 325) && (y <= 80))) {
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
                        if (!((y <= 40) && (x <= 460))) {
                            y -= moveY;
                        }

                        // goes to area 3
                        if (y < 0) {
                            y = 700;
                            city1_bg.setVisible(false);
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
                        if (!(y <= 0)) {
                            if (!((y <= 180) && (x <= 1285))) {
                                y -= moveY;
                            }
                        }
                    } else if (area4) {
                        if (y <= 0) {
                            SwingUtilities.invokeLater(()->{
                                Willowbrook.this.dispose();
                                Amberdale amberdale = new Amberdale(1300, 700);
                                amberdale.Quadrant1();
                            });
                        }

                        if (!((y <= 130) && (x >= 345))) {
                            if (!((y <= 660) && (x >= 1090))) {
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
                                Willowbrook.this.dispose();
                                Azureport azureport = new Azureport(870,470);
                                azureport.Quadrant4();
                            });
                        }

                        if (!((y >= 530) && (x <= 850))) {
                            y += moveY;
                        }
                    } else if (area2) {
                        if (!(y >= 700)) {
                            if (!((y >= 490) && (x >= 570))) {
                                y += moveY;
                            }
                        }
                    } else if (area3) {
                        y += moveY;

                        // goes to area 1
                        if (y > 700) {
                            y = 0;
                            city3_bg.setVisible(false);
                            Quadrant1();
//                            y = 0;
                        }
                    } else if (area4) {
                        if (!((y >= 700) && (x >= 1090))) {
                            y += moveY;
                        }

                        // goes to area 2
                        if (y > 700) {
                            city4_bg.setVisible(false);
                            y = 0;
                            Quadrant2();
                        }
                    }
                    System.out.println(x + "," + y);
                    currentSpriteIndex = (currentSpriteIndex + 1) % walkingFront.length;

                    player_bg.setIcon(walkingFront[currentSpriteIndex]);
                    player_bg.setBounds(x, y, width, height);
                    StoreEntrance();
                } else if (keyCode == KeyEvent.VK_ENTER) {
                    if ("Water Quality Investigation".equals(bldg_label.getText())) {
                        SwingUtilities.invokeLater(() -> new ProgressPanel("Water Quality Investigation"));
                    } else if ("Community Awareness".equals(bldg_label.getText())) {
                        SwingUtilities.invokeLater(() -> new ProgressPanel("Community Awareness"));
                    } else if ("Sustainable Sanitation".equals(bldg_label.getText())) {
                        SwingUtilities.invokeLater(() -> new ProgressPanel("Sustainable Sanitation"));
                    } else if ("Water Resource Management".equals(bldg_label.getText())) {
                        SwingUtilities.invokeLater(() -> new ProgressPanel("Water Resource Management"));
                    }else if ("Boss Battle".equals(bldg_label.getText())) {
                        SwingUtilities.invokeLater(() -> new ItemPicking(root + "Backgrounds\\Willowbrook_bg.jpg",root + "Bosses\\MayorWillow.png"));
                    }
                } else if (keyCode == KeyEvent.VK_M) {
                    SwingUtilities.invokeLater(() -> {
                        Willowbrook.this.dispose();
                        new GameMap(false);
                    });
                } else if (keyCode == KeyEvent.VK_P){
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
        if (area2 && (x >= 530 && x <= 935) && (y >= 150 && y <= 430)) {
            setBannerText("Water Quality Investigation", 40);
        } else if (area1 && (x >= 685 && x <= 985) && (y >= 40 && y <= 270)) {
            setBannerText("Community Awareness", 50);
        } else if (area3 && (x >= 580 && x <= 1150) && y <= 410) {
            setBannerText("Sustainable Sanitation", 50);
        } else if ((area1 && (x >= 1225 && y <= 1420) && y <= 0) || (area2 && (x >= 0 && x <= 75) && y <=0) || (area3 && (x >= 1225 && x<= 1420) && (y >= 560 && y <= 700)) || (area4 && (x >= 0 && x <= 90) && (y >= 560 && y <= 700))){
            setBannerText("Water Resource Management", 40);
        } else if (area4 && (x >= 815 && x <= 1070) && y <= 370) {
            setBannerText("Boss Battle", 80);
        } else {
            setBannerText("Willowbrook", 80);
        }
    }
}
