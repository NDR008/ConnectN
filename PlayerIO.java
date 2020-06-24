public interface PlayerIO {

    /* All players need to give a value to the board
     * Values start from 1 (not 0) */
    public int getUserInput();

    /* Players need to show their token */
    public char getToken();

    /* Players keep track of their scores */
    public void incWins();

    /* Players show how many wins */
    public int getWins();

    /* Players show their name */
    public String getName();

    /* Not used players can be give a snapshot of the board */
    public void seeBoard(char[][] board);
}