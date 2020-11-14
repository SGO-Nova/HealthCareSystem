package Backend;

public class Doctor extends Actor{
	String name;
	schedule schedule;
	//basic constructor, defaults to a test case
	public Doctor()
	{
		this.name = "Test Doctor";
		this.schedule = new schedule();
		this.clearance = 3;
		this.password = "test";
	}
	public Doctor(String name, schedule schedule, String password)
	{
		this.clearance = 3;
		this.name = name;
		this.schedule = new schedule();
		this.password = password;
	}
	
}
