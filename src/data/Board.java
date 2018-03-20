
package data;

public class Board {

    private char board[];

    public Board() {
        board = new char[]{'1','2','3','4','5','6','7','8','9'};
    }
    
    public char[] getBoard() {
        return board;
    }
    
    public int getBoardSize(){
        return board.length;
    }
    
   
    public void putMove(char player, int move) {
        board[move-1] = player;
    }
    
    public char getElement(int index){
        return board[index];
    }
    
}
