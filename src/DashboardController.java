/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

/**
 * FXML Controller class
 *
 * @author anan
 */
public class DashboardController implements Initializable {

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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
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
