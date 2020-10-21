package Final.Controller.StaffController;

import Final.Controller.Building.Room;
import Final.Controller.CSVControlInterface;
import Final.Controller.CSVControlInterfaceControl;
import Final.Controller.Item.Document;
import Final.Controller.Item.Letter;
import Final.Controller.Item.Package;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class ResidentReceiveController {
    String path;
    private String username;
    private int index;

    public void setUser(String username,int Index) {
        this.username = username;
        this.index = index;
    }

    @FXML
    Button getButton;
    @FXML
    TextField roomNumberField;

    private CSVControlInterface csvControlInterface = new CSVControlInterfaceControl();
    ArrayList<Room> rooms;
    ArrayList<Letter> letters;
    ArrayList<Package> packages;
    ArrayList<Document> documents;
    int check = 0;
    int checkRoom = 0;
    public void initialize() throws IOException {
        rooms = csvControlInterface.createRoomListFromCSV();
        letters = csvControlInterface.createLetterListFromCSV();
        packages = csvControlInterface.createPackageListFromCSV();
        documents = csvControlInterface.createDocumentListFromCSV();
    }

    @FXML public void handleGetButton(ActionEvent event) throws IOException {
        for (Room room : rooms) {
            if (room.getRoomNumberFull().equals(roomNumberField.getText())) {
                room.setItem("No item in mailBox");
                checkRoom = 1;
                break;
            }
        }
        for(int i=0;i<letters.size();i++)
        {
            if(letters.get(i).getRoomNumber().equals(roomNumberField.getText()))
            {
                path = letters.get(i).getPicture();
                File file = new File(path);
                file.delete();
                letters.remove(letters.get(i));
                i--;
                check+=1;
            }
        }
        for(int i=0;i<packages.size();i++)
        {
            if(packages.get(i).getRoomNumber().equals(roomNumberField.getText()))
            {
                path = packages.get(i).getPicture();
                File file = new File(path);
                file.delete();
                packages.remove(packages.get(i));
                i--;
                check += 1;
            }
        }
        for(int i=0;i<documents.size();i++)
        {
            if(documents.get(i).getRoomNumber().equals(roomNumberField.getText()))
            {
                path = documents.get(i).getPicture();
                File file = new File(path);
                file.delete();
                documents.remove(documents.get(i));
                i--;
                check += 1;
            }
        }
        if(checkRoom==0)
        {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Failed");
            alert.setHeaderText("Cannot get room number");
            alert.setContentText("No room number input");
            alert.showAndWait();
        }
        else if(check > 0)
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText("All item will be received");
            alert.setContentText("All item in mailBox room : "+roomNumberField.getText()+" are Received");
            alert.showAndWait();

            csvControlInterface.writePackageListToCSV(packages);
            csvControlInterface.writeLetterListToCSV(letters);
            csvControlInterface.writeDocumentListToCSV(documents);
            csvControlInterface.writeRoomListToCSV(rooms);

            roomNumberField.clear();

        }
        else if (check == 0)
        {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Failed");
            alert.setHeaderText("Cannot get item");
            alert.setContentText("No item in mailBox");
            alert.showAndWait();
        }
    }

    @FXML public void handleBackButton(ActionEvent event){
        Button b = (Button) event.getSource();                                                                   // change scene
        Stage stage = (Stage) b.getScene().getWindow();
        stage.close();
    }
}
