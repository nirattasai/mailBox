package Final.controller.staffController;

import Final.account.Account;
import Final.account.Staff;
import Final.ControlInterface;
import Final.UserControlInterface;
import Final.CSVControlInterface;
import Final.CSVControlInterfaceControl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;

public class ChangePasswordStaffController {

    private Staff currentStaff;
    private UserControlInterface userControlInterface = new ControlInterface();
    private CSVControlInterface csvControlInterface = new CSVControlInterfaceControl();
    private ArrayList<Staff> staffs;
    private ArrayList<Account> accounts;

    public void initialize(Staff staff) throws IOException {
        staffs = csvControlInterface.createStaffListFromCSV();
        accounts = userControlInterface.createAccountFromCSV();
        this.currentStaff = staff;
    }

    @FXML
    PasswordField oldPassword,confirmPassword,newPassword;
    @FXML
    Button okButton,cancelButton;
    @FXML public void handleOKButton(ActionEvent event) throws IOException {
        if(oldPassword.getText().equals("") || newPassword.getText().equals("") || confirmPassword.getText().equals(""))
        {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("WARNING");
            alert.setHeaderText("Cannot change password.");
            alert.setContentText("Don't leave blank space.");
            alert.showAndWait();
        }
        else if (!currentStaff.getPassword().equals(oldPassword.getText()))
        {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("WARNING");
            alert.setHeaderText("Cannot change password.");
            alert.setContentText("Wrong old password.");
            alert.showAndWait();
            oldPassword.setText("");
            newPassword.setText("");
            confirmPassword.setText("");
        }
        else if (!newPassword.getText().equals(confirmPassword.getText()))
        {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("WARNING");
            alert.setHeaderText("Cannot change password.");
            alert.setContentText("New password not same.");
            alert.showAndWait();
            oldPassword.setText("");
            newPassword.setText("");
            confirmPassword.setText("");
        }
        else {
            for (Staff staff : staffs) {
                if (staff.getUsername().equals(currentStaff.getUsername())) {
                    staff.changePassword(newPassword.getText());
                    break;
                }
            }
            csvControlInterface.writeStaffListToCSV(staffs);

            for (Account account : accounts) {
                if (account.getUsername().equals(currentStaff.getUsername())) {
                    account.changePassword(newPassword.getText());
                    break;
                }
            }
            userControlInterface.writeAccountToCSV(accounts);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("SUCCESS");
            alert.setHeaderText("Change password success.");
            alert.setContentText("Password already changed.");
            oldPassword.setText("");
            newPassword.setText("");
            confirmPassword.setText("");
            alert.showAndWait();
            Button b = (Button) event.getSource();                                                                   // change scene
            Stage stage = (Stage) b.getScene().getWindow();
            stage.close();
        }
    }
    @FXML public void handleCancelButton(ActionEvent event){
        Button b = (Button) event.getSource();                                                                   // change scene
        Stage stage = (Stage) b.getScene().getWindow();
        stage.close();
    }
}
