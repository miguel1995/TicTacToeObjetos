/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businessLogic;

/* TicTacToeConsole.java
 * By Frank McCown (Harding University)
 * 
 * This is a tic-tac-toe game that runs in the console window.  The human
 * is X and the computer is O. 
 */
import data.Board;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;
import ui.Visualizer;

public class TicTacToeBeta {

    public static final char HUMAN_PLAYER = 'X';
    public static final char COMPUTER_PLAYER = 'O';

    

    public TicTacToeBeta() {

    }

    static private boolean checkForMoves(Board board, char humanPlayer, char computerPlayer) {
        for (int i = 0; i < board.getBoardSize(); i++) {
            // If we find a number, then no one has won yet
            if (board.getElement(i) != humanPlayer && board.getElement(i) != humanPlayer) {
                return true;
            }
        }

        return false;
    }

    static private boolean checkForWinner(Board board, char player) {

        // Check horizontal wins
        for (int i = 0; i <= 6; i += 3) {
            if (board.getElement(i) == player
                    && board.getElement(i + 1) == player
                    && board.getElement(i + 2) == player) {
                return true;
            }
        }

        // Check vertical wins
        for (int i = 0; i <= 2; i++) {
            if (board.getElement(i) == player
                    && board.getElement(i + 3) == player
                    && board.getElement(i + 6) == player) {
                return true;
            }
        }

        // Check for diagonal wins
        if ((board.getElement(0) == player
                && board.getElement(4) == player
                && board.getElement(8) == player)
                || (board.getElement(2) == player
                && board.getElement(4) == player
                && board.getElement(6) == player)) {
            return true;
        }

        return false;
    }

    // Check for a winner.  Return
    //  0 if no winner or tie yet
    //  1 if it's a tie
    //  2 if X won
    //  3 if O won
    static private int checkForWinner(Board board) {

        //check if human is the winner
        if (checkForWinner(board, HUMAN_PLAYER)) {
            return 2;
        }

        //check if computer is the winner
        if (checkForWinner(board, COMPUTER_PLAYER)) {
            return 3;
        }

        // Check if there are possible moves
        if (checkForMoves(board, HUMAN_PLAYER, COMPUTER_PLAYER)) {
            return 0;
        }

        // If we make it through the previous loop, all places are taken, so it's a tie
        return 1;
    }

    static private int getComputerMove(Board board) {
        int index;
        Random mRand=new Random();

        // First see if there's a move O can make to win
        for (int i = 0; i < board.getBoardSize(); i++) {
            if (board.getElement(i) != HUMAN_PLAYER && board.getElement(i) != COMPUTER_PLAYER) {
                char curr = board.getElement(i);
                board.putMove(COMPUTER_PLAYER, i+1);
                if (checkForWinner(board) == 3) {
                    return i+1;
                } else {
                    board.putMove(curr, i+1);
                }
            }
        }

        // See if there's a move O can make to block X from winning
        for (int i = 0; i < board.getBoardSize(); i++) {
            if (board.getElement(i) != HUMAN_PLAYER && board.getElement(i) != COMPUTER_PLAYER) {
                char curr = board.getElement(i);   // Save the current number
                board.putMove(HUMAN_PLAYER, i+1);
                if (checkForWinner(board) == 2) {
                    return i+1;
                   
                } else {
                     board.putMove(curr, i+1);
                }
            }
        }

        // Generate random move
        do {
            index = mRand.nextInt(board.getBoardSize());
        } while (board.getElement(index) == HUMAN_PLAYER || board.getElement(index) == COMPUTER_PLAYER);

        return index+1;
    }

    /**
     * @param args
     */
    public static void main(String[] args) {

        int move = 0;
        int win = 0;
        char turn;

        turn = HUMAN_PLAYER;

        Board board = new Board();
        Visualizer visualizer = new Visualizer();

        while (win == 0) {

            if (turn == HUMAN_PLAYER) {
                visualizer.displayBoard(board.getBoard());
                move = visualizer.getUserMove(board.getBoardSize());
                board.putMove(turn, move);
                turn = COMPUTER_PLAYER;
            } else {
                move = getComputerMove(board);
                board.putMove(turn, move);
                System.out.println("Computer is moving to " + (move));
                turn = HUMAN_PLAYER;
            }
            
            win = checkForWinner(board);
        }

        visualizer.displayBoard(board.getBoard());
        visualizer.displayEndGame(win);
    }
}
