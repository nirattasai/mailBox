package Final.controller.staffController;

import Final.building.Room;
import Final.CSVControlInterface;
import Final.CSVControlInterfaceControl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class CheckRoomController {

    @FXML
    TableView roomTableView;
    @FXML
    Button backButton;

    CSVControlInterface csvControlInterface = new CSVControlInterfaceControl();
    private ObservableList<Room> roomsList ;
    ArrayList<Room> rooms;

    public void initialize() throws IOException {
        rooms = csvControlInterface.createRoomListFromCSV();
        roomsList = FXCollections.observableList(rooms);
        roomTableView.setItems(roomsList);

        TableColumn col = new TableColumn("Building");
        col.setCellValueFactory(new PropertyValueFactory<>("building"));
        col.setPrefWidth(100);
        roomTableView.getColumns().add(col);

        col = new TableColumn("Floor");
        col.setCellValueFactory(new PropertyValueFactory<>("floor"));
        col.setPrefWidth(100);
        roomTableView.getColumns().add(col);

        col = new TableColumn("roomNumber");
        col.setCellValueFactory(new PropertyValueFactory<>("roomNumber"));
        col.setPrefWidth(100);
        roomTableView.getColumns().add(col);

        col = new TableColumn("Type");
        col.setCellValueFactory(new PropertyValueFactory<>("type"));
        col.setPrefWidth(150);
        roomTableView.getColumns().add(col);

        col = new TableColumn("Status");
        col.setCellValueFactory(new PropertyValueFactory<>("status"));
        col.setPrefWidth(150);
        roomTableView.getColumns().add(col);
    }

    @FXML public void handleBackButton(ActionEvent event) throws IOException {
        Button b = (Button) event.getSource();                                                                   // change scene
        Stage stage = (Stage) b.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/StaffPage.fxml"));
        stage.setScene(new Scene(loader.load(),1000,600));
        stage.show();
    }
}
