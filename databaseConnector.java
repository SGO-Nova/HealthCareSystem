import java.sql.*;
public class databaseConnector{
	public static void main(String[] args)
		{

		String url = "jdbc:mysql://153.33.75.65:3306/greenhouse";
		String username = "root";
		String password = "root";
		System.out.println("Connecting to Database...");

		try (Connection connection = DriverManager.getConnection(url, username, password)) 
		{
		    System.out.println("Database connected!");
		} catch (SQLException e) {
		    throw new IllegalStateException("Cannot connect the database!", e);
		}
		}
}
