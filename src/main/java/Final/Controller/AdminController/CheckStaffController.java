package Final.Controller.AdminController;

import Final.Controller.Account.Staff;
import Final.Controller.StaffInterface;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;


public class CheckStaffController {

    @FXML
    TableView<String> checkLogTableView;
    @FXML
    TextField blockField,unblockField,checkStatusField;
    @FXML
    Button blockButton,unblockButton,checkButton,backButton;

    @FXML public void handleBlockButton() throws IOException {
        int stage=0;
        ArrayList<Staff> staff = StaffInterface.createStaffListFromCSV();
        for(int i=0; i<staff.size(); i++)
        {
            if (blockField.getText().equals(staff.get(i).getUsername()))
            {
                if(staff.get(i).getStatus().equals("1"))
                {
                    staff.get(i).setStatus("0");
                    stage = 1;
                    break;
                }
                else
                {
                    stage=2;
                }
            }
        }
        if(stage == 1){
            StaffInterface.writeStaffListToCSV(staff);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Blocked Staff Success");
            alert.setHeaderText("You blocked Staff");
            alert.setContentText("You blocked Staff Username: "+blockField.getText());
            alert.showAndWait();
        }
        else if (stage == 0){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Blocked Staff Failed");
            alert.setHeaderText("Username not found!");
            alert.setContentText("No username : "+blockField.getText()+" in Staff list.");
            alert.showAndWait();
        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Blocked Staff Failed");
            alert.setHeaderText("Staff is already blocked");
            alert.setContentText("User: "+blockField.getText()+" is already blocked.");
            alert.showAndWait();
        }
        blockField.setText("");
    }

    @FXML public void handleUnblockButton() throws IOException {
        ArrayList<Staff> staff = StaffInterface.createStaffListFromCSV();
        int stage = 0;
        for(int i=0; i<staff.size(); i++)
        {
            if (unblockField.getText().equals(staff.get(i).getUsername()))
            {
                if(staff.get(i).getStatus().equals("0")) {
                    staff.get(i).setStatus("1");
                    staff.get(i).setTryBlockLogin(0);
                    stage = 1;
                }
                else
                {
                    stage=2;
                }
            }
        }
        if(stage == 1){
            StaffInterface.writeStaffListToCSV(staff);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Unblocked Staff Success");
            alert.setHeaderText("You unblocked Staff");
            alert.setContentText("You unblocked Staff Username: "+unblockField.getText());
            alert.showAndWait();
        }
        else if(stage == 0){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Unblocked Staff Failed");
            alert.setHeaderText("Username not found!");
            alert.setContentText("No username : "+unblockField.getText()+" in Staff list.");
            alert.showAndWait();
        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Unblocked Staff Failed");
            alert.setHeaderText("Staff is already unblocked");
            alert.setContentText("User: "+unblockField.getText()+" is already unblocked.");
            alert.showAndWait();
        }
        unblockField.setText("");
    }

    @FXML public void handleCheckButton() throws IOException {
        ArrayList<Staff> staff = StaffInterface.createStaffListFromCSV();
        String status;
        int stage=0;
        for(int i=0; i<staff.size(); i++)
        {
            if (checkStatusField.getText().equals(staff.get(i).getUsername()))
            {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Status Staff");
                if(staff.get(i).checkStatus())
                {
                    status = "Normal";
                    alert.setHeaderText("Staff Status : "+ status);
                    alert.setContentText("Staff "+staff.get(i).getUsername()+" is : "+status);
                }
                else
                {
                    status = "Blocked";
                    alert.setHeaderText("Staff Status : "+ status);
                    alert.setContentText("Staff "+staff.get(i).getUsername()+" is : "+status+"\n"+"Try to login: "+staff.get(i).getTryBlockLogin());
                }
                alert.showAndWait();
                stage = 1;
                break;
            }
        }
        if(stage != 1){
            StaffInterface.writeStaffListToCSV(staff);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Not found Staff");
            alert.setHeaderText("Not Found staff!!");
            alert.setContentText("Not found : "+checkStatusField.getText()+" !!");
            alert.showAndWait();
        }
        checkStatusField.setText("");
    }

    @FXML public void handleBackButton(ActionEvent event) throws IOException {
        Button b = (Button) event.getSource();                                                                   // change scene
        Stage stage = (Stage) b.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/AdminPage.fxml"));
        stage.setScene(new Scene(loader.load(),1000,600));
        stage.show();
    }
}
