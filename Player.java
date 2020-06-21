public abstract class Player implements PlayerIO {

    private char token;
    private String name;
    private int winCounter=0;


    public Player(char token, String name){
        this.name = name;
        this.token = token;
    }

    public int getUserInput(){
        return 999; //We will use 999 as an invalid move
    }

    public char getToken(){
        return token;
    }

    public String getName(){
        return name;
    }

    public void incWins(){
        winCounter++;
    }

    public int getWins(){
        return winCounter;
    }

}