package Final.Controller.StaffController;

import Final.Controller.Account.RoomOwner;
import Final.Controller.Building.Room;
import Final.Controller.CSVControlInterface;
import Final.Controller.CSVControlInterfaceControl;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

public class RoomDetailController {
    @FXML
    Text roomNumber,floor,building,room,type,status;
    @FXML
    Button addButton,removeButton,backButton;
    @FXML
    TableView residentTable;

    private CSVControlInterface csvControlInterface = new CSVControlInterfaceControl();
    private ArrayList<RoomOwner> residents;
    private ObservableList<RoomOwner> roomOwnerObservableList;
    private Room currentRoom;
    private RoomOwner removeResident;

    public void initialize(Room currentRoom) throws IOException {
        residents = csvControlInterface.createRoomOwnerListFromCSV();
        this.currentRoom = currentRoom;
        Platform.runLater(() ->
        {
            try {
                setRoom(this.currentRoom);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        residentTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) ->
        {
            if(newValue!=null)
            {
                removeResident = (RoomOwner) newValue;
            }
        });
    }

    public void setTable() throws IOException {
        residents = csvControlInterface.createRoomOwnerListFromCSV();
        ArrayList<RoomOwner> residentsShow = new ArrayList<>();
        residentTable.getColumns().clear();
        for(RoomOwner res : residents)
        {
            if(res.getRoomNumber().equals(currentRoom.getRoomNumberFull()))
            {
                residentsShow.add(res);
            }
        }

        roomOwnerObservableList = FXCollections.observableList(residentsShow);
        residentTable.setItems(roomOwnerObservableList);

        TableColumn col = new TableColumn("NAME");
        col.setMaxWidth(100);
        col.setMinWidth(100);
        col.setPrefWidth(100);
        col.setCellValueFactory(new PropertyValueFactory<>("Name"));
        col.setStyle("-fx-alignment: CENTER");
        residentTable.getColumns().add(col);

        col = new TableColumn("SURNAME");
        col.setMaxWidth(100);
        col.setMinWidth(100);
        col.setPrefWidth(100);
        col.setCellValueFactory(new PropertyValueFactory<>("Surname"));
        col.setStyle("-fx-alignment: CENTER");
        residentTable.getColumns().add(col);

        col = new TableColumn("TEL NO.");
        col.setMaxWidth(100);
        col.setMinWidth(100);
        col.setPrefWidth(100);
        col.setCellValueFactory(new PropertyValueFactory<>("Tel"));
        col.setStyle("-fx-alignment: CENTER");
        residentTable.getColumns().add(col);

    }

    public void setRoom(Room currentRoom) throws IOException {
        ArrayList<Room> rooms = csvControlInterface.createRoomListFromCSV();
        Room roomSet = null;
        for(Room x : rooms)
        {
            if(x.getRoomNumberFull().equals(currentRoom.getRoomNumberFull()))
            {
                roomSet = x;
                break;
            }
        }
        roomNumber.setText(roomSet.getRoomNumberFull());
        floor.setText(roomSet.getFloor());
        building.setText(roomSet.getBuilding());
        room.setText(roomSet.getRoomNumber());
        type.setText(roomSet.getType());
        status.setText(roomSet.getStatus());
        setTable();
        residentTable.setPlaceholder(new Label("No resident"));
    }

    public void handleBackButton() {
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.close();
    }

    public void handleRemoveButton() throws IOException {
        residents = csvControlInterface.createRoomOwnerListFromCSV();
        ArrayList<Room> rooms = csvControlInterface.createRoomListFromCSV();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("Are you sure to remove : "+removeResident.getName()+"  "+removeResident.getSurname());
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            for(RoomOwner x : residents)
            {
                if(x.getName().equals(removeResident.getName()) && x.getSurname().equals(removeResident.getSurname()))
                {
                    for(Room i : rooms)
                    {
                        if(i.getRoomNumberFull().equals(removeResident.getRoomNumber()))
                        {
                            i.removeResident();
                            if(i.getCurrentResident().equals("0"))
                                i.emptyRoom();
                            break;
                        }
                    }
                    residents.remove(x);
                    break;
                }
            }
            csvControlInterface.writeRoomListToCSV(rooms);
            csvControlInterface.writeRoomOwnerListToCSV(residents);
            refresh();
        }

    }

    public void handleAddButton() throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/AddResident.fxml"));
        stage.setTitle("Add resident");
        stage.setScene(new Scene(loader.load(),400,600));
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        double width = 400;
        double height = 600;
        stage.setX(((screenBounds.getWidth() - width) / 2)+100);
        stage.setY((screenBounds.getHeight() - height) / 2);
        AddResidentController dw = loader.getController();
        dw.setRoomNumber(currentRoom.getRoomNumberFull());
        stage.showAndWait();
        refresh();
    }

    @FXML public void refresh() throws IOException {
        setRoom(currentRoom);
    }
}
