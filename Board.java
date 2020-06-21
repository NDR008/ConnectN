/* All boards need to have the following at least
 * Some games may have more complex moves
 */
interface Board {

    public void initBoard(); //set the board to start state

    public boolean canMove(); //some games may have a draw condition

    public boolean makeMove(int x, char token); //make the move

    public boolean checkWin(char token); //check if that player won

    public void displayBoard();

}
