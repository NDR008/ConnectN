import java.io.*;

public class PlayerHuman extends Player {

    private BufferedReader reader;

    public PlayerHuman(char token, String name){
        super(token, name);
        reader = new BufferedReader(new InputStreamReader(System.in));
    }

    @Override
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