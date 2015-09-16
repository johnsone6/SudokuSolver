//Nicholas Perkins
public class counterTestDriver 
{

	public static void main(String[] args) 
	{
		Counter c = new Counter(6);
		
		for(int i = 0; i < Math.pow(6, 6); i++)
			System.out.println(c.count().toString());
	
		
		

	}

}
