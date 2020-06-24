class ConnectN implements Board {

    private char[][] board;
    private int winValue = 4;
    private int maxX = 7;
    private int maxY = 6;
    private int maxMoves = maxX * maxY;
    private int moveCounter = 0;
    private final char blankSpace = ' '; //In case blank spaces are not cool

    /* Construct default Connect 4*/
    public ConnectN() {
        board = new char[maxX][maxY];
    }

    /* Construct Connect N and check values are reasonable */
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
    /* Initialise the board */
    public void initBoard() {
        moveCounter = 0;
        for (int i = 0; i < maxX; i++) {
            for (int j = 0; j < maxY; j++) {
                board[i][j] = blankSpace;
            }
        }
    }

    @Override
    /* Check if the board is at a full state or not */
    public boolean canMove() {
        if (moveCounter < maxMoves) {
            return true;
        }
        return false;
    }

    @Override
    /* place the token at position x */
    public boolean makeMove(int x, char token) {
        x = x - 1; // change x from user domain to java indexing domain.
        boolean spaceAvailable = false;
        int yPos = -1;
        /* Check if token can be place at desired position */
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
    /* Check if the user's move was a winning one */
    public boolean checkWin(char player) {
        return checkHorizontal(player) || checkVertical(player) || checkDiagonal3(player);
    }

    @Override
    /* return the board state */
    public char[][] getBoard() {
        return this.board;
    }

    /* Check rows */
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

    /* Check columns */
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

    /* Check leading & back diagonals  */
    private boolean checkDiagonal3(char player) {
        int numberOfMatchesDiag1 = 0;
        int numberOfMatchesDiag2 = 0;
        for (int i = -(maxY - 1); i < maxX; i++) {
            for (int j = 0; j < maxY; j++) {
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
            numberOfMatchesDiag1 = 0;
            numberOfMatchesDiag2 =0;
        }
        return false;
    }

    /* Method used by the checkDiagonal3() method to make sure there is
     * no attempt to index outside of the array limits. */
    private boolean isWithinBounds(int x, int y) {
        return !((x >= maxX || x < 0 || y < 0 || y >= maxY));
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

}
