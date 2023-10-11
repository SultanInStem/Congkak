import java.awt.Color;
public class Game {
    private final int REFRESH_DELAY = 1000;
    private boolean isPlayer1 = true;
    private boolean isPlaying;
    private Frame frame;
    Player player1 = new Player(1, Color.RED);
    Player player2 = new Player(2, Color.BLUE);
    private Hole[] row1 = new Hole[7];
    private Hole[] row2 = new Hole[7];
    private Hole[][] board = {row1, row2};

    public Game(){
        this.isPlaying = true; 
        createBoard();
        this.frame = new Frame(player1, player2);
    }
    public void start(){
        Hole hole; 
        int holeId; 
        int marbles; 
        int col;
        int row; 
        while(isPlaying){
            if(isPlayer1){
                // GAME LOGIC FOR PLAYER 1
                frame.display(board, isPlayer1, false, -1);
                holeId = takeInput(isPlayer1);
          
                hole = row1[holeId];
                System.out.println("Player1 picks hole with id: " + hole.getId());

                marbles = hole.getMarbles(); 
                player1.setCurrentMarbles(marbles);
                hole.emptyAllMarbles();

                // SETTING UP THE POINTERS
                holeId += 1; 
                if(holeId >= row1.length){
                    row = 1; 
                    col = row2.length - 1;
                }else{
                    col = holeId;
                    row = 0; 
                }

                while(player1.getCurrentMarbles() > 0 && col >= -1 && row >= 0 && row <= 1){
                    
                    if(row == 1 && col == -1 && player1.getCurrentMarbles() > 1){ 
                        // PLAYER PUTS MARBLES INTO THE HOUSE AND HAS MORE MARBLES TO GO 
                        player1.incrementHouseMarbles(); 
                        player1.decrementCurrentMarbles(); 
                        frame.display(board, isPlayer1, true, -1);
                    }else if(player1.getCurrentMarbles() <= 1 && row == 1 && col == -1){
                        // PLAYER PUTS LAST MARBLE INTO THEIR HOUSE 
                        player1.incrementHouseMarbles(); 
                        player1.decrementCurrentMarbles(); 
                        frame.display(board, isPlayer1, true, -1);
                        isPlayer1 = false; 

                    }else if(player1.getCurrentMarbles() <= 1 && col >= 0 && !board[row][col].isEmpty()){
                        // PLAYER PUTS MARBLES INTO NON-EMPTY HOLE 
                        player1.decrementCurrentMarbles(); 
                        hole = board[row][col]; 
                        hole.incrementMarbles();
                        marbles = hole.getMarbles();
                        player1.setCurrentMarbles(marbles);
                        hole.emptyAllMarbles();
                        frame.display(board, isPlayer1, true, hole.getId());
                    }else if(player1.getCurrentMarbles() <= 1 && col >= 0 && board[row][col].isEmpty()){ 
                        // PLAYER PUTS LAST MARBLE INTO EMPTY HOLE
                        hole = board[row][col]; 
                        player1.decrementCurrentMarbles(); 
                        hole.incrementMarbles(); 
                        frame.display(board, isPlayer1, true, hole.getId());
                        break;
                    }else if(player1.getCurrentMarbles() > 1 && col >= 0){
                        hole = board[row][col]; 
                        player1.decrementCurrentMarbles();
                        hole.incrementMarbles();
                        frame.display(board, isPlayer1, true, hole.getId());
                    }

                    // UPDATING POINTERS ACCORDINGLY 
                    if(row == 0 && col < row1.length - 1){
                        col += 1;
                    }else if(row == 0 && col == row1.length - 1){
                        row = 1; 
                        col = row2.length - 1;
                    }else if(row == 1 && col >= 0){
                        col -=1;
                    }else if(row == 1 && col < 0){
                        row = 0; 
                        col = 0;
                    }
                    try{
                        Thread.sleep(this.REFRESH_DELAY);
                    }catch(InterruptedException e){
                        // HANDLE EXCEPTION 
                    }
                }

                isPlayer1 = !isPlayer1;
            }else{
                // GAME LOGIC FOR PLAYER 2
                frame.display(board, isPlayer1, false, -1);
                holeId = takeInput(isPlayer1); 
                hole = row2[holeId - row2.length]; 

                marbles = hole.getMarbles(); 
                player2.setCurrentMarbles(marbles);
                hole.emptyAllMarbles(); 

                holeId = holeId - row2.length;
                holeId -=1; 

                if(holeId < 0){
                    row = 0; 
                    col = row1.length - 1; 
                }else{
                    row = 1;
                    col = holeId;
                }

                while(player2.getCurrentMarbles() > 0 && row >= 0 && row <= 1 && col >= -1){

                    if(player2.getCurrentMarbles() > 1 && col < 0){
                        // IF PLAYERS TWO PUTS MARBLE INTO THE HOUSE BUT HAS MORE MARBLES TO GO
                        player2.decrementCurrentMarbles(); 
                        player2.incrementHouseMarbles();
                        frame.display(board, isPlayer1, true, -1);
                    }else if(player2.getCurrentMarbles() <= 1 && col < 0){
                        // IF PLAYER PUT LAST MARBLE INTO THEIR HOUSE 
                        player2.decrementCurrentMarbles(); 
                        player2.incrementHouseMarbles(); 
                        frame.display(board, isPlayer1, true, -1);
                        isPlayer1 = true;
                    }else if(col >= 0 && board[row][col].isEmpty() && player2.getCurrentMarbles() <= 1){
                        // IF PLAYER PUTS LAST MARBLE INTO EMPTY HOLE
                        player2.decrementCurrentMarbles(); 
                        board[row][col].incrementMarbles();
                        frame.display(board, isPlayer1, true, board[row][col].getId());
                  
                    }else if(col >= 0 && !board[row][col].isEmpty() && player2.getCurrentMarbles() <= 1){
                        // IF PLAYER PUTS LAST MARBLE INTO NON-EMPTY HOLE
                        player2.decrementCurrentMarbles(); 
                        board[row][col].incrementMarbles(); 
                        marbles = board[row][col].getMarbles();
                        player2.setCurrentMarbles(marbles);
                        board[row][col].emptyAllMarbles();
                        frame.display(board, isPlayer1, true, board[row][col].getId());
                    }else if(col >= 0 && player2.getCurrentMarbles() > 1){
                        // PLAYER HAS ENOUGH MARBLES AND THE HASN'T REACHED THE HOUSE YET
                        player2.decrementCurrentMarbles(); 
                        board[row][col].incrementMarbles(); 
                        frame.display(board, isPlayer1, true, board[row][col].getId());
                    }

                    System.out.println("Row: " + row + " Col: " + col);
                    System.out.println(player2.toString());
                    System.out.println("---------------");
                    if(row == 1 && col > 0){
                        col -= 1; 
                    }else if(row == 1 && col == 0){
                        row = 0;
                        col = 0;
                    }else if(row == 0 && col < row1.length - 1 && col >= 0){
                        col += 1;
                    }else if(row == 0 && col == row1.length - 1){
                        col = -1;
                    }else if(col < 0){
                        row = 1; 
                        col = row2.length -1;
                    }


                    try{
                        Thread.sleep(REFRESH_DELAY);
                    }catch(InterruptedException e){
                        // HANDLE THE EXCEPTION
                    }
                }
                isPlayer1 = !isPlayer1;
            }
        }
    }

    // TAKE INPUT FROM THE USER
    public int takeInput(boolean turnPlayer1){
        int i = turnPlayer1 ? 0 : 1;
        int id = -1; 

        while(id < 0){
            Hole hole;
            for(int j = 0; j < board[i].length; j++){
                hole = board[i][j]; 
                if(hole.getIsChosen()){
                    if(!hole.isEmpty()){
                        // IF USES PICKS AVAILABLE HOLE 
                        id = hole.getId(); 
                        hole.setIsChosen(false);
                        break;
                    }else{
                        // IF USER TRIES TO TAKE MARBLES FROM AN EMPTY HOLE
                        System.out.println("INVALID INPUT");
                        hole.setIsChosen(false);
                        break;
                    }
                }
            }
        }
        return id; 
    }

    // SETS UP THE BOARD OF HOLES 
    public void createBoard(){
        int id = 0;
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < row1.length; j++){
                // CREATING A HOLE AND PUTTING IT INTO THE ARRAY
                Hole hole = new Hole(true, id);
                board[i][j] = hole;
                id+=1;
            }
        }
    }
}
