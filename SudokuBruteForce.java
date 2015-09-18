import java.util.Stack;
public class SudokuBruteForce {
	private int[][] board, newBoard, blanks;
	private int width, length, boardSize, magicInt, numOfBlanks;
	private Stack<Integer> count;
	private Counter counter;
	
	
	
	public SudokuBruteForce(int width, int length, int[][] board){
		this.width = width;
		this.length = length;
		this.board = board;
		this.boardSize = width * length;
		
		calcSumInt(boardSize);
		findBlanks();
		
		//findSolution();
		
	}
	
	
	
	public boolean findSolution(){
		boolean solved = false;
		while (!solved){
			count = counter.count();
			fillInSolution();
			
			if(checkPuzzleSum(board, magicInt)){
				if(/*checkRows() && checkColumns() &&*/ checkBoxes()){
					solved = true;
				}
			}
			
			//Need some way to break out of loop when we have exhausted all possible numbers
			
		}
		return solved;
	}
	
	public void fillInSolution(){
		for (int i = 0; i < numOfBlanks; i++){
			board[blanks[i][0]][blanks[i][1]] = count.pop();
		}
		
		//Testing
		/*
		for (int i = 0; i < boardSize; i++){
			for (int j = 0; j < boardSize; j++){
				System.out.print(board[i][j] + " ");
			}
			System.out.println();
		}
		*/
	}
	
	
	
	/**
	 * This method finds the blanks and stores them in a 2D array
	 * blanks.length will get you the number of blanks in the main puzzle
	 * the coordinates of each blank are: 
	 * x = blanks[(counter variable)][0]
	 * y = blanks[(counter variable)][1]
	 * 
	 * 
	 */
	private void findBlanks(){
		int blankCount = 0;
		
		
		for (int i = 0; i < boardSize; i++){
			for (int j = 0; j < boardSize; j++){
				if (board[i][j] == 0){
					blankCount++;
				}
			}
		}
		
		numOfBlanks = blankCount;
		blanks = new int[blankCount][2];
		blankCount = 0;
		for (int i = 0; i < boardSize; i++){
			for (int j = 0; j < boardSize; j++){
				if (board[i][j] == 0){
					blanks[blankCount][0] = i;
					blanks[blankCount][1] = j;
					blankCount++;
				}
			}
		}
		
		//Testing
		/*
		for (int i = 0; i < blankCount; i++){
			System.out.print(blanks[i][0]);
			System.out.println(blanks[i][1]);
		}
		*/
	}
	
	/** 
	* finds sum of one row then calculates the
	* sum of the entire board
	**/
	public int calcSumInt(int boardSize){
		int bs = boardSize;
		int sumRowInt;
		int sumInt;
		sumRowInt = (((bs*bs) + bs)/2);
		sumInt = sumRowInt * bs;
		return sumInt;
	}
	
	/** 
	 * sums actual values of puzzle, compares to 
	 * the puzzles magic integer, then returns
	 * it's validity
	 **/
	public boolean checkPuzzleSum(int[][] board, int magicInt) {
		boolean sumValid = false;
		int sum = 0;
		int[][] b = board;
		int mi = magicInt;
		
		for(int i = 0; i < b.length; i++){
            for(int j = 0; j < b[i].length; j++){
                sum = sum + b[i][j];
            }
        }
		
		System.out.println("Sum of puzzle: " + sum);
		
		if(sum == mi){
			sumValid = true;
			return sumValid;
		}
		else 
			return sumValid;
	}
	
	//checks each column for duplicate nums
	public boolean checkColumns(int board[][]){
		boolean dupeNums = false;
		int[][] b = board;
		
		for (int i = 0; i < b.length; i++) {
	        HashSet<Integer> hset = new HashSet<Integer>();
	        for (int j=0; j < b.length; j++) {
	            if (hset.contains(b[j][i])){
	            	System.out.println("column duplicates found: " + b[j][i]);
	            	System.out.println(i + " by " + j);
	            	dupeNums = true;
	            	return dupeNums;
	            }
	            else
	            	hset.add(b[j][i]);
	        }
		}
		System.out.println("No column duplicates found!");
		return dupeNums;
	}
	
	//checks each row for duplicate nums
	public boolean checkRows(int board[][]){
		boolean dupeNums = false;
		int[][] b = board;
		
		for (int i = 0; i < b.length; i++) {
	        HashSet<Integer> hset = new HashSet<Integer>();
	        for (int j=0; j < b.length; j++) {
	            if (hset.contains(b[i][j])){
	            	System.out.println("row duplicates found: " + b[j][i]);
	            	System.out.println(i + " by " + j);
	            	dupeNums = true;
	            	return dupeNums;
	            }
	            else
	            	hset.add(b[j][i]);
	        }
		}
		System.out.println("No row duplicates found!");
		return dupeNums;
	}
	
	//checks duplicates
	//calls methods to run all three checks (columns, rows, boxes)
	public boolean duplicateCheck(int board[][]){
		boolean dupeNums = false;
		int[][] b = board;
		
		if (checkColumns(b) == true || 
			checkRows(b) == true || 
			checkBoxes(b) == true){
			System.out.println("YES DUPLICATES! START OVER!");
			return dupeNums;
		}
		else{
			System.out.println("NO DUPLICATES!");
			dupeNums = true;
			return dupeNums;
		}
	}**
	 * Checks the inner boxes for duplicate items
	 * @return true if the board contains no duplicate items, false if the board contains duplicates
	 */
	public boolean checkBoxes(int board[][]){
		boolean[] numbers; 

		for (int boxX = 0; boxX < boardSize; boxX += length){
			for (int boxY = 0; boxY < boardSize; boxY += width){
				//This is initialized to the default of all false
				numbers = new boolean[boardSize];
				
				for (int x = boxX; x < length + boxX; x++){
					for (int y = boxY; y < width + boxY; y++){
						
						//System.out.print(board[x][y]);
						
						//rather than checking the numbers themselves, I am using an array in a pseudo sorted manner
						//If a number is already contained in the "box" (array) it's spot will be set to true, and I can check
						//that spot. Otherwise, add it to the known numbers (ie: set it's index to true)
						
						if (numbers[board[x][y] - 1]){
							return false;
						}
						else{
							numbers[board[x][y] - 1] = true;
						}
					}
					//System.out.println();
				}
				//System.out.println();
			}
		}

		return true;
	}
	
	
}
