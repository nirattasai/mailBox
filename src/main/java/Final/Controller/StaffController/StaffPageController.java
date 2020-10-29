package Final.Controller.StaffController;

import Final.Controller.Account.RoomOwner;
import Final.Controller.Account.Staff;
import Final.Controller.Building.Room;
import Final.Controller.CSVControlInterface;
import Final.Controller.CSVControlInterfaceControl;
import Final.Controller.StaffController.Mailbox.MailBoxController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

public class StaffPageController {

    private ArrayList<Staff> staffs;
    private Staff currentStaff;
    private CSVControlInterface csvControlInterface = new CSVControlInterfaceControl();
    private int index;
    private ObservableList<Room> roomsList ;
    private ArrayList<Room> rooms;

    @FXML
    TableView roomTableView;
    @FXML
    Label addRoomButton,changePasswordButton,mailBoxButton,staffLabel,searchLabel;


    public void initialize()
    {

        try {
            staffs = csvControlInterface.createStaffListFromCSV();
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Cannot read staff.csv");
            alert.setContentText("Something wrong with csv file please check it.");
            alert.showAndWait();
        }

        try {
            rooms = csvControlInterface.createRoomListFromCSV();
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Cannot read staff.csv");
            alert.setContentText("Something wrong with csv file please check it.");
            alert.showAndWait();
        }
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

        roomTableView.refresh();

        roomTableView.setRowFactory( tv -> {
            TableRow<Room> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                    Room rowData = row.getItem();
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

    private void showRoom(Room rowData) throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/RoomDetail.fxml"));
        stage.setScene(new Scene(loader.load(),400,600));
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        double width = 400;
        double height = 600;
        stage.setX((screenBounds.getWidth() - width) / 2);
        stage.setY((screenBounds.getHeight() - height) / 2);
        RoomDetailController dw = loader.getController();
        dw.initialize(rowData);
        stage.showAndWait();
        refresh();
    }

    public void setCurrentStaff(Staff staff)
    {
        this.currentStaff = staff;
        staffLabel.setText(currentStaff.getUsername());
    }

    public void setStaffs(String username,int index)
    {
        for(Staff staff : staffs)
        {
            if(staff.getUsername().equals(username))
            {
                currentStaff = staff;
                staffLabel.setText(currentStaff.getUsername());
                break;
            }
        }
        this.index = index;
    }
    @FXML public void handleAddRoomButton() throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/AddRoom.fxml"));
        stage.setScene(new Scene(loader.load(),400,600));
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        double width = 400;
        double height = 600;
        stage.setX((screenBounds.getWidth() - width) / 2);
        stage.setY((screenBounds.getHeight() - height) / 2);
        stage.setTitle("Add room");
        stage.showAndWait();
        refresh();
    }

    @FXML public void handleMailBoxButton() throws IOException {
        Stage stage = (Stage) mailBoxButton.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/MailBox.fxml"));
        stage.setScene(new Scene(loader.load(),1000,600));
        stage.setTitle("MailBox");
        MailBoxController dw = loader.getController();
        dw.setCurrentStaff(currentStaff);
        stage.show();
    }

    @FXML public void handleChangePasswordButton() throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ChangePasswordStaff.fxml"));
        stage.setScene(new Scene(loader.load(),400,600));
        ChangePasswordStaffController dw = loader.getController();
        dw.initialize(index);
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        double width = 400;
        double height = 600;
        stage.setX((screenBounds.getWidth() - width) / 2);
        stage.setY((screenBounds.getHeight() - height) / 2);
        stage.setTitle("Change password");
        stage.show();
    }

    @FXML public void handleLogoutButton() throws IOException {
        Stage stage = (Stage) addRoomButton.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Welcome.fxml"));
        stage.setScene(new Scene(loader.load(),400,600));
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        double width = 400;
        double height = 600;
        stage.setX((screenBounds.getWidth() - width) / 2);
        stage.setY((screenBounds.getHeight() - height) / 2);
        stage.setTitle("Login");
        stage.show();
    }

    @FXML public void refresh() throws IOException {
        rooms = csvControlInterface.createRoomListFromCSV();
        roomsList = FXCollections.observableList(rooms);
        roomTableView.setItems(roomsList);
        roomTableView.refresh();
    }

    @FXML public void searchLabelClick() throws IOException {
        ArrayList<RoomOwner> resident = csvControlInterface.createRoomOwnerListFromCSV();
        ArrayList<RoomOwner> match = new ArrayList<>();
        TextInputDialog textInputDialog = new TextInputDialog();
        textInputDialog.setContentText("Enter name to search : ");
        textInputDialog.setHeaderText(null);
        Optional<String> result = textInputDialog.showAndWait();

        if(result.isPresent())
        {
            String search = result.get();
            int check = 0;
            if(!search.equals("")) {
                for (RoomOwner roomOwner : resident) {
                    if (roomOwner.getName().contains(search))
                    {
                        match.add(roomOwner);
                        check = 1;
                    }
                }
                if(check == 0)
                {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText(null);
                    alert.setContentText("No resident contain keyword.");
                    alert.getDialogPane().setPrefWidth(300);
                    alert.showAndWait();
                }
                else {
                    Stage stage = new Stage();
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/SearchResident.fxml"));
                    stage.setTitle("Search Resident");
                    stage.setScene(new Scene(loader.load(), 400, 600));
                    Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
                    double width = 400;
                    double height = 600;
                    stage.setX((screenBounds.getWidth() - width) / 2);
                    stage.setY((screenBounds.getHeight() - height) / 2);
                    SearchResidentController dw = loader.getController();
                    dw.initialize(match);
                    stage.show();
                }
            }
            else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("Please enter keyword.");
                alert.getDialogPane().setPrefWidth(300);
                alert.showAndWait();
            }
        }
    }
}
