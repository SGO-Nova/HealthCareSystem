package Backend;

public class day {
	Object time[][] = new Object[9][2];
	public day()
	{
		for(int i = 0; i < 9; i++)
		{
			for(int j = 0; j < 2; j++)
			{
				if(j==0)
				{
					if(i <= 3)
					{
						time[i][j] = i + 9;
					}
					else
					{
						time[i][j] = i - 3;
					}
				}
				else
				{
					time[i][j] = true;
				}
			}
		}
	}
	//probably don't need this since it should always initalize with a completely open schedule.
	public day(Object time[][])
	{
		this.time = time;
	}
	
	public void print()
	{
		for(int i = 0; i < 9; i++)
		{
			for(int j = 0; j < 2; j++)
			{
				if(j==0)
				{
					if(i <= 3)
						System.out.print(time[i][j] + " AM  ");
					else
						System.out.print(time[i][j] + " PM  ");
				}
				else 
				{
					if((boolean) time[i][j])
						System.out.println("Status: Open");
					else
						System.out.println("Status: Full");
				}
			}
		}
	}
}
