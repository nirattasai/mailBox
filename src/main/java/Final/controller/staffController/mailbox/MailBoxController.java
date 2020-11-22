package Final.controller.staffController.mailbox;

import Final.CSVControlInterface;
import Final.CSVControlInterfaceControl;
import Final.account.Staff;
import Final.building.Room;
import Final.controller.staffController.mailbox.itemDetail.DocumentDetailController;
import Final.controller.staffController.mailbox.itemDetail.LetterDetailController;
import Final.controller.staffController.mailbox.itemDetail.PackageDetailController;
import Final.controller.staffController.StaffPageController;
import Final.item.Document;
import Final.item.Letter;
import Final.item.Package;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Optional;

public class MailBoxController {
    @FXML
    Label backButton,addItemButton,residentReceivedButton,itemReceived,staffLabel;
    @FXML
    TableView mailBoxTableView,itemTable;
    @FXML
    TableColumn timeCol,senderCol,receiverCol,typeCol;
    @FXML
    ChoiceBox sort,sortRoom;
    @FXML
    Text roomText;

    private Staff currentStaff;
    private ObservableList<Room> roomObservableList;
    private ObservableList<Letter> itemObservableList;
    private CSVControlInterface csvControlInterface = new CSVControlInterfaceControl();
    private ArrayList<Room> rooms;
    private ArrayList<Letter> getAllItem;
    private ArrayList<Letter> letters;
    private ArrayList<Document> documents;
    private ArrayList<Package> packages;
    private Room roomChoose;
    private ArrayList<Letter> letterHistory;
    private ArrayList<Package> packageHistory;
    private ArrayList<Document> documentHistory;
    private String resident;
    private String sortType = "Latest";
    private String sortRoomType = "Ascending";
    private String roomSearch="";

    public void initialize() throws IOException {

        letters = csvControlInterface.createLetterListFromCSV();
        packages = csvControlInterface.createPackageListFromCSV();
        documents = csvControlInterface.createDocumentListFromCSV();
        rooms = csvControlInterface.createRoomListFromCSV();

        letterHistory = csvControlInterface.createLetterHistoryListFromCSV();
        packageHistory = csvControlInterface.createPackageHistoryListFromCSV();
        documentHistory = csvControlInterface.createDocumentHistoryListFromCSV();
        mailBoxTableView.setPlaceholder(new Label("NO ROOM"));
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

        itemTable.setRowFactory( tv -> {
            TableRow<Document> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                    Letter rowData = row.getItem();
                    try {
                        showItem(rowData);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            return row;
        });

        sort.getItems().add("Latest");
        sort.getItems().add("Oldest");
        sort.setValue("Latest");
        sort.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) ->
        {
            if(newValue != null)
            {
                sortType = (String) newValue;
                try {
                    setItem(roomChoose);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        sortRoom.getItems().add("Ascending");
        sortRoom.getItems().add("Descending");
        sortRoom.setValue("Ascending");
        sortRoom.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> 
        {
            if(newValue != null)
            {
                sortRoomType = (String) newValue;
                try {
                    setMailBox(sortRoomType);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        setItem(rooms.get(0));
        roomChoose = rooms.get(0);
        roomText.setText(roomChoose.getRoomNumberFull());
    }

    private void setMailBox(String sortRoomType) throws IOException {
        if(sortRoomType.equals("Ascending"))
        {
            rooms = csvControlInterface.createRoomListFromCSV();
            roomObservableList = FXCollections.observableList(rooms);
            mailBoxTableView.setItems(roomObservableList);
            mailBoxTableView.refresh();
        }
        else if(sortRoomType.equals("Descending"))
        {
            rooms = csvControlInterface.createRoomListFromCSV();
            Collections.reverse(rooms);
            roomObservableList = FXCollections.observableList(rooms);
            mailBoxTableView.setItems(roomObservableList);
            mailBoxTableView.refresh();
        }
    }

    private void showItem(Letter letter) throws IOException {
        if (letter.getType().equals("Letter"))
        {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/LetterDetail.fxml"));
            stage.setTitle("Letter Detail");
            stage.setScene(new Scene(loader.load(), 400, 600));
            Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
            double width = 400;
            double height = 600;
            stage.setX((screenBounds.getWidth() - width) / 2);
            stage.setY((screenBounds.getHeight() - height) / 2);
            LetterDetailController dw = loader.getController();
            dw.initialize(letter);
            stage.show();
        }
        else if  (letter.getType().equals("Package"))
        {
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
            dw.initialize((Package) letter);
            stage.show();
        }
        else if (letter.getType().equals("Document"))
        {
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
            dw.initialize((Document) letter);
            stage.show();
        }
    }

    public void setRoomChoose(Object newValue) {
        this.roomChoose = (Room) newValue;
        roomText.setText(roomChoose.getRoomNumberFull());
    }

    public void setCurrentStaff(Staff staff) {
        currentStaff = staff;
        staffLabel.setText(currentStaff.getUsername());
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
        stage.showAndWait();
        refresh();
    }

    @FXML public void handleResidentReceivedButton() throws IOException {
        TextInputDialog textInputDialog = new TextInputDialog();
        textInputDialog.setContentText("Receiver Name: ");
        textInputDialog.setHeaderText(null);
        Optional<String> result = textInputDialog.showAndWait();
        if(result.isPresent()){
            resident = result.get();
            if(!resident.equals("")) {
                if(roomChoose==null) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setHeaderText(null);
                    alert.setContentText("Select room to receive item first.");
                    alert.getDialogPane().setPrefWidth(300);
                    alert.showAndWait();
                }
                else {
                    int check = 0;
                    for (Room room : rooms) {
                        if (room.getRoomNumberFull().equals(roomChoose.getRoomNumberFull())) {
                            room.setItem("No item in mailbox");
                            break;
                        }
                    }
                    for (int i = 0; i < letters.size(); i++) {
                        if (letters.get(i).getRoomNumber().equals(roomChoose.getRoomNumberFull())) {
                            letters.get(i).setPaider(currentStaff.getUsername());
                            letters.get(i).setResident(resident);
                            letters.get(i).setOut(java.time.LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                                    java.time.LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
                            letterHistory.add(letters.get(i));
                            letters.remove(letters.get(i));
                            i--;
                            check += 1;
                        }
                    }
                    for (int i = 0; i < packages.size(); i++) {
                        if (packages.get(i).getRoomNumber().equals(roomChoose.getRoomNumberFull())) {
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
                    for (int i = 0; i < documents.size(); i++) {
                        if (documents.get(i).getRoomNumber().equals(roomChoose.getRoomNumberFull())) {
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
                    if (check > 0) {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Success");
                        alert.setHeaderText("All item are received");
                        alert.setContentText("All item in mailbox room : " + roomChoose.getRoomNumberFull() + " are received");

                        csvControlInterface.writePackageListToCSV(packages);
                        csvControlInterface.writeLetterListToCSV(letters);
                        csvControlInterface.writeDocumentListToCSV(documents);
                        csvControlInterface.writeRoomListToCSV(rooms);
                        csvControlInterface.writeDocumentHistoryListToCSV(documentHistory);
                        csvControlInterface.writeLetterHistoryListToCSV(letterHistory);
                        csvControlInterface.writePackageHistoryListToCSV(packageHistory);
                        alert.showAndWait();
                        refresh();

                    } else if (check == 0) {
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("Failed");
                        alert.setHeaderText("Cannot get item");
                        alert.setContentText("No item in mailbox's room : " + roomChoose.getRoomNumberFull());
                        alert.showAndWait();
                    }
                }
            }
            else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Please enter receiver name.");
                alert.setHeaderText(null);
                alert.getDialogPane().setPrefWidth(300);
                alert.showAndWait();
            }
        }
    }

    @FXML public void handleBackButton() throws IOException {
        Stage stage = (Stage) backButton.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/StaffPage.fxml"));
        stage.setScene(new Scene(loader.load(),1000,600));
        StaffPageController dw = loader.getController();
        dw.setCurrentStaff(currentStaff);
        stage.setTitle("Staff Page");
        stage.show();
    }

    @FXML public void refresh() throws IOException {
        if(sortRoomType.equals("Ascending")) {
            rooms = csvControlInterface.createRoomListFromCSV();
            roomObservableList = FXCollections.observableList(rooms);
        }
        else if (sortRoomType.equals("Descending")) {
            ArrayList<Room> roomsReverse = csvControlInterface.createRoomListFromCSV();
            Collections.reverse(roomsReverse);
            roomObservableList = FXCollections.observableList(roomsReverse);
        }
        mailBoxTableView.setItems(roomObservableList);
        mailBoxTableView.refresh();
        setItem(roomChoose);
    }

    public void setItem(Object newValue) throws IOException {
        packages = csvControlInterface.createPackageListFromCSV();
        letters = csvControlInterface.createLetterListFromCSV();
        documents = csvControlInterface.createDocumentListFromCSV();
        getAllItem = new ArrayList<>();
        for (Letter x : letters)
        {
            getAllItem.add(x);
        }
        for (Document x : documents)
        {
            getAllItem.add(x);
        }
        for (Package x : packages)
        {
            getAllItem.add(x);
        }
        Room room = (Room) newValue;
        ArrayList<Letter> itemSelected = new ArrayList<>();
        for (Letter x : getAllItem)
        {
            if(x.getRoomNumber().equals(room.getRoomNumberFull()))
            {
                itemSelected.add(x);
            }
        }
        if (sortType.equals("Oldest")){
            Collections.sort(itemSelected, new Comparator<Letter>() {
                @Override
                public int compare(Letter o1, Letter o2) {
                    int sComp = o1.getDate().compareTo(o2.getDate());
                    if (sComp!=0){
                        return sComp;
                    }
                    return o1.getTime().compareTo(o2.getTime());
                }
            });
        }
        else if (sortType.equals("Latest")){
            Collections.sort(itemSelected, new Comparator<Letter>() {
                @Override
                public int compare(Letter o1, Letter o2) {
                    int sComp = o1.getDate().compareTo(o2.getDate());
                    if (sComp!=0){
                        return sComp;
                    }
                    return o1.getTime().compareTo(o2.getTime());
                }
            });
            Collections.reverse(itemSelected);
        }
        itemObservableList = FXCollections.observableList(itemSelected);
        itemTable.setItems(itemObservableList);
        senderCol.setCellValueFactory(new PropertyValueFactory<>("SenderName"));
        receiverCol.setCellValueFactory(new PropertyValueFactory<>("ReceiverName"));
        timeCol.setCellValueFactory(new PropertyValueFactory<>("date"));
        typeCol.setCellValueFactory(new PropertyValueFactory<>("Type"));
        itemTable.refresh();
    }

    @FXML public void handleItemReceived() throws IOException {
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

    @FXML public void searchClick() throws IOException {
        int check = 0;
        TextInputDialog textInputDialog = new TextInputDialog();
        textInputDialog.setContentText("Search room: ");
        textInputDialog.setHeaderText(null);
        Optional<String> result = textInputDialog.showAndWait();
        if(result.isPresent())
        {
            roomSearch = result.get();
            if(!roomSearch.equals("")) {
                for (Room x : rooms) {
                    if (x.getRoomNumberFull().equals(roomSearch)) {
                        setItem(x);
                        roomChoose = x;
                        roomText.setText(roomSearch);
                        check = 1;
                        break;
                    }
                }
                if (check == 0) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText(null);
                    alert.setContentText("No room number : " + roomSearch);
                    alert.getDialogPane().setPrefWidth(300);
                    alert.showAndWait();
                }
            }
                else
                {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setHeaderText(null);
                    alert.setContentText("Please enter room number to search.");
                    alert.getDialogPane().setPrefWidth(300);
                    alert.showAndWait();
                }
        }
    }
}
