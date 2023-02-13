/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */

import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author anan
 */
public class AuthenticationController implements Initializable {

    @FXML
    private Button signInBtn;
    @FXML
    private TextField TFuser;
    @FXML
    private TextField TFpass;
    @FXML
    private Button signupBtn;
    @FXML
    private TextField TFuser1;
    @FXML
    private TextField TFemail;
    @FXML
    private TextField TFpass1;

    public static boolean isValidEmailAddress(String email) {
        String regex = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+(?:\\.[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+)*@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$";  
        Pattern pattern = Pattern.compile(regex);  
        Matcher matcher = pattern.matcher(email);  
        return matcher.matches();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        
        signInBtn.setOnAction( e -> {
           if (TFuser1.getText().contains(" ") || TFuser.getText().length() < 4) {
                Main.mainClass.openDialog("Wrong Username Format", """
                 1. You can't use space character in your username
                 2. Length of username must be greater than or equal to 4""");
           }
           else {
                if ( APIHelper.signIn(TFuser.getText(), TFpass.getText()) ) {
                    Main.mainClass.setScene("AcademyFlix", "Dashboard.fxml");
                }else {
                    Main.mainClass.openDialog("Wrong Credential", """
                                                                   Reasons for decling your signin requests:

                                                                   1. Wrong username/password,
                                                                   2. Unstable internet connection"""); 
                }
           }
           
            
        });
        
        signupBtn.setOnAction( e -> {
            if (!isValidEmailAddress(TFemail.getText())) {
                Main.mainClass.openDialog("Wrong Email Format", """
                 Please add a valid email address that you have access.""");
            }
            else if (TFuser1.getText().contains(" ") || TFuser.getText().length() < 4) {
                Main.mainClass.openDialog("Wrong Username Format", """
                 1. You can't use space character in your username,
                 2. Length of username must be greater than or equal to 4""");

            }
            else {
                if (APIHelper.createExperts(TFuser1.getText(), TFpass1.getText(), TFemail.getText())) {
                Main.mainClass.setScene("AcademyFlix", "Dashboard.fxml");
                }else {
                    Main.mainClass.openDialog("Signup Request Declined", """
                                                                  Reasons for decling your signup requests:

                                                                  1. Duplicate username/email,
                                                                  2. Unstable internet connection""");
                }
            }
            
        });
    }    
    
}
