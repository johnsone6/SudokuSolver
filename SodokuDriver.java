import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Project members: Chris Houze, Nick Perkins, Erica Johnson
 *
 */

public class SudokuDriver {
	private static int MAX_TRIES = 3;
	
	public static void main(String[] args) {
		//Variable declarations
		int[][] board;
		int  width, length, boardSize;
		Scanner console = new Scanner(System.in);
		Scanner fileScanner;
		String input;
		SudokuBruteForce solver;
		
		
		
		//Main loop for user input
		for (int i = 0; i < MAX_TRIES; i++){
			try {
				System.out.print("Enter the name of the file to be processed: ");
				
				fileScanner = new Scanner(new FileReader(console.next()));
				//Skips past the commented lines at the beginning of the file
				while(true){
					input = fileScanner.nextLine();
					if (!(input.startsWith("c") || input.startsWith("C"))){
						break;
					}
				}
				
				//Reads the dimensions of the board
				width = Integer.parseInt(input);
				length = fileScanner.nextInt();
				
				boardSize = length * width;
				board = new int[boardSize][boardSize];
				
				
				//Initializes the board
				for (int w = 0; w < boardSize; w++){
					for (int l = 0; l < boardSize; l++){
						board[w][l] = fileScanner.nextInt();
					}
				}
				
				//Prints out the board, mainly for testing purposes, these loops may be commented out
				printBoard(board);
				
				//Creates the solver object
				solver = new SudokuBruteForce(width, length, board);
				
				
				
				
				if(solver.findSolution()){
					System.out.println("Solution found: ");
					board = solver.getSolution();
					printBoard(board);
				}
				else{
					System.out.println("No solution found");
				}
					
				
					
				
				console.close();
				break;
				
				
			}
			catch (FileNotFoundException e1){
				System.out.println("Could not locate file, you have " + (MAX_TRIES - (i + 1)) + " tries left.\n");
			}
			catch (InputMismatchException | NumberFormatException e2){
				System.out.println("There was an error processing the file, please make sure you" +
						"\nplease make sure you specified the correct file, or try another file." + 
						"\nYou have " + (MAX_TRIES - (i + 1)) + " tries left.\n");
				
			}
		}
	}
	
	
	
	
	private static void printBoard(int[][] board){
		for (int w = 0; w < board.length; w++){
			for (int l = 0; l < board.length; l++){
				System.out.print(board[w][l] + " ");
			}
			System.out.println();
		}
	}
	
	
}
