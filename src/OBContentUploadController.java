/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

/**
 * FXML Controller class
 *
 * @author anan
 */
public class OBContentUploadController implements Initializable {

    @FXML
    private Button orbitBtn;
    @FXML
    private Button contentBtn;
    @FXML
    private Button generalBtn;
    @FXML
    private Button orbitDashBtn;
    @FXML
    private TextField TFtitle;
    @FXML
    private TextArea TAdesc;
    @FXML
    private TextField TFIndexNo;
    @FXML
    private ComboBox<String> CBvisibility;
    @FXML
    private Button btnCreateOrbitBox;
    @FXML
    private Button chooseImgBtn;
    @FXML
    private Label imgLabel;
    @FXML
    private ImageView LoadedImg;
    @FXML
    private ComboBox<String> CBvisibility1;
    private String b64str = "";
    @FXML
    private ComboBox<String> CBContentType;
    @FXML
    private TextField TFlink;
    @FXML
    private Label imgLabel1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        CBvisibility.getItems().addAll("public", "private");
        CBvisibility.getSelectionModel().selectFirst();
        
        CBvisibility1.getItems().addAll("without enrolled", "enrolled");
        CBvisibility1.getSelectionModel().selectFirst();
        
        CBContentType.getItems().addAll("Video", "Pdf");
        CBContentType.getSelectionModel().selectFirst();
        
        chooseImgBtn.setOnAction(e-> {
            final FileChooser fileChooser = new FileChooser();
            File file = fileChooser.showOpenDialog(Main.MainStage);
            if (file != null && (file.toURI().toString().endsWith(".jpg") || file.toURI().toString().endsWith(".png"))) {
                try {
                    b64str = ImageHelper.imgToB64(file);
                    LoadedImg.setImage(ImageHelper.getImageFromBase64String(b64str));
                } catch (IOException ex) {
                    Main.mainClass.openDialog("Corrupted Image", "Please use a valid image file (png/jpg) and make sure file size is less than 5 MB.");
                }
            }else {
                Main.mainClass.openDialog("Select Proper File Formats", "Please use a valid image file (png/jpg) and make sure file size is less than 5 MB.");
            }
        
        });
        
        
        btnCreateOrbitBox.setOnAction(e-> {
                
               if (b64str.isEmpty()) {
                   Main.mainClass.openDialog("Select Proper File Formats", "Please use a valid image file (png/jpg) and make sure file size is less than 5 MB.");
               }
               else if (TAdesc.getText().isEmpty() 
                       ||TFtitle.getText().isEmpty()
                       ||TFIndexNo.getText().isEmpty()
                       ||CBContentType.getValue().isEmpty()
                       ||CBvisibility.getValue().isEmpty()
                       ||CBvisibility1.getValue().isEmpty()
                       ||TFlink.getText().isEmpty()) {
                   
                   Main.mainClass.openDialog("Fill Up All The Fields", "Carefully edit the form, choose an image for the thumbnail of the orbit box and submit the form");
               }else {
                  boolean result = APIHelper.createOrbitBox(TFtitle.getText(), TAdesc.getText(), CBContentType.getValue(), TFIndexNo.getText(), TFlink.getText(), b64str, CBvisibility.getValue(), CBvisibility1.getValue());
                  if (result) {
                       Main.mainClass.setScene("AcademyFlix", "OrbitBoxContent.fxml");
                  }else {
                       Main.mainClass.openDialog("Unstable Internet Issue", "Please try again later. Something went wrong. Reasons:"
                               + "\n1. Random special characters in input,"
                               + "\n2. No internet connection available right now");
                       Main.mainClass.setScene("AcademyFlix", "OrbitBox.fxml");
                  }
               }
            
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
