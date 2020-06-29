/* This class' logic was previously contained in ConnectN
 * Some of it seems redundant such as determining the size of the grid.
 * It is thus slightly more wasteful on resource, but makes the class
 * more independent of each other.
 */

public class Display {
    private final int maxX;
    private final int maxY;

    /* Constructor of the display */
    public Display(char[][] board){
        maxX = board.length;
        maxY = board[0].length;
    }

    /* Colors used for the terminal print out */
    public static class Colors {
        public static final String RESET = "\033[0m";  // Text Reset
        public static final String RED = "\033[1;31m";
        public static final String GREEN = "\033[1;32m";
        public static final String BLUE = "\033[1;34m";
        public static final String PURPLE = "\033[1;35m";
    }

    /* Pure terminal print out */
    public void displayBoard(char[][] board) {
        char blankSpaceFiller = '_';
        String padding = "    ";
        System.out.print(Colors.RED);
        System.out.print(Colors.RESET);
        System.out.println();
        System.out.print(Colors.PURPLE);
        System.out.print("-------------------------------");
        System.out.println(Colors.RESET);
        for (int j = maxY - 1; j >= 0; j--) {
            System.out.print(Colors.GREEN);
            System.out.print(padding + (j + 1) + " ");
            for (int i = 0; i < maxX; i++) {
                System.out.print(Colors.BLUE + "|");
                if (board[i][j]==' '){
                    System.out.print(Colors.GREEN + blankSpaceFiller);
                }
                else{
                    System.out.print(Colors.GREEN + board[i][j]);
                }
            }
            System.out.print(Colors.BLUE + "|");
            System.out.print(Colors.RESET);
            System.out.println();
        }
        System.out.print(Colors.GREEN);
        System.out.print(padding + "/ ");
        for (int i = 1; i <= maxX; i++) {
            System.out.print(Colors.BLUE + " " + Colors.GREEN + i);
        }
        System.out.print(Colors.BLUE + " ");
        System.out.println();
        System.out.print(Colors.PURPLE + "-------------------------------");
        System.out.println(Colors.RESET);
    }

    public void displayScores(String playerName, int wins){
        System.out.println(Colors.BLUE + "Total wins for " + Colors.GREEN + playerName
                + Colors.BLUE + ":\t" + Colors.RED + wins + Colors.BLUE +  " wins");
        System.out.print(Colors.RESET);
    }

}
