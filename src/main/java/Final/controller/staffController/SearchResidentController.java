package Final.controller.staffController;

import Final.account.RoomOwner;
import Final.building.Room;
import Final.CSVControlInterface;
import Final.CSVControlInterfaceControl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class SearchResidentController {
    @FXML
    TableView searchTable;
    @FXML
    Button backButton;
    @FXML public void initialize(ArrayList<RoomOwner> show)
    {
        ObservableList<RoomOwner> roomOwnerObservableList = FXCollections.observableList(show);
        searchTable.setItems(roomOwnerObservableList);

        TableColumn col = new TableColumn("ROOM NUMBER");
        col.setCellValueFactory(new PropertyValueFactory<>("RoomNumber"));
        col.setPrefWidth(100);
        col.setMinWidth(100);
        col.setMaxWidth(100);
        col.setStyle("fx-alignment: CENTER");
        searchTable.getColumns().add(col);

        col = new TableColumn("NAME");
        col.setCellValueFactory(new PropertyValueFactory<>("Name"));
        col.setPrefWidth(100);
        col.setMinWidth(100);
        col.setMaxWidth(100);
        col.setStyle("fx-alignment: CENTER");
        searchTable.getColumns().add(col);

        col = new TableColumn("SURNAME");
        col.setCellValueFactory(new PropertyValueFactory<>("Surname"));
        col.setPrefWidth(100);
        col.setMinWidth(100);
        col.setMaxWidth(100);
        col.setStyle("fx-alignment: CENTER");
        searchTable.getColumns().add(col);

        searchTable.setRowFactory( tv -> {
            TableRow<RoomOwner> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                    RoomOwner rowData = row.getItem();
                    try {
                        showRoom(rowData);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            return row;
        });
    }

    private void showRoom(RoomOwner rowData) throws IOException {
        ArrayList<Room> rooms ;
        CSVControlInterface csvControlInterface = new CSVControlInterfaceControl();
        rooms = csvControlInterface.createRoomListFromCSV();
        Room select = null;
        for(Room x : rooms)
        {
            if(x.getRoomNumberFull().equals(rowData.getRoomNumber()))
            {
                select = x;
                break;
            }
        }

        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/RoomDetail.fxml"));
        stage.setScene(new Scene(loader.load(),400,600));
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        double width = 400;
        double height = 600;
        stage.setX((screenBounds.getWidth() - width) / 2);
        stage.setY((screenBounds.getHeight() - height) / 2);
        RoomDetailController dw = loader.getController();
        dw.initialize(select);
        stage.showAndWait();
    }

    public void backClick() {
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.close();
    }
}
