import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;


public class SudokuBruteForceTester {
	private int[][] sixBySix;
	
	@Before
	public void setUp() throws Exception {
		 sixBySix = new int[][]{{1,4,2,5,3,6},{0,0,0,0,0,0},{3,6,1,4,2,5},{4,0,0,2,0,0},{0,0,6,0,4,0},{0,3,0,0,0,2}};
	}

	@Test 
	public void testPuzzle(){
		Scanner fileScanner;
		try {
			//Sets up the board
			fileScanner = new Scanner(new FileReader("6x6SemiSolved.txt"));
			String input;
			int width, length, boardSize;
			int[][] board;

			while(true){
				input = fileScanner.nextLine();
				if (!(input.startsWith("c") || input.startsWith("C"))){
					break;
				}
			}
			width = Integer.parseInt(input);
			length = fileScanner.nextInt();

			boardSize = length * width;
			board = new int[boardSize][boardSize];

			for (int w = 0; w < boardSize; w++){
				for (int l = 0; l < boardSize; l++){
					board[w][l] = fileScanner.nextInt();
				}
			}

			
			//End board set up
			
			//Prints out the board
			for (int w = 0; w < boardSize; w++){
				for (int l = 0; l < boardSize; l++){
					System.out.print(board[w][l] + " ");
				}
				System.out.println();
			}
			System.out.println();
			
			//Creates the actual solver
			SudokuBruteForce solver = new SudokuBruteForce(width, length, board);
			
			//Sees if the solver has a solution
			if(solver.findSolution()){
				System.out.println("Solution Found!");
				board = solver.getSolution();

				for (int w = 0; w < boardSize; w++){
					for (int l = 0; l < boardSize; l++){
						System.out.print(board[w][l] + " ");
					}
					System.out.println();
				}

				assertTrue(!solver.duplicates(board));
			}
			else{
				System.out.println("Solution not found :(");
			}

			
			
		} catch (FileNotFoundException e) {
			fail();
		}

	}

}
