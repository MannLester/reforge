import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;

public class ClimaVoid extends City {
    public ClimaVoid(int xPos, int yPos) {
        super(xPos, yPos, "ClimaVoid");
    }

    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource()==menuButton){
            ClimaVoid.this.dispose();
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
                    if(area1){
                        if(!(x <= 100 && (y >= 120 && y <= 640) || x <= 10)){
                            x -= moveX;
                        }
                    }else if(area2){
                        x -= moveX;

                        if(x <= 10){
                            city2_bg.setVisible(false);
                            x = 1480;
                            Quadrant1();
                        }
                    }else if(area3){
                        if(!(x <= 100 && (y >= 120 && y <= 640) || x <= 10)){
                            x -= moveX;
                        }
                    }else if(area4){
                        x -= moveX;

                        if(x <= 10){
                            city4_bg.setVisible(false);
                            x = 1480;
                            Quadrant3();
                        }
                    }

                    currentSpriteIndex = (currentSpriteIndex + 1) % walkingLeft.length;

                    player_bg.setIcon(walkingLeft[currentSpriteIndex]);
                    player_bg.setBounds(x, y, width, height);
                    StoreEntrance();
                } else if (keyCode == KeyEvent.VK_RIGHT) {
                    if(area1){
                        x += moveX;

                        if(x >= 1480){
                            city1_bg.setVisible(false);
                            x = 10;
                            Quadrant2();
                        }
                    }else if(area2||area4){
                        if(!(x >= 1210 && y >= 10)){
                            x += moveX;
                        }
                    }else if(area3){
                        x += moveX;

                        if(x >= 1480){
                            city3_bg.setVisible(false);
                            x = 10;
                            Quadrant4();
                        }
                    }

                    currentSpriteIndex = (currentSpriteIndex + 1) % walkingRight.length;

                    player_bg.setIcon(walkingRight[currentSpriteIndex]);
                    player_bg.setBounds(x, y, width, height);
                    StoreEntrance();
                } else if (keyCode == KeyEvent.VK_UP) {
                    if(area1){
                        y -= moveY;

                        if (y <= 10){
                            city1_bg.setVisible(false);
                            y = 780;
                            Quadrant3();
                        }
                    }else if(area2){
                        y -= moveY;

                        if (y <= 10){
                            city2_bg.setVisible(false);
                            y = 780;
                            Quadrant4();
                        }
                    }else if(area3||area4){
                        if(!(y <= 10)){
                            y -= moveY;
                        }
                    }

                    currentSpriteIndex = (currentSpriteIndex + 1) % walkingBack.length;

                    player_bg.setIcon(walkingBack[currentSpriteIndex]);
                    player_bg.setBounds(x, y, width, height);

                    StoreEntrance();
                } else if (keyCode == KeyEvent.VK_DOWN) {
                    if(area1){
                        if(!((x <= 100 && y >= 70) || (x >=95 && y >= 640))){
                            y += moveY;
                        }
                    }else if(area2){
                        if(!(x >=95 && y >= 640)){
                            y += moveY;
                        }
                    }else if(area3){
                        y += moveY;

                        if(y >= 780){
                            city3_bg.setVisible(false);
                            y = 10;
                            Quadrant1();
                        }
                    }else if(area4){
                        y += moveY;

                        if(y >= 780){
                            city4_bg.setVisible(false);
                            y = 10;
                            Quadrant2();
                        }
                    }

                    currentSpriteIndex = (currentSpriteIndex + 1) % walkingFront.length;

                    player_bg.setIcon(walkingFront[currentSpriteIndex]);
                    player_bg.setBounds(x, y, width, height);
                    StoreEntrance();
                } else if (keyCode == KeyEvent.VK_ENTER) {
                    if ("Calamity Mitigation".equals(bldg_label.getText())) {
                        SwingUtilities.invokeLater(()-> new ProgressPanel("Calamity Mitigation"));
                    } else if ("Collaborative Research".equals(bldg_label.getText())) {
                        SwingUtilities.invokeLater(()-> new ProgressPanel("Collaborative Research"));
                    } else if ("Laboratory Restoration".equals(bldg_label.getText())) {
                        SwingUtilities.invokeLater(()-> new ProgressPanel("Laboratory Restoration"));
                    } else if ("Raise Climate Awareness".equals(bldg_label.getText())) {
                        SwingUtilities.invokeLater(()-> new ProgressPanel("Raise Climate Awareness"));
                    } else if ("Boss Battle".equals(bldg_label.getText())) {
                        SwingUtilities.invokeLater(()-> new ItemPicking(root + "Backgrounds\\ClimaVoid_bg.jpg", root + "Bosses\\CatastropheSentinel.png"));
                    }
                } else if (keyCode == KeyEvent.VK_M){
                    SwingUtilities.invokeLater(()->{
                        ClimaVoid.this.dispose();
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
        if (area1 && (x >= 985 && x <= 1400) && y <= 100){
            setBannerText("Calamity Mitigation", 50);
        }else if(area2 && (x >= 670 && x <= 1210) && y <= 400){
            setBannerText("Collaborative Research", 50);
        }else if(area4 && (x >= 340 && x <= 1210) && y <= 530){
            setBannerText("Laboratory Restoration", 50);
        }else if(area1 && (x >= 355 && x <= 1400) && (y <= 580 && y >= 180)){
            setBannerText("Raise Climate Awareness", 45);
        }else if(area3 && (x >= 455 && x <= 1100) && y <= 450){
            setBannerText("Boss Battle", 80);
        }else{
            setBannerText("ClimaVoid", 80);
        }
    }
}
