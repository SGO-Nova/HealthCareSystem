import java.time.LocalDate;
import java.util.ArrayList;
public class schedule {
	Object[][] schedule = new Object[365][2];//holds ALL the info. 
	public schedule()
	{
		for(int i = 0; i < 365; i++)
		{
			for(int j = 0; j < 2; j++)
			{
				if(j==0)
				{
					schedule[i][j] = LocalDate.of(2020,1,1).plusDays(i);
				}
				else
				{
					schedule[i][j] = new day();
				}
			}
		}
	}
	
	public void updateSchedule(int time,LocalDate date)
	{
		for(int i = 0; i < 365; i++)
		{
			if(date.equals(schedule[i][0]))
			{
				day temp = (day)schedule[i][1];
				for(int j = 0; j < 9; j++)
				{
					if((int)temp.time[j][0] == time)
					{
						temp.time[j][1] = false;
					}
				}
				schedule[i][1] = temp;
			}
		}
		
	}
	
	public void rescheduleApp(LocalDate old, LocalDate now, int oldTime, int newTime)
	{
		for(int i = 0; i < 365; i++)
		{
			if(schedule[i][0].equals(old))
			{
				day temp = (day)schedule[i][1];
				for(int j = 0; j < 9; j++)
				{
					if(oldTime==(int)temp.time[j][0] && (!(boolean)temp.time[j][1]))
					{
						temp.time[j][1] = true;
						schedule[i][1] = temp;
					}
				}
			}
			if(schedule[i][0].equals(now))
			{
				day temp = (day)schedule[i][1];
				for(int j = 0; j < 9; j++)
				{
					if(newTime==(int)temp.time[j][0] && ((boolean)temp.time[j][1]))
					{
						temp.time[j][1] = false;
						schedule [i][1] = temp;
					}
				}
			}
		}
	}
	
	public boolean checkSchedule(int time,LocalDate date)
	{
		for(int i = 0; i < 365; i++)
		{
			if(schedule[i][0].equals(date))
			{
				day temp = (day)schedule[i][1];
				for(int j = 0; j < 9; j++)
				{
					if((int)temp.time[j][0] == time)
					{
						if((boolean)temp.time[j][1])
						{
							return true;
						}
					}
				}
			}
		}
		return false;//otherwise returns false, since they are NOT free at that time
	}
	
	public void cancelApp(int time,LocalDate date,ArrayList<patient> patient)
	{
		//take input for patient name
		String patName = "test";
		for(patient pat : patient)
		{
			if(pat.name.equals(patName))
			{
				for(int i = 0; i<365;i++)
				{
					if(date.equals((LocalDate)(pat.appointment.doctor.schedule.schedule[i][0])))
					{
						day temp = (day)pat.appointment.doctor.schedule.schedule[i][1];
						for(int j = 0; j < 9; j++)
						{
							if(time == (int)temp.time[j][0])
							{
								temp.time[j][1] = true;
								pat.appointment.doctor.schedule.schedule[i][1] = temp;
								pat.appointment = null;
								return;
							}
						}
					}
				}
			}
		}
	}
	
	public void printSchedule(LocalDate date)
	{
		for(int i = 0; i < 365; i++)
		{
			if(date.equals(schedule[i][0]))
			{
				day temp = (day)schedule[i][1];
				temp.print();
				temp = null;
				System.gc();
			}
		}
	}
}
