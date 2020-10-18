package Final.Controller.StaffController;

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

public class AddRoomController {
    @FXML
    Button okButton,cancelButton;
    @FXML
    ChoiceBox floorChoice,roomChoice,typeChoice;
    @FXML
    TextField buildingField;

    private CSVControlInterface csvControlInterface = new CSVControlInterfaceControl();
    private ArrayList<Room> rooms;

    {
        try {
            rooms = csvControlInterface.createRoomListFromCSV();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void initialize(){
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
    private int check = 0;
    @FXML public void handleOKButton(ActionEvent event) throws IOException {
        check = 0;
        if(buildingField.getText().equals("") || floorChoice.getValue()==null || roomChoice.getValue() == null || typeChoice.getValue() == null)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Cannot add room");
            alert.setContentText("Cannot leave a blank.");
            csvControlInterface.sortRoomList();
            alert.showAndWait();
        }
        else {

            for (int i = 0; i < rooms.size(); i++) {

                if (buildingField.getText().equals(rooms.get(i).getBuilding()) && floorChoice.getValue().toString().equals(rooms.get(i).getFloor()) && roomChoice.getValue().toString().equals(rooms.get(i).getRoomNumber()))
                {
                    check = 1;
                    break;
                }
            }

            if (check == 0) {
                Room roomAdd = new Room(buildingField.getText(), floorChoice.getValue().toString(), roomChoice.getValue().toString(), typeChoice.getValue().toString(), "Not Owner");
                rooms.add(roomAdd);
                try {
                    csvControlInterface.writeRoomListToCSV(rooms);

                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("SUCCESS");
                    alert.setHeaderText("Add room success");
                    alert.setContentText("The room building :" + buildingField.getText() + "\nFloor : " + floorChoice.getValue().toString() + "\nRoomNumber : " + roomChoice.getValue().toString() + "\nType : " + typeChoice.getValue().toString() + "\nStatus : Not Owner");
                    alert.showAndWait();

                    Button b = (Button) event.getSource();                                                                   // change scene
                    Stage stage = (Stage) b.getScene().getWindow();
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/StaffPage.fxml"));
                    stage.setScene(new Scene(loader.load(), 1000, 600));
                    stage.show();
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

    @FXML public void handleCancelButton (ActionEvent event) throws IOException {
        Button b = (Button) event.getSource();                                                                   // change scene
        Stage stage = (Stage) b.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/StaffPage.fxml"));
        stage.setScene(new Scene(loader.load(), 1000, 600));
        stage.show();
    }
}
