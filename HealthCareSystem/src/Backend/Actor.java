package Backend;
//parent class for Staff, Doctor, CEO, Patient, etc.
public class Actor {
	String name;
	int clearance;
	String password;
	int ID;
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
	
	public Actor(String name, int clearance)
	{
		this.name = name;
		this.clearance = clearance;
	}
}
