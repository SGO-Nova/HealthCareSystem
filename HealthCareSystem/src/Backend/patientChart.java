package Backend;

public class patientChart {
	patient patient;
	String SSN;
        String Address1;
        String Address2;
        String Email;
        String Insurance;
        String Phone1;
        
        
	public patientChart()
	{
		this.patient = null;
                this.Address1 = "123 ABC Street";
                this.Address2 = "";
                this.Email = "test@email.com";
                this.Insurance = "Real Ins. Co.";
                this.Phone1 = "(333)234-5469";
                this.SSN = "324135643";
	}
	public patientChart(patient patient,String add1, String add2, String email, String ins, String ph1, String SSN)
	{
		this.patient = patient;
		this.SSN = SSN;
                this.Address1 = add1;
                this.Address2 = add2;
                this.Email = email;
                this.Insurance = ins;
                this.Phone1 = ph1;
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
        public String getSSN(){
            return this.SSN;
        }
}
