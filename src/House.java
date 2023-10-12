import javax.swing.JPanel;
import java.awt.Color;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.Font;
import Constants.Const;
public class House extends JPanel{
    private JLabel marblesLabel;
    public House(Color bgColor){
        this.setVisible(true);
        this.setOpaque(true);
        this.setLayout(new GridBagLayout());
        this.setBackground(bgColor);

        marblesLabel = new JLabel("0");
        marblesLabel.setFont(new Font(Const.FontStyle, Font.BOLD, Const.Font_Large_Size));
        this.add(marblesLabel);
    }
    public void showMarbles(int marbles){
        this.remove(this.marblesLabel);
        this.marblesLabel.setText(""+marbles);
        this.add(marblesLabel);
        this.repaint();
    }
}
