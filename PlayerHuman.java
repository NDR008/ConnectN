import java.io.*;

public class PlayerHuman extends Player {

    private BufferedReader reader;

    /* Construct a human player */
    public PlayerHuman(char token, String name){
        super(token, name);
        reader = new BufferedReader(new InputStreamReader(System.in));
    }

    @Override
    /* Since humans make mistakes we need to check their input
     * Also check if the human wants to end the game. */
    public int getUserInput() {
        String inString;
        final int returnFailValue = 999;
        try {
            inString = reader.readLine();
            if (isNumeric(inString)) {
                return Integer.parseInt(inString);
            }
            else if (inString.equals("q")||inString.equals("Q")){
                System.exit(0);
            }
            return returnFailValue;

        } catch (IOException e) {
            return returnFailValue; //We will use 999 as an invalid move
        }
    }

    /* Checks if the string contains a number */
    public boolean isNumeric(String input) {
        try {
            {
                Integer.parseInt(input);
                return true;
            }
        } catch (NumberFormatException nfe) {
            return false;
        }
    }

}