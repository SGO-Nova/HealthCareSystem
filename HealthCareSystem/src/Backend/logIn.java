package Backend;

import java.util.ArrayList;
import java.util.Scanner;
public class logIn {
	public static Object logIn(String ID, String pass, ArrayList<nurse> nurse,ArrayList<Doctor> doctor, ArrayList<staff> staff,ArrayList<CEO> ceo)
	{
            /*
		System.out.println("Please enter your ID and password");
		System.out.print("ID: ");
		Scanner in = new Scanner(System.in);
		String ID = in.nextLine();
		boolean check = check(ID);
		while(!check)
		{
			ID = in.nextLine();
			check = check(ID);
		}
		System.out.print("Password: ");
		String pass = in.nextLine();
            */
            
		for (nurse nur : nurse)
		{
			if(nur.id == Integer.parseInt(ID))
			{
				
				if(nur.password.equals(pass))
				{
					System.out.println("Welcome back " + nur.name + "!");
					//in.close();
					return nur;
				}
				else
				{
					System.out.println("That password was incorrect");
					//in.close();
					return null;
				}
			}
		}
		for (Doctor doc : doctor)
		{
			if(doc.id == Integer.parseInt(ID))
			{
				if(doc.password.equals(pass))
				{
					System.out.println("Welcome back " + doc.name + "!");
					//in.close();
					return doc;
				}
				else
				{
					System.out.println("That password was incorrect");
					//in.close();
					return null;
				}
			}
		}
		for(staff stf : staff)
		{
			if(stf.id == Integer.parseInt(ID))
			{
				if(stf.password.equals(pass))
				{
					System.out.println("Welcome back " + stf.name + "!");
					//in.close();
					return stf;
				}
				else
				{
					System.out.println("That password was incorrect");
					//in.close();
					return null;
				}
			}
		}
		for(CEO CEO : ceo)
		{
			if(CEO.id == Integer.parseInt(ID))
			{
				if(CEO.password.equals(pass))
				{
					System.out.println("Welcome back " + CEO.name + "!");
					//in.close();
					return CEO;
				}
				else
				{
					System.out.println("That password was incorrect");
					//in.close();
					return null;
				}
			}
		}
		System.out.println("That ID does not match any that are on file.");
		//in.close();
		return null;
	}
	
	public static void logOut()
	{
		Scanner in = new Scanner(System.in);
		System.out.println("Are you sure you want to log out?");
		String input = "1";//scanner was eating inputs, so im just forcing values here
		if(input.equals("1"))
		{
			in.close();
			System.exit(1);
		}
		else
		{
			in.close();
			System.out.println("Returning to menu...");
		}
	}
	
	public static boolean check(String in)
	{
		if(in == null)
		{
			return false;
		}
		try
		{
			int a = Integer.parseInt(in);
		}
		catch(NumberFormatException e)
		{
			System.out.println("That was not an acceptable input for an ID number. Please try again.");
			return false;
		}
		return true;
	}
}
