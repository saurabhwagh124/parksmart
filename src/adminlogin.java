import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class adminlogin extends Application {
    static Image backimg= new Image("file:/S:/Javafx/Advance/paksmart__/lib/backbt.jpg");
    public static ImageView backImageView = new ImageView(backimg);

    static Button adreg = new Button("REGISTER");
    static Button adsbt = new Button("SUBMIT");
    public static Scene retadminlg(){
        
        
        backImageView.setFitHeight(50);
        backImageView.setFitWidth(50);
        AnchorPane bckpane = new AnchorPane(backImageView);


        Label adlb = new Label("Admin name");
        adlb.setFont(new Font("SANS SERIF", 20));
        Label adpasslb = new Label("Password");
        adpasslb.setFont(new Font("SANS SERIF", 20));

        TextField adlgTF = new TextField();
        adlgTF.setPrefSize(200, 40);
        adlgTF.setPromptText("Enter your username");
        PasswordField adpassTF = new PasswordField();
        adpassTF.setPrefSize(200, 40);
        adpassTF.setPromptText("Enter your password");

        GridPane adGPane = new GridPane();
        adGPane.setHgap(10);
        adGPane.setVgap(10);
        adGPane.setAlignment(Pos.CENTER);

        HBox adsubmit = new HBox(20);
        adsbt.setPrefSize(120, 60);
        adreg.setPrefSize(120, 60);
        adsubmit.getChildren().addAll(adsbt,adreg);

        adGPane.add(adlb, 0, 0);
        adGPane.add(adlgTF, 1, 0);
        adGPane.add(adpasslb, 0, 1);
        adGPane.add(adpassTF, 1, 1);
        adGPane.add(adsubmit, 1, 2);
        GridPane.setMargin(adpassTF, new Insets(0, 0, 20, 0)); 


        BorderPane adbpane = new BorderPane();
        adbpane.setCenter(adGPane);
        adbpane.setLeft(bckpane);
        Scene adscene = new Scene(adbpane, 1200, 800);
        return adscene;



    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        throw new UnsupportedOperationException("Unimplemented method 'start'");
    }
    
}
