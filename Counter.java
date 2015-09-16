import java.util.*;
//Nicholas Perkins
//Nicks work
//Counter
//Method to insert numbers
public class Counter 
{
	private int size;
	private Stack<Integer> count = new Stack<Integer>();
	private int ndx;

	public Counter(int size) 
	{
		this.size = size;
		fillStack();
	}
	
	private void fillStack()
	{
		for(int i= 0; i< size; i++)
			count.push(1);
		
	}
	
	public Stack<Integer> count() 
	{
		if(ndx == 0)
		{
			ndx++;
			return count;
		}
		ArrayList<Integer> hold = new ArrayList<Integer>();
		int current;
		current = count.pop();
		
		while(current == size)
		{
			current = 1;
			hold.add(current);
			current = count.pop();
		}
		
		current++;
		
		count.push(current);
		
		for(int i=0; i < hold.size(); i++)
			count.push(hold.get(i));
			
		ndx++;
		return count;
		
	}
	
	
}
