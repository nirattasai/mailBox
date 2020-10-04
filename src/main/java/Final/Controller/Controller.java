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
import java.util.ArrayList;

public class Controller implements allFunction{

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
        System.out.println("Username : "+username);
        System.out.println("Password : "+password);

        for (int i=0;i<allFunction.usernameSend().size();i++)
        {
            if (username.equals(allFunction.usernameSend().get(i)) && password.equals(allFunction.passwordSend().get(i)))
            {
                permission = allFunction.permissionSend().get(i);
                break;
            }
            else {
                permission = "denied";
            }
        }

        System.out.println("Permission : "+permission);
        if(permission.equals("admin"))
        {
            System.out.println("Admin");
            Button b = (Button) event.getSource();                                                                   // change scene
            Stage stage = (Stage) b.getScene().getWindow();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/AdminPage.fxml"));

            stage.setScene(new Scene(loader.load(),1000,600));

            stage.show();
        }
        else if (permission.equals("staff"))
        {
            System.out.println("Staff");
            // send to staff
        }
        else if (permission.equals("roomOwner"))
        {
            System.out.println("RoomOwner");
            // send to roomOwner
        }
        else
        {
            System.out.println("Permission Denied");
        }
    }
}
