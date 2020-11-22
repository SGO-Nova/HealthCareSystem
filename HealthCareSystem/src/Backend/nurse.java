package Backend;

import java.util.ArrayList;

public class nurse extends Actor {
	public nurse()
	{
		this.name = "Test Nurse";
		this.clearance = 2;
		this.password = "test";
		this.id = 123345;
	}
        
        public nurse(String name, String password){
            this.name = name;
            this.clearance = 2;
            this.password = password;
        }
	
	public void updateMeasurements(ArrayList<patient> patient)
	{
		//take input for patient name
		String patName = "";
		int set = 0;
		for(patient pat : patient)
		{
			if(pat.name.equals(patName))
			{
				//print patient chart :)
				//take input for new stuff
				String height = "";
				String bloodPressure = "";
				String weight = "";
				String reason = "";
				pat.treatmentRecord.bloodPressure = bloodPressure;
				pat.treatmentRecord.height = height;
				pat.treatmentRecord.weight = weight;
				pat.treatmentRecord.reason = reason;
				set = 1;
			}
		}
		if(set != 1)
		{
			//print that patient name was not associated with a real patient
		}
	}
}
