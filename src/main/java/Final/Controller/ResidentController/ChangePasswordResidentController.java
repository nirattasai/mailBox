package Final.Controller.ResidentController;

import Final.Controller.Account.Account;
import Final.Controller.Account.Staff;
import Final.Controller.AdminController.ControlInterface;
import Final.Controller.AdminController.UserControlInterface;
import Final.Controller.CSVControlInterface;
import Final.Controller.CSVControlInterfaceControl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class ChangePasswordResidentController {

    private Account currentAccount;
    private UserControlInterface userControlInterface = new ControlInterface();
    private ArrayList<Account> accounts;

    public void initialize(Account account) throws IOException {
        accounts = userControlInterface.createAccountFromCSV();
        this.currentAccount = account;
        System.out.println(currentAccount.getUsername());
    }

    @FXML
    PasswordField oldPassword,confirmPassword,newPassword;
    @FXML
    Button okButton,cancelButton;
    @FXML public void handleOKButton() throws IOException {
        if(oldPassword.getText().equals("") || newPassword.getText().equals("") || confirmPassword.getText().equals(""))
        {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("WARNING");
            alert.setHeaderText("Cannot change password.");
            alert.setContentText("Don't leave blank space.");
            alert.showAndWait();
        }
        else if (!currentAccount.getPassword().equals(oldPassword.getText()))
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
            for (Account account : accounts) {
                if (account.getUsername().equals(currentAccount.getUsername())) {
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
        }
    }
    @FXML public void handleCancelButton(ActionEvent event){
        Button b = (Button) event.getSource();                                                                   // change scene
        Stage stage = (Stage) b.getScene().getWindow();
        stage.close();
    }
}