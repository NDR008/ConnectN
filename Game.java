import java.io.*;
import java.util.ArrayList;

public class Game{

    private ConnectN board;
    int x=7; //default board width

    /* Construct without arguments */
    public Game(){
        board = new ConnectN();
    }

    /* Construct with arguments */
    public Game(int x, int y, int connectN){
        board = new ConnectN(x,y,connectN);
        this.x=x;
    }

    /* Main method */
    public void playGame() {
        Display display;
        int numberOfPlayers;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String userContinue = "Y";

        ArrayList<Player> players = new ArrayList<>();
        players.add(new PlayerHuman('M', "Michael Knight"));
        players.add(new PlayerAI('J', "Joshua (WOPR)", x));
        players.add(new PlayerAI('W', "Sky Net", x));
        //players.add(new PlayerAI('@', "Bond"));
        numberOfPlayers = players.size();

        int currentIndex = 0;
        Player currentPlayer = players.get(currentIndex);
        int move;

        /* Main Loop */
        while (userContinue.equals("Y")) {
            board.initBoard();
            display = new Display(board.getBoard());
            System.out.println();
            System.out.println("Welcome to the Game");
            System.out.println("Players may quit by entering q or Q at their turn ");
            System.out.println();
            display.displayBoard(board.getBoard());
            boolean nextMove;
            /* In the future, after each round, each player should be given a seeBoar()
             * That way they can display the game on their own end and
             * AI make compute more intelligently how to win. */
            while (true) {
                System.out.println("Player " + currentPlayer.getName() + " makes the next move");
                do {
                    move = currentPlayer.getUserInput();
                    nextMove = !board.makeMove(move, currentPlayer.getToken());
                    if(nextMove){
                        System.out.println("Please enter a valid move.");
                    }
                } while (nextMove);

                display.displayBoard(board.getBoard());
                if (board.checkWin(currentPlayer.getToken())) {
                    currentPlayer.incWins();
                    System.out.println("WOW!! Player " + currentPlayer.getName() + " has won!!!");
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
                System.out.println("Total wins for "+ players.get(i).getName() + ": " + players.get(i).getWins() + " wins");
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