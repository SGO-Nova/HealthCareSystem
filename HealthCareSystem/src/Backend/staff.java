package Backend;

public class staff extends Actor {
	//basic constructor, defaults as a test case
	public staff()
	{
		this.clearance = 1;
		this.name = "Test Staff";
		this.password = "test";
	}
	public staff(String name, String password)
	{
		this.clearance = 1;
		this.name = name;
		this.password = password;
	}
}
