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



	/*
	@Test
	public void testArrayCounter(){
		ArrayCounter c = new ArrayCounter(10, 10);
		//int num[];
		long max = (long)Math.pow(10, 10);
		for(long i = 0; i < max ; i++){
			c.count();
		}
	}

	 */
	
	/*
	@Test
	public void testArrayCounter2(){
		ArrayCounter c = new ArrayCounter(13, 6);
		int num[];
		num = c.count();
		for (int j = 0; j < num.length; j++){
			System.out.print(num[j] + " ");
		}
		System.out.println();
		while(c.hasNext()){
			num = c.count();
		}
		for (int j = 0; j < num.length; j++){
			System.out.print(num[j] + " ");
		}
		System.out.println();
	}
*/

	
	
	/*
	@Test
	public void testCounter(){
		Counter c2 = new Counter(10, 10);
		long max = (long)Math.pow(10, 10);
		for(long i = 0; i < max ; i++)
			c2.count();
	}

	 */

	@Test 
	public void testPuzzle(){
		Scanner fileScanner;
		try {
			fileScanner = new Scanner(new FileReader("puzzle1.txt"));
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

			for (int w = 0; w < boardSize; w++){
				for (int l = 0; l < boardSize; l++){
					System.out.print(board[w][l] + " ");
				}
				System.out.println();
			}
			System.out.println();
			SudokuBruteForce solver = new SudokuBruteForce(width, length, board);
			System.out.println(solver.findSolution());

			board = solver.getSolution();

			for (int w = 0; w < boardSize; w++){
				for (int l = 0; l < boardSize; l++){
					System.out.print(board[w][l] + " ");
				}
				System.out.println();
			}
			
			assertTrue(solver.noDuplicates(board));
		} catch (FileNotFoundException e) {

		}

	}

}
