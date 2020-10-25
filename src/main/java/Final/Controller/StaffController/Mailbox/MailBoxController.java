package Final.Controller.StaffController.Mailbox;

import Final.Controller.Account.Staff;
import Final.Controller.Building.Room;
import Final.Controller.CSVControlInterface;
import Final.Controller.CSVControlInterfaceControl;
import Final.Controller.Item.Document;
import Final.Controller.Item.Letter;
import Final.Controller.Item.Package;
import Final.Controller.StaffController.Mailbox.ItemDetail.DocumentDetailController;
import Final.Controller.StaffController.Mailbox.ItemDetail.LetterDetailController;
import Final.Controller.StaffController.Mailbox.ItemDetail.PackageDetailController;
import Final.Controller.StaffController.StaffPageController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class MailBoxController {
    @FXML
    Label backButton,addItemButton,residentReceivedButton,itemReceived;
    @FXML
    TableView mailBoxTableView,letterTable,documentTable,packageTable;
    @FXML
    TableColumn roomNumberLetter,senderLetter,receiverLetter,dateLetter,roomNumberPackage,senderPackage,receiverPackage
            ,datePackage,roomNumberDoc,senderDoc,receiverDoc,dateDoc;

    private Staff currentStaff;
    private ObservableList<Room> roomObservableList;
    private ObservableList<Letter> letterObservableList;
    private ObservableList<Package> packageObservableList;
    private ObservableList<Document> documentObservableList;
    private CSVControlInterface csvControlInterface = new CSVControlInterfaceControl();
    private ArrayList<Room> rooms;
    private ArrayList<Letter> letters;
    private ArrayList<Document> documents;
    private ArrayList<Package> packages;
    private Room roomChoose;
    private ArrayList<Letter> letterHistory;
    private ArrayList<Package> packageHistory;
    private ArrayList<Document> documentHistory;
    public static String resident;
    public static int checkRes=0;

    public void initialize() throws IOException {
        rooms = csvControlInterface.createRoomListFromCSV();
        letters = csvControlInterface.createLetterListFromCSV();
        packages = csvControlInterface.createPackageListFromCSV();
        documents = csvControlInterface.createDocumentListFromCSV();
        letterHistory = csvControlInterface.createLetterHistoryListFromCSV();
        packageHistory = csvControlInterface.createPackageHistoryListFromCSV();
        documentHistory = csvControlInterface.createDocumentHistoryListFromCSV();

        mailBoxTableView.setPlaceholder(new Label("NO ROOM"));
        letterTable.setPlaceholder(new Label("NO LETTER"));
        documentTable.setPlaceholder(new Label("NO DOCUMENT"));
        packageTable.setPlaceholder(new Label("NO PACKAGE"));

        roomObservableList = FXCollections.observableList(rooms);

        mailBoxTableView.setItems(roomObservableList);

        TableColumn col = new TableColumn("ROOM NUMBER");
        col.setCellValueFactory(new PropertyValueFactory<>("roomNumberFull"));
        col.setMinWidth(100);
        col.setMaxWidth(100);
        col.setPrefWidth(100);
        col.setStyle("-fx-alignment: CENTER");
        mailBoxTableView.getColumns().add(col);

        col = new TableColumn("STATUS");
        col.setCellValueFactory(new PropertyValueFactory<>("item"));
        col.setMaxWidth(200);
        col.setMinWidth(200);
        col.setPrefWidth(200);
        col.setStyle("-fx-alignment: CENTER");
        mailBoxTableView.getColumns().add(col);

        mailBoxTableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) ->
        {
            if(newValue!=null)
            {
                try {
                    setItem(newValue);
                    setRoomChoose(newValue);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        letterTable.setRowFactory( tv -> {
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

        packageTable.setRowFactory( tv -> {
            TableRow<Package> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                    Package rowData = row.getItem();
                    try {
                        showPackage(rowData);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            return row;
        });

        documentTable.setRowFactory( tv -> {
            TableRow<Document> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                    Document rowData = row.getItem();
                    try {
                        showDocument(rowData);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            return row;
        });
    }

    public void setResident(String resident) {
        this.resident = resident;
    }

    private void showLetter(Letter letter) throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/LetterDetail.fxml"));
        stage.setTitle("Letter Detail");
        stage.setScene(new Scene(loader.load(),400,600));
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        double width = 400;
        double height = 600;
        stage.setX((screenBounds.getWidth() - width) / 2);
        stage.setY((screenBounds.getHeight() - height) / 2);
        LetterDetailController dw = loader.getController();
        dw.initialize(letter);
        stage.show();
    }

    private void showPackage(Package packages) throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/PackageDetail.fxml"));
        stage.setTitle("Package Detail");
        stage.setScene(new Scene(loader.load(),400,600));
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        double width = 400;
        double height = 600;
        stage.setX((screenBounds.getWidth() - width) / 2);
        stage.setY((screenBounds.getHeight() - height) / 2);
        PackageDetailController dw = loader.getController();
        dw.initialize(packages);
        stage.show();
    }

    private void showDocument(Document document) throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/DocumentDetail.fxml"));
        stage.setTitle("Document Detail");
        stage.setScene(new Scene(loader.load(),400,600));
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        double width = 400;
        double height = 600;
        stage.setX((screenBounds.getWidth() - width) / 2);
        stage.setY((screenBounds.getHeight() - height) / 2);
        DocumentDetailController dw = loader.getController();
        dw.initialize(document);
        stage.show();
    }

    public void setRoomChoose(Object newValue) {
        this.roomChoose = (Room) newValue;
    }
    public void setCurrentStaff(Staff staff)
    {
        currentStaff = staff;
    }

    @FXML public void handleAddItemButton() throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/AddItem.fxml"));
        stage.setScene(new Scene(loader.load(),400,600));
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        double width = 400;
        double height = 600;
        stage.setX((screenBounds.getWidth() - width) / 2);
        stage.setY((screenBounds.getHeight() - height) / 2);
        stage.setTitle("Add item");
        AddItemController dw = loader.getController();
        dw.setCurrentStaff(currentStaff);
        stage.show();
    }

    @FXML public void handleResidentReceivedButton() throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/SetReceived.fxml"));
        stage.setScene(new Scene(loader.load(),400,100));
        stage.showAndWait();
        System.out.println(resident);
        int check = 0;
        for (Room room : rooms) {
            if (room.getRoomNumberFull().equals(roomChoose.getRoomNumberFull())) {
                room.setItem("No item in mailbox");
                break;
            }
        }
        for(int i=0;i<letters.size();i++)
        {
            if(letters.get(i).getRoomNumber().equals(roomChoose.getRoomNumberFull()))
            {
                letters.get(i).setPaider(currentStaff.getUsername());
                letters.get(i).setResident(resident);
                letters.get(i).setOut(java.time.LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                        java.time.LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
                letterHistory.add(letters.get(i));
                letters.remove(letters.get(i));
                i--;
                check+=1;
            }
        }
        for(int i=0;i<packages.size();i++)
        {
            if(packages.get(i).getRoomNumber().equals(roomChoose.getRoomNumberFull()))
            {
                packages.get(i).setPaider(currentStaff.getUsername());
                packages.get(i).setResident(resident);
                packages.get(i).setOut(java.time.LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                        java.time.LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
                packageHistory.add(packages.get(i));
                packages.remove(packages.get(i));
                i--;
                check += 1;
            }
        }
        for(int i=0;i<documents.size();i++)
        {
            if(documents.get(i).getRoomNumber().equals(roomChoose.getRoomNumberFull()))
            {
                documents.get(i).setPaider(currentStaff.getUsername());
                documents.get(i).setResident(resident);
                documents.get(i).setOut(java.time.LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                        java.time.LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
                documentHistory.add(documents.get(i));
                documents.remove(documents.get(i));
                i--;
                check += 1;
            }
        }
        if(check > 0)
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText("All item will be received");
            alert.setContentText("All item in mailbox room : "+roomChoose.getRoomNumberFull()+" are received");
            alert.showAndWait();

            csvControlInterface.writePackageListToCSV(packages);
            csvControlInterface.writeLetterListToCSV(letters);
            csvControlInterface.writeDocumentListToCSV(documents);
            csvControlInterface.writeRoomListToCSV(rooms);
            csvControlInterface.writeDocumentHistoryListToCSV(documentHistory);
            csvControlInterface.writeLetterHistoryListToCSV(letterHistory);
            csvControlInterface.writePackageHistoryListToCSV(packageHistory);

        }
        else if (check == 0)
        {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Failed");
            alert.setHeaderText("Cannot get item");
            alert.setContentText("No item in mailbox");
            alert.showAndWait();
        }
    }

    @FXML public void handleBackButton() throws IOException {
        Stage stage = (Stage) backButton.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/StaffPage.fxml"));
        stage.setScene(new Scene(loader.load(),1000,600));
        StaffPageController dw = loader.getController();
        dw.setCurrentStaff(currentStaff);
        stage.show();
    }

    @FXML public void refresh() throws IOException {
        rooms = csvControlInterface.createRoomListFromCSV();
        roomObservableList = FXCollections.observableList(rooms);
        mailBoxTableView.setItems(roomObservableList);
        mailBoxTableView.refresh();
    }

    public void setItem(Object newValue) throws IOException {
        ArrayList<Package> packageArrayList = new ArrayList<>();
        ArrayList<Letter> letterArrayList = new ArrayList<>();
        ArrayList<Document> documentArrayList = new ArrayList<>();
        Room room = (Room) newValue;
        String roomNumber = room.getRoomNumberFull();
        for (Package x : packages)
        {
            if(roomNumber.equals(x.getRoomNumber()))
            {
                packageArrayList.add(x);
            }
        }
        for (Letter x : letters)
        {
            if(roomNumber.equals(x.getRoomNumber()))
            {
                letterArrayList.add(x);
            }
        }
        for (Document x : documents)
        {
            if(roomNumber.equals(x.getRoomNumber()))
            {
                documentArrayList.add(x);
            }
        }
        letterObservableList = FXCollections.observableList(letterArrayList);
        packageObservableList = FXCollections.observableList(packageArrayList);
        documentObservableList = FXCollections.observableList(documentArrayList);

        letterTable.setItems(letterObservableList);
        packageTable.setItems(packageObservableList);
        documentTable.setItems(documentObservableList);

        roomNumberLetter.setCellValueFactory(new PropertyValueFactory<>("roomNumber"));
        roomNumberLetter.setStyle("-fx-alignment: CENTER");
        senderLetter.setCellValueFactory(new PropertyValueFactory<>("senderName"));
        senderLetter.setStyle("-fx-alignment: CENTER");
        receiverLetter.setCellValueFactory(new PropertyValueFactory<>("receiverName"));
        receiverLetter.setStyle("-fx-alignment: CENTER");
        dateLetter.setCellValueFactory(new PropertyValueFactory<>("date"));
        dateLetter.setStyle("-fx-alignment: CENTER");

        roomNumberPackage.setCellValueFactory(new PropertyValueFactory<>("roomNumber"));
        roomNumberPackage.setStyle("-fx-alignment: CENTER");
        senderPackage.setCellValueFactory(new PropertyValueFactory<>("senderName"));
        senderPackage.setStyle("-fx-alignment: CENTER");
        receiverPackage.setCellValueFactory(new PropertyValueFactory<>("receiverName"));
        receiverPackage.setStyle("-fx-alignment: CENTER");
        datePackage.setCellValueFactory(new PropertyValueFactory<>("date"));
        datePackage.setStyle("-fx-alignment: CENTER");

        roomNumberDoc.setCellValueFactory(new PropertyValueFactory<>("roomNumber"));
        roomNumberDoc.setStyle("-fx-alignment: CENTER");

        senderDoc.setCellValueFactory(new PropertyValueFactory<>("senderName"));
        senderDoc.setStyle("-fx-alignment: CENTER");

        receiverDoc.setCellValueFactory(new PropertyValueFactory<>("receiverName"));
        receiverDoc.setStyle("-fx-alignment: CENTER");

        dateDoc.setCellValueFactory(new PropertyValueFactory<>("date"));
        dateDoc.setStyle("-fx-alignment: CENTER");

        letters = csvControlInterface.createLetterListFromCSV();
        packages = csvControlInterface.createPackageListFromCSV();
        documents = csvControlInterface.createDocumentListFromCSV();

        letterObservableList = FXCollections.observableList(letterArrayList);
        packageObservableList = FXCollections.observableList(packageArrayList);
        documentObservableList = FXCollections.observableList(documentArrayList);

        letterTable.setItems(letterObservableList);
        packageTable.setItems(packageObservableList);
        documentTable.setItems(documentObservableList);

        letterTable.refresh();
        packageTable.refresh();
        documentTable.refresh();
    }

    public void handleItemReceived() throws IOException {
        Stage stage = (Stage) itemReceived.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ItemReceived.fxml"));
        stage.setScene(new Scene(loader.load(),1000,600));
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        double width = 1000;
        double height = 600;
        stage.setX((screenBounds.getWidth() - width) / 2);
        stage.setY((screenBounds.getHeight() - height) / 2);
        stage.setTitle("Item Received");
        ItemReceivedController dw = loader.getController();
        dw.setCurrentStaff(currentStaff);
        stage.show();
    }
}
