import Database.loggedin_identifier;
import Database.read_jointable;
import Database.read_table;
//import Inventory.Inventory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URLEncoder;

public class ItemPicking extends JFrame implements ActionListener {
    read_table readtable = new read_table();
    loggedin_identifier logidentifier = new loggedin_identifier();


    JButton[] supportButtons = new JButton[5];
    JButton[] attackButtons = new JButton[10]; // Create an array to store 10 buttons for attack icons
    JButton[] defenseButtons = new JButton[10];
    read_jointable readjointable = new read_jointable();
    String account_loggedin = logidentifier.readValue("LOGGEDIN_IDENTIFIER","ACCOUNT_ID");


    String[] data = readjointable.readjoinValues(account_loggedin, "URL"); // gets data from inventory

    String[] pass_url = new String[4];
    String[] item_table_columns = {"ITEM_ID","ITEM_NAME","ITEM_SKILL","ITEM_DESC","ITEM_EFFECT","DAMAGE","GOLD_AMOUNT","IRON_AMOUNT","HERB_AMOUNT","ITEM_TYPE","URL"};


    String root = "C:\\Users\\User\\Reforge2\\src\\assets\\";
    int click_ctr = 0;
    JButton frostbiteDagger, infernoScythe, phoenixWingSpear, runeboundCrossbow, serpentsClaws;
    JButton shadowReaverAxe, soulshatterBlade, starfallBow, thunderstrikeHammer, voidbringerStaff;
    JButton aegisSteel, armorersGemstone, barrierBracelet, defendersTalisman, guardiansAmulet;
    JButton protectionCharm, safeguardingMedallion, sentinelsBarricade, shieldbearersPendant, wardingRing;
    JButton brewOfDecay, elixirOfResilience, potentFuryBrew, potionOfRecovery, weaknessPotion;
    JPanel desc_panel;
    JLabel itemDesc_label,itemImg_label,  itemName_label, desc_panel_label, slot_label;

    public String[] data_table(String url, String[] table_to_get, String table, String identifier){
        String[] data_from_table = new String[table_to_get.length];
        for (int i = 0; i < table_to_get.length; i++)
        {
            data_from_table[i] = readtable.readValue(table,identifier, url, table_to_get[i]);
//            System.out.println(data_from_table[i]);
        }
        return data_from_table;
    }
    String bg_battle, bg_boss;
    ItemPicking(String bg, String  boss){
        bg_battle = bg;
        bg_boss = boss;
        int width = 1000;
        int height = 735;

        ImageIcon slot_img = new ImageIcon(root + "ItemPicking\\skill_panel2.jpg");
        this.setSize(width, height);
        this.setVisible(true);
        this.setLayout(null);
        this.setTitle("Reforge: Rise of the Seventeen Cities");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setResizable(false);
        AttackButtons();
        DefenseButtons();
        SupportButtons();
        DescPanel();
        this.getContentPane().setBackground(Color.BLACK);

        slot_label = new JLabel();
        slot_label.setIcon(slot_img);
        slot_label.setBounds(-3,0, 740,735);
        this.add(slot_label);


    }

    public void DescPanel(){
        ImageIcon desc_img = new ImageIcon(root + "ItemPicking\\desc_panel.png");
        desc_panel = new JPanel();
        desc_panel.setBounds(731,0,260, 735);
        desc_panel.setLayout(new BorderLayout());

        itemDesc_label = new JLabel();
        itemName_label = new JLabel();
        itemImg_label = new JLabel();
        desc_panel_label = new JLabel();

        desc_panel_label.setIcon(desc_img);
        desc_panel_label.setBounds(0,0, 260, 735);

        itemName_label.setBounds(85, 250, 300,100);
        itemDesc_label.setBounds(85, 200, 100,100);

        desc_panel.add(itemName_label);
        desc_panel.add(itemDesc_label);
        desc_panel.add(itemImg_label);
        desc_panel.add(desc_panel_label);
        this.add(desc_panel);
    }

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

    String[] attack_buttons_array = {frostbiteDagger_pic_url, infernoScythe_pic_url, phoenixWingSpear_pic_url, runeboundCrossbow_pic_url,
            serpentsClaws_pic_url, shadowReaverAxe_pic_url, soulshatterBlade_pic_url, starfallBow_pic_url, thunderstrikeHammer_pic_url, voidbringerStaff_pic_url};


    public void AttackButtons() {
        String attack_name, attack_desc;
        for (int i = 0; i < attack_buttons_array.length; i++) {
            String[] support_data = data_table(attack_buttons_array[i],item_table_columns,"ITEM","URL");
            attack_name = support_data[1];
            attack_desc = support_data[3];
            ImageIcon buttonIcon = new ImageIcon(attack_buttons_array[i]);
            attackButtons[i] = new JButton(buttonIcon);
            attackButtons[i].setContentAreaFilled(false);
            attackButtons[i].setBorderPainted(false);
            attackButtons[i].setFocusable(false);

            // Set the bounds using calculated values based on 'i'
            int x = (i % 5) * 140;
            int y = (i / 5) * 140;
            attackButtons[i].setBounds(x, y, 140, 140);

            attackButtons[i].addActionListener(this);

            // Check if the URL is in the data array and disable the button if not found
            boolean weaponFound = false;
            for (String dataURL : data) {
                if (attack_buttons_array[i].endsWith(dataURL)) {
                    weaponFound = true;
                    break;
                }
            }
            if (!weaponFound) {
                attackButtons[i].setEnabled(false);
            }
            attackButtons[i].addMouseListener(createMouseListener(attack_desc,attack_name,buttonIcon));
            add(attackButtons[i]);
        }

    }

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


    String[] defense_buttons_array = {aegisSteel_pic_url, armorersGemstone_pic_url, barrierBracelet_pic_url, defendersTalisman_pic_url, guardiansAmulet_pic_url,
            protectionCharm_pic_url, safeguardingMedallion_pic_url, sentinelsBarricade_pic_url, shieldbearersPendant_pic_url, wardingRing_pic_url};

    public void DefenseButtons() {
        String defense_name, defense_desc;
        for (int i = 0; i < defense_buttons_array.length; i++) {
            String[] support_data = data_table(defense_buttons_array[i],item_table_columns,"ITEM","URL");
            defense_name = support_data[1];
            defense_desc = support_data[3];
            ImageIcon buttonIcon = new ImageIcon(defense_buttons_array[i]);
            defenseButtons[i] = new JButton(buttonIcon);
            defenseButtons[i].setContentAreaFilled(false);
            defenseButtons[i].setBorderPainted(false);
            defenseButtons[i].setFocusable(false);

            int x = (i % 5) * 140;
            int y = ((i / 5) * 140) + 280; // Adjust the 'y' position for defense buttons

            defenseButtons[i].setBounds(x, y, 140, 140);
            defenseButtons[i].addActionListener(this);

            // Check if the URL is in the data array and disable the button if not found
            boolean weaponFound = false;
            for (String dataURL : data) {
                if (defense_buttons_array[i].endsWith(dataURL)) {
                    weaponFound = true;
                    break;
                }
            }

            if (!weaponFound) {
                defenseButtons[i].setEnabled(false);
            }
            defenseButtons[i].addMouseListener(createMouseListener(defense_desc,defense_name,buttonIcon));
            add(defenseButtons[i]);
        }
    }


    String brewOfDecay_pic_url = root + "Buttons\\support_buttons\\brew_of_decay_button.png";
    String elixirOfResilience_pic_url = root + "Buttons\\support_buttons\\elixir_of_resilience_button.png";
    String potentFuryBrew_pic_url = root + "Buttons\\support_buttons\\potent_fury_brew_button.png";
    String potionOfRecovery_pic_url = root + "Buttons\\support_buttons\\potion_of_recovery_button.png";
    String weaknessPotion_pic_url = root + "Buttons\\support_buttons\\weakness_potion_button.png";


    String[] support_buttons_array = {brewOfDecay_pic_url, elixirOfResilience_pic_url,potentFuryBrew_pic_url, potionOfRecovery_pic_url,
            weaknessPotion_pic_url};
    public void SupportButtons(){
        String support_name, support_desc;

        for (int i = 0; i < Math.min(support_buttons_array.length, supportButtons.length); i++) {
            String[] support_data = data_table(support_buttons_array[i],item_table_columns,"ITEM","URL");
            support_name = support_data[1];
            support_desc = support_data[3];
            ImageIcon buttonIcon = new ImageIcon(support_buttons_array[i]);
            supportButtons[i] = new JButton(buttonIcon);
            supportButtons[i].setContentAreaFilled(false);
            supportButtons[i].setBorderPainted(false);
            supportButtons[i].setFocusable(false);

            int x = (i % 5) * 140;
            int y = 560;

            supportButtons[i].setBounds(x, y, 140, 140);
            supportButtons[i].addActionListener(this);

            // Check if the URL is in the data array and disable the button if not found
            boolean weaponFound = false;
            for (String dataURL : data) {
                if (support_buttons_array[i].endsWith(dataURL)) {
                    weaponFound = true;
                    break;
                }
            }

            if (!weaponFound) {
                supportButtons[i].setEnabled(false);
            }
            supportButtons[i].addMouseListener(createMouseListener(support_desc,support_name,buttonIcon));
            add(supportButtons[i]);
        }
    }
    private MouseAdapter createMouseListener(String name, String desc, ImageIcon image) {
        return new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                if (((JButton)e.getSource()).isEnabled()) {
                    if (e.getSource() == starfallBow||e.getSource() == thunderstrikeHammer||e.getSource()==voidbringerStaff){
                        itemImg_label.setBounds(66,71,140,140);
                    }else {
                        itemImg_label.setBounds(73, 73, 140, 140);
                    }
                    itemName_label.setText(name);
                    itemImg_label.setIcon(image);
                    itemDesc_label.setText(desc);
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                itemName_label.setText("");
                itemImg_label.setIcon(null);
                itemDesc_label.setText(null);
            }
        };
    }


    String[] all_weapons_buttons_url_array = {frostbiteDagger_pic_url, infernoScythe_pic_url, phoenixWingSpear_pic_url, runeboundCrossbow_pic_url,
            serpentsClaws_pic_url, shadowReaverAxe_pic_url, soulshatterBlade_pic_url, starfallBow_pic_url, thunderstrikeHammer_pic_url, voidbringerStaff_pic_url,
            aegisSteel_pic_url, armorersGemstone_pic_url, barrierBracelet_pic_url, defendersTalisman_pic_url, guardiansAmulet_pic_url,
            protectionCharm_pic_url, safeguardingMedallion_pic_url, sentinelsBarricade_pic_url, shieldbearersPendant_pic_url, wardingRing_pic_url,
            brewOfDecay_pic_url, elixirOfResilience_pic_url,potentFuryBrew_pic_url,potentFuryBrew_pic_url, potionOfRecovery_pic_url,weaknessPotion_pic_url};

    //        public String readjoinValue(String tableName1, String tableName2, String idtable1, String idtable2, String idColumn, String url, String valueColumn) {
//    public String[] data_table(String url){
//        String[] table_columns = {"ITEM_ID", "ITEM_NAME", "ITEM_SKILL", "ITEM_DESC","GOLD_AMOUNT","URL"};
//        String[] data_from_table = new String[6];
//        for (int i = 0; i < table_columns.length; i++)
//        {
////            data_from_table[i] = readjointable.readjoinValue("ACC-1", "URL");
//        }
//        return data_from_table;
//    }
    public void inventory()
    {
        JButton[] buttons = {frostbiteDagger, infernoScythe, phoenixWingSpear, runeboundCrossbow, serpentsClaws,
                shadowReaverAxe, soulshatterBlade, starfallBow, thunderstrikeHammer, voidbringerStaff,
                aegisSteel, armorersGemstone, barrierBracelet, defendersTalisman, guardiansAmulet,
                protectionCharm, safeguardingMedallion, sentinelsBarricade, shieldbearersPendant, wardingRing,
                brewOfDecay, elixirOfResilience, potentFuryBrew, potionOfRecovery, weaknessPotion};

        if (data != null && data.length > 1) {
            for (String value : data) {
//                System.out.println(value);
            }
        } else {
            System.out.println("No data or insufficient data located for account: ACC-1");
        }
        for (int i = 0; i < all_weapons_buttons_url_array.length; i++) {
            boolean weaponfound_flag = false;
            for (String value : data) {
                int lastIndex1 = all_weapons_buttons_url_array[i].lastIndexOf("\\");
                int lastIndex2 = value.lastIndexOf("\\");
                String result1 = all_weapons_buttons_url_array[i].substring(lastIndex1); // Get the substring from the character after the last "\"
                String result2 = value.substring(lastIndex2);

                if (result1.equals(result2)) { // Check if the URL ends with the weapon example
                    weaponfound_flag = true;
                    break; // No need to continue checking if the weapon is found
                }
            }

            if (!weaponfound_flag) {
                buttons[i].setEnabled(false);
            }
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        JButton[] buttons = {frostbiteDagger, infernoScythe, phoenixWingSpear, runeboundCrossbow, serpentsClaws,
                shadowReaverAxe, soulshatterBlade, starfallBow, thunderstrikeHammer, voidbringerStaff,
                aegisSteel, armorersGemstone, barrierBracelet, defendersTalisman, guardiansAmulet,
                protectionCharm, safeguardingMedallion, sentinelsBarricade, shieldbearersPendant, wardingRing,
                brewOfDecay, elixirOfResilience, potentFuryBrew, potionOfRecovery, weaknessPotion};

        // Check which button was clicked based on its action command
//        for (int i = 0; i < buttons.length; i++) {
//            attackButtons[i].addActionListener(this);
//            defenseButtons[i].addActionListener(this);
//            supportButtons[i].addActionListener(this);
//
//
//            // Populate the buttons array with the button
//            buttons[i] = attackButtons[i];
//
//            if (buttons[i] != null && e.getSource() == buttons[i]) {
//                System.out.println("Button clicked: " + e.getActionCommand());
//
//                // Handle the click on the button with index i
//                String buttonName = buttons[i].getText();
//                itemName_label.setText(buttonName);
//                System.out.println("URL: " + all_weapons_buttons_url_array[i]);
//                pass_url[click_ctr++] = all_weapons_buttons_url_array[i];
//                System.out.println("ctr after: " + click_ctr);
//                buttons[i].setEnabled(false);
//
//                if (click_ctr == 4) {
//                    // If ctr reaches 4, break out of the loop to prevent further clicks.
//                    getPassUrl();
//                    new Battlefield();
//                }
//            }
//        }
        for (int i = 0; i < attackButtons.length; i++) {
            if (e.getSource() == attackButtons[i]) {
                handleButton(attackButtons[i], all_weapons_buttons_url_array[i]);
            }
        }

        for (int i = 0; i < defenseButtons.length; i++) {
            if (e.getSource() == defenseButtons[i]) {
                handleButton(defenseButtons[i], all_weapons_buttons_url_array[i + attackButtons.length]);
            }
        }

        for (int i = 0; i < supportButtons.length; i++) {
            if (e.getSource() == supportButtons[i]) {
                handleButton(supportButtons[i], all_weapons_buttons_url_array[i + attackButtons.length + defenseButtons.length]);
            }
        }
    }

    private String[] handleButton(JButton button, String url) {
        String[] data = new String[4];
        System.out.println("Button clicked: " + button.getActionCommand());

        // Handle the click on the button
        itemName_label.setText(button.getActionCommand());
        System.out.println("URL: " + url);
        pass_url[click_ctr++] = url;
        System.out.println("ctr after: " + click_ctr);
        button.setEnabled(false);

        if (click_ctr == 4) {
            // If ctr reaches 4, break out of the loop to prevent further clicks.
//            new Battlefield(getPassUrl(),root + "Backgrounds\\Academere_bg.jpg",root + "Bosses\\CatastropheSentinel.png");
            new Battlefield(getPassUrl(),bg_battle,bg_boss);

        }return data;

    }

    public String[] getPassUrl() {
        System.out.println("\nPASS URL HERE");
        for (String hold: pass_url)
        {

//            System.out.println(hold);
        }
        return pass_url;
    }

}
