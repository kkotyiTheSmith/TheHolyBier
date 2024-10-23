import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import javax.swing.*;

/*
 * The Holy Bier game developed by Kornél and Javier.
 * The current file Controller.java is in charge of handling the the GUI, changing scenes.
 * Scenes are represented as functions in class Controller. These scenes make a chain that
 * esentially is the game itself. The chain starts at startUp() and always ends with youDied().
 * This means the game is over, the player is dead.
 * This is the only start and end that is possible. (Note: closing the game doesen't bring up
 * the youDied scene).
 * 
 */

class Controller {
    JFrame holyBierGame = new JFrame("The Holy Bier");//The frame with the game, this is permanent.
    Color basicBackground = new Color(197, 165, 94); 
    Player player = new Player();
    Boss boss;
    boolean youDied = false;
    static int level; // Keeps track of the levels of the game.
    static int numOfBoss; // Keeps track of the bosses killed for the death screen and bonus.
    static int numOfItems; // Keeps track of the healing items taken for the death screen.
    static int numOfHeals; // Keeps track of the healing items taken for the death screen and bonus.

    static int getLevel() {
        return level;
    }

    /*
     * Used when entering a new scene.
     */
    void reload() {
    
        holyBierGame.getContentPane().removeAll();
        holyBierGame.revalidate();
        holyBierGame.repaint();
    }
    
    /*
     * The game starts with the main screen, represented by startUp().
     * On button press we continue to the next screen, to the intro.
     */
    void startUp() {
        holyBierGame.setSize(1000, 700);
        holyBierGame.setLayout(null);
        holyBierGame.setResizable(false);

        JPanel panel = new JPanel();
        panel.setBounds(0, 0, holyBierGame.getWidth(), holyBierGame.getHeight());
        panel.setBackground(basicBackground);
        panel.setLayout(null);

        JLabel theHolyBierTitle = new JLabel("<html>The <span style='color: yellow;'>Holy</span> Bier</html>");
        theHolyBierTitle.setBounds(80, 100, 1000, 200);
        theHolyBierTitle.setBackground(Color.BLACK);
        theHolyBierTitle.setFont(new Font("Press Start 2P", Font.BOLD, 60));

        JLabel start = new JLabel("Press the mouse to start");
        start.setBounds(80, 500, 1000, 200);
        start.setBackground(Color.BLACK);
        start.setFont(new Font("Press Start 2P", Font.PLAIN, 20));
        
        panel.add(theHolyBierTitle);
        panel.add(start);

        panel.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                reload();
                startNewGame(); 
            }
        });
        
        holyBierGame.add(panel);
        holyBierGame.setVisible(true);
        holyBierGame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /*
     * Some introduction to new players.
     * Note: <br><br> is an intended double line break everywhere when used.
     */
    void startNewGame() { 
        JLabel intro = new JLabel(
            "<html>Welcome Warrior to our RPG game called The Holy Bier!"+
            "<br><br>In this game you will fight bosses, find new items and drink delicious drinks!" +
            "<br><br>Enjoy!" + 
            "</html>"
        );
        textScreen(intro);
    }

    /*
     * Displays a JLabel. Used for several purposes.
     * There is a special case, when youDied is true, then the game is over, so we dispose
     * if the game is over.
     */
    void textScreen(JLabel l) {
        JPanel panel = new JPanel();
        panel.setBounds(0, 0, holyBierGame.getWidth(), holyBierGame.getHeight());
        panel.setBackground(basicBackground);
        panel.setLayout(null);

        l.setBounds(50, 50, 900, 600);
        l.setBackground(Color.BLACK);
        l.setFont(new Font("Press Start 2P", Font.CENTER_BASELINE, 25));

        panel.add(l);
        holyBierGame.add(panel);

        panel.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                if (!youDied) {
                    reload();
                    PathChose();
                } else {
                    reload();
                    holyBierGame.dispose();
                }
                
            }
        });
    }


    /*
     * This is the scene where we chose paths, represented by 3 buttons. Pressing the button 
     * starts the corresponding scene. 
     */
    void PathChose() { 
        JPanel panel = new JPanel();
        panel.setBounds(0, 0, holyBierGame.getWidth(), holyBierGame.getHeight());
        panel.setBackground(basicBackground);
        panel.setLayout(null);
        level = level + 1; // Everytime the player acceses the chosePath() screen, the floor level increases.

        JLabel stats = new JLabel(
            "<html>Level: " + player.getLevel() +
            "<br>Hp: <span style='color: green;'>" + player.getHealth() + "</span>" + "/" + player.getMaxHp() + 
            "<br>Armor: " + player.getMaxArmor() +
            "<br>Damage: " + player.getDamage() +
            "<br>Xp: " + player.getCurrXp() + "/" + player.getNextLevel() +
            "<br>Gold: " + player.getGold() +
            "</html>"
        );
        stats.setBounds(
            holyBierGame.getWidth()/20, 
            holyBierGame.getHeight() * 1/3,
            holyBierGame.getHeight()/2,
            holyBierGame.getWidth()/5
        );
        stats.setFont(new Font("Press Start 2P", Font.PLAIN, 25));

        int buttonWidth = holyBierGame.getWidth()/3;
        int buttonHeight = holyBierGame.getHeight()/3 - holyBierGame.getHeight()/30;
        int buttonX = holyBierGame.getWidth() * 2/3;

        Item newItem = generateNewItem(); // Generating the items that can be obtained.
        Heal newHeal = new EmptyBottle().generateNewHeal();

        JButton itemRoom = new JButton("<html>Item room\n Cost: <span style='color: green;'>" + newItem.getCost() + "</span></html>");
        itemRoom.setBounds(buttonX, holyBierGame.getHeight()/70, buttonWidth, buttonHeight);
        itemRoom.setFont(new Font("Press Start 2P", Font.PLAIN, 25));
        JButton bossRoom = new JButton("Boss room");
        bossRoom.setBounds(buttonX, 2 *holyBierGame.getHeight()/70 + buttonHeight, buttonWidth, buttonHeight);
        bossRoom.setFont(new Font("Press Start 2P", Font.PLAIN, 25));
        JButton pubRoom = new JButton("<html>The Pub!\nCost: <span style='color: green;'>" + newHeal.getCost() + "</span></html>");
        pubRoom.setBounds(buttonX, 3 * holyBierGame.getHeight()/70 + 2 * buttonHeight, buttonWidth, buttonHeight);
        pubRoom.setFont(new Font("Press Start 2P", Font.PLAIN, 25));

        // every 25 floors there is a special stronger boss which can't be skipped nor escaped.
        if(!((level % 25) == 0)){
            panel.add(itemRoom);
            panel.add(pubRoom);
        }

        panel.add(bossRoom);
        panel.add(stats);
        
        holyBierGame.add(panel);
        
        itemRoom.addActionListener((ActionEvent e) -> {
            reload();
            if (player.decreaseGold(newItem.getCost())) {
                itemRoomScene(newItem);
                // Whenever the itemRoomScene is accesed, increase the amount of items picked by one
                numOfItems = numOfItems + 1; 
            } else {
                /* 
                 * As floor level increases each time pathChose() runs,
                 * but a floor hasn't beed cleared, so the level count has to be corrected
                 */
                level = level - 1; 
                JLabel noGold = new JLabel(
                    "Not enough gold!" 
                );
                textScreen(noGold);
            }
        });
        
        bossRoom.addActionListener((ActionEvent e) -> {
            reload();
            boss = new Boss(); 
            bossRoomScene();
        });
        
        pubRoom.addActionListener((ActionEvent e) -> {
            reload();
            if (player.decreaseGold(newHeal.getCost())) {
                thePub(newHeal);
                numOfHeals = numOfHeals + 1;
            } else {
                level  = level - 1;
                JLabel noGold = new JLabel(
                    "Not enough gold!"
                );
                textScreen(noGold);
            }
        });
    }


    /*
     * This is the item change scene. The new item is generated before the call that we pass as a
     * parameter. We can change our items by pressing one of the buttons.
     */
    void itemRoomScene(Item newI) { 
        JPanel panel = new JPanel();
        panel.setBounds(0, 0, holyBierGame.getWidth(), holyBierGame.getHeight());
        panel.setBackground(basicBackground);
        panel.setLayout(null);

        JLabel newILabel = new JLabel("You found a new item!");
        newILabel.setFont(new Font("Press Start 2P", Font.PLAIN, 40));
        newILabel.setBounds(80, 50, 1000, 100);

        JLabel changeTo = new JLabel("You can change to one of your items :");
        changeTo.setFont(new Font("Press Start 2P", Font.PLAIN, 19));
        changeTo.setBounds(80, 300, 1000, 100);

        JLabel itemStats = new JLabel(
            "<html>Name: " + newI.getName() +
            "<br>Level: " + newI.getLevel() +
            "<br>Damage: <span style='color: red;'>" + newI.getDamage() + "</span>" +
            "<br>Hp: <span style='color: green;'>" + newI.getHp() + "</span>" +
            "<br>Armor: " + newI.getArmor() +
            "</html>"
        );
        itemStats.setFont(new Font("Press Start 2P", Font.PLAIN, 24));
        itemStats.setBounds(80, 150, 1000, 200);

        int buttonWidth = holyBierGame.getWidth()/3 - holyBierGame.getHeight()/30;
        int buttonHeight = holyBierGame.getHeight()/3;
        int buttonY = holyBierGame.getHeight() * 3/5;

        JButton choice1 = new JButton(
            "<html>Name: " + player.getItem(0).getName() +
            "<br>Level: " + player.getItem(0).getLevel() +
            "<br>Damage: <span style='color: red;'>" + player.getItem(0).getDamage() + "</span>" +
            "<br>Hp: <span style='color: green;'>" + player.getItem(0).getHp() + "</span>" +
            "<br>Armor: " + player.getItem(0).getArmor() +
            "</html>"
        );
        choice1.setBounds(holyBierGame.getWidth()/70, buttonY, buttonWidth, buttonHeight);
        choice1.setFont(new Font("Press Start 2P", Font.PLAIN, 19));
        JButton choice2 = new JButton(
            "<html>Name: " + player.getItem(1).getName() +
            "<br>Level: " + player.getItem(1).getLevel() +
            "<br>Damage: <span style='color: red;'>" + player.getItem(1).getDamage() + "</span>" +
            "<br>Hp: <span style='color: green;'>" + player.getItem(1).getHp() + "</span>" +
            "<br>Armor: " + player.getItem(1).getArmor() +
            "</html>"
        );
        choice2.setBounds(2 * holyBierGame.getWidth()/70 + buttonWidth, buttonY, buttonWidth, buttonHeight);
        choice2.setFont(new Font("Press Start 2P", Font.PLAIN, 19));
        JButton choice3 = new JButton(
            "<html>Name: " + player.getItem(2).getName() +
            "<br>Level: " + player.getItem(2).getLevel() +
            "<br>Damage: <span style='color: red;'>" + player.getItem(2).getDamage() + "</span>" +
            "<br>Hp: <span style='color: green;'>" + player.getItem(2).getHp() + "</span>" +
            "<br>Armor: " + player.getItem(2).getArmor() +
            "</html>"
        );
        choice3.setBounds(3 * holyBierGame.getWidth()/70 + 2 * buttonWidth, buttonY, buttonWidth, buttonHeight);
        choice3.setFont(new Font("Press Start 2P", Font.PLAIN, 19));

        panel.add(itemStats);
        panel.add(choice1);
        panel.add(choice2);
        panel.add(choice3);
        panel.add(changeTo);
        panel.add(newILabel);

        holyBierGame.add(panel);

        choice1.addActionListener((ActionEvent e) -> {
            player.changeItem(0, newI);
            // The player max/total Hp and Armor has to be recalculated as a different object has been adquired.
            player.setMaxHp();
            player.setMaxArmor();
            reload();
            PathChose();
        });
        
        choice2.addActionListener((ActionEvent e) -> {
            player.changeItem(1, newI);
            // The player max/total Hp and Armor has to be recalculated as a different object has been adquired.
            player.setMaxHp();
            player.setMaxArmor();
            reload();
            PathChose();
        });
        
        choice3.addActionListener((ActionEvent e) -> {
            player.changeItem(2, newI);
            // The player max/total Hp and Armor has to be recalculated as a different object has been adquired.
            player.setMaxHp();
            player.setMaxArmor();
            reload();
            PathChose();
        });
    }

    /*
     * The boss fight scene. We have 3 options, these are the buttons. The buttons call the
     * corresponding event.
     */
    void bossRoomScene() { 
        JPanel panel = new JPanel();
        panel.setBounds(0, 0, holyBierGame.getWidth(), holyBierGame.getHeight());
        panel.setBackground(basicBackground);
        panel.setLayout(null);

        int buttonWidth = holyBierGame.getWidth()/3 - holyBierGame.getHeight()/30;
        int buttonHeight = holyBierGame.getHeight()/3;
        int buttonY = holyBierGame.getHeight() * 3/5;

        JButton fight = new JButton("Fight");
        fight.setBounds(holyBierGame.getWidth()/70, buttonY, buttonWidth, buttonHeight);
        fight.setFont(new Font("Press Start 2P", Font.PLAIN, 25));
        JButton heal = new JButton("Heal");
        heal.setBounds(2 * holyBierGame.getWidth()/70 + buttonWidth, buttonY, buttonWidth, buttonHeight);
        heal.setFont(new Font("Press Start 2P", Font.PLAIN, 25));
        JButton escape = new JButton("Escape");
        escape.setBounds(3 * holyBierGame.getWidth()/70 + 2 * buttonWidth, buttonY, buttonWidth, buttonHeight);
        escape.setFont(new Font("Press Start 2P", Font.PLAIN, 25));

        JLabel statsPlayer = new JLabel(
            "<html>Player stats: " +
            "Level: " + player.getLevel() +
            "<br>Hp: <span style='color: green;'>" + player.getHealth() + "</span>" + "/" + player.getMaxHp() + 
            "<br>Armor: " + player.getMaxArmor() +
            "<br>Damage: <span style='color: red;'>" + player.getDamage() + "</span>" +
            "</html>"
        );
        statsPlayer.setBounds(
            holyBierGame.getWidth() * 1/20, 
            holyBierGame.getHeight() * 1/5, 
            holyBierGame.getHeight()/2, 
            holyBierGame.getWidth()/5
        );
        statsPlayer.setFont(new Font("Press Start 2P", Font.PLAIN, 25));

        JLabel statsBoss = new JLabel(
            "<html>Boss stats: " +
            "<br>Level: " + boss.getLevel() +
            "<br>Hp: <span style='color: green;'>" + boss.getHealth() + "</span>" + "/" + boss.getHp() +
            "<br>Damage: <span style='color: red;'>" + boss.getCurrentDamage() + "</span>" +
            "</html>"
        );
        statsBoss.setBounds(
            holyBierGame.getWidth() * 11/20, 
            holyBierGame.getHeight() * 1/5, 
            holyBierGame.getHeight()/2, 
            holyBierGame.getWidth()/5);
        statsBoss.setFont(new Font("Press Start 2P", Font.PLAIN, 25));

        panel.add(statsBoss);
        panel.add(statsPlayer);
        panel.add(fight);
        panel.add(heal);

        // every 25 floors there is a special stronger boss which can't be skipped nor escaped.
        if(!(level % 25 == 0)){
            panel.add(escape);
        }
        
        holyBierGame.add(panel);

        fight.addActionListener((ActionEvent e) -> {
            reload();
            choseItem(); // We can chose the item to fight with
        });

        heal.addActionListener((ActionEvent e) -> {
            player.increaseHealth(player.getMaxHp());

            if (player.getHealth() == 0) {
                reload();
                youDied();
            } else {
                reload();
                bossRoomScene();
            }
        });

        escape.addActionListener((ActionEvent e) -> {
            dealBossDamageToPlayer();
            if (player.getHealth() == 0) {
                reload();
                youDied();
            } else {
                reload();
                PathChose();
            }
        });
    }

    /*
     * This method checks if the boss is dead. If yes the player is rewarded.
     */
    void checkBossDeath() { 
        if (boss.getHealth() == 0) {
            reload();
            JLabel bossKilled = new JLabel(
            "<html>You killed the boss! Congrats!" +
            "<br><br>Your reward:" +    
            "<br><br>Experience: <span style='color: blue;'>" + boss.getXp() + "</span>,    Gold: <span style='color: yellow;'>" + boss.getGold() + "</span>" +
            "</html>"
            );

            player.playerReward(boss.getXp(),boss.getGold());
            // Since the stats of the player have increased, the max/ total hp and armor has  to be recalculated.
            player.setMaxHp();
            player.setMaxArmor();
            numOfBoss = numOfBoss + 1;//Whenever the boss is actually killed, increase the boss counter by 1.
            textScreen(bossKilled);

        } else {
            dealBossDamageToPlayer(); //Boss deals damage to player under conditions.
            reload();
            if (player.getHealth() == 0) {
                youDied();
            } else {
                bossRoomScene();
            }
        }
    }

    /*
     * This used when we fight the boss. Called when we chose the fight button in a boss fight.
     * After attacking we check if the boss is dead.
     */
    void choseItem() { 
        JPanel panel = new JPanel();
        panel.setBounds(0, 0, holyBierGame.getWidth(), holyBierGame.getHeight());
        panel.setBackground(basicBackground);
        panel.setLayout(null);

        int buttonWidth = holyBierGame.getWidth()/3 - holyBierGame.getHeight()/30;
        int buttonHeight = holyBierGame.getHeight()/3;
        int buttonY = holyBierGame.getHeight() * 3/5;

        JButton choice1 = new JButton(
            "<html>Name: " + player.getItem(0).getName() +
            ",  Level: " + player.getItem(0).getLevel() +
            " <br>Damage: " + player.getItem(0).getDamage() +
            "</html>"
        );
        choice1.setBounds(holyBierGame.getWidth()/70, buttonY, buttonWidth, buttonHeight);
        choice1.setFont(new Font("Press Start 2P", Font.PLAIN, 25));

        JButton choice2 = new JButton(
            "<html>Name: " + player.getItem(1).getName() +
            ",  Level: " + player.getItem(1).getLevel() +
            " <br>Damage: " + player.getItem(1).getDamage() +
            "</html>"
        );
        choice2.setBounds(2 * holyBierGame.getWidth()/70 + buttonWidth, buttonY, buttonWidth, buttonHeight);
        choice2.setFont(new Font("Press Start 2P", Font.PLAIN, 25));
        
        JButton choice3 = new JButton(
            "<html>Name: " + player.getItem(2).getName() +
            ",  Level: " + player.getItem(2).getLevel() +
            " <br>Damage: " + player.getItem(2).getDamage() +
            "</html>"
        );
        choice3.setBounds(3 * holyBierGame.getWidth()/70 + 2 * buttonWidth, buttonY, buttonWidth, buttonHeight);
        choice3.setFont(new Font("Press Start 2P", Font.PLAIN, 25));

        JLabel itemToUse = new JLabel("Chose your item to use!");
        itemToUse.setFont(new Font("Press Start 2P", Font.PLAIN, 19));
        itemToUse.setBounds(80, 300, 1000, 100);

        JLabel statsPlayer = new JLabel(
            "<html>Player stats: " +
            "Level: " + player.getLevel() +
            "<br>Hp: <span style='color: green;'>" + player.getHealth() + "</span>" + "/" + player.getMaxHp() +
            "<br>Armor: " + player.getMaxArmor() +
            "<br>Damage: <span style='color: red;'>" + player.getDamage() +"</span>" +
            "</html>"
        );
        statsPlayer.setBounds(
            holyBierGame.getWidth() * 1/20, 
            holyBierGame.getHeight() * 1/5, 
            holyBierGame.getHeight()/2, 
            holyBierGame.getWidth()/5
        );
        statsPlayer.setFont(new Font("Press Start 2P", Font.PLAIN, 25));

        JLabel statsBoss = new JLabel(
            "<html>Boss stats: " +
            "<br>Level: " + boss.getLevel() +
            "<br>Hp: <span style='color: green;'>" + boss.getHealth() + "</span>" + "/" + boss.getHp() +
            "<br>Damage: <span style='color: red;'>" + boss.getCurrentDamage() + "</span>" +
            "</html>"
        );
        statsBoss.setBounds(
            holyBierGame.getWidth() * 11/20, 
            holyBierGame.getHeight() * 1/5, 
            holyBierGame.getHeight()/2, 
            holyBierGame.getWidth()/5
        );
        statsBoss.setFont(new Font("Press Start 2P", Font.PLAIN, 25));

        panel.add(choice1);
        panel.add(choice2);
        panel.add(choice3);
        panel.add(statsPlayer);
        panel.add(statsBoss);
        panel.add(itemToUse);

        holyBierGame.add(panel);

        choice1.addActionListener((ActionEvent e) -> {
            player.dealDamageTo(player.getCurrentDamage(player.getItem(0),boss), boss);
            checkBossDeath();
        });
        
        choice2.addActionListener((ActionEvent e) -> {
            player.dealDamageTo(player.getCurrentDamage(player.getItem(1),boss), boss);
            checkBossDeath();
        });
        
        choice3.addActionListener((ActionEvent e) -> {
            player.dealDamageTo(player.getCurrentDamage(player.getItem(2),boss), boss);
            checkBossDeath();
        });
    }

    /*
     * The healing item chose screen. Works really similar to item chose screen.
     */
    void thePub(Heal newH) { 
        JPanel panel = new JPanel();
        panel.setBounds(0, 0, holyBierGame.getWidth(), holyBierGame.getHeight());
        panel.setBackground(basicBackground);
        panel.setLayout(null);

        JLabel newHLabel = new JLabel("<html>The barkeeper offers<br> you a pint! </html>");
        newHLabel.setFont(new Font("Press Start 2P", Font.PLAIN, 35));
        newHLabel.setBounds(80, 50, 1000, 100);

        JLabel changeTo = new JLabel("You can change to one of your bottles :");
        changeTo.setFont(new Font("Press Start 2P", Font.PLAIN, 19));
        changeTo.setBounds(80, 300, 1000, 100);

        JLabel itemStats = new JLabel(
            "<html>Name: " + newH.getName() +
            "<br>Heal amount: <span style='color: green;'>" + (newH.getHealPercentage()) + "% </span>" + 
            "</html>"
        );
        itemStats.setFont(new Font("Press Start 2P", Font.PLAIN, 24));
        itemStats.setBounds(80, 200, 1000, 100);

        int buttonWidth = holyBierGame.getWidth()/3 - holyBierGame.getHeight()/30;
        int buttonHeight = holyBierGame.getHeight()/3;
        int buttonY = holyBierGame.getHeight() * 3/5;

        JButton choice1 = new JButton(player.getHeal(0).getName());
        choice1.setBounds(holyBierGame.getWidth()/70, buttonY, buttonWidth, buttonHeight);
        choice1.setFont(new Font("Press Start 2P", Font.PLAIN, 22));
        JButton choice2 = new JButton(player.getHeal(1).getName());
        choice2.setBounds(2 * holyBierGame.getWidth()/70 + buttonWidth, buttonY, buttonWidth, buttonHeight);
        choice2.setFont(new Font("Press Start 2P", Font.PLAIN, 22));
        JButton choice3 = new JButton(player.getHeal(2).getName());
        choice3.setBounds(3 * holyBierGame.getWidth()/70 + 2 * buttonWidth, buttonY, buttonWidth, buttonHeight);
        choice3.setFont(new Font("Press Start 2P", Font.PLAIN, 22));

        panel.add(itemStats);
        panel.add(choice1);
        panel.add(choice2);
        panel.add(choice3);
        panel.add(changeTo);
        panel.add(newHLabel);

        holyBierGame.add(panel);

        choice1.addActionListener((ActionEvent e) -> {
            player.changeHeal(0, newH);
            reload();
            PathChose();
        });
        
        choice2.addActionListener((ActionEvent e) -> {
            player.changeHeal(1, newH);
            reload();
            PathChose();
        });
        
        choice3.addActionListener((ActionEvent e) -> {
            player.changeHeal(2, newH);
            reload();
            PathChose();
        });

    }

    /*
     * Ending and displaying the stats of the game.
    */
    void youDied() { 
        youDied = true;
        JLabel youDiedL = new JLabel(
            "<html><span style='color: red;'>You died! :(</span>" +
            "<br>" +
            "<br> Bosses killed: " + numOfBoss + " (+13)" +
            "<br> Items picked: " + numOfItems + " (0)" +
            "<br> Heals obtained: " + numOfHeals + " (-3)" +
            "<br>" +
            "<br> <span style='color: yellow;'>Score: " + bonusCalc() + "</span>" +
            "</html>"
        );
        reload();
        textScreen(youDiedL);
    }

    /*
     * This gives back a random item. Used befor the item room.
     */
    Item generateNewItem() {
        switch(new Random().nextInt(4)){
            case 0 -> { 
                return new Sword();
            }
            case 1 -> { 
                return new Axe();
            }
            case 2 -> { 
                return new Grenade();
            }
            case 3 -> { 
                return new Spear();
            }
        }
        return null;
    }

    /*
     * This is used to calculate a special ability of an item. If true the character doesn't get 
     * damage in a turn.
     */
    Boolean damageNullification(){
        Random rand = new Random();
        int randNum = rand.nextInt(100);

        if (player.getItem(0).damageNull || 
            player.getItem(1).damageNull || 
            player.getItem(2).damageNull) 
        {
            return randNum < 50;
        } else {
            return false;
        }
    }

    void dealBossDamageToPlayer() { // Deals damage under conditions.
        if (damageNullification()){
            boss.dealDamageTo(0, player);
            } else {
                boss.dealDamageTo(boss.getCurrentDamage(), player);
            }
    }

    long bonusCalc() { // Determining the score of the player.
        long puntuation = numOfBoss * 13 - numOfHeals * 3;
        if(puntuation < 0){
            puntuation = 0;
        }
        return puntuation;
    }

    public static void main(String[] args) {
        new Controller().startUp();
    }
}
