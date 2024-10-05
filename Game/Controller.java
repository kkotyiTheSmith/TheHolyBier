import java.awt.Color;
import javax.swing.*;

class Controller {
    CharacterManager characterManager = new CharacterManager();
    JFrame holyBierGame = new JFrame("The Holy Bier");

    void startUp() {
        holyBierGame.setSize(500, 500);

        JPanel panel = new JPanel();
        panel.setBounds(100, 100, 200, 200);
        panel.setBackground(Color.black);
        
        JButton newGameButton = new JButton("New Game ->");
        newGameButton.setBounds(0, 0, 50, 50);
        newGameButton.setBackground(Color.GREEN);
        
        panel.add(newGameButton);
        
        holyBierGame.add(panel);
        
        holyBierGame.setVisible(true);
        holyBierGame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        

    }

    /*
    newGameButton.addActionListener((ActionEvent e) -> {
            holyBierGame.getContentPane().removeAll();
            holyBierGame.revalidate();
            holyBierGame.repaint();
            this.startNewGame();
        });

--------------------------------------------------------------
    void startNewGame() {
        holyBierGame.getContentPane().removeAll();
        holyBierGame.revalidate();
        holyBierGame.repaint();

        JPanel panel = new JPanel();

        panel.setBackground(Color.blue);
        panel.setBounds(0, 0, holyBierGame.getWidth(), holyBierGame.getHeight());

        holyBierGame.add(panel);
    }


    */

    

    public static void main(String[] args) {
        new Controller().startUp();
    }
}