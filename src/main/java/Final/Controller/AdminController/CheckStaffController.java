package Final.Controller.AdminController;

import Final.Controller.Account.Staff;
import Final.Controller.CSVControlInterface;
import Final.Controller.CSVControlInterfaceControl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;


public class CheckStaffController {

    @FXML
    Button blockButton,unblockButton,backButton;
    @FXML
    Text nameText,usernameText,logText,emailText,statusText,triedText,telText;
    @FXML
    ImageView staffImage;

    private ArrayList<Staff> staffs;
    private CSVControlInterface csvControlInterface = new CSVControlInterfaceControl();
    Staff currentStaff;

    public void initialize(Staff staff) throws IOException {
        staffs = csvControlInterface.createStaffListFromCSV();
        this.currentStaff = staff;

        nameText.setText(currentStaff.getName()+"  "+currentStaff.getSurname());
        usernameText.setText(currentStaff.getUsername());
        emailText.setText(currentStaff.getEmail());
        telText.setText(currentStaff.getTel());
        statusText.setText(currentStaff.getStatus());
        triedText.setText(currentStaff.getTryBlockLogin());
        logText.setText(currentStaff.getDate()+" "+currentStaff.getTime());
        Image image = new Image(currentStaff.getPicture());
        staffImage.setImage(image);
    }

    @FXML public void handleBlockButton() {
        for (Staff staff : staffs) {
            if (staff.getUsername().equals(currentStaff.getUsername())) {
                staff.setStatus("blocked");
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setContentText("You blocked staff username: " + staff.getUsername());
                alert.getDialogPane().setPrefWidth(300);
                statusText.setText(staff.getStatus());
                alert.showAndWait();
                break;
            }
        }
       try {
            csvControlInterface.writeStaffListToCSV(staffs);

        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Cannot read staff.csv");
            alert.setContentText("Something wrong with csv file please check it.");
            alert.showAndWait();
        }
    }

    @FXML public void handleUnblockButton()  {
        for (Staff staff : staffs) {
            if (staff.getUsername().equals(currentStaff.getUsername())) {
                staff.setStatus("normal");
                staff.setTryBlockLogin(0);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setContentText("You unblocked staff username: " + staff.getUsername());
                alert.getDialogPane().setPrefWidth(300);
                statusText.setText(staff.getStatus());
                triedText.setText(staff.getTryBlockLogin());
                alert.showAndWait();
                break;
            }
        }
        try {
            csvControlInterface.writeStaffListToCSV(staffs);
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Cannot read staff.csv");
            alert.setContentText("Something wrong with csv file please check it.");
            alert.showAndWait();
        }
    }

    @FXML public void handleBackButton(){
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.close();
    }
}
