package Final.Controller;

import Final.Controller.Account.Staff;
import Final.Controller.AdminController.UserControlInterface;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;

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
        }

        else if (permission.equals("staff")) {
            ArrayList<Staff> staff = StaffInterface.createStaffListFromCSV();
            for(int i=0;i<staff.size();i++)
            {
                if(staff.get(i).getUsername().equals(username))
                {
                    if(staff.get(i).checkStatus())
                    {
                        UserControlInterface.getLog(username);
                        System.out.println("Staff");
                    }
                    else
                    {
                        staff.get(i).countBlock();
                        StaffInterface.writeStaffListToCSV(staff);
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("Permission denied!");
                        alert.setHeaderText("Contact to ADMIN");
                        alert.setContentText("You were blocked by admin.");
                        passwordField.setText("");
                        usernameField.setText("");
                        alert.showAndWait();
                    }
                }
            }

        }

        else if (permission.equals("roomOwner")) {
            System.out.println("RoomOwner");
            // send to roomOwner
        }

        else {
            System.out.println("Permission Denied");
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("LOGIN FAILED!");
            alert.setHeaderText("TRY NEW!");
            alert.setContentText("Username or Password is incorrect!");
            passwordField.setText("");
            usernameField.setText("");

            alert.showAndWait();
        }
    }
}
