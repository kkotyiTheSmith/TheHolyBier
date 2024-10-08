import java.awt.Color;
import java.awt.event.ActionEvent;
import javax.swing.*;

class Scene {
    JPanel panel;

    JPanel startUpScene() {
        panel = new JPanel();
        panel.setBounds(100, 100, 200, 200);
        panel.setBackground(Color.black);
        
        JButton newGameButton = new JButton("New Game ->");
        newGameButton.setBounds(200, 0, 50, 50);
        newGameButton.setBackground(Color.GREEN);
        
        panel.add(newGameButton);

        newGameButton.addActionListener((ActionEvent e) -> {
            /*
            holyBierGame.getContentPane().removeAll();
            holyBierGame.revalidate();
            holyBierGame.repaint();
            this.startNewGame();
            */
        });

        return panel;
    }

    JPanel pathScene() {
        
        
        return panel;
    }

    JPanel bossScene() {


        return panel;
    }

    JPanel thePub() {


        return panel;
    }

    JPanel newItemScene() {

        
        return panel;
    }
}
