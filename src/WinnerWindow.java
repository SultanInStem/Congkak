import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.Color;
public class WinnerWindow extends JPanel {
    private JPanel top;
    private JPanel bottom; 
    private JButton playAgain; 
    private JButton quit; 
    public WinnerWindow(){
        this.setVisible(true);
        this.setOpaque(true);
        this.setLayout(new BorderLayout());

        top = new JPanel();
        top.setVisible(true);
        top.setOpaque(true);


        bottom = new JPanel();
        bottom.setVisible(true);
        bottom.setOpaque(true);

        playAgain = new JButton();
        quit = new JButton();
        
        this.add(top, BorderLayout.NORTH);
        this.add(bottom, BorderLayout.SOUTH);
    }
}
