import Database.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Merchant extends JFrame implements ActionListener{
    read_table readtable = new read_table();
    loggedin_identifier logidentifier = new loggedin_identifier();

    read_bossdroptable readt = new read_bossdroptable();
    TableUpdater tableupdate = new TableUpdater();
    read_jointable readjointable = new read_jointable();
    TableInserter tableinsert = new TableInserter();
    String account_loggedin = logidentifier.readValue("LOGGEDIN_IDENTIFIER","ACCOUNT_ID");



    String[] currency_table_columns = {"CURRENCY.CURRENCY_ID","GOLD_COUNT","IRON_COUNT","HERB_COUNT","BISCUIT_COUNT","DIAMOND_COUNT"};
    String[] player_table_columns = {"ACCOUNT_ID","WEAPONS_FORGED","PLAYER_HP","PLAYER_DMG","PET_OWNED","BOSSES_KILLED","PETS_TAMED","PLAYERS.CURRENCY_ID","PLAYERS.SHIELDS_BOUGHT"};
    String[] item_table_columns = {"ITEM_ID","ITEM_NAME","ITEM_SKILL","ITEM_DESC","ITEM_EFFECT","DAMAGE","GOLD_AMOUNT","IRON_AMOUNT","HERB_AMOUNT","ITEM_TYPE","URL"};


    String root = "C:\\Users\\User\\Reforge2\\src\\assets\\";
    ImageIcon imageShow;
    private boolean buttonClicked = false;
    ImageIcon exit = new ImageIcon(root + "Buttons\\misc\\exitButton.png");
    ImageIcon goldIcon = new ImageIcon(root + "Currency\\Gold Icon.png");
    ImageIcon aegisSteel_button = new ImageIcon(root + "Buttons\\defense_buttons\\aegis_steel_button.png");
    ImageIcon armorersGemstone_button = new ImageIcon(root + "Buttons\\defense_buttons\\armorers_gemstone_button.png");
    ImageIcon barrierBracelet_button = new ImageIcon(root + "Buttons\\defense_buttons\\barrier_bracelet_button.png");
    ImageIcon defendersTalisman_button = new ImageIcon(root + "Buttons\\defense_buttons\\defenders_talisman_button.png");
    ImageIcon guardiansAmulet_button = new ImageIcon(root + "Buttons\\defense_buttons\\guardians_amulet_button.png");
    ImageIcon protectionCharm_button = new ImageIcon(root + "Buttons\\defense_buttons\\protection_charm_button.png");
    ImageIcon safeguardingMedallion_button = new ImageIcon(root + "Buttons\\defense_buttons\\safeguarding_medallion_button.png");
    ImageIcon sentinelsBarricade_button = new ImageIcon(root + "Buttons\\defense_buttons\\sentinels_barricade_button.png");
    ImageIcon shieldbearersPendant_button = new ImageIcon(root + "Buttons\\defense_buttons\\shieldbearers_pendant_button.png");
    ImageIcon wardingRing_button = new ImageIcon(root + "Buttons\\defense_buttons\\warding_ring_button.png");
    ImageIcon buyButton_button = new ImageIcon(root + "Buttons\\defense_buttons\\buyButton.png");

    String aegisSteel_pic_url = root + "Buttons\\defense_buttons\\aegis_steel_button.png";
    String armorersGemstone_pic_url = root + "Buttons\\defense_buttons\\armorers_gemstone_button.png";
    String barrierBracelet_pic_url = root + "Buttons\\defense_buttons\\barrier_bracelet_button.png";
    String defendersTalisman_pic_url = root + "Buttons\\defense_buttons\\defenders_talisman_button.png";
    String guardiansAmulet_pic_url = root + "Buttons\\defense_buttons\\guardians_amulet_button.png";
    String protectionCharm_pic_url = root + "Buttons\\defense_buttons\\protection_charm_button.png";
    String safeguardingMedallion_pic_url = root + "Buttons\\defense_buttons\\safeguarding_medallion_button.png";
    String sentinelsBarricade_pic_url = root + "Buttons\\defense_buttons\\sentinels_barricade_button.png";
    String shieldbearersPendant_pic_url = root + "Buttons\\defense_buttons\\shieldbearers_pendant_button.png";
    String wardingRing_pic_url = root + "Buttons\\defense_buttons\\warding_ring_button.png";

    JPanel itemSide, processSide;
    JLabel itemSide_label, itemShow_label, itemName_label, itemDesc_label, itemGold_label, goldIcon_label, goldAmount_label,goldAmount2_label, itemGoldIcon_label, itemGold2_label, itemName2_label, itemDesc2_label;
    JButton aegisSteel, armorersGemstone, barrierBracelet, defendersTalisman, guardiansAmulet;
    JButton protectionCharm, safeguardingMedallion, sentinelsBarricade, shieldbearersPendant, wardingRing;
    JButton buyButton, exitButton;
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

    Merchant(){
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
        ImageIcon bg_pic = new ImageIcon(root + "Backgrounds\\Merchant_bg.jpg");
        JLabel bg = new JLabel();
        bg.setIcon(bg_pic);
        bg.setBounds(0,0, width, height);
        bg.setOpaque(false);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (dim.width - getWidth()) / 2;

        setLocation(x, 0);

        PlayerAmount();

        DefenseItems();

        BuyProcess();


        //add to frame
        this.add(exitButton);
        this.add(itemDesc2_label);
        this.add(itemName2_label);
        this.add(goldAmount2_label);
        this.add(goldAmount_label);
        this.add(goldIcon_label);
        this.add(buyButton);
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
    int player_gold_amount;

    public void PlayerAmount(){
        player_data = data_table(account_loggedin,player_table_columns, "PLAYERS", "ACCOUNT_ID");
        currency_id = player_data[7];

        currency_data = data_jointable(currency_id, currency_table_columns, "PLAYERS", "CURRENCY","CURRENCY_ID","");
        player_gold_amount = Integer.parseInt(currency_data[1]);
        goldIcon_label = new JLabel();
        goldAmount_label = new JLabel();

        goldIcon_label.setBounds(550,0,45,45);
        goldIcon_label.setIcon(goldIcon);
        goldIcon_label.setOpaque(false);

        ImageIcon playerGold = new ImageIcon(root + "Cities\\Valoria\\Merchant\\owned_contain.png");
        goldAmount_label = new JLabel();
        goldAmount_label.setBounds(600,0,150,45);
        goldAmount_label.setIcon(playerGold);
        goldAmount_label.setText(currency_data[1]);

        //label for player gold
        goldAmount2_label = new JLabel();
        goldAmount2_label.setBounds(615,0,150,45);
        goldAmount2_label.setText(currency_data[1]);

    }
    public String[] data_table(String url){
        String[] table_columns = {"ITEM_ID", "ITEM_NAME", "ITEM_SKILL", "ITEM_DESC","GOLD_AMOUNT","URL"};
        String[] data_from_table = new String[6];
        for (int i = 0; i < table_columns.length; i++)
        {
            data_from_table[i] = readtable.readValue("ITEM","URL", url, table_columns[i]);
        }
        return data_from_table;
    }

    String[] imageUrlArray = {
            root + "Cities\\Valoria\\Merchant\\Aegis Steel.png",
            root + "Cities\\Valoria\\Merchant\\Armorer's Gemstone.png",
            root + "Cities\\Valoria\\Merchant\\Barrier Bracelet.png",
            root + "Cities\\Valoria\\Merchant\\Defender's Talisman.png",
            root + "Cities\\Valoria\\Merchant\\Guardian's Amulet.png",
            root + "Cities\\Valoria\\Merchant\\Protection Charm.png",
            root + "Cities\\Valoria\\Merchant\\Safeguarding Medallion.png",
            root + "Cities\\Valoria\\Merchant\\Sentinel's Barricade.png",
            root + "Cities\\Valoria\\Merchant\\Shieldbearer's Pendant.png",
            root + "Cities\\Valoria\\Merchant\\Warding Ring.png",

    };

    String[] support_buttons_array = {aegisSteel_pic_url, armorersGemstone_pic_url,barrierBracelet_pic_url, defendersTalisman_pic_url,
            guardiansAmulet_pic_url, protectionCharm_pic_url, safeguardingMedallion_pic_url, sentinelsBarricade_pic_url,
            shieldbearersPendant_pic_url, wardingRing_pic_url};
    String[] inventory_data = readjointable.readjoinValues(account_loggedin, "URL");
    String[] support_buttonsname_array = {"Aegis Steel", "Armorer's Gemstone","Barrier Bracelet","Defender's Talisman",
            "Guardian's Amulet", "Protection Charm","Safeguarding Medallion","Sentinel's Barricade","Shieldbearer's Pendant",
            "Warding Ring"};
    JButton[] supportButtons = new JButton[10];


    public void DefenseItems(){
        aegisSteel = new JButton(aegisSteel_button);
        armorersGemstone = new JButton(armorersGemstone_button);
        barrierBracelet = new JButton(barrierBracelet_button);
        defendersTalisman = new JButton(defendersTalisman_button);
        guardiansAmulet = new JButton(guardiansAmulet_button);
        protectionCharm = new JButton(protectionCharm_button);
        safeguardingMedallion = new JButton(safeguardingMedallion_button);
        sentinelsBarricade = new JButton(sentinelsBarricade_button);
        shieldbearersPendant = new JButton(shieldbearersPendant_button);
        wardingRing = new JButton(wardingRing_button);

        String support_name, support_desc,support_gold, support_herb;
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
            support_herb = support_data[8];
            ImageIcon buttonIcon = new ImageIcon(support_buttons_array[i]);

            supportButtons[i] = new JButton(buttonIcon);
            supportButtons[i].setContentAreaFilled(false);
            supportButtons[i].setBorderPainted(false);
            supportButtons[i].setFocusable(false);
            switch (support_buttonsname_array[i]) {
                case "Aegis Steel":
                    supportButtons[i].setBounds(10, 10, 140, 140);
                    supportButtons[i].addActionListener(this);
                    // Add other properties or listeners if needed
                    break;
                case "Armorer's Gemstone":
                    supportButtons[i].setBounds(150, 10, 140, 140);
                    supportButtons[i].addActionListener(this);
                    break;
                case "Barrier Bracelet":
                    supportButtons[i].setBounds(10, 145, 140, 140);
                    supportButtons[i].addActionListener(this);
                    break;
                case "Defenders Talisman":
                    supportButtons[i].setBounds(150, 145, 140, 140);
                    supportButtons[i].addActionListener(this);
                    break;
                case "Guardian's Amulet":
                    supportButtons[i].setBounds(10, 285, 140, 140);
                    supportButtons[i].addActionListener(this);
                    break;
                case "Protection Charm":
                    supportButtons[i].setBounds(150, 285, 140, 140);
                    supportButtons[i].addActionListener(this);
                    break;
                case "Safeguarding Medallion":
                    supportButtons[i].setBounds(10, 425, 140, 140);
                    supportButtons[i].addActionListener(this);
                    break;
                case "Sentinel's Barricade":
                    supportButtons[i].setBounds(150, 425, 140, 140);
                    supportButtons[i].addActionListener(this);
                    break;
                case "Shieldbearer's Pendant":
                    supportButtons[i].setBounds(5, 560, 140, 140);  // Set bounds for Shieldbearer's Pendant
                    supportButtons[i].addActionListener(this);
                    break;
                case "Warding Ring":
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
            supportButtons[i].addMouseListener(createMouseListener(support_name,support_desc,support_gold, imageUrlArray[i]));
            add(supportButtons[i]);
            itemSide.add(supportButtons[i]);
        }
    }
    public void BuyProcess(){
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
        ImageIcon buyName = new ImageIcon(root + "Cities\\Valoria\\Merchant\\buyName.png");
        itemName_label = new JLabel();
        itemName_label.setIcon(buyName);
        itemName_label.setOpaque(false);
        itemName_label.setBounds(550, 65, 450, 100);

        //label for the item name
        itemName2_label = new JLabel();
        itemName2_label.setBounds(550,65,450,100);
        itemName2_label.setForeground(Color.BLACK);
        itemName2_label.setFont(new Font("Times New Roman", Font.BOLD, 20));
        itemName2_label.setHorizontalAlignment(JLabel.CENTER);

        //label for the item desc
        ImageIcon shieldDesc = new ImageIcon(root + "Cities\\Valoria\\Merchant\\contain.png");
        itemDesc_label = new JLabel();
        itemDesc_label.setIcon(shieldDesc);
        itemDesc_label.setOpaque(false);
        itemDesc_label.setBounds(550, 200, 450, 300);

        //label for the item desc
        itemDesc2_label = new JLabel();
        itemDesc2_label.setBounds(590,280,380,300);
        itemDesc2_label.setForeground(Color.BLACK);
        itemDesc2_label.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        itemDesc2_label.setHorizontalAlignment(JLabel.LEFT);
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


        //button for forging
        buyButton = new JButton(buyButton_button);
        buyButton.setBorderPainted(false);
        buyButton.setFocusable(false);
        buyButton.setContentAreaFilled(false);
        buyButton.setBounds(660, 620, 220, 100);
        buyButton.setVisible(false);
        buyButton.setEnabled(false);
        buyButton.addActionListener(this);

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
    int gold_amt;
    String[] weapon_data = new String[7];

    @Override
    public void actionPerformed(ActionEvent e) {
        gold_amt = 0;


        Object[][] weapons = {
                {supportButtons[0], "aegis steel", aegisSteel_pic_url},
                {supportButtons[1], "armorers gemstone", armorersGemstone_pic_url},
                {supportButtons[2], "barrier bracelet", barrierBracelet_pic_url},
                {supportButtons[3], "defenders talisman", defendersTalisman_pic_url},
                {supportButtons[4], "guardians amulet", guardiansAmulet_pic_url},
                {supportButtons[5], "protection charm", protectionCharm_pic_url},
                {supportButtons[6], "safeguarding medallion", safeguardingMedallion_pic_url},
                {supportButtons[7], "sentinels barricade", sentinelsBarricade_pic_url},
                {supportButtons[8], "shield bearers pendant", shieldbearersPendant_pic_url},
                {supportButtons[9], "voidbringer staff",wardingRing_pic_url}
        };

        for (Object[] weapon : weapons) {
            if (e.getSource() == weapon[0]) {
                System.out.println(weapon[1] + " clicked!");
                weapon_clicked = (String) weapon[1];
                weapon_clicked_url = (String) weapon[2];
                weapon_data = data_table((String) weapon[2]);

                gold_amt = Integer.parseInt(weapon_data[4]);
                break; // Exit the loop once a match is found
            }
        }
        if (e.getSource() != buyButton && e.getSource() == exitButton) {
            SwingUtilities.invokeLater(() -> {
                Merchant.this.dispose();
            });
        }
        if (e.getSource() != buyButton) {
            if (!buttonClicked) {
                // Check if player has enough gold and biscuit
                if ((player_gold_amount >= gold_amt)) {
                    buyButton.setVisible(true);
                    buyButton.setEnabled(true);
                } else {
                    buyButton.setVisible(false);
                    buyButton.setEnabled(false);
                }
                buttonClicked = true;
            } else {
                buyButton.setVisible(false);
                buyButton.setEnabled(false);
                buttonClicked = false;
            }
        }else{
//            String[] table_columns = {"ITEM_ID", "ITEM_NAME", "ITEM_SKILL", "ITEM_DESC","GOLD_AMOUNT","URL"};
            if (weapon_data[4] != null) {
                gold_amt = Integer.parseInt(weapon_data[4]);
                //print debugger
//                System.out.println("gold_amt: " + gold_amt);
//                System.out.println("player_gold_amount: " + player_gold_amount);
//
//
//                System.out.println("support: " + weapon_clicked);
//                System.out.println("url: " + weapon_clicked_url);
//                System.out.println("gold amount: " + gold_amt);
//
//                // Print values to understand why they are null
//                System.out.println("support_data[5]new: " + weapon_data[5]);

                // update database here
                currency_data = data_jointable(currency_id, currency_table_columns, "PLAYERS", "CURRENCY", "CURRENCY_ID", "");
                if (player_gold_amount >= gold_amt) {
                    int gold_amt_toupdate = player_gold_amount - gold_amt;

                    System.out.println("Before updating database: gold_amt_toupdate = " + gold_amt_toupdate);

                    tableupdate.updateTable("CURRENCY", "GOLD_COUNT", "CURRENCY_ID", currency_id, gold_amt_toupdate);
                    tableupdate.updateTable("PLAYERS", "SHIELDS_BOUGHT", "CURRENCY_ID", currency_id, Integer.parseInt(player_data[8]) + 1);

                    String[] columns_of_inventory = {"ACCOUNT_ID", "ITEM_ID", "ITEMTYPE"};
                    String[] column_to_insert = {account_loggedin, weapon_data[0], "Artifact"};
                    tableinsert.insertIntoTable("inventory", columns_of_inventory, column_to_insert);

                    System.out.println("After updating database: gold_amt_toupdate = " + gold_amt_toupdate);

                    PlayerAmount();
                    DefenseItems();
                    SwingUtilities.invokeLater(()->new GameNotif("Successful Transaction!"));
                    SwingUtilities.invokeLater(Merchant.this::dispose);
                }
                else {
                    System.out.println("Not enough gold!");
                }

            } else {
                // Handle the case where support_data[4] or support_data[6] is null
                System.out.println("Error: support_data[4] or support_data[6] is null");
            }
        }
    }
    private MouseAdapter createMouseListener(String itemName, String itemDesc,
                                             String goldAmount, String img){
        return new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                if(!buttonClicked) {
                    itemName2_label.setText(itemName);
                    itemDesc2_label.setText("<html>" + itemDesc + "</html>");
                    itemGold_label.setText(goldAmount);

                    imageShow = new ImageIcon(img);
                    itemShow_label.setIcon(imageShow);
                }
            }
            @Override
            public void mouseExited(MouseEvent e){
                if(!buttonClicked) {
                    itemName2_label.setText("");
                    itemDesc2_label.setText("");
                    itemGold_label.setText("");
                    itemShow_label.setIcon(null);
                }
            }
        };
    }
}
