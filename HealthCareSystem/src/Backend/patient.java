package Backend;

import java.time.LocalDate;

public class patient extends Actor {
	
        String Fname;
        String Lname;
        
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
                
	}
	//real constructor 
	public patient(String Fname,String Lname, String birthday)
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
	}
        
        public patient(String Fname,String Lname, String birthday, appointment appointment, patientChart pChart, treatmentRecord tRecord, paymentInformation pInfo)
	{
		this.name = Fname + " " + Lname;
                this.Fname = Fname;
                this.Lname = Lname;
		this.appointment = appointment;
		this.password = null;
		this.clearance = 0;
		this.birthday = birthday;
		this.patientChart = pChart;
		this.treatmentRecord = tRecord;
		this.paymentInfo = pInfo;
	}
        
        public void setChart(patientChart chart){
            this.patientChart = chart;
        }
        
        public void setAppointment(appointment app){
            this.appointment = app;
        }
        
        public void setRecord(treatmentRecord rec){
            this.treatmentRecord = rec;
        }
        
        public void setPayment(paymentInformation payment){
            this.paymentInfo = payment;
        }
        
        public patientChart getChart(){
            return this.patientChart;
        }
        
        public appointment getAppointment(){
            return this.appointment;
        }
        
        public treatmentRecord getRecord(){
            return this.treatmentRecord;
        }
        
        public paymentInformation getPayment(){
            return this.paymentInfo;
        }
        
        public String getFname(){
            return this.Fname;
        }
        
        public String getLname(){
            return this.Lname;
        }
        
        public String getBirthday(){
            return this.birthday;
        }
}
