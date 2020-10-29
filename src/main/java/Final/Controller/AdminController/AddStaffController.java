package Final.Controller.AdminController;

import Final.Controller.Account.Staff;
import Final.Controller.CSVControlInterface;
import Final.Controller.CSVControlInterfaceControl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Screen;
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
    PasswordField passwordField,confirmPasswordField;
    @FXML
    ImageView preImage;

    private final UserControlInterface userControlInterface = new ControlInterface();
    private final CSVControlInterface csvControlInterface = new CSVControlInterfaceControl();
    private int check=0;
    ArrayList<Staff> staff;

    public void initialize()
    {
        try {
            staff = csvControlInterface.createStaffListFromCSV();
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Cannot read staff.csv");
            alert.setContentText("Something wrong with csv file please check it.");
            alert.showAndWait();
        }
    }

    @FXML public void handleOKButton(ActionEvent event) throws IOException {
        if(nameField.getText().equals("") || surnameField.getText().equals("") || usernameField.getText().equals("") || passwordField.getText().equals("") || emailField.getText().equals("") || telField.getText().equals(""))
        {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText(null);
            alert.setTitle("WARNING");
            alert.setContentText("Cannot leave blank.");
            alert.getDialogPane().setPrefWidth(300);
            nameField.setText("");
            surnameField.setText("");
            usernameField.setText("");
            passwordField.setText("");
            emailField.setText("");
            telField.setText("");
            alert.showAndWait();
        }
        else if (target==null)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText(null);
            alert.setContentText("Upload image of staff first.");
            alert.getDialogPane().setPrefWidth(300);
            alert.showAndWait();
        }
        else if (!passwordField.getText().equals(confirmPasswordField.getText()))
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText(null);
            alert.setContentText("Password not same.");
            alert.getDialogPane().setPrefWidth(300);
            alert.showAndWait();
        }

        else
        {
            check = 0;
            for (Staff value : staff) {
                if (usernameField.getText().equals(value.getUsername())) {
                    check = 1;
                    break;
                }
            }
            if (check==1)
            {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setHeaderText(null);
                alert.setTitle("WARNING");
                alert.setContentText("Username is already used.");
                alert.getDialogPane().setPrefWidth(300);
                alert.showAndWait();
            }
            else {
                Staff staff1 = new Staff(nameField.getText(), surnameField.getText(), usernameField.getText(), passwordField.getText(), emailField.getText(), telField.getText(), "-", "-", "normal", 0,target.toUri().toString());
                staff.add(staff1);
                csvControlInterface.writeStaffListToCSV(staff);
                userControlInterface.addUser("staff", usernameField.getText(), passwordField.getText(), nameField.getText(), surnameField.getText());

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("SUCCESS");
                alert.setHeaderText("Register staff success");
                alert.setContentText("Register success\n"+
                        "Staff name : "+nameField.getText()+" "+surnameField.getText()+
                        "\nStaff username : "+usernameField.getText()+
                        "\nStaff email : "+emailField.getText()+
                        "\nStaff Tel : "+telField.getText());
                alert.showAndWait();

                Button b = (Button) event.getSource();
                Stage stage = (Stage) b.getScene().getWindow();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/AdminPage.fxml"));
                stage.setScene(new Scene(loader.load(), 1000, 600));
                Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
                double width = 1000;
                double height = 600;
                stage.setX((screenBounds.getWidth() - width) / 2);
                stage.setY((screenBounds.getHeight() - height) / 2);
                stage.show();
            }
        }
    }

    @FXML public void handleCancelButton(ActionEvent event) {

        file = new File(String.valueOf(target));
        file.delete();

        Button b = (Button) event.getSource();
        Stage stage = (Stage) b.getScene().getWindow();
        stage.close();
    }

    @FXML public void handleImageUploadButton() throws IOException {
        if(nameField.getText().equals("") || surnameField.getText().equals("") || usernameField.getText().equals("") || passwordField.getText().equals("") || emailField.getText().equals("") || telField.getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText(null);
            alert.setTitle("WARNING");
            alert.setContentText("Filled blanks before upload image.");
            alert.getDialogPane().setPrefWidth(300);
            nameField.clear();
            surnameField.clear();
            usernameField.clear();
            passwordField.clear();
            emailField.clear();
            telField.clear();
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
                        target = FileSystems.getDefault().getPath(destDir/*.getAbsolutePath() */+ System.getProperty("file.separator") + filename);
                        Files.copy(file.toPath(), target, StandardCopyOption.REPLACE_EXISTING);

                        preImage.setFitHeight(100);
                        preImage.setFitWidth(100);
                        preImage.setImage(new Image(target.toUri().toString()));
                    }
                }

    }

    @FXML public void usedUsername()
    {
        for(Staff staff1 : staff)
        {
            if(staff1.getUsername().equals(usernameField.getText()))
            {
                imageUploadButton.setDisable(true);
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setHeaderText(null);
                alert.setTitle("WARNING");
                alert.setContentText("Username is already used.");
                alert.getDialogPane().setPrefWidth(300);
                alert.showAndWait();
                break;
            }
            else
            {
                imageUploadButton.setDisable(false);
            }

        }
    }
}
