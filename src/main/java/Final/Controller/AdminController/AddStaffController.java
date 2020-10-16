package Final.Controller.AdminController;

import Final.Controller.Account.Staff;
import Final.Controller.StaffInterface;
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


public class AddStaffController {
    @FXML
    Button okButton, cancelButton,imageUploadButton;
    @FXML
    TextField nameField,surnameField,emailField,usernameField,telField;
    @FXML
    PasswordField passwordField;


    @FXML public void handleOKButton(ActionEvent event) throws IOException {
        ArrayList<Staff> staff = StaffInterface.createStaffListFromCSV();
        if(nameField.getText().equals("") || surnameField.getText().equals("") || usernameField.getText().equals("") || passwordField.getText().equals("") || emailField.getText().equals("") || telField.getText().equals(""))
        {
            System.out.println("Can't leave a blank");
        }
        else
        {
            Staff staff1 = new Staff(nameField.getText(),surnameField.getText(),usernameField.getText(),passwordField.getText(),emailField.getText(),telField.getText(),"null","null","1",0);
            staff.add(staff1);
            StaffInterface.writeStaffListToCSV(staff);
            UserControlInterface.addUser("staff",usernameField.getText(),passwordField.getText(),nameField.getText(),surnameField.getText());


            Button b = (Button) event.getSource();                                                                   // change scene
            Stage stage = (Stage) b.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/AdminPage.fxml"));
            stage.setScene(new Scene(loader.load(),1000,600));
            stage.show();
        }
    }

    @FXML public void handleCancelButton(ActionEvent event) throws IOException {
        Button b = (Button) event.getSource();                                                                   // change scene
        Stage stage = (Stage) b.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/AdminPage.fxml"));
        stage.setScene(new Scene(loader.load(),1000,600));
        stage.show();
    }

}
