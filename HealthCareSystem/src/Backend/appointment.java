package Backend;

import java.time.LocalDate;
import java.util.ArrayList;

public class appointment {
	LocalDate date;
	Doctor doctor;
	int time;
        String notes;
	public appointment()
	{
		this.date = LocalDate.now();
		this.doctor = new Doctor();
		this.time = 10;
                this.notes = "Notes";
	}
	public appointment(Doctor doctor, LocalDate date,int time, String notes)
	{
		this.date = date;
		this.doctor = doctor;
		this.time = time;
                this.notes = notes;
	}
	public void printApp()
	{
		//print app, but in javaFX
	}
        
        public void setNote(String note){
            this.notes = note;
        }
	
        public Doctor getDoctor(){
            return this.doctor;
        }
        
}
