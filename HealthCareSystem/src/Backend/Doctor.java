package Backend;

import java.util.ArrayList;

public class Doctor extends Actor{
	String name;
	schedule schedule;
	int earned;
	ArrayList<patientChart> patientChart;
	//basic constructor, defaults to a test case
	public Doctor()
	{
		this.name = "Test Doctor";
		this.schedule = new schedule();
		this.clearance = 3;
		this.password = "test";
		this.earned = 0;
		this.id = 123123;
		this.patientChart = new ArrayList<patientChart>();
	}
	public Doctor(String name, String password)
	{
		this.clearance = 3;
		this.name = name;
		this.schedule = new schedule();
		this.password = password;
		this.earned = 0;
		this.patientChart = new ArrayList<patientChart>();
	}
	
	public void updateEarned(int newEarned)
	{
		//make sure input variable is above 0
		earned += newEarned;
	}
	
	public void addChart(patientChart chart)
	{
		//adds new patient chart
		patientChart.add(chart);
	}
	
	public void clearChart()
	{
		//clears all patient charts from arraylist
		patientChart.clear();
	}
        
        public String getName(){
            return this.name;
        }
}

