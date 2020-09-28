package healthcaresystem;

import java.io.IOException;
import java.net.URL;
import javafx.util.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class MainSystemController implements Initializable {
    
    private String pattern = "ccc | yyyy-MM-dd | hh:mm:ss a";
    
    @FXML
    private Label time;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            LocalDateTime nowTime = LocalDateTime.now();
            time.setText(nowTime.format(DateTimeFormatter.ofPattern(pattern)));
        }),
             new KeyFrame(Duration.seconds(1))
        );
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
    }    
    
    
    public void LogOutClick(ActionEvent event) throws IOException {
        Alert alert = new Alert(AlertType.CONFIRMATION);
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
    
}
