import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;

public class Azureport extends City {
    public Azureport(int xPos, int yPos) {
        super(xPos, yPos, "Azureport");
    }

    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource()==menuButton){
            Azureport.this.dispose();
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
                        if (!(x <= 280)) {
                            if (!((x == 910) && (y >= 200))) {
                                if (!((x <= 1105) && (y >= 410))) {
                                    if (!((x <= 1300) && (y >= 530))) {
                                        if (!((y >= 200) && (x <= 550))) {
                                            x -= moveX;
                                        }
                                    }
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
                        if (!(x <= 280)) {
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
                        if (!((y >= 200) && (x == 700))) {
                            if (!((x >= 1420) && (y >= 410))) {
                                x += moveX;
                            }
                        }

                        // goes to area 2
                        if (x > 1420) {
                            x = 0;
                            city1_bg.setVisible(false);
                            Quadrant2();
                        }
                    } else if (area2) {
                        if (!(x >= 1080)) {
                            x += moveX;
                        }
                    } else if (area3) {
                        if (!((x >= 1420) && (y <= 440))) {
                            x += moveX;
                        }

                        // goes to area 4
                        if (x > 1420) {
                            x = 0;
                            city3_bg.setVisible(false);
                            Quadrant4();
                        }
                    } else if (area4) {
                        if (!(x >= 1030)) {
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
                        if (!(y <= 160)) {
                            y -= moveY;
                        }
                    } else if (area4) {
                        if ((y <= 470) && (x >= 860) && (x <= 880)) {
                            SwingUtilities.invokeLater(()->{
                                Azureport.this.dispose();
                                Willowbrook willowbrook = new Willowbrook(x, 700);
                                willowbrook.Quadrant1();
                            });
                        }

                        if (!(y <= 450)) {
                            y -= moveY;
                        }
                    }

                    currentSpriteIndex = (currentSpriteIndex + 1) % walkingBack.length;

                    player_bg.setIcon(walkingBack[currentSpriteIndex]);
                    player_bg.setBounds(x, y, width, height);

                    StoreEntrance();
                } else if (keyCode == KeyEvent.VK_DOWN) {
                    if (area1) {
                        if (!((y >= 560) && (x >= 1300))) {
                            if (!((y >= 520) && (x <= 1285))) {
                                if (!((y >= 400) && (x <= 1090))) {
                                    if (!((y == 190) && (((x >= 715) && (x <= 895)) || (x <= 520)))) {
                                        y += moveY;
                                    }
                                }
                            }
                        }
                        System.out.println(x + "," + y);
                    } else if (area2) {
                        if (!(y >= 360)) {
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
                    if ("Travel".equals(bldg_label.getText())) {
                        SwingUtilities.invokeLater(BoatTravel::new);
                    } else if ("Pollution Control".equals(bldg_label.getText())) {
                        SwingUtilities.invokeLater(()-> new ProgressPanel("Pollution Control"));
                    } else if ("Clean-up Efforts".equals(bldg_label.getText())) {
                        SwingUtilities.invokeLater(()-> new ProgressPanel("Clean-up Efforts"));
                    } else if ("Advocate for Marine Conservation".equals(bldg_label.getText())) {
                        SwingUtilities.invokeLater(()-> new ProgressPanel("Advocate for Marine Conservation"));
                    } else if ("Community Education".equals(bldg_label.getText())) {
                        SwingUtilities.invokeLater(()-> new ProgressPanel("Community Education"));
                    } else if ("Boss Battle".equals(bldg_label.getText())) {
                        SwingUtilities.invokeLater(()-> new ItemPicking(root + "Backgrounds\\Azureport_bg.jpg", root + "Bosses\\HydroborneLeviathan.png"));
                    }
                } else if (keyCode == KeyEvent.VK_M) {
                    SwingUtilities.invokeLater(() -> {
                        if(isOwl){
                            Azureport.this.dispose();
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
        if (area2 && (x >= 435 && x <= 750) && y <= 110){
            setBannerText("Pollution Control", 65);
        }else if(area2 && (x >= 0 && x <= 195) && y <= 270){
            setBannerText("Clean-up Efforts", 60);
        }else if(area4 && (x >= 0 && x <= 255) && y <= 599){
            setBannerText("Advocate for Marine Conservation", 35);
        }else if(area3 && (x >= 595 && x <= 955) && y <= 510){
            setBannerText("Community Education", 50);
        }else if(area1 && (x >= 970 && x <= 1240) && y <= 20){
            setBannerText("Boss Battle", 80);
        }else if(area1 && (x >= 535 && x <= 700) && y >= 260){
            setBannerText("Travel", 80);
        }else{
            setBannerText("Azureport", 80);
        }
    }
}
