import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import Database.*;

public class PetSanctuary extends JFrame implements ActionListener{
    read_table readtable = new read_table();
    loggedin_identifier logidentifier = new loggedin_identifier();

    read_bossdroptable readt = new read_bossdroptable();
    TableUpdater tableupdate = new TableUpdater();
    TableInserter tableinsert = new TableInserter();
    read_jointable readjointable = new read_jointable();
    String account_loggedin = logidentifier.readValue("LOGGEDIN_IDENTIFIER","ACCOUNT_ID");

    String[] currency_table_columns = {"CURRENCY.CURRENCY_ID","GOLD_COUNT","IRON_COUNT","HERB_COUNT","BISCUIT_COUNT","DIAMOND_COUNT"};
    String[] player_table_columns = {"ACCOUNT_ID","WEAPONS_FORGED","PLAYER_HP","PLAYER_DMG","PET_OWNED","BOSSES_KILLED","PETS_TAMED","PLAYERS.CURRENCY_ID","PLAYERS.PETS_TAMED"};
    //    String[] pet_table_columns = {"PET_ID","PET_NAME","PET_DESC","PET_SKILL","PET_SKILL_DESC","PET_EFFECT","GOLD_AMOUNT","BISCUIT_AMOUNT","URL"};
    String[] pet_table_columns = {"ITEM_ID","ITEM_NAME","ITEM_SKILL","ITEM_DESC","ITEM_EFFECT","DAMAGE","GOLD_AMOUNT","IRON_AMOUNT","HERB_AMOUNT","ITEM_TYPE","URL","ITEM_SKILL_DESC","BISCUIT_COUNT"};

    String root = "C:\\Users\\User\\Reforge2\\src\\assets\\";
    ImageIcon imageShow;
    private boolean buttonClicked = false;

    String aquaSerpent_url = root + "Buttons\\pet_buttons\\aquaSerpentButton.png";
    String celestialDragon_url = root + "Buttons\\pet_buttons\\celestialDragonButton.png";
    String earthGolem_url = root + "Buttons\\pet_buttons\\earthGolemButton.png";
    String mysticUnicorn_url = root + "Buttons\\pet_buttons\\mysticUnicornButton.png";
    String flameheartPhoenix_url = root + "Buttons\\pet_buttons\\phoenixButton.png";
    String shadowfoxFamiliar_url = root + "Buttons\\pet_buttons\\shadowfoxButton.png";
    String venomousSpiderling_url = root + "Buttons\\pet_buttons\\spiderlingButton.png";
    String zephyrOwl_url = root + "Buttons\\pet_buttons\\zephyrOwlButton.png";


    String aquaSerpent_url2 = root + "Cities\\Valoria\\PetShop\\Aqua Serpent Companion.png";
    String celestialDragon_url2 = root + "Cities\\Valoria\\PetShop\\Celestial Dragon Kin.png";
    String earthGolem_url2 = root + "Cities\\Valoria\\PetShop\\Earth Golem Guardian.png";
    String mysticUnicorn_url2 = root + "Cities\\Valoria\\PetShop\\Mystic Unicorn Steed.png";
    String flameheartPhoenix_url2 = root + "Cities\\Valoria\\PetShop\\Flameheart Phoenix.png";
    String shadowfoxFamiliar_url2 = root + "Cities\\Valoria\\PetShop\\Shadowfox Familiar.png";
    String venomousSpiderling_url2 = root + "Cities\\Valoria\\PetShop\\Venomous Spiderling.png";
    String zephyrOwl_url2 = root + "Cities\\Valoria\\PetShop\\Zephyr Owl Companion.png";

    ImageIcon exit = new ImageIcon(root + "Buttons\\misc\\exitButton.png");
    ImageIcon goldIcon = new ImageIcon(root + "Currency\\Gold Icon.png");
    ImageIcon biscuitIcon = new ImageIcon(root + "Currency\\Biscuit Icon.png");
    ImageIcon tameButton_button = new ImageIcon(root + "Buttons\\pet_buttons\\tameButton.png");
    ImageIcon aquaSerpent_button = new ImageIcon(aquaSerpent_url);
    ImageIcon celestialDragon_button = new ImageIcon(celestialDragon_url);
    ImageIcon earthGolem_button = new ImageIcon(earthGolem_url);
    ImageIcon mysticUnicorn_button = new ImageIcon(mysticUnicorn_url);
    ImageIcon flameheartPheonix_button = new ImageIcon(flameheartPhoenix_url);
    ImageIcon shadowfoxFamiliar_button = new ImageIcon(shadowfoxFamiliar_url);
    ImageIcon venomousSpiderling_button = new ImageIcon(venomousSpiderling_url);
    ImageIcon zephyrOwl_button = new ImageIcon(zephyrOwl_url);
    JPanel itemSide, processSide;
    JLabel itemSide_label, itemShow_label, itemName_label, itemDesc_label, itemGold_label, itemGoldIcon_label,
            itemBiscuit_label, itemBiscuitIcon_label, biscuitIcon_label, biscuitAmount_label, goldIcon_label, goldAmount_label, itemName2_label, itemDesc2_label,

    itemGold2_label, itemBiscuit2_label, goldAmount2_label, biscuitAmount2_label;
    JButton aquaSerpent, celestialDragon, earthGolem, mysticUnicorn, flameheartPheonix, shadowFox, venomousSpiderling, zephyrOwl;
    JButton tameButton, exitButton;

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

    PetSanctuary(){
        int width = 1520;
        int height = 820;

        //frame layout
        this.setVisible(true);
        this.setSize(width, height);
        this.setTitle("Reforge: Rise of the Seventeen Cities");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setResizable(false);
        this.setLayout(new BorderLayout());

        //background
        ImageIcon bg_pic = new ImageIcon(root + "Backgrounds\\PetStore_bg.jpg");
        JLabel bg = new JLabel();
        bg.setIcon(bg_pic);
        bg.setBounds(0,0,width,height);
        bg.setOpaque(false);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (dim.width - getWidth()) / 2;

        setLocation(x, 0);

        PlayerAmount();

        Pets();

        TameProcess();

        //add to frame
        this.add(exitButton);
        this.add(itemDesc2_label);
        this.add(itemName2_label);
        this.add(biscuitAmount2_label);
        this.add(biscuitAmount_label);
        this.add(biscuitIcon_label);
        this.add(goldAmount2_label);
        this.add(goldAmount_label);
        this.add(goldIcon_label);
        this.add(tameButton);
        this.add(itemGoldIcon_label);
        this.add(itemGold2_label);
        this.add(itemGold_label);
        this.add(itemBiscuitIcon_label);
        this.add(itemBiscuit2_label);
        this.add(itemBiscuit_label);
        this.add(itemDesc_label);
        this.add(itemName_label);
        this.add(processSide);
        this.add(itemSide);
        this.add(bg);
    }
    String[] player_data, currency_data = new String[6];
    String currency_id;
    int player_gold_amount, player_biscuit_amount;

    public void PlayerAmount(){
        player_data = data_table(account_loggedin,player_table_columns, "PLAYERS", "ACCOUNT_ID");
        currency_id = player_data[7];

        currency_data = data_jointable(currency_id, currency_table_columns, "PLAYERS", "CURRENCY","CURRENCY_ID","");
        player_gold_amount = Integer.parseInt(currency_data[1]);
        player_biscuit_amount = Integer.parseInt(currency_data[4]);

        //label for player gold icon
        goldIcon_label = new JLabel();
        goldIcon_label.setBounds(550,0,45,45);
        goldIcon_label.setIcon(goldIcon);
        goldIcon_label.setOpaque(false);

        //label for player gold bg
        ImageIcon playerGold = new ImageIcon(root + "Cities\\Valoria\\PetShop\\owned_contain.png");
        goldAmount_label = new JLabel();
        goldAmount_label.setBounds(600,0,150,45);
        goldAmount_label.setIcon(playerGold);
        goldAmount_label.setText(currency_data[1]);

        //label for player gold
        goldAmount2_label = new JLabel();
        goldAmount2_label.setBounds(615,0,150,45);
        goldAmount2_label.setText(currency_data[1]);


        //label for player biscuit icon
        biscuitIcon_label = new JLabel();
        biscuitIcon_label.setBounds(800,0,45,45);
        biscuitIcon_label.setIcon(biscuitIcon);
        biscuitIcon_label.setOpaque(false);


        //label for player biscuit bg
        ImageIcon playerBiscuit = new ImageIcon(root + "Cities\\Valoria\\PetShop\\owned_contain.png");
        biscuitAmount_label = new JLabel();
        biscuitAmount_label.setBounds(850,0,150,45);
        biscuitAmount_label.setIcon(playerBiscuit);

        //label for player biscuit
        biscuitAmount2_label = new JLabel();
        biscuitAmount2_label.setBounds(865,0,150,45);
        biscuitAmount2_label.setText(currency_data[4]);

    }

    public String[] data_table(String url){
        String[] table_columns = {"ITEM_ID", "ITEM_NAME", "ITEM_SKILL", "ITEM_DESC","ITEM_SKILL_DESC","GOLD_AMOUNT","BISCUIT_COUNT","ITEM_EFFECT"};

        String[] data_from_table = new String[8];
        for (int i = 0; i < table_columns.length; i++)
        {
            data_from_table[i] = readtable.readValue("ITEM","URL",url,table_columns[i]);
        }
        return data_from_table;
    }
    String[] data = new String[7];
    String[] imageUrlArray = {
            aquaSerpent_url2,
            celestialDragon_url2,
            earthGolem_url2,
            mysticUnicorn_url2,
            flameheartPhoenix_url2,
            shadowfoxFamiliar_url2,
            venomousSpiderling_url2,
            zephyrOwl_url2

    };

    String[] support_buttons2_array = {aquaSerpent_url, celestialDragon_url,earthGolem_url, mysticUnicorn_url,
            flameheartPhoenix_url, shadowfoxFamiliar_url, venomousSpiderling_url, zephyrOwl_url};
    String[] support_buttons_array = {aquaSerpent_url2, celestialDragon_url2,earthGolem_url2, mysticUnicorn_url2,
            flameheartPhoenix_url2, shadowfoxFamiliar_url2, venomousSpiderling_url2, zephyrOwl_url2};
    String[] inventory_data = readjointable.readjoinValues(account_loggedin, "URL");
    String[] support_buttonsname_array = {"Aqua Serpent Companion", "Celestial Dragon Kin","Earth Golem Guardian","Mystic Unicorn Steed","Flameheart Phoenix",
            "Shadowfox Familiar", "Venomous Spiderling","Zephyr Owl Companion"};
    JButton[] supportButtons = new JButton[10];
    public void Pets(){
        String[] data = new String[7];
        aquaSerpent = new JButton(aquaSerpent_button);
        celestialDragon = new JButton(celestialDragon_button);
        earthGolem = new JButton(earthGolem_button);
        mysticUnicorn = new JButton(mysticUnicorn_button);
        flameheartPheonix = new JButton(flameheartPheonix_button);
        shadowFox = new JButton(shadowfoxFamiliar_button);
        venomousSpiderling = new JButton(venomousSpiderling_button);
        zephyrOwl = new JButton(zephyrOwl_button);

        String support_name, support_desc,support_gold, support_biscuit;

        //panel setup
        ImageIcon itemSide_bg = new ImageIcon(root + "itemSide.png");
        itemSide = new JPanel();
        itemSide.setLayout(new BorderLayout());
        itemSide.setBounds(1030,85, 300,720);
        itemSide.setOpaque(false);

        //label for buttons setup
        itemSide_label = new JLabel();
        itemSide_label.setIcon(itemSide_bg);
        itemSide_label.setBounds(0,0,300, 720);
        //String[] pet_table_columns = {"ITEM_ID","ITEM_NAME","ITEM_SKILL","ITEM_DESC","ITEM_EFFECT","DAMAGE","GOLD_AMOUNT",
        // "IRON_AMOUNT","HERB_AMOUNT","ITEM_TYPE","URL","ITEM_SKILL_DESC","BISCUIT_COUNT"};
        //images for buttons
        for (int i = 0; i < Math.min(support_buttons_array.length, supportButtons.length); i++) {
            String[] support_data = data_table(support_buttons_array[i],pet_table_columns,"ITEM","URL");
            support_name = support_data[1];
            support_desc = support_data[3];
            support_gold = support_data[6];
            support_biscuit = support_data[12];
            ImageIcon buttonIcon = new ImageIcon(support_buttons2_array[i]);
            GridLayout itemSideLayout = new GridLayout(5, 2, 10, 10);
            itemSide.setLayout(itemSideLayout);

            supportButtons[i] = new JButton(buttonIcon);
            supportButtons[i].setContentAreaFilled(false);
            supportButtons[i].setBorderPainted(false);
            supportButtons[i].setFocusable(false);

//            aquaSerpent = new JButton(aquaSerpent_button);
//            celestialDragon = new JButton(celestialDragon_button);
//            earthGolem = new JButton(earthGolem_button);
//            mysticUnicorn = new JButton(mysticUnicorn_button);
//            flameheartPheonix = new JButton(flameheartPheonix_button);
//            shadowFox = new JButton(shadowfoxFamiliar_button);
//            venomousSpiderling = new JButton(venomousSpiderling_button);
//            zephyrOwl = new JButton(zephyrOwl_button);

//    String[] support_buttonsname_array = {"Aqua Serpent Companion", "Celestial Dragon Kin","Earth Golem Guardian","Mystic Unicorn Steed","Flameheart Phoenix",
//            "Shadowfox Familiar", "Venomous Spiderling","Zephyr Owl Companion"};
            switch (support_buttonsname_array[i]) {
                case "Aqua Serpent Companion":
                    supportButtons[i].setBounds(10, 10, 140, 140);
                    supportButtons[i].addActionListener(this);
                    // Add other properties or listeners if needed
                    break;
                case "Celestial Dragon Kin":
                    supportButtons[i].setBounds(150, 10, 140, 140);
                    supportButtons[i].addActionListener(this);
                    break;
                case "Earth Golem Guardian":
                    supportButtons[i].setBounds(10, 145, 140, 140);
                    supportButtons[i].addActionListener(this);
                    break;
                case "Mystic Unicorn Steed":
                    supportButtons[i].setBounds(150, 145, 140, 140);
                    supportButtons[i].addActionListener(this);
                    break;
                case "Flameheart Phoenix":
                    supportButtons[i].setBounds(10, 285, 140, 140);
                    supportButtons[i].addActionListener(this);
                    break;
                case "Shadowfox Familiar":
                    supportButtons[i].setBounds(150, 285, 140, 140);
                    supportButtons[i].addActionListener(this);
                    break;
                case "Venomous Spiderling":
                    supportButtons[i].setBounds(10, 425, 140, 140);
                    supportButtons[i].addActionListener(this);
                    break;
                case "Zephyr Owl Companion":
                    supportButtons[i].setBounds(150, 425, 140, 140);
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
            supportButtons[i].addMouseListener(createMouseListener(support_name,support_desc,support_gold, support_biscuit, imageUrlArray[i]));
            add(supportButtons[i]);
            itemSide.add(supportButtons[i]);
        }
    }


    public void TameProcess(){
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
        ImageIcon petName = new ImageIcon(root + "Cities\\Valoria\\PetShop\\petName.png");
        itemName_label = new JLabel();
        itemName_label.setIcon(petName);
        itemName_label.setOpaque(false);
        itemName_label.setBounds(550, 65, 450, 100);

        //label for the item name
        itemName2_label = new JLabel();
        itemName2_label.setBounds(550,65,450,100);
        itemName2_label.setForeground(Color.BLACK);
        itemName2_label.setFont(new Font("Times New Roman", Font.BOLD, 20));
        itemName2_label.setHorizontalAlignment(JLabel.CENTER);

        //label for the item desc bg
        ImageIcon weaponDesc = new ImageIcon(root + "Cities\\Valoria\\PetShop\\contain.png");
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
        ImageIcon goldAmount = new ImageIcon(root + "Cities\\Valoria\\PetShop\\amount_contain.png");
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
        itemGold2_label.setBounds(619,527,175,50);

        //label for item biscuit amount bg
        ImageIcon biscuitAmount = new ImageIcon(root + "Cities\\Valoria\\PetShop\\amount_contain.png");
        itemBiscuit_label = new JLabel();
        itemBiscuit_label.setIcon(biscuitAmount);
        itemBiscuit_label.setBounds(825, 530, 175, 50);

        //label for item biscuit icon
        itemBiscuitIcon_label = new JLabel();
        itemBiscuitIcon_label.setIcon(biscuitIcon);
        itemBiscuitIcon_label.setBounds(780,530,45,50);
        itemBiscuitIcon_label.setOpaque(false);

        //lable for item biscuit amount
        itemBiscuit2_label = new JLabel();
        itemBiscuit2_label.setBounds(840,527,175,50);
        //button for brewing
        tameButton = new JButton(tameButton_button);
        tameButton.setBorderPainted(false);
        tameButton.setFocusable(false);
        tameButton.setContentAreaFilled(false);
        tameButton.setBounds(660, 600, 220, 100);
        tameButton.setVisible(false);
        tameButton.setEnabled(false);
        tameButton.addActionListener(this);

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
    String pet_clicked = "";
    String pet_clicked_url = "";
    int gold_amt, biscuit_amt;
    String[] pet_data = new String[7];
    @Override
    public void actionPerformed(ActionEvent e) {
//        support_data = new String[7]; // Reset support_data with a new array at the beginning
        gold_amt = 0;
        biscuit_amt = 0;

        Object[][] weapons = {
                {supportButtons[0], "aqua Serpent", aquaSerpent_url2},
                {supportButtons[1], "celestial dragon", celestialDragon_url2},
                {supportButtons[2], "earth golem", earthGolem_url2},
                {supportButtons[3], "mystic unicorn", mysticUnicorn_url2},
                {supportButtons[4], "flameheart phoenix", flameheartPhoenix_url2},
                {supportButtons[5], "shadow fox", shadowfoxFamiliar_url2},
                {supportButtons[6], "venomous spiderling", venomousSpiderling_url2},
                {supportButtons[7], "zephyrowl", zephyrOwl_url2},
        };


        for (Object[] weapon : weapons) {
            if (e.getSource() == weapon[0]) {
                System.out.println(weapon[1] + " clicked!");
                pet_clicked = (String) weapon[1];
                pet_clicked_url = (String) weapon[2];
                pet_data = data_table((String) weapon[2]);

                gold_amt = Integer.parseInt(pet_data[5]);
                biscuit_amt = Integer.parseInt(pet_data[6]);
                break; // Exit the loop once a match is found
            }
        }
        if (e.getSource() != tameButton && e.getSource() == exitButton) {
            SwingUtilities.invokeLater(() -> {
                PetSanctuary.this.dispose();
            });
        }
        if (e.getSource() != tameButton) {
            if (!buttonClicked) {
                // Check if player has enough gold and biscuit
                if ((player_gold_amount >= gold_amt) && (player_biscuit_amount >= biscuit_amt)) {
                    tameButton.setVisible(true);
                    tameButton.setEnabled(true);
                } else {
                    tameButton.setVisible(false);
                    tameButton.setEnabled(false);
                }
                buttonClicked = true;
            } else {
                tameButton.setVisible(false);
                tameButton.setEnabled(false);
                buttonClicked = false;
            }
        }else{
//         String[] table_columns = {"ITEM_ID", "ITEM_NAME", "ITEM_EFFECT", "ITEM_DESC","GOLD_AMOUNT","URL","HERB_AMOUNT"};
            if (pet_data[5] != null && pet_data[6] != null) {
                gold_amt = Integer.parseInt(pet_data[5]);
                biscuit_amt = Integer.parseInt(pet_data[6]);
                //print debugger
//                System.out.println("gold_amt: " + gold_amt);
//                System.out.println("player_gold_amount: " + player_gold_amount);
//
//
//                System.out.println("support: " + pet_clicked);
//                System.out.println("url: " + pet_clicked_url);
//                System.out.println("gold amount: " + gold_amt);
//                System.out.println("biscuit amount: " + biscuit_amt);
//
//                // Print values to understand why they are null
//                System.out.println("support_data[5]new: " + pet_data[5]);
//                System.out.println("support_data[6]: " + pet_data[6]);

                // update database here
//                currency_data = data_jointable(currency_id, currency_table_columns, "PLAYERS", "CURRENCY", "CURRENCY_ID", "");

                if ((player_gold_amount >= gold_amt) && (player_biscuit_amount >= biscuit_amt)) {

                    int gold_amt_toupdate = player_gold_amount - gold_amt;
                    int biscuit_amt_toupdate = player_biscuit_amount - biscuit_amt;

                    System.out.println("Before updating database: gold_amt_toupdate = " + gold_amt_toupdate + ", biscuit_amt_toupdate = " + biscuit_amt_toupdate);

                    System.out.println(currency_id + " is the id.");
                    tableupdate.updateTable("CURRENCY", "GOLD_COUNT", "CURRENCY_ID", currency_id, gold_amt_toupdate);
                    tableupdate.updateTable("CURRENCY", "BISCUIT_COUNT", "CURRENCY_ID", currency_id, biscuit_amt_toupdate);
                    tableupdate.updateTable("PLAYERS", "PETS_TAMED", "CURRENCY_ID", currency_id, Integer.parseInt(player_data[8]) + 1);

                    String[] columns_of_inventory = {"ACCOUNT_ID", "ITEM_ID", "ITEMTYPE"};
                    String[] column_to_insert = {account_loggedin, pet_data[0], "Pet"};
                    tableinsert.insertIntoTable("inventory", columns_of_inventory, column_to_insert);

                    System.out.println("After updating database: gold_amt_toupdate = " + gold_amt_toupdate + ", biscuit_amt_toupdate = " + biscuit_amt_toupdate);

                    PlayerAmount();
                    Pets();
                    SwingUtilities.invokeLater(()->new GameNotif("Successful Transaction!"));
                    SwingUtilities.invokeLater(PetSanctuary.this::dispose);
                }else{
                    System.out.println("Not enough gold and biscuit");
                }
            } else {
                // Handle the case where support_data[4] or support_data[6] is null
                System.out.println("Error: support_data[4] or support_data[6] is null");
            }
        }
    }
    private MouseAdapter createMouseListener(String itemName, String itemDesc,
                                             String goldAmount, String biscuitAmount, String img){
        return new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                if(!buttonClicked) {
                    itemName2_label.setText(itemName);
                    itemDesc2_label.setText("<html>" + itemDesc + "</html>");
                    itemGold2_label.setText(goldAmount);
                    itemBiscuit2_label.setText(biscuitAmount);
//                    goldAmount2_label.setText("200");
//                    biscuitAmount2_label.setText("200");

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
                    itemBiscuit2_label.setText("");

                    itemShow_label.setIcon(null);
                }
            }
        };
    }
}