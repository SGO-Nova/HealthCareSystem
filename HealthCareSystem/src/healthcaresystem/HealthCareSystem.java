package healthcaresystem;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import Backend.*;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HealthCareSystem extends Application {
    
    private String pattern = "HH:mm:ss";
    
    @Override
    public void start(Stage stage) throws Exception {
        
        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            LocalDateTime nowTime = LocalDateTime.now();
            LoginController repo = new LoginController();
            if(((String)LocalDateTime.now().format(DateTimeFormatter.ofPattern(pattern))).equals("20:00:00")){
                System.out.println("CLEARING ALL NO-SHOW APPOINTMENTS");
                try {
                    repo.clearAppointmentsDaily();
                } catch (IOException ex) {
                    Logger.getLogger(HealthCareSystem.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(((String)LocalDateTime.now().format(DateTimeFormatter.ofPattern(pattern))).equals("21:00:30")){
                System.out.println("Making daily report");
                 
                try {
                    repo.makeReport();
                } catch (IOException ex) {
                    Logger.getLogger(HealthCareSystem.class.getName()).log(Level.SEVERE, null, ex);
                }
                System.out.println("Completed making daily report.");
            }
        }),
             new KeyFrame(Duration.seconds(1))
                
        );
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
        System.out.println(LocalDateTime.now().format(DateTimeFormatter.ofPattern(pattern)));
        
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();
        
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}
