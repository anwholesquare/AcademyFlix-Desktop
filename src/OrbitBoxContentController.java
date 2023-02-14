/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
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
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        OrbitScrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        OrbitScrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        OrbitScrollPane.setFitToWidth(true);
        
        Orbit orbit = APIHelper.viewOrbit(AuthSession.readCurrentOID(), AuthSession.readID());
        LabelTitle.setText(orbit.title);
       
        List<Node> nodeList = new ArrayList<>();
        List<OrbitBoxStorage> ol = APIHelper.checkOrbitBoxList();
        for (OrbitBoxStorage o : ol) {
            try {
            Node n1 = FXMLLoader.load(getClass().getResource("OrbitBoxDesign.fxml"));
            
            Label created_date = (Label) n1.lookup("#LabelcreatedDate");
            Label LabelOrbitTitle = (Label) n1.lookup("#LabelTitle");
            Label LabelOrbitDesk = (Label) n1.lookup("#Labeldesc");
            Label LabelViews = (Label) n1.lookup("#LabelViews");
            ImageView thumbnail = (ImageView) n1.lookup("#thumbIMG");
            
            created_date.setText(o.created_date);
            LabelOrbitTitle.setText(o.title);  
            LabelOrbitDesk.setText(o.description);
            LabelViews.setText(o.views);
                System.out.println("design: " + o.id);
            
            thumbnail.setImage(ImageHelper.getImageFromBase64String(o.thumbnail));
            

            Button edit = (Button) n1.lookup("#BtnEdit");
            Button delete = (Button) n1.lookup("#BtnDelete");
            
            
            edit.setOnAction (e ->  { 
                //AuthSession.writeCurrentOID(o.id);
                //Main.mainClass.setScene("AcademyFlix", "OrbitBox.fxml");
            });
            
            delete.setOnAction(e-> { 
                ButtonType ybtn = new ButtonType("Yes");
                ButtonType nbtn = new ButtonType("No");
                Alert a = new Alert(Alert.AlertType.NONE, "You can't retrieve the orbit box after this action", ybtn, nbtn);
                a.setTitle("AcademyFlix Alert Box");
                a.setHeaderText("Are you really want to delete the content?");
                a.showAndWait().ifPresent(response -> {
                    if (response == ybtn) {
                        //APIHelper.deleteOrbit(o.id);
                        //Main.mainClass.setScene("AcademyFlix", "OrbitDashboard.fxml");
                    }
                });
            });

            nodeList.add(n1);
            } catch (IOException ex) {}
        }
        
        
        
        
        
        for (Node n: nodeList) {
             OrbitHolders.getChildren().add(n);
        }
        
        
        
        
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
