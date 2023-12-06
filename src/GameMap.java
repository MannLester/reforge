import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GameMap extends JFrame implements ActionListener {
    private String city;
    String root = "C:\\Users\\User\\Reforge2\\src\\assets\\";
    private boolean buttonClicked = false;
    JButton eldoriaButton, havenbrookButton, amberdaleButton, academereButton, ironspireButton;
    JButton willowbrookButton, shadowfallButton, rusthavenButton, backwardenButton, stonewatchButton;
    JButton ghosthavenButton, industriaButton, climavoidButton, azureportButton, rosewoodButton;
    JButton justiceButton, skyportButton, valoriaButton, enterButton;
    JLabel cityName_label, cityStory_label;
    GameMap(boolean isOwl){
        int width = 1520;
        int height = 820;

        this.setVisible(true);
        this.setSize(width,height);
        this.setTitle("Reforge: Rise of the Seventeen Cities");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLayout(null);

        ImageIcon map_bg = new ImageIcon(root + "Map\\gameMap2.jpg");
        JLabel map = new JLabel();
        map.setIcon(map_bg);
        map.setBounds(0,0,1000,800);
        map.setOpaque(false);

        ImageIcon map_desc = new ImageIcon(root + "Map\\map_desc.jpg");
        JLabel mapDesc_label = new JLabel();
        mapDesc_label.setIcon(map_desc);
        mapDesc_label.setBounds(1000,0,520,800);
        mapDesc_label.setOpaque(false);

        cityName_label = new JLabel();
        cityName_label.setHorizontalAlignment(JLabel.CENTER);
        cityName_label.setVerticalAlignment(JLabel.CENTER);
        cityName_label.setForeground(new Color(0x945A2B));
        cityName_label.setBounds(1060,100,400,100);
        cityName_label.setOpaque(false);

        cityStory_label = new JLabel();
        cityStory_label.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        cityStory_label.setHorizontalAlignment(JLabel.LEFT);
        cityStory_label.setVerticalAlignment(JLabel.TOP);
        cityStory_label.setForeground(new Color(0x945A2B));
        cityStory_label.setBounds(1060, 230, 400, 430);
        cityStory_label.setOpaque(false);

        if(isOwl) {
            ImageIcon enterButton_pic = new ImageIcon(root + "Buttons\\misc\\enterButton.png");
            enterButton = new JButton();
            enterButton.setIcon(enterButton_pic);
            enterButton.setFocusable(false);
            enterButton.setBounds(1148, 668, 220, 100);
            enterButton.setOpaque(false);
            enterButton.setVisible(false);
            enterButton.setEnabled(false);
            enterButton.addActionListener(this);

            this.add(enterButton);
        }else {
            this.addKeyListener(new KeyListener() {
                @Override
                public void keyTyped(KeyEvent e) {

                }

                @Override
                public void keyPressed(KeyEvent e) {
                    int keyCode = e.getKeyCode();
                    if(keyCode == KeyEvent.VK_M){
                        SwingUtilities.invokeLater(GameMap.this::dispose);
                    }
                }

                @Override
                public void keyReleased(KeyEvent e) {

                }
            });
            this.setFocusable(true);
            this.requestFocusInWindow();
        }

        this.add(cityStory_label);
        this.add(cityName_label);
        this.add(mapDesc_label);
        this.add(map);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (dim.width - getWidth()) / 2;

        setLocation(x,0);

        MapButtons();
    }

    public void MapButtons(){
        //valoria button
        valoriaButton = new JButton("Valoria");
        valoriaButton.setBounds(45,480,200,200);
        valoriaButton.setContentAreaFilled(false);
        valoriaButton.setBorderPainted(false);
        valoriaButton.setOpaque(false);
        valoriaButton.addActionListener(this);
        valoriaButton.addMouseListener(createMouseListener("VALORIA", "Story of Valoria"));

        //academere button
        academereButton = new JButton("Academere");
        academereButton.setBounds(250,530,100,100);
        academereButton.setContentAreaFilled(false);
        academereButton.setBorderPainted(false);
        academereButton.setOpaque(false);
        academereButton.addActionListener(this);
        academereButton.addMouseListener(createMouseListener("ACADEMERE", "Story of Academere"));

        //shadowfall button
        shadowfallButton = new JButton("Shadowfall");
        shadowfallButton.setBounds(310,650,200,100);
        shadowfallButton.setContentAreaFilled(false);
        shadowfallButton.setBorderPainted(false);
        shadowfallButton.setOpaque(false);
        shadowfallButton.addActionListener(this);
        shadowfallButton.addMouseListener(createMouseListener("SHADOWFALL", "Story of Shadowfall"));

        //ghosthaven button
        ghosthavenButton = new JButton("Ghosthaven");
        ghosthavenButton.setBounds(95,100,130,150);
        ghosthavenButton.setContentAreaFilled(false);
        ghosthavenButton.setBorderPainted(false);
        ghosthavenButton.setOpaque(false);
        ghosthavenButton.addActionListener(this);
        ghosthavenButton.addMouseListener(createMouseListener("GHOSTHAVEN", "Story of Ghosthaven"));

        //havenbrook button
        havenbrookButton = new JButton("Havenbrook");
        havenbrookButton.setBounds(163,340,280,130);
        havenbrookButton.setContentAreaFilled(false);
        havenbrookButton.setBorderPainted(false);
        havenbrookButton.setOpaque(false);
        havenbrookButton.addActionListener(this);
        havenbrookButton.addMouseListener(createMouseListener("HAVENBROOK", "Story of Havenbrook"));

        //backwarden button
        backwardenButton = new JButton("Back");
        backwardenButton.setBounds(230,140,100,200);
        backwardenButton.setContentAreaFilled(false);
        backwardenButton.setBorderPainted(false);
        backwardenButton.setOpaque(false);
        backwardenButton.addActionListener(this);
        backwardenButton.addMouseListener(createMouseListener("BACKWARDEN", "Story of Backwarden"));

        //rusthaven button
        rusthavenButton = new JButton("Rust");
        rusthavenButton.setBounds(335,140,100,200);
        rusthavenButton.setContentAreaFilled(false);
        rusthavenButton.setBorderPainted(false);
        rusthavenButton.setOpaque(false);
        rusthavenButton.addActionListener(this);
        rusthavenButton.addMouseListener(createMouseListener("RUSTHAVEN", "Story of Rusthaven"));

        //azureport button
        azureportButton = new JButton("Azureport");
        azureportButton.setBounds(440,460,150,190);
        azureportButton.setContentAreaFilled(false);
        azureportButton.setBorderPainted(false);
        azureportButton.setOpaque(false);
        azureportButton.addActionListener(this);
        azureportButton.addMouseListener(createMouseListener("AZUREPORT", "Story of Azureport"));

        //willowbrook button
        willowbrookButton = new JButton("Willowbrook");
        willowbrookButton.setBounds(500,400,150,50);
        willowbrookButton.setContentAreaFilled(false);
        willowbrookButton.setBorderPainted(false);
        willowbrookButton.setOpaque(false);
        willowbrookButton.addActionListener(this);
        willowbrookButton.addMouseListener(createMouseListener("WILLOWBROOK", "Story of Willowbrook"));

        //amberdale button
        amberdaleButton = new JButton("Amberdale");
        amberdaleButton.setBounds(450,350,150,50);
        amberdaleButton.setContentAreaFilled(false);
        amberdaleButton.setBorderPainted(false);
        amberdaleButton.setOpaque(false);
        amberdaleButton.addActionListener(this);
        amberdaleButton.addMouseListener(createMouseListener("AMBERDALE", "Story of Amberdale"));

        //ironspire button
        ironspireButton = new JButton("Ironspire");
        ironspireButton.setBounds(440,150,150,150);
        ironspireButton.setContentAreaFilled(false);
        ironspireButton.setBorderPainted(false);
        ironspireButton.setOpaque(false);
        ironspireButton.addActionListener(this);
        ironspireButton.addMouseListener(createMouseListener("IRONSPIRE", "Story of Ironspire"));


        //climavoid button
        climavoidButton = new JButton("ClimaVoid");
        climavoidButton.setBounds(600,660,110,110);
        climavoidButton.setContentAreaFilled(false);
        climavoidButton.setBorderPainted(false);
        climavoidButton.setOpaque(false);
        climavoidButton.addActionListener(this);
        climavoidButton.addMouseListener(createMouseListener("CLIMAVOID", "Story of ClimaVoid Research Center"));

        //eldoria button
        eldoriaButton = new JButton("Eldoria");
        eldoriaButton.setBounds(600,450,170,150);
        eldoriaButton.setContentAreaFilled(false);
        eldoriaButton.setBorderPainted(false);
        eldoriaButton.setOpaque(false);
        eldoriaButton.addActionListener(this);
        eldoriaButton.addMouseListener(createMouseListener("ELDORIA", "Story of Eldoria"));

        //rosewood button
        rosewoodButton = new JButton("Rosewood");
        rosewoodButton.setBounds(629,273,100,40);
        rosewoodButton.setContentAreaFilled(false);
        rosewoodButton.setBorderPainted(false);
        rosewoodButton.setOpaque(false);
        rosewoodButton.addActionListener(this);
        rosewoodButton.addMouseListener(createMouseListener("ROSEWOOD", "Story of Rosewood"));

        //industria button
        industriaButton = new JButton("Industria");
        industriaButton.setBounds(650,175,150,100);
        industriaButton.setContentAreaFilled(false);
        industriaButton.setBorderPainted(false);
        industriaButton.setOpaque(false);
        industriaButton.addActionListener(this);
        industriaButton.addMouseListener(createMouseListener("INDUSTRIA", "Story of Industria"));

        //stonewatch button
        stonewatchButton = new JButton("Stonewatch");
        stonewatchButton.setBounds(770,600,150,150);
        stonewatchButton.setContentAreaFilled(false);
        stonewatchButton.setBorderPainted(false);
        stonewatchButton.setOpaque(false);
        stonewatchButton.addActionListener(this);
        stonewatchButton.addMouseListener(createMouseListener("STONEWATCH", "Story of Stonewatch"));

        //justice button
        justiceButton = new JButton("Justice");
        justiceButton.setBounds(850,350,80,80);
        justiceButton.setContentAreaFilled(false);
        justiceButton.setBorderPainted(false);
        justiceButton.setOpaque(false);
        justiceButton.addActionListener(this);
        justiceButton.addMouseListener(createMouseListener("JUSTICE CENTRAL", "Story of Justice Central"));

        //skyport button
        skyportButton = new JButton("Skyport");
        skyportButton.setBounds(850,260,80,80);
        skyportButton.setContentAreaFilled(false);
        skyportButton.setBorderPainted(false);
        skyportButton.setOpaque(false);
        skyportButton.addActionListener(this);
        skyportButton.addMouseListener(createMouseListener("SKYPORT TERMINAL", "Story of Skyport Terminal"));

        this.add(skyportButton);
        this.add(justiceButton);
        this.add(stonewatchButton);
        this.add(industriaButton);
        this.add(rosewoodButton);
        this.add(eldoriaButton);
        this.add(climavoidButton);
        this.add(ironspireButton);
        this.add(amberdaleButton);
        this.add(willowbrookButton);
        this.add(azureportButton);
        this.add(rusthavenButton);
        this.add(backwardenButton);
        this.add(havenbrookButton);
        this.add(ghosthavenButton);
        this.add(shadowfallButton);
        this.add(academereButton);
        this.add(valoriaButton);
    }

    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource()==enterButton){
            switch (city) {
                case "VALORIA" -> SwingUtilities.invokeLater(() -> {
                    GameMap.this.dispose();
                    Valoria valoria = new Valoria(550,420);
                    valoria.Quadrant1();
                });
                case "ELDORIA" -> SwingUtilities.invokeLater(() -> {
                    GameMap.this.dispose();
                    Eldoria eldoria = new Eldoria(50,200);
                    eldoria.Quadrant2();
                });
                case "HAVENBROOK" -> SwingUtilities.invokeLater(() -> {
                    GameMap.this.dispose();
                    Havenbrook havenbrook = new Havenbrook(300,100);
                    havenbrook.Quadrant1();
                });
                case "AMBERDALE" -> SwingUtilities.invokeLater(() -> {
                    GameMap.this.dispose();
                    Amberdale amberdale = new Amberdale(50,50);
                    amberdale.Quadrant1();
                });
                case "ACADEMERE" -> SwingUtilities.invokeLater(() -> {
                    GameMap.this.dispose();
                    Academere academere = new Academere(100,550);
                    academere.Quadrant1();
                });
                case "IRONSPIRE" -> SwingUtilities.invokeLater(() -> {
                    GameMap.this.dispose();
                    Ironspire ironspire = new Ironspire(20,100);
                    ironspire.Quadrant1();
                });
                case "WILLOWBROOK" -> SwingUtilities.invokeLater(() -> {
                    GameMap.this.dispose();
                    Willowbrook willowbrook = new Willowbrook(500,450);
                    willowbrook.Quadrant4();
                });
                case "SHADOWFALL" -> SwingUtilities.invokeLater(() -> {
                    GameMap.this.dispose();
                    Shadowfall shadowfall = new Shadowfall(150,650);
                    shadowfall.Quadrant4();
                });
                case "RUSTHAVEN" -> SwingUtilities.invokeLater(() -> {
                    GameMap.this.dispose();
                    Rusthaven rusthaven = new Rusthaven(100,600);
                    rusthaven.Quadrant2();
                });
                case "BACKWARDEN" -> SwingUtilities.invokeLater(() -> {
                    GameMap.this.dispose();
                    Backwarden backwarden = new Backwarden(1300,500);
                    backwarden.Quadrant3();
                });
                case "STONEWATCH" -> SwingUtilities.invokeLater(()->{
                    GameMap.this.dispose();
                    Stonewatch stonewatch = new Stonewatch(80,100);
                    stonewatch.Quadrant4();
                });
                case "GHOSTHAVEN" -> SwingUtilities.invokeLater(()->{
                    GameMap.this.dispose();
                    Ghosthaven ghosthaven = new Ghosthaven(150,650);
                    ghosthaven.Quadrant4();
                });
                case "INDUSTRIA" -> SwingUtilities.invokeLater(()->{
                    GameMap.this.dispose();
                    Industria industria = new Industria(1030,200);
                    industria.Quadrant3();
                });
                case "CLIMAVOID" -> SwingUtilities.invokeLater(()->{
                    GameMap.this.dispose();
                    ClimaVoid climaVoid = new ClimaVoid(50,50);
                    climaVoid.Quadrant1();
                });
                case "AZUREPORT" -> SwingUtilities.invokeLater(()->{
                    GameMap.this.dispose();
                    Azureport azureport = new Azureport(870,470);
                    azureport.Quadrant4();
                });
                case "ROSEWOOD" -> SwingUtilities.invokeLater(()->{
                    GameMap.this.dispose();
                    Rosewood rosewood = new Rosewood(50,650);
                    rosewood.Quadrant3();
                });
                case "JUSTICE CENTRAL" -> SwingUtilities.invokeLater(()->{
                    GameMap.this.dispose();
                    Justice justice = new Justice(1150,500);
                    justice.Quadrant1();
                });
                case "SKYPORT TERMINAL" -> SwingUtilities.invokeLater(()->{
                    GameMap.this.dispose();
                    Skyport skyport = new Skyport(1200,650);
                    skyport.Quadrant2();
                });
            }
        }else if(e.getSource()!=enterButton){
            if(!buttonClicked){
                enterButton.setVisible(true);
                enterButton.setEnabled(true);

                buttonClicked = true;
            }else{
                enterButton.setVisible(false);
                enterButton.setEnabled(false);

                buttonClicked = false;
            }
            this.setFocusable(true);
            this.requestFocusInWindow();
        }
    }

    private MouseAdapter createMouseListener(String city_name, String city_story){
        return new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e){
                if(!buttonClicked) {
                    if(e.getSource()==justiceButton||e.getSource()==willowbrookButton){
                        cityName_label.setFont(new Font("Times New Roman", Font.BOLD, 40));
                    }else if(e.getSource() == skyportButton){
                        cityName_label.setFont(new Font("Times New Roman", Font.BOLD, 35));
                    }else {
                        cityName_label.setFont(new Font("Times New Roman", Font.BOLD, 50));
                    }
                    cityName_label.setText(city_name);
                    cityStory_label.setText(city_story);
                    city = city_name;
                }
            }
            @Override
            public void mouseExited(MouseEvent e){
                if(!buttonClicked) {
                    cityName_label.setText("");
                    cityStory_label.setText("");
                }
            }
        };
    }
}
