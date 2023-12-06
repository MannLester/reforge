import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;

public class Ghosthaven extends City {
    public Ghosthaven(int xPos, int yPos) {
        super(xPos, yPos, "Ghosthaven");
    }

    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource()==menuButton){
            Ghosthaven.this.dispose();
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
                        if (!(x <= 220)) {
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
                        if (!(x <= 220)) {
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
                        if (!(x >= 1200)) {
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
                        if (x >= 1440) {
                            SwingUtilities.invokeLater(()->{
                                Ghosthaven.this.dispose();
                                Backwarden backwarden = new Backwarden(300,10);
                                backwarden.Quadrant3();
                            });
                        }

                        if (!((x >= 1200) && (y <= 590))) {
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
                        if (!(y <= 90)) {
                            y -= moveY;
                        }
                    } else if (area4) {
                        if (!(y <= 90)) {
                            if (!((y <= 600) && (x >= 1215))) {
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
                        if (!(y >= 550)) {
                            y += moveY;
                        }
                    } else if (area2) {
                        if (!(y >= 550)) {
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
                        if (!((y >= 700) && (x >= 1215))) {
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
                    if ("Tourism Reinvestment".equals(bldg_label.getText())) {
                        SwingUtilities.invokeLater(() -> new ProgressPanel("Tourism Reinvestment"));
                    } else if ("Infrastructure Restoration".equals(bldg_label.getText())) {
                        SwingUtilities.invokeLater(() -> new ProgressPanel("Infrastructure Restoration"));
                    } else if ("Community Engagements".equals(bldg_label.getText())) {
                        SwingUtilities.invokeLater(() -> new ProgressPanel("Community Engagements"));
                    } else if ("Public Safety".equals(bldg_label.getText())) {
                        SwingUtilities.invokeLater(() -> new ProgressPanel("Public Safety"));
                    } else if ("Boss Battle".equals(bldg_label.getText())) {
                        SwingUtilities.invokeLater(() -> new ItemPicking(root + "Backgrounds\\Ghosthaven_bg.jpg",root + "Bosses\\WraithPriest.png"));
                    }
                } else if (keyCode == KeyEvent.VK_M) {
                    SwingUtilities.invokeLater(() -> {
                        if(isOwl){
                            Ghosthaven.this.dispose();
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
        if(area1 && (x >= 565 && x <= 1165) && y <= 400){
            setBannerText("Tourism Reinvestment", 50);
        }else if(area2 && (x >= 285 && x <= 825) && y <= 390){
            setBannerText("Infrastructure Restoration", 45);
        }else if(area4 && (x >= 645 && x <= 990) && y <= 470){
            setBannerText("Community Engagements", 48);
        }else if(area4 && (x >= 30 && x <= 390) && y <= 560){
            setBannerText("Public Safety", 80);
        }else if(area3 && (x >= 670 && x <= 1045) && y <= 560){
            setBannerText("Boss Battle", 80);
        }else{
            setBannerText("Ghosthaven", 80);
        }
    }
}
