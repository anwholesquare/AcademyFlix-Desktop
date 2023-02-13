/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author anan
 */
public class NotificationsController implements Initializable {

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
    private ScrollPane notifItems;
    
    @FXML
    private VBox notifItemHolder;
    @FXML
    private Button LogoutBtn;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        notifItems.setHbarPolicy(ScrollBarPolicy.NEVER);
        notifItems.setVbarPolicy(ScrollBarPolicy.NEVER);
        // TODO
        
        List<Node> nodeList = new ArrayList<>();
        
        try {
            Node n1 = FXMLLoader.load(getClass().getResource("notificationItem.fxml"));
            
            Label TFsubject = (Label) n1.lookup("#TFsubject");
            TFsubject.setText("Welcome to the Notifications");
            
            Label TFbody = (Label) n1.lookup("#TFbody");
            TFbody.setText("All the automated notifications will pop up here. No worries, notifications will be forwarded to your emails too.");
            
            Label TFTime = (Label) n1.lookup("#TFTime");
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");  
            
            TFTime.setText(formatter.format(new Date()));
            
            nodeList.add((n1));
        } catch (IOException ex) {}
        
        
       

        
        for (Node n: nodeList) {
             notifItemHolder.getChildren().add(n);
        }

        
        
        
        
        
        
        
        
        
        
        
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
