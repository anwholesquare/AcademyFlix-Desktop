/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author anan
 */
public class AlertBoxController implements Initializable {

    @FXML
    private Label subjectLabel;
    @FXML
    private Label bodyLabel;
    @FXML
    private Button btnCreateOrbit;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        subjectLabel.setText(Main.AlertSubject);
        bodyLabel.setText(Main.AlertBody);
        
        btnCreateOrbit.setOnAction(e-> { 
            Main.DialogStage.close();
        } );
        
    }    
    
}
