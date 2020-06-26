import java.io.BufferedReader; //Google style guide recommends not using wildcards.
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class GameSession{
    private final ConnectN board;

    public GameSession(){
        board = new ConnectN();
    }

    public GameSession(int x, int y, int connectN){
        board = new ConnectN(x,y,connectN);
    }

    /* Main method */
    public void playGame() {
        Display displayObj;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String userContinue = "Y";
        int move;

        /* This array list and contents should really become its own method or class in the future
         * With the option for multiple dynamic number of players and player type */
        ArrayList<Player> players = new ArrayList<>();
        players.add(new PlayerAI('J', "Joshua (WOPR)"));
        players.add(new PlayerAI('S', "Sky Net System"));
        players.add(new PlayerHuman('M', "Michael Knight"));
        //players.add(new PlayerAI('B', "James Bond"));
        int numberOfPlayers = players.size();
        int currentIndex = 0;
        Player currentPlayer = players.get(currentIndex);

        /* Main Loop */
        while (userContinue.equals("Y")) {
            board.initBoard();
            displayObj = new Display(board.getBoard());
            System.out.println();
            System.out.println("Welcome to the Game");
            System.out.println("Players may quit by entering q or Q at their turn ");
            System.out.println();
            displayObj.displayBoard(board.getBoard());
            boolean nextMove;
            while (true) {
                currentPlayer.getBoard(board.getBoard());
                System.out.println("Player " + currentPlayer.getName() + " makes the next move");
                do {
                    move = currentPlayer.getUserInput();
                    nextMove = !board.makeMove(move, currentPlayer.getToken());
                    if(nextMove){
                        System.out.println("Your move was invalid. Please enter a valid move.");
                    }
                } while (nextMove);

                displayObj.displayBoard(board.getBoard());
                if (board.checkWin(currentPlayer.getToken())) {
                    currentPlayer.incWins();
                    System.out.println("WOW!! Player " + currentPlayer.getName() + " has won!!!");
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
            System.out.println("---------Score Board---------");
            for (int i =0; i<numberOfPlayers; i++){
                System.out.println("Total wins for "+ players.get(i).getName() + ":\t" + players.get(i).getWins() + " wins");
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