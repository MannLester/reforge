import Database.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class Brewing extends JFrame implements ActionListener {
    read_table readtable = new read_table();
    loggedin_identifier logidentifier = new loggedin_identifier();

    read_bossdroptable readt = new read_bossdroptable();
    TableUpdater tableupdate = new TableUpdater();
    read_jointable readjointable = new read_jointable();
    TableInserter tableinsert = new TableInserter();


    JButton[] supportButtons = new JButton[5];

    String[] currency_table_columns = {"CURRENCY.CURRENCY_ID","GOLD_COUNT","IRON_COUNT","HERB_COUNT","BISCUIT_COUNT","DIAMOND_COUNT"};
    String[] player_table_columns = {"ACCOUNT_ID","WEAPONS_FORGED","PLAYER_HP","PLAYER_DMG","PET_OWNED","BOSSES_KILLED","PETS_TAMED","PLAYERS.CURRENCY_ID", "PLAYERS.POTIONS_BREWED"};
    String[] item_table_columns = {"ITEM_ID","ITEM_NAME","ITEM_SKILL","ITEM_DESC","ITEM_EFFECT","DAMAGE","GOLD_AMOUNT","IRON_AMOUNT","HERB_AMOUNT","ITEM_TYPE","URL"};

    String root = "C:\\Users\\User\\Reforge2\\src\\assets\\";
    ImageIcon imageShow;
    private boolean buttonClicked = false;
    ImageIcon exit = new ImageIcon(root + "Buttons\\misc\\exitButton.png");
    ImageIcon goldIcon = new ImageIcon(root + "Currency\\Gold Icon.png");
    ImageIcon herbIcon = new ImageIcon(root + "Currency\\Herb Icon.png");
    ImageIcon brewOfDecay_button = new ImageIcon(root + "Buttons\\support_buttons\\brew_of_decay_button.png");
    ImageIcon elixirOfResilience_button = new ImageIcon(root + "Buttons\\support_buttons\\elixir_of_resilience_button.png");
    ImageIcon potentFuryBrew_button = new ImageIcon(root + "Buttons\\support_buttons\\potent_fury_brew_button.png");
    ImageIcon potionOfRecovery_button = new ImageIcon(root + "Buttons\\support_buttons\\potion_of_recovery_button.png");
    ImageIcon weaknessPotion_button = new ImageIcon(root + "Buttons\\support_buttons\\weakness_potion_button.png");
    ImageIcon brewButton_button = new ImageIcon(root + "Buttons\\support_buttons\\brewButton.png");

    String brewOfDecay_pic_url = root + "Buttons\\support_buttons\\brew_of_decay_button.png";
    String elixirOfResilience_pic_url = root + "Buttons\\support_buttons\\elixir_of_resilience_button.png";
    String potentFuryBrew_pic_url = root + "Buttons\\support_buttons\\potent_fury_brew_button.png";
    String potionOfRecovery_pic_url = root + "Buttons\\support_buttons\\potion_of_recovery_button.png";
    String weaknessPotion_pic_url = root + "Buttons\\support_buttons\\weakness_potion_button.png";
    JPanel itemSide, processSide;
    JLabel itemSide_label, itemShow_label, itemName_label, itemDesc_label, itemGold_label, itemGoldIcon_label,
            itemHerb_label, itemHerbIcon_label, herbIcon_label, herbAmount_label, goldIcon_label, goldAmount_label, itemName2_label, itemDesc2_label;
    JButton brewOfDecay, elixirOfResilience, potentFuryBrew, potionOfRecovery, weaknessPotion;
    JButton brewButton, exitButton;


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
    Brewing(){

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
        ImageIcon bg_pic = new ImageIcon(root + "Backgrounds\\Brewing_bg.jpg");
        JLabel bg = new JLabel();
        bg.setIcon(bg_pic);
        bg.setBounds(0,0, width, height);
        bg.setOpaque(false);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (dim.width - getWidth()) / 2;

        setLocation(x, 0);

        PlayerAmount();

        SupportItems();

        BrewProcess();

        //add to frame
        this.add(exitButton);
        this.add(itemDesc2_label);
        this.add(itemName2_label);
        this.add(herbAmount_label);
        this.add(herbIcon_label);
        this.add(goldAmount_label);
        this.add(goldIcon_label);
        this.add(brewButton);
        this.add(itemGoldIcon_label);
        this.add(itemGold_label);
        this.add(itemHerbIcon_label);
        this.add(itemHerb_label);
        this.add(itemDesc_label);
        this.add(itemName_label);
        this.add(processSide);
        this.add(itemSide);
        this.add(bg);
    }
    String[] player_data, currency_data = new String[6];
    String currency_id;
    int player_gold_amount, player_herb_amount;
    String account_loggedin = logidentifier.readValue("LOGGEDIN_IDENTIFIER","ACCOUNT_ID");


    public void PlayerAmount(){
        player_data = data_table(account_loggedin,player_table_columns, "PLAYERS", "ACCOUNT_ID");
        currency_id = player_data[7];

        currency_data = data_jointable(currency_id, currency_table_columns, "PLAYERS", "CURRENCY","CURRENCY_ID","");
        player_gold_amount = Integer.parseInt(currency_data[1]);
        player_herb_amount = Integer.parseInt(currency_data[3]);
        goldIcon_label = new JLabel();
        goldAmount_label = new JLabel();
        herbIcon_label = new JLabel();
        herbAmount_label = new JLabel();

        goldIcon_label.setBounds(550,0,45,45);
        goldIcon_label.setIcon(goldIcon);
        goldIcon_label.setOpaque(false);

        ImageIcon playerGold = new ImageIcon(root + "Cities\\Valoria\\Brewing\\owned_contain.png");
        goldAmount_label.setBounds(600,0,150,45);
        goldAmount_label.setIcon(playerGold);
        goldAmount_label.setOpaque(false);
        goldAmount_label.setText(currency_data[1]);

        herbIcon_label.setBounds(800,0,45,45);
        herbIcon_label.setIcon(herbIcon);
        herbIcon_label.setOpaque(false);

        ImageIcon playerHerb = new ImageIcon(root + "Cities\\Valoria\\Brewing\\owned_contain.png");
        herbAmount_label.setBounds(850,0,150,45);
        herbAmount_label.setIcon(playerHerb);
        herbAmount_label.setOpaque(false);
        herbAmount_label.setText(currency_data[3]);

    }

    public String[] data_table(String url){
        String[] table_columns = {"ITEM_ID", "ITEM_NAME", "ITEM_EFFECT", "ITEM_DESC","GOLD_AMOUNT","URL","HERB_AMOUNT"};
        String[] data_from_table = new String[7];
        for (int i = 0; i < table_columns.length; i++)
        {
            data_from_table[i] = readtable.readValue("ITEM","URL", url, table_columns[i]);
        }
        return data_from_table;
    }
    String[] imageUrlArray = {
            root + "Cities\\Valoria\\Brewing\\Brew Of Decay.png",
            root + "Cities\\Valoria\\Brewing\\Elixir of Resilience.png",
            root + "Cities\\Valoria\\Brewing\\Potent Fury Brew.png",
            root + "Cities\\Valoria\\Brewing\\Potion of Recovery.png",
            root + "Cities\\Valoria\\Brewing\\Weakness Potion.png"
    };

    String[] support_buttons_array = {brewOfDecay_pic_url, elixirOfResilience_pic_url,potentFuryBrew_pic_url, potionOfRecovery_pic_url,
            weaknessPotion_pic_url};
    String[] data = readjointable.readjoinValues(account_loggedin, "URL");
    String[] support_buttonsname_array = {"Brew of Decay", "Elixir of Resilience","Potent Fury Brew","Potion of Recovery","Weakness Potion"};

    public void SupportItems(){

        String support_name, support_desc,support_gold, support_herb;
        ImageIcon itemSide_bg = new ImageIcon(root + "itemSide.png");
        itemSide = new JPanel();
        itemSide.setLayout(new BorderLayout());
        itemSide.setBounds(1030,100, 300,500);
        itemSide.setOpaque(false);

        //label for buttons setup
        itemSide_label = new JLabel();
        itemSide_label.setIcon(itemSide_bg);
        itemSide_label.setBounds(0,0,300, 720);

        GridLayout itemSideLayout = new GridLayout(3, 2, 10, 10);
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
                case "Brew of Decay":
                    supportButtons[i].setBounds(10, 10, 140, 140);
                    supportButtons[i].addActionListener(this);
                    break;
                case "Elixir of Resilience":
                    supportButtons[i].setBounds(150,10,140,140);
                    supportButtons[i].addActionListener(this);
                    break;
                case "Potent Fury Brew":
                    supportButtons[i].setBounds(10,145,140,140);
                    supportButtons[i].addActionListener(this);
                    break;
                case "Potion of Recovery":
                    supportButtons[i].setBounds(150,145,140,140);
                    supportButtons[i].addActionListener(this);
                    break;
                case "Weakness Potion":
                    supportButtons[i].setBounds(10,285,140,140);
                    supportButtons[i].addActionListener(this);
                    break;
                // Add more cases if needed for additional buttons
                default:
                    // Default position if the buttonName is not recognized
//                    supportButtons[i].setBounds(10, 10, 140, 140);
                    supportButtons[i].addActionListener(this);

            }
//            int x = (i % 5) * 140;
//            int y = 560;
//
//            supportButtons[i].setBounds(x, y, 140, 140);
//            supportButtons[i].addActionListener(this);

            // Check if the URL is in the data array and disable the button if not found
            boolean weaponFound = false;
            for (String dataURL : data) {
                if (support_buttons_array[i].endsWith(dataURL)) {
                    weaponFound = true;
                    break;
                }
            }

            if (weaponFound) {
                supportButtons[i].setEnabled(false);
            }
            supportButtons[i].addMouseListener(createMouseListener(support_name,support_desc,support_gold, support_herb, imageUrlArray[i]));
            itemSide.add(supportButtons[i]);
        }
    }


    public void BrewProcess(){
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
        ImageIcon brewName = new ImageIcon(root + "Cities\\Valoria\\Brewing\\brewName.png");
        itemName_label = new JLabel();
        itemName_label.setIcon(brewName);
        itemName_label.setOpaque(false);
        itemName_label.setBounds(550, 65, 450, 100);

        //label for the item name
        itemName2_label = new JLabel();
        itemName2_label.setBounds(550,65,450,100);
        itemName2_label.setForeground(Color.BLACK);
        itemName2_label.setFont(new Font("Times New Roman", Font.BOLD, 20));
        itemName2_label.setHorizontalAlignment(JLabel.CENTER);

        //label for the item desc
        ImageIcon potionDesc = new ImageIcon(root + "Cities\\Valoria\\Brewing\\contain.png");
        itemDesc_label = new JLabel();
        itemDesc_label.setIcon(potionDesc);
        itemDesc_label.setOpaque(false);
        itemDesc_label.setBounds(550, 200, 450, 300);

        //label for the item desc
        itemDesc2_label = new JLabel();
        itemDesc2_label.setBounds(590,280,380,300);
        itemDesc2_label.setForeground(Color.BLACK);
        itemDesc2_label.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        itemDesc2_label.setHorizontalAlignment(JLabel.CENTER);
        itemDesc2_label.setVerticalAlignment(JLabel.TOP);

        //label for item gold amount
        itemGold_label = new JLabel();
        itemGoldIcon_label = new JLabel();

        ImageIcon goldAmount = new ImageIcon(root + "Cities\\Valoria\\Brewing\\amount_contain.png");
        itemGold_label.setIcon(goldAmount);
        itemGold_label.setOpaque(false);
        itemGold_label.setBounds(595, 530, 175, 50);

        itemGoldIcon_label.setIcon(goldIcon);
        itemGoldIcon_label.setBounds(550,530,45,50);
        itemGoldIcon_label.setOpaque(false);

        //label for item herb amount
        itemHerb_label = new JLabel();
        itemHerbIcon_label = new JLabel();

        ImageIcon herbAmount = new ImageIcon(root + "Cities\\Valoria\\Brewing\\amount_contain.png");
        itemHerb_label.setIcon(herbAmount);
        itemHerb_label.setOpaque(false);
        itemHerb_label.setBounds(825, 530, 175, 50);

        itemHerbIcon_label.setIcon(herbIcon);
        itemHerbIcon_label.setBounds(780,530,45,50);
        itemHerbIcon_label.setOpaque(false);

        //button for brewing
        brewButton = new JButton(brewButton_button);
        brewButton.setBorderPainted(false);
        brewButton.setFocusable(false);
        brewButton.setContentAreaFilled(false);
        brewButton.setBounds(660, 600, 220, 100);
        brewButton.setVisible(false);
        brewButton.setEnabled(false);
        brewButton.addActionListener(this);

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

    String support_clicked = "";
    String support_clicked_url = "";
    int gold_amt, herb_amt;
    String[] support_data = new String[7]; // Initialize support_data with an array of appropriate size


    @Override
    public void actionPerformed(ActionEvent e) {
//        support_data = new String[7]; // Reset support_data with a new array at the beginning

        gold_amt = 0;
        herb_amt = 0;

        Object[][] weapons = {
                {supportButtons[0], "brew decay", brewOfDecay_pic_url},
                {supportButtons[1], "elixir of resilience", elixirOfResilience_pic_url},
                {supportButtons[2], "potent fury", potentFuryBrew_pic_url},
                {supportButtons[3], "potion of recovery", potionOfRecovery_pic_url},
                {supportButtons[4], "weakness potion", weaknessPotion_pic_url}
        };

        for (Object[] weapon : weapons) {
            if (e.getSource() == weapon[0]) {
                System.out.println(weapon[1] + " clicked!");
                support_clicked = (String) weapon[1];
                support_clicked_url = (String) weapon[2];
                support_data = data_table((String) weapon[2]);

                gold_amt = Integer.parseInt(support_data[4]);
                herb_amt = Integer.parseInt(support_data[6]);
                break; // Exit the loop once a match is found
            }
        }
        if (e.getSource() != brewButton && e.getSource() == exitButton) {
            SwingUtilities.invokeLater(() -> {
                Brewing.this.dispose();
            });
        }
        if (e.getSource() != brewButton) {
            if (!buttonClicked) {
                // Check if player has enough gold and biscuit
                if ((player_gold_amount >= gold_amt) && (player_herb_amount >= herb_amt)) {
                    brewButton.setVisible(true);
                    brewButton.setEnabled(true);
                } else {
                    brewButton.setVisible(false);
                    brewButton.setEnabled(false);
                }
                buttonClicked = true;
            } else {
                brewButton.setVisible(false);
                brewButton.setEnabled(false);
                buttonClicked = false;
            }
        }else{
//         String[] table_columns = {"ITEM_ID", "ITEM_NAME", "ITEM_EFFECT", "ITEM_DESC","GOLD_AMOUNT","URL","HERB_AMOUNT"};
            if (support_data[4] != null && support_data[6] != null) {
                gold_amt = Integer.parseInt(support_data[4]);
                herb_amt = Integer.parseInt(support_data[6]);
//                  print debugger
//                System.out.println("support: " + support_clicked);
//                System.out.println("url: " + support_clicked_url);
//                System.out.println("gold amount: " + gold_amt);
//                System.out.println("herb amount: " + herb_amt);
//
//                // Print values to understand why they are null
//                System.out.println("support_data[4]: " + support_data[4]);
//                System.out.println("support_data[6]: " + support_data[6]);

                // update database here
                currency_data = data_jointable(currency_id, currency_table_columns, "PLAYERS", "CURRENCY", "CURRENCY_ID", "");
                if ((player_gold_amount >= gold_amt) && (player_herb_amount >= herb_amt)) {

                    int gold_amt_toupdate = player_gold_amount - gold_amt;
                    int herb_amt_toupdate = player_herb_amount - herb_amt;

                    System.out.println("Before updating database: gold_amt_toupdate = " + gold_amt_toupdate + ", herb_amt_toupdate = " + herb_amt_toupdate);

                    tableupdate.updateTable("CURRENCY", "GOLD_COUNT", "CURRENCY_ID", currency_id, gold_amt_toupdate);
                    tableupdate.updateTable("CURRENCY", "HERB_COUNT", "CURRENCY_ID", currency_id, herb_amt_toupdate);
                    tableupdate.updateTable("PLAYERS", "POTIONS_BREWED", "CURRENCY_ID", currency_id, Integer.parseInt(player_data[8]) + 1);

                    String[] columns_of_inventory = {"ACCOUNT_ID", "ITEM_ID", "ITEMTYPE"};
                    String[] column_to_insert = {account_loggedin, support_data[0], "Potion"};
                    tableinsert.insertIntoTable("inventory", columns_of_inventory, column_to_insert);

                    System.out.println("After updating database: gold_amt_toupdate = " + gold_amt_toupdate + ", herb_amt_toupdate = " + herb_amt_toupdate);

                    PlayerAmount();
                    SupportItems();
                    SwingUtilities.invokeLater(()-> new GameNotif("Successful Transaction"));
                    SwingUtilities.invokeLater(Brewing.this::dispose);
                }
                else {
                    System.out.println("not enough gold and herb");
                }
            } else {
                // Handle the case where support_data[4] or support_data[6] is null
                System.out.println("Error: support_data[4] or support_data[6] is null");
                System.out.println(support_data[4] + support_data[6]);

            }
        }
    }


    private MouseAdapter createMouseListener(String itemName, String itemDesc,
                                             String goldAmount, String herbAmount, String img){
        return new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                if(!buttonClicked) {
                    itemName2_label.setText(itemName);
                    itemDesc2_label.setText("<html>" + itemDesc + "</html>");
                    itemGold_label.setText(goldAmount);
                    itemHerb_label.setText(herbAmount);

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
                    itemHerb_label.setText("");

                    itemShow_label.setIcon(null);
                }
            }
        };
    }
}