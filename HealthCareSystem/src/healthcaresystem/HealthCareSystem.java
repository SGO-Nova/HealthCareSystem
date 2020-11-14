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

public class HealthCareSystem extends Application {
    
    private String pattern = "HH:mm:ss";
    
    @Override
    public void start(Stage stage) throws Exception {
        
        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            LocalDateTime nowTime = LocalDateTime.now();
            if(((String)LocalDateTime.now().format(DateTimeFormatter.ofPattern(pattern))).equals("20:00:00")){
                System.out.println("CLEARING ALL NO-SHOW APPOINTMENTS");
            }
            if(((String)LocalDateTime.now().format(DateTimeFormatter.ofPattern(pattern))).equals("21:00:00")){
                System.out.println("GENERATING DAILY REPORTS");
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
