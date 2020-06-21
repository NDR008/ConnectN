class ConnectN implements Board {

    private char[][] board;
    private int winValue = 4;
    private int maxX = 7;
    private int maxY = 6;
    private int maxMoves = maxX * maxY;
    private int moveCounter = 0;
    private final char blankSpace = ' '; //In case blank spaces are not cool
    private final String padding = "    ";

    // Colors for terminal/console output
    public class Colors {
        public static final String RESET = "\033[0m";  // Text Reset
        public static final String RED = "\033[1;31m";
        public static final String GREEN = "\033[1;32m";
        public static final String BLUE = "\033[1;34m";
        public static final String PURPLE = "\033[1;35m";
    }

    public ConnectN() {
        board = new char[maxX][maxY];
    }

    public ConnectN(int x, int y, int nConnect) {
        if (nConnect > x) {
            x = nConnect; // if nConnect is 4, a grid of 0..4 is already 1 element larger
            System.out.println("Overriding number of columns to " + x);
        }
        maxX = x;
        if (nConnect > y) {
            y = nConnect;
            System.out.println("Overriding number of rows to " + y);
        }
        maxY = y;
        board = new char[x][y];
        winValue = nConnect;
        maxMoves = maxX * maxY;
    }

    @Override
    public void initBoard() {
        moveCounter = 0;
        for (int i = 0; i < maxX; i++) {
            for (int j = 0; j < maxY; j++) {
                board[i][j] = blankSpace;
            }
        }

    }

    @Override
    public boolean canMove() {
        if (moveCounter < maxMoves) {
            return true;
        }
        return false;
    }

    @Override
    public boolean makeMove(int x, char token) {
        x = x - 1;
        boolean spaceAvailable = false;
        int yPos = -1;
        if (x >= 0 && x <= (maxX - 1)) {
            for (int j = (maxY - 1); j >= 0; j--) {
                if (board[x][j] == blankSpace) {
                    spaceAvailable = true;
                    yPos = j;
                }
            }
            if (spaceAvailable) {
                board[x][yPos] = token;
                moveCounter++;
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean checkWin(char player) {
        return checkHorizontal(player) || checkVertical(player) || checkDiagonal3(player);
    }

    @Override
    public void displayBoard() {
        System.out.print(Colors.RED);
        System.out.print(padding + "Game move count is... " + moveCounter + "   ");
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
                System.out.print(Colors.GREEN + board[i][j]);
            }
            System.out.print(Colors.BLUE + "|");
            System.out.print(Colors.RESET);
            System.out.println();
        }
        System.out.print(Colors.GREEN);
        System.out.print(padding + "X ");
        for (int i = 1; i <= maxX; i++) {
            System.out.print(Colors.BLUE + " " + Colors.GREEN + i);
        }
        System.out.print(Colors.BLUE + " ");
        System.out.println();
        System.out.print(Colors.PURPLE + "-------------------------------");
        System.out.println(Colors.RESET);
    }

    private boolean checkHorizontal(char player) {
        int numberOfMatches = 0;
        for (int j = 0; j < maxY; j++) {
            for (int i = 0; i < maxX; i++) {
                if (board[i][j] == player) {
                    numberOfMatches++;
                } else {
                    numberOfMatches = 0;
                }
                if (numberOfMatches >= winValue) {
                    return true;
                }
            }
            numberOfMatches = 0;
        }
        return false;
    }

    private boolean checkVertical(char player) {
        int numberOfMatches = 0;
        for (int i = 0; i < maxX; i++) {
            for (int j = 0; j < maxY; j++) {
                if (board[i][j] == player) {
                    numberOfMatches++;
                } else {
                    numberOfMatches = 0;
                }
                if (numberOfMatches >= winValue) {
                    return true;
                }
            }
            numberOfMatches = 0;
        }
        return false;
    }

    /*
    // checkDiagonal1() integrated into checkDiagonal3()
    private boolean checkDiagonal1(char player) {
        int numberOfMatches = 0;
        for (int i = -(maxY - 1); i < maxX; i++) {
            //System.out.println(i + " ");
            for (int j = 0; j < maxY; j++) {
                //System.out.println("Diag 1: " + (i + j+1) + " " + (j+1));
                if (!isWithinBounds(i + j, j)) {
                    continue;
                }
                if (board[i + j][j] == player) {
                    numberOfMatches++;
                } else {
                    numberOfMatches = 0;
                }
                if (numberOfMatches >= winValue) {
                    return true;
                }
            }
            //System.out.println();
            numberOfMatches = 0;
        }
        return false;
    }

    // checkDiagonal2() integrated into checkDiagonal3()
    private boolean checkDiagonal2(char player) {
        int numberOfMatches = 0;
        for (int i = -(maxY - 1); i < maxX; i++) {
            //System.out.println(i + " ");
            for (int j = 0; j < maxY; j++) {
                //System.out.println("Diag 2: " + (i + j+1) + " " + ((maxY-1)-j+1));
                if (!isWithinBounds(i + j, (maxY-1)-j)) {
                    continue;
                }
                if (board[i + j][(maxY-1)-j] == player) {
                    numberOfMatches++;
                } else {
                    numberOfMatches = 0;
                }
                if (numberOfMatches >= winValue) {
                    return true;
                }
            }
            //System.out.println();
            numberOfMatches = 0;
        }
        return false;
    }
    */

    private boolean checkDiagonal3(char player) {
        int numberOfMatchesDiag1 = 0;
        int numberOfMatchesDiag2 = 0;
        for (int i = -(maxY - 1); i < maxX; i++) {
            for (int j = 0; j < maxY; j++) {
                /*if(true) { //Code to debug
                    System.out.print("Diag1: " + (i + j + 1) + " " + ((maxY - 1) - j + 1));
                    System.out.print("  ");
                    System.out.println("Diag2: " + (i + j + 1) + " " + (j + 1));
                }*/
                if (isWithinBounds(i + j, (maxY-1)-j)) {
                    if (board[i + j][(maxY-1)-j] == player) {
                        numberOfMatchesDiag1++;
                    } else {
                        numberOfMatchesDiag1 = 0;
                    }
                    if (numberOfMatchesDiag1 >= winValue) {
                        return true;
                    }
                }

                if (isWithinBounds(i + j, j)) {
                    if (board[i + j][j] == player) {
                        numberOfMatchesDiag2++;
                    } else {
                        numberOfMatchesDiag2 = 0;
                    }
                    if (numberOfMatchesDiag2 >= winValue) {
                        return true;
                    }
                }
            }
            //System.out.println();
            numberOfMatchesDiag1 = 0;
            numberOfMatchesDiag2 =0;
        }
        return false;
    }

    private boolean isWithinBounds(int x, int y) {
        return !((x >= maxX || x < 0 || y < 0 || y >= maxY));
    }
}
