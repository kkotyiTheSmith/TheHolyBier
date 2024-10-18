import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import javax.swing.*;

/*
 * The Holy Bier game developed by Kornél and Javier.
 * The current file Controller.java is in charge of handling the the GUI, changing scenes.
 * Scenes are represented as functions in class Controller. These scenes make a chain that
 * esentially is the game itself. The chain starts at startUp() and always ends with youDied().
 * This is the only start and end that is possible. (Note: closing the game doesen't bring up
 * the youDied() scene).
 * 
 */

class Controller {
    JFrame holyBierGame = new JFrame("The Holy Bier"); // The frame with the game, this is permanent.
    Color basicBackground = new Color(197, 165, 94); 
    Player player = new Player();
    Boss boss;
    boolean youDied = false;

    void reload() { // Used when entering a new scene.
        holyBierGame.getContentPane().removeAll();
        holyBierGame.revalidate();
        holyBierGame.repaint();
    }
    
    void startUp() { // The game starts with the main screen, represented by startUp().
        holyBierGame.setSize(1000, 700);
        holyBierGame.setLayout(null);
        holyBierGame.setResizable(false);

        JPanel panel = new JPanel();
        panel.setBounds(0, 0, holyBierGame.getWidth(), holyBierGame.getHeight());
        panel.setBackground(basicBackground);
        panel.setLayout(null);

        JLabel theHolyBierTitle = new JLabel("The Holy Bier");
        theHolyBierTitle.setBounds(80, 100, 1000, 200);
        theHolyBierTitle.setBackground(Color.BLACK);
        theHolyBierTitle.setFont(new Font("Press Start 2P", Font.BOLD, 60));

        JLabel start = new JLabel("Press the mouse to start");
        start.setBounds(80, 500, 1000, 200);
        start.setBackground(Color.BLACK);
        start.setFont(new Font("Press Start 2P", Font.PLAIN, 20));
        
        /* // TODO delete in case of handling
        ImagePanel newGameScreen = new ImagePanel("graphics/MainScreen/background.jpg");
        newGameScreen.setBounds(0, 0, holyBierGame.getWidth(), holyBierGame.getHeight());
        panel.add(newGameScreen);
        */
        
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

    void startNewGame() { // Some story and help, to introduce to new players.
        JLabel intro = new JLabel(
            "<html>This is a RPG game. Buttons and shit my friend. But don't worry it's not skill based. Enjoy!<html>"
        );
        textScreen(intro);
    }

    void textScreen(JLabel l) { // Displays a JLabel. Used for several purposes.
        JPanel panel = new JPanel();
        panel.setBounds(0, 0, holyBierGame.getWidth(), holyBierGame.getHeight());
        panel.setBackground(basicBackground);
        panel.setLayout(null);

        l.setBounds(50, 50, 800, 500);
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

    void PathChose() { // We can chose between 3 paths all the time. New item, bossfight and pub.
        JPanel panel = new JPanel();
        panel.setBounds(0, 0, holyBierGame.getWidth(), holyBierGame.getHeight());
        panel.setBackground(basicBackground);
        panel.setLayout(null);

        JLabel stats = new JLabel(
            "<html>Level: " + player.getLevel() +
            "<br>Healt: " + player.getHealth() + "/" + player.getMaxHp() +
            "<br>Damage: " + player.getCurrentDamage() +
            "<br>Gold: " + player.getGold() +
            "<html>"
        );
        stats.setBounds(holyBierGame.getWidth()/20, holyBierGame.getHeight() * 2/3, holyBierGame.getHeight()/2, holyBierGame.getWidth()/5);
        stats.setFont(new Font("Press Start 2P", Font.PLAIN, 25));

        int buttonWidth = holyBierGame.getWidth()/3;
        int buttonHeight = holyBierGame.getHeight()/3 - holyBierGame.getHeight()/30;
        int buttonX = holyBierGame.getWidth() * 2/3;

        Item newItem = generateNewItem(); // Generating the items that can be obtained.
        Heal newHeal = new EmptyBottle().generateNewHeal();

        JButton itemRoom = new JButton("<html>Item room\n Cost: " + newItem.getCost() + "<html>");
        itemRoom.setBounds(buttonX, holyBierGame.getHeight()/70, buttonWidth, buttonHeight);
        itemRoom.setFont(new Font("Press Start 2P", Font.PLAIN, 25));
        JButton bossRoom = new JButton("Boss room");
        bossRoom.setBounds(buttonX, 2 *holyBierGame.getHeight()/70 + buttonHeight, buttonWidth, buttonHeight);
        bossRoom.setFont(new Font("Press Start 2P", Font.PLAIN, 25));
        JButton pubRoom = new JButton("<html>The Pub!\nCost: " + newHeal.getCost() + "<html>");
        pubRoom.setBounds(buttonX, 3 * holyBierGame.getHeight()/70 + 2 * buttonHeight, buttonWidth, buttonHeight);
        pubRoom.setFont(new Font("Press Start 2P", Font.PLAIN, 25));

        panel.add(itemRoom);
        panel.add(bossRoom);
        panel.add(pubRoom);
        panel.add(stats);
        
        holyBierGame.add(panel);
        
        itemRoom.addActionListener((ActionEvent e) -> {
            reload();
            if (player.decreaseGold(newItem.getCost())) {
                itemRoomScene(newItem);
            } else {
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
            } else {
                JLabel noGold = new JLabel(
                    "Not enough gold!"
                );
                textScreen(noGold);
            }
        });
    }

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
            ",  Level: " + newI.getLevel() +
            "<br>Damage: " + newI.getDamage() +
            "<html>"
        );
        itemStats.setFont(new Font("Press Start 2P", Font.PLAIN, 24));
        itemStats.setBounds(80, 200, 1000, 100);

        int buttonWidth = holyBierGame.getWidth()/3 - holyBierGame.getHeight()/30;
        int buttonHeight = holyBierGame.getHeight()/3;
        int buttonY = holyBierGame.getHeight() * 3/5;

        JButton choice1 = new JButton(
            "<html>Name: " + player.getItem(0).getName() +
            ",  Level: " + player.getItem(0).getLevel() +
            "<br>Damage: " + player.getItem(0).getDamage() +
            ",  Health: " + player.getItem(0).getHp() +
            "<br>Armor: " + player.getItem(0).getArmor() +
            "<html>"
        );
        choice1.setBounds(holyBierGame.getWidth()/70, buttonY, buttonWidth, buttonHeight);
        choice1.setFont(new Font("Press Start 2P", Font.PLAIN, 19));
        JButton choice2 = new JButton(
            "<html>Name: " + player.getItem(1).getName() +
            ",  Level: " + player.getItem(1).getLevel() +
            "<br>Damage: " + player.getItem(1).getDamage() +
            ",  Health: " + player.getItem(0).getHp() +
            "<br>Armor: " + player.getItem(0).getArmor() +
            "<html>"
        );
        choice2.setBounds(2 * holyBierGame.getWidth()/70 + buttonWidth, buttonY, buttonWidth, buttonHeight);
        choice2.setFont(new Font("Press Start 2P", Font.PLAIN, 19));
        JButton choice3 = new JButton(
            "<html>Name: " + player.getItem(2).getName() +
            ",  Level: " + player.getItem(2).getLevel() +
            "<br>Damage: " + player.getItem(2).getDamage() +
            ",  Health: " + player.getItem(0).getHp() +
            "<br>Armor: " + player.getItem(0).getArmor() +
            "<html>"
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
            reload();
            PathChose();
        });
        
        choice2.addActionListener((ActionEvent e) -> {
            player.changeItem(1, newI);
            reload();
            PathChose();
        });
        
        choice3.addActionListener((ActionEvent e) -> {
            player.changeItem(2, newI);
            reload();
            PathChose();
        });
    }

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
            "<br>Healt: " + player.getHealth() + "/" + player.getMaxHp() +
            "<br>Damage: " + player.getCurrentDamage() +
            "<html>"
        );
        statsPlayer.setBounds(holyBierGame.getWidth() * 1/20, holyBierGame.getHeight() * 1/5, holyBierGame.getHeight()/2, holyBierGame.getWidth()/5);
        statsPlayer.setFont(new Font("Press Start 2P", Font.PLAIN, 25));

        JLabel statsBoss = new JLabel(
            "<html>Boss stats: " +
            "Name: " + boss.getName() +
            "Level: " + boss.getLevel() +
            "<br>Healt: " + boss.getHealth() +
            "<br>Damage: " + boss.getCurrentDamage() +
            "<html>"
        );
        statsBoss.setBounds(holyBierGame.getWidth() * 11/20, holyBierGame.getHeight() * 1/5, holyBierGame.getHeight()/2, holyBierGame.getWidth()/5);
        statsBoss.setFont(new Font("Press Start 2P", Font.PLAIN, 25));

        panel.add(statsBoss);
        panel.add(statsPlayer);
        panel.add(fight);
        panel.add(heal);
        panel.add(escape);
        
        holyBierGame.add(panel);

        fight.addActionListener((ActionEvent e) -> {
            reload();
            choseItem();
        });

        heal.addActionListener((ActionEvent e) -> {
            reload();
            player.increaseHealth();
            boss.dealDamageTo(boss.getCurrentDamage(), player);
            if (player.getHealth() == 0) {
                reload();
                youDied();
            } else {
                bossRoomScene();
            }
        });

        escape.addActionListener((ActionEvent e) -> {
            boss.dealDamageTo(boss.getCurrentDamage(), player);
            if (player.getHealth() == 0) {
                reload();
                youDied();
            } else {
                reload();
                PathChose();
            }
        });
    }

    void checkBossDeath() {
        if (boss.getHealth() == 0) {
            reload();
            JLabel bossKilled = new JLabel(
            "<html>You killed the boss! Congrats!" +
            "<br><br>Your reward:" +    
            "<br><br>Experience: " + boss.getXp() + ",    Gold: " + boss.getGold() +
            "<html>"
            );
            player.increaseGold(boss.getGold());
            player.incereaseXP(boss.getXp());
            textScreen(bossKilled); 
        } else {
            boss.dealDamageTo(boss.getCurrentDamage(), player);
            reload();
            if (player.getHealth() == 0) {
                youDied();
            } else {
                bossRoomScene();
            }
        }
    }

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
            "<br>Damage: " + player.getItem(0).getDamage() +
            "<html>"
        );
        choice1.setBounds(holyBierGame.getWidth()/70, buttonY, buttonWidth, buttonHeight);
        choice1.setFont(new Font("Press Start 2P", Font.PLAIN, 25));

        JButton choice2 = new JButton(
            "<html>Name: " + player.getItem(1).getName() +
            ",  Level: " + player.getItem(1).getLevel() +
            "<br>Damage: " + player.getItem(1).getDamage() +
            "<html>"
        );
        choice2.setBounds(2 * holyBierGame.getWidth()/70 + buttonWidth, buttonY, buttonWidth, buttonHeight);
        choice2.setFont(new Font("Press Start 2P", Font.PLAIN, 25));
        
        JButton choice3 = new JButton(
            "<html>Name: " + player.getItem(2).getName() +
            ",  Level: " + player.getItem(2).getLevel() +
            "<br>Damage: " + player.getItem(2).getDamage() +
            "<html>"
        );
        choice3.setBounds(3 * holyBierGame.getWidth()/70 + 2 * buttonWidth, buttonY, buttonWidth, buttonHeight);
        choice3.setFont(new Font("Press Start 2P", Font.PLAIN, 25));

        JLabel itemToUse = new JLabel("Chose your item to use!");
        itemToUse.setFont(new Font("Press Start 2P", Font.PLAIN, 19));
        itemToUse.setBounds(80, 300, 1000, 100);

        JLabel statsPlayer = new JLabel(
            "<html>Player stats: " +
            "Level: " + player.getLevel() +
            "<br>Healt: " + player.getHealth() +
            "<br>Damage: " + player.getCurrentDamage() +
            "<html>"
        );
        statsPlayer.setBounds(holyBierGame.getWidth() * 1/20, holyBierGame.getHeight() * 1/5, holyBierGame.getHeight()/2, holyBierGame.getWidth()/5);
        statsPlayer.setFont(new Font("Press Start 2P", Font.PLAIN, 25));

        JLabel statsBoss = new JLabel(
            "<html>Boss stats: " +
            "Name: " + boss.getName() +
            "Level: " + boss.getLevel() +
            "<br>Healt: " + boss.getHealth() +
            "<br>Damage: " + boss.getCurrentDamage() +
            "<html>"
        );
        statsBoss.setBounds(holyBierGame.getWidth() * 11/20, holyBierGame.getHeight() * 1/5, holyBierGame.getHeight()/2, holyBierGame.getWidth()/5);
        statsBoss.setFont(new Font("Press Start 2P", Font.PLAIN, 25));

        panel.add(choice1);
        panel.add(choice2);
        panel.add(choice3);
        panel.add(statsPlayer);
        panel.add(statsBoss);
        panel.add(itemToUse);

        holyBierGame.add(panel);

        choice1.addActionListener((ActionEvent e) -> {
            player.dealDamageTo(player.getItem(0).getDamage(), boss);
            checkBossDeath();
        });
        
        choice2.addActionListener((ActionEvent e) -> {
            player.dealDamageTo(player.getItem(1).getDamage(), boss);
            checkBossDeath();
        });
        
        choice3.addActionListener((ActionEvent e) -> {
            player.dealDamageTo(player.getItem(2).getDamage(), boss);
            checkBossDeath();
        });
    }

    void thePub(Heal newH) { 
        JPanel panel = new JPanel();
        panel.setBounds(0, 0, holyBierGame.getWidth(), holyBierGame.getHeight());
        panel.setBackground(basicBackground);
        panel.setLayout(null);

        JLabel newHLabel = new JLabel("<html>The barkeeper offers<br> you a pint! <html>");
        newHLabel.setFont(new Font("Press Start 2P", Font.PLAIN, 35));
        newHLabel.setBounds(80, 50, 1000, 100);

        JLabel changeTo = new JLabel("You can change to one of your bottles :");
        changeTo.setFont(new Font("Press Start 2P", Font.PLAIN, 19));
        changeTo.setBounds(80, 300, 1000, 100);

        JLabel itemStats = new JLabel(
            "<html>Name: " + newH.getName() +
            ",  Level: " + newH.getLevel() +
            "<br>Heal amount: " + newH.getHealAmount() +
            "<html>"
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

    void youDied() {
        youDied = true;
        JLabel youDiedL = new JLabel(
            "<html>You died! :( <html>"
        );
        reload();
        textScreen(youDiedL); // TODO dont return to path chose
    }

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

    public static void main(String[] args) {
        new Controller().startUp();
    }
}
