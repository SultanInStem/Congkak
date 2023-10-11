import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Cursor;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;
public class Hole extends JPanel{
    private Color holeBg = new Color(123,13,67);
    private int marbles; 
    private boolean isChosen;
    private boolean isBtnHidden;
    private int id;
    private JButton button;

    private JPanel marbleContainer;
    private int MARBLE_CONTAINER_HEIGHT = 300; 
    private JPanel buttonContainer;
    private int BUTTON_CONTAINER_HEIGHT = 50; 

    JLabel tempLabel;
    public Hole(boolean isBtnHidden, int id){
        // SET UP THE NON-VISUAL COMPONENTS 
        this.isChosen = false; 
        this.marbles = 7; 
        this.id = id;
        this.isBtnHidden = isBtnHidden;
        // SET UP THE VISUAL COMPONENTS 
        this.setVisible(true);
        this.setOpaque(true);
        this.setBackground(this.holeBg);
        this.setBorder(BorderFactory.createEtchedBorder());
        this.setLayout(new BorderLayout());
        
        marbleContainer = new JPanel();
        marbleContainer.setVisible(true);
        marbleContainer.setPreferredSize(new Dimension(this.getWidth(), MARBLE_CONTAINER_HEIGHT));
        tempLabel = new JLabel();
        tempLabel.setSize(new Dimension(200, 50)); // temp code
        tempLabel.setText("" + this.marbles);
        tempLabel.setFont(new Font("Sarif", Font.BOLD, 20));
        tempLabel.setOpaque(true);


        marbleContainer.add(tempLabel); 
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
        this.marbleContainer.setBackground(holeBg);
        this.repaint();
    }
    public void repaintMarbles(){
        this.tempLabel.setText(""+ this.marbles);
        this.tempLabel.setSize(new Dimension(200, 50)); // temp code
        this.marbleContainer.remove(this.tempLabel);
        this.marbleContainer.add(tempLabel);
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
