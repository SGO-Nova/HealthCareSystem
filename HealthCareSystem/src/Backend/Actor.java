package Backend;

import java.util.ArrayList;

//parent class for Staff, Doctor, CEO, Patient, etc.
public class Actor {
	String name;
	int clearance;
	String password;
	int id;
	/*
	 * clearance = 0, patient (shouldn't even have a clearance)
	 * clearance = 1, staff
	 * clearance = 2, nurse
	 * clearance = 3, doctor
	 * clearance = 4, CEO
	 */
	//empty constructor lol
	public Actor()
	{
		
	}
	
	public Actor(String name, int clearance, int id)
	{
		this.name = name;
		this.clearance = clearance;
		this.id = id;
	}
	
        public void setID(int ID){
            this.id = ID;
        }
        
        public String getName(){
            return this.name;
        }
        
	public int randomNum()
	{
		return (int)(Math.random()*((999999-100000)-1))+100000;
	}
	
	public int createID(ArrayList<Doctor> doctor, ArrayList<patient> patient, ArrayList<staff> staff)
	{
		int id = randomNum();
		for(Doctor doc : doctor)
		{
			if(doc.id == id)
			{
				createID(doctor,patient,staff);
			}
		}
		for(patient pat : patient)
		{
			if(pat.id == id)
			{
				createID(doctor,patient,staff);
			}
		}
		for(staff stf : staff)
		{
			if(stf.id == id)
			{
				createID(doctor,patient,staff);
			}
		}
		return id;
	}
}
