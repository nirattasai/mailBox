package Final.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;



public class AddStaffController {
    @FXML
    Button okButton, cancelButton,imageUploadButton;
    @FXML
    TextField nameField,surnameField,emailField,usernameField,telField;
    @FXML
    PasswordField passwordField;

    @FXML public void handleOKButton(ActionEvent event)
    {
        if (nameField.getText().equals("NAME"))
        {
            surnameField.setText("Surname");
        }
    }


}
