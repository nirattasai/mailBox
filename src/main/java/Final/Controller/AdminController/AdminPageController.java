package Final.Controller.AdminController;

import Final.Controller.Account.Staff;
import Final.Controller.CSVControlInterface;
import Final.Controller.CSVControlInterfaceControl;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.TransformationList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class AdminPageController {

    @FXML
    Label addStaffButton,checkStaffButton,changePasswordButton,logoutButton;
    @FXML
    AnchorPane scene;
    @FXML
    TableView staffTableView;

    private ObservableList<Staff> staffList;
    private ArrayList<Staff> staff;
    private final CSVControlInterface CSVControlInterface = new CSVControlInterfaceControl();
    private String username="";


    public void initialize() throws IOException {
        try {
            staff = CSVControlInterface.createStaffListFromCSV();
            staffList = FXCollections.observableArrayList(staff);
            staffTableView.setItems(staffList);

            TableColumn col = new TableColumn("Username");
            col.setCellValueFactory(new PropertyValueFactory<>("username"));
            col.setPrefWidth(150);
            col.setMaxWidth(150);
            col.setMinWidth(150);
            col.setStyle("-fx-alignment: CENTER");
            staffTableView.getColumns().add(col);

            col = new TableColumn("Date");
            col.setCellValueFactory(new PropertyValueFactory<>("date"));
            col.setPrefWidth(150);
            col.setMaxWidth(150);
            col.setMinWidth(150);
            col.setStyle("-fx-alignment: CENTER");
            staffTableView.getColumns().add(col);

            col = new TableColumn("Time");
            col.setCellValueFactory(new PropertyValueFactory<>("time"));
            col.setPrefWidth(150);
            col.setMaxWidth(150);
            col.setMinWidth(150);
            col.setStyle("-fx-alignment: CENTER");
            staffTableView.getColumns().add(col);

            col = new TableColumn("Status");
            col.setCellValueFactory(new PropertyValueFactory<>("status"));
            col.setPrefWidth(150);
            col.setMaxWidth(150);
            col.setMinWidth(150);
            col.setStyle("-fx-alignment: CENTER");
            staffTableView.getColumns().add(col);

        } catch (IOException e) {
            e.printStackTrace();
        }

        staffTableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) ->
        {
            if(newValue!=null)
            {
                setUsername((Staff) newValue);
            }
        });
    }

    private void setUsername(Staff staff)
    {
        username = staff.getUsername();
    }

    @FXML public void reset() throws IOException {
        staff = CSVControlInterface.createStaffListFromCSV();
        staffList = FXCollections.observableArrayList(staff);
        staffTableView.setItems(staffList);
        staffTableView.refresh();
    }
    @FXML public void handleAddStaffButton() throws IOException {
        Stage stage = (Stage) addStaffButton.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/AddStaff.fxml"));
        stage.setScene(new Scene(loader.load(),400,600));
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        double width = 400;
        double height = 600;
        stage.setX((screenBounds.getWidth() - width) / 2);
        stage.setY((screenBounds.getHeight() - height) / 2);
        stage.setTitle("Register staff");
        stage.show();
    }

    @FXML public void handleChangePasswordButton() throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ChangePasswordAdmin.fxml"));
        stage.setScene(new Scene(loader.load(),400,600));
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        double width = 400;
        double height = 600;
        stage.setX((screenBounds.getWidth() - width) / 2);
        stage.setY((screenBounds.getHeight() - height) / 2);
        stage.setTitle("Change Password");
        stage.show();
    }

    @FXML public void handleLogoutButton() throws IOException {
        Stage stage = (Stage) logoutButton.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Welcome.fxml"));
        stage.setScene(new Scene(loader.load(),400,600));
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        double width = 400;
        double height = 600;
        stage.setX((screenBounds.getWidth() - width) / 2);
        stage.setY((screenBounds.getHeight() - height) / 2);
        stage.show();
    }

    @FXML public void handleCheckButton() throws IOException {
        if(username!="") {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/CheckStaff.fxml"));
            stage.setScene(new Scene(loader.load(), 400, 600));
            Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
            double width = 400;
            double height = 600;
            stage.setX((screenBounds.getWidth() - width) / 2);
            stage.setY((screenBounds.getHeight() - height) / 2);
            CheckStaffController dw = loader.getController();
            dw.initialize(username);
            stage.setTitle("Staff detail");
            stage.show();
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.ERROR,"Please select staff first",ButtonType.OK);
            alert.showAndWait();
        }
    }

    public void handleSortButton(ActionEvent event) {
    }
}
