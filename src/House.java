import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
public class House extends JPanel{
    private JLabel tempLabel;
    public House(Color bgColor){
        this.setVisible(true);
        this.setOpaque(true);
        this.setBackground(bgColor);
        tempLabel = new JLabel("marbles: 0");
        this.add(tempLabel);
    }
    public void showMarbles(int marbles){
        this.remove(this.tempLabel);
        this.tempLabel.setText("mabrles: " + marbles);
        this.add(tempLabel);
        this.repaint();
    }
}
