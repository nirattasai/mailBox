package Final.Controller.StaffController;

import Final.Controller.Account.RoomOwner;
import Final.Controller.Building.Room;
import Final.Controller.CSVControlInterface;
import Final.Controller.CSVControlInterfaceControl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.ArrayList;

public class AddResidentController {

    @FXML
    Button okButton,cancelButton;
    @FXML
    TextField nameField,surnameField,roomNumberField,telField;

    private CSVControlInterface csvControlInterface = new CSVControlInterfaceControl();
    private ArrayList<Room> rooms ;
    private ArrayList<RoomOwner> roomOwners;

    public void initialize() throws IOException {
        rooms = csvControlInterface.createRoomListFromCSV();
        roomOwners = csvControlInterface.createRoomOwnerListFromCSV();
    }

    private int check = 0,i=0;


    @FXML public void handleOKButton() throws IOException {
        check = 0;
        for(i=0;i<rooms.size();i++)
        {
            System.out.println(i);
            if(roomNumberField.getText().equals(rooms.get(i).getRoomNumberFull()))
            {
                check = 1;
                for(int j=0;j<roomOwners.size();j++)
                {
                    if (roomNumberField.getText().equals(roomOwners.get(j).getRoomNumber()))
                    {
                        check = 2;
                        break;
                    }

                }
                break;
            }

        }

        if(check==0){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Cannot add resident.");
            alert.setContentText("Don't have room number :"+roomNumberField.getText());
            alert.showAndWait();
        }
        else if(check==2)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Cannot add resident.");
            alert.setContentText("Room number : "+roomNumberField.getText()+" is already owned.");
            alert.showAndWait();
        }
        else {
            RoomOwner roomOwner = new RoomOwner(nameField.getText(),surnameField.getText(),roomNumberField.getText(),telField.getText());
            roomOwners.add(roomOwner);
            rooms.get(i).setStatus("Owned");
            csvControlInterface.writeRoomListToCSV(rooms);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("SUCCESS");
            alert.setHeaderText("Add resident success.");
            alert.setContentText("Resident name : "+nameField.getText()+"  "+surnameField.getText()+"" +
                    "\nRoom Number : "+roomNumberField.getText()+"\nTelephone Number : "+telField.getText());
            csvControlInterface.writeRoomOwnerListToCSV(roomOwners);
            alert.showAndWait();
        }
    }

    public void handleCancelButton(ActionEvent event) {
    }
}
