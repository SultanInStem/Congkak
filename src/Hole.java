import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Cursor;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;

import Constants.Const;
import javax.swing.JButton;
public class Hole extends JPanel{
    private int marbles; 
    private boolean isChosen;
    private boolean isBtnHidden;
    private int id;
    private JButton button;

    private JPanel marbleContainer;
    private int MARBLE_CONTAINER_HEIGHT = 300; 
    private JPanel buttonContainer;
    private int BUTTON_CONTAINER_HEIGHT = 50; 

    JLabel marblesLabel;
    public Hole(boolean isBtnHidden, int id){
        // SET UP THE NON-VISUAL COMPONENTS 
        this.isChosen = false; 
        this.marbles = 7; 
        this.id = id;
        this.isBtnHidden = isBtnHidden;
        // SET UP THE VISUAL COMPONENTS 
        this.setVisible(true);
        this.setOpaque(true);
        this.setBackground(Const.defaultHoleColor);
        this.setBorder(BorderFactory.createEtchedBorder());
        this.setLayout(new BorderLayout());
        
        marbleContainer = new JPanel();
        marbleContainer.setVisible(true);
        marbleContainer.setPreferredSize(new Dimension(this.getWidth(), MARBLE_CONTAINER_HEIGHT));
        marbleContainer.setLayout(new GridBagLayout());
        marblesLabel = new JLabel();
        marblesLabel.setSize(new Dimension(200, 50)); // temp code
        marblesLabel.setText("" + this.marbles);
        marblesLabel.setFont(new Font(Const.FontStyle, Font.BOLD, 25));
        marblesLabel.setOpaque(false);


        marbleContainer.add(marblesLabel); 
        buttonContainer = new JPanel();
        buttonContainer.setVisible(true);
        buttonContainer.setPreferredSize(new Dimension(this.getWidth(), BUTTON_CONTAINER_HEIGHT));
        buttonContainer.setLayout(new BorderLayout());
        button = new JButton("grab");
        button.addActionListener(e -> {
            // SET "isChosen" TO TRUE SO WE KNOW WHICH BUTTON WAS CLICKED
            this.isChosen = true;
        });
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        this.buttonContainer.add(button, BorderLayout.CENTER);

        this.add(marbleContainer, BorderLayout.NORTH);
        this.add(buttonContainer, BorderLayout.SOUTH);
    }
    // NON-VISUAL METHODS 
    public void incrementMarbles(){
        this.marbles += 1; 
    }
    public boolean getIsChosen(){
        return this.isChosen; 
    }
    public void emptyAllMarbles(){
        this.marbles = 0;
    }
    public void setIsChosen(boolean val){
        this.isChosen = val;
    }
    public int getMarbles(){
        return this.marbles;
    }
    public int getId(){
        return this.id;
    }
    public boolean isEmpty(){
        return this.marbles > 0 ? false : true;
    }

    // VISUAL METHODS 
    public void hightLight(){
        this.marbleContainer.setBackground(Const.iterationHighlightColor);
        this.repaint();
    }
    public void repaintMarbles(){
        this.marblesLabel.setText(""+ this.marbles);
        this.marblesLabel.setSize(new Dimension(200, 50)); // temp code
        this.marbleContainer.remove(this.marblesLabel);
        this.marbleContainer.add(marblesLabel);
        this.marbleContainer.repaint();
    }
    public void resetBgColor(){
        this.marbleContainer.setBackground(Color.WHITE);
        this.repaint();
    }
    public void hideBtn(){
        this.isBtnHidden = true;
        this.buttonContainer.remove(this.button);
        this.buttonContainer.revalidate();
        this.buttonContainer.repaint();
        this.repaint();
    }
    public void showBtn(){
        this.isBtnHidden = false;
        this.buttonContainer.remove(this.button);
        this.buttonContainer.add(this.button, BorderLayout.CENTER);
        this.buttonContainer.revalidate();
        this.buttonContainer.repaint();
        this.repaint();
    }
}
