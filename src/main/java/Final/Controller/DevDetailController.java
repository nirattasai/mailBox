package Final.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class DevDetailController {

    @FXML
    Button backButton,manualButton;

    public void manualClick(ActionEvent event) {
    }

    public void backClick() {
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.close();
    }
}
