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
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner; 
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.Optional;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class LoginController implements Initializable {

    @FXML
    private TextField loginID;
    @FXML
    private PasswordField login;
    @FXML
    private Label time;
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
    private TextField pPhone2;
    @FXML
    private TextField pSSN;
    
    private ArrayList<patient> patient = new ArrayList<patient>();
    private ArrayList<nurse> nurse = new ArrayList<nurse>();
    private ArrayList<Doctor> doctor = new ArrayList<Doctor>();
    private ArrayList<staff> staff = new ArrayList<staff>();
    private ArrayList<CEO> ceo = new ArrayList<CEO>();
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
                String ph2 = tok.nextToken();
                int ssn = Integer.parseInt(tok.nextToken());
                
                patient patien = new patient(Fname, Lname, birthday, add1, add2, email, ins, ph1, ph2, ssn);
                patien.setID(ID);
                patient.add(patien);
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
    
    public void findClick(ActionEvent event) throws IOException{
        String name = fName.getText() + " " + lName.getText();
        for(int i = 0; i < patient.size(); i++){
            if(patient.get(i).getName().equals(name)){
                System.out.println("Patient found");
                pAddress1.setText(patient.get(i).getAddress1());
                pAddress2.setText(patient.get(i).getAddress2());
                pEmail.setText(patient.get(i).getEmail());
                pInsurance.setText(patient.get(i).getInsurance());
                pID.setText(String.valueOf(i+1));
                pPhone1.setText(patient.get(i).getPhone1());
                pPhone2.setText(patient.get(i).getPhone2());
                pSSN.setText(String.valueOf(patient.get(i).getSSN()));
            }
            else{
                System.out.println("Patient not found");
            }
        }
    }
    
    public void findIDClick(ActionEvent event) throws IOException{
        int id = Integer.parseInt(pID.getText());
        for(int i = 0; i < patient.size(); i++){
            if(i+1 == id){
                System.out.println("Patient found");
                fName.setText(patient.get(i).getFname());
                lName.setText(patient.get(i).getLname());
                pAddress1.setText(patient.get(i).getAddress1());
                pAddress2.setText(patient.get(i).getAddress2());
                pEmail.setText(patient.get(i).getEmail());
                pInsurance.setText(patient.get(i).getInsurance());
                pPhone1.setText(patient.get(i).getPhone1());
                pPhone2.setText(patient.get(i).getPhone2());
                pSSN.setText(String.valueOf(patient.get(i).getSSN()));
            }
            else{
                System.out.println("Patient not found");
            }
        }
    }
    
}
