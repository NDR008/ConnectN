import java.util.Random;
import  java.lang.Math;

public class PlayerAI extends Player {

    private int upperLimit=0;
    private int middleCol;
    private Random r = new Random();
    private char[][] board;
    int[] locations = new int[upperLimit];

    public PlayerAI(char token, String name, int upperLimit){
        super(token, name);
        this.upperLimit=upperLimit;
        middleCol = Math.round((((float) upperLimit+1)/2));
    }

    @Override
    public int getUserInput( ) {
        /* currently the AI plays blindly knowing only the with of the board
         * in the future the AI should make use of the board content
         * to establish where is the best place to bias the move */
        int out=0;
        /* the AI biases to drop around the middle columns
         * Middle columns prevent connect-4 in a traditional 7-column game */
        for (int i=0; i<3; i++){
            out = r.nextInt(upperLimit) + 1;
            if ((out > (middleCol-1)) && (out < (middleCol+1))){
                System.out.println(out);
                return out;
            }
        }
        return out;
    }

    /* Method to be used in the future */
    private void decideNextMove(){
    }

}