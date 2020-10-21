package Final.Controller.AdminController;

import Final.Controller.Account.Staff;
import Final.Controller.CSVControlInterface;
import Final.Controller.CSVControlInterfaceControl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;


public class CheckStaffController {

    @FXML
    TableView<Staff> checkLogTableView;
    @FXML
    TextField blockField,unblockField,checkStatusField;
    @FXML
    Button blockButton,unblockButton,checkButton,backButton;

    private ObservableList<Staff> staffList;
    private ArrayList<Staff> staff;
    private final CSVControlInterface CSVControlInterface = new CSVControlInterfaceControl();

    @FXML public void initialize() {
        try {
            staff = CSVControlInterface.createStaffListFromCSV();
            staffList = FXCollections.observableArrayList(staff);
            checkLogTableView.setItems(staffList);

            TableColumn col = new TableColumn("Username");
            col.setCellValueFactory(new PropertyValueFactory<>("username"));
            col.setPrefWidth(150);
            col.setMaxWidth(150);
            col.setMinWidth(150);
            checkLogTableView.getColumns().add(col);

            col = new TableColumn("Date");
            col.setCellValueFactory(new PropertyValueFactory<>("date"));
            col.setPrefWidth(150);
            col.setMaxWidth(150);
            col.setMinWidth(150);
            checkLogTableView.getColumns().add(col);

            col = new TableColumn("Time");
            col.setCellValueFactory(new PropertyValueFactory<>("time"));
            col.setPrefWidth(150);
            col.setMaxWidth(150);
            col.setMinWidth(150);
            checkLogTableView.getColumns().add(col);

            col = new TableColumn("Status");
            col.setCellValueFactory(new PropertyValueFactory<>("status"));
            col.setPrefWidth(150);
            col.setMaxWidth(150);
            col.setMinWidth(150);
            checkLogTableView.getColumns().add(col);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML public void handleBlockButton() {
        int stage=0;
        for (Staff value : staff) {
            if (blockField.getText().equals(value.getUsername())) {
                if (value.getStatus().equals("normal")) {
                    value.setStatus("blocked");
                    stage = 1;
                    break;
                } else {
                    stage = 2;
                }
            }
        }
        if(stage == 1){
            try {
                CSVControlInterface.writeStaffListToCSV(staff);
            } catch (IOException e) {
                e.printStackTrace();
            }
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Blocked Staff Success");
            alert.setHeaderText("You blocked Staff");
            alert.setContentText("You blocked Staff Username: "+blockField.getText());
            alert.showAndWait();
        }
        else if (stage == 0){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Blocked Staff Failed");
            alert.setHeaderText("Username not found!");
            alert.setContentText("No username : "+blockField.getText()+" in Staff list.");
            alert.showAndWait();
        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Blocked Staff Failed");
            alert.setHeaderText("Staff is already blocked");
            alert.setContentText("User: "+blockField.getText()+" is already blocked.");
            alert.showAndWait();
        }
        blockField.clear();
        checkLogTableView.refresh();
    }

    @FXML public void handleUnblockButton()  {
        int stage = 0;
        for (Staff value : staff) {
            if (unblockField.getText().equals(value.getUsername())) {
                if (value.getStatus().equals("blocked")) {
                    value.setStatus("normal");
                    value.setTryBlockLogin(0);
                    stage = 1;
                } else {
                    stage = 2;
                }
            }
        }
        if(stage == 1){
            try {
                CSVControlInterface.writeStaffListToCSV(staff);
            } catch (IOException e) {
                e.printStackTrace();
            }
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Unblocked Staff Success");
            alert.setHeaderText("You unblocked Staff");
            alert.setContentText("You unblocked Staff Username: "+unblockField.getText());
            alert.showAndWait();
        }
        else if(stage == 0){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Unblocked Staff Failed");
            alert.setHeaderText("Username not found!");
            alert.setContentText("No username : "+unblockField.getText()+" in Staff list.");
            alert.showAndWait();
        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Unblocked Staff Failed");
            alert.setHeaderText("Staff is already unblocked");
            alert.setContentText("User: "+unblockField.getText()+" is already unblocked.");
            alert.showAndWait();
        }
        unblockField.setText("");
        checkLogTableView.refresh();
    }

    @FXML public void handleCheckButton() {
        String status;
        int stage=0;
        for (Staff value : staff) {
            if (checkStatusField.getText().equals(value.getUsername())) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Status Staff");
                if (value.checkStatus()) {
                    status = "Normal";
                    alert.setHeaderText("Staff Status : " + status);
                    alert.setContentText("Staff " + value.getUsername() + " is : " + status);
                } else {
                    status = "Blocked";
                    alert.setHeaderText("Staff Status : " + status);
                    alert.setContentText("Staff " + value.getUsername() + " is : " + status + "\n" + "Try to login: " + value.getTryBlockLogin());
                }
                alert.showAndWait();
                stage = 1;
                break;
            }
        }
        if(stage != 1){
            try {
                CSVControlInterface.writeStaffListToCSV(staff);
            } catch (IOException e) {
                e.printStackTrace();
            }
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Not found Staff");
            alert.setHeaderText("Not Found staff!!");
            alert.setContentText("Not found : "+checkStatusField.getText()+" !!");
            alert.showAndWait();
        }
        checkStatusField.setText("");
        checkLogTableView.refresh();
    }

    @FXML public void handleBackButton(ActionEvent event) throws IOException {
        Button b = (Button) event.getSource();                                                                   // change scene
        Stage stage = (Stage) b.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/AdminPage.fxml"));
        stage.setScene(new Scene(loader.load(),1000,600));
        stage.show();
    }
}
