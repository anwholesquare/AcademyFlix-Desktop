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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author anan
 */
public class OrbitDashboardController implements Initializable {

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
    private ScrollPane OrbitScrollPane;
    @FXML
    private VBox OrbitHolders;
    @FXML
    private Button btnCreateOrbit;
    @FXML
    private Button LogoutBtn;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        OrbitScrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        OrbitScrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        OrbitScrollPane.setFitToWidth(true);
        // TODO
        List<Node> nodeList = new ArrayList<>();
        List<Orbit> ol = APIHelper.checkOrbitList(AuthSession.readID());
        for (Orbit o : ol) {
            try {
            Node n1 = FXMLLoader.load(getClass().getResource("OrbitItems.fxml"));
            Label LabelOrbitPrice = (Label) n1.lookup("#LabelOrbitPrice");
            Label created_date = (Label) n1.lookup("#created_date");
            Label LabelOrbitTitle = (Label) n1.lookup("#LabelOrbitTitle");
            Label LabelOrbitDesk = (Label) n1.lookup("#LabelOrbitDesk");
            LabelOrbitPrice.setText(o.price + " " + o.price_unit);
            created_date.setText(o.created_date);
            LabelOrbitTitle.setText(o.title);  
            LabelOrbitDesk.setText(o.description);

            Button view = (Button) n1.lookup("#BtnView");
            Button edit = (Button) n1.lookup("#BtnEdit");
            Button delete = (Button) n1.lookup("#BtnDelete");
            
            view.setOnAction(e-> { 
                
                
                
            });
            
            edit.setOnAction (e ->  { 
                AuthSession.writeCurrentOID(o.id);
                Main.mainClass.setScene("AcademyFlix", "OrbitBox.fxml");
            });
            
            delete.setOnAction(e-> { 
                ButtonType ybtn = new ButtonType("Yes");
                ButtonType nbtn = new ButtonType("No");
                Alert a = new Alert(AlertType.NONE, "You can't retrieve the orbit after this action", ybtn, nbtn);
                a.setTitle("AcademyFlix Alert Box");
                a.setHeaderText("Are you really want to delete the orbit?");
                a.showAndWait().ifPresent(response -> {
                    if (response == ybtn) {
                        APIHelper.deleteOrbit(o.id);
                        Main.mainClass.setScene("AcademyFlix", "OrbitDashboard.fxml");
                    }
                });
            });

            nodeList.add(n1);
            } catch (IOException ex) {}
        }
        
        
        
        
        
        for (Node n: nodeList) {
             OrbitHolders.getChildren().add(n);
        }
        
        
        btnCreateOrbit.setOnAction( e -> {
            
                Main.mainClass.setScene("AcademyFlix", "CreateOrbit.fxml");
            
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
