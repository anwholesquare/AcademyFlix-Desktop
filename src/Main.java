import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {
    
    public static Stage MainStage;
    public static Main mainClass;
    
    @Override
    public void start(Stage primaryStage) throws IOException {
        MainStage = primaryStage;
        mainClass = this;
        if (!AuthSession.readID().equals("@ERROR")) {
                Main.mainClass.setScene("AcademyFlix", "Dashboard.fxml");
        }else {
            setScene("AcademyFlix", "Authentication.fxml");
        }
        
    }
    
    public void setScene (String title, String fxml) {
        
        Stage primaryStage = Main.MainStage;
        primaryStage.setResizable(false);
        FXMLLoader loader = new FXMLLoader (getClass().getResource(fxml));
        Parent root = null; 
        try {
            root = loader.load();
        } catch (IOException ex) { System.out.println(ex.toString());}
        Scene scene = new Scene(root);
        primaryStage.setTitle(title);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public static String AlertSubject = "";
    public static String AlertBody = "";
    public static Stage DialogStage;
    public void openDialog(String subject, String body) {
        AlertSubject = subject;
        AlertBody = body;
        Stage primaryStage = new Stage();
        DialogStage = primaryStage;
        FXMLLoader loader = new FXMLLoader (getClass().getResource("AlertBox.fxml"));
        Parent root = null; 
        try {
            root = loader.load();
        } catch (IOException ex) {}
        Scene scene = new Scene(root);
        primaryStage.setTitle("AcademyFlix Alert Box");
        primaryStage.setScene(scene);
        primaryStage.show();
    } 

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
