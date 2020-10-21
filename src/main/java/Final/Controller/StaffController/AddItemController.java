package Final.Controller.StaffController;

import Final.Controller.Building.Room;
import Final.Controller.CSVControlInterface;
import Final.Controller.CSVControlInterfaceControl;
import Final.Controller.Item.Document;
import Final.Controller.Item.Letter;
import Final.Controller.Item.Package;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.print.Doc;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.util.ArrayList;

public class AddItemController {
    @FXML
    Button addImageButton,cancelButton,addButton;
    @FXML
    TextField roomNumberField,senderNameField,receiverNameField,trackingNumberField,deliveryServiceField;
    @FXML
    ChoiceBox typeChoice,sizeChoice,privacyChoice;
    @FXML
    ImageView itemImage;

    private File file;
    private File destDir;
    private String filename;
    private Path target = null;

    private int check=0;
    private String username;
    private int index;
    public void setUser(String username,int index)
    {
        this.index = index;
        this.username = username;
    }
    ArrayList<Letter> letters;
    ArrayList<Document> documents;
    ArrayList<Package> packages;
    ArrayList<Room> rooms;
    CSVControlInterface csvControlInterface = new CSVControlInterfaceControl();
    public void initialize() throws IOException {

        letters = csvControlInterface.createLetterListFromCSV();
        documents = csvControlInterface.createDocumentListFromCSV();
        packages = csvControlInterface.createPackageListFromCSV();
        rooms = csvControlInterface.createRoomListFromCSV();
        typeChoice.getItems().add("Letter");
        typeChoice.getItems().add("Package");
        typeChoice.getItems().add("Document");
        typeChoice.setValue("Letter");
        sizeChoice.getItems().add("XL");
        sizeChoice.getItems().add("L");
        sizeChoice.getItems().add("M");
        sizeChoice.getItems().add("S");
        privacyChoice.getItems().add("High");
        privacyChoice.getItems().add("Medium");
        privacyChoice.getItems().add("Low");
        privacyChoice.getItems().add("Super Secret");

        typeChoice.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                if (newValue.toString().equals("Package"))
            {
                trackingNumberField.setDisable(false);
                deliveryServiceField.setDisable(false);
                privacyChoice.setDisable(true);
            }
            else if (newValue.toString().equals("Document"))
            {
                trackingNumberField.setDisable(true);
                deliveryServiceField.setDisable(true);
                privacyChoice.setDisable(false);
            }
                else if (newValue.toString().equals("Letter"))
                {
                    trackingNumberField.setDisable(true);
                    deliveryServiceField.setDisable(true);
                    privacyChoice.setDisable(true);
                }
            }
        });
    }


    @FXML public void handleAddImageButton() throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File(System.getProperty("user.dir")));
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("images PNG JPG", "*.png", "*.jpg"));
        file = fileChooser.showOpenDialog(addImageButton.getScene().getWindow());
        if (file != null) {
            destDir = new File("images" + System.getProperty("file.separator") + typeChoice.getValue().toString());
            destDir.mkdirs();
            filename =  roomNumberField.getText()+"_"+typeChoice.getValue().toString()+"_"+ java.time.LocalDate.now().toString()+"_"+java.time.LocalTime.now()+".jpg";
            target = FileSystems.getDefault().getPath(destDir.getAbsolutePath() + System.getProperty("file.separator") + filename);
            Files.copy(file.toPath(), target, StandardCopyOption.REPLACE_EXISTING);

            itemImage.setImage(new Image(target.toUri().toString(), 100, 100, false, false));
        }

    }

    @FXML public void handleAddButton(ActionEvent event) throws IOException {
        check = 0;
        switch (typeChoice.getValue().toString())
        {
            case "Letter":
            {
                for(int i=0;i<rooms.size();i++)
                {
                    if(rooms.get(i).getRoomNumberFull().equals(roomNumberField.getText()))
                    {
                        rooms.get(i).setItem("Item in mailBox");
                        Letter letter = new Letter(roomNumberField.getText(),senderNameField.getText(),
                                receiverNameField.getText(),sizeChoice.getValue().toString(),
                                java.time.LocalDate.now().toString(),
                                java.time.LocalTime.now().toString(),target.toString());
                        letters.add(letter);
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("Add item Success");
                        alert.setHeaderText("Add Letter success.");
                        alert.setContentText("Add letter to room : "+roomNumberField.getText()
                        +"\nSender : "+senderNameField.getText()
                        +"\nReceiver : "+receiverNameField.getText()
                        +"\nSize : "+sizeChoice.getValue().toString());
                        alert.showAndWait();
                        check=1;
                        break;
                    }
                }
                if(check == 0)
                {
                    file = new File(String.valueOf(target));
                    file.delete();
                    itemImage.setImage(null);
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Add item failed");
                    alert.setHeaderText("Add Letter failed.");
                    alert.setContentText("No room number you input");
                    roomNumberField.setText("");
                    alert.showAndWait();
                }
                break;
            }
            case "Package":
            {
                for(int i=0;i<rooms.size();i++)
                {
                    if(rooms.get(i).getRoomNumberFull().equals(roomNumberField.getText()))
                    {
                        rooms.get(i).setItem("Item in mailBox");
                        Package aPackage = new Package(roomNumberField.getText(),senderNameField.getText(),
                                receiverNameField.getText(),sizeChoice.getValue().toString(),deliveryServiceField.getText(),trackingNumberField.getText(),java.time.LocalDate.now().toString()
                                ,java.time.LocalTime.now().toString(),target.toString());
                        packages.add(aPackage);
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("Add item Success");
                        alert.setHeaderText("Add Package success.");
                        alert.setContentText("Add Package to room : "+roomNumberField.getText()
                                +"\nSender : "+senderNameField.getText()
                                +"\nReceiver : "+receiverNameField.getText()
                                +"\nSize : "+sizeChoice.getValue().toString()
                                +"\nTracking : "+trackingNumberField.getText()
                                +"\nDelivery Service : "+deliveryServiceField.getText());
                        alert.showAndWait();
                        check=1;
                        break;
                    }
                }
                if(check == 0)
                {
                    file = new File(String.valueOf(target));
                    file.delete();
                    itemImage.setImage(null);
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Add item failed");
                    alert.setHeaderText("Add Package failed.");
                    alert.setContentText("No room number you input");
                    roomNumberField.setText("");
                    alert.showAndWait();
                }
                break;
            }
            case "Document":
            {
                for(int i=0;i<rooms.size();i++)
                {
                    if(rooms.get(i).getRoomNumberFull().equals(roomNumberField.getText()))
                    {
                        rooms.get(i).setItem("Item in mailBox");
                        Document document = new Document(roomNumberField.getText(),senderNameField.getText(),
                                receiverNameField.getText(),sizeChoice.getValue().toString(),
                                privacyChoice.getValue().toString(),java.time.LocalDate.now().toString(),
                                java.time.LocalTime.now().toString(),target.toString());
                        documents.add(document);
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("Add item Success");
                        alert.setHeaderText("Add Document success.");
                        alert.setContentText("Add Document to room : "+roomNumberField.getText()
                                +"\nSender : "+senderNameField.getText()
                                +"\nReceiver : "+receiverNameField.getText()
                                +"\nSize : "+sizeChoice.getValue().toString()
                                +"\nPrivacy : "+privacyChoice.getValue().toString());
                        alert.showAndWait();
                        check=1;
                        break;
                    }
                }
                if(check == 0)
                {
                    file = new File(String.valueOf(target));
                    file.delete();
                    itemImage.setImage(null);
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Add item failed");
                    alert.setHeaderText("Add Document failed.");
                    alert.setContentText("No room number you input");
                    roomNumberField.setText("");
                    alert.showAndWait();
                }
                break;
            }
        }

        csvControlInterface.writeRoomListToCSV(rooms);
        csvControlInterface.writeDocumentListToCSV(documents);
        csvControlInterface.writeLetterListToCSV(letters);
        csvControlInterface.writePackageListToCSV(packages);
        roomNumberField.clear();
        senderNameField.clear();
        receiverNameField.clear();
        trackingNumberField.clear();
        deliveryServiceField.clear();
        typeChoice.setValue("Letter");
        sizeChoice.setValue(null);
        privacyChoice.setValue(null);
        itemImage.setImage(null);

    }

    @FXML public void handleCancelButton(ActionEvent event) throws IOException {
        file = new File(String.valueOf(target));
        file.delete();

        Button b = (Button) event.getSource();                                                                   // change scene
        Stage stage = (Stage) b.getScene().getWindow();
        stage.close();
    }
}
