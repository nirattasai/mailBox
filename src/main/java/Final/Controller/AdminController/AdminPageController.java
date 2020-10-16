package Final.Controller.AdminController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminPageController {

    @FXML
    Button addStaffButton,checkLogButton,changePasswordButton,logoutButton;


    @FXML public void handleAddStaffButton(ActionEvent event) throws IOException {
        Button b = (Button) event.getSource();                                                                   // change scene
        Stage stage = (Stage) b.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/AddStaff.fxml"));
        stage.setScene(new Scene(loader.load(),1000,600));
        stage.show();
    }

    @FXML public void handleCheckStaffButton(ActionEvent event) throws IOException {
        Button b = (Button) event.getSource();                                                                   // change scene
        Stage stage = (Stage) b.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/CheckStaff.fxml"));
        stage.setScene(new Scene(loader.load(),1000,600));
        stage.show();
    }

    @FXML public void handleChangePasswordButton(ActionEvent event) throws IOException {
        Button b = (Button) event.getSource();                                                                   // change scene
        Stage stage = (Stage) b.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ChangePasswordAdmin.fxml"));
        stage.setScene(new Scene(loader.load(),1000,600));
        stage.show();
    }

    @FXML public void handleLogoutButton(ActionEvent event) throws IOException {
        Button b = (Button) event.getSource();                                                                   // change scene
        Stage stage = (Stage) b.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Welcome.fxml"));
        stage.setScene(new Scene(loader.load(),1000,600));
        stage.show();
    }
}
