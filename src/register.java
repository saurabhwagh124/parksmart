import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
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

public class register extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    static Button submitreg = new Button("Submit");
    static TextField unameregTF = new TextField();
    static PasswordField passregTF = new PasswordField();
    static TextField nameregTF = new TextField();
    static TextField emailregTF = new TextField();
    static TextField phoneregTF = new TextField();
    static Image backimg= new Image("file:/S:/Javafx/Advance/paksmart__/lib/backbt.jpg");
    public static int allnumbsflag = 0;
    public static ImageView backImageView = new ImageView(backimg);

    public static Scene retregister() {
        backImageView.setFitHeight(50);
        backImageView.setFitWidth(50);
        AnchorPane bckpane = new AnchorPane(backImageView);



        Label unamereg = new Label("Username");
        unamereg.setFont(new Font("Times New Roman", 30));
        Label passreq = new Label("Password");
        passreq.setFont(new Font("Times New Roman",30));
        Label namereg = new Label("Name");
        namereg.setFont(new Font("Times New Roman", 30));
        Label phonereg = new Label("Phone number");
        phonereg.setFont(new Font("Times New Roman", 30));
        Label emailreg = new Label("Email");
        emailreg.setFont(new Font("Times New Roman", 30));

        unameregTF.setPrefSize(200, 40);
        unameregTF.setPromptText("Enter unique username");
        unameregTF.setFont(Font.font("Times New Roman", 20));
        
        passregTF.setPrefSize(200, 40);
        passregTF.setPromptText("Enter Password");
        passregTF.setFont(Font.font("Times New Roman", 20));
        
        nameregTF.setPrefSize(200, 40);
        nameregTF.setPromptText("Enter Name");
        nameregTF.setFont(Font.font("Times New Roman", 20));
        phoneregTF.setPrefSize(200, 40);
        phoneregTF.setPromptText("Enter Phonenumber");
        phoneregTF.setFont(Font.font("Times New Roman", 20));
        
        emailregTF.setPrefSize(200, 40);
        emailregTF.setPromptText("Enter Email");
        emailregTF.setFont(Font.font("Times New Roman", 20));

        GridPane gpanereg = new GridPane();
        gpanereg.setAlignment(Pos.CENTER);
        gpanereg.setHgap(10);
        gpanereg.setVgap(10);

        
        submitreg.setPrefSize(120, 60);


        HBox hbox1 = new HBox(30);
        hbox1.setAlignment(Pos.CENTER);
        hbox1.getChildren().add(submitreg);

        gpanereg.add(namereg, 0, 0);
        gpanereg.add(nameregTF, 1, 0);
        gpanereg.add(phonereg,0,1);
        gpanereg.add(phoneregTF, 1, 1);
        gpanereg.add(emailreg, 0, 2);
        gpanereg.add(emailregTF, 1, 2);
        gpanereg.add(unamereg, 0, 3);
        gpanereg.add(unameregTF,1,3);
        gpanereg.add(passreq, 0, 4);
        gpanereg.add(passregTF, 1, 4);
        gpanereg.add(hbox1, 0, 5);

        BorderPane bpanereg = new BorderPane();
        bpanereg.setCenter(gpanereg);
        bpanereg.setLeft(bckpane);
        
        

        Scene regsc = new Scene(bpanereg,1200,800);
        return regsc;
        
    }
    public static int chkreg(){
        String nrg = nameregTF.getText();
        String prg = phoneregTF.getText();
        String erg = emailregTF.getText();
        String unrg = unameregTF.getText();
        String psrg = passregTF.getText();
        String imageurl = "lib/Profile.jpeg";

        boolean allNumbers = prg.matches("\\d+"); // Check if prg contains all numbers

        if (allNumbers) {
            DBConnection.registerUser(unrg, nrg, prg, erg, psrg, imageurl);
        } else {    
        Alert numbAlert = new Alert(AlertType.WARNING, "Phone number should contain only numbers.");
        numbAlert.show();
        phoneregTF.clear(); 
        }   
        if (allNumbers) {
            allnumbsflag=1;
             //login.rootstage.setScene(login.loginScene);
        }
        return allnumbsflag;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        throw new UnsupportedOperationException("Unimplemented method 'start'");
    }
}
