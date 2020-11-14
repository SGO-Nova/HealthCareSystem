package Backend;

public class schedule {
	static int[][] sched = new int[2][8];
	
	public schedule()
	{
		for(int i = 0; i < 8; i++)
		{
			for(int j =0; j < 2; j++)
			{
				if(j==0)
				{
					sched[i][j] = i+9;//sets time
				}
				else
				{
					sched[i][j] = 0;//sets status if open or not.
				}
			}
		}
	}
	
	public void updateSchedule(int time)
	{
		for(int i = 0; i < 8; i++)
		{
			if(time == sched[i][0])
			{
				sched[i][1] = 1;
			}
		}
	}
	
	public boolean checkSchedule(int time)
	{
		for(int i = 0; i< 8; i++)
		{
			if(time == sched[i][0])
			{
				if(sched[i][1] == 1)
				{
					return false;//returns false if they are not free at that time
				}
			}
		}
		return true;//otherwise returns true, since they are free at that time
	}
	public void printSchedule()
	{
		for(int i = 0; i<8; i++)
		{
			for(int j = 0; j<2;j++)
			{
				//print, but in JavaFX
			}
		}
	}
}
