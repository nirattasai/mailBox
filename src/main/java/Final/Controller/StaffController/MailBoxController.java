package Final.Controller.StaffController;

import Final.Controller.Building.Room;
import Final.Controller.CSVControlInterface;
import Final.Controller.CSVControlInterfaceControl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.util.ArrayList;

public class MailBoxController {
    @FXML
    Button backButton,addItemButton,residentReceivedButton;
    @FXML
    TableView mailBoxTableView;

    private ObservableList<Room> roomObservableList;
    private CSVControlInterface csvControlInterface = new CSVControlInterfaceControl();
    private ArrayList<Room> rooms;

    public void initialize() throws IOException {
        rooms = csvControlInterface.createRoomListFromCSV();
        roomObservableList = FXCollections.observableList(rooms);
        mailBoxTableView.setItems(roomObservableList);

        TableColumn col = new TableColumn("Room Number");
        col.setCellValueFactory(new PropertyValueFactory<>("roomNumber"));
        col.setPrefWidth(300);
        col.setEditable(true);
        mailBoxTableView.getColumns().add(col);

        col = new TableColumn("Status");
        col.setCellValueFactory(new PropertyValueFactory<>("item"));
        col.setPrefWidth(300);
        col.setEditable(false);
        mailBoxTableView.getColumns().add(col);
    }
    private String username;
    private int index;

    public void setUser(String username,int index)
    {
        this.username = username;
        this.index = index;
    }

    public void handleAddItemButton(ActionEvent event) {
    }

    public void handleResidentReceivedButton(ActionEvent event) {
    }

    public void handleBackButton(ActionEvent event) {
    }
}
