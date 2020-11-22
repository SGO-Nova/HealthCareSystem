package Backend;

public class CEO extends Actor {
	public CEO()
	{
		this.name = "Test CEO";
		this.clearance =4;
		this.password = "test";
		this.id = 432432;
	}
	public CEO(String name,String password)
	{
		this.clearance = 4;
		this.name = name;
		this.password = password;
	}
	
	public void viewReport(CEO ceo)
	{
		
	}
}
