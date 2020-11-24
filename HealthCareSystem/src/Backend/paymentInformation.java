package Backend;

import java.time.LocalDate;

public class paymentInformation {
	int patientId;
	String name;
	LocalDate date;
	int amountOwed;
	String paymentType;
	int referenceNumber;
        
	public paymentInformation(int id, String name, int owe, LocalDate date)
	{
            this.patientId = id;
            this.name = name;
            this.amountOwed = owe;
            this.date = date;
            this.paymentType = null;
            this.referenceNumber = 0;
	}
        
        public void setAmountOwed(int amount){
            this.amountOwed += amount;
        }
        
        public void setPaymentType(String type){
            this.paymentType = type;
        }
        
        public void setReferenceNumber(int ref){
            this.referenceNumber = ref;
        }
        
        public int getAmountOwed(){
            return this.amountOwed;
        }
        
        public int getReferenceNumber(){
            return this.referenceNumber;
        }
        
}
