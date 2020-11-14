package Backend;

public class patient extends Actor {
	
	appointment appointment;
	//empty constructor with defaults for a test case
	public patient()
	{
		this.name = "test";
		this.password = null;
		this.clearance = 0;
		this.appointment = null;
	}
	//real constructor 
	public patient(String name, appointment appointment)
	{
		this.name = name;
		this.appointment = null;
		this.password = null;
		this.clearance = 0;
	}
}