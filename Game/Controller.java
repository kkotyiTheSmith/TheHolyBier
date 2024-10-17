import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import javax.swing.*;

class Controller {
    JFrame holyBierGame = new JFrame("The Holy Bier");
    Color basicBackground = new Color(197, 165, 94);
    Player player = new Player();
    Boss boss;

    void reload() {
        holyBierGame.getContentPane().removeAll();
        holyBierGame.revalidate();
        holyBierGame.repaint();
    }
    
    void startUp() {
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
        
        /*
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

    void startNewGame() {
        JPanel panel = new JPanel();
        panel.setBounds(0, 0, holyBierGame.getWidth(), holyBierGame.getHeight());
        panel.setBackground(basicBackground);
        panel.setLayout(null);

        
        JLabel intro = new JLabel(
            "<html>This is a RPG game. Buttons and shit my friend. But don't worry it's not skill based. Enjoy!<html>"
        );
        intro.setBounds(50, 50, 800, 500);
        intro.setBackground(Color.BLACK);
        intro.setFont(new Font("Press Start 2P", Font.CENTER_BASELINE, 25));

        panel.add(intro);
        holyBierGame.add(panel);

        panel.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                reload();
                PathChose();
            }
        });
    }

    void PathChose() {
        JPanel panel = new JPanel();
        panel.setBounds(0, 0, holyBierGame.getWidth(), holyBierGame.getHeight());
        panel.setBackground(basicBackground);
        panel.setLayout(null);

        JLabel stats = new JLabel(
            "<html>Level: " + player.getLevel() +
            "<br>Healt: " + player.getHealth() +
            "<br>Damage: " + player.getCurrentDamage() +
            "<html>"
        );
        stats.setBounds(holyBierGame.getWidth()/20, holyBierGame.getHeight() * 2/3, holyBierGame.getHeight()/2, holyBierGame.getWidth()/5);
        stats.setFont(new Font("Press Start 2P", Font.PLAIN, 25));

        int buttonWidth = holyBierGame.getWidth()/3;
        int buttonHeight = holyBierGame.getHeight()/3 - holyBierGame.getHeight()/30;
        int buttonX = holyBierGame.getWidth() * 2/3;

        Item newItem = generateNewItem();
        Heal newHeal = player.heals[0].generateNewHeal();

        JButton itemRoom = new JButton("<html>Item room\n Cost: " + newItem.getCost() + "<html>");
        itemRoom.setBounds(buttonX, holyBierGame.getHeight()/70, buttonWidth, buttonHeight);
        itemRoom.setFont(new Font("Press Start 2P", Font.PLAIN, 25));
        JButton bossRoom = new JButton("Boss room");
        bossRoom.setBounds(buttonX, 2 *holyBierGame.getHeight()/70 + buttonHeight, buttonWidth, buttonHeight);
        bossRoom.setFont(new Font("Press Start 2P", Font.PLAIN, 25));
        JButton pubRoom = new JButton("<html>The Pub!\n Cost" + newHeal.getCost() + "<html>");
        pubRoom.setBounds(buttonX, 3 * holyBierGame.getHeight()/70 + 2 * buttonHeight, buttonWidth, buttonHeight);
        pubRoom.setFont(new Font("Press Start 2P", Font.PLAIN, 25));

        panel.add(itemRoom);
        panel.add(bossRoom);
        panel.add(pubRoom);
        panel.add(stats);
        
        holyBierGame.add(panel);
        
        itemRoom.addActionListener((ActionEvent e) -> {
            reload();
            itemRoomScene(newItem);
        });
        
        bossRoom.addActionListener((ActionEvent e) -> {
            reload();
            boss = new Boss(); 
            bossRoomScene();
        });
        
        pubRoom.addActionListener((ActionEvent e) -> {
            reload();
            thePub(newHeal);
        });
    }

    void itemRoomScene(Item newItem) {
        JPanel panel = new JPanel();
        panel.setBounds(0, 0, holyBierGame.getWidth(), holyBierGame.getHeight());
        panel.setBackground(basicBackground);
        panel.setLayout(null);

        JLabel newItemLabel = new JLabel("You found a new item!");
        newItemLabel.setFont(new Font("Press Start 2P", Font.PLAIN, 40));
        newItemLabel.setBounds(80, 50, 1000, 100);

        JLabel changeTo = new JLabel("You can change to one of your items :");
        changeTo.setFont(new Font("Press Start 2P", Font.PLAIN, 19));
        changeTo.setBounds(80, 300, 1000, 100);

        JLabel itemStats = new JLabel(
            "<html>Name: " + newItem.getName() +
            ",  Level: " + newItem.getLevel() +
            "<br>Damage: " + newItem.getDamage() +
            ",  Crit chance: " + newItem.getCritChance() +
            "<html>"
        );
        itemStats.setFont(new Font("Press Start 2P", Font.PLAIN, 24));
        itemStats.setBounds(80, 200, 1000, 100);

        int buttonWidth = holyBierGame.getWidth()/3 - holyBierGame.getHeight()/30;
        int buttonHeight = holyBierGame.getHeight()/3;
        int buttonY = holyBierGame.getHeight() * 3/5;

        JButton choice1 = new JButton(player.getItem(0).getName());
        choice1.setBounds(holyBierGame.getWidth()/70, buttonY, buttonWidth, buttonHeight);
        choice1.setFont(new Font("Press Start 2P", Font.PLAIN, 25));
        JButton choice2 = new JButton(player.getItem(0).getName());
        choice2.setBounds(2 * holyBierGame.getWidth()/70 + buttonWidth, buttonY, buttonWidth, buttonHeight);
        choice2.setFont(new Font("Press Start 2P", Font.PLAIN, 25));
        JButton choice3 = new JButton(player.getItem(0).getName());
        choice3.setBounds(3 * holyBierGame.getWidth()/70 + 2 * buttonWidth, buttonY, buttonWidth, buttonHeight);
        choice3.setFont(new Font("Press Start 2P", Font.PLAIN, 25));

        panel.add(itemStats);
        panel.add(choice1);
        panel.add(choice2);
        panel.add(choice3);
        panel.add(changeTo);
        panel.add(newItemLabel);

        holyBierGame.add(panel);

        choice1.addActionListener((ActionEvent e) -> {
            player.changeItem(0, newItem);
            reload();
            PathChose();
        });
        
        choice2.addActionListener((ActionEvent e) -> {
            player.changeItem(1, newItem);
            reload();
            PathChose();
        });
        
        choice3.addActionListener((ActionEvent e) -> {
            player.changeItem(2, newItem);
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

        panel.add(fight);
        panel.add(heal);
        panel.add(escape);
        
        holyBierGame.add(panel);

        fight.addActionListener((ActionEvent e) -> {
            reload();
            player.dealDamageTo(player.getCurrentDamage(), boss); // TODO for K - this mechanics should be changed later
            boss.dealDamageTo(boss.getCurrentDamage(), player);
            bossRoomScene();
        });

        heal.addActionListener((ActionEvent e) -> {
            reload();
            player.increaseHealth(10); // TODO for K - change to designed mechanic later
            boss.dealDamageTo(boss.getCurrentDamage(), player);
            bossRoomScene();
        });

        escape.addActionListener((ActionEvent e) -> {
            boss.dealDamageTo(boss.getCurrentDamage(), player);
            if (player.getHealth() <= 0) {
                reload();
                youDied();
            }
            reload();
            PathChose();
        });
    }

    void thePub(Heal newHeal) { // TODO for K - Create the pub.
        JPanel panel = new JPanel();
        panel.setBounds(0, 0, holyBierGame.getWidth(), holyBierGame.getHeight());
        panel.setBackground(basicBackground);
        panel.setLayout(null);

        JLabel newHealLabel = new JLabel("You found a new item!");
        newHealLabel.setFont(new Font("Press Start 2P", Font.PLAIN, 40));
        newHealLabel.setBounds(80, 50, 1000, 100);

        JLabel changeTo = new JLabel("You can change to one of your items :");
        changeTo.setFont(new Font("Press Start 2P", Font.PLAIN, 19));
        changeTo.setBounds(80, 300, 1000, 100);

        JLabel itemStats = new JLabel(
            "<html>Name: " + "Beer" + // TODO give names to beer
            ",  Level: " + "Beer" + // TODO give level to beer
            "<br>Healregen: " + newHeal.getHealAmount() +
            ",  Crit chance: " + "   " + // TODO
            "<html>"
        );
        itemStats.setFont(new Font("Press Start 2P", Font.PLAIN, 24));
        itemStats.setBounds(80, 200, 1000, 100);

        int buttonWidth = holyBierGame.getWidth()/3 - holyBierGame.getHeight()/30;
        int buttonHeight = holyBierGame.getHeight()/3;
        int buttonY = holyBierGame.getHeight() * 3/5;

        JButton choice1 = new JButton(player.getHeal(0).getName());
        choice1.setBounds(holyBierGame.getWidth()/70, buttonY, buttonWidth, buttonHeight);
        choice1.setFont(new Font("Press Start 2P", Font.PLAIN, 25));
        JButton choice2 = new JButton(player.getHeal(0).getName());
        choice2.setBounds(2 * holyBierGame.getWidth()/70 + buttonWidth, buttonY, buttonWidth, buttonHeight);
        choice2.setFont(new Font("Press Start 2P", Font.PLAIN, 25));
        JButton choice3 = new JButton(player.getHeal(0).getName());
        choice3.setBounds(3 * holyBierGame.getWidth()/70 + 2 * buttonWidth, buttonY, buttonWidth, buttonHeight);
        choice3.setFont(new Font("Press Start 2P", Font.PLAIN, 25));

        panel.add(itemStats);
        panel.add(choice1);
        panel.add(choice2);
        panel.add(choice3);
        panel.add(changeTo);
        panel.add(newHealLabel);

        holyBierGame.add(panel);

        choice1.addActionListener((ActionEvent e) -> {
            player.changeHeal(0, newHeal);
            reload();
            PathChose();
        });
        
        choice2.addActionListener((ActionEvent e) -> {
            player.changeHeal(1, newHeal);
            reload();
            PathChose();
        });
        
        choice3.addActionListener((ActionEvent e) -> {
            player.changeHeal(2, newHeal);
            reload();
            PathChose();
        });
    }

    void youDied() {

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
