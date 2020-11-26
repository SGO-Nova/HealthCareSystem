package Backend;

import java.util.Random;

public class bank {
	public boolean rand()
	{
		Random rand = new Random();
		return rand.nextBoolean();
	}
	
	public int approval()
	{
		if(rand())
		{
			double out = ((Math.random() * ((99999999-10000000)+1))+10000000);
			return (int) out;
		}
		return -1;
	}
}
