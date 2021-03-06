import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

/**
 * Project members: Chris Houze, Nick Perkins, Erica Johnson
 * @author Chris Houze
 *
 */

public class SudokuDriver {
	private static int MAX_TRIES = 3;
	
	public static void main(String[] args) {
		int[][] board;
		int  width, length, boardSize;
		Scanner console = new Scanner(System.in);
		Scanner fileScanner;
		String input;
		SudokuBruteForce solver;
		
		for (int i = 0; i < MAX_TRIES; i++){
			try {
				System.out.println("Enter the name of the file to be processed");
				fileScanner = new Scanner(new FileReader(console.next()));
				
				while(true){
					input = fileScanner.nextLine();
					if (!(input.startsWith("c") || input.startsWith("C"))){
						break;
					}
				}
				width = Integer.parseInt(input);
				length = fileScanner.nextInt();
				
				//for testing purposes
				System.out.println("width: " + width);
				System.out.println("length: " + length);
				
				boardSize = length * width;
				board = new int[boardSize][boardSize];
				
				for (int w = 0; w < boardSize; w++){
					for (int l = 0; l < boardSize; l++){
						board[w][l] = fileScanner.nextInt();
					}
				}
				
			/////////////////////  this tests Erica's part ////////////////	
			boolean solved = false;
				
				while (solved == false){
					solver = new SudokuBruteForce(width, length, board);
					//Calculating magic integer to check against
					int magicInt = solver.calcSumInt(boardSize);
					System.out.println("Check Solutions against sum= " + magicInt);
					//Checking magic integer against actually puzzle sum
					boolean sumValid = solver.checkPuzzleSum(board, magicInt);
					//boolean sumValid = true;
					System.out.println("Sum is correct: " + sumValid);
					//Checking for duplicates...
					if(sumValid == true){
						boolean dupeNums = solver.duplicateCheck(board);
						if(dupeNums == false){
							solved = true;
						}
					}
					else{
						solved = false;
						//TODO start over
					}
				}
				///////////////////
				
				
				console.close();
				break;
			}
			catch (FileNotFoundException e1){
				System.out.println("Could not locate file, you have " + (MAX_TRIES - (i + 1)) + " tries left.");
			}
		}
	}
	
	
}
