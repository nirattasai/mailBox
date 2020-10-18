package Final.Controller.StaffController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class StaffPageController {

    @FXML
    Button checkRoomButton,addRoomButton,addResidentButton,mailboxButton;

    @FXML public void handleAddRoomButton(ActionEvent event) throws IOException {
        Button b = (Button) event.getSource();                                                                   // change scene
        Stage stage = (Stage) b.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/AddRoom.fxml"));
        stage.setScene(new Scene(loader.load(),1000,600));
        stage.show();
    }

    @FXML public void handleCheckRoomButton(ActionEvent event) throws IOException {
        Button b = (Button) event.getSource();                                                                   // change scene
        Stage stage = (Stage) b.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/CheckRoom.fxml"));
        stage.setScene(new Scene(loader.load(),1000,600));
        stage.show();
    }
}
