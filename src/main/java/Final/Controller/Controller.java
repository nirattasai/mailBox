package Final.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.*;

public class Controller implements UserControlInterface {

    @FXML
    TextField usernameField;
    @FXML
    PasswordField passwordField;
    @FXML
    Button loginButton;


    @FXML public void handleLoginButton(ActionEvent event) throws IOException {
        String username = usernameField.getText();
        String password = passwordField.getText();
        String permission = "";

        permission = UserControlInterface.checkLogin(username,password);

        FXMLLoader loader = null;
        if (permission.equals("admin")) {
            System.out.println("Admin");
            Button b = (Button) event.getSource();                                                                   // change scene
            Stage stage = (Stage) b.getScene().getWindow();
            loader = new FXMLLoader(getClass().getResource("/AdminPage.fxml"));
            stage.setScene(new Scene(loader.load(), 1000, 600));
            stage.show();
        } else if (permission.equals("staff")) {
            UserControlInterface.getLog(username);
            System.out.println("Staff");
        } else if (permission.equals("roomOwner")) {
            System.out.println("RoomOwner");
            // send to roomOwner
        } else {
            System.out.println("Permission Denied");
        }
    }
}
