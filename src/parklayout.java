import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class parklayout extends Application{

    public static void main(String[] args) {
        launch(args);
    }
    static int i =0;
    static int book=0;
    static String ag = "";
    static String bg = "";
    static String cg = "";
    public static String icprf2= profilepg.prfurl;
    public static Image prfimage = new Image(icprf2);
    static Image backimgsch2= new Image("file:/S:/Javafx/Advance/paksmart__/lib/backbt.jpg");
    public static ImageView bimgvsch2 = new ImageView(backimgsch2);


    static Scene scene;
    public static Scene retlayout() {
        
        bimgvsch2.setFitHeight(40);
        bimgvsch2.setFitWidth(40);

        ImageView iconimg2 = new ImageView(prfimage);
        iconimg2.setFitHeight(100);
        iconimg2.setFitWidth(100);

        GridPane carpark = createCarPark();

        GridPane bikepark = createBikePark();

        Button bookButton = new Button("Book Parking");
        bookButton.setOnAction(e -> bookParkingSpace());

        javafx.scene.layout.VBox vbox = new javafx.scene.layout.VBox(20);
        vbox.setPadding(new Insets(0,0,0,80));
        vbox.setAlignment(Pos.CENTER);
        vbox.getChildren().addAll(carpark); 

        VBox vbike = new VBox(20);
        vbike.setPadding(new Insets(0,80,0,0));
        vbike.setAlignment(Pos.CENTER_LEFT);
        vbike.getChildren().add(bikepark);

        HBox hbx = new HBox(20);
        hbx.getChildren().addAll(vbike,iconimg2);

        BorderPane bPane = new BorderPane(); 
        bPane.setRight(hbx);
        bPane.setCenter(vbox);
        bPane.setLeft(bimgvsch2);

        bimgvsch2.setOnMouseClicked(e->{
            login.rootStage.setScene(searchparkspc.returnsearch());
        });
        iconimg2.setOnMouseClicked(e->{
            login.rootStage.setScene(profilepg.retprofile());
        });

        Scene scenelayout = new Scene(bPane, 1200, 800);
        return scenelayout;
    }

    private static GridPane createCarPark() {
        GridPane grid = new GridPane();
        grid.setHgap(55);
        grid.setVgap(55);

        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 4; col++) {
                Button parkingSpace = new Button("CAR P" + (i=i+1));
                parkingSpace.setMinSize(150, 150);
                parkingSpace.setOnAction(e -> {
                    selectParkingSpace(parkingSpace.getText());
                    if (book==1) {
                        parkingSpace.setDisable(true);
                        parkingSpace.setStyle("-fx-background-color: #ff0000;");
                        enterVehicleDetails();
                    }
                    
                });
                grid.add(parkingSpace, col, row);
            }
        }

        return grid;
    }

    

    private static GridPane createBikePark(){
        GridPane gridbike = new GridPane();
        gridbike.setHgap(20);
        gridbike.setVgap(20);

       for (int row = 0; row<10; row++) {
            for(int col = 0; col<2; col++){
                Button prkspcbk = new Button("BIKE P"+(row*2+col+1));
                prkspcbk.setMinSize(150, 80);
                prkspcbk.setOnAction(e->{
                    selectParkingSpace(prkspcbk.getText());
                    if (book==1) {
                        prkspcbk.setDisable(true);
                        prkspcbk.setStyle("-fx-background-color: #ff0000;");
                        enterVehicleDetails();
                    }
                });
                gridbike.add(prkspcbk, col, row);
            }
        }
        return gridbike;
    }


    static void selectParkingSpace(String space) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Parking Space Selected");
        alert.setHeaderText(null);
        alert.setContentText("Your selected parking space: " + space);

        ButtonType bookButton = new ButtonType("Book");
        ButtonType cancelButton = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(bookButton, cancelButton);

        alert.showAndWait().ifPresent(response -> {
            if (response == bookButton) {
                System.out.println("Booking parking space: " + space);
                book=1;
                ag = space;
                
            }
        });
    }

    static void enterVehicleDetails() {
        Dialog<ButtonType> vehicleDialog = new Dialog<>();
        vehicleDialog.setTitle("Enter Vehicle Details");
        vehicleDialog.setHeaderText(null);

        ButtonType confirmButtonType = new ButtonType("Confirm", ButtonBar.ButtonData.OK_DONE);
        vehicleDialog.getDialogPane().getButtonTypes().addAll(confirmButtonType, ButtonType.CANCEL);
        Button confirmButton = (Button) vehicleDialog.getDialogPane().lookupButton(confirmButtonType);
        confirmButton.setDisable(true);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField vehicleNumber = new TextField();
        TextField vehicleModel = new TextField();

        grid.add(new Label("Vehicle Number:"), 0, 0);
        grid.add(vehicleNumber, 1, 0);
        grid.add(new Label("Vehicle Model:"), 0, 1);
        grid.add(vehicleModel, 1, 1);

        vehicleDialog.getDialogPane().setContent(grid);

        vehicleNumber.textProperty().addListener((observable, oldValue, newValue) -> {
            confirmButton.setDisable(newValue.trim().isEmpty() || vehicleModel.getText().trim().isEmpty());
        });

        vehicleModel.textProperty().addListener((observable, oldValue, newValue) -> {
            confirmButton.setDisable(newValue.trim().isEmpty() || vehicleNumber.getText().trim().isEmpty());
        });


        vehicleDialog.setResultConverter(dialogButton -> {
            if (dialogButton == confirmButtonType) {
                return confirmButtonType;
            }
            return null;
        });

        vehicleDialog.showAndWait().ifPresent(response -> {
            if (vehicleModel!=null&&vehicleNumber!=null) {
                if (response == confirmButtonType) {
                    String vm = vehicleModel.getText();
                    String vn = vehicleNumber.getText();
                    System.out.println("Vehicle Number: " + vehicleNumber.getText());
                    System.out.println("Vehicle Model: " + vehicleModel.getText());
                    System.out.println(bg);
                    String un = login.unameTextField.getText();
                    DBConnection.bookingdts(vm, vn, bg,ag,un);
                }
            }
        });
    
    }


    private static void bookParkingSpace() {
        System.out.println("Booking Parking Space...");
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'start'");
    }
}


   

