package Final;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller {

    @FXML
    PasswordField passwordField;
    @FXML
    TextField usernameField;
    @FXML
    Button loginButton;

    @FXML public void handleLoginButton(ActionEvent event) throws IOException {
        if(usernameField.getText().equals("username"))
        {
            usernameField.setText("True");
            Button b = (Button) event.getSource();
            Stage stage = (Stage) b.getScene().getWindow();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/AddStaff.fxml"));

            stage.setScene(new Scene(loader.load(),1000,600));
            stage.show();
        }

    }
}
