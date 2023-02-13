/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author anan
 */
public class OrbitBoxController implements Initializable {

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
    private Label LabelDesc;
    @FXML
    private ImageView logoIMG;
    @FXML
    private Label LabelTitle2;
    @FXML
    private Label LabelTitle21;
    @FXML
    private Label priceLabel;
    @FXML
    private Label visLabel;
    @FXML
    private Label LabelLastEdit;
    @FXML
    private Label LabelCreatedDate;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Orbit orbit = APIHelper.viewOrbit(AuthSession.readCurrentOID(), AuthSession.readID());
        LabelTitle.setText(orbit.title);
        LabelDesc.setText(orbit.description);
        priceLabel.setText(orbit.price + " " + orbit.price_unit.toUpperCase());
        visLabel.setText(orbit.visibility.toUpperCase());
        LabelCreatedDate.setText("Created Date: " + orbit.created_date);
        LabelLastEdit.setText("Last Edit Date: " + orbit.last_date);
        
        try {logoIMG.setImage(ImageHelper.getImageFromBase64String(orbit.coverpic));} 
        catch (IOException ex) {Main.mainClass.openDialog("Corrupted Image", "Stored cover image date is corrupted. Please try to upload a new image from the general section.");}
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
