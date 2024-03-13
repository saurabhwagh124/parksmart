import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class login extends Application {

    public static String usernamesql;
    static String passwordsql;
    static Scene loginScene;
    static Stage rootStage = new Stage();
    static String username1 = null;
    static TextField unameTextField = new TextField();
    static PasswordField passTextField = new PasswordField();
    static String una = "";
    
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        rootStage.setTitle("ParkSmart");
        
        Label unameLabel = new Label("Username");
        unameLabel.setFont(Font.font("Times New Roman", 30)); 

        Label passLabel = new Label("Password");
        passLabel.setFont(Font.font("Times New Roman", 30)); 

        unameTextField.setPrefSize(200, 40);
        unameTextField.setPromptText("Enter username");
        unameTextField.setFont(Font.font("Times New Roman", 25));

        passTextField.setPrefSize(200, 40);
        passTextField.setPromptText("Enter Password");
        passTextField.setFont(Font.font("Times New Roman", 25));


        Button logButton = new Button("Login");
        logButton.setPrefSize(120, 60);
        Button RegButton = new Button("Register");
        RegButton.setPrefSize(120, 60);

        BorderPane bPane = new BorderPane();
        HBox hBox1 = new HBox(30);
        hBox1.getChildren().addAll(logButton, RegButton);

        GridPane gPane = new GridPane();
        gPane.setAlignment(Pos.CENTER);
        gPane.setHgap(10);
        gPane.setVgap(10);

        HBox headerbox = new HBox();
        Label prksmt = new Label("PARKSMART");
        prksmt.setFont(new Font("OLD ENGLISH", 35));
        headerbox.getChildren().addAll(prksmt);
        headerbox.setAlignment(Pos.CENTER);

        HBox adminlg = new HBox();
        Button admin = new Button("ADMIN LOGIN");
        admin.setPrefSize(120, 60);
        adminlg.getChildren().add(admin);


      //  gPane.add(headerbox, 0, 0);
        gPane.add(unameLabel, 0, 1);    
        gPane.add(unameTextField, 1, 1);
        gPane.add(passLabel, 0, 2);
        gPane.add(passTextField, 1, 2);
        gPane.add(hBox1, 1, 3);

        
        GridPane.setMargin(passTextField, new Insets(0, 0, 20, 0)); 

        bPane.setRight(adminlg);
        bPane.setCenter(gPane);
        bPane.setTop(headerbox);

        loginScene = new Scene(bPane, 1200, 800);
        //rootStage.setScene(profilepg.retprofile());
        rootStage.setScene(loginScene);
        rootStage.show();
        //DBConnection.getprofiledetails();
        //login buttons
        logButton.setOnAction(e->{
            if(unameTextField.getText()!=null&&passTextField.getText()!=null){
               DBConnection.checklogin(unameTextField.getText(), passTextField.getText());
                DBConnection.getprofiledetails();
                if(DBConnection.lgsuc==1){
                rootStage.setScene(searchparkspc.returnsearch());
                } 
            }
        });
        RegButton.setOnAction(e->{
            rootStage.setScene(register.retregister());
        });

        //admin buttons
        admin.setOnAction(e->{
            rootStage.setScene(adminlogin.retadminlg());
        });
        adminlogin.adreg.setOnAction(e->{
            rootStage.setScene(adminregister.retadregister());
        });
        adminregister.backImageView.setOnMouseClicked(e->{
            rootStage.setScene(adminlogin.retadminlg());
        });
        adminlogin.backImageView.setOnMouseClicked(e->{
            rootStage.setScene(loginScene);
        });

        //user buttons
    
        register.backImageView.setOnMouseClicked(e->{
            rootStage.setScene(loginScene);
        });
        register.submitreg.setOnAction(e->{
            if((register.chkreg())==1){
                rootStage.setScene(loginScene);
            }
        }); 

    }

 
}
