package Final.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class DevDetailController {

    @FXML
    Button backButton,manualButton;

    public void manualClick() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Guideline for program");
        alert.setContentText("อ่านคู่มือการใช้งานก่อนทุกครั้ง");
        alert.showAndWait();
        if (Desktop.isDesktopSupported()) {
            try {
                File myFile = new File("images/6210407455.pdf");
                Desktop.getDesktop().open(myFile);
            } catch (IOException ex) {
//                System.out.println("Kell");
            }
        }
    }

    public void backClick() {
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.close();
    }
}
