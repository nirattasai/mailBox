package Final.Controller.StaffController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class StaffPageController {

    private String username;
    private int index;

    public void setUser(String username,int index)
    {
        this.username = username;
        this.index = index;
    }

    @FXML
    Button checkRoomButton,addRoomButton,addResidentButton,mailboxButton,changePasswordButton;

    @FXML public void handleAddRoomButton(ActionEvent event) throws IOException {
        Button b = (Button) event.getSource();                                                                   // change scene
        Stage stage = (Stage) b.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/AddRoom.fxml"));
        stage.setScene(new Scene(loader.load(),1000,600));
        stage.show();
    }

    @FXML public void handleCheckRoomButton(ActionEvent event) throws IOException {
        Button b = (Button) event.getSource();                                                                   // change scene
        Stage stage = (Stage) b.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/CheckRoom.fxml"));
        stage.setScene(new Scene(loader.load(),1000,600));
        stage.show();
    }

    @FXML public void handleAddResidentButton(ActionEvent event) throws IOException {
        Button b = (Button) event.getSource();                                                                   // change scene
        Stage stage = (Stage) b.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/AddResident.fxml"));
        stage.setScene(new Scene(loader.load(),1000,600));
        stage.show();
    }

    @FXML public void handleChangePasswordButton(ActionEvent event) throws IOException {
        Button b = (Button) event.getSource();                                                                   // change scene
        Stage stage = (Stage) b.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ChangePasswordStaff.fxml"));
        stage.setScene(new Scene(loader.load(),1000,600));
        ChangePasswordStaffController dw = loader.getController();
        dw.setUser(username,index);
        stage.show();

    }

    @FXML public void handleMailBoxButton(ActionEvent event) throws IOException {
        Button b = (Button) event.getSource();                                                                   // change scene
        Stage stage = (Stage) b.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/MailBox.fxml"));
        stage.setScene(new Scene(loader.load(),1000,600));
        MailBoxController dw = loader.getController();
        dw.setUser(username,index);
        stage.show();
    }
}
