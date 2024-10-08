import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import javax.swing.*;

class Controller {
    Player player = new Player();
    Scene currentScene;
    JFrame holyBierGame = new JFrame("The Holy Bier");
    Color kreolkek = new Color(84, 120, 112);
    JPanel panel;

    void startUp() {
        holyBierGame.setSize(500, 500);
        holyBierGame.setLayout(null);

        panel = new JPanel();
        panel.setBounds(0, 0, holyBierGame.getWidth(), holyBierGame.getHeight());
        panel.setBackground(kreolkek);
        panel.setLayout(null);

        JLabel theHolyBierTitle = new JLabel("The Holy Bier");
        theHolyBierTitle.setBounds(holyBierGame.getWidth()/2 - 150, holyBierGame.getWidth()/3, 400, 100);
        theHolyBierTitle.setBackground(Color.BLACK);
        theHolyBierTitle.setFont(new Font("Arial", Font.BOLD, 50));
        
        
        JButton newGameButton = new JButton("New Game ->");
        newGameButton.setBounds(holyBierGame.getWidth()/2 - 50, holyBierGame.getWidth()/2, 100, 50);
        newGameButton.setBackground(Color.GREEN);
        
        panel.add(theHolyBierTitle);
        panel.add(newGameButton);

        newGameButton.addActionListener((ActionEvent e) -> {
            holyBierGame.getContentPane().removeAll();
            holyBierGame.revalidate();
            holyBierGame.repaint();
            this.startNewGame();
        });
        
        holyBierGame.add(panel);
        
        holyBierGame.setVisible(true);
        holyBierGame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    void startNewGame() {
        panel = new JPanel();
        holyBierGame.setLayout(null);
        

        panel.setBackground(kreolkek);
        panel.setBounds(0, 0, holyBierGame.getWidth(), holyBierGame.getHeight());

        holyBierGame.add(panel);
    }
    
    public static void main(String[] args) {
        new Controller().startUp();
    }
}