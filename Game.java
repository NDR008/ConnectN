import java.io.*;
import java.util.ArrayList;

public class Game{

    private ConnectN board;
    private int numberOfPlayers;
    int x=7;
    public Game(){
        board = new ConnectN();
    }
    public Game(int x, int y, int connectN){
        board = new ConnectN(x,y,connectN);
    }

    public void playGame() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String userContinue = "Y";

        ArrayList<Player> players = new ArrayList<>();

        players.add(new PlayerAI('J', "Joshua (WOPR)"));
        players.add(new PlayerAI('W', "Sky Net"));
        players.add(new PlayerHuman('M', "Michael Knight"));
        //players.add(new PlayerAI('@', "Bond"));
        numberOfPlayers = players.size();

        int currentIndex = 0;
        Player currentPlayer = players.get(currentIndex);
        int move;

        while (userContinue.equals("Y")) {
            board.initBoard();
            System.out.println();
            System.out.println("Welcome to the Game");
            System.out.println();
            board.displayBoard();
            boolean nextMove;
            while (true) {
                System.out.println("Player " + currentPlayer.getName() + " makes the next move");
                do {
                    move = currentPlayer.getUserInput();
                    nextMove = !board.makeMove(move, currentPlayer.getToken());
                } while (nextMove);

                board.displayBoard();
                if (board.checkWin(currentPlayer.getToken())) {
                    currentPlayer.incWins();
                    System.out.println("OMG! Player " + currentPlayer.getName() + " has won!!!");
                    System.out.println("Their score is " + currentPlayer.getWins());
                    break;
                }
                if (!board.canMove()) {
                    System.out.println("There are no more moves to be played");
                    break;
                }
                currentIndex++;
                if(currentIndex>numberOfPlayers-1){
                    currentIndex=0;
                }
                currentPlayer = players.get(currentIndex);
            }
            for (int i =0; i<numberOfPlayers; i++){
                System.out.println("Total wins for "+ players.get(i).getName() + " won... " + players.get(i).getWins() + " wins");
            }
            System.out.println();
            userContinue="";
            try {
                do {
                    System.out.println("Shall we play another round? (Y/n)");
                    System.out.println("(Last player starts)");
                    userContinue = reader.readLine();
                } while (!(userContinue.equals("Y") || userContinue.equals("n")));
            } catch (IOException e) {
                System.err.println(e.getMessage());
                System.exit(1);
            }
        }
    }
}