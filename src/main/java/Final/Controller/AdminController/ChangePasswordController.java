package Final.Controller.AdminController;

import Final.Controller.Account.Account;
import Final.Controller.Account.Staff;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;

public class ChangePasswordController {
    @FXML
    PasswordField oldPassword,confirmPassword,newPassword;
    @FXML
    Button okButton,cancelButton;
    @FXML public void handleOKButton() throws IOException {
        if (oldPassword.getText().equals(UserControlInterface.passwordSend().get(0)) && confirmPassword.getText().equals(newPassword.getText()))
        {
            String password = newPassword.getText();

            File file = new File("CSV/User.csv");
            new FileReader(file);
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = "";
            ArrayList<Account> accounts = new ArrayList<>();
            while((line = bufferedReader.readLine())!=null)
            {
                String[] accountTmp = line.split(",");
                Account accountAdd = new Staff(accountTmp[3],accountTmp[4],accountTmp[1],accountTmp[2],accountTmp[5],accountTmp[6],accountTmp[7],accountTmp[8],accountTmp[9]);
                accounts.add(accountAdd);
            }
            fileReader.close();
            bufferedReader.close();

            accounts.get(0).changePassword(password);

            File file1 = new File("CSV/User.csv");
            FileWriter fileWriter = null;
            try {
                fileWriter = new FileWriter(file1);
            } catch (IOException e) {
                e.printStackTrace();
            }
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            String line1 = "";
            for(int i=0;i<accounts.size();i++)
            {
                if (i==0) {
                    line1 = "admin" + "," + accounts.get(i).getUsername() + "," + accounts.get(i).getPassword() + "," + accounts.get(i).getName() + "," + accounts.get(i).getSurname() + "," + accounts.get(i).getEmail() + "," + accounts.get(i).getTel() + "," + accounts.get(i).getDate() + "," + accounts.get(i).getTime();
                }
                else{
                    line1 = "staff" + "," + accounts.get(i).getUsername() + "," + accounts.get(i).getPassword() + "," + accounts.get(i).getName() + "," + accounts.get(i).getSurname() + "," + accounts.get(i).getEmail() + "," + accounts.get(i).getTel() + "," + accounts.get(i).getDate() + "," + accounts.get(i).getTime();
                }
                System.out.println(line1);
                bufferedWriter.append(line1);
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
            fileWriter.close();
        }

    }
    @FXML public void handleCancelButton(ActionEvent event) throws IOException {
        Button b = (Button) event.getSource();                                                                   // change scene
        Stage stage = (Stage) b.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/AdminPage.fxml"));
        stage.setScene(new Scene(loader.load(),1000,600));
        stage.show();
    }
}
