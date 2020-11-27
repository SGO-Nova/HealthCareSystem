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
import java.time.format.DateTimeParseException;
import java.util.Scanner; 
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.util.Duration;

public class LoginController implements Initializable {

    @FXML
    private Button A1, A2, A3, CA, PF, UM, GT, VR, checkButt, pButton, pCCI, UpButt;
    @FXML
    private CheckBox check1, check2, check3;
    @FXML
    private PasswordField login;
    @FXML
    private Label time, Amount;
    @FXML
    private TextField loginID, fName, lName, pAddress1, pAddress2, pEmail, pInsurance, pID, pPhone1, pBirthday, pSSN, aDateY, aDateM, aDateD, pHeight1, pHeight2, pWeight, pBP1, pBP2, pTotal, dProfit, tFee, tax, total, cardNumber, cardExp1, cardExp2, cardCVV, cardName, cardZIP;
    @FXML
    private TextArea aReason, aTreatment, aNotes, reportArea;
    @FXML
    private ComboBox<String> doctorDropDown = new ComboBox<String>();
    @FXML
    private ComboBox<String> checkInDropDown = new ComboBox<String>();
    @FXML
    private ComboBox<Integer> aTime = new ComboBox<Integer>();
    @FXML
    private ComboBox<String> pType = new ComboBox<String>();
    @FXML
    private ComboBox<String> reportDropDown = new ComboBox<String>();
    
    private ArrayList<patient> appointments_today = new ArrayList<patient>();
    private ArrayList<patient> patient = new ArrayList<patient>();
    private ArrayList<nurse> nurse = new ArrayList<nurse>();
    private ArrayList<Doctor> doctor = new ArrayList<Doctor>();
    private ArrayList<staff> staff = new ArrayList<staff>();
    private ArrayList<CEO> ceo = new ArrayList<CEO>();
    private ArrayList<String> reports = new ArrayList<String>();
    private String[] report;
    private String pattern = "ccc | yyyy-MM-dd | hh:mm:ss a";
    

    
  
    
    
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
        try {
            File file = new File("src/healthcaresystem/Database/PatientDueRecord.txt"); 
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                StringTokenizer tok = new StringTokenizer(sc.nextLine(), ",");

                int ID = Integer.parseInt(tok.nextToken());
                int owe = Integer.parseInt(tok.nextToken());
                String DateY = tok.nextToken();
                String DateM = tok.nextToken();
                String DateD = tok.nextToken();
                
                String date = DateY + "-" + DateM + "-" + DateD;
                patient patien = patient.get(ID-1);
                paymentInformation paymentInfo = new paymentInformation(ID, patien.getName(), owe, LocalDate.parse(date));
                patien.setPayment(paymentInfo);
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
            for(int i = 0; i < doctor.size(); i++){
                doctorDropDown.getItems().addAll(doctor.get(i).getName());
            }
            File path = new File("src/healthcaresystem/Database/Reports");
            report = path.list();
            reports.addAll(Arrays.asList(report));
             ObservableList<Integer> times = FXCollections.observableArrayList(900,1000,1100,1200,1300,1400,1500,1600,1700);
            aTime.getItems().addAll(times);
            pType.getItems().addAll("Debit", "Credit", "Cash");
            reportDropDown.getItems().addAll(reports);
            for(patient pat : patient){
                if((pat.getAppointment() != null) && (pat.getAppointment().getDate().toString().equals(LocalDate.now().toString()))){
                    appointments_today.add(pat);
                }
            }
            for(int i = 0; i < appointments_today.size(); i++){
                checkInDropDown.getItems().addAll(appointments_today.get(i).getName());
                System.out.println(appointments_today.get(i).getName() + ": " + i);
            }
            try{
                StringBuffer buffer3 = new StringBuffer();
                File file = new File("src/healthcaresystem/Database/CurrentClearance.txt");
                Scanner sc1 = new Scanner(file);
                while (sc1.hasNextLine()) {
                    buffer3.append(sc1.nextLine());
                }
                sc1.close();
                String buf = buffer3.toString();
                try{
                    switch(Integer.parseInt(buf)){
                        case 1:
                            A1.setDisable(false);
                            A2.setDisable(false);
                            A3.setDisable(false);
                            CA.setDisable(false);
                            PF.setDisable(false);
                            UM.setDisable(true);
                            GT.setDisable(true);
                            VR.setDisable(true);
                            break;
                        case 2:
                            A1.setDisable(true);
                            A2.setDisable(true);
                            A3.setDisable(true);
                            CA.setDisable(true);
                            PF.setDisable(true);
                            UM.setDisable(false);
                            GT.setDisable(true);
                            VR.setDisable(true);
                            break;
                        case 3:
                            A1.setDisable(true);
                            A2.setDisable(true);
                            A3.setDisable(true);
                            CA.setDisable(true);
                            PF.setDisable(true);
                            UM.setDisable(true);
                            GT.setDisable(false);
                            VR.setDisable(true);
                            break;
                        case 4:
                            A1.setDisable(true);
                            A2.setDisable(true);
                            A3.setDisable(true);
                            CA.setDisable(true);
                            PF.setDisable(true);
                            UM.setDisable(true);
                            GT.setDisable(true);
                            VR.setDisable(false);
                            break;
                        default:
                            A1.setDisable(true);
                            A2.setDisable(true);
                            A3.setDisable(true);
                            CA.setDisable(true);
                            PF.setDisable(true);
                            UM.setDisable(true);
                            GT.setDisable(true);
                            VR.setDisable(true);
                    } 
                }catch(NullPointerException f){
                System.out.print("IDK at this point");
            }
                
            }catch(IOException e){
                System.out.print("IO Error");
            }
            
    }       
    
    
    
    @FXML
    public void loginButtonClick(ActionEvent event) throws IOException{
        String id = loginID.getText();
        String pass = login.getText();
        int clearance;
        if(logIn.logIn(id, pass, nurse, doctor, staff, ceo) != -1){
            clearance = logIn.logIn(id, pass, nurse, doctor, staff, ceo);
            StringBuffer buffer = new StringBuffer();
            try{
                buffer.append(clearance);
                FileWriter writer = new FileWriter("src/healthcaresystem/Database/CurrentClearance.txt");
                writer.append(buffer);
                writer.flush();
                writer.close();
            }catch (FileNotFoundException e) {
              System.out.println("An error occurred.");
            }
            
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
                pType.setDisable(false);
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
                pType.setDisable(false);
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
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.getButtonTypes().removeAll(ButtonType.CANCEL);
        alert.setTitle("Patient Information Update");
        alert.setHeaderText("Patient Information has been updated!");
        Optional<ButtonType> result = alert.showAndWait();
        if(result.get() == ButtonType.OK){
            Parent mainSystemParent = FXMLLoader.load(getClass().getResource("MainSystem.fxml"));
            Scene mainSystemScene = new Scene(mainSystemParent);

            Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();

            window.setScene(mainSystemScene);
            window.show();
        }
        //ADD POP UP TO LET KNOW Patient info was updated
    }
    
    public void createClick(ActionEvent event) throws IOException{
        int id = Integer.parseInt(pID.getText());
        if(id  > patient.size()){
           StringBuffer buffer = new StringBuffer();
           StringBuffer buffer1 = new StringBuffer();
           StringBuffer buffer2 = new StringBuffer();
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
                paymentInformation paymentInfo = new paymentInformation(id, patien.getName(), 0, LocalDate.now());
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
            try{
                File file = new File("src/healthcaresystem/Database/PatientDueRecord.txt");
                Scanner sc1 = new Scanner(file);
                while (sc1.hasNextLine()) {
                    buffer2.append(sc1.nextLine()).append(System.lineSeparator());
                }
                sc1.close();
                buffer2.append(String.valueOf(id)).append(",").append(0).append(",").append(LocalDate.now().getYear()).append(",").append(LocalDate.now().getMonthValue()).append(",").append(LocalDate.now().getDayOfMonth());
                FileWriter writer = new FileWriter("src/healthcaresystem/Database/PatientDueRecord.txt");
                writer.append(buffer2);
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
        //System.out.println(LocalDate.parse(date, DateTimeFormatter.ISO_LOCAL_DATE) + ", " +  time + ", " + aNotes.getText() + ", " + doctorDropDown.getValue() + ", " + String.valueOf(patient.get(id-1).getName()));
        try{
            staff.get(0).scheduleApp(doctor, patient, LocalDate.parse(date, DateTimeFormatter.ISO_LOCAL_DATE), time, aNotes.getText(), doctorDropDown.getValue(), String.valueOf(patient.get(id-1).getName()));
            try{
                StringBuffer buffer = new StringBuffer();
                File file = new File("src/healthcaresystem/Database/PatientAppointment.txt"); 
                Scanner sc1 = new Scanner(file);
                while (sc1.hasNextLine()) {
                    buffer.append(sc1.nextLine()).append(System.lineSeparator());
                }
                sc1.close();
                System.out.println(buffer.toString());
                String newLine = String.valueOf(id) + "," + aDateY.getText() + "," + aDateM.getText() + "," + aDateD.getText() + "," + aTime.getValue() + "," + doctorDropDown.getValue() + "," + aNotes.getText();
                buffer.append(newLine).append(System.lineSeparator());
                System.out.println(buffer.toString());
                FileWriter writer = new FileWriter("src/healthcaresystem/Database/PatientAppointment.txt");
                writer.append(buffer);
                writer.flush();
                writer.close();
              } catch (FileNotFoundException e) {
                System.out.println("An error occurred.");
              }
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.getButtonTypes().removeAll(ButtonType.CANCEL);
            alert.setTitle("Patient Creation");
            alert.setHeaderText("Patient Appointment has been created!");
            Optional<ButtonType> result = alert.showAndWait();
            if(result.get() == ButtonType.OK){
                Parent mainSystemParent = FXMLLoader.load(getClass().getResource("MainSystem.fxml"));
                Scene mainSystemScene = new Scene(mainSystemParent);

                Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();

                window.setScene(mainSystemScene);
                window.show();
            }
        }catch(DateTimeParseException d){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.getButtonTypes().removeAll(ButtonType.CANCEL);
            alert.setTitle("ERROR");
            alert.setHeaderText("Make sure the date in put in correctly! \n yyyy-mm-dd");
            Optional<ButtonType> result = alert.showAndWait();
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
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.getButtonTypes().removeAll(ButtonType.CANCEL);
        alert.setTitle("Patient Information Update");
        alert.setHeaderText("Patient Information has been updated!");
        Optional<ButtonType> result = alert.showAndWait();
        if(result.get() == ButtonType.OK){
            Parent mainSystemParent = FXMLLoader.load(getClass().getResource("MainSystem.fxml"));
            Scene mainSystemScene = new Scene(mainSystemParent);

            Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();

            window.setScene(mainSystemScene);
            window.show();
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
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.getButtonTypes().removeAll(ButtonType.CANCEL);
        alert.setTitle("Patient Appointment Update");
        alert.setHeaderText("Patient Appointment has been updated!");
        Optional<ButtonType> result = alert.showAndWait();
        if(result.get() == ButtonType.OK){
            Parent mainSystemParent = FXMLLoader.load(getClass().getResource("MainSystem.fxml"));
            Scene mainSystemScene = new Scene(mainSystemParent);

            Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();

            window.setScene(mainSystemScene);
            window.show();
        }
    }
    
    public void updateReportText(ActionEvent event)throws IOException{
        try {
            String link = "src/healthcaresystem/Database/Reports/";
            link = link.concat(reportDropDown.getValue());
            File file = new File(link); 
            Scanner sc = new Scanner(file);
            StringBuffer buffer = new StringBuffer();
            while (sc.hasNextLine()) {
                buffer.append(sc.nextLine()+System.lineSeparator());
            }
            sc.close();
            reportArea.setText(buffer.toString());
          } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
          }
    }
    
    public void cancelAppClick(ActionEvent event)throws IOException{
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.getButtonTypes().removeAll(ButtonType.OK);
        alert.getButtonTypes().removeAll(ButtonType.CANCEL);
        alert.getButtonTypes().add(ButtonType.YES);
        alert.getButtonTypes().add(ButtonType.NO);
        alert.setTitle("Cancel Appointment");
        alert.setHeaderText("Are you sure you want to canel the appointment?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.YES){
            for(Doctor doc : doctor){
                if(doc.getName().equals(patient.get(Integer.parseInt(pID.getText())-1).getAppointment().getDoctor().getName())){
                    String date = aDateY.getText() + "-" + aDateM.getText() + "-" + aDateD.getText();
                    doc.getSchedule().cancelApp(aTime.getValue(), LocalDate.parse(date, DateTimeFormatter.ISO_LOCAL_DATE), patient);
                }
            }
           try {
            StringBuffer buffer = new StringBuffer();
            File file = new File("src/healthcaresystem/Database/PatientAppointment.txt"); 
            Scanner sc1 = new Scanner(file);
            while (sc1.hasNextLine()) {
                buffer.append(sc1.nextLine()+System.lineSeparator());
            }
            sc1.close();
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                StringTokenizer tok = new StringTokenizer(line, ",");
                if(tok.nextToken().equals(pID.getText())){
                    String all = buffer.toString().replaceAll(line, "").trim();
                    sc.close();
                    FileWriter writer = new FileWriter("src/healthcaresystem/Database/PatientAppointment.txt");
                    writer.append(all);
                    writer.flush();
                    System.out.println("Appointment Canceled");
                    break;
                }
            }
          } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
          }
             
            Parent mainSystemParent = FXMLLoader.load(getClass().getResource("MainSystem.fxml"));
            Scene mainSystemScene = new Scene(mainSystemParent);

            Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();

            window.setScene(mainSystemScene);
            window.show();

            Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
            window.setX((screenBounds.getWidth() - window.getWidth()) / 2); 
            window.setY((screenBounds.getHeight() - window.getHeight()) / 2);
        } else {
            System.out.println("Cancled Logout sequence");
        }
    }
    
    public void makeReport()throws IOException{
        try{
            String link = "src/healthcaresystem/Database/Reports/";
            link = link.concat(String.valueOf(LocalDate.parse(String.valueOf(LocalDate.now()),DateTimeFormatter.ISO_LOCAL_DATE)));
            link = link.concat(".txt");
            FileWriter writer = new FileWriter(link);
            StringBuffer buffer = new StringBuffer();
            System.out.println(link);

            buffer.append("Doctors:\n");
            for(Doctor doc : doctor){
                String doctor_name = doc.getName();
                int number_of_paitents = doc.getNumberOfPatients();
                int amount_earned = doc.getEarned();
                System.out.println(doc.getNumberOfPatients() + " : " + doc.getEarned());

                String doctorFormat = String.format("\tName: Dr. %s\n\t\tNumber of Patients: %d\n\t\tAmount earned: $%d\n", doctor_name, number_of_paitents, amount_earned);
                buffer.append(doctorFormat);
                doc.resetPaitentsAndEarned();
            }
            buffer.append("\nReported at: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern(pattern)));
            writer.append(buffer);
            writer.flush();
        }catch(FileNotFoundException e){
            System.out.println("No can do");
        }
        
        
        
    }
    
    public void checkInClick(ActionEvent event)throws IOException{
        System.out.println("CHECKING IN!");
        String name = fName.getText() + " " + lName.getText();
        String bday = "";
        String docname = doctorDropDown.getValue();
        for(patient pat : patient){
            if(pat.getName().equals(name)){
                bday = pat.getBirthday();
                if(pat.getPayment() == null){
                    paymentInformation pay = new paymentInformation(pat.getID(), pat.getName(), (int)Float.parseFloat(total.getText()), LocalDate.now());
                    pat.setPayment(pay);
                    System.out.println("patient's total: " + pat.getPayment().getAmountOwed());
                }
                else{
                    pat.getPayment().setAmountOwed(pat.getPayment().getAmountOwed()+ ((int) (Float.parseFloat(total.getText()))));
                    System.out.println("patient's new total: " + pat.getPayment().getAmountOwed());
                }
                
                StringBuffer buffer1 = new StringBuffer();
                try {
                    File file = new File("src/healthcaresystem/Database/PatientDueRecord.txt"); 
                    Scanner sc1 = new Scanner(file);
                    while (sc1.hasNextLine()) {
                        buffer1.append(sc1.nextLine()+System.lineSeparator());
                     }
                    sc1.close();
                    Scanner sc = new Scanner(file);
                    while (sc.hasNextLine()) {
                        String line = sc.nextLine();
                        StringTokenizer tok = new StringTokenizer(line, ",");
                        if(Integer.parseInt(tok.nextToken()) == pat.getID()){
                            String ID = pID.getText();
                            int tot = pat.getPayment().getAmountOwed();
                            
                            String newLine = String.valueOf(ID) + "," + tot + "," + LocalDate.now().getYear() + "," + LocalDate.now().getMonthValue() + "," + LocalDate.now().getDayOfMonth();
                            String all = buffer1.toString().replace(line, newLine);
                            sc.close();
                            FileWriter writer = new FileWriter("src/healthcaresystem/Database/PatientDueRecord.txt");
                            writer.append(all);
                            writer.flush();
                            break;
                        }
                    }
                  } catch (FileNotFoundException e) {
                    System.out.println("An error occurred.");
                  }
                
                staff.get(0).checkIn(patient, name, bday, doctor, docname);
                appointments_today.remove(pat);
                for(Doctor doc : doctor){
                    if(doc.getName().equals(docname)){
                        String date = aDateY.getText() + "-" + aDateM.getText() + "-" + aDateD.getText();
                        doc.getSchedule().cancelApp(aTime.getValue(), LocalDate.parse(date, DateTimeFormatter.ISO_LOCAL_DATE), patient);
                        doc.addNumberOfPatient();
                        doc.updateEarned((int)Float.parseFloat(dProfit.getText()));
                    }
                }
               try {
                StringBuffer buffer = new StringBuffer();
                File file = new File("src/healthcaresystem/Database/PatientAppointment.txt"); 
                Scanner sc1 = new Scanner(file);
                while (sc1.hasNextLine()) {
                    buffer.append(sc1.nextLine()+ System.lineSeparator());
                }
                sc1.close();
                Scanner sc = new Scanner(file);
                while (sc.hasNextLine()) {
                    String line = sc.nextLine();
                    StringTokenizer tok = new StringTokenizer(line, ",");
                    if(tok.nextToken().equals(pID.getText())){
                        String all = buffer.toString().replaceAll(line, "").trim();
                        sc.close();
                        FileWriter writer = new FileWriter("src/healthcaresystem/Database/PatientAppointment.txt");
                        writer.append(all);
                        writer.flush();
                        break;
                    }
                }
              } catch (FileNotFoundException e) {
                System.out.println("An error occurred.");
              }
            }
        }
        
    }
    
    public void checkInUpdate(ActionEvent event)throws IOException{
        for(patient pat : patient){
            if(checkInDropDown.getValue().toString().equals(pat.getName())){
                fName.setText(pat.getFname());
                lName.setText(pat.getLname());
                pID.setText(String.valueOf(pat.getID()));
                doctorDropDown.setValue(pat.getAppointment().getDoctor().getName());       
                StringTokenizer tok = new StringTokenizer(pat.getAppointment().getDate().toString(),"-"); 
                String dateY = tok.nextToken();
                String dateM = tok.nextToken();
                String dateD = tok.nextToken();
                aDateY.setText(dateY);
                aDateM.setText(dateM);
                aDateD.setText(dateD);
                int time = pat.getAppointment().getTime();
                if(time < 800){
                    time += 1200;
                }
                aTime.setValue(time);
                aNotes.setText(pat.getAppointment().getNotes());
            }
        }
        
    }
    
    public void checkCheck(ActionEvent event)throws IOException{
        System.out.println(check1.selectedProperty().getValue() +","+check2.selectedProperty().getValue()+","+check3.selectedProperty().getValue());
        if(check1.selectedProperty().getValue() && check2.selectedProperty().getValue() && check3.selectedProperty().getValue()){
            System.out.println("Enabling");
            checkButt.setDisable(false);
            checkButt.setOpacity(1);
        }
        else{
            checkButt.setDisable(true);
            checkButt.setOpacity(.6);
        }
    }
    
    public void totalUpdate(ActionEvent event)throws IOException{
        int pre_total = Integer.parseInt(pTotal.getText());
        dProfit.setText(String.valueOf(pre_total*.25));
        tFee.setText(String.valueOf(pre_total*.1));
        int subTotal = (int) (pre_total + (pre_total*.25) + (pre_total*.1));
        tax.setText(String.valueOf(subTotal * .0825));
        total.setText(String.valueOf(subTotal + subTotal*.0825));
        check3.selectedProperty().setValue(true);
        checkCheck(event);
    }
    
    public void checkCardInfoClick(ActionEvent event)throws IOException{
       String cardDate = "20" + cardExp2.getText() + "-" + cardExp1.getText() + "-01";
       LocalDate card = LocalDate.parse(cardDate, DateTimeFormatter.ISO_LOCAL_DATE);
       if(LocalDate.now().compareTo(card) <= 0){
           bank rand = new bank();
            if(cardNumber.getText().getBytes().length == 16){
                if(cardCVV.getText().getBytes().length == 3 || cardCVV.getText().getBytes().length == 4){
                    if(cardZIP.getText().getBytes().length == 5){
                        if(cardName.getText().getBytes().length > 3){
                            if(rand.approval() > 0){
                                System.out.println("Card info went through");
                                pButton.setDisable(false);
                                pCCI.setDisable(true);
                            }
                            else{
                                System.out.println("Card info did not go through");
                                pButton.setDisable(true);
                            } 
                        }
                        else{
                            System.out.println("Card Name length is wrong: " + cardName.getText().getBytes().length);
                            pButton.setDisable(true);
                        }
                    }
                    else{
                        System.out.println("Card ZIP length is wrong: " + cardZIP.getText().getBytes().length);
                        pButton.setDisable(true);
                    }
                }
                else{
                    System.out.println("Card CVV length wrong: " + cardCVV.getText().getBytes().length);
                    pButton.setDisable(true);
                }
            }
            else{
                System.out.println("Card number length wrong: " + cardNumber.getText().getBytes().length);
                pButton.setDisable(true);
            }
        }
        else{
            System.out.println("Card out of date");
            pButton.setDisable(true);
        }
    }
    
    public void typeChange(ActionEvent event)throws IOException{
        if(pType.getValue().equals("Cash")){
            cardNumber.setDisable(true);
            cardExp1.setDisable(true);
            cardExp2.setDisable(true);
            cardCVV.setDisable(true);
            cardName.setDisable(true);
            cardZIP.setDisable(true);
            pButton.setDisable(false);
            pCCI.setDisable(true);
        }
        else{
            cardNumber.setDisable(false);
            cardExp1.setDisable(false);
            cardExp2.setDisable(false);
            cardCVV.setDisable(false);
            cardName.setDisable(false);
            cardZIP.setDisable(false);
            pButton.setDisable(true);
            pCCI.setDisable(false);
        }
        
    }
    
    public void payClick(ActionEvent event)throws IOException{
        StringBuffer buffer1 = new StringBuffer();
        try{
            File file = new File("src/healthcaresystem/Database/PatientPaymentRecord.txt");
            Scanner sc1 = new Scanner(file);
            while (sc1.hasNextLine()) {
                buffer1.append(sc1.nextLine()).append(System.lineSeparator());
            }
            sc1.close();
            buffer1.append(String.valueOf(pID.getText())).append(",").append(Amount.getText()).append(",").append(pType.getValue()).append(",").append(LocalDate.now());
            FileWriter writer = new FileWriter("src/healthcaresystem/Database/PatientPaymentRecord.txt");
            writer.append(buffer1);
            writer.flush();
            writer.close();
        }catch (FileNotFoundException e) {
          System.out.println("An error occurred.");
        }
        patient.get(Integer.parseInt(pID.getText())-1).getPayment().setAmountOwed(0);
        StringBuffer buffer = new StringBuffer();
        try {
            File file = new File("src/healthcaresystem/Database/PatientDueRecord.txt"); 
            Scanner sc1 = new Scanner(file);
            while (sc1.hasNextLine()) {
                buffer.append(sc1.nextLine()+System.lineSeparator());
             }
            sc1.close();
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                StringTokenizer tok = new StringTokenizer(line, ",");
                if(Integer.parseInt(tok.nextToken()) == Integer.parseInt(pID.getText())){
                    String ID = pID.getText();

                    String newLine = String.valueOf(ID) + "," + 0 + "," + LocalDate.now().getYear() + "," + LocalDate.now().getMonthValue() + "," + LocalDate.now().getDayOfMonth();
                    String all = buffer.toString().replace(line, newLine);
                    sc.close();
                    FileWriter writer = new FileWriter("src/healthcaresystem/Database/PatientDueRecord.txt");
                    writer.append(all);
                    writer.flush();
                    break;
                }
            }
          } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
          }
        System.out.println("Payment went through!");
        System.out.println(patient.get(Integer.parseInt(pID.getText())-1).getPayment().getAmountOwed());
    }
    
    public void clearAppointmentsDaily()throws IOException{
        for(patient pat : appointments_today){
            appointments_today.remove(pat);
            for(Doctor doc : doctor){
                if(doc.getName().equals(pat.getAppointment().getDoctor().getName())){
                    doc.getSchedule().cancelApp(aTime.getValue(), LocalDate.now(), patient);
                }
            }
            try {
                StringBuffer buffer = new StringBuffer();
                File file = new File("src/healthcaresystem/Database/PatientAppointment.txt"); 
                Scanner sc1 = new Scanner(file);
                while (sc1.hasNextLine()) {
                    buffer.append(sc1.nextLine()+ System.lineSeparator());
                }
                sc1.close();
                Scanner sc = new Scanner(file);
                while (sc.hasNextLine()) {
                    String line = sc.nextLine();
                    StringTokenizer tok = new StringTokenizer(line, ",");
                    if(tok.nextToken().equals(pat.getID())){
                        String all = buffer.toString().replaceAll(line, "").trim();
                        sc.close();
                        FileWriter writer = new FileWriter("src/healthcaresystem/Database/PatientAppointment.txt");
                        writer.append(all);
                        writer.flush();
                        break;
                    }
                }
              } catch (FileNotFoundException e) {
                System.out.println("An error occurred.");
              }
        }
    }
    
    public void checkAvailButton(ActionEvent event)throws IOException{
        patient pat = patient.get(Integer.parseInt(pID.getText())-1);
        int time = aTime.getValue();
        time /= 100;
        if(time > 12)
            time -=12;
        String date1 = aDateY.getText() + "-" + aDateM.getText() + "-" + aDateD.getText();
        LocalDate date = LocalDate.parse(date1, DateTimeFormatter.ISO_LOCAL_DATE);
        //System.out.println("Doctors current schedule: ");
        for(Doctor doc : doctor){
            if(doc.getName().equals(doctorDropDown.getValue())){
                doc.getSchedule().printSchedule(date);
                System.out.println(date + " : " + time);
                if(doc.getSchedule().checkSchedule(time,date)){
                    System.out.println("Can make appointment");
                    UpButt.setDisable(false);
                }
                else{
                    System.out.println("Cannot make appointment");
                    UpButt.setDisable(true);
                }
            }
        }       
        //take input for new date/time
        //LocalDate date = LocalDate.now();
        //LocalDate date = LocalDate.of(year,month,day);
        //int time = 9;
        //int time = input of some kind.
        

}
    
}
