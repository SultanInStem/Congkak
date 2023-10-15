import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.colorchooser.ColorSelectionModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Cursor;

import Constants.Const;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Color;
import java.awt.GridBagLayout;

public class WinnerWindow extends JPanel {
    private JPanel top;
    private JPanel bottom;
    private JButton playAgain;
    private JButton quit;
    private JLabel resultLabel;
    private String action;
    public WinnerWindow() {
        this.action = "";
        this.setVisible(true);
        this.setOpaque(true);
        this.setLayout(new BorderLayout());
        this.setBackground(Color.RED);

        top = new JPanel();
        top.setVisible(true);
        top.setOpaque(true);
        // top.setBackground(Color.BLUE);
        top.setPreferredSize(new Dimension(this.getWidth(), 300));
        top.setLayout(new GridBagLayout());
        resultLabel = new JLabel();
        resultLabel.setOpaque(false);
        resultLabel.setVisible(true);
        resultLabel.setFont(new Font(Const.FontStyle, Font.BOLD, Const.Font_Large_Size));
        top.add(resultLabel);

        bottom = new JPanel();
        bottom.setVisible(true);
        bottom.setOpaque(true);
        // bottom.setBackground(Color.BLACK);
        bottom.setLayout(new GridBagLayout());
        bottom.setPreferredSize(new Dimension(this.getWidth(), 100));

        playAgain = new JButton();
        playAgain.setText("Resume");
        playAgain.addActionListener(e -> {
            this.action = "p";
        }); 
        playAgain.setCursor(new Cursor(Cursor.HAND_CURSOR));
        quit = new JButton();
        quit.setText("Quit");
        quit.addActionListener(e -> {
            this.action = "q";
        });
        quit.setCursor(new Cursor(Cursor.HAND_CURSOR));
        bottom.add(playAgain);
        bottom.add(quit);

        // this.setBackground(Color.YELLOW);
        this.add(top, BorderLayout.NORTH);
        this.add(bottom, BorderLayout.SOUTH);
    }

    public void showOutcome(String text, Color fontColor){
        resultLabel.setText(text);
        resultLabel.setForeground(fontColor);
        System.out.println("Changing text and colors");
    }
    public String getAction(){
        return this.action;
    }
    public void resetAction(){
        this.action = "";
    }
}
