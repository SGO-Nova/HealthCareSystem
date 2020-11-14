/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package healthcaresystem;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.util.Duration;
import Backend.*;

/**
 * FXML Controller class
 *
 * @author ryanb
 */
public class UpdateMeasurementsController implements Initializable {

    @FXML
    private Label time;
    private String pattern = "ccc | yyyy-MM-dd | hh:mm:ss a";
    
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
    
    public void backClick(ActionEvent event) throws IOException{
        Parent mainSystemParent = FXMLLoader.load(getClass().getResource("MainSystem.fxml"));
        Scene mainSystemScene = new Scene(mainSystemParent);

        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();

        window.setScene(mainSystemScene);
        window.show();
    }
}
