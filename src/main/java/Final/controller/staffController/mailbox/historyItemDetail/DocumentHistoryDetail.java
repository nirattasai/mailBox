package Final.controller.staffController.mailbox.historyItemDetail;

import Final.item.Document;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class DocumentHistoryDetail {
    @FXML
    ImageView image;
    @FXML
    Text roomNumber,receiver,sender,date,time,size,staff,staffPaid,timeOut,dateOut,resident;
    @FXML
    Button backButton;
    private Document currentDocument;
    public void initialize(Document document)
    {
        this.currentDocument = document;
        Image imageShow = new Image(currentDocument.getPicture());
        image.setImage(imageShow);
        roomNumber.setText(currentDocument.getRoomNumber());
        receiver.setText(currentDocument.getReceiverName());
        sender.setText(currentDocument.getSenderName());
        date.setText(currentDocument.getDate());
        time.setText(currentDocument.getTime());
        staff.setText(currentDocument.getGetter());
        size.setText(currentDocument.getSize());
        staffPaid.setText(currentDocument.getPaider());
        timeOut.setText(currentDocument.getTimePaid());
        dateOut.setText(currentDocument.getDatePaid());
        resident.setText(currentDocument.getResident());
    }

    @FXML public void handleBackButton(ActionEvent event){
        Button b = (Button) event.getSource();
        Stage stage = (Stage) b.getScene().getWindow();
        stage.close();
    }
}
