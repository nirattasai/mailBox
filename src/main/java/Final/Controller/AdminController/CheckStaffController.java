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
    private final CSVControlInterface csvControlInterface = new CSVControlInterfaceControl();
    Staff currentStaff;

    public void initialize(String username) {
        try {
            staffs = csvControlInterface.createStaffListFromCSV();
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Cannot read staff.csv");
            alert.setContentText("Something wrong with csv file please check it.");
            alert.showAndWait();
        }
        for(Staff staff : staffs)
        {
            if(staff.getUsername().equals(username))
            {
                currentStaff = staff;
            }
        }
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
       for (int i=0;i<staffs.size();i++)
       {
           if(staffs.get(i).getUsername().equals(currentStaff.getUsername()))
           {
               staffs.get(i).setStatus("blocked");
               Alert alert = new Alert(Alert.AlertType.INFORMATION);
               alert.setHeaderText("You blocked staff username: "+staffs.get(i).getUsername());
               alert.setContentText("Staff : "+staffs.get(i).getUsername()+" is already blocked.");
               statusText.setText(staffs.get(i).getStatus());
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
        for (int i=0;i<staffs.size();i++)
        {
            if(staffs.get(i).getUsername().equals(currentStaff.getUsername()))
            {
                staffs.get(i).setStatus("normal");
                staffs.get(i).setTryBlockLogin(0);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("You unblocked staff username: "+staffs.get(i).getUsername());
                alert.setContentText("Staff : "+staffs.get(i).getUsername()+" is already unblocked.");
                statusText.setText(staffs.get(i).getStatus());
                triedText.setText(staffs.get(i).getTryBlockLogin());
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

    @FXML public void handleBackButton(ActionEvent event){
        Button b = (Button) event.getSource();                                                                   // change scene
        Stage stage = (Stage) b.getScene().getWindow();
        stage.close();
    }
}
