
package ui;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Visualizer {

    private Scanner reader;
    private boolean needMove;
    private int move;
    
    public Visualizer() {
        reader = new Scanner(System.in);
    }
    
    public void displayBoard(char mBoard[])	{
		System.out.println();
		System.out.println(mBoard[0] + " | " + mBoard[1] + " | " + mBoard[2]);
		System.out.println("-----------");
		System.out.println(mBoard[3] + " | " + mBoard[4] + " | " + mBoard[5]);
		System.out.println("-----------");
		System.out.println(mBoard[6] + " | " + mBoard[7] + " | " + mBoard[8]);
		System.out.println();
	}
    
    public int getUserMove(int boardSize) 
	{
            // Eclipse throws a NullPointerException with Console.readLine
            // Known bug: https://bugs.eclipse.org/bugs/show_bug.cgi?id=122429
            //Console console = System.console();

            needMove = true;

            while (needMove) {			
                try {
                    System.out.print("Enter your move: ");
                    move = reader.nextInt();
                    if (move < 1 || move > 9) {
                        System.out.println("Please enter a number between 1 and " + boardSize + ".");
                    }else{
                        needMove = false;
                        return move;
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Wrong Entry ");
                    reader.next();  // Get next line so we start fresh
                }
            }
            
            return 0;
	}

    public void displayEndGame(int win) {
        System.out.println();
        
        switch(win){
            case 1: 
                System.out.println("It's a tie.");
                break;
            case 2: 
                System.out.println('X' + " wins!");
                break;
            case 3: 
                System.out.println('O' + " wins!");
                break;
            default:
                System.out.println("There is a logic problem!");
        }
    }
}
