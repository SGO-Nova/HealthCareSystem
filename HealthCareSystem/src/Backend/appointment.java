package Backend;

import java.util.ArrayList;

public class appointment {
	String date;
	Doctor doctor;
	int time;
	public appointment()
	{
		this.date = "";
		this.doctor = null;
	}
	public appointment(Doctor doctor, String date,int time)
	{
		this.date = date;
		this.doctor = doctor;
		this.time = time;
	}
	public static void scheduleApp(ArrayList<Doctor> doctor,String docName, ArrayList<patient> patient)
	{
		int appSet = 0;
		for(Doctor doc : doctor)
		{
			if(docName == doc.name)
			{
				doc.schedule.printSchedule();
				//staff enters patients name, date and time for their appointment
				String patName = "";//edit this so that it can take an input
				String date = "";//edit this so that it can take an input
				int time = 0;//edit this so that it can take an input
				if(doc.schedule.checkSchedule(time))
				{
					doc.schedule.updateSchedule(time);
					appointment app = new appointment(doc,date,time);
					for(patient pat : patient)
					{
						if(patName == pat.name)
						{
							pat.appointment = app;
						}
					}
					appSet = 1;
					//print something about appointment being set.
				}
			}
		}
		if(appSet != 1)
		{
			//print something about appointment not being set.
		}
	}
	public static void cancelApp(ArrayList<patient> patient)
	{
		//staff enters patient name
		String name = "";//edit this so that it can take an input
		for(patient pat : patient)
		{
			if(name == pat.name)
			{
				if(pat.appointment != null)
				{
					//confirm you want to delete appointment
					boolean confirm = true;
					if(confirm)
					{
						int time = pat.appointment.time;
						pat.appointment.doctor.schedule.cancelApp(time);
						pat.appointment = null;
						//print something about appointment being cancelled. 
					}
				}
				else
				{
					//patient didn't have an appointment
					//print something about no appointment
				}
			}
		}
	}
}
