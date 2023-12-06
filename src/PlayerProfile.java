import Database.loggedin_identifier;
import Database.read_bossdroptable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class PlayerProfile extends JFrame {
    loggedin_identifier logidentifier = new loggedin_identifier();

    String root = "C:\\Users\\User\\Reforge2\\src\\assets\\";
    int width = 1005;
    int height = 725;
    ImageIcon profile_pic = new ImageIcon(root + "Profile\\player_profile.jpg");
    JLabel profile_bg, equippedPet_label, weaponsForged_label, shieldsBought_label, potionsBrewed_label, petsTamed_label, bossesKilled_label, progressCompleted_label;
    JLabel playerName_label, playerID_label;
    String account_loggedin = logidentifier.readValue("LOGGEDIN_IDENTIFIER","ACCOUNT_ID");

    String[] player_table_columns = {"PLAYERS.ACCOUNT_ID", "PLAYERS.WEAPONS_FORGED", "PLAYERS.PLAYER_HP", "PLAYERS.PLAYER_DMG", "PLAYERS.BOSSES_KILLED",
            "PLAYERS.PETS_TAMED","PLAYERS.CURRENCY_ID","PLAYERS.PROGRESS_COMPLETED" ,"PLAYERS.POTIONS_BREWED", "PLAYERS.SHIELDS_BOUGHT","ACCOUNTS.USERNAME"};
    read_bossdroptable readt = new read_bossdroptable();

    public String[] data_jointable(String boss_identifier, String[] info_to_get, String table1, String table2, String identifer, String limit) {
        String[] name = readt.readjoindropValues(boss_identifier, info_to_get, table1, table2, identifer, limit);

//        for (String val: name)
//        {
////            System.out.println(val);
//        }
        return name;
    }
    PlayerProfile(){
        String[] player_data = data_jointable(account_loggedin, player_table_columns, "ACCOUNTS", "PLAYERS", "ACCOUNT_ID", "");

        this.setSize(width, height);
        this.setUndecorated(true);
        this.setVisible(true);
        this.requestFocusInWindow();

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (dim.width - getWidth()) / 2;
        int  y = (dim.height - getHeight()) / 2;

        setLocation(x,y);

        setLayout(null);

        profile_bg = new JLabel();
        profile_bg.setIcon(profile_pic);
        profile_bg.setBounds(0,0, width, height);

        playerName_label = new JLabel();
        playerName_label.setOpaque(true);
        playerName_label.setBounds(354,105,383,42);

        playerID_label = new JLabel();
        playerID_label.setOpaque(true);
        playerID_label.setBounds(354,225,383,42);

        weaponsForged_label = new JLabel();
        weaponsForged_label.setOpaque(true);
        weaponsForged_label.setBounds(345,340,100,50);

        shieldsBought_label = new JLabel();
        shieldsBought_label.setOpaque(true);
        shieldsBought_label.setBounds(345, 450,100,50);

        potionsBrewed_label = new JLabel();
        potionsBrewed_label.setOpaque(true);
        potionsBrewed_label.setBounds(345, 560,100,50);

        petsTamed_label = new JLabel();
        petsTamed_label.setOpaque(true);
        petsTamed_label.setBounds(795,340,100,50);

        bossesKilled_label = new JLabel();
        bossesKilled_label.setOpaque(true);
        bossesKilled_label.setBounds(795,450,100,50);

        progressCompleted_label = new JLabel();
        progressCompleted_label.setOpaque(true);
        progressCompleted_label.setBounds(795,560,100,50);

        this.add(playerName_label);
        this.add(playerID_label);
        this.add(weaponsForged_label);
        this.add(shieldsBought_label);
        this.add(potionsBrewed_label);
        this.add(petsTamed_label);
        this.add(bossesKilled_label);
        this.add(progressCompleted_label);
        this.add(profile_bg);

        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();
                if (keyCode == KeyEvent.VK_P){
                    SwingUtilities.invokeLater(PlayerProfile.this::dispose);
                }

            }

            @Override
            public void keyReleased(KeyEvent e) {
                // Not used in this example
            }
        });

        // Make the frame focusable to receive key events
        this.setFocusable(true);
        this.requestFocusInWindow();
        String[] player_table_columns = {"PLAYERS.ACCOUNT_ID", "PLAYERS.WEAPONS_FORGED", "PLAYERS.PLAYER_HP", "PLAYERS.PLAYER_DMG", "PLAYERS.BOSSES_KILLED",
                "PLAYERS.PETS_TAMED","PLAYERS.CURRENCY_ID","PLAYERS.PROGRESS_COMPLETED", "PLAYERS.POTIONS_BREWED", "PLAYERS.SHIELDS_BOUGHT","ACCOUNTS.USERNAME"};

        playerName_label.setText(player_data[10]);
        playerID_label.setText(player_data[0]);
        weaponsForged_label.setText(player_data[1]);
        shieldsBought_label.setText(player_data[9]);
        potionsBrewed_label.setText(player_data[8]);
        petsTamed_label.setText(player_data[5]);
        bossesKilled_label.setText(player_data[4]);
        progressCompleted_label.setText(player_data[7]);
    }
}
