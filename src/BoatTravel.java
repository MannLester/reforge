import javax.swing.*;
import java.awt.*;

public class BoatTravel extends JFrame{
    String root = "C:\\Users\\User\\Reforge2\\src\\assets\\";
    int width = 1005;
    int height = 725;
    ImageIcon travel_pic = new ImageIcon(root + "Boat\\travelPanel.jpg");
    ImageIcon feeButton_pic = new ImageIcon(root + "Buttons\\misc\\travelButton.png");
    ImageIcon[] destinations = {
            new ImageIcon(root + "Boat\\valoria.jpg"),
            new ImageIcon(root + "Boat\\academere.jpg"),
            new ImageIcon(root + "Boat\\shadowfall.jpg"),
            new ImageIcon(root + "Boat\\climavoid.jpg"),
            new ImageIcon(root + "Boat\\stonewatch.jpg"),
            new ImageIcon(root + "Boat\\justice.jpg"),
            new ImageIcon(root + "Boat\\skyport.jpg"),
            new ImageIcon(root + "Boat\\industria.jpg"),
            new ImageIcon(root + "Boat\\ghosthaven.jpg"),
            new ImageIcon(root + "Boat\\havenbrook.jpg"),
            new ImageIcon(root + "Boat\\eldoria.jpg")
    };
    String[] destinationName = {
            "Valoria",
            "Academere",
            "Shadowfall",
            "ClimaVoid",
            "Stonewatch",
            "Justice Central",
            "Skyport Terminal",
            "Industria",
            "Ghosthaven",
            "Havenbrook",
            "Eldoria"
    };
    JLabel travel_bg, destination_bg, destination_name;
    JButton rightButton, leftButton, feeButton;
    int currentIndex = 0;

    BoatTravel() {
        this.setSize(width, height);
        this.setUndecorated(true);
        this.setVisible(true);
        this.requestFocusInWindow();

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (dim.width - getWidth()) / 2;
        int y = (dim.height - getHeight()) / 2;

        setLocation(x, y);

        setLayout(null);

        travel_bg = new JLabel();
        travel_bg.setIcon(travel_pic);
        travel_bg.setBounds(0, 0, width, height);

        destination_bg= new JLabel();
        destination_bg.setBounds(252, 150, 500,500);

        destination_name = new JLabel();
        destination_name.setBounds(350, 20, 550,150);

        leftButton = new JButton();
        leftButton.setContentAreaFilled(false);
        leftButton.setBorderPainted(false);
        leftButton.setOpaque(false);
        leftButton.setBounds(130, 310, 100, 100);
        leftButton.addActionListener(e -> handleLeftButtonClick());

        rightButton = new JButton();
        rightButton.setContentAreaFilled(false);
        rightButton.setBorderPainted(false);
        rightButton.setOpaque(false);
        rightButton.setBounds(780, 310, 100, 100);
        rightButton.addActionListener(e -> handleRightButtonClick());

        feeButton = new JButton();
        feeButton.setIcon(feeButton_pic);
        feeButton.setContentAreaFilled(false);
        feeButton.setBorderPainted(false);
        feeButton.setOpaque(true);
        feeButton.setBounds(775,600,180,100);
        feeButton.addActionListener(e -> handleFeeButtonClick());

        this.add(leftButton);
        this.add(rightButton);
        this.add(feeButton);
        this.add(destination_name);
        this.add(destination_bg);
        this.add(travel_bg);

        updateDestinationImage();
    }

    private void handleLeftButtonClick() {
        currentIndex = (currentIndex - 1 + destinations.length) % destinations.length;
        updateDestinationImage();
    }

    private void handleRightButtonClick() {
        currentIndex = (currentIndex + 1) % destinations.length;
        updateDestinationImage();
    }

    private void updateDestinationImage() {
        destination_name.setText(destinationName[currentIndex]);
        destination_name.setFont(new Font("Brush Script MT", Font.PLAIN, 80));
        destination_name.setHorizontalAlignment(JLabel.CENTER);
        destination_bg.setIcon(destinations[currentIndex]);
    }

    private void handleFeeButtonClick(){
        if(currentIndex == 0){
            BoatTravel.this.dispose();
            Valoria valoria = new Valoria(550,420);
            valoria.Quadrant1();
        }else if(currentIndex == 1){
            BoatTravel.this.dispose();
            Academere academere = new Academere(100,550);
            academere.Quadrant1();
        }else if(currentIndex == 2){
            BoatTravel.this.dispose();
            Shadowfall shadowfall = new Shadowfall(150,650);
            shadowfall.Quadrant4();
        }else if(currentIndex == 3){
            BoatTravel.this.dispose();
            ClimaVoid climaVoid = new ClimaVoid(50,50);
            climaVoid.Quadrant1();
        }else if(currentIndex == 4){
            BoatTravel.this.dispose();
            Stonewatch stonewatch = new Stonewatch(130,700);
            stonewatch.Quadrant2();
        }else if(currentIndex == 5){
            BoatTravel.this.dispose();
            Justice justice = new Justice(1150,500);
            justice.Quadrant1();
        }else if(currentIndex == 6){
            BoatTravel.this.dispose();
            Skyport skyport = new Skyport(1200,650);
            skyport.Quadrant2();
        }else if(currentIndex == 7){
            BoatTravel.this.dispose();
            Industria industria = new Industria(1030,200);
            industria.Quadrant3();
        }else if(currentIndex == 8){
            BoatTravel.this.dispose();
            Ghosthaven ghosthaven = new Ghosthaven(150,650);
            ghosthaven.Quadrant4();
        }else if(currentIndex == 9){
            BoatTravel.this.dispose();
            Havenbrook havenbrook = new Havenbrook(300,100);
            havenbrook.Quadrant1();
        }else{
            BoatTravel.this.dispose();
            Eldoria eldoria = new Eldoria(50,200);
            eldoria.Quadrant2();
        }
    }
}
