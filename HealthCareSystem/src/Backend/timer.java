package Backend;

import java.util.ArrayList;

public class timer extends Actor
{
	public timer()
	{
		this.name = "";
		this.clearance = 5;
		this.password = "";
	}
	
	public timer(String name, int clearance, String password)
	{
		this.name = name;
		this.clearance = clearance;
		this.password = password;
	}
	
	public void clearNoShow(ArrayList<patient> patient)
	{
		for(patient pat : patient)
		{
			if(pat.appointment != null)
			{
				pat.appointment = null;
				//print something about appointment being canceled.
			}
			else
			{
				//nothing?
			}
		}
	}
	
	public void generateReport()
	{
		
	}
}
