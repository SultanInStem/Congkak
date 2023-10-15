import javax.swing.JPanel;
import java.awt.Color;
public class Player extends JPanel{
    private int id;
    private int TOTAL_MARBLES;  // marbles that we put into the houses 
    private int currentMabrles; // dummy marbles 
    private Color defaultColor;
    private Color darkerColor;
    
    public Player(int id, Color color){
        this.id = id; 
        this.defaultColor = color;
        int red = this.defaultColor.getRed() - 10;
        int blue = this.defaultColor.getBlue() - 10; 
        int green = this.defaultColor.getGreen() - 10;
        this.darkerColor = new Color(red, green, blue);
        this.TOTAL_MARBLES= 0;
    }
    // NON-VISUAL METHODS
    public void setHouseMarbles(int marbles){
        this.TOTAL_MARBLES = marbles;
    }
    public void incrementHouseMarbles(){
        this.TOTAL_MARBLES += 1;
    }
    public void decrementCurrentMarbles(){
        this.currentMabrles -= 1;
    }
    public void setCurrentMarbles(int marblesNum){
        this.currentMabrles = marblesNum; 
    }
    public int getCurrentMarbles(){
        return this.currentMabrles;
    }
    public int getTotalMarbles(){
        return this.TOTAL_MARBLES; 
    }
    public String toString(){
        return "Current m: " + this.currentMabrles + " Total m: " + this.TOTAL_MARBLES;
    }
    // VISUAL METHODS 
    public Color getPlayerDefaultColor(){
        return this.defaultColor;
    }
    public Color getDarkColor(){
        return this.darkerColor;
    }
}
