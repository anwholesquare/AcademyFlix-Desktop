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

public class CreateOrbitController implements Initializable {

    @FXML
    private Button dashBtn;
    @FXML
    private Button notifBtn;
    @FXML
    private Button orbitBtn;
    @FXML
    private Button studBtn;
    @FXML
    private Button settingBtn;
    @FXML
    private Button LogoutBtn;
    @FXML
    private TextField TFtitle;
    @FXML
    private TextArea TAdesc;
    @FXML
    private TextField TFprice;
    @FXML
    private TextField TFpriceUnit;
    @FXML
    private ComboBox<String> CBvisibility;
    @FXML
    private Button btnCreateOrbit;
    @FXML
    private Button chooseImgBtn;
    @FXML
    private Label imgLabel;
    @FXML
    private ImageView LoadedImg;
    private String b64str = "";

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        CBvisibility.getItems().addAll("public", "private");
        CBvisibility.getSelectionModel().selectFirst();
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
        
        btnCreateOrbit.setOnAction(e-> {
                
               if (b64str.isEmpty()) {
                   Main.mainClass.openDialog("Select Proper File Formats", "Please use a valid image file (png/jpg) and make sure file size is less than 5 MB.");
               }
               else if (TAdesc.getText().isEmpty() 
                       ||TFtitle.getText().isEmpty()
                       ||TFprice.getText().isEmpty()
                       ||TFpriceUnit.getText().isEmpty()
                       ||CBvisibility.getValue().isEmpty()) {
                   
                   Main.mainClass.openDialog("Fill Up All The Fields", "Carefully edit the form, choose an image for the cover of the orbit and submit the form");
               }else {
                  boolean result =  APIHelper.createOrbit(TFtitle.getText(), TAdesc.getText(), TFprice.getText(), TFpriceUnit.getText(), CBvisibility.getValue(), b64str);
                  if (result) {
                       Main.mainClass.setScene("AcademyFlix", "OrbitDashboard.fxml");
                  }else {
                       Main.mainClass.openDialog("Unstable Internet Issue", "Please try again later. Something went wrong. Reasons:"
                               + "\n1. Random special characters in input,"
                               + "\n2. No internet connection available right now");
                       Main.mainClass.setScene("AcademyFlix", "OrbitDashboard.fxml");
                  }
               }
            
        });
        
        
        
        
        dashBtn.setOnAction( e -> {
            
                Main.mainClass.setScene("AcademyFlix", "Dashboard.fxml");
            
        });
        
        
        notifBtn.setOnAction( e -> {
            
                Main.mainClass.setScene("AcademyFlix", "Notifications.fxml");
            
        });
        
        
        settingBtn.setOnAction( e -> {
            
            Main.mainClass.setScene("AcademyFlix", "Settings.fxml");
            
        });
        
        
        orbitBtn.setOnAction( e -> {
            
            Main.mainClass.setScene("AcademyFlix", "OrbitDashboard.fxml");
            
        });
        
        studBtn.setOnAction( e -> {
            
            Main.mainClass.setScene("AcademyFlix", "StudentsDashboard.fxml");
            
        });
        
        LogoutBtn.setOnAction( e -> {
            
            Main.mainClass.setScene("AcademyFlix", "Authentication.fxml");
            AuthSession.logout();
            
        });
    }    
    
}
