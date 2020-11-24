package healthcaresystem;


import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Screen;
import javafx.stage.Stage;
import Backend.*;
import java.io.File; 
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner; 
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.Optional;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.util.Duration;

public class LoginController implements Initializable {

    @FXML
    private TextField loginID;
    @FXML
    private PasswordField login;
    @FXML
    private Label time;
    @FXML
    private Label Amount;
    @FXML
    private TextField fName;
    @FXML
    private TextField lName;
    @FXML
    private TextField pAddress1;
    @FXML
    private TextField pAddress2;
    @FXML
    private TextField pEmail;
    @FXML
    private TextField pInsurance;
    @FXML
    private TextField pID;
    @FXML
    private TextField pPhone1;
    @FXML
    private TextField pBirthday;
    @FXML
    private TextField pSSN;
    @FXML
    private TextField aDateY;
    @FXML
    private TextField aDateM;
    @FXML
    private TextField aDateD;
    @FXML
    private TextField pHeight1;
    @FXML
    private TextField pHeight2;
    @FXML
    private TextField pWeight;
    @FXML
    private TextField pBP1;
    @FXML
    private TextField pBP2;
    @FXML
    private TextArea aReason;
    @FXML
    private TextArea aTreatment;
    @FXML
    private TextArea aNotes;
    @FXML
    private ComboBox<String> doctorDropDown = new ComboBox<String>();
    @FXML
    private ComboBox<Integer> aTime = new ComboBox<Integer>();
    @FXML
    private ComboBox<String> pType = new ComboBox<String>();
    
    private ArrayList<patient> patient = new ArrayList<patient>();
    private ArrayList<nurse> nurse = new ArrayList<nurse>();
    private ArrayList<Doctor> doctor = new ArrayList<Doctor>();
    private ArrayList<staff> staff = new ArrayList<staff>();
    private ArrayList<CEO> ceo = new ArrayList<CEO>();
    private String pattern = "ccc | yyyy-MM-dd | hh:mm:ss a";
    private int counter = 0;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        try {
            File file = new File("src/healthcaresystem/Database/LoginInformation.txt"); 
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                StringTokenizer tok = new StringTokenizer(sc.nextLine(), ",");

                int clear = Integer.parseInt(tok.nextToken());
                int ID = Integer.parseInt(tok.nextToken());
                String pass = tok.nextToken();
                String name = tok.nextToken();

                switch(clear){
                    case 1: staff sta = new staff(name, pass);
                            sta.setID(ID);
                            staff.add(sta);
                            break;
                    case 2: nurse nur = new nurse(name, pass);
                            nur.setID(ID);
                            nurse.add(nur);
                            break;
                    case 3: Doctor doc = new Doctor(name, pass);
                            doc.setID(ID);
                            doctor.add(doc);
                            break;
                    case 4: CEO ce = new CEO(name, pass);
                            ce.setID(ID);
                            ceo.add(ce);
                            break;
              }
            }
            sc.close();
          } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
          }
        
        
        
        for(int i = 0; i < doctor.size(); i++){
            doctorDropDown.getItems().addAll(doctor.get(i).getName());
        }
        ObservableList<Integer> times = FXCollections.observableArrayList(900,1000,1100,1200,1300,1400,1500,1600,1700);
        aTime.getItems().addAll(times);
        pType.getItems().addAll("Debit", "Credit", "Cash");
        
        try {
            File file = new File("src/healthcaresystem/Database/PatientInformation.txt"); 
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                StringTokenizer tok = new StringTokenizer(sc.nextLine(), ",");

                int ID = Integer.parseInt(tok.nextToken());
                String Fname = tok.nextToken();
                String Lname = tok.nextToken();
                String birthday = tok.nextToken();
                String add1 = tok.nextToken();
                String add2 = tok.nextToken();
                String email = tok.nextToken();
                String ins = tok.nextToken();
                String ph1 = tok.nextToken();
                String ssn = tok.nextToken();
                
                patient patien = new patient(Fname, Lname, birthday);
                patien.setChart(new patientChart(patien, add1, add2, email, ins, ph1, ssn));
                patien.setID(ID);
                paymentInformation paymentInfo = new paymentInformation(ID, patien.getName(), 100000, LocalDate.now());
                patien.setPayment(paymentInfo);
                patient.add(patien);
            }
            sc.close();
          } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
          }
        try {
            File file = new File("src/healthcaresystem/Database/PatientRecord.txt"); 
            Scanner sc = new Scanner(file);
            
            while (sc.hasNextLine()) {
                StringTokenizer tok = new StringTokenizer(sc.nextLine(), ",");

                int ID = Integer.parseInt(tok.nextToken());
                int Height1 = Integer.parseInt(tok.nextToken());
                int Height2 = Integer.parseInt(tok.nextToken());
                int Weight = Integer.parseInt(tok.nextToken());
                int BP1 = Integer.parseInt(tok.nextToken());
                int BP2 = Integer.parseInt(tok.nextToken());
                String Reason = tok.nextToken();
                String Treatment = tok.nextToken();
                
                patient.get(ID-1).setRecord(new treatmentRecord(String.valueOf(LocalTime.now()),Reason, Height1, Height2, Weight, BP1, BP2));
                patient.get(ID-1).getRecord().setTreatment(Treatment);
            }
            sc.close();
          } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
          }
        try {
            File file = new File("src/healthcaresystem/Database/PatientAppointment.txt"); 
            Scanner sc = new Scanner(file);
            
            while (sc.hasNextLine()) {
                StringTokenizer tok = new StringTokenizer(sc.nextLine(), ",");

                int ID = Integer.parseInt(tok.nextToken());
                String DateY = tok.nextToken();
                String DateM = tok.nextToken();
                String DateD = tok.nextToken();
                int time = Integer.parseInt(tok.nextToken());
                String dName = tok.nextToken();
                String Notes = tok.nextToken();
                LocalDate date = LocalDate.parse(DateY + "-" + DateM + "-" + DateD, DateTimeFormatter.ISO_LOCAL_DATE);
                for(int i = 0; i < doctor.size(); i++){
                    if(doctor.get(i).getName().equals(dName)){
                        Doctor doc = doctor.get(i);
                        patient.get(ID-1).setAppointment(new appointment(doc, date, time, Notes));
                    }
                }
                
                
                
            }
            sc.close();
          } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
          }
            Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
                LocalDateTime nowTime = LocalDateTime.now();
                try{
                    time.setText(nowTime.format(DateTimeFormatter.ofPattern(pattern)));
                }
                catch(NullPointerException f){
                    
                }
            }),
                 new KeyFrame(Duration.seconds(1))
            );
            clock.setCycleCount(Animation.INDEFINITE);
            clock.play();
    }       
    
    @FXML
    public void loginButtonClick(ActionEvent event) throws Exception{
        String id = loginID.getText();
        String pass = login.getText();
        if(logIn.logIn(id, pass, nurse, doctor, staff, ceo) != null){
            Actor act = (Actor)logIn.logIn(id, pass, nurse, doctor, staff, ceo);
            System.out.println("");
            System.out.print(act.getName() + " has loged in");
            System.out.println("");

            Parent mainSystemParent = FXMLLoader.load(getClass().getResource("MainSystem.fxml"));
            Scene mainSystemScene = new Scene(mainSystemParent);

            Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();

            window.setScene(mainSystemScene);
            window.show();

            Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
            window.setX((screenBounds.getWidth() - window.getWidth()) / 2); 
            window.setY((screenBounds.getHeight() - window.getHeight()) / 2);
        }
        else{
            System.out.println("NOPE");
        }
        
    }
    
    public void LogOutClick(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.getButtonTypes().removeAll(ButtonType.OK);
        alert.getButtonTypes().removeAll(ButtonType.CANCEL);
        alert.getButtonTypes().add(ButtonType.YES);
        alert.getButtonTypes().add(ButtonType.NO);
        alert.setTitle("Logout");
        alert.setHeaderText("Are you sure you want to logout?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.YES){
            Parent mainSystemParent = FXMLLoader.load(getClass().getResource("Login.fxml"));
            Scene mainSystemScene = new Scene(mainSystemParent);

            Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();

            window.setScene(mainSystemScene);
            window.show();

            Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
            window.setX((screenBounds.getWidth() - window.getWidth()) / 2); 
            window.setY((screenBounds.getHeight() - window.getHeight()) / 2);
            System.out.println("Logout at " + LocalDateTime.now().format(DateTimeFormatter.ofPattern(pattern)));
        } else {
            System.out.println("Cancled Logout sequence");
        }
    }
    
    public void viewReportClick(ActionEvent event) throws IOException{
        Parent mainSystemParent = FXMLLoader.load(getClass().getResource("ViewReports.fxml"));
        Scene mainSystemScene = new Scene(mainSystemParent);

        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();

        window.setScene(mainSystemScene);
        window.show();
        
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        window.setX((screenBounds.getWidth() - window.getWidth()) / 2); 
        window.setY((screenBounds.getHeight() - window.getHeight()) / 2);
    }
    
    public void makeAppointmentClick(ActionEvent event) throws IOException{
        Parent mainSystemParent = FXMLLoader.load(getClass().getResource("CreateAppointment.fxml"));
        Scene mainSystemScene = new Scene(mainSystemParent);

        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();

        window.setScene(mainSystemScene);
        window.show();
        
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        window.setX((screenBounds.getWidth() - window.getWidth()) / 2); 
        window.setY((screenBounds.getHeight() - window.getHeight()) / 2);
    }
    
    public void changeAppointmentClick(ActionEvent event) throws IOException{
        Parent mainSystemParent = FXMLLoader.load(getClass().getResource("ChangeAppointment.fxml"));
        Scene mainSystemScene = new Scene(mainSystemParent);

        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();

        window.setScene(mainSystemScene);
        window.show();
        
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        window.setX((screenBounds.getWidth() - window.getWidth()) / 2); 
        window.setY((screenBounds.getHeight() - window.getHeight()) / 2);
    }
    
    public void cancelAppointmentClick(ActionEvent event) throws IOException{
        Parent mainSystemParent = FXMLLoader.load(getClass().getResource("CancelAppointment.fxml"));
        Scene mainSystemScene = new Scene(mainSystemParent);

        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();

        window.setScene(mainSystemScene);
        window.show();
        
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        window.setX((screenBounds.getWidth() - window.getWidth()) / 2); 
        window.setY((screenBounds.getHeight() - window.getHeight()) / 2);
    }
    
    public void updateMeasurementsClick(ActionEvent event) throws IOException{
        Parent mainSystemParent = FXMLLoader.load(getClass().getResource("UpdateMeasurements.fxml"));
        Scene mainSystemScene = new Scene(mainSystemParent);

        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();

        window.setScene(mainSystemScene);
        window.show();
        
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        window.setX((screenBounds.getWidth() - window.getWidth()) / 2); 
        window.setY((screenBounds.getHeight() - window.getHeight()) / 2);
        System.out.println(patient.get(0).getRecord());
    }
    
    public void giveTreatmentClick(ActionEvent event) throws IOException{
        Parent mainSystemParent = FXMLLoader.load(getClass().getResource("giveTreatment.fxml"));
        Scene mainSystemScene = new Scene(mainSystemParent);

        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();

        window.setScene(mainSystemScene);
        window.show();
        
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        window.setX((screenBounds.getWidth() - window.getWidth()) / 2); 
        window.setY((screenBounds.getHeight() - window.getHeight()) / 2);
    }
    
    public void payFeesClick(ActionEvent event) throws IOException{
        Parent mainSystemParent = FXMLLoader.load(getClass().getResource("PayFees.fxml"));
        Scene mainSystemScene = new Scene(mainSystemParent);

        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();

        window.setScene(mainSystemScene);
        window.show();
        
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        window.setX((screenBounds.getWidth() - window.getWidth()) / 2); 
        window.setY((screenBounds.getHeight() - window.getHeight()) / 2);
    }
    
    public void checkInPatientmentClick(ActionEvent event) throws IOException{
        Parent mainSystemParent = FXMLLoader.load(getClass().getResource("CheckInPatient.fxml"));
        Scene mainSystemScene = new Scene(mainSystemParent);

        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();

        window.setScene(mainSystemScene);
        window.show();
        
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        window.setX((screenBounds.getWidth() - window.getWidth()) / 2); 
        window.setY((screenBounds.getHeight() - window.getHeight()) / 2);
    }
    
    public void backClick(ActionEvent event) throws IOException{
        Parent mainSystemParent = FXMLLoader.load(getClass().getResource("MainSystem.fxml"));
        Scene mainSystemScene = new Scene(mainSystemParent);

        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();

        window.setScene(mainSystemScene);
        window.show();
    }
    
    
    private int pat;
    public void findClick(ActionEvent event) throws IOException{
        
        String name = fName.getText() + " " + lName.getText();
        for(int i = 0; i < patient.size(); i++){
            if(patient.get(i).getName().equals(name)){
                System.out.println("Patient found");
                pAddress1.setText(patient.get(i).getChart().getAddress1());
                pAddress2.setText(patient.get(i).getChart().getAddress2());
                pEmail.setText(patient.get(i).getChart().getEmail());
                pInsurance.setText(patient.get(i).getChart().getInsurance());
                pID.setText(String.valueOf(i+1));
                pat = i+1;
                pPhone1.setText(patient.get(i).getChart().getPhone1());
                pBirthday.setText(patient.get(i).getBirthday());
                pSSN.setText(String.valueOf(patient.get(i).getChart().getSSN()));
            }
            else{
                System.out.println("Patient not found");
            }
        }
         try{
            StringBuffer buffer1 = new StringBuffer();
            File file = new File("src/healthcaresystem/Database/PatientAppointment.txt");
            Scanner sc1 = new Scanner(file);
            while (sc1.hasNextLine()) {
                buffer1.append(sc1.nextLine()).append(System.lineSeparator());
            }
            sc1.close();
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                StringTokenizer tok = new StringTokenizer(line, ",");
                if(tok.nextToken().equals(String.valueOf(pat))){
                    aDateY.setText(tok.nextToken());
                    aDateM.setText(tok.nextToken());
                    aDateD.setText(tok.nextToken());
                    aTime.setValue(Integer.parseInt(tok.nextToken()));
                    doctorDropDown.setValue(tok.nextToken());
                    aNotes.setText(tok.nextToken());
                }
            }
            sc.close();
        }catch (FileNotFoundException e) {
          System.out.println("An error occurred.");
        }
    }
    
    public void findIDClick(ActionEvent event) throws IOException{
        int id = Integer.parseInt(pID.getText());
        for(int i = 0; i < patient.size(); i++){
            if(i+1 == id){
                System.out.println("Patient found");
                fName.setText(patient.get(i).getFname());
                lName.setText(patient.get(i).getLname());
                pAddress1.setText(patient.get(i).getChart().getAddress1());
                pAddress2.setText(patient.get(i).getChart().getAddress2());
                pEmail.setText(patient.get(i).getChart().getEmail());
                pInsurance.setText(patient.get(i).getChart().getInsurance());
                pPhone1.setText(patient.get(i).getChart().getPhone1());
                pBirthday.setText(patient.get(i).getBirthday());
                pSSN.setText(String.valueOf(patient.get(i).getChart().getSSN()));
                
            }
            else{
                System.out.println("Patient not found");
            }
        }
        try{
            StringBuffer buffer1 = new StringBuffer();
            File file = new File("src/healthcaresystem/Database/PatientAppointment.txt");
            Scanner sc1 = new Scanner(file);
            while (sc1.hasNextLine()) {
                buffer1.append(sc1.nextLine()).append(System.lineSeparator());
            }
            sc1.close();
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                StringTokenizer tok = new StringTokenizer(line, ",");
                if(tok.nextToken().equals(String.valueOf(id))){
                    aDateY.setText(tok.nextToken());
                    aDateM.setText(tok.nextToken());
                    aDateD.setText(tok.nextToken());
                    aTime.setValue(Integer.parseInt(tok.nextToken()));
                    doctorDropDown.setValue(tok.nextToken());
                    aNotes.setText(tok.nextToken());
                }
            }
            sc.close();
        }catch (FileNotFoundException e) {
          System.out.println("An error occurred.");
        }
    }
    
    public void findClickFees(ActionEvent event) throws IOException{
        String name = fName.getText() + " " + lName.getText();
        for(int i = 0; i < patient.size(); i++){
            if(patient.get(i).getName().equals(name)){
                System.out.println("Patient found");
                pID.setText(String.valueOf(i+1));
                Amount.setText(String.valueOf(patient.get(i).getPayment().getAmountOwed()));
            }
            else{
                System.out.println("Patient not found");
            }
        }
    }
    
    public void findIDClickFees(ActionEvent event) throws IOException{
        int id = Integer.parseInt(pID.getText());
        for(int i = 0; i < patient.size(); i++){
            if(i+1 == id){
                System.out.println("Patient found");
                fName.setText(patient.get(i).getFname());
                lName.setText(patient.get(i).getLname());
                Amount.setText(String.valueOf(patient.get(i).getPayment().getAmountOwed()));
            }
            else{
                System.out.println("Patient not found");
            }
        }
    }
    
     public void findClickTreatment(ActionEvent event) throws IOException{
        String name = fName.getText() + " " + lName.getText();
        for(int i = 0; i < patient.size(); i++){
            if(patient.get(i).getName().equals(name)){
                System.out.println("Patient found");
                pID.setText(String.valueOf(i+1));
                System.out.println(patient.get(i).getRecord());
                if(patient.get(i).getRecord() == null){
                    System.out.println("New treatment record being made!");
                    patient.get(i).setRecord(new treatmentRecord());
                    System.out.println(patient.get(i).getRecord());
                }
                pHeight1.setText(String.valueOf(patient.get(i).getRecord().getHeight1()));
                pHeight2.setText(String.valueOf(patient.get(i).getRecord().getHeight2()));
                pWeight.setText(String.valueOf(patient.get(i).getRecord().getWeight()));
                pBP1.setText(String.valueOf(patient.get(i).getRecord().getBP1()));
                pBP2.setText(String.valueOf(patient.get(i).getRecord().getBP2()));
                aReason.setText(String.valueOf(patient.get(i).getRecord().getReason()));
                aTreatment.setText(String.valueOf(patient.get(i).getRecord().getTreatment()));
            }
            else{
                System.out.println("Patient not found");
            }
        }
    }
    
    public void findIDClickTreatment(ActionEvent event) throws IOException{
        int id = Integer.parseInt(pID.getText());
        for(int i = 0; i < patient.size(); i++){
            if(i+1 == id){
                System.out.println("Patient found");
                fName.setText(patient.get(i).getFname());
                lName.setText(patient.get(i).getLname());
                System.out.println(patient.get(i).getRecord());
                if(patient.get(i).getRecord() == null){
                    System.out.println("New treatment record being made!");
                    patient.get(i).setRecord(new treatmentRecord());
                    System.out.println(patient.get(i).getRecord());
                }
                pHeight1.setText(String.valueOf(patient.get(i).getRecord().getHeight1()));
                pHeight2.setText(String.valueOf(patient.get(i).getRecord().getHeight2()));
                pWeight.setText(String.valueOf(patient.get(i).getRecord().getWeight()));
                pBP1.setText(String.valueOf(patient.get(i).getRecord().getBP1()));
                pBP2.setText(String.valueOf(patient.get(i).getRecord().getBP2()));
                aReason.setText(String.valueOf(patient.get(i).getRecord().getReason()));
                aTreatment.setText(String.valueOf(patient.get(i).getRecord().getTreatment()));
            }
            else{
                System.out.println("Patient not found");
            }
        }
    }
    
    public void generateIDClick(ActionEvent event) throws IOException{
        pID.setText(String.valueOf(patient.size()+1));
    }
    
    public void updateClick(ActionEvent event) throws IOException{
        int id = Integer.parseInt(pID.getText());
        StringBuffer buffer = new StringBuffer();
        try {
            File file = new File("src/healthcaresystem/Database/PatientInformation.txt"); 
            Scanner sc1 = new Scanner(file);
            while (sc1.hasNextLine()) {
                buffer.append(sc1.nextLine()+System.lineSeparator());
             }
            sc1.close();
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                StringTokenizer tok = new StringTokenizer(line, ",");
                if(Integer.parseInt(tok.nextToken()) == id){
                    String Fname = fName.getText();
                    String Lname = lName.getText();
                    String birthday = pBirthday.getText();
                    String add1 = pAddress1.getText();
                    String add2 = pAddress2.getText();
                    if(add2.equals("")){
                        add2 = " ";
                    }
                    String email = pEmail.getText();
                    String ins = pInsurance.getText();
                    if(ins.equals("")){
                        ins = "None";
                    }
                    String ph1 = pPhone1.getText();
                    String ssn = pSSN.getText();
                    String newLine = String.valueOf(id) + "," + Fname + "," + Lname + "," + birthday + "," + add1 + "," + add2 + "," + email + "," + ins + "," + ph1 + "," + ssn;
                    String all = buffer.toString().replace(line, newLine);
                    sc.close();
                    FileWriter writer = new FileWriter("src/healthcaresystem/Database/PatientInformation.txt");
                    writer.append(all);
                    writer.flush();
                    break;
                }
            }
          } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
          }
        //ADD POP UP TO LET KNOW Patient info was updated
    }
    
    public void createClick(ActionEvent event) throws IOException{
        int id = Integer.parseInt(pID.getText());
        if(id  > patient.size()){
           StringBuffer buffer = new StringBuffer();
           StringBuffer buffer1 = new StringBuffer();
            try {
                File file = new File("src/healthcaresystem/Database/PatientInformation.txt"); 
                Scanner sc1 = new Scanner(file);
                while (sc1.hasNextLine()) {
                    buffer.append(sc1.nextLine()+System.lineSeparator());
                 }
                sc1.close();
                String Fname = fName.getText();
                String Lname = lName.getText();
                String birthday = pBirthday.getText();
                String add1 = pAddress1.getText();
                String add2 = pAddress2.getText();
                if(add2.equals("")){
                    add2 = " ";
                }
                String email = pEmail.getText();
                String ins = pInsurance.getText();
                if(ins.equals("")){
                    ins = "None";
                }
                String ph1 = pPhone1.getText();
                String ssn = pSSN.getText();
                String newLine = String.valueOf(id) + "," + Fname + "," + Lname + "," + birthday + "," + add1 + "," + add2 + "," + email + "," + ins + "," + ph1 + "," + ssn;

                buffer.append(newLine);
                FileWriter writer = new FileWriter("src/healthcaresystem/Database/PatientInformation.txt");
                writer.append(buffer);
                writer.flush();
                writer.close();
                patient patien = new patient(Fname, Lname, birthday);
                patien.setChart(new patientChart(patien, add1, add2, email, ins, ph1, ssn));
                patien.setID(id);
                paymentInformation paymentInfo = new paymentInformation(id, patien.getName(), 100000, LocalDate.now());
                patien.setPayment(paymentInfo);
                patient.add(patien);
                
            } catch (FileNotFoundException e) {
              System.out.println("An error occurred.");
            }
            try{
                File file = new File("src/healthcaresystem/Database/PatientRecord.txt");
                Scanner sc1 = new Scanner(file);
                while (sc1.hasNextLine()) {
                    buffer1.append(sc1.nextLine()).append(System.lineSeparator());
                }
                sc1.close();
                buffer1.append(String.valueOf(id)).append(",").append(0).append(",").append(0).append(",").append(0).append(",").append(0).append(",").append(0).append(", , ");
                FileWriter writer = new FileWriter("src/healthcaresystem/Database/PatientRecord.txt");
                writer.append(buffer1);
                writer.flush();
                writer.close();
            }catch (FileNotFoundException e) {
              System.out.println("An error occurred.");
            }
        }
        String date = aDateY.getText() + "-" + aDateM.getText() + "-" + aDateD.getText();
        int time = ((aTime.getValue()) / 100);
        if(time > 12){
            time %= 13;
            time += 1;
        }
        System.out.println(LocalDate.parse(date, DateTimeFormatter.ISO_LOCAL_DATE) + ", " +  time + ", " + aNotes.getText() + ", " + doctorDropDown.getValue() + ", " + String.valueOf(patient.get(id-1).getName()));
        staff.get(0).scheduleApp(doctor, patient, LocalDate.parse(date, DateTimeFormatter.ISO_LOCAL_DATE), time, aNotes.getText(), doctorDropDown.getValue(), String.valueOf(patient.get(id-1).getName()));
        try{
            StringBuffer buffer = new StringBuffer();
            File file = new File("src/healthcaresystem/Database/PatientAppointment.txt"); 
            FileWriter writer = new FileWriter("src/healthcaresystem/Database/PatientAppointment.txt");
            Scanner sc1 = new Scanner(file);
            while (sc1.hasNextLine()) {
                buffer.append(sc1.nextLine()).append(System.lineSeparator());
            }
            String newLine = String.valueOf(id) + "," + aDateY.getText() + "," + aDateM + "," + aDateD + "," + aTime + "," + doctorDropDown.getValue() + "," + aNotes.getText();
            buffer.append(newLine);
            writer.append(buffer);
            writer.flush();
            writer.close();
            sc1.close();
          } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
          }
    //ADD POP UP TO LET KNOW APPOINTMENT WAS CREATED
    }
    
    public void updateClickTreatment(ActionEvent event) throws IOException{
        int ID = Integer.parseInt(pID.getText());
        patient pat = patient.get(ID-1);
        pat.getRecord().setHeight(Integer.parseInt(pHeight1.getText()), Integer.parseInt(pHeight2.getText()));
        pat.getRecord().setBloodPreasure(Integer.parseInt(pBP1.getText()), Integer.parseInt(pBP2.getText()));
        pat.getRecord().setWeight(Integer.parseInt(pWeight.getText()));
        pat.getRecord().setReason(aReason.getText());
        pat.getRecord().setTreatment(aTreatment.getText());
        System.out.println("Treatment Updated!");
        int id = Integer.parseInt(pID.getText());
        StringBuffer buffer = new StringBuffer();
        try {
            File file = new File("src/healthcaresystem/Database/PatientRecord.txt"); 
            Scanner sc1 = new Scanner(file);
            while (sc1.hasNextLine()) {
                buffer.append(sc1.nextLine()+System.lineSeparator());
             }
            sc1.close();
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                StringTokenizer tok = new StringTokenizer(line, ",");
                if(Integer.parseInt(tok.nextToken()) == id){
                    String newLine = String.valueOf(id) + "," + pat.getRecord().getHeight1() + "," + pat.getRecord().getHeight2() + "," + pat.getRecord().getWeight() + "," + pat.getRecord().getBP1() + "," + pat.getRecord().getBP2() + "," + pat.getRecord().getReason() + "," + pat.getRecord().getTreatment();
                    String all = buffer.toString().replace(line, newLine);
                    sc.close();
                    FileWriter writer = new FileWriter("src/healthcaresystem/Database/PatientRecord.txt");
                    writer.append(all);
                    writer.flush();
                    break;
                }
            }
          } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
          }
    }
 
    public void updateClickAppointment(ActionEvent event) throws IOException{
        try{
            StringBuffer buffer1 = new StringBuffer();
            File file = new File("src/healthcaresystem/Database/PatientAppointment.txt");
            Scanner sc1 = new Scanner(file);
            while (sc1.hasNextLine()) {
                buffer1.append(sc1.nextLine()).append(System.lineSeparator());
            }
            sc1.close();
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String newLine = pID.getText() + "," + aDateY.getText() + "," + aDateM.getText() + "," + aDateD.getText() + "," + aTime.getValue() + "," + doctorDropDown.getValue() + "," + aNotes.getText();
                String replace = buffer1.toString().replace(line, newLine);
                sc.close();
                FileWriter writer = new FileWriter("src/healthcaresystem/Database/PatientAppointment.txt");
                writer.append(replace);
                writer.flush();
                String date = aDateY.getText() + "-" + aDateM.getText() + "-" + aDateD.getText();
                int time = ((aTime.getValue()) / 100);
                if(time > 12){
                    time %= 13;
                    time += 1;
                }
                staff.get(0).changeApp(patient, patient.get(Integer.parseInt(pID.getText())-1).getName(), doctor, LocalDate.parse(date, DateTimeFormatter.ISO_LOCAL_DATE), time, aNotes.getText());
                System.out.println("Appointment Updated!");
                break;
            }
            
        }catch (FileNotFoundException e) {
          System.out.println("An error occurred.");
        }
    }
}
