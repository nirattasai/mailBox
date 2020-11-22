package Final.controller;

import Final.CSVControlInterface;
import Final.CSVControlInterfaceControl;
import Final.account.Account;
import Final.account.RoomOwner;
import Final.account.Staff;
import Final.ControlInterface;
import Final.UserControlInterface;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class RegisterResidentController {
    @FXML
    TextField nameField,usernameField,passwordField,confirmField;
    @FXML
    Button ok,cancel;
    private UserControlInterface userControlInterface = new ControlInterface();
    private CSVControlInterface csvControlInterface = new CSVControlInterfaceControl();
    private ArrayList<RoomOwner> residents;
    private ArrayList<Staff> staffs = csvControlInterface.createStaffListFromCSV();

    public RegisterResidentController() throws IOException {
    }

    public void initialize() throws IOException {
        residents = csvControlInterface.createRoomOwnerListFromCSV();
    }

    @FXML public void okClick()
    {
        if(nameField.getText().equals("") || usernameField.getText().equals("") || passwordField.getText().equals("") || confirmField.getText().equals(""))
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Cannot register");
            alert.setContentText("Cannot leave blank");
            alert.showAndWait();
        }
        else if (!confirmField.getText().equals(passwordField.getText()))
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Cannot register");
            alert.setContentText("Password not same");
            alert.showAndWait();
        }
        else
        {
            int check = 0;
            String name = null,surname = null;

            for(RoomOwner resident : residents)
            {
                if(resident.getName().equals(nameField.getText()))
                {
                    name = resident.getName();
                    surname = resident.getSurname();
                    check = 1;
                    break;
                }
            }
            if(check == 0)
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Cannot register");
                alert.setContentText("Name isn't in resident database");
                nameField.clear();
                usernameField.clear();
                passwordField.clear();
                confirmField.clear();
                alert.showAndWait();
            }
            else if (check == 1)
            {
                for(Staff staff : staffs)
                {
                    if(staff.getUsername().equals(usernameField.getText()))
                    {
                        check = 2;
                        break;
                    }
                }
                if (check == 2)
                {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText("Cannot register");
                    alert.setContentText("User is already used.");
                    usernameField.clear();
                    passwordField.clear();
                    confirmField.clear();
                    alert.showAndWait();
                }
                else if (check == 1) {
                    Account account = new Account("resident",usernameField.getText(),passwordField.getText(),name,surname);
                    userControlInterface.addUser(account);
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText("Register success");
                    alert.setContentText("Username : " + usernameField.getText()
                            + "\nName : " + nameField.getText());
                    nameField.clear();
                    usernameField.clear();
                    passwordField.clear();
                    confirmField.clear();
                    alert.showAndWait();
                    Stage stage = (Stage) cancel.getScene().getWindow();
                    stage.close();
                }
            }
        }
    }

    @FXML public void cancelClick()
    {
        Stage stage = (Stage) cancel.getScene().getWindow();
        stage.close();
    }
}
