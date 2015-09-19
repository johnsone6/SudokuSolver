//Nicholas Perkins
//Nicks work
//Counter
//Method to insert numbers
public class ArrayCounter 
{
	private int size;
	private int[] numbers;
	private int ndx;
	private int upperLimit;

	
	/**
	 * 
	 * @param size the number of "digits" to generate
	 * @param upperLimit the maximum value of each digit
	 */
	public ArrayCounter(int size, int upperLimit) 
	{
		this.size = size;
		this.upperLimit = upperLimit;
		numbers = new int[size];
		
		fillArray();
	}
	
	/**
	 * Fills the array with ones
	 */
	private void fillArray()
	{
		for(int i= 0; i < size; i++)
			numbers[i] = 1;
	}
	
	/**
	 * gets the next generated series of numbers
	 * @return the array containing all the values for each spot in the board
	 */
	public int[] count() 
	{
		int count = numbers.length - 1;
		if(ndx == 0)
		{
			ndx++;
			return numbers;
		}
		
		
		while(numbers[count] == upperLimit)
		{
			numbers[count] = 1;
			count--;
		}
		
		numbers[count]++;
		ndx++;
		
		return numbers;
		
	}
	
	/**
	 * Determines whether the counter has reached the end 
	 * @return whether or not there is another number which can be generated
	 */
	public boolean hasNext(){
		for (int i = numbers.length - 1; i >= 0; i--){
			if (numbers[i] != upperLimit){
				return true;
			}
		}
		return false;
	}
	
}
