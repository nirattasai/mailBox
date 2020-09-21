package Final;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

public class Controller {

    @FXML
    PasswordField passwordField;
    @FXML
    TextField usernameField;
    @FXML
    Button loginButton;

//    @FXML public void initialize()
//    {
//    }

    @FXML public void handleLoginButton(ActionEvent event)
    {
        if(usernameField.getText().equals("username"))
        {
            usernameField.setText("True");
        }
    }
}
