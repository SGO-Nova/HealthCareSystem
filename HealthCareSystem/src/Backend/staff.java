package Backend;

import java.time.LocalDate;
import java.util.ArrayList;
public class staff extends Actor {
	public staff()
	{
		this.name = "Test Staff";
		this.clearance = 1;
		this.password = "test";
		this.id = 321321;
	}
	
	public staff(String name, String password)
	{
		this.clearance = 1;
		this.name = name;
		this.password = password;
	}
	
	public void scheduleApp(ArrayList<Doctor> doctor,String docName, ArrayList<patient> patient)
	{
		int appSet = 0;
		for(Doctor doc : doctor)
		{
			if(docName == doc.name)
			{
				
				//take input of date for appointment
				LocalDate date = LocalDate.now();
				System.out.println("Doctors current schedule: ");
				doc.schedule.printSchedule(date);
				//staff enters patients name, date and time for their appointment
				String patName = "test";//edit this so that it can take an input
				int time = 10;//edit this so that it can take an input
				if(doc.schedule.checkSchedule(time,date))
				{
					doc.schedule.updateSchedule(time,date);
					appointment app = new appointment(doc,date,time);
					for(patient pat : patient)
					{
						if(patName.equals(pat.name))
						{
							pat.appointment = app;
							doc.schedule.updateSchedule(time, date);
							System.out.println("Appointment Scheduled");
							doc.schedule.printSchedule(date);
							appSet = 1;
						}
					}
					//print something about appointment being set.
				}
			}
		}
		if(appSet != 1)
		{
			System.out.println("Appointment was not scheduled.");
			//print something about appointment not being set.
		}
	}
	
	public void changeApp(ArrayList<patient> patient, String patientName,ArrayList<Doctor> doctor)
	{
		//take input for patient name here, just passing it for testing purposes.
		for(patient pat : patient)
		{
			if(pat.name.equals(patientName))
			{
				if(pat.appointment!=null)
				{
					//System.out.println("Appointment found...");
					pat.appointment.printApp();
					//take input for doctor name
					for(Doctor doc : doctor)
					{
						if(doc.name.equals(pat.appointment.doctor.name))
						{
							System.out.println("Doctors current schedule: ");
							doc.schedule.printSchedule(pat.appointment.date);
							//take input for new date/time
							LocalDate date = LocalDate.now();
							//LocalDate date = LocalDate.of(year,month,day);
							int time = 9;
							//int time = input of some kind.
							if(doc.schedule.checkSchedule(time,date))
							{
								//schedule was updated
								doc.schedule.rescheduleApp(pat.appointment.date,date,pat.appointment.time,time);
								System.out.println("Doctors updated schedule: ");
								doc.schedule.printSchedule(date);
								System.out.println("Appointment Rescheduled");
							}
							else
							{
								//schedule was not updated since doctor was busy
								//take input if patient just wants to cancel instead
								boolean cancel = false;
								//boolean cancel = input of some kind
								if(cancel)
								{
									pat.appointment= null;
									System.out.println("Appointment Canceled.");
									//print something about appointment being canceled.
								}
								else
								{
									System.out.println("Appointment Unchanged.");
									//print something about the appointment still being active, on the same day.
								}
							}
						}
					}
				}
				else
				{
					//print something about app not existing
					System.out.println("There is no appointment on file");
				}
			}
		}
	}
	
	
	
	public void checkIn(ArrayList<patient> patient,String patName, String birth, ArrayList<Doctor> doctor)
	{
		for(patient pat : patient)
		{
			if(pat.name.equals(patName))
			{
				pat.birthday = birth;
				if(pat.birthday.equals(birth))
				{
					pat.patientChart = null;
					if(pat.patientChart != null)
					{
						System.out.println("Existing Patient");
						pat.patientChart.printChart();
						//take input if changes are necessary
						boolean input = false;
						if(input)
						{
							//take input since changes are necessary
							//say something about this being the updated chart
							pat.patientChart.printChart();
						}
						else
						{
							//nothing here since no changes are necessary
						}
					}
					else
					{
						//take input for new patient info
						System.out.println("New Patient");
						pat.patientChart = new patientChart();
					}
					//take doc's name
					String docname = "Test Doctor";
					for(Doctor doc : doctor)
					{
						if(doc.name.equals(docname))
						{
							doc.addChart(pat.patientChart);
							System.out.println("Checked in.");
						
						}
					}
					//need to take input for stuff to put into payment information.
					pat.paymentInfo = new paymentInformation();
					pat.appointment = null;
				}
			}
		}
	}
	public void payMedicalFee()
	{
		
	}
}
