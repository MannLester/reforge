import javax.management.Notification;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import Database.*;


public class Blacksmith extends JFrame implements ActionListener {
    loggedin_identifier logidentifier = new loggedin_identifier();

    read_table readtable = new read_table();
    read_bossdroptable readt = new read_bossdroptable();
    TableInserter tableinsert = new TableInserter();
    TableUpdater tableupdate = new TableUpdater();
    read_jointable readjointable = new read_jointable();

    String[] currency_table_columns = {"CURRENCY.CURRENCY_ID","GOLD_COUNT","IRON_COUNT","HERB_COUNT","BISCUIT_COUNT","DIAMOND_COUNT"};
    String[] player_table_columns = {"ACCOUNT_ID","WEAPONS_FORGED","PLAYER_HP","PLAYER_DMG","PET_OWNED","BOSSES_KILLED","PETS_TAMED","PLAYERS.CURRENCY_ID", "PLAYERS.WEAPONS_FORGED"};
    String[] item_table_columns = {"ITEM_ID","ITEM_NAME","ITEM_SKILL","ITEM_DESC","ITEM_EFFECT","DAMAGE","GOLD_AMOUNT","IRON_AMOUNT","HERB_AMOUNT","ITEM_TYPE","URL"};

    String root = "C:\\Users\\User\\Reforge2\\src\\assets\\";
    private boolean buttonClicked = false;
    ImageIcon imageShow;
    ImageIcon exit = new ImageIcon(root + "Buttons\\misc\\exitButton.png");
    ImageIcon goldIcon = new ImageIcon(root + "Currency\\Gold Icon.png");
    ImageIcon ironIcon = new ImageIcon(root + "Currency\\Iron Icon.png");
    ImageIcon frostbiteDagger_button = new ImageIcon(root + "Buttons\\attack_buttons\\frostbite_dagger_button.png");
    ImageIcon infernoScythe_button = new ImageIcon(root + "Buttons\\attack_buttons\\inferno_scythe_button.png");
    ImageIcon phoenixWingSpear_button = new ImageIcon(root + "Buttons\\attack_buttons\\phoenix_wing_spear_button.png");
    ImageIcon runeboundCrossbow_button = new ImageIcon(root + "Buttons\\attack_buttons\\runebound_crossbow_button.png");
    ImageIcon serpentsClaws_button = new ImageIcon(root + "Buttons\\attack_buttons\\serpent's_claws_button.png");
    ImageIcon shadowReaverAxe_button = new ImageIcon(root + "Buttons\\attack_buttons\\shadow_reaver_axe_button.png");
    ImageIcon soulshatterBlade_button = new ImageIcon(root + "Buttons\\attack_buttons\\soulshatter_blade_button.png");
    ImageIcon starfallBow_button = new ImageIcon(root + "Buttons\\attack_buttons\\starfall_bow_button.png");
    ImageIcon thunderstrikeHammer_button = new ImageIcon(root + "Buttons\\attack_buttons\\thunderstrike_hammer_button.png");
    ImageIcon voidbringerStaff_button = new ImageIcon(root + "Buttons\\attack_buttons\\voidbringer_staff_button.png");
    ImageIcon forgeButton_button = new ImageIcon(root + "Buttons\\attack_buttons\\forgeButton.png");

    String frostbiteDagger_pic_url = root + "Buttons\\attack_buttons\\frostbite_dagger_button.png";
    String infernoScythe_pic_url = root + "Buttons\\attack_buttons\\inferno_scythe_button.png";
    String phoenixWingSpear_pic_url = root + "Buttons\\attack_buttons\\phoenix_wing_spear_button.png";
    String runeboundCrossbow_pic_url = root + "Buttons\\attack_buttons\\runebound_crossbow_button.png";
    String serpentsClaws_pic_url = root + "Buttons\\attack_buttons\\serpent's_claws_button.png";
    String shadowReaverAxe_pic_url = root + "Buttons\\attack_buttons\\shadow_reaver_axe_button.png";
    String soulshatterBlade_pic_url = root + "Buttons\\attack_buttons\\soulshatter_blade_button.png";
    String starfallBow_pic_url = root + "Buttons\\attack_buttons\\starfall_bow_button.png";
    String thunderstrikeHammer_pic_url = root + "Buttons\\attack_buttons\\thunderstrike_hammer_button.png";
    String voidbringerStaff_pic_url = root + "Buttons\\attack_buttons\\voidbringer_staff_button.png";
    JPanel itemSide, processSide;
    JLabel itemSide_label, itemShow_label, itemName_label, itemDesc_label, itemGold_label, itemIron_label, itemGoldIcon_label, itemIronIcon_label,
            goldIcon_label, goldAmount_label, ironIcon_label, ironAmount_label, itemName2_label, itemDesc2_label, itemGold2_label, itemIron2_label,
            goldAmount2_label, ironAmount2_label;
    JButton frostbiteDagger, infernoScythe, phoenixWingSpear, runeboundCrossbow, serpentsClaws;
    JButton shadowReaverAxe, soulshatterBlade, starfallBow, thunderstrikeHammer, voidbringerStaff;
    JButton forgeButton, exitButton;

    public String[] data_table(String url, String[] table_to_get, String table, String identifier){
        String[] data_from_table = new String[table_to_get.length];
        for (int i = 0; i < table_to_get.length; i++)
        {
            data_from_table[i] = readtable.readValue(table,identifier, url, table_to_get[i]);
//            System.out.println(data_from_table[i]);
        }
        return data_from_table;
    }
    public String[] data_jointable(String boss_identifier, String[] info_to_get, String table1, String table2, String identifer, String limit){
        String[] name = readt.readjoindropValues(boss_identifier, info_to_get,table1,table2, identifer, limit);

        return name;
    }

    Blacksmith(){
        int width = 1520;
        int height = 820;

        //frame layout
        this.setVisible(true);
//        SkillsChosen(values);
        this.setSize(width, height); //input values of frame width and height here to automatically adjust other panels
        this.setTitle("Reforge: Rise of the Seventeen Cities");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setResizable(false);
//        this.getContentPane().setBackground(Color.BLACK);
        this.setLayout(new BorderLayout());

        //background
        ImageIcon bg_pic = new ImageIcon(root + "Backgrounds\\Blacksmith_bg.jpg");
        JLabel bg = new JLabel();
        bg.setIcon(bg_pic);
        bg.setBounds(0,0, width, height);
        bg.setOpaque(false);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (dim.width - getWidth()) / 2;

        setLocation(x, 0);

        PlayerAmount();

        AttackItems();

        ForgeProcess();

        //add to frame
        this.add(exitButton);
        this.add(itemDesc2_label);
        this.add(itemName2_label);
        this.add(ironAmount2_label);
        this.add(ironAmount_label);
        this.add(ironIcon_label);
        this.add(goldAmount2_label);
        this.add(goldAmount_label);
        this.add(goldIcon_label);
        this.add(forgeButton);
        this.add(itemIronIcon_label);
        this.add(itemIron2_label);
        this.add(itemIron_label);
        this.add(itemGoldIcon_label);
        this.add(itemGold2_label);
        this.add(itemGold_label);
        this.add(itemDesc_label);
        this.add(itemName_label);
        this.add(processSide);
        this.add(itemSide);
        this.add(bg);
    }


    String[] player_data, currency_data = new String[6];
    String currency_id;
    int player_gold_amount, player_iron_amount;

    public void PlayerAmount(){
        player_data = data_table(account_loggedin,player_table_columns, "PLAYERS", "ACCOUNT_ID");
        currency_id = player_data[7];

        currency_data = data_jointable(currency_id, currency_table_columns, "PLAYERS", "CURRENCY","CURRENCY_ID","");
        player_gold_amount = Integer.parseInt(currency_data[1]);
        player_iron_amount = Integer.parseInt(currency_data[2]);

        //label for player gold icon
        goldIcon_label = new JLabel();
        goldIcon_label.setBounds(550,0,45,45);
        goldIcon_label.setIcon(goldIcon);
        goldIcon_label.setOpaque(false);

        //label for player gold bg
        ImageIcon playerGold = new ImageIcon(root + "Cities\\Valoria\\Blacksmith\\owned_contain.png");
        goldAmount_label = new JLabel();
        goldAmount_label.setBounds(600,0,150,45);
        goldAmount_label.setIcon(playerGold);
        goldAmount_label.setText(currency_data[1]);

        //label for player gold
        goldAmount2_label = new JLabel();
        goldAmount2_label.setBounds(615,0,150,45);
        goldAmount2_label.setText(currency_data[1]);


        //label for player iron icon
        ironIcon_label = new JLabel();
        ironIcon_label.setBounds(800,0,45,45);
        ironIcon_label.setIcon(ironIcon);
        ironIcon_label.setOpaque(false);

        //label for player iron bg
        ImageIcon playerIron = new ImageIcon(root + "Cities\\Valoria\\Blacksmith\\owned_contain.png");
        ironAmount_label = new JLabel();
        ironAmount_label.setBounds(850,0,150,45);
        ironAmount_label.setIcon(playerIron);
        ironAmount_label.setText(currency_data[2]);


        //label for player iron
        ironAmount2_label = new JLabel();
        ironAmount2_label.setBounds(865,0,150,45);
        ironAmount2_label.setText(currency_data[2]);

    }

    public String[] data_table(String url){
        String[] table_columns = {"ITEM_ID", "ITEM_NAME", "ITEM_SKILL", "ITEM_DESC","DAMAGE","GOLD_AMOUNT","IRON_AMOUNT"};
        String[] data_from_table = new String[7];
        for (int i = 0; i < table_columns.length; i++)
        {
            data_from_table[i] = readtable.readValue("item","URL",url,table_columns[i]);
        }
        return data_from_table;
    }
    String[] imageUrlArray = {
            root + "Cities\\Valoria\\Blacksmith\\Frostbite Dagger.png",
            root + "Cities\\Valoria\\Blacksmith\\Inferno Scythe.png",
            root + "Cities\\Valoria\\Blacksmith\\Phoenix Wing Spear.png",
            root + "Cities\\Valoria\\Blacksmith\\Runebound Crossbow.png",
            root + "Cities\\Valoria\\Blacksmith\\Serpent's Claws.png",
            root + "Cities\\Valoria\\Blacksmith\\Shadow Reaver Axe.png",
            root + "Cities\\Valoria\\Blacksmith\\Soulshatter Blade.png",
            root + "Cities\\Valoria\\Blacksmith\\Starfall Bow.png",
            root + "Cities\\Valoria\\Blacksmith\\Thunderstrike Hammer.png",
            root + "Cities\\Valoria\\Blacksmith\\Voidbringer Staff.png",

    };
    String[] data = new String[7];
    String[] support_buttons_array = {frostbiteDagger_pic_url, infernoScythe_pic_url,phoenixWingSpear_pic_url, runeboundCrossbow_pic_url,
            serpentsClaws_pic_url, shadowReaverAxe_pic_url, soulshatterBlade_pic_url, starfallBow_pic_url,
            thunderstrikeHammer_pic_url, voidbringerStaff_pic_url};
    String account_loggedin = logidentifier.readValue("LOGGEDIN_IDENTIFIER","ACCOUNT_ID");

    String[] inventory_data = readjointable.readjoinValues(account_loggedin, "URL");
    String[] support_buttonsname_array = {"Frostbite Dagger", "Inferno Scythe","Phoenix Wing Spear","Runebound Crossbow",
            "Serpent's Claws", "Shadow Reaver Axe","Soulshatter Blade","Starfall Bow","Thunderstrike Hammer",
            "Voidbringer Staff"};
    JButton[] supportButtons = new JButton[10];

    public void AttackItems(){
        frostbiteDagger = new JButton(frostbiteDagger_button);
        infernoScythe = new JButton(infernoScythe_button);
        phoenixWingSpear = new JButton(phoenixWingSpear_button);
        runeboundCrossbow = new JButton(runeboundCrossbow_button);
        serpentsClaws = new JButton(serpentsClaws_button);
        shadowReaverAxe = new JButton(shadowReaverAxe_button);
        soulshatterBlade = new JButton(soulshatterBlade_button);
        starfallBow = new JButton(starfallBow_button);
        thunderstrikeHammer = new JButton(thunderstrikeHammer_button);
        voidbringerStaff = new JButton(voidbringerStaff_button);

        String support_name, support_desc,support_gold, support_iron;
        ImageIcon itemSide_bg = new ImageIcon(root + "itemSide.png");
        itemSide = new JPanel();
        itemSide.setLayout(new BorderLayout());
        itemSide.setBounds(1030,45, 300,720);
        itemSide.setOpaque(false);

        //label for buttons setup
        itemSide_label = new JLabel();
        itemSide_label.setIcon(itemSide_bg);
        itemSide_label.setBounds(0,0,300, 720);
        GridLayout itemSideLayout = new GridLayout(5, 2, 10, 10);
        itemSide.setLayout(itemSideLayout);

        for (int i = 0; i < Math.min(support_buttons_array.length, supportButtons.length); i++) {
            String[] support_data = data_table(support_buttons_array[i],item_table_columns,"ITEM","URL");
            support_name = support_data[1];
            support_desc = support_data[3];
            support_gold = support_data[6];
            support_iron = support_data[7];
            ImageIcon buttonIcon = new ImageIcon(support_buttons_array[i]);

            supportButtons[i] = new JButton(buttonIcon);
            supportButtons[i].setContentAreaFilled(false);
            supportButtons[i].setBorderPainted(false);
            supportButtons[i].setFocusable(false);
            String[] support_buttonsname_array = {"Frostbite Dagger", "Inferno Scythe","Phoenix Wing Spear","Runebound Crossbow",
                    "Serpent's Claws", "Shadow Reaver Axe","Soulshatter Blade","Starfall Bow","Thunderstrike Hammer",
                    "Voidbringer Staff"};
            switch (support_buttonsname_array[i]) {
                case "Frostbite Dagger":
                    supportButtons[i].setBounds(10, 10, 140, 140);
                    supportButtons[i].addActionListener(this);
                    // Add other properties or listeners if needed
                    break;
                case "Inferno Scythe":
                    supportButtons[i].setBounds(150, 10, 140, 140);
                    supportButtons[i].addActionListener(this);
                    break;
                case "Phoenix Wing Spear":
                    supportButtons[i].setBounds(10, 145, 140, 140);
                    supportButtons[i].addActionListener(this);
                    break;
                case "Runebound Crossbow":
                    supportButtons[i].setBounds(150, 145, 140, 140);
                    supportButtons[i].addActionListener(this);
                    break;
                case "Serpent's Claws":
                    supportButtons[i].setBounds(10, 285, 140, 140);
                    supportButtons[i].addActionListener(this);
                    break;
                case "Shadow Reaver Axe":
                    supportButtons[i].setBounds(150, 285, 140, 140);
                    supportButtons[i].addActionListener(this);
                    break;
                case "Soulshatter Blade":
                    supportButtons[i].setBounds(10, 425, 140, 140);
                    supportButtons[i].addActionListener(this);
                    break;
                case "Starfall Bow":
                    supportButtons[i].setBounds(150, 425, 140, 140);
                    supportButtons[i].addActionListener(this);
                    break;
                case "Thunderstrike Hammer":
                    supportButtons[i].setBounds(5, 560, 140, 140);  // Set bounds for Shieldbearer's Pendant
                    supportButtons[i].addActionListener(this);
                    break;
                case "Voidbringer Staff":
                    supportButtons[i].setBounds(150, 560, 140, 140);  // Set bounds for Warding Ring
                    supportButtons[i].addActionListener(this);
                    break;
                default:
                    // Default position if the buttonName is not recognized
                    supportButtons[i].addActionListener(this);
                    add(supportButtons[i]);

            }

            // Check if the URL is in the data array and disable the button if not found
            boolean weaponFound = false;
            for (String dataURL : inventory_data) {
                if (support_buttons_array[i].endsWith(dataURL)) {
                    weaponFound = true;
                    break;
                }
            }

            if (weaponFound) {
                supportButtons[i].setEnabled(false);
            }
            supportButtons[i].addMouseListener(createMouseListener(support_name,support_desc,support_gold, support_iron, imageUrlArray[i]));
            add(supportButtons[i]);
            itemSide.add(supportButtons[i]);
        }
    }

    public void ForgeProcess(){
        //label for weapon image. with name and description. gold and iron amount. and forge button
        processSide = new JPanel();
        processSide.setOpaque(false);
        processSide.setLayout(new BorderLayout());
        processSide.setBounds(0,45,500,720);

        //label for the item showcase
        itemShow_label = new JLabel();
//        itemShow_label.setBackground(Color.DARK_GRAY);
        itemShow_label.setOpaque(false);
        itemShow_label.setBounds(0,0,500,720);

        //label for the item name bg
        ImageIcon forgeName = new ImageIcon(root + "Cities\\Valoria\\Blacksmith\\forgeName.png");
        itemName_label = new JLabel();
        itemName_label.setIcon(forgeName);
        itemName_label.setOpaque(false);
        itemName_label.setBounds(550, 65, 450, 100);

        //label for the item name
        itemName2_label = new JLabel();
        itemName2_label.setBounds(550,65,450,100);
        itemName2_label.setForeground(Color.BLACK);
        itemName2_label.setFont(new Font("Times New Roman", Font.BOLD, 20));
        itemName2_label.setHorizontalAlignment(JLabel.CENTER);

        //label for the item desc
        ImageIcon weaponDesc = new ImageIcon(root + "Cities\\Valoria\\Blacksmith\\contain.png");
        itemDesc_label = new JLabel();
        itemDesc_label.setIcon(weaponDesc);
        itemDesc_label.setOpaque(false);
        itemDesc_label.setBounds(550, 200, 450, 300);

        //label for the item desc
        itemDesc2_label = new JLabel();
        itemDesc2_label.setBounds(590,280,380,300);
        itemDesc2_label.setForeground(Color.BLACK);
        itemDesc2_label.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        itemDesc2_label.setHorizontalAlignment(JLabel.CENTER);
        itemDesc2_label.setVerticalAlignment(JLabel.TOP);

        //label for item gold amount bg
        ImageIcon goldAmount = new ImageIcon(root + "Cities\\Valoria\\Blacksmith\\amount_contain.png");
        itemGold_label = new JLabel();
        itemGold_label.setIcon(goldAmount);
        itemGold_label.setBounds(595, 530, 175, 50);

        //label for item gold icon
        itemGoldIcon_label = new JLabel();
        itemGoldIcon_label.setIcon(goldIcon);
        itemGoldIcon_label.setBounds(550,530,45,50);
        itemGoldIcon_label.setOpaque(false);

        //label for item gold amount
        itemGold2_label = new JLabel();
        itemGold2_label.setBounds(610,527,175,50);

        //label for item iron amount bg
        ImageIcon ironAmount = new ImageIcon(root + "Cities\\Valoria\\Blacksmith\\amount_contain.png");
        itemIron_label = new JLabel();
        itemIron_label.setIcon(ironAmount);
        itemIron_label.setBounds(825, 530, 175, 50);

        //label for item iron icon
        itemIronIcon_label = new JLabel();
        itemIronIcon_label.setIcon(ironIcon);
        itemIronIcon_label.setBounds(780,530,45,50);
        itemIronIcon_label.setOpaque(false);

        //label for item iron amount
        itemIron2_label = new JLabel();
        itemIron2_label.setBounds(840, 527, 175,50);

        //button for forging
        forgeButton = new JButton(forgeButton_button);
        forgeButton.setBorderPainted(false);
        forgeButton.setFocusable(false);
        forgeButton.setContentAreaFilled(false);
        forgeButton.setBounds(660, 620, 220, 100);
        forgeButton.setVisible(false);
        forgeButton.setEnabled(false);
        forgeButton.addActionListener(this);

        //button for exit
        exitButton = new JButton(exit);
        exitButton.setBorderPainted(false);
        exitButton.setFocusable(false);
        exitButton.setContentAreaFilled(false);
        exitButton.setBounds(1350,660,100,100);
        exitButton.setVisible(true);
        exitButton.setEnabled(true);
        exitButton.addActionListener(this);

        //add to panel
        processSide.add(itemShow_label);
    }

    String weapon_clicked = "";
    String weapon_clicked_url = "";
    int gold_amt, iron_amt;
    String[] weapon_data = new String[7];

    @Override
    public void actionPerformed(ActionEvent e) {
        gold_amt = 0;
        iron_amt = 0;

        Object[][] weapons = {
                {supportButtons[0], "frostbite dagger", frostbiteDagger_pic_url},
                {supportButtons[1], "inferno scythe", infernoScythe_pic_url},
                {supportButtons[2], "phoenix wing spear", phoenixWingSpear_pic_url},
                {supportButtons[3], "runebound crossbow", runeboundCrossbow_pic_url},
                {supportButtons[4], "serpent'c claws", serpentsClaws_pic_url},
                {supportButtons[5], "shadow reaver axe", shadowReaverAxe_pic_url},
                {supportButtons[6], "soulshatter blade", soulshatterBlade_pic_url},
                {supportButtons[7], "starfall bow", starfallBow_pic_url},
                {supportButtons[8], "thunderstrike hammer", thunderstrikeHammer_pic_url},
                {supportButtons[9], "voidbringer staff",voidbringerStaff_pic_url}
        };

        for (Object[] weapon : weapons) {
            if (e.getSource() == weapon[0]) {
                System.out.println(weapon[1] + " clicked!");
                weapon_clicked = (String) weapon[1];
                weapon_clicked_url = (String) weapon[2];
                weapon_data = data_table((String) weapon[2]);

                gold_amt = Integer.parseInt(weapon_data[5]);
                iron_amt = Integer.parseInt(weapon_data[6]);
                break; // Exit the loop once a match is found
            }
        }
        if (e.getSource() != forgeButton && e.getSource() == exitButton) {
            SwingUtilities.invokeLater(Blacksmith.this::dispose);
        }
        if (e.getSource() != forgeButton) {
            if (!buttonClicked) {
                // Check if player has enough gold and biscuit
                if ((player_gold_amount >= gold_amt) && (player_iron_amount >= iron_amt)) {
                    forgeButton.setVisible(true);
                    forgeButton.setEnabled(true);
                } else {
                    forgeButton.setVisible(false);
                    forgeButton.setEnabled(false);
                }
                buttonClicked = true;
            } else {
                forgeButton.setVisible(false);
                forgeButton.setEnabled(false);
                buttonClicked = false;
            }
        }else{
//         String[] table_columns = {"ITEM_ID", "ITEM_NAME", "ITEM_EFFECT", "ITEM_DESC","GOLD_AMOUNT","URL","HERB_AMOUNT"};
            if (weapon_data[4] != null && weapon_data[6] != null) {
                gold_amt = Integer.parseInt(weapon_data[5]);
                iron_amt = Integer.parseInt(weapon_data[6]);
                //print debugger
//                System.out.println("gold_amt: " + gold_amt);
//                System.out.println("player_gold_amount: " + player_gold_amount);


//                System.out.println("support: " + weapon_clicked);
//                System.out.println("url: " + weapon_clicked_url);
//                System.out.println("gold amount: " + gold_amt);
//                System.out.println("iron amount: " + iron_amt);

                // Print values to understand why they are null
//                System.out.println("support_data[5]new: " + weapon_data[5]);
//                System.out.println("support_data[6]: " + weapon_data[6]);

                // update database here
                currency_data = data_jointable(currency_id, currency_table_columns, "PLAYERS", "CURRENCY", "CURRENCY_ID", "");
                if ((player_gold_amount >= gold_amt) && (player_iron_amount >= iron_amt)) {

                    int gold_amt_toupdate = player_gold_amount - gold_amt;
                    int iron_amt_toupdate = player_iron_amount - iron_amt;

                    System.out.println("Before updating database: gold_amt_toupdate = " + gold_amt_toupdate + ", herb_amt_toupdate = " + iron_amt_toupdate);

                    tableupdate.updateTable("CURRENCY", "GOLD_COUNT", "CURRENCY_ID", currency_id, gold_amt_toupdate);
                    tableupdate.updateTable("CURRENCY", "IRON_COUNT", "CURRENCY_ID", currency_id, iron_amt_toupdate);
                    tableupdate.updateTable("PLAYERS", "WEAPONS_FORGED", "CURRENCY_ID", currency_id, Integer.parseInt(player_data[8]) + 1);

                    String account_loggedin = logidentifier.readValue("LOGGEDIN_IDENTIFIER","ACCOUNT_ID");

                    String[] columns_of_inventory = {"ACCOUNT_ID", "ITEM_ID", "ITEMTYPE"};
                    String[] column_to_insert = {account_loggedin, weapon_data[0], "Weapon"};
                    tableinsert.insertIntoTable("inventory", columns_of_inventory, column_to_insert);

                    System.out.println("After updating database: gold_amt_toupdate = " + gold_amt_toupdate + ", herb_amt_toupdate = " + iron_amt_toupdate);

                    PlayerAmount();
                    AttackItems();
                    SwingUtilities.invokeLater(()-> new GameNotif("Successful Transaction!"));
                    SwingUtilities.invokeLater(Blacksmith.this::dispose);
                }
                else {
                    SwingUtilities.invokeLater(()-> new GameNotif("Not enough Resources!"));
                }
            } else {
                // Handle the case where support_data[4] or support_data[6] is null
                System.out.println("Error: support_data[4] or support_data[6] is null");
            }
        }
    }
    private MouseAdapter createMouseListener(String itemName, String itemDesc,
                                             String goldAmount, String ironAmount, String img){
        return new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                if(!buttonClicked) {
                    itemName2_label.setText(itemName);
                    itemDesc2_label.setText("<html>" + itemDesc + "</html>");
                    itemGold2_label.setText(goldAmount);
                    itemIron2_label.setText(ironAmount);
//                    goldAmount2_label.setText("200");
//                    ironAmount2_label.setText();

                    imageShow = new ImageIcon(img);
                    itemShow_label.setIcon(imageShow);
                }
            }
            @Override
            public void mouseExited(MouseEvent e){
                if(!buttonClicked) {
                    itemName2_label.setText("");
                    itemDesc2_label.setText("");
                    itemGold2_label.setText("");
                    itemIron2_label.setText("");
                    itemShow_label.setIcon(null);
                }
            }
        };
    }
}