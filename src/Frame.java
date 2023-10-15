import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import Constants.Const;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.Toolkit; 
import java.awt.Font;
public class Frame extends JFrame {

    private final int HEADER_HEIGHT = 100;

    private final int FOOTER_HEIGHT = 100; 

    private final int SIDEBAR_WIDTH = 100;

    private final int HEDER_LABEL_WIDTH = 200;
    private final int HEADER_LABEL_HEIGHT = 80;
    private final int HEADER_LABEL_FONT_SIZE = 20;

    private final int FOOTER_LABEL_WIDTH = 200; 
    private final int FOOTER_LABEL_HEIGHT = 80;
    private final int FOOTER_LABEL_FONT_SIZE = 20;

    private House sidebarLeft; 
    private House sidebarRight;
    private JPanel mainGrid; 
    private JLabel headerLabel;
    private JLabel mainFooterLabel;
    private Player player1; 
    private Player player2;
    private WinnerWindow winnerWindow;

    public Frame(Player player1, Player player2){
        this.setVisible(true);
        this.setSize(Const.WINDOW_WIDTH, Const.WINDOW_HEIGHT);
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
        header.setBackground(Const.mainBackgroundColor);
        header.setPreferredSize(new Dimension(this.getWidth(), HEADER_HEIGHT));
        headerLabel = new JLabel("Player");
        headerLabel.setVisible(true);
        headerLabel.setOpaque(false);
        headerLabel.setFont(new Font(Const.FontStyle, Font.BOLD, HEADER_LABEL_FONT_SIZE));
        headerLabel.setPreferredSize(new Dimension(HEDER_LABEL_WIDTH, HEADER_LABEL_HEIGHT));
        headerLabel.setHorizontalAlignment(JLabel.CENTER);
        header.add(headerLabel);

        JPanel footer = new JPanel();
        footer.setBackground(Const.mainBackgroundColor);
        footer.setOpaque(true);
        footer.setVisible(true);
        footer.setPreferredSize(new Dimension(this.getWidth(), FOOTER_HEIGHT));
        mainFooterLabel = new JLabel(); 
        mainFooterLabel.setOpaque(true);
        mainFooterLabel.setVisible(true);
        mainFooterLabel.setFont(new Font(Const.FontStyle, Font.BOLD, FOOTER_LABEL_FONT_SIZE)); 
        mainFooterLabel.setSize(new Dimension(FOOTER_LABEL_WIDTH, FOOTER_LABEL_HEIGHT));
        // CREATE MAIN GRID
        mainGrid = new JPanel();
        mainGrid.setVisible(true);
        mainGrid.setOpaque(true);
        mainGrid.setBackground(Const.mainBackgroundColor);
        mainGrid.setLayout(new GridLayout(2,7));
        // SET UP SIDEBARS AKA HOMES 
        sidebarLeft = new House(player1.getPlayerDefaultColor()); 
        sidebarLeft.setPreferredSize(new Dimension(SIDEBAR_WIDTH, this.getHeight()));

        sidebarRight = new House(player2.getPlayerDefaultColor());
        sidebarRight.setPreferredSize(new Dimension(SIDEBAR_WIDTH, this.getHeight()));

        // set up winner-window 
        winnerWindow = new WinnerWindow();

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
                this.headerLabel.setForeground(player1.getPlayerDefaultColor());
                board[0][j].showBtn();
                board[1][j].hideBtn();
            }else{
                this.headerLabel.setText("Player 2");
                this.headerLabel.setForeground(player2.getPlayerDefaultColor());
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
        this.remove(winnerWindow);  
        this.remove(mainGrid);
        mainGrid.removeAll();
        this.add(mainGrid, BorderLayout.CENTER); 
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
        this.revalidate();
        this.repaint();
    }
    public void displayWinner(){
        this.remove(mainGrid);
        String message;
        Color fontColor; 
        if(player1.getTotalMarbles() > player2.getTotalMarbles()){
            message = "PLAYER 1 WON";
            fontColor = player1.getPlayerDefaultColor();
        }else if(player1.getTotalMarbles() < player2.getTotalMarbles()){
            message = "PLAYER 2 WON"; 
            fontColor = player2.getPlayerDefaultColor();
        }else{
            message = "IT'S A DRAW";
            fontColor = Const.drawFontColor;
        }
        winnerWindow.showOutcome(message, fontColor);
        System.out.println("ADDING THE RESULT WINDOW");
        this.add(winnerWindow, BorderLayout.CENTER);
        this.revalidate();
        this.repaint();
    }
    public void displayError(String errMessage, Hole[][]board){
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[i].length; j++){
                mainGrid.add(board[i][j]);
            }
        }
        this.mainFooterLabel.setText(errMessage);
        this.mainFooterLabel.setForeground(Color.RED);
        this.revalidate();
        this.repaint();
    }
    public String getWinnerWindowAction(){
        return this.winnerWindow.getAction();
    }
    public void resetWInnerBtn(){
        this.winnerWindow.resetAction();
    }
}
