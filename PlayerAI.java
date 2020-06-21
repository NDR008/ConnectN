import java.util.Random;

public class PlayerAI extends Player {

    private int upperLimit=6+1;
    private Random r = new Random();

    public PlayerAI(char token, String name, int upperLimit){
        super(token, name);
        this.upperLimit=upperLimit+1;
    }

    public PlayerAI(char token, String name){ //default game
        super(token, name);
    }

    @Override
    public int getUserInput( ) {
        int out = r.nextInt(upperLimit) + 1;
        //System.out.println("the number is........... " +out);
        return out;
    }

}