import java.util.HashSet;


public class SudokuBruteForce {

	private int[][] board, blanks, finalSolution;
	private int width, length, boardSize, magicInt, numBlanks, initialTotal, upperLimit, puzzleSum;
	private long maximumTries;
	private boolean solutionFound;
	


	/**
	 * Constructor
	 * @param width the width of the inner boxes of the puzzle
	 * @param length the length of the inner boxes of the puzzle
	 * @param board the initial, unsolved board
	 */
	public SudokuBruteForce(int width, int length, int[][] board){
		this.width = width;
		this.length = length;
		this.board = board;
		this.boardSize = width * length;
		this.upperLimit = boardSize;
		this.solutionFound = false;

		magicInt = calcSumInt(boardSize);
		initialTotal = findInitialPuzzleSum();
		findBlanks();

		//	this.maximumTries = (int)Math.pow(boardSize, numBlanks);
		long temp = 1;
		for (int i = 0; i < numBlanks; i++){
			temp = temp * boardSize;
		}
		maximumTries = temp;
		
		
		System.out.println(maximumTries + " possible combinations");
		//counter = new ArrayCounter(numBlanks, boardSize);
	}


	/**
	 * Gets the solution to the Sudoku board
	 * @return null if the board has not been found, or the completed board
	 */
	public int[][] getSolution(){
		if (solutionFound){
			return this.finalSolution;
		}
		else{
			return null;
		}
	}

	
	public boolean hasSolution(){
		return solutionFound;
	}



	/**
	 * This method attempts to find the solution to the given Sudoku board
	 * @return true if the board has a solution, false if it does not
	 */
	public boolean findSolution(){
		long current = 0;
		//Checks in case the puzzle is already solved
		fillInSolution();
		puzzleSum = findInitialPuzzleSum();
		if (solutionFound || !duplicates(board)){
			return true;
		}


		//Loops as long as there is a new number to use
		while (hasNext()){
			
			count();
			//fillInSolution();
			
			//Test, this displays the percentage of the numbers that have been checked
			
			current++;
			if ((current % 1000000000) == 0){
				System.out.println((((double)current / (double)maximumTries) * 100d)+ "% of numbers tried");
			}
			//end test
			
			if(puzzleSum == magicInt){
				if(!duplicates(board)){
					createFinalSolution(board);
					solutionFound = true;
					return true;
				}
			}
		}
		return false;
	}



	/**
	 * Fills all the unknown spaces in the board with a starting value
	 */
	public void fillInSolution(){
		for (int i = 0; i < numBlanks; i++){
			board[blanks[i][0]][blanks[i][1]] = 1;
		}
	}
	

	
	/**
	 * This method now computes the next number directly into the board
	 * and determines the sum of the puzzle at the same time
	 * 
	 */
	public void count() 
	{
		int count = numBlanks - 1;
		
		while(board[blanks[count][0]][blanks[count][1]] == upperLimit)
		{
			board[blanks[count][0]][blanks[count][1]] = 1;
			puzzleSum -= (upperLimit - 1);
			count--;
		}
		
		board[blanks[count][0]][blanks[count][1]]++;
		puzzleSum++;
	}
	
	/**
	 * Determines whether the counter has reached the end 
	 * @return whether or not there is another number which can be generated
	 */
	public boolean hasNext(){
		for (int i = numBlanks - 1; i >= 0; i--){
			if (board[blanks[i][0]][blanks[i][1]] != upperLimit){
				return true;
			}
		}
		return false;
	}
	


	/**
	 * This method finds the blanks and stores them in a 2D array
	 * blanks.length will get you the number of blanks in the main puzzle
	 * the coordinates of each blank are: 
	 * x = blanks[(counter variable)][0]
	 * y = blanks[(counter variable)][1]
	 */
	private void findBlanks(){
		int blankCount = 0;

		//Finds the number of blank spaces in the puzzle
		for (int i = 0; i < boardSize; i++){
			for (int j = 0; j < boardSize; j++){
				if (board[i][j] == 0){
					blankCount++;
				}
			}
		}

		numBlanks = blankCount;
		
		//Uses the number of blank spaces to determine the correct size needed for the array
		blanks = new int[blankCount][2];
		blankCount = 0;
		
		//Adds the coordinates of each blank space to the array
		for (int i = 0; i < boardSize; i++){
			for (int j = 0; j < boardSize; j++){
				if (board[i][j] == 0){
					blanks[blankCount][0] = i;
					blanks[blankCount][1] = j;
					blankCount++;
				}
			}
		}
	}


	/**
	 * Finds the number which all correct Sudoku of this size will add up to
	 * @param boardSize the board to be checked
	 * @return the number which all Sudoku matrices of these dimensions will add up to
	 */
	public int calcSumInt(int boardSize){
		int bs = boardSize;
		int sumRowInt;
		int sumInt;
		sumRowInt = (((bs*bs) + bs)/2);
		sumInt = sumRowInt * bs;
		return sumInt;
	}

	
	/**
	 * Sums the actual values of puzzle, compares to the puzzles magic integer, then returns
	 * it's validity
	 * @param board the board to be checked
	 * @param magicInt the number that all correct matrices will add up to
	 * @return	whether or not the sum of this board matches the target sum
	 */
	
	/**
	 * This method finds the total sum of all the numbers given in the initial board
	 * @return the sum of all given entries in the puzzle
	 */
	public int findInitialPuzzleSum() {
		int sum = 0;

		for(int i = 0; i < board.length; i++){
			for(int j = 0; j < board[i].length; j++){
				sum = sum + board[i][j];
			}
		}
		
		return sum;
	}
	
	
	/**
	 * Copies the final board into a separate 2D array so we don't lose it
	 * @param board the board to be copied
	 */
	private void createFinalSolution(int[][] board){
		finalSolution = new int[boardSize][boardSize];
		
		for (int i = 0; i < boardSize; i++){
			for (int j = 0; j < boardSize; j++){
				finalSolution[i][j] = board[i][j];
			}
		}
	}
	
	
	/**
	 * checks duplicates
	 * calls methods to run all three checks (columns, rows, boxes)
	 * @param board the board to be checked
	 * @return true if the board does not contain duplicates, false if the board contains duplicates
	 */
	public boolean duplicates(int board[][]){
		//boolean dupeNums = false;
		//int[][] b = board;

		return (checkColumns(board) || checkRows(board) || checkBoxes(board));
		
		
		//This seems a bit over complicated to me, but if it's clearer to you, feel free to uncomment/use it again
		/*
		if (checkColumns(b) || 
				checkRows(b)  || 
				checkBoxes(b) ){
			//System.out.println("YES DUPLICATES! START OVER!");
			return dupeNums;
		}
		else{
			//System.out.println("NO DUPLICATES!");
			dupeNums = true;
			return dupeNums;
		}
		*/
	}

	
	
	/**
	 * checks each column for duplicate numbers
	 * @param board the board to be checked
	 * @return true if the columns of the board contain duplicated numbers, false if the columns do not contain duplicated numbers
	 */
	public boolean checkColumns(int board[][]){
		//boolean dupeNums = false;
		//int[][] b = board;
		//HashSet<Integer> hset;
		boolean[] numbers;

		for (int i = 0; i < board.length; i++) {
			//hset = new HashSet<Integer>();
			numbers = new boolean[boardSize + 1];
			for (int j=0; j < board.length; j++) {
				if (/*hset.contains(board[j][i])*/numbers[board[j][i]]){
					//System.out.println("column duplicates found: " + b[j][i]);
					//System.out.println(i + " by " + j);
					//dupeNums = true;
					return true;
				}
				else{
					//hset.add(board[j][i]);
					numbers[board[j][i]] = true;
				}
					
			}
		}
		//System.out.println("No column duplicates found!");
		return false;
	}


	/**
	 * checks each row for duplicate numbers
	 * @param board the board to be checked
	 * @return true if the rows of the board contain duplicated numbers, false if the rows do not contain duplicated numbers
	 */
	public boolean checkRows(int board[][]){
		//boolean dupeNums = false;
		//int[][] b = board;
		//HashSet<Integer> hset;
		boolean[] numbers;
		for (int i = 0; i < board.length; i++) {
			
			numbers = new boolean[boardSize + 1];
			for (int j=0; j < board.length; j++) {
				if (numbers[board[i][j]]){
					
					return true;
				}
				else{
					
					numbers[board[i][j]] = true;
				}
					
			}
		}
		return false;
	}


	/**
	 * Checks the inner boxes for duplicate items
	 * @param board the board to be checked
	 * @return false if the board contains no duplicate items, true if the board contains duplicates
	 */
	public boolean checkBoxes(int board[][]){
		boolean[] numbers; 

		//outer loops controls which box we are currently looking at
		for (int boxX = 0; boxX < boardSize; boxX += length){
			for (int boxY = 0; boxY < boardSize; boxY += width){
				//This is initialized to the default of all false
				numbers = new boolean[boardSize + 1];

				//Inner loops check each box for duplicate numbers
				for (int x = boxX; x < length + boxX; x++){
					for (int y = boxY; y < width + boxY; y++){
						
						//rather than checking the numbers themselves, I am using an array in a pseudo sorted manner
						//If a number is already contained in the "box" (array) it's spot will be set to true, and I can check
						//that spot. Otherwise, add it to the known numbers (ie: set it's index to true)
						if (numbers[board[x][y]]){
							return true;
						}
						else{
							numbers[board[x][y]] = true;
						}
					}
				}
			}
		}
		return false;
	}
}


