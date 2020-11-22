package Backend;

import java.time.LocalDate;
import java.util.ArrayList;

public class appointment {
	LocalDate date;
	Doctor doctor;
	int time;
	public appointment()
	{
		this.date = LocalDate.now();
		this.doctor = new Doctor();
		this.time = 10;
	}
	public appointment(Doctor doctor, LocalDate date,int time)
	{
		this.date = date;
		this.doctor = doctor;
		this.time = time;
	}
	public void printApp()
	{
		//print app, but in javaFX
	}
	
}
