package Final.Controller.StaffController.Mailbox;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SetReceivedController {
    @FXML
    TextField name;
    @FXML
    Button getButton;

    public static Stage stage;
    @FXML public void handleGetButton()
    {
        MailBoxController.resident = name.getText();
        MailBoxController.checkRes = 1;
        stage = (Stage) getButton.getScene().getWindow();
        stage.close();
    }
}
