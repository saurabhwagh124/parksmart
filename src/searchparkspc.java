import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class searchparkspc extends Application{

    public static void main(String [] sau){
        launch(sau);
    }
    public static GridPane searchtb;
    public static String icprf= profilepg.prfurl;
    public static Image prfimage = new Image(icprf);
    static Image backimgsch= new Image("file:/S:/Javafx/Advance/paksmart__/lib/backbt.jpg");
    public static ImageView bimgvsch = new ImageView(backimgsch);
    static Scene searchsc;

    public static Scene returnsearch(){
        
       // Image prfimage = new Image(icprf);
        Label sch1 = new Label("Search parking space: ");
        sch1.setFont(new Font("TIMES NEW ROMAN", 20));

        TextField searchfield = new TextField();
        searchfield.setPrefSize(220, 40);
        searchfield.setFont(new Font("ARIAL", 18));
        searchfield.setPromptText("Enter area for parking space");

        Button searchbt = new Button("search"); 
        searchbt.setPrefSize(80, 40);        

        HBox hb1 = new HBox(20);
        hb1.setAlignment(Pos.CENTER);
        hb1.getChildren().addAll(sch1, searchfield, searchbt);

        VBox vb1 = new VBox(20);


        ImageView iconimg = new ImageView(prfimage);
        iconimg.setFitHeight(100);
        iconimg.setFitWidth(100);
        
        iconimg.setOnMouseClicked(e->{
            login.rootStage.setScene(profilepg.retprofile());
        });


        searchbt.setOnAction(e->{
            DBConnection.fetchsearch(searchfield.getText());
        });

        searchtb =  new GridPane();
        searchtb.setHgap(10);
        searchtb.setVgap(10);
        searchtb.setAlignment(Pos.CENTER);

        BorderPane bpane = new BorderPane();
        bpane.setTop(hb1);
        bpane.setCenter(searchtb);
        bpane.setRight(iconimg);

        searchsc = new Scene(bpane,1200,800);
        return searchsc;
        
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'start'");
    }

}

