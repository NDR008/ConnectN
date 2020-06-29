import java.util.Random;
import java.lang.Math;

public class PlayerAI extends Player {
    private int middleCol;
    private Random r = new Random();
    private char[][] board;
    private int maxY;
    private int maxX;

    public PlayerAI(char token, String name){
        super(token, name);
    }

    @Override
    /* the AI will decide if it should just play towards the middle or try to block or try to win.
     * The middle easily blocks the opponents from having the chance of a horizontal connect.
     */
    public int getUserInput( ) {
        int nextPlay = -1;
        /* Buggy logic that does not work
        for( int j = 0; j < maxY; j++ ){
            for( int i = 2; i < maxX-2; i++ ) {
                // Block the other player!
                if (board[i][j] == getToken() && board[i - 1][j] == getToken()){
                    nextPlay = i-2+1;
                }
                else if (board[i][j] == getToken() && board[i + 1][j] == getToken() ){
                    nextPlay= i+2+1;
                }
            }
        }
        */
        // Instead the PlayerAI will bias towards the middle //if (nextPlay == -1) {
        do {
            for (int i = 0; i < 2; i++) {
                nextPlay = r.nextInt(maxX) + 1;
                if ((nextPlay >= (middleCol - 1)) && (nextPlay <= (middleCol + 1))) {
                    break;
                }
            }
        }
        while (!checkForSpace(nextPlay));
        return nextPlay;
    }

    @Override
    public void getBoard(char[][] inBoard){
        board = inBoard;
        maxX = board.length;
        maxY = board[0].length;
        middleCol = Math.round((((float) maxX+1)/2));
    }

    public boolean checkForSpace(int column){
        column = column-1;
        for (int i=0; i<maxY; i++){
            if (board[column][i] == ' ') {
                return true;
            }
        }
        return false;
    }
}