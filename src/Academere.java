import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;

public class Academere extends City {
    public Academere(int xPos, int yPos) {
        super(xPos, yPos, "Academere");
    }

    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource()==menuButton){
            Academere.this.dispose();
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
                        if(x >= 10){
                            x -= moveX;
                        }
                    } else if (area2) {
                        if (x < 0) {
                            x = 1420;
                            city2_bg.setVisible(false);
                            Quadrant1();
                        }else{
                            x -= moveX;
                        }
                    } else if (area3) {
                        if(x >= 200){
                            x -= moveX;
                        }
                    } else if (area4) {
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
                    if(area1){
                        if(x >= 1450){
                            city1_bg.setVisible(false);
                            x = 10;
                            Quadrant2();
                        }else{
                            x += moveX;
                        }
                    }else if(area2 || area4){
                        if(x <= 1250){
                            x += moveX;
                        }
                    }else if(area3){
                        if(x >= 1450){
                            city3_bg.setVisible(false);
                            x = 10;
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
                    if(area1){
                        if(y <= 50){
                            city1_bg.setVisible(false);
                            y = 780;
                            Quadrant3();
                        }else{
                            y -= moveY;
                        }
                    }else if(area2){
                        if (y <= 50){
                            city2_bg.setVisible(false);
                            y = 780;
                            Quadrant4();
                        }else{
                            y -= moveY;
                        }
                    }else if(area3 || area4){
                        if (y >= 10){
                            y -= moveY;
                        }
                    }

                    currentSpriteIndex = (currentSpriteIndex + 1) % walkingBack.length;

                    player_bg.setIcon(walkingBack[currentSpriteIndex]);
                    player_bg.setBounds(x, y, width, height);

                    StoreEntrance();
                } else if (keyCode == KeyEvent.VK_DOWN) {
                    if (area1 || area2){
                        if(y <= 650){
                            y += moveY;
                        }
                    }else if(area3){
                        if (y >= 700) {
                            city3_bg.setVisible(false);
                            y = 10;
                            Quadrant1();
                        }else{
                            y += moveY;
                        }
                    }else if(area4){
                        if (y >= 700){
                            city4_bg.setVisible(false);
                            y = 10;
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
                    if ("Improve Campus Facilities".equals(bldg_label.getText())) {
                        SwingUtilities.invokeLater(()-> new ProgressPanel("Improve Campus Facilities"));
                    } else if ("Faculty Development".equals(bldg_label.getText())) {
                        SwingUtilities.invokeLater(() -> new ProgressPanel("Faculty Development"));
                    } else if ("Boss Battle".equals(bldg_label.getText())) {
                        SwingUtilities.invokeLater(() -> new ItemPicking(root + "Backgrounds\\Academere_bg.jpg", root + "Bosses\\PrincipalCorrupto.png"));
                    }
                } else if (keyCode == KeyEvent.VK_M){
                    SwingUtilities.invokeLater(()->{
                        if(isOwl){
                            Academere.this.dispose();
                        }
                        new GameMap(isOwl);
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
        if (area1 && (x >= 295 && x <= 400) && y <= 510) {
            setBannerText("Improve Campus Facilities", 45);
        }else if(area1 && (x >= 1135 && x <= 1225) && y <= 510){
            setBannerText("Faculty Development", 55);
        }else if(area2 && (x >= 580 && x <= 910) && y <= 500){
            setBannerText("Boss Battle", 80);
        }else{
            setBannerText("Academere", 80);
        }
    }
}
