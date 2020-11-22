package Final.controller.staffController.Mailbox;

import Final.account.Staff;
import Final.CSVControlInterface;
import Final.CSVControlInterfaceControl;
import Final.item.Document;
import Final.item.Letter;
import Final.item.Package;
import Final.controller.staffController.Mailbox.HistoryItemDetail.DocumentHistoryDetail;
import Final.controller.staffController.Mailbox.HistoryItemDetail.LetterHistoryDetail;
import Final.controller.staffController.Mailbox.HistoryItemDetail.PackageHistoryDetail;
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

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

public class ItemReceivedController {
    @FXML
    Label letter, document, back, packages, staff, clear;
    @FXML
    TableView table;

    private ArrayList<Letter> lettersHistory;
    private ArrayList<Package> packagesHistory;
    private ArrayList<Document> documentsHistory;
    private CSVControlInterface csvControlInterface = new CSVControlInterfaceControl();
    private ObservableList<Letter> letterObservableList;
    private ObservableList<Package> packageObservableList;
    private ObservableList<Document> documentObservableList;
    private Staff currentStaff;
    private int check = 0;

    public void setCurrentStaff(Staff currentStaff) {
        this.currentStaff = currentStaff;
        staff.setText(currentStaff.getUsername());
    }

    public void initialize() throws IOException {
        lettersHistory = csvControlInterface.createLetterHistoryListFromCSV();
        packagesHistory = csvControlInterface.createPackageHistoryListFromCSV();
        documentsHistory = csvControlInterface.createDocumentHistoryListFromCSV();

        letterObservableList = FXCollections.observableList(lettersHistory);
        packageObservableList = FXCollections.observableList(packagesHistory);
        documentObservableList = FXCollections.observableList(documentsHistory);

        table.setPlaceholder(new Label("PLEASE SELECT ITEM FROM LEFT MENU"));

        table.setRowFactory(tv -> {
            TableRow<Object> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    Object rowData = row.getItem();
                    try {
                        showDetail(rowData);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            return row;
        });
    }

    private void showDetail(Object rowData) throws IOException {
        if (check == 1) {
            Letter letter = (Letter) rowData;
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
            dw.initialize(letter);
            stage.show();
        } else if (check == 2) {
            Document document = (Document) rowData;
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/DocumentHistoryDetail.fxml"));
            stage.setTitle("Document Detail");
            stage.setScene(new Scene(loader.load(), 800, 600));
            Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
            double width = 800;
            double height = 600;
            stage.setX((screenBounds.getWidth() - width) / 2);
            stage.setY((screenBounds.getHeight() - height) / 2);
            DocumentHistoryDetail dw = loader.getController();
            dw.initialize(document);
            stage.show();
        } else if (check == 3) {
            Package packages = (Package) rowData;
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/PackageHistoryDetail.fxml"));
            stage.setTitle("Package Detail");
            stage.setScene(new Scene(loader.load(), 800, 600));
            Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
            double width = 800;
            double height = 600;
            stage.setX((screenBounds.getWidth() - width) / 2);
            stage.setY((screenBounds.getHeight() - height) / 2);
            PackageHistoryDetail dw = loader.getController();
            dw.initialize(packages);
            stage.show();
        }
    }

    @FXML
    public void letterClick() {
        check = 1;
        table.setItems(letterObservableList);
        table.getColumns().clear();
        TableColumn col = new TableColumn("ROOM NUMBER");
        col.setCellValueFactory(new PropertyValueFactory<>("roomNumber"));
        col.setPrefWidth(100);
        col.setMinWidth(100);
        col.setMaxWidth(100);
        col.setStyle("-fx-alignment: CENTER");
        table.getColumns().add(col);

        col = new TableColumn("SENDER");
        col.setCellValueFactory(new PropertyValueFactory<>("senderName"));
        col.setPrefWidth(100);
        col.setMinWidth(100);
        col.setMaxWidth(100);
        col.setStyle("-fx-alignment: CENTER");
        table.getColumns().add(col);

        col = new TableColumn("RECEIVER");
        col.setCellValueFactory(new PropertyValueFactory<>("receiverName"));
        col.setPrefWidth(100);
        col.setMinWidth(100);
        col.setMaxWidth(100);
        col.setStyle("-fx-alignment: CENTER");
        table.getColumns().add(col);

        col = new TableColumn("DATE RECEIVE");
        col.setCellValueFactory(new PropertyValueFactory<>("date"));
        col.setPrefWidth(100);
        col.setMinWidth(100);
        col.setMaxWidth(100);
        col.setStyle("-fx-alignment: CENTER");
        table.getColumns().add(col);

        col = new TableColumn("TIME RECEIVE");
        col.setCellValueFactory(new PropertyValueFactory<>("time"));
        col.setPrefWidth(100);
        col.setMinWidth(100);
        col.setMaxWidth(100);
        col.setStyle("-fx-alignment: CENTER");
        table.getColumns().add(col);

        col = new TableColumn("STAFF GET");
        col.setCellValueFactory(new PropertyValueFactory<>("Getter"));
        col.setPrefWidth(100);
        col.setMinWidth(100);
        col.setMaxWidth(100);
        col.setStyle("-fx-alignment: CENTER");
        table.getColumns().add(col);

        col = new TableColumn("STAFF PAID");
        col.setCellValueFactory(new PropertyValueFactory<>("Paider"));
        col.setPrefWidth(100);
        col.setMinWidth(100);
        col.setMaxWidth(100);
        col.setStyle("-fx-alignment: CENTER");
        table.getColumns().add(col);
    }

    @FXML
    public void documentClick() {
        check = 2;
        table.setItems(documentObservableList);
        table.getColumns().clear();
        TableColumn col = new TableColumn("ROOM NUMBER");
        col.setCellValueFactory(new PropertyValueFactory<>("roomNumber"));
        col.setPrefWidth(100);
        col.setMinWidth(100);
        col.setMaxWidth(100);
        col.setStyle("-fx-alignment: CENTER");
        table.getColumns().add(col);

        col = new TableColumn("SENDER");
        col.setCellValueFactory(new PropertyValueFactory<>("senderName"));
        col.setPrefWidth(100);
        col.setMinWidth(100);
        col.setMaxWidth(100);
        col.setStyle("-fx-alignment: CENTER");
        table.getColumns().add(col);

        col = new TableColumn("RECEIVER");
        col.setCellValueFactory(new PropertyValueFactory<>("receiverName"));
        col.setPrefWidth(100);
        col.setMinWidth(100);
        col.setMaxWidth(100);
        col.setStyle("-fx-alignment: CENTER");
        table.getColumns().add(col);

        col = new TableColumn("DATE RECEIVE");
        col.setCellValueFactory(new PropertyValueFactory<>("date"));
        col.setPrefWidth(100);
        col.setMinWidth(100);
        col.setMaxWidth(100);
        col.setStyle("-fx-alignment: CENTER");
        table.getColumns().add(col);

        col = new TableColumn("TIME RECEIVE");
        col.setCellValueFactory(new PropertyValueFactory<>("time"));
        col.setPrefWidth(100);
        col.setMinWidth(100);
        col.setMaxWidth(100);
        col.setStyle("-fx-alignment: CENTER");
        table.getColumns().add(col);

        col = new TableColumn("STAFF GET");
        col.setCellValueFactory(new PropertyValueFactory<>("Getter"));
        col.setPrefWidth(100);
        col.setMinWidth(100);
        col.setMaxWidth(100);
        col.setStyle("-fx-alignment: CENTER");
        table.getColumns().add(col);

        col = new TableColumn("STAFF PAID");
        col.setCellValueFactory(new PropertyValueFactory<>("Paider"));
        col.setPrefWidth(100);
        col.setMinWidth(100);
        col.setMaxWidth(100);
        col.setStyle("-fx-alignment: CENTER");
        table.getColumns().add(col);
    }

    @FXML
    public void packageClick() {
        check = 3;
        table.setItems(packageObservableList);
        table.getColumns().clear();
        TableColumn col = new TableColumn("ROOM NUMBER");
        col.setCellValueFactory(new PropertyValueFactory<>("roomNumber"));
        col.setPrefWidth(100);
        col.setMinWidth(100);
        col.setMaxWidth(100);
        col.setStyle("-fx-alignment: CENTER");
        table.getColumns().add(col);

        col = new TableColumn("SENDER");
        col.setCellValueFactory(new PropertyValueFactory<>("senderName"));
        col.setPrefWidth(100);
        col.setMinWidth(100);
        col.setMaxWidth(100);
        col.setStyle("-fx-alignment: CENTER");
        table.getColumns().add(col);

        col = new TableColumn("RECEIVER");
        col.setCellValueFactory(new PropertyValueFactory<>("receiverName"));
        col.setPrefWidth(100);
        col.setMinWidth(100);
        col.setMaxWidth(100);
        col.setStyle("-fx-alignment: CENTER");
        table.getColumns().add(col);

        col = new TableColumn("DATE RECEIVE");
        col.setCellValueFactory(new PropertyValueFactory<>("date"));
        col.setPrefWidth(100);
        col.setMinWidth(100);
        col.setMaxWidth(100);
        col.setStyle("-fx-alignment: CENTER");
        table.getColumns().add(col);

        col = new TableColumn("TIME RECEIVE");
        col.setCellValueFactory(new PropertyValueFactory<>("time"));
        col.setPrefWidth(100);
        col.setMinWidth(100);
        col.setMaxWidth(100);
        col.setStyle("-fx-alignment: CENTER");
        table.getColumns().add(col);

        col = new TableColumn("STAFF GET");
        col.setCellValueFactory(new PropertyValueFactory<>("Getter"));
        col.setPrefWidth(100);
        col.setMinWidth(100);
        col.setMaxWidth(100);
        col.setStyle("-fx-alignment: CENTER");
        table.getColumns().add(col);

        col = new TableColumn("STAFF PAID");
        col.setCellValueFactory(new PropertyValueFactory<>("Paider"));
        col.setPrefWidth(100);
        col.setMinWidth(100);
        col.setMaxWidth(100);
        col.setStyle("-fx-alignment: CENTER");
        table.getColumns().add(col);
    }

    @FXML
    public void backClick() throws IOException {
        Stage stage = (Stage) back.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/MailBox.fxml"));
        stage.setScene(new Scene(loader.load(), 1000, 600));
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        double width = 1000;
        double height = 600;
        stage.setX((screenBounds.getWidth() - width) / 2);
        stage.setY((screenBounds.getHeight() - height) / 2);
        stage.setTitle("MailBox");
        MailBoxController dw = loader.getController();
        dw.setCurrentStaff(currentStaff);
        stage.show();
    }

    @FXML
    public void clearClick() throws IOException {
        clearHistory();
    }

    private void clearHistory() throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("Are you sure to clear history mailbox?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            File file = new File("CSV/documentHistory.csv");
            FileWriter fileWriter = null;
            try {
                fileWriter = new FileWriter(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
            assert fileWriter != null;
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.append("");
            bufferedWriter.close();
            fileWriter.close();

            file = new File("CSV/letterHistory.csv");
            fileWriter = null;
            try {
                fileWriter = new FileWriter(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
            assert fileWriter != null;
            bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.append("");
            bufferedWriter.close();
            fileWriter.close();

            file = new File("CSV/packageHistory.csv");
            fileWriter = null;
            try {
                fileWriter = new FileWriter(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
            assert fileWriter != null;
            bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.append("");
            bufferedWriter.close();
            fileWriter.close();
            refresh();
        }
    }

    private void refresh() throws IOException {
        lettersHistory = csvControlInterface.createLetterHistoryListFromCSV();
        letterObservableList = FXCollections.observableList(lettersHistory);
        table.setItems(letterObservableList);
        table.refresh();
    }
}
