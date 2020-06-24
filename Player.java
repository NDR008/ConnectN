public abstract class Player implements PlayerIO {

    private char token;
    private String name;
    private int winCounter=0;

    public Player(char token, String name){
        this.name = name;
        this.token = token;
    }

    /* Take user's input */
    public int getUserInput(){
        return 999; //We will use 999 as an invalid move
    }

    /* Return the token char of the player */
    public char getToken(){
        return token;
    }

    /* Return the name of the player */
    public String getName(){
        return name;
    }

    /* Increase the player score count */
    public void incWins(){
        winCounter++;
    }

    /* Return the number of wins of the player */
    public int getWins(){
        return winCounter;
    }

    public void seeBoard(char[][] board) {
    }

}