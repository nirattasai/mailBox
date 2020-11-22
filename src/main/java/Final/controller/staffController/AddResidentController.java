package Final.controller.staffController;

import Final.account.RoomOwner;
import Final.building.Room;
import Final.CSVControlInterface;
import Final.CSVControlInterfaceControl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class AddResidentController {

    @FXML
    Button okButton,cancelButton;
    @FXML
    TextField nameField,surnameField,roomNumberField,telField;

    private final CSVControlInterface csvControlInterface = new CSVControlInterfaceControl();
    private ArrayList<Room> rooms ;
    private ArrayList<RoomOwner> roomOwners;
    private RoomOwner residentAdd;
    private String roomNumber;

    public void initialize() throws IOException {
        rooms = csvControlInterface.createRoomListFromCSV();
        roomOwners = csvControlInterface.createRoomOwnerListFromCSV();
    }

    private int check = 0,i=0;
    public void setRoomNumber(String roomNumber)
    {
        this.roomNumber = roomNumber;
        roomNumberField.setText(roomNumber);
        roomNumberField.setEditable(false);
    }

    @FXML public void handleOKButton(ActionEvent event) throws IOException {
        check = 0;
        if(!roomNumberField.getText().equals("") && !telField.getText().equals("") && !surnameField.getText().equals("")) {
            for (Room x : rooms) {
                if (roomNumber.equals(x.getRoomNumberFull())) {
                    if (x.isFull())
                        check = 1; // room full
                    else {
                        x.addResident();
                        x.setStatus("Room is owned");
                        residentAdd = new RoomOwner(nameField.getText(), surnameField.getText(), roomNumberField.getText(),
                                telField.getText());
                        roomOwners.add(residentAdd);
                        Collections.sort(roomOwners, new Comparator<RoomOwner>() {
                            @Override
                            public int compare(RoomOwner o1, RoomOwner o2) {
                                return o1.getRoomNumber().compareTo(o2.getRoomNumber());
                            }
                        });
                    }
                }
            }
        }
        else
            check = 2;
        if(check == 1)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Room is full");
            alert.getDialogPane().setPrefWidth(200);
            alert.setHeaderText(null);
            alert.showAndWait();
            nameField.clear();
            surnameField.clear();
            telField.clear();
        }
        else if(check == 0)
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Add resident success");
            alert.getDialogPane().setPrefWidth(200);
            alert.setHeaderText(null);
            alert.showAndWait();
            nameField.clear();
            surnameField.clear();
            telField.clear();
            Button b = (Button) event.getSource();
            Stage stage = (Stage) b.getScene().getWindow();
            stage.close();
        }
        else if(check == 2)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Cannot leave blanks");
            alert.getDialogPane().setPrefWidth(200);
            alert.setHeaderText(null);
            alert.showAndWait();
        }
        csvControlInterface.writeRoomListToCSV(rooms);
        csvControlInterface.writeRoomOwnerListToCSV(roomOwners);
    }

    public void handleCancelButton(ActionEvent event){
        Button b = (Button) event.getSource();
        Stage stage = (Stage) b.getScene().getWindow();
        stage.close();
    }
}
