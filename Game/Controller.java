import java.awt.Color;
import javax.swing.*;


class Controller {
    CharacterManager characterManager = new CharacterManager();
    Scene currentScene;
    JFrame holyBierGame = new JFrame("The Holy Bier");

    void startUp() {
        holyBierGame.setSize(500, 500);
        currentScene = new Scene();
        
        holyBierGame.add(currentScene.startUpScene());
        
        holyBierGame.setLayout(null);
        holyBierGame.setVisible(true);
        holyBierGame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    void startNewGame() {
        holyBierGame.getContentPane().removeAll();
        holyBierGame.revalidate();
        holyBierGame.repaint();

        JPanel panel = new JPanel();

        panel.setBackground(Color.blue);
        panel.setBounds(0, 0, holyBierGame.getWidth(), holyBierGame.getHeight());

        holyBierGame.add(panel);
    }
    
    public static void main(String[] args) {
        new Controller().startUp();
    }
}