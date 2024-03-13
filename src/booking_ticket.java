import javafx.application.Application;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.ScrollBar;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.control.Label;

import java.sql.SQLException;
import java.util.Stack;

public class booking_ticket extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Stage stg1 = new Stage();
        Stack<String> s = getTickets();  // Generate dummy tickets

        if (s.size() == 0) {
            Label n = new Label("No tickets Found!");
            n.setFont(Font.font("Times New Roman", 35));
            BorderPane bp = new BorderPane(n);
            Scene sc = new Scene(bp);
            stg1.setScene(sc);
        } else {
            int k = s.size();
            Label lbarr[] = new Label[k];

            for (int i = 0; i < lbarr.length; i++) {
                lbarr[i] = new Label(s.pop());
            }

            FlowPane root = new FlowPane();

            for (int i = 0; i < lbarr.length; i++) {
                GridPane gpane = createGridPane(lbarr, i);
                root.getChildren().add(gpane);
            }

            ScrollBar scb = new ScrollBar();
            scb.setOrientation(Orientation.VERTICAL);

            Scene sc1 = new Scene(new BorderPane(root), 1200, 800);
            stg1.setScene(sc1);
        }
        stg1.show();
    }

    private Stack<String> getTickets() throws SQLException {
        Stack<String> Tickets = DBConnection.getTickets();
        return Tickets;
    }

    private GridPane createGridPane(Label[] lbarr, int index) {
        GridPane gpane = new GridPane();
        gpane.setVgap(10);

        int start = index * 8;
        gpane.add(new Label("Parking_name"), 0, 0);
        gpane.add(lbarr[start], 1, 0);
        gpane.add(new Label("Parking_no."), 0, 1);
        gpane.add(lbarr[start + 1], 1, 1);
        gpane.add(new Label("Time"), 0, 2);
        gpane.add(lbarr[start + 2], 1, 2);
        gpane.add(new Label("Date"), 0, 3);
        gpane.add(lbarr[start + 3], 1, 3);
        gpane.add(new Label("Vehicle_no."), 0, 4);
        gpane.add(lbarr[start + 4], 1, 4);
        gpane.add(new Label("Vehicle_model"), 0, 5);
        gpane.add(lbarr[start + 5], 1, 5);
        gpane.add(new Label("Username"), 0, 6);
        gpane.add(lbarr[start + 6], 1, 6);
        gpane.add(new Label("Booking_ticket"), 0, 7);
        gpane.add(lbarr[start + 7], 1, 7);
        gpane.setGridLinesVisible(false);

        return gpane;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
