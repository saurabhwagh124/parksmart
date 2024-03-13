import java.io.File;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class profilepg extends Application{
    private static ImageView profileimg;
    public static String urlimg = null;
    static String ae="";
    static String be="";
    static String ce="";
    static String de="";
    static String prfurl ="file:/S:/Javafx/Advance/paksmart/lib/Profile.jpeg"; //while register pass profile url as hardcode and the use this to replace and save new profile in database;
    static Image prfimg;

    public static void main(String[] args) {
        launch(args);
    }
    static Image backimgsch= new Image("file:/S:/Javafx/Advance/paksmart__/lib/backbt.jpg");
    public static ImageView bimgv = new ImageView(backimgsch);
    public static Scene retprofile(){

        bimgv.setFitHeight(50);
        bimgv.setFitWidth(50);
        bimgv.setOnMouseClicked(e->{
            login.rootStage.setScene(searchparkspc.returnsearch());
        });
        AnchorPane bckpane = new AnchorPane(bimgv);
        
        Label usrlabel = new Label("USERNAME: ");
        usrlabel.setFont(new Font("TIMES NEW ROMAN", 23));
        Label prnamelb = new Label("NAME: ");
        prnamelb.setFont(new Font("TIMES NEW ROMAN", 23));
        Label phnolb = new Label("PHONE:");
        phnolb.setFont(new Font("TIMES NEW ROMAN", 23));
        Label emaillb = new Label("EMAIL: ");
        emaillb.setFont(new Font("TIMES NEW ROMAN", 23));
        DBConnection.getprofiledetails();

        Label prnamelb1 = new Label(be);
        prnamelb1.setFont(new Font("ARIAL", 22));
        Label phnolb1 = new Label(ce);
        phnolb1.setFont(new Font("ARIAL", 22));
        Label emaillb1 = new Label(de);
        emaillb1.setFont(new Font("ARIAL", 22));
        Label usrnmlb1 = new Label(ae);
        usrnmlb1.setFont(new Font("ARIAL", 22));

        HBox hb1 = new HBox(20);
        hb1.getChildren().addAll(prnamelb,prnamelb1);
        HBox hb2 = new HBox(20);
        hb2.getChildren().addAll(phnolb,phnolb1);
        HBox hb3 = new HBox(20);
        hb3.getChildren().addAll(emaillb,emaillb1);
        HBox hb4 = new HBox(20);
        hb4.getChildren().addAll(usrlabel,usrnmlb1);

        GridPane prgpane = new GridPane();
        prgpane.setAlignment(Pos.CENTER);
        prgpane.setHgap(10);
        prgpane.setVgap(10);
        prgpane.add(hb1, 0, 0);
        prgpane.add(hb2, 0, 1);
        prgpane.add(hb3, 0, 2);
        prgpane.add(hb4, 0, 3);
        

        Label primgname = new Label(de);
        primgname.setFont(new Font("ARIAL",20));

        Button cngprf = new Button("Change profile");
        prfimg = new Image(prfurl);
        profileimg = new ImageView(prfimg);
        profileimg.setFitHeight(250);
        profileimg.setFitWidth(250);

        Button logoutbt = new Button("LOGOUT");
        logoutbt.setPrefSize(120, 40);

        logoutbt.setOnAction(e->{
            login.rootStage.setScene(login.loginScene);
            login.passTextField.setText(null);
            login.unameTextField.setText(null);
        });

        HBox hblg = new HBox();
        hblg.getChildren().addAll(logoutbt);
        hblg.setAlignment(Pos.BOTTOM_CENTER);

        VBox vbpane = new VBox(20);
        vbpane.setAlignment(Pos.CENTER);
        vbpane.getChildren().addAll(profileimg,primgname,cngprf);
        cngprf.setOnAction(e-> changeImage());

        HBox superhbox = new HBox(70);
        superhbox.setAlignment(Pos.CENTER);
        superhbox.getChildren().addAll(vbpane,prgpane);
    

        BorderPane prbpane = new BorderPane(superhbox);
        prbpane.setLeft(bckpane);
        prbpane.setBottom(hblg);
        Scene prfscene = new Scene(prbpane, 1200, 800);
        return prfscene;
    }

    private static void changeImage() {
    FileChooser fileChooser = new FileChooser();
    fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
    File selectedFile = fileChooser.showOpenDialog(null);

    if (selectedFile != null) {
        urlimg = (selectedFile.toURI().toString());
        Image image = new Image(urlimg);
        profileimg.setImage(image);
        DBConnection.profdetails(urlimg);
        searchparkspc.prfimage = new Image(urlimg);
    }
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
       
        throw new UnsupportedOperationException("Unimplemented method 'start'");
    }

}