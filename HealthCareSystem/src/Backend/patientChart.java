public class patientChart {
	patient patient;
	String address;
	String phoneNumber;
	String email;
	String SSN;
	String insurance;
	public patientChart()
	{
		this.patient = null;
		this.address = "Home";
		this.phoneNumber = "806-420-6969";
		this.email = "an email@somewhere.com";
		this.SSN = "420";
		this.insurance = "Cigna";
	}
	public patientChart(patient patient, String address, String phoneNumber, String email, String SSN, String insurance)
	{
		this.patient = patient;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.SSN = SSN;
		this.insurance = insurance;
	}
	
	public void printChart()
	{
		//print, but in javaFX
		System.out.println(email);
	}
}
