/* All boards need to have the following at least
 * Some games may have more complex moves
 */
interface Board {

    /* set the board to start state */
    public void initBoard();

    /* set the board to start state */
    public boolean canMove(); //some games may have a draw condition

    /* set the board to start state */
    public boolean makeMove(int x, char token); //make the move

    /* set the board to start state */
    public boolean checkWin(char token); //check if that player won

    //public void displayBoard();
    /* Original code assumed the board should display itself.
     * But it might be desired to display the board on webpage/gui/etc
     * So instead a "getBoard() method will be implemented.
     * his will also be used for players (AI/remote human) to "see" board state or
     * for Display objects to see "see" the board state */
    public char[][] getBoard();
}
