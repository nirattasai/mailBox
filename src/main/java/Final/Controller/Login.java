package Final.Controller;

import Final.Controller.Account.Staff;
import Final.Controller.AdminController.ControlInterface;
import Final.Controller.AdminController.UserControlInterface;
import Final.Controller.ResidentController.ResidentController;
import Final.Controller.StaffController.StaffPageController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;

public class Login {

    @FXML
    TextField usernameField;
    @FXML
    PasswordField passwordField;
    @FXML
    Button loginButton,devButton,register;

    private UserControlInterface userControlInterface = new ControlInterface();
    private CSVControlInterface CSVControlInterface = new CSVControlInterfaceControl();

    @FXML public void handleLoginButton(ActionEvent event) throws IOException {
        String username = usernameField.getText();
        String password = passwordField.getText();
        String permission;

        ((ControlInterface)userControlInterface).setUsername(username);
        ((ControlInterface)userControlInterface).setPassword(password);
        permission = userControlInterface.checkLogin();

        FXMLLoader loader;
        switch (permission) {
            case "admin": {
                System.out.println("Admin");
                Button b = (Button) event.getSource();
                Stage stage = (Stage) b.getScene().getWindow();
                loader = new FXMLLoader(getClass().getResource("/AdminPage.fxml"));
                stage.setScene(new Scene(loader.load(), 1000, 600));
                Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
                double width = 1000;
                double height = 600;
                stage.setX((screenBounds.getWidth() - width) / 2);
                stage.setY((screenBounds.getHeight() - height) / 2);
                stage.setTitle("Admin Page");
                stage.show();
                break;
            }
            case "staff":
                ArrayList<Staff> staff = CSVControlInterface.createStaffListFromCSV();
                for (int i = 0; i < staff.size(); i++) {
                    if (staff.get(i).getUsername().equals(username)) {
                        if (staff.get(i).checkStatus()) {
                            ((ControlInterface)userControlInterface).setUsername(username);
                            userControlInterface.getLog();
                            Button b = (Button) event.getSource();                                                                   // change scene
                            Stage stage = (Stage) b.getScene().getWindow();
                            Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
                            double width = 1000;
                            double height = 600;
                            stage.setX((screenBounds.getWidth() - width) / 2);
                            stage.setY((screenBounds.getHeight() - height) / 2);
                            loader = new FXMLLoader(getClass().getResource("/StaffPage.fxml"));
                            stage.setScene(new Scene(loader.load(), 1000, 600));
                            StaffPageController dw = loader.getController();
                            dw.setCurrentStaff(staff.get(i));
                            stage.setTitle("Staff Page");
                            stage.show();
                        } else {
                            staff.get(i).countBlock();
                            CSVControlInterface.writeStaffListToCSV(staff);
                            Alert alert = new Alert(Alert.AlertType.WARNING);
                            alert.setTitle("Permission denied!");
                            alert.setHeaderText("Contact to ADMIN");
                            alert.setContentText("You were blocked by admin.");
                            passwordField.setText("");
                            usernameField.setText("");
                            alert.showAndWait();
                        }
                    }
                }

                break;
            case "resident": {
                System.out.println("Resident");
                Button b = (Button) event.getSource();
                Stage stage = (Stage) b.getScene().getWindow();
                loader = new FXMLLoader(getClass().getResource("/RoomerPage.fxml"));
                stage.setScene(new Scene(loader.load(), 1000, 600));
                Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
                double width = 1000;
                double height = 600;
                stage.setX((screenBounds.getWidth() - width) / 2);
                stage.setY((screenBounds.getHeight() - height) / 2);
                ResidentController dw = loader.getController();
                stage.setTitle("Resident Page");
                dw.initialize(usernameField.getText());
                stage.show();
                break;
            }
            default:
                System.out.println("Permission Denied");
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("LOGIN FAILED!");
                alert.setHeaderText("TRY NEW!");
                alert.setContentText("Username or Password is incorrect!");
                passwordField.setText("");
                usernameField.setText(usernameField.getText());
                alert.showAndWait();
                break;
        }
    }

    @FXML public void devClick() throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/DevDetail.fxml"));
        stage.setScene(new Scene(loader.load(),400,600));
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        double width = 400;
        double height = 600;
        stage.setX((screenBounds.getWidth() - width) / 2);
        stage.setY((screenBounds.getHeight() - height) / 2);
        stage.setTitle("Developer Detail");
        stage.show();
    }

    @FXML public void registerClick() throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/RegisterResident.fxml"));
        stage.setScene(new Scene(loader.load(),400,600));
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        double width = 400;
        double height = 600;
        stage.setX((screenBounds.getWidth() - width) / 2);
        stage.setY((screenBounds.getHeight() - height) / 2);
        stage.setTitle("Register Resident");
        stage.show();
    }
}
