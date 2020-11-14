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

public class LoginController implements Initializable {

    @FXML
    private TextField loginID;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    public void loginButtonClick(ActionEvent event) throws Exception{
        String id = loginID.getText();
        System.out.println("");
        System.out.print(id + " has loged in");
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
    
}
