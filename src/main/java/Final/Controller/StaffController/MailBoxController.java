package Final.Controller.StaffController;

import Final.Controller.Building.Room;
import Final.Controller.CSVControlInterface;
import Final.Controller.CSVControlInterfaceControl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Popup;
import javafx.stage.PopupBuilder;
import javafx.stage.Stage;

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
        col.setCellValueFactory(new PropertyValueFactory<>("roomNumberFull"));
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

    public void handleAddItemButton(ActionEvent event) throws IOException {
        Button b = (Button) event.getSource();                                                                   // change scene
        Stage stage = (Stage) b.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/AddItem.fxml"));
        stage.setScene(new Scene(loader.load(),350,600));
        AddItemController dw = loader.getController();
        dw.setUser(username,index);
        stage.show();

    }

    public void handleResidentReceivedButton(ActionEvent event) {
    }

    public void handleBackButton(ActionEvent event) {
    }
}
