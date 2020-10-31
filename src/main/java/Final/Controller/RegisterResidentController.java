package Final.Controller;

import Final.Controller.Account.RoomOwner;
import Final.Controller.Account.Staff;
import Final.Controller.AdminController.ControlInterface;
import Final.Controller.AdminController.UserControlInterface;
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
                    userControlInterface.addUser("resident", usernameField.getText(), passwordField.getText(), name, surname);
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText("Register success");
                    alert.setContentText("Username : " + usernameField.getText()
                            + "\nName : " + nameField.getText());
                    alert.showAndWait();
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
