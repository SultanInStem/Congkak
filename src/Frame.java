import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.Font;
public class Frame extends JFrame {
    private final String FONT_STYLE = "Serif";
    private final int WINDOW_WIDTH = 600; 
    private final int WINDOW_HEIGHT = 500;

    private final int HEADER_HEIGHT = 100;
    private final Color HEADER_COLOR = new Color(68,83,193);

    private final Color FOOTER_COLOR = new Color(68,83,193);
    private final int FOOTER_HEIGHT = 100; 

    private final int SIDEBAR_WIDTH = 100;
    private final Color MAIN_GRID_COLOR = new Color(1,1,1);

    private final int HEDER_LABEL_WIDTH = 200;
    private final int HEADER_LABEL_HEIGHT = 80;
    private final int HEADER_LABEL_FONT_SIZE = 20;


    House sidebarLeft; 
    House sidebarRight;
    JPanel mainGrid; 
    JLabel headerLabel;

    Player player1; 
    Player player2;

    public Frame(Player player1, Player player2){
        this.setVisible(true);
        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.setTitle("Congkak game!");
        // INITIALIZING THE INSTANCES OF THE PLAYERS 
        this.player1 = player1;
        this.player2 = player2;
        // CREATE HEADER AND FOOTER 
        JPanel header = new JPanel();
        header.setLayout(new GridBagLayout());
        header.setOpaque(true);
        header.setVisible(true);
        header.setBackground(HEADER_COLOR);
        header.setPreferredSize(new Dimension(this.getWidth(), HEADER_HEIGHT));
        headerLabel = new JLabel("Player");
        headerLabel.setVisible(true);
        headerLabel.setOpaque(false);
        headerLabel.setFont(new Font(FONT_STYLE, Font.BOLD, HEADER_LABEL_FONT_SIZE));
        headerLabel.setPreferredSize(new Dimension(HEDER_LABEL_WIDTH, HEADER_LABEL_HEIGHT));
        headerLabel.setHorizontalAlignment(JLabel.CENTER);
        header.add(headerLabel);
        JPanel footer = new JPanel();
        footer.setBackground(FOOTER_COLOR);
        footer.setOpaque(true);
        footer.setVisible(true);
        footer.setPreferredSize(new Dimension(this.getWidth(), FOOTER_HEIGHT));
        // CREATE MAIN GRID
        mainGrid = new JPanel();
        mainGrid.setVisible(true);
        mainGrid.setOpaque(true);
        mainGrid.setBackground(MAIN_GRID_COLOR);
        mainGrid.setLayout(new GridLayout(2,7));
        // SET UP SIDEBARS AKA HOMES 
        sidebarLeft = new House(player1.getPlayerDefaultColor()); 
        sidebarLeft.setPreferredSize(new Dimension(SIDEBAR_WIDTH, this.getHeight()));

        sidebarRight = new House(player2.getPlayerDefaultColor());
        sidebarRight.setPreferredSize(new Dimension(SIDEBAR_WIDTH, this.getHeight()));

        // ADDING COMPONENTS TO THE FRAME
        this.add(header, BorderLayout.NORTH);
        this.add(sidebarLeft, BorderLayout.WEST);
        this.add(mainGrid, BorderLayout.CENTER); 
        this.add(sidebarRight, BorderLayout.EAST);
        this.add(footer, BorderLayout.SOUTH);
    }    
    public void switchButtonState(Hole[][] board, boolean turnPlayer1){
        for(int j = 0; j < board[0].length; j++){
            if(turnPlayer1){
                this.headerLabel.setText("Player 1");
                board[0][j].showBtn();
                board[1][j].hideBtn();
            }else{
                this.headerLabel.setText("Player 2");
                board[1][j].showBtn();
                board[0][j].hideBtn();
            }
        }
    }
    public void resetHoles(Hole[][] board){
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[i].length; j++){
                board[i][j].hideBtn();
                board[i][j].resetBgColor();
            }
        }
    }
    public void display(Hole[][] board, boolean turnPLayer1, boolean isDynamic, int holeId){       
        if(isDynamic){
            // TEMP CODE ----

            // RESET BUTTONS AND COLORS 
            resetHoles(board);
            this.sidebarLeft.setBackground(player1.getPlayerDefaultColor());
            this.sidebarRight.setBackground(player2.getPlayerDefaultColor());

            for(int i = 0; i < board.length; i++){
                for(int j = 0; j < board[i].length; j++){
                    if(holeId < 0){
                        // means we must put marbles into our house 
                        if(turnPLayer1){
                            this.sidebarLeft.setBackground(new Color(139, 0, 0));;
                            this.sidebarLeft.showMarbles(player1.getTotalMarbles());
                        }else if(!turnPLayer1){
                            this.sidebarRight.setBackground(new Color(0, 0, 139));;
                            sidebarRight.showMarbles(player2.getTotalMarbles());
                        }
                     
                    }else if(holeId == board[i][j].getId()){
                        board[i][j].hightLight();
                    }
                    board[i][j].repaintMarbles();
                    mainGrid.add(board[i][j]);
                }
            }
            // --------------
        }else{ 
            // SWICH BUTTON STATES
            switchButtonState(board, turnPLayer1);
            for(int i = 0; i < board.length; i++){
                for(int j = 0; j < board[i].length; j++){
                    mainGrid.add(board[i][j]);
                }
            }
        }
        this.repaint();
    }
}
