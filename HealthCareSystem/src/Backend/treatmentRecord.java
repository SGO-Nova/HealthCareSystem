package Backend;

public class treatmentRecord {
	String date;
	String reason;
	String treatment;
        int Height1;
        int Height2;
        int Weight;
        int BloodP1;
        int BloodP2;
        
	public treatmentRecord()
	{
		this.date = "";
		this.reason = "";
		this.treatment = "";
                this.Height1 = 0;
                this.Height2 = 0;
                this.Weight = 0;
                this.BloodP1 = 0;
                this.BloodP2 = 0;
	}
        
        public treatmentRecord(String date, String reason, int height1, int height2, int weight, int blood1, int blood2){
            this.date = date;
            this.reason = reason;
            this.treatment = "";
            this.Height1 = height1;
            this.Height2 = height2;
            this.Weight = weight;
            this.BloodP1 = blood1;
            this.BloodP2 = blood2;
        }
	
        public void setTreatment(String treatment){
            this.treatment = treatment;
        }
        
        public void setReason(String reason){
            this.reason = reason;
        }
        
        public void setHeight(int feet, int in){
            this.Height1 = feet;
            this.Height2 = in;
        }
        
        public void setWeight(int weight){
            this.Weight = weight;
        }
        
        public void setBloodPreasure(int BP1, int BP2){
            this.BloodP1 = BP1;
            this.BloodP2 = BP2;
        }
        
        public String getReason(){
            return this.reason;
        }
        
        public String getTreatment(){
            return this.treatment;
        }
        
        public int getHeight1(){
            return this.Height1;
        }
        
        public int getHeight2(){
            return this.Height2;
        }
        
        public int getWeight(){
            return this.Weight;
        }
        
        public int getBP1(){
            return this.BloodP1;
        }
        
        public int getBP2(){
            return this.BloodP2;
        }
}
