package Final.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.*;
import java.nio.Buffer;
import java.util.ArrayList;

public class Controller {

    @FXML
    TextField usernameField;
    @FXML
    PasswordField passwordField;
    @FXML
    Button loginButton;

    private ArrayList<String> usernameSend() throws IOException {
        File file = new File("CSV/User.csv");
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line ="";
        ArrayList<String> username = new ArrayList<>();
        while((line = bufferedReader.readLine()) != null)
        {
            String[] tmp = line.split(",");
            username.add(tmp[1]);
        }
        fileReader.close();
        return username;
    }

    private ArrayList<String> passwordSend() throws IOException {
        File file = new File("CSV/User.csv");
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line ="";
        ArrayList<String> password = new ArrayList<>();
        while((line = bufferedReader.readLine()) != null)
        {
            String[] tmp = line.split(",");
            password.add(tmp[2]);
        }
        fileReader.close();
        return password;
    }

    private ArrayList<String> permissionSend() throws IOException {
        File file = new File("CSV/User.csv");
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line ="";
        ArrayList<String> permission = new ArrayList<>();
        while((line = bufferedReader.readLine()) != null)
        {
            String[] tmp = line.split(",");
            permission.add(tmp[0]);
        }
        fileReader.close();
        return permission;
    }

    @FXML public void handleLoginButton(ActionEvent event) throws IOException {
        String username = usernameField.getText();
        String password = passwordField.getText();
        String permission = "";
        System.out.println("Username : "+username);
        System.out.println("Password : "+password);

        for (int i=0;i<usernameSend().size();i++)
        {
            //System.out.println("Permission: "+permissionSend().get(i)+" User: "+usernameSend().get(i)+" Pass: "+passwordSend().get(i));
            if (username.equals(usernameSend().get(i)) && password.equals(passwordSend().get(i)))
            {
                permission = permissionSend().get(i);
                break;
            }
            else {
                permission = "denied";
            }
        }

        System.out.println("Permission : "+permission);
        if(permission.equals("admin"))
        {
            System.out.println("Admin");
            Button b = (Button) event.getSource();                                                                   // change scene
            Stage stage = (Stage) b.getScene().getWindow();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/AdminPage.fxml"));

            stage.setScene(new Scene(loader.load(),1000,600));

            stage.show();
        }
        else if (permission.equals("staff"))
        {
            System.out.println("Staff");
            // send to staff
        }
        else if (permission.equals("roomOwner"))
        {
            System.out.println("RoomOwner");
            // send to roomOwner
        }
        else
        {
            System.out.println("Permission Denied");
        }
    }
}
