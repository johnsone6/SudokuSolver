package soduke;

//Nicholas Perkins
public class CounterTestDriver 
{

	public static void main(String[] args) 
	{
		Counter c = new Counter(5, 9);
		
		for(int i = 0; i < Math.pow(10, 10); i++)
			System.out.println(c.count().toString());
	
		
		

	}

}

