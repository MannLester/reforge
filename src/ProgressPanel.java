import javax.swing.*;
import java.awt.*;
import Database.TableUpdater;
import Database.read_bossdroptable;
import Database.read_table;

public class ProgressPanel extends JFrame {
    String root = "C:\\Users\\User\\Reforge2\\src\\assets\\";
    ImageIcon progressPanel = new ImageIcon(root + "CityProgress\\progressPanel.png");
    ImageIcon[] progressBars = {
            new ImageIcon(root + "CityProgress\\progressBar1.png"),
            new ImageIcon(root + "CityProgress\\progressBar2.png"),
            new ImageIcon(root + "CityProgress\\progressBar2.png"),
            new ImageIcon(root + "CityProgress\\progressBar2.png"),
            new ImageIcon(root + "CityProgress\\progressBar3.png"),
    };
    int x_bar = 130;
    JLabel[] progressContainers = new JLabel[progressBars.length];
    int width = 1105;
    int height = 750;
    JLabel progress_bg, progress_name;
    JButton progress_button;
    String progressName;
    read_table readtable = new read_table();
    read_bossdroptable readt = new read_bossdroptable();
    TableUpdater tableupdate = new TableUpdater();
    String[] currency_table_columns = {"CURRENCY.CURRENCY_ID","GOLD_COUNT","IRON_COUNT","HERB_COUNT","BISCUIT_COUNT","DIAMOND_COUNT"};
    String[] player_table_columns = {"ACCOUNT_ID","WEAPONS_FORGED","PLAYER_HP","PLAYER_DMG","PET_OWNED","BOSSES_KILLED","PETS_TAMED","PLAYERS.CURRENCY_ID","PLAYERS.PROGRESS_COMPLETED"};
    String[] player_data, currency_data = new String[6];
    String currency_id;
    int player_gold_amount;
    public String[] data_table(String url, String[] table_to_get, String table, String identifier){
        String[] data_from_table = new String[table_to_get.length];
        for(int i=0; i<table_to_get.length; i++){
            data_from_table[i] = readtable.readValue(table,identifier,url,table_to_get[i]);
        }
        return data_from_table;
    }
    public String[] data_jointable(String boss_identifier, String[] info_to_get, String table1, String table2, String identifer, String limit){
        String[] name = readt.readjoindropValues(boss_identifier, info_to_get, table1, table2,identifer,limit);
        return name;
    }
    String[] city_progress_table_columns = {"CITY_ID","PROGRESS_NAME","PROGRESS_CURRENT","PROGRESS_LIMIT", "IS_FINISHED"};
    int gold_amt_toupdate;
    ProgressPanel(String progressName){
        String[] progress_data = data_table(progressName, city_progress_table_columns,"CITY_PROGRESS","PROGRESS_NAME");
        this.progressName = progressName;
        this.setSize(width, height);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setTitle("City Progress");
        this.setVisible(true);

        player_data = data_table("ACC-1", player_table_columns, "PLAYERS", "ACCOUNT_ID");
        currency_id = player_data[7];
        currency_data = data_jointable(currency_id, currency_table_columns, "PLAYERS", "CURRENCY", "CURRENCY_ID", "");
        player_gold_amount = Integer.parseInt(currency_data[1]);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (dim.width - getWidth()) / 2;
        int y = (dim.height - getHeight()) / 2;

        setLocation(x, y);

        setLayout(null);

        progress_bg = new JLabel();
        progress_bg.setIcon(progressPanel);
        progress_bg.setBounds(0,0,width,height);

        progress_name = new JLabel(this.progressName);
        progress_name.setOpaque(true);
        progress_name.setForeground(Color.BLACK);
        progress_name.setHorizontalAlignment(JLabel.CENTER);
        progress_name.setFont(new Font("Arial", Font.BOLD, 40));
        progress_name.setBounds(150,150,800,100);
        int max_bars = Integer.parseInt(progress_data[3]);
        int num_of_bars = (Integer.parseInt(progress_data[2]) * 5)/ max_bars;

        for (int i = 0; i < num_of_bars; i++){
            progressContainers[i] = new JLabel();
            progressContainers[i].setIcon(progressBars [i]);
            progressContainers[i].setBounds(x_bar,410,150,86);
            x_bar += 175;
            this.add(progressContainers[i]);
        }

        progress_button = new JButton();
        progress_button.setBounds(400,560,300,150);
        progress_button.setContentAreaFilled(false);
        progress_button.setBorderPainted(false);
        progress_button.setOpaque(false);
        gold_amt_toupdate = player_gold_amount - 1000;

        String[] finalProgress_data = progress_data;
        progress_button.addActionListener(e ->{
            if(player_gold_amount < 1000){
                SwingUtilities.invokeLater(()->new GameNotif("Not enough Gold!"));
            }else{
                tableupdate.updateTable("CURRENCY", "GOLD_COUNT", "CURRENCY_ID", currency_id, gold_amt_toupdate);
                tableupdate.updateTable("CITY_PROGRESS", "PROGRESS_CURRENT", "PROGRESS_NAME",progressName, Integer.parseInt(progress_data[2]) + 1);
                player_data = data_table("ACC-1",player_table_columns, "PLAYERS", "ACCOUNT_ID");
                SwingUtilities.invokeLater(()->new GameNotif("Successful Transaction!"));
                SwingUtilities.invokeLater(ProgressPanel.this::dispose);
            }
        });

        finalProgress_data = data_table(progressName,city_progress_table_columns,"CITY_PROGRESS","PROGRESS_NAME");
        System.out.println(((Integer.parseInt(progress_data[2]) * 5)/ max_bars));
        if (((Integer.parseInt(finalProgress_data[2]) * 5)/ max_bars) == 5) {
            System.out.println("inside check if");
            int newPlayerHP = (int) (Integer.parseInt(player_data[2]) + (Integer.parseInt(player_data[2]) * 0.05));
            int newPlayerDMG = (int) (Integer.parseInt(player_data[3]) + (Integer.parseInt(player_data[3]) * 0.05));

            tableupdate.updateTable("PLAYERS", "PLAYER_HP", "CURRENCY_ID", currency_id, newPlayerHP);
            tableupdate.updateTable("PLAYERS", "PLAYER_DMG", "CURRENCY_ID", currency_id, newPlayerDMG);
            tableupdate.updateTable("PLAYERS", "PROGRESS_COMPLETED", "CURRENCY_ID", currency_id, Integer.parseInt(player_data[8]) + 1);

            // Update player_data after the changes
            player_data[2] = String.valueOf(newPlayerHP);
            player_data[3] = String.valueOf(newPlayerDMG);
        }

        this.add(progress_button);
        this.add(progress_name);
        this.add(progress_bg);
    }
}
