package Final.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.*;


public class AddStaffController {
    @FXML
    Button okButton, cancelButton,imageUploadButton;
    @FXML
    TextField nameField,surnameField,emailField,usernameField,telField;
    @FXML
    PasswordField passwordField;


    @FXML public void handleOKButton(ActionEvent event) throws IOException {
        File file = new File("CSV/User.csv");
        FileWriter fileWriter = new FileWriter(file,true);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        if(nameField.getText().equals("") || surnameField.getText().equals("") || usernameField.getText().equals("") || passwordField.getText().equals("") || emailField.getText().equals("") || telField.getText().equals(""))
        {
            System.out.println("Can't leave a blank");
        }
        else
        {
            bufferedWriter.newLine();
            String line = "staff"+","+usernameField.getText()+","+passwordField.getText();
            bufferedWriter.write(line);
            bufferedWriter.close();
        }
    }


}
