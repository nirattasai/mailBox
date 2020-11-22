package Final.controller.staffController;

import Final.building.Room;
import Final.CSVControlInterface;
import Final.CSVControlInterfaceControl;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.IntStream;

public class AddRoomController {
    @FXML
    Button okButton,cancelButton;
    @FXML
    ChoiceBox floorChoice,roomChoice,typeChoice,buildingChoice;

    private final CSVControlInterface csvControlInterface = new CSVControlInterfaceControl();
    private ArrayList<Room> rooms;

    public void initialize() throws IOException {
        rooms = csvControlInterface.createRoomListFromCSV();
        IntStream.rangeClosed(1, 8).forEach(i -> floorChoice.getItems().add(String.valueOf(i)));
        IntStream.rangeClosed(1, 10).forEach(i -> roomChoice.getItems().add(String.valueOf(i)));
        typeChoice.getItems().add("Studio Room");
        typeChoice.getItems().add("1-bedroom Room");
        buildingChoice.getItems().addAll("A","B");
    }

    private int check = 0;
    @FXML public void handleOKButton(){
        check = 0;
        if(buildingChoice.getValue()==null || floorChoice.getValue()==null ||
                roomChoice.getValue() == null || typeChoice.getValue() == null)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText(null);
            alert.setContentText("Cannot leave a blank.");
            alert.getDialogPane().setPrefWidth(300);
            alert.showAndWait();
        }
        else {
            String roomNumber;
            if(!roomChoice.getValue().toString().equals("10"))
                roomNumber = "0"+roomChoice.getValue().toString();
            else
                roomNumber = "10";
            for (Room room : rooms)
                if (room.getRoomNumberFull().equals(buildingChoice.getValue().toString()+
                        floorChoice.getValue().toString()+roomNumber)) {
                    check = 1;
                    break;
                }

            if (check == 0) {
                int maxResident = 0;

                if(typeChoice.getValue().toString().equals("Studio Room"))
                    maxResident = 1;
                else if (typeChoice.getValue().toString().equals("1-bedroom Room"))
                    maxResident = 2;
                Room roomAdd = new Room(buildingChoice.getValue().toString(), floorChoice.getValue().toString(),
                        roomNumber, typeChoice.getValue().toString(),
                        "Room is not Owned","No item in mailbox",maxResident,0);

                rooms.add(roomAdd);

                rooms.sort(Comparator.comparing(Room::getRoomNumberFull));

                try {
                    csvControlInterface.writeRoomListToCSV(rooms);

                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("SUCCESS");
                    alert.setHeaderText("Add room success");
                    alert.setContentText("The room building :" + buildingChoice.getValue().toString() +
                            "\nFloor : " + floorChoice.getValue().toString() +
                            "\nRoomNumber : " + roomChoice.getValue().toString() +
                            "\nType : " + typeChoice.getValue().toString() +
                            "\nStatus : No Owner");
                    floorChoice.setValue(null);
                    roomChoice.setValue(null);
                    typeChoice.setValue(null);
                    buildingChoice.setValue(null);
                    alert.showAndWait();
                    Stage stage = (Stage) cancelButton.getScene().getWindow();
                    stage.close();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR");
                alert.setHeaderText(null);
                alert.setContentText("The room details are duplicate.");
                alert.getDialogPane().setPrefWidth(300);
                alert.showAndWait();
            }
        }
    }

    @FXML public void handleCancelButton (){
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
}
