package Backend;

import java.time.LocalDate;

public class patient extends Actor {
	
        String Fname;
        String Lname;
        String Address1;
        String Address2;
        String Email;
        String Insurance;
        String Phone1;
        String Phone2;
        int SSN;
	appointment appointment;
	patientChart patientChart;
	treatmentRecord treatmentRecord;
	String birthday;
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
                this.Address1 = "123 ABC Street";
                this.Address2 = "";
                this.Email = "test@email.com";
                this.Insurance = "Real Ins. Co.";
                this.Phone1 = "(333)234-5469";
                this.Phone2 = "";
                this.SSN = 324135643;
	}
	//real constructor 
	public patient(String Fname,String Lname, String birthday, String add1, String add2, String email, String ins, String ph1, String ph2, int ssn)
	{
		this.name = Fname + " " + Lname;
                this.Fname = Fname;
                this.Lname = Lname;
		this.appointment = null;
		this.password = null;
		this.clearance = 0;
		this.birthday = birthday;
		this.patientChart = null;
		this.treatmentRecord = null;
		this.paymentInfo = null;
                this.Address1 = add1;
                this.Address2 = add2;
                this.Email = email;
                this.Insurance = ins;
                this.Phone1 = ph1;
                this.Phone2 = ph2;
                this.SSN = ssn;
                
	}
        
        public String getFname(){
            return this.Fname;
        }
        public String getLname(){
            return this.Lname;
        }
        public String getAddress1(){
            return this.Address1;
        }
        public String getAddress2(){
            return this.Address2;
        }
        public String getEmail(){
            return this.Email;
        }
        public String getInsurance(){
            return this.Insurance;
        }
        public String getPhone1(){
            return this.Phone1;
        }
        public String getPhone2(){
            return this.Phone2;
        }
        public int getSSN(){
            return this.SSN;
        }
}
