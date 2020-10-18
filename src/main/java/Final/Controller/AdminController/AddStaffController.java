package Final.Controller.AdminController;

import Final.Controller.Account.Staff;
import Final.Controller.CSVControlInterface;
import Final.Controller.CSVControlInterfaceControl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;


public class AddStaffController {

    private File file;
    private File destDir;
    private String filename;
    private Path target;

    @FXML
    Button okButton, cancelButton,imageUploadButton;
    @FXML
    TextField nameField,surnameField,emailField,usernameField,telField;
    @FXML
    PasswordField passwordField;
    @FXML
    ImageView preImage;

    private UserControlInterface userControlInterface = new ControlInterface();
    private CSVControlInterface csvControlInterface = new CSVControlInterfaceControl();
    private int check=0;

    @FXML public void handleOKButton(ActionEvent event) throws IOException {
        ArrayList<Staff> staff = csvControlInterface.createStaffListFromCSV();
        if(nameField.getText().equals("") || surnameField.getText().equals("") || usernameField.getText().equals("") || passwordField.getText().equals("") || emailField.getText().equals("") || telField.getText().equals(""))
        {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("Cannot Register Staff");
            alert.setTitle("WARNING");
            alert.setContentText("Cannot leave blank.");
            nameField.setText("");
            surnameField.setText("");
            usernameField.setText("");
            passwordField.setText("");
            emailField.setText("");
            telField.setText("");
            alert.showAndWait();
        }
        else
        {
            for (Staff value : staff) {
                if (usernameField.getText().equals(value.getUsername())) {
                    check = 1;
                    break;
                }
            }
            if (check==1)
            {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setHeaderText("Cannot Register Staff");
                alert.setTitle("WARNING");
                alert.setContentText("Username is already used.");
                alert.showAndWait();
            }
            else {
                Staff staff1 = new Staff(nameField.getText(), surnameField.getText(), usernameField.getText(), passwordField.getText(), emailField.getText(), telField.getText(), "haven't locked in", "haven't locked in", "normal", 0);
                staff.add(staff1);
                csvControlInterface.writeStaffListToCSV(staff);
                userControlInterface.addUser("staff", usernameField.getText(), passwordField.getText(), nameField.getText(), surnameField.getText());

                Button b = (Button) event.getSource();                                                                   // change scene
                Stage stage = (Stage) b.getScene().getWindow();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/AdminPage.fxml"));
                stage.setScene(new Scene(loader.load(), 1000, 600));
                stage.show();
            }
        }
    }

    @FXML public void handleCancelButton(ActionEvent event) throws IOException {

        file = new File("images/staff/"+filename);
        file.delete();

        Button b = (Button) event.getSource();                                                                   // change scene
        Stage stage = (Stage) b.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/AdminPage.fxml"));
        stage.setScene(new Scene(loader.load(),1000,600));
        stage.show();
    }

    @FXML public void handleImageUploadButton() throws IOException {
        if(nameField.getText().equals("") || surnameField.getText().equals("") || usernameField.getText().equals("") || passwordField.getText().equals("") || emailField.getText().equals("") || telField.getText().equals(""))
        {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("Cannot Register Staff");
            alert.setTitle("WARNING");
            alert.setContentText("Filled blanks before upload image.");
            nameField.setText("");
            surnameField.setText("");
            usernameField.setText("");
            passwordField.setText("");
            emailField.setText("");
            telField.setText("");
            alert.showAndWait();
        }
        else {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setInitialDirectory(new File(System.getProperty("user.dir")));
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("images PNG JPG", "*.png", "*.jpg"));
            file = fileChooser.showOpenDialog(imageUploadButton.getScene().getWindow());
            if (file != null) {
                destDir = new File("images" + System.getProperty("file.separator") + "staff");
                destDir.mkdirs();
                filename = usernameField.getText() + "_Profile.jpg";
                target = FileSystems.getDefault().getPath(destDir.getAbsolutePath() + System.getProperty("file.separator") + filename);
                Files.copy(file.toPath(), target, StandardCopyOption.REPLACE_EXISTING);

                preImage.setFitHeight(200);
                preImage.setFitWidth(200);
                preImage.setImage(new Image(target.toUri().toString(), 200, 200, false, false));
            }
        }
    }
}
