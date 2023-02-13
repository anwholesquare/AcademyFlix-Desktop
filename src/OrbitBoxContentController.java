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
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author anan
 */
public class OrbitBoxContentController implements Initializable {

    @FXML
    private Button orbitBtn;
    @FXML
    private Button contentBtn;
    @FXML
    private Button generalBtn;
    @FXML
    private Button orbitDashBtn;
    @FXML
    private Label LabelTitle;
    @FXML
    private Button liveBtn;
    @FXML
    private Button btnAddBox;
    @FXML
    private ScrollPane OrbitScrollPane;
    @FXML
    private VBox OrbitHolders;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        OrbitScrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        OrbitScrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        
        Orbit orbit = APIHelper.viewOrbit(AuthSession.readCurrentOID(), AuthSession.readID());
        LabelTitle.setText(orbit.title);
        
        btnAddBox.setOnAction(e-> {
            Main.mainClass.setScene("AcademyFlix", "OBContentUpload.fxml");
        });
        
        
        orbitBtn.setOnAction (e-> {
             Main.mainClass.setScene("AcademyFlix", "OrbitBox.fxml");
        });
        
        contentBtn.setOnAction (e-> {
             Main.mainClass.setScene("AcademyFlix", "OrbitBoxContent.fxml");
        });
        
        generalBtn.setOnAction (e-> {
             Main.mainClass.setScene("AcademyFlix", "OrbitBoxGeneral.fxml");
        });
        
        orbitDashBtn.setOnAction (e-> {
             Main.mainClass.setScene("AcademyFlix", "OrbitDashboard.fxml");
        });
    }    
    
}
