import java.time.LocalDate;

public class patient extends Actor {
	
	appointment appointment;
	patientChart patientChart;
	treatmentRecord treatmentRecord;
	LocalDate birthday;
	paymentInformation paymentInfo;
	//empty constructor with defaults for a test case
	public patient()
	{
		this.name = "test";
		this.password = null;
		this.clearance = 0;
		this.appointment = new appointment();
		this.patientChart = new patientChart();
		this.treatmentRecord = null;
		this.birthday = null;
		this.paymentInfo = null;
	}
	//real constructor 
	public patient(String name, appointment appointment,LocalDate birthday)
	{
		this.name = name;
		this.appointment = null;
		this.password = null;
		this.clearance = 0;
		this.birthday = birthday;
		this.patientChart = null;
		this.treatmentRecord = null;
		this.paymentInfo = null;
	}
}
