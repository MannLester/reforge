import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;

public class Justice extends City {
    public Justice(int xPos, int yPos) {
        super(xPos, yPos, "Justice");
    }

    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource()==menuButton){
            Justice.this.dispose();
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
                    if (area1 || area3) {
                        if(!(x <= 10)) {
                            x -= moveX;
                        }
                    } else if (area2) {
                        // goes to area 1
                        if (x < 0) {
                            x = 1420;
                            city2_bg.setVisible(false);
                            Quadrant1();
                        }else{
                            x -= moveX;
                        }
                    } else if (area4) {
                        // goes to area 3
                        if (x < 0) {
                            x = 1420;
                            city4_bg.setVisible(false);
                            Quadrant3();
                        }else{
                            x -= moveX;
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
                    } else if (area2 || area4) {
                        if(!(x >= 1480)){
                            x += moveX;
                        }
                    } else if (area3) {
                        // goes to area 4
                        if (x > 1420) {
                            x = 0;
                            city3_bg.setVisible(false);
                            Quadrant4();
                        }else{
                            x += moveX;
                        }
                    }

                    currentSpriteIndex = (currentSpriteIndex + 1) % walkingRight.length;

                    player_bg.setIcon(walkingRight[currentSpriteIndex]);
                    player_bg.setBounds(x, y, width, height);
                    StoreEntrance();
                } else if (keyCode == KeyEvent.VK_UP) {
                    if (area1) {
                        // goes to area 3
                        if (x >= 1200 && y <= 0) {
                            city1_bg.setVisible(false);
                            y = 700;
                            Quadrant3();
                        } else {
                            y -= moveY;
                        }
                    } else if (area2) {
                        if (x < 945 && y <= 0) {
                            city2_bg.setVisible(false);
                            y = 700;
                            Quadrant4();
                        }else{
                            y -= moveY;
                        }
                    } else if (area3 || area4) {
                        if(!(y <= 10)){
                            y -= moveY;
                        }
                    }

                    currentSpriteIndex = (currentSpriteIndex + 1) % walkingBack.length;

                    player_bg.setIcon(walkingBack[currentSpriteIndex]);
                    player_bg.setBounds(x, y, width, height);

                    StoreEntrance();
                } else if (keyCode == KeyEvent.VK_DOWN) {
                    if (area1 || area2) {
                        if (!(y >= 670)){
                            y += moveY;
                        }
                    } else if (area3) {
                        // goes to area 1
                        if (y > 700) {
                            city3_bg.setVisible(false);
                            y = 0;
                            Quadrant1();
                        }else{
                            y += moveY;
                        }
                    } else if (area4) {
                        // goes to area 2
                        if (y > 700) {
                            city4_bg.setVisible(false);
                            y = 0;
                            Quadrant2();
                        }else{
                            y += moveY;
                        }
                    }

                    currentSpriteIndex = (currentSpriteIndex + 1) % walkingFront.length;

                    player_bg.setIcon(walkingFront[currentSpriteIndex]);
                    player_bg.setBounds(x, y, width, height);
                    StoreEntrance();
                } else if (keyCode == KeyEvent.VK_ENTER) {
                    if ("Legal Ethics Training".equals(bldg_label.getText())) {
                        SwingUtilities.invokeLater(() -> new ProgressPanel("Legal Ethics Training"));
                    } else if ("Legal Education".equals(bldg_label.getText())) {
                        SwingUtilities.invokeLater(() -> new ProgressPanel("Legal Education"));
                    } else if ("Transparency Initiatives".equals(bldg_label.getText())) {
                        SwingUtilities.invokeLater(() -> new ProgressPanel("Transparency Initiatives"));
                    } else if ("Legal Aid Programs".equals(bldg_label.getText())) {
                        SwingUtilities.invokeLater(() -> new ProgressPanel("Legal Aid Programs"));
                    }
                    else if ("Boss Battle".equals(bldg_label.getText())) {
                        SwingUtilities.invokeLater(() -> new ItemPicking(root + "Backgrounds\\JusticeCentral_bg.jpg",root + "Bosses\\ChiefInjustice.png"));
                    }
                } else if (keyCode == KeyEvent.VK_M){
                    SwingUtilities.invokeLater(()->{
                        if(isOwl){
                            Justice.this.dispose();
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
        if (area1 && (x >= 10 && x <= 550) && (y <= 590 && y >= 370)){
            setBannerText("Legal Ethics Training", 50);
        }else if(area1 && (x >= 565 && x <= 1030) && y <= 240){
            setBannerText("Legal Education", 60);
        }else if(area2 && (x >= 270 && x <= 735) && y <= 240){
            setBannerText("Transparency Initiatives", 48);
        }else if(area2 && (x >= 630 && x <= 1395) && (y <= 680 && y >= 230)){
            setBannerText("Legal Aid Programs", 55);
        }else if(area3 && (x >= 715 && x <= 1430) && y <= 360){
            setBannerText("Boss Battle", 80);
        } else{
            setBannerText("Justice Central", 75);
        }
    }
}
