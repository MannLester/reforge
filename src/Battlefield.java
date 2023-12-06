import Database.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;


public class Battlefield extends JFrame implements ActionListener {
    Animations animations = new Animations();
    loggedin_identifier logidentifier = new loggedin_identifier();
    String account_loggedin = logidentifier.readValue("LOGGEDIN_IDENTIFIER","ACCOUNT_ID");
    String[] boss_table_columns = {"BOSS_LVL_ID", "CITY_ID", "DROP_ID", "BOSS_NAME", "BOSS_LOCATION", "BOSS_SKILL_ID", "X_AXIS", "Y_AXIS", "BOSS_CHARACTER_FILENAME", "BOSS_ICON_FILENAME","BOSS_PROGRESS"};
    String[] boss_drop_table_columns = {"DROP_ID","GOLD_DROP","IRON_DROP","BISCUIT_DROP","HERB_DROP","DIAMOND_DROP"};
    String[] currency_table_columns = {"CURRENCY.CURRENCY_ID","GOLD_COUNT","IRON_COUNT","HERB_COUNT","BISCUIT_COUNT","DIAMOND_COUNT"};

    String[] get_boss_lvl = {"BOSS_LVL.BOSS_LVL_ID", "BOSS_LVL.BOSS_HP", "BOSS_LVL.BOSS_DMG", "BOSS_LVL.BOSS_ATK_EFFECT", "BOSS_LVL.BOSS_DEF_EFFECT", "BOSS_LVL.BOSS_SUPP_EFFECT", "BOSS_LVL.BOSS_FINALE_EFFECT"};

    String[] player_table_columns = {"PLAYERS.ACCOUNT_ID", "PLAYERS.WEAPONS_FORGED", "PLAYERS.PLAYER_HP", "PLAYERS.PLAYER_DMG", "PLAYERS.BOSSES_KILLED", "PLAYERS.PETS_TAMED","PLAYERS.CURRENCY_ID"};
    String[] account_table_columns = {"ACCOUNT_ID", "USERNAME", "PASSWORD"};
    String[] item_table_columns = {"ITEM_ID", "ITEM_NAME", "ITEM_SKILL", "ITEM_DESC", "ITEM_EFFECT", "DAMAGE", "GOLD_AMOUNT", "IRON_AMOUNT", "HERB_AMOUNT", "ITEM_TYPE", "URL"};
    read_bossdroptable readt = new read_bossdroptable();
    read_jointable readjointable = new read_jointable();
    String[] data = readjointable.readjoinValues(account_loggedin, "URL"); // gets data from inventory


    RandomBooleanGenerator num_generator = new RandomBooleanGenerator();
    JLabel bossHp;
    private boolean isPlayerTurn = true;
    private Timer timer;


    read_table readtable = new read_table();
    TableUpdater tableupdate = new TableUpdater();

    public String[] data_table(String url, String[] table_to_get, String table, String identifier) {
        String[] data_from_table = new String[table_to_get.length];
        for (int i = 0; i < table_to_get.length; i++) {
            data_from_table[i] = readtable.readValue(table, identifier, url, table_to_get[i]);
        }
        return data_from_table;
    }

    public String[] data_jointable(String boss_identifier, String[] info_to_get, String table1, String table2, String identifer, String limit) {
        String[] name = readt.readjoindropValues(boss_identifier, info_to_get, table1, table2, identifer, limit);

//        for (String val: name)
//        {
////            System.out.println(val);
//        }
        return name;
    }

    String bossToBattle;
    String root = "C:\\Users\\User\\Reforge2\\src\\assets\\";

    String orig_boss_city;
    String boss_progress;
    ImageIcon battleNotifications = new ImageIcon(root + "Battlefield\\battleNotifs.png");
    Battlefield(String[] values, String bgForBattle, String bossPass) {
        System.out.println("account data: " + account_data);
        int width = 1520;
        int height = 820;

        //frame layout
        this.setVisible(true);
        SkillsChosen(values);
        bossToBattle = bossPass;
//        int lastBackslashIndex = bossPass.lastIndexOf("\\");
//        String filepath_abs = bossPass.substring(lastBackslashIndex + 1);

        boss_data = data_table(bossPass, boss_table_columns, "BOSS", "BOSS_CHARACTER_FILENAME");
        orig_boss_city = boss_data[1];
        boss_progress = boss_data[10];
        if (boss_data[10] == null || boss_data[10] == "0")
        {
            boss_data = data_table(root + "Bosses\\Level1Boss.png", boss_table_columns, "BOSS", "BOSS_CHARACTER_FILENAME");
            bossToBattle = root + "Bosses\\Level1Boss.png";
        }
        else if (boss_data[10].equals("1"))
        {
            boss_data = data_table(root + "Bosses\\Level2Boss.png", boss_table_columns, "BOSS", "BOSS_CHARACTER_FILENAME");
            bossToBattle = root + "Bosses\\Level2Boss.png";
        }
        else if (boss_data[10].equals("2"))
        {
            boss_data = data_table(bossPass, boss_table_columns, "BOSS", "BOSS_CHARACTER_FILENAME");
            bossToBattle = bossPass;
        }
//            String[] boss_table_columns = {"BOSS_LVL_ID", "CITY_ID", "DROP_ID", "BOSS_NAME", "BOSS_LOCATION", "BOSS_SKILL_ID", "X_AXIS", "Y_AXIS", "BOSS_CHARACTER_FILENAME", "BOSS_ICON_FILENAME","BOSS_PROGRESS"};


        boss_lvl_data = data_jointable(boss_data[0], get_boss_lvl, "BOSS", "BOSS_LVL", "BOSS_LVL_ID", "limit 1");
        boss_drop_data = data_table(boss_data[2], boss_drop_table_columns, "BOSS_DROP","DROP_ID");

        boss_health = Integer.parseInt(boss_lvl_data[1]);
        boss_max_health = Integer.parseInt(boss_lvl_data[1]);
        boss_level = boss_lvl_data[0];

//        System.out.println("abs file of boss: " + filepath_abs);

        System.out.println("boss passed inside url here: " + root + "Bosses\\PrincipalCorrupto.png");
        System.out.println("bosstobattle url here:       " + bossToBattle);


        SizeAdjust(width, height); //input values of frame width and height here to automatically adjust other panels
        this.setTitle("Reforge: Rise of the Seventeen Cities");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLayout(new BorderLayout());

        //background
        ImageIcon bg_pic = new ImageIcon(bgForBattle);
        JLabel bg = new JLabel();
        bg.setIcon(bg_pic);
        bg.setBounds(0, 0, width, height);
        bg.setOpaque(false);
        this.add(bg);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (dim.width - getWidth()) / 2;

        setLocation(x, 0);
        playerTurn();
        pets_skills();
    }
    ImageIcon skill1_img, skill2_img, skill3_img, skill4_img;
    JButton skill1_button, skill2_button, skill3_button, skill4_button;
    JPanel skill_panel, profile_panel, boss_panel, boss_character_panel, player_character_panel;
    JLabel skill_panel_label, profile_panel_label, profile_pic_label, boss_panel_label, profile_username_label, profile_health_label, boss_name_label,
            boss_character_label, player_character_label, bossIcon_label;

    String[] account_data = data_table(account_loggedin, account_table_columns, "ACCOUNTS", "ACCOUNT_ID");
    //    String[] player_data = data_jointable(account_data[0],player_table_columns,"ACCOUNTS","PLAYERS","ACCOUNT_ID","");
    String[] player_data = data_jointable(account_loggedin, player_table_columns, "ACCOUNTS", "PLAYERS", "ACCOUNT_ID", "");
    String currency_id = player_data[6];

    String [] currency_data = data_jointable(currency_id, currency_table_columns, "PLAYERS", "CURRENCY","CURRENCY_ID","");

    int player_health = Integer.parseInt(player_data[2]);
    int player_max_health = Integer.parseInt(player_data[2]);


//    String[] boss_data = data_table(root + "Bosses\\CatastropheSentinel.png", boss_table_columns, "BOSS", "BOSS_CHARACTER_FILENAME");
//    String[] boss_data = data_table(root + "Bosses\\PrincipalCorrupto.png", boss_table_columns, "BOSS", "BOSS_CHARACTER_FILENAME");
//    String[] boss_data = data_table(bossToBattle, boss_table_columns, "BOSS", "BOSS_CHARACTER_FILENAME");

    String[] boss_data;

    // boss_data[0] to get boss_lvl_data
//    String[] boss_lvl_data = data_jointable(boss_data[0], get_boss_lvl, "BOSS", "BOSS_LVL", "BOSS_LVL_ID", "limit 1");
    String[] boss_lvl_data;
    String[] boss_drop_data;
    int currentFrame = 0;

    int boss_health, boss_max_health;
    public void SizeAdjust(int width, int height) {
        //for self-adjustments of borders
        int right_border_x = width - 188;
        int border_height = height - 150;

        //for self adjustments of panels
        int skill_panel_y = height - 170;
        int skill_panel_width = width - 520;
        int panel_height = height - border_height;
        int skill_buttons_width = panel_height - 10;
        int skill_buttons_height = panel_height - 10;
        int buttons_margin_x = (skill_panel_width - (skill_buttons_width * 4)) / 5;
        int buttons_margin_y = panel_height - 145;
        int profile_panel_width = width - skill_panel_width;

        //frame size
        this.setSize(width, height);

        BattlefieldLayout(right_border_x, border_height, skill_panel_y, skill_panel_width, panel_height,
                skill_buttons_width, skill_buttons_height, profile_panel_width, buttons_margin_x, buttons_margin_y);
    }

    JLabel notifs = new JLabel();
    JLabel notifsbg = new JLabel();

    public void BattlefieldLayout(int right_x, int border_height, int skill_y, int skill_width, int panel_height,
                                  int button_width, int button_height, int profile_width, int margin_x, int margin_y) {

        //border layout
        ImageIcon right_border = new ImageIcon(root + "Battlefield\\right_border.png");
        ImageIcon left_border = new ImageIcon(root + "Battlefield\\left_border.png");
        JLabel right_border_label = new JLabel();
        JLabel left_border_label = new JLabel();
        right_border_label.setIcon(right_border);
        right_border_label.setOpaque(false);
        right_border_label.setBounds(right_x, 0, 177, border_height);
        left_border_label.setIcon(left_border);
        left_border_label.setOpaque(false);
        left_border_label.setBounds(0, 0, 177, border_height);

        notifs.setText("");
        notifs.setBounds(650,200,850,106);
        notifs.setForeground(Color.WHITE);
        notifs.setVisible(false);

        notifsbg.setIcon(battleNotifications);
        notifsbg.setBounds(500,200,850,106);
        notifsbg.setVisible(false);

        //skill panel layout
        SkillPanel(skill_y, skill_width, panel_height);

        //skill buttons layout
        FirstSkill(margin_x, margin_y, button_width, button_height);
        SecondSkill(margin_x, margin_y, button_width, button_height);
        ThirdSkill(margin_x, margin_y, button_width, button_height);
        FourthSkill(margin_x, margin_y, button_width, button_height);

        //add to skill panel
        skill_panel.add(skill1_button);
        skill_panel.add(skill2_button);
        skill_panel.add(skill3_button);
        skill_panel.add(skill4_button);
        skill_panel.add(skill_panel_label);

        //profile panel layout
        ProfilePanel(skill_width, skill_y, profile_width, panel_height, button_width, button_height);

        //add to profile panel
        profile_panel.add(profile_health_label);
        profile_panel.add(profile_username_label);
        profile_panel.add(profile_pic_label);
        profile_panel.add(profile_panel_label);

        //boss profile panel layout
        BossPanel();

        BossCharacter();

        PlayerCharacter();
        //add to boss panel
        boss_panel.add(bossIcon_label);
        boss_panel.add(boss_panel_label);


        //add to frame
        this.add(notifs);
        this.add(notifsbg);
        this.add(skill_panel);
        this.add(profile_panel);
        this.add(right_border_label);
        this.add(left_border_label);
        this.add(boss_panel);
        this.add(boss_character_panel);
        this.add(player_character_panel);
    }

    String[] skill1_data = new String[11];
    String[] skill2_data = new String[11];
    String[] skill3_data = new String[11];
    String[] skill4_data = new String[11];

    String[] skill_urls = new String[4];

    public void SkillsChosen(String[] images) {
        int dmg = 0;
        int skills = 1;
        for (String url : images) {
            if (skills == 1) {
                skill1_img = new ImageIcon(url);
                skill_urls[0] = url;
                skill1_data = data_table(url, item_table_columns, "ITEM", "URL");
            } else if (skills == 2) {
                skill2_img = new ImageIcon(url);
                skill_urls[1] = url;
                skill2_data = data_table(url, item_table_columns, "ITEM", "URL");
            } else if (skills == 3) {
                skill3_img = new ImageIcon(url);
                skill_urls[2] = url;
                skill3_data = data_table(url, item_table_columns, "ITEM", "URL");
            } else if (skills == 4) {
                skill4_img = new ImageIcon(url);
                skill_urls[3] = url;
                skill4_data = data_table(url, item_table_columns, "ITEM", "URL");
            }
            skills++;
        }
    }

    public void SkillPanel(int skill_y, int skill_width, int panel_height) {
        //skill panel layout
        ImageIcon skill_panel_pic = new ImageIcon(root + "Battlefield\\skill_panel.png");
        skill_panel_label = new JLabel();
        skill_panel_label.setIcon(skill_panel_pic);
        skill_panel_label.setBounds(0, 0, 1000, 140);

        skill_panel = new JPanel();
        skill_panel.setBounds(0, skill_y, skill_width, panel_height);
        skill_panel.setLayout(new BorderLayout());

    }

    public void FirstSkill(int margin_x, int margin_y, int button_width, int button_height) {
        skill1_button = new JButton(skill1_img);
        skill1_button.setBounds(margin_x, margin_y, button_width, button_height);
        skill1_button.setContentAreaFilled(false);
        skill1_button.setBorderPainted(false);
        skill1_button.setFocusable(false);
        skill1_button.addActionListener(this);
    }

    public void SecondSkill(int margin_x, int margin_y, int button_width, int button_height) {
        skill2_button = new JButton(skill2_img);
        skill2_button.setBounds((margin_x * 2) + button_width, margin_y, button_width, button_height);
        skill2_button.setContentAreaFilled(false);
        skill2_button.setBorderPainted(false);
        skill2_button.setFocusable(false);
        skill2_button.addActionListener(this);
    }

    public void ThirdSkill(int margin_x, int margin_y, int button_width, int button_height) {
        skill3_button = new JButton(skill3_img);
        skill3_button.setBounds((margin_x * 3) + (button_width * 2), margin_y, button_width, button_height);
        skill3_button.setContentAreaFilled(false);
        skill3_button.setBorderPainted(false);
        skill3_button.setFocusable(false);
        skill3_button.addActionListener(this);
    }

    public void FourthSkill(int margin_x, int margin_y, int button_width, int button_height) {
        skill4_button = new JButton(skill4_img);
        skill4_button.setBounds((margin_x * 4) + (button_width * 3), margin_y, button_width, button_height);
        skill4_button.setContentAreaFilled(false);
        skill4_button.setBorderPainted(false);
        skill4_button.setFocusable(false);
        skill4_button.addActionListener(this);
    }

    public void ProfilePanel(int skill_width, int skill_y, int profile_width, int panel_height, int button_width, int button_height) {
        //profile panel layout
        ImageIcon profile_panel_pic = new ImageIcon(root + "Battlefield\\profile_panel.png");
        ImageIcon profile_player_pic = new ImageIcon(root + "Character\\characterIcon.png");

        profile_panel_label = new JLabel();
        profile_panel_label.setIcon(profile_panel_pic);
        profile_panel_label.setOpaque(false);
        profile_panel_label.setBounds(0, 0, profile_width, panel_height);

        profile_pic_label = new JLabel();
        profile_pic_label.setIcon(profile_player_pic);
        profile_pic_label.setOpaque(false);
        profile_pic_label.setBounds(50, 5, button_width, button_height);

        profile_username_label = new JLabel();
        profile_username_label.setText(account_data[1]);
        profile_username_label.setBounds(200, 0, 300, 80);

        profile_health_label = new JLabel();
        profile_health_label.setText(player_health + " / " + player_max_health);
        profile_health_label.setBounds(200, 50, 300, 80);

        profile_panel = new JPanel();
        profile_panel.setBounds(skill_width, skill_y, profile_width, panel_height);
        profile_panel.setLayout(new BorderLayout());
    }

    public void BossPanel() {
        ImageIcon boss_panel_pic = new ImageIcon(root + "Battlefield\\profile_panel.png");
        boss_panel = new JPanel();
        boss_panel.setBounds(805, 0, 510, 140);
        boss_panel_label = new JLabel();
        boss_panel_label.setIcon(boss_panel_pic);
        boss_panel_label.setBounds(0, 0, 510, 140);
        boss_panel_label.setOpaque(false);
        boss_panel.setLayout(new BorderLayout());

//        ImageIcon bossIcon_pic = new ImageIcon(root + "Bosses\\Level2Boss_icon.png");
        ImageIcon bossIcon_pic = new ImageIcon(boss_data[9]);
        bossIcon_label = new JLabel();
        bossIcon_label.setBounds(20, 0, 120, 120);
        bossIcon_label.setIcon(bossIcon_pic);
        bossIcon_label.setOpaque(false);

        JLabel bossName = new JLabel(boss_data[3]);
        bossName.setBounds(150, 0, 200, 50);
        bossName.setVisible(true);

        bossHp = new JLabel("Boss Hp: " + boss_health + " / " + boss_max_health);
        bossHp.setBounds(150, 30, 120, 50);
        bossHp.setVisible(true);

        boss_panel.add(bossName);
        boss_panel.add(bossHp);

    }


    public void BossCharacter() {
        ImageIcon bossPic = new ImageIcon(bossToBattle);
        boss_character_panel = new JPanel();
        boss_character_label = new JLabel();
        boss_character_panel.setBounds(900, 245, 500, 550);
        boss_character_label.setBounds(0, 0, 500, 550);
        boss_character_label.setIcon(bossPic);
        boss_character_panel.setOpaque(false);
        boss_character_panel.setLayout(new BorderLayout());

        boss_character_panel.add(boss_character_label);
    }

    private void playerTurn() {
        // Enable player's skills buttons
        skill1_button.setEnabled(true);
        skill2_button.setEnabled(true);
        skill3_button.setEnabled(true);
        skill4_button.setEnabled(true);
    }


    private void bossTurn() {
        // Disable player's skills buttons during boss's turn
        skill1_button.setEnabled(false);
        skill2_button.setEnabled(false);
        skill3_button.setEnabled(false);
        skill4_button.setEnabled(false);

        System.out.println("Boss's turn started!");

        // Use a Timer for a delayed boss attack
        Timer bossAttackTimer = new Timer(5000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Simulate boss's attack
//                float bossDamage = calculateBossDamage(); // Implement your logic to calculate boss damage
//                player_health -= bossDamage;
//                player_health = Math.max(0, player_health);  // Ensure player's health doesn't go below 0
//
//                // Update player health label
//                profile_health_label.setText(player_health + " / " + player_max_health);

                // After boss's turn, it's the player's turn again
                notifs.setVisible(false);
                notifsbg.setVisible(false);
                notifs.setText(""); // Clear the text when hiding
                isPlayerTurn = true;
                playerTurn();
            }
        });

//         Start the boss attack timer
        bossAttackTimer.setRepeats(false); // Set to false to run only once
        bossAttackTimer.start();
    }


    public void PlayerCharacter() {
        ImageIcon player = new ImageIcon(root + "Character\\character.png");
        player_character_panel = new JPanel();
        player_character_label = new JLabel();
        player_character_panel.setBounds(320, 360, 315, 320);
        player_character_label.setBounds(0, 0, 315, 320);
        player_character_label.setIcon(player);
        player_character_panel.setOpaque(false);
        player_character_panel.setLayout(new BorderLayout());

        player_character_panel.add(player_character_label);
    }

    private boolean hasPlayerAttacked = false;

    float bossDamage;
    int gold_bossdrop, iron_bossdrop, biscuit_bossdrop, herb_bossdrop, diamond_bossdrop;


    @Override
    public void actionPerformed(ActionEvent e) {
        bossDamage = 0;
        // Stop the timer when it reaches its specified delay
        if (e.getSource() == timer) {
            // Simulate boss's attack
            if (!is_frozen && !is_stun) {
                if (armorer_defense_up) {
                    bossDamage = calculateBossDamage() - armorer_defense;
                    System.out.println("boss dmg inside: " + bossDamage);

                } else {
                    bossDamage = calculateBossDamage();
                } // Implement your logic to calculate boss damage
            } else if (is_stun && !is_frozen) {
                bossDamage = (float) (calculateBossDamage() * 0.50);
                setNotifsVisibilityWithTimer(true, 2000, "Boss is Stunned!");
            } else {
                bossDamage = 0;
            }
            System.out.println("boss dmg: " + bossDamage);
            player_health -= bossDamage;
            player_health = Math.max(0, player_health);  // Ensure player's health doesn't go below 0

            // Update player health label
            profile_health_label.setText(player_health + " / " + player_max_health);


            // After boss's turn, it's the player's turn again
            if (boss_health <= 0) {
                gold_bossdrop = Integer.parseInt(boss_drop_data[1]);
                iron_bossdrop = Integer.parseInt(boss_drop_data[2]);
                herb_bossdrop = Integer.parseInt(boss_drop_data[4]);
                biscuit_bossdrop = Integer.parseInt(boss_drop_data[3]);
                diamond_bossdrop = Integer.parseInt(boss_drop_data[5]);

                if (mystic_unicorn_activated)
                {
                    gold_bossdrop = (int) (gold_bossdrop * 1.1f);
                    iron_bossdrop = (int) (iron_bossdrop * 1.1f);
                    herb_bossdrop = (int) (herb_bossdrop * 1.1f);
                    biscuit_bossdrop = (int) (biscuit_bossdrop * 1.1f);
                }

                tableupdate.updateTable("CURRENCY", "GOLD_COUNT", "CURRENCY_ID", currency_id,Integer.parseInt(currency_data[1]) + gold_bossdrop);
                tableupdate.updateTable("CURRENCY", "IRON_COUNT", "CURRENCY_ID", currency_id,Integer.parseInt(currency_data[2]) + iron_bossdrop);
                tableupdate.updateTable("CURRENCY", "HERB_COUNT", "CURRENCY_ID", currency_id, Integer.parseInt(currency_data[3]) + herb_bossdrop);
                tableupdate.updateTable("CURRENCY", "BISCUIT_COUNT", "CURRENCY_ID", currency_id, Integer.parseInt(currency_data[4]) + biscuit_bossdrop);
                tableupdate.updateTable("CURRENCY", "DIAMOND_COUNT", "CURRENCY_ID", currency_id, Integer.parseInt(currency_data[5]) + diamond_bossdrop);
                tableupdate.updateTable("PLAYERS", "BOSSES_KILLED", "CURRENCY_ID", currency_id, Integer.parseInt(player_data[4]) + 1);

                String boss_city = orig_boss_city;

                if (boss_progress == null)
                {
                    tableupdate.updateTable("BOSS", "BOSS_PROGRESS", "CITY_ID", boss_city, 1);
                }
                else if (boss_progress.equals("1"))
                {
                    System.out.println("update here on boss progress");
                    tableupdate.updateTable("BOSS", "BOSS_PROGRESS", "CITY_ID", boss_city, 2);
                }
                else if (boss_progress.equals("2"))
                {
                    tableupdate.updateTable("BOSS", "BOSS_PROGRESS", "CITY_ID", boss_city, 3);
                }
                else
                {
                    System.out.println("located nothing");
                }
//                String boss_city = boss_data[1];

                String lastTwoCharacters = boss_city.substring(boss_city.length() - 2);
                int index = Integer.parseInt(lastTwoCharacters);
                if (boss_city.equals("CITY-01")) {
                    Eldoria eldoria = new Eldoria(50, 200);
                    eldoria.Quadrant2();
                } else if (boss_city.equals("CITY-02")) {
                    Havenbrook havenbrook = new Havenbrook(580, 380);
                    havenbrook.Quadrant3();
                } else if (boss_city.equals("CITY-03")) {
                    Amberdale amberdale = new Amberdale(770, 480);
                    amberdale.Quadrant4();
                } else if (boss_city.equals("CITY-04")) {
                    Academere academere = new Academere(600, 500);
                    academere.Quadrant2();
                } else if (boss_city.equals("CITY-05")) {
                    Ironspire ironspire = new Ironspire(110, 470);
                    ironspire.Quadrant4();
                } else if (boss_city.equals("CITY-06")) {
                    Willowbrook willowbrook = new Willowbrook(815, 370);
                    willowbrook.Quadrant4();
                } else if (boss_city.equals("CITY-07")) {
                    Shadowfall shadowfall = new Shadowfall(40, 160);
                    shadowfall.Quadrant4();
                } else if (boss_city.equals("CITY-08")) {
                    Rusthaven rusthaven = new Rusthaven(1110, 60);
                    rusthaven.Quadrant4();
                } else if (boss_city.equals("CITY-09")) {
                    Backwarden backwarden = new Backwarden(350, 370);
                    backwarden.Quadrant4();
                } else if (boss_city.equals("CITY-10")) {
                    Stonewatch stonewatch = new Stonewatch(450, 210);
                    stonewatch.Quadrant4();
                } else if (boss_city.equals("CITY-11")) {
                    Ghosthaven ghosthaven = new Ghosthaven(680, 560);
                    ghosthaven.Quadrant3();
                } else if (boss_city.equals("CITY-12")) {
                    Industria industria = new Industria(200, 340);
                    industria.Quadrant3();
                } else if (boss_city.equals("CITY-13")) {
                    ClimaVoid climaVoid = new ClimaVoid(460, 450);
                    climaVoid.Quadrant3();
                } else if (boss_city.equals("CITY-14")) {
                    Azureport azureport = new Azureport(980, 20);
                    azureport.Quadrant1();
                } else if (boss_city.equals("CITY-15")) {
                    Rosewood rosewood = new Rosewood(620, 70);
                    rosewood.Quadrant1();
                } else if (boss_city.equals("CITY-16")) {
                    Justice justice = new Justice(720, 360);
                    justice.Quadrant3();
                } else if (boss_city.equals("CITY-17")) {
                    Skyport skyport = new Skyport(10, 540);
                    skyport.Quadrant4();
                } else if (boss_city.equals("CITY-18")) {
                    Valoria valoria = new Valoria(750, 240);
                    valoria.Quadrant3();
                }

                // Make the frame appear

                // Boss is defeated, perform your victory actions here
                System.out.println("Boss Defeated!");
                // Add any additional logic or method calls for when the boss is defeated

                // For now, let's print "Game Over" if the boss is defeated
                System.out.println("Game Over");
            } else {
                // Continue with the game
                // After boss's turn, it's the player's turn again
                isPlayerTurn = true;
                if (!player_cancel_turn_activated) {
                    playerTurn();
                    System.out.println("player not cancelled " + player_cancel_turn_activated);
                } else {
//                    player_cancel_turn_activated = false; // Reset the flag when the player cancels the turn
                    bossTurn();
                    System.out.println("player cancelled");
                }
                hasPlayerAttacked = false; // Reset the flag for the new player turn
            }
        } else if (isPlayerTurn && !hasPlayerAttacked) {
            // Player's turn
            // Start the timer only once
            if (timer == null || !timer.isRunning()) {
                // Set the timer with the appropriate delay
                timer = new Timer(2000, this);
                timer.setRepeats(false); // Set to false to run only once
                timer.start();

                // Perform any other actions related to the player's turn here
                handlePlayerAttack(e);

                hasPlayerAttacked = true;
            }
        }
        // Handle other actions if needed
    }

    boolean is_frozen = false;
    boolean is_stun = false;

    private void handlePlayerAttack(ActionEvent e) {
        float playerDamage = 0;
        // Handle player's attack based on the pressed skill button
//        if (e.getSource() == skill1_button || e.getSource() == skill3_button || e.getSource() == skill2_button || e.getSource() == skill4_button) {
        if (e.getSource() == skill1_button) {
            // Implement logic for skill 1 and skill 3 attacks
            playerDamage = calculatePlayerDamage(skill_urls[0]);
        } // Implement your logic to calculate player damage
        // Update boss's health (assuming boss_health is a variable representing boss's health)
        else if (e.getSource() == skill2_button) {
            playerDamage = calculatePlayerDamage(skill_urls[1]);
        } else if (e.getSource() == skill3_button) {
            playerDamage = calculatePlayerDamage(skill_urls[2]);
        } else if (e.getSource() == skill4_button) {
            playerDamage = calculatePlayerDamage(skill_urls[3]);
        }
        if (boss2_skill3_reduce_damage) {
            playerDamage = (float) (playerDamage * 0.8);
        } else {
        }
        boss_health -= playerDamage;
        boss_health = Math.max(0, boss_health);  // Ensure boss's health doesn't go below 0

        // Update boss health label directly
        bossHp.setText("Boss Hp: " + boss_health + " / " + boss_max_health);

        // After player's turn, it's the boss's turn
        isPlayerTurn = false;
        // Implement logic for the boss's turn (e.g., calling bossTurn())
        if (!is_frozen) {
            bossTurn();
        } else {
            System.out.println("Frozen effect!");
        }
    }

    String[] pet_skill;
    String[] pets_url = {root + "Cities\\Valoria\\PetShop\\Aqua Serpent Companion.png", root + "Cities\\Valoria\\PetShop\\Celestial Dragon Kin.png",
            root + "Cities\\Valoria\\PetShop\\Earth Golem Guardian.png", root + "Cities\\Valoria\\PetShop\\Flameheart Phoenix.png", root + "Cities\\Valoria\\PetShop\\Mystic Unicorn Steed.png",
            root + "Cities\\Valoria\\PetShop\\Shadowfox Familiar.png", root + "Cities\\Valoria\\PetShop\\Venomous Spiderling.png", root + "Cities\\Valoria\\PetShop\\Zephyr Owl Companion.png"};

    boolean earth_golem_activated = false, celestial_dragon_activated = false, mystic_unicorn_activated = false;
    //    String[] item_table_columns = {"ITEM_ID", "ITEM_NAME", "ITEM_SKILL", "ITEM_DESC", "ITEM_EFFECT", "DAMAGE", "GOLD_AMOUNT", "IRON_AMOUNT", "HERB_AMOUNT", "ITEM_TYPE", "URL"};
    private void pets_skills() {
        for (String url : pets_url) {
            pet_skill = data_table(url, item_table_columns, "ITEM", "URL");

            for (String dataurl : data) {
                processPetActivation(dataurl);
            }
        }
    }

    private void processPetActivation(String dataurl) {
        int lastBackslashIndex = dataurl.lastIndexOf("\\");
        String name_abs = dataurl.substring(lastBackslashIndex + 1);

        switch (name_abs) {
            case "Flameheart Phoenix.png":
                if (player_health <= (player_max_health * 0.15)) {
                    player_health += player_max_health * 0.10;
                }
                break;
            case "Shadowfox Familiar.png":
                System.out.println("fox activated");
                break;
            case "Earth Golem Guardian.png":
                System.out.println("golem activated");
                earth_golem_activated = true;
                break;
            case "Celestial Dragon Kin.png":
                System.out.println("dragon celes activated");
                celestial_dragon_activated = num_generator.generateRandomBoolean(10);
                break;
            case "Venomous Spiderling.png":
                System.out.println("venom activated");
                boss_health -= (boss_health * 0.02);
                break;
            case "Mystic Unicorn Steed.png":
                System.out.println("Mystic activated");
                mystic_unicorn_activated = true;
                break;
            case "Zephyr Owl Companion.png":
                System.out.println("owl activated");
                mystic_unicorn_activated = true;
                break;
            default:
                System.out.println("Not found pet: " + name_abs + dataurl);
        }
    }

    int damage;
    //    String[] item_table_columns = {"ITEM_ID","ITEM_NAME","ITEM_SKILL","ITEM_DESC","ITEM_EFFECT","DAMAGE","GOLD_AMOUNT","IRON_AMOUNT","HERB_AMOUNT","ITEM_TYPE","URL"};
    float damage_float;
    int stardust_damage = 0;
    int burn_damage = 0;
    int poison_damage = 0;
    float crit_damage = 1.0f;
    float armorer_defense = 0;
    boolean armorer_defense_up = false;
    boolean safeguarding_medallion_activated = false;
    boolean already_ran_safeguarding_medallion_activated = false;
    boolean brew_of_decay_activated = false;
    boolean potent_fury_activated = false;
    boolean weakness_potion_activated = false;

    public void setNotifsVisibility(boolean isVisible, String text) {
        notifs.setVisible(isVisible);
        notifs.setText(text);
        notifsbg.setVisible(isVisible);
//        notifs.setHorizontalAlignment(SwingConstants.LEFT);

    }

    private void setNotifsVisibilityWithTimer(boolean isVisible, int delay, String text) {
        setNotifsVisibility(isVisible, text);

//        Timer notifsTimer = new Timer(delay, evt -> {
//            notifs.setVisible(false);
//            notifs.setText(""); // Clear the text when hiding
//            ((Timer) evt.getSource()).stop();
//        });
//
//        notifsTimer.setRepeats(false);
//        notifsTimer.start();
    }

    private float calculatePlayerDamage(String url) {
        // Implement your logic to calculate player's damage
        // This can be based on the skill selected by the player
        // For example, you can use the values value1, value2, value3, value4

        String[] skill_data = data_table(url, item_table_columns, "ITEM", "URL");
        int player_dmg = Integer.parseInt(player_data[3]);
        is_frozen = false;
        crit_damage = 1.0f;
        stardust_damage = 0;
        armorer_defense_up = false;
        weakness_potion_activated = false;
        int damage = 0;

        if (skill_data[9].equals("Weapon")) {
            if (skill_data[1].equals("Frostbite Dagger")) {
                startAnimation(animations.getFrostbiteDagger());
                is_frozen = num_generator.generateRandomBoolean(30);
                if (is_frozen)
                {
                    setNotifsVisibilityWithTimer(true, 2500, "Player uses Frozen Effect on Boss!");
                }
                else {
                    setNotifsVisibilityWithTimer(true, 2500, "Player used Frostbite Dagger!");
                }
            } else if (skill_data[1].equals("Inferno Scythe")) {
                startAnimation(animations.getInfernoScythe());
                burn_damage = 10;
                setNotifsVisibilityWithTimer(true, 2000, "Inferno Scythe, deals 10 burn damage!");
            } else if (skill_data[1].equals("Phoenix Wing Spear")) {
                startAnimation(animations.getPhoenixSpear());
                boolean is_crit = num_generator.generateRandomBoolean(50);
                if (is_crit) {
                    crit_damage = 1.2f;
                    setNotifsVisibilityWithTimer(true, 2000, "Phoenix Wing Spear, deals crit effect!");
                }
            } else if (skill_data[1].equals("Serpent's Claws")) {
                startAnimation(animations.getSerpentClaws());
                poison_damage = 5;
                setNotifsVisibilityWithTimer(true, 2000, "Serpent's Claws poison damage activated!");
            } else if (skill_data[1].equals("Shadow Reaver Axe")) {
                startAnimation(animations.getShadowAxe());
                is_frozen = num_generator.generateRandomBoolean(20);
            } else if (skill_data[1].equals("Soulshatter Blade")) {
                startAnimation(animations.getSoulBlade());
                player_health += 10;
                player_health = Math.max(0, player_health);  // Ensure player's health doesn't go below 0
                setNotifsVisibilityWithTimer(true, 2000, "Soulshatter Blade activated, Revive Player HP!");
            } else if (skill_data[1].equals("Starfall Bow")) {
                startAnimation(animations.getStarfallBow());
                stardust_damage = 10;
                setNotifsVisibilityWithTimer(true, 2000, "Starfall Bow Activated stardust damage!");
            } else if (skill_data[1].equals("Thunderstrike Hammer")) {
                startAnimation(animations.getThunderHammer());
                is_stun = num_generator.generateRandomBoolean(60);
            }

            if (brew_of_decay_activated) {
                damage = Integer.parseInt(skill_data[5]) + player_dmg + burn_damage + poison_damage + stardust_damage;
                damage_float = (float) (damage * crit_damage * 1.2);
                brew_of_decay_activated = false;
            }
            if (potent_fury_activated) {
                damage = Integer.parseInt(skill_data[5]) + player_dmg + burn_damage + poison_damage + stardust_damage;
                damage_float = (float) (damage * crit_damage * 1.25);
                potent_fury_activated = false;
            } else {
                damage = Integer.parseInt(skill_data[5]) + player_dmg + burn_damage + poison_damage + stardust_damage;
                damage_float = (float) (damage * crit_damage);
            }
        } else if (skill_data[9].equals("Artifact")) {
            if (skill_data[1].equals("Guardian's Amulet")) {
                startAnimation(animations.getGuardiansAmulet());
                float bossDamage = calculateBossDamage();
                player_health += bossDamage;
                setNotifsVisibilityWithTimer(true, 2000, "Guardian's Amulet Activated, revive Player HP!");
            } else if (skill_data[1].equals("Warding Ring")) {
                startAnimation(animations.getWardingRing());
                float bossDamage = calculateBossDamage();
                boss_health -= bossDamage;
                setNotifsVisibilityWithTimer(true, 2000, "Warding Ring Activated, reflect Boss Damage!");
            } else if (skill_data[1].equals("Defender's Talisman")) {
                startAnimation(animations.getDefendersTalisman());
                float bossDamage = calculateBossDamage();
                boss_health -= bossDamage;
                player_health -= bossDamage;
                setNotifsVisibilityWithTimer(true, 2000, "Defender's Talisman Activated, Boss and Player would receive damage!");
            } else if (skill_data[1].equals("Barrier Bracelet")) {
                startAnimation(animations.getBarrierBracelet());
                float bossDamage = calculateBossDamage();
                boss_health -= bossDamage * 0.5;
                setNotifsVisibilityWithTimer(true, 2000, "Barrier Bracelet Activated, reflect 50% of Damage received by Player!");

            } else if (skill_data[1].equals("Safeguarding Medallion")) {
                startAnimation(animations.getSafeguardingMedallion());
                safeguarding_medallion_activated = true;
                if ((player_max_health * 0.1) >= (player_health)) {
                    if (!already_ran_safeguarding_medallion_activated) {
                        safeguarding_medallion_activated = true;
                        player_health = (int) (player_max_health * 0.5);
                        already_ran_safeguarding_medallion_activated = true;
                        setNotifsVisibilityWithTimer(true, 2000, "Safeguarding Medallion Activated, revive 50% Player HP!");

                    }
                }
            } else if (skill_data[1].equals("Protection Charm")) {
                startAnimation(animations.getProtectionCharm());
                is_stun = num_generator.generateRandomBoolean(50);
            } else if (skill_data[1].equals("Sentinel's Barricade")) {
                startAnimation(animations.getSentinelBarricade());
                float bossDamage = calculateBossDamage();
                player_health += bossDamage;
                setNotifsVisibilityWithTimer(true, 2000, "Sentinels Barricade Activated, Player absorbs Crit Damage!");
            } else if (skill_data[1].equals("Armorer's Gemstone")) {
                startAnimation(animations.getArmorersGemstone());
                float bossDamage = calculateBossDamage();
                armorer_defense = (float) (bossDamage * 0.1);
                armorer_defense_up = true;
                setNotifsVisibilityWithTimer(true, 2000, "Armorer's Gemstone Activated, Increase Player Defense!");
            } else if (skill_data[1].equals("Aegis Steel")) {
                startAnimation(animations.getAegisSteel());
                //used armorer gemstone's variables because it does the samething
                float bossDamage = calculateBossDamage();
                armorer_defense = (float) (bossDamage * 0.2);
                armorer_defense_up = true;
                setNotifsVisibilityWithTimer(true, 2000, "Aegis Steel Activated, reduce Boss' Damage by 20%!");
            }
            damage_float = 0;
        } else if (skill_data[9].equals("Potion")) {
            if (skill_data[1].equals("Brew of Decay")) {
                startAnimation(animations.getBrewOfDecay());
                player_health += player_max_health * 0.5;
                player_health = Math.min(player_health, 100);
                brew_of_decay_activated = true;
//                damage = Integer.parseInt(skill_data[5]) + player_dmg + burn_damage + poison_damage + stardust_damage;
//                damage_float = (float) (damage * 0.2);
                setNotifsVisibilityWithTimer(true, 2000, "Brew of Decay Activated, revive Player HP!");
                damage_float = 0;
            } else if (skill_data[1].equals("Potent Fury Brew")) {
                startAnimation(animations.getPotentFuryBrew());
                damage_float = 0;
                potent_fury_activated = true;
                setNotifsVisibilityWithTimer(true, 2000, "Potent Fury Activated, Increase Player Damage by 25%!");
            } else if (skill_data[1].equals("Potion of Recovery")) {
                startAnimation(animations.getPotionOfRecovery());
                player_health += player_max_health * 0.25;
                player_health = Math.min(player_health, 100);
                setNotifsVisibilityWithTimer(true, 2000, "Potion of Recovery Activated, revive Player HP!");
            } else if (skill_data[1].equals("Weakness Potion")) {
                startAnimation(animations.getWeaknessPotion());
                weakness_potion_activated = true;
                setNotifsVisibilityWithTimer(true, 2000, "Weakness Potion Activated, decrease Damage of Boss!");
            }
        } else {
            damage_float = 0;
        }

        if (celestial_dragon_activated)
        {
            damage_float = (float) (damage_float * 1.1);
        }
        return damage_float;
    }

    int randomSkill;
//    String[] get_boss_lvl = {"BOSS_LVL.BOSS_LVL_ID","BOSS_LVL.BOSS_HP","BOSS_LVL.BOSS_DMG","BOSS_LVL.BOSS_ATK_EFFECT","BOSS_LVL.BOSS_DEF_EFFECT","BOSS_LVL.BOSS_SUPP_EFFECT","BOSS_LVL.BOSS_FINALE_EFFECT"};

    String boss_level;
    //            String boss_level = "BOSS_LVL-03";
    float boss_damage;
    boolean damage_chance, player_cancel_turn_activated, boss2_skill3_reduce_damage = false;

    private float calculateBossDamage() {
        // Implement your logic to calculate boss's damage
        // This can be based on the boss's attack patterns or random values
        randomSkill = num_generator.boss_selector();
//        randomSkill = 1;
        boss2_skill3_reduce_damage = false;
        damage_chance = false;
        player_cancel_turn_activated = false;
        float boss_reflect_damage;


        switch (randomSkill) {
            case 1: // attack skill here
                if (boss_level.equals("BOSS_LVL-02")) { //10% chance to double dmg
                    System.out.println("Attack lvl 2");
                    damage_chance = num_generator.generateRandomBoolean(10);
                    if (damage_chance) {
                        boss_damage = (float) (Integer.parseInt(boss_lvl_data[2]) * 1.1);
                        setNotifsVisibilityWithTimer(true, 2000, "Boss Activates Attack Skill, deals 10% more Damage!");
                    }
                } else if (boss_level.equals("BOSS_LVL-03")) {
                    System.out.println("Attack lvl 3");
                    player_cancel_turn_activated = num_generator.generateRandomBoolean(30);
                    boss_damage = Integer.parseInt(boss_lvl_data[2]);
                    setNotifsVisibilityWithTimer(true, 2000, "Boss Activates Attack Skill, 30% chance of Player Stun!");

                } else if (boss_level.equals("BOSS_LVL-01")) {
                    boss_damage = (Integer.parseInt(boss_lvl_data[2]));
                    setNotifsVisibilityWithTimer(true, 2000, "Boss Activates Attack Skill!");
                } else {
                    boss_damage = (Integer.parseInt(boss_lvl_data[2]));
                    setNotifsVisibilityWithTimer(true, 2000, "Boss Activates Attack Skill!");
                }
                break;
            case 2: // boss defense skill
                if (boss_level.equals("BOSS_LVL-02")) { // returns 20% damage of user to user
                    boss_reflect_damage = (float) (damage_float * 0.2); // damage_float is the prev damage of the player
                    player_health -= boss_reflect_damage;
                    boss_damage = 0;
                    setNotifsVisibilityWithTimer(true, 2000, "Boss Activates Defense Skill, reflects 20% Player Damage!");

                } else if (boss_level.equals("BOSS_LVL-03")) { // returns user's damage back to user
                    player_health -= damage_float;
                    boss_damage = 0;
                    setNotifsVisibilityWithTimer(true, 2000, "Boss Activates Defense Skill, reflects Player Damage!");

                } else if (boss_level.equals("BOSS_LVL-01")) {
                    System.out.println("boss lvl 1 here");
                    boss_damage = (Integer.parseInt(boss_lvl_data[2]));
                    setNotifsVisibilityWithTimer(true, 2000, "Boss Activates Defense Skill!");
                }
                break;
            case 3: // boss supp effect
                if (boss_level.equals("BOSS_LVL-02")) { // reduce player's damage
                    boss2_skill3_reduce_damage = true;
                    boss_damage = 0;
                    setNotifsVisibilityWithTimer(true, 2000, "Boss Activates Support Skill, reduces Player Damage!");

                } else if (boss_level.equals("BOSS_LVL-03")) { // returns user's damage back to user
                    System.out.println("returns 25% of user's damage back to user");
                    boss_damage = (float) ((Integer.parseInt(boss_lvl_data[2])) * 1.25);
                    setNotifsVisibilityWithTimer(true, 2000, "Boss Activates Support Skill, returns 25% Player Damage!");
                } else if (boss_level.equals("BOSS_LVL-01")) {
                    boss_damage = (Integer.parseInt(boss_lvl_data[2]));
                    setNotifsVisibilityWithTimer(true, 2000, "Boss Activates Support Skill!");
                }
                break;
            // Add more cases for additional boss skills
            default:
                System.out.println("Invalid skill");
                break;
        }
        if (weakness_potion_activated) {
            boss_damage = (float) (Integer.parseInt(boss_lvl_data[2]) * 0.5);
            weakness_potion_activated = false;
        } else {
            System.out.println("No weakness activated");
        }
        pets_skills();
        if (earth_golem_activated)
        {
            boss_damage = (float) (boss_damage * 0.9);
        }
        return boss_damage; // Placeholder value, replace it with your actual logic
    }
    private void startAnimation(ImageIcon[] imagePaths) {

        // Set up a timer for the animation
        Timer animationTimer = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Check if the animation is complete
                if (currentFrame < imagePaths.length) {
                    // Update the player_label's icon with the next frame of the animation
                    player_character_label.setIcon(imagePaths[currentFrame]);

                    // Increment the animation index
                    currentFrame++;
                } else {
                    // Stop the animation when it is complete
                    ((Timer) e.getSource()).stop();
                    currentFrame = 0;
                    // Reset the player_label's icon to the default state after the animation is complete
                    player_character_label.setIcon(imagePaths[0]);
                }
            }
        });

        // Start the animation timer
        animationTimer.start();
    }
}

