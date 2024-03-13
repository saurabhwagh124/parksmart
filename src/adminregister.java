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

public class adminregister extends Application {
    static Image backimg= new Image("file:/S:/Javafx/Advance/paksmart__/lib/backbt.jpg");
    public static ImageView backImageView = new ImageView(backimg);
    
    public static void main(String[] args) {
        launch(args);
    }
    static TextField phoneregTF = new TextField();

    public static Scene retadregister() {

        backImageView.setFitHeight(50);
        backImageView.setFitWidth(50);
        AnchorPane bckpane = new AnchorPane(backImageView);


        Label unamereg = new Label("Admin name");
        unamereg.setFont(new Font("Sans Serif", 21));
        Label passreq = new Label("Password");
        passreq.setFont(new Font("Sans Serif",21));
        Label namereg = new Label("Name");
        namereg.setFont(new Font("Sans Serif", 21));
        Label phonereg = new Label("Phone number");
        phonereg.setFont(new Font("Sans Serif", 21));
        Label emailreg = new Label("Email");
        emailreg.setFont(new Font("Sans Serif", 21));

        TextField unameregTF = new TextField();
        unameregTF.setPrefSize(200, 40);
        unameregTF.setPromptText("Enter username");
        unameregTF.setFont(Font.font("Sans Serif", 20));
        PasswordField passregTF = new PasswordField();
        passregTF.setPrefSize(200, 40);
        passregTF.setPromptText("Enter Password");
        passregTF.setFont(Font.font("Sans Serif", 20));
        TextField nameregTF = new TextField();
        nameregTF.setPrefSize(200, 40);
        nameregTF.setPromptText("Enter Name");
        nameregTF.setFont(Font.font("Sans Serif", 20));
        phoneregTF.setPrefSize(200, 40);
        phoneregTF.setPromptText("Enter Phonenumber");
        phoneregTF.setFont(Font.font("Sans Serif", 20));
        TextField emailregTF = new TextField();
        emailregTF.setPrefSize(200, 40);
        emailregTF.setPromptText("Enter Email");
        emailregTF.setFont(Font.font("Sans Serif", 20));

        GridPane gpanereg = new GridPane();
        gpanereg.setAlignment(Pos.CENTER);
        gpanereg.setHgap(10);
        gpanereg.setVgap(10);

        Button submitreg = new Button("Submit");
        submitreg.setPrefSize(120, 60);
        HBox hbox1 = new HBox(21);
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

        
        submitreg.setOnAction(e->{
            String nrg = nameregTF.getText();
            String prg = phoneregTF.getText();
            String erg = emailregTF.getText();
            String unrg = unameregTF.getText();
            String psrg = passregTF.getText();

            boolean allNumbers = prg.matches("\\d+"); // Check if prg contains all numbers

            if (allNumbers) {
                DBConnection.registerAdmin(nrg, psrg, prg, erg, unrg);
            } else {    
            Alert numbAlert = new Alert(AlertType.WARNING, "Phone number should contain only numbers.");
            numbAlert.show();
            phoneregTF.clear(); 
            } 
        });
        Scene adregsc = new Scene(bpanereg,1200,800);
        return adregsc;
        
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        throw new UnsupportedOperationException("Unimplemented method 'start'");
    }
}
