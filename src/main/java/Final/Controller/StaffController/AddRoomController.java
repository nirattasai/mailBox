package Final.Controller.StaffController;

import Final.Controller.Account.Staff;
import Final.Controller.Building.Room;
import Final.Controller.CSVControlInterface;
import Final.Controller.CSVControlInterfaceControl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class AddRoomController {
    @FXML
    Button okButton,cancelButton;
    @FXML
    ChoiceBox floorChoice,roomChoice,typeChoice;
    @FXML
    TextField buildingField;

    private final CSVControlInterface csvControlInterface = new CSVControlInterfaceControl();
    private ArrayList<Room> rooms;
    private Staff currentStaff;

    public void initialize() throws IOException {
        rooms = csvControlInterface.createRoomListFromCSV();
        for(int i=1;i<=8;i++)
        {
            floorChoice.getItems().add(String.valueOf(i));
        }
        for(int i=1;i<=10;i++)
        {
            roomChoice.getItems().add(String.valueOf(i));
        }
        typeChoice.getItems().add("Studio Room");
        typeChoice.getItems().add("1-bedroom Room");

    }

    public void setCurrentStaff(Staff currentStaff)
    {
        this.currentStaff = currentStaff;
    }

    private int check = 0;
    @FXML public void handleOKButton(ActionEvent event){
        check = 0;
        if(buildingField.getText().equals("") || floorChoice.getValue()==null || roomChoice.getValue() == null || typeChoice.getValue() == null)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Cannot add room");
            alert.setContentText("Cannot leave a blank.");
            alert.showAndWait();
        }
        else {

            for (Room room : rooms) {

                if (buildingField.getText().equals(room.getBuilding()) && floorChoice.getValue().toString().equals(room.getFloor()) && roomChoice.getValue().toString().equals(room.getRoomNumber())) {
                    check = 1;
                    break;
                }
            }

            if (check == 0) {
                int maxResident = 0;
                if(typeChoice.getValue().toString().equals("Studio Room"))
                {
                    maxResident = 1;
                }
                else if (typeChoice.getValue().toString().equals("1-bedroom Room"))
                {
                    maxResident = 2;
                }
                Room roomAdd = new Room(buildingField.getText(), floorChoice.getValue().toString(),
                        roomChoice.getValue().toString(), typeChoice.getValue().toString(),
                        "Room is not Owned","No item in mailbox",maxResident,0);
                rooms.add(roomAdd);
                Collections.sort(rooms, new Comparator<Room>() {
                    @Override
                    public int compare(Room o1, Room o2) {
                        return o1.getRoomNumberFull().compareTo(o2.getRoomNumberFull());
                    }
                });
                try {
                    csvControlInterface.writeRoomListToCSV(rooms);

                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("SUCCESS");
                    alert.setHeaderText("Add room success");
                    alert.setContentText("The room building :" + buildingField.getText() +
                            "\nFloor : " + floorChoice.getValue().toString() +
                            "\nRoomNumber : " + roomChoice.getValue().toString() +
                            "\nType : " + typeChoice.getValue().toString() +
                            "\nStatus : No Owner");
                    floorChoice.setValue(null);
                    roomChoice.setValue(null);
                    typeChoice.setValue(null);
                    buildingField.clear();
                    alert.showAndWait();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR");
                alert.setHeaderText("Cannot add room");
                alert.setContentText("The room details are duplicate.");
                alert.showAndWait();
            }
        }
    }

    @FXML public void handleCancelButton (ActionEvent event){
        Button b = (Button) event.getSource();                                                                   // change scene
        Stage stage = (Stage) b.getScene().getWindow();
        stage.close();
    }
}
