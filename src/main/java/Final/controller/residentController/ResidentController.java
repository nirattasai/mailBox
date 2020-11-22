package Final.controller.residentController;

import Final.account.Account;
import Final.account.RoomOwner;
import Final.ControlInterface;
import Final.UserControlInterface;
import Final.CSVControlInterface;
import Final.CSVControlInterfaceControl;
import Final.item.Document;
import Final.item.Letter;
import Final.item.Package;
import Final.controller.staffController.Mailbox.HistoryItemDetail.LetterHistoryDetail;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class ResidentController {
    @FXML
    Label username,changePassword,logout;
    @FXML
    TableView notReceived,received;
    @FXML
    TableColumn sendNot,receiverNot,dateNot,sendRe,receiverRe,dateRe;

    private ArrayList<Letter> letters;
    private ArrayList<Package> packages;
    private ArrayList<Document> documents;
    private ArrayList<Letter> lettersHis;
    private ArrayList<Package> packagesHis;
    private ArrayList<Document> documentsHis;
    private ArrayList<Letter> show = new ArrayList<>();
    private ArrayList<Letter> his = new ArrayList<>();
    private CSVControlInterface csvControlInterface = new CSVControlInterfaceControl();
    private UserControlInterface userControlInterface = new ControlInterface();
    private ArrayList<Account> accounts;
    private ObservableList<Letter> showObs;
    private ObservableList<Letter> hisObs;
    private RoomOwner currentResident;
    private ArrayList<RoomOwner> roomOwners;

    public void initialize(String username) throws IOException {
        this.username.setText(username);
        String name = null;
        roomOwners = csvControlInterface.createRoomOwnerListFromCSV();
        accounts = userControlInterface.createAccountFromCSV();
        for(Account x : accounts)
        {
            if(x.getUsername().equals(username))
            {
                name = x.getName();
                break;
            }
        }
        for(RoomOwner roomOwner : roomOwners)
        {
            if(roomOwner.getName().equals(name))
            {
                currentResident = roomOwner;
                break;
            }
        }

        letters = csvControlInterface.createLetterListFromCSV();
        packages = csvControlInterface.createPackageListFromCSV();
        documents = csvControlInterface.createDocumentListFromCSV();
        lettersHis = csvControlInterface.createLetterHistoryListFromCSV();
        packagesHis = csvControlInterface.createPackageHistoryListFromCSV();
        documentsHis = csvControlInterface.createDocumentHistoryListFromCSV();
        for(Letter letter : letters)
        {
            if(letter.getRoomNumber().equals(currentResident.getRoomNumber()))
                show.add(letter);
        }
        for(Package aPackage : packages)
        {
            if(aPackage.getRoomNumber().equals(currentResident.getRoomNumber()))
                show.add(aPackage);
        }
        for(Document document : documents)
        {
            if(document.getRoomNumber().equals(currentResident.getRoomNumber()))
                show.add(document);
        }
        for(Letter letter : lettersHis)
        {
            if(letter.getRoomNumber().equals(currentResident.getRoomNumber()))
                his.add(letter);
        }
        for(Package aPackage : packagesHis)
        {
            if(aPackage.getRoomNumber().equals(currentResident.getRoomNumber()))
                his.add(aPackage);
        }
        for(Document document : documentsHis)
        {
            if(document.getRoomNumber().equals(currentResident.getRoomNumber()))
                his.add(document);
        }
        showObs = FXCollections.observableList(show);
        hisObs = FXCollections.observableList(his);
        notReceived.setItems(showObs);
        received.setItems(hisObs);
        sendNot.setCellValueFactory(new PropertyValueFactory<>("SenderName"));
        receiverNot.setCellValueFactory(new PropertyValueFactory<>("ReceiverName"));
        dateNot.setCellValueFactory(new PropertyValueFactory<>("date"));
        sendRe.setCellValueFactory(new PropertyValueFactory<>("SenderName"));
        receiverRe.setCellValueFactory(new PropertyValueFactory<>("SenderName"));
        dateRe.setCellValueFactory(new PropertyValueFactory<>("DatePaid"));

        received.setRowFactory( tv -> {
            TableRow<Letter> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                    Letter rowData = row.getItem();
                    try {
                        showLetter(rowData);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            return row;
        });

        received.setRowFactory( tv -> {
            TableRow<Letter> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                    Letter rowData = row.getItem();
                    try {
                        showLetter(rowData);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            return row;
        });

        notReceived.setRowFactory( tv -> {
            TableRow<Letter> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                    Letter rowData = row.getItem();
                    try {
                        showLetter(rowData);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            return row;
        });
    }

    private void showLetter(Letter rowData) throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/LetterHistoryDetail.fxml"));
        stage.setTitle("Letter Detail");
        stage.setScene(new Scene(loader.load(), 800, 600));
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        double width = 800;
        double height = 600;
        stage.setX((screenBounds.getWidth() - width) / 2);
        stage.setY((screenBounds.getHeight() - height) / 2);
        LetterHistoryDetail dw = loader.getController();
        dw.initialize(rowData);
        stage.show();
    }

    public void changePasswordClick() throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ChangePasswordResident.fxml"));
        stage.setScene(new Scene(loader.load(),400,600));
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        double width = 400;
        double height = 600;
        stage.setX((screenBounds.getWidth() - width) / 2);
        stage.setY((screenBounds.getHeight() - height) / 2);
        stage.setTitle("Change Password");
        ChangePasswordResidentController dw = loader.getController();
        for(Account account : accounts)
        {
            if(account.getName().equals(currentResident.getName()))
            {
                System.out.println(currentResident.getName());
                dw.initialize(account);
                break;
            }
        }
        stage.show();
    }

    public void logoutClick() throws IOException {
        Stage stage = (Stage) logout.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Login.fxml"));
        stage.setScene(new Scene(loader.load(),400,600));
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        double width = 400;
        double height = 600;
        stage.setX((screenBounds.getWidth() - width) / 2);
        stage.setY((screenBounds.getHeight() - height) / 2);
        stage.setTitle("Login");
        stage.show();
    }
}
