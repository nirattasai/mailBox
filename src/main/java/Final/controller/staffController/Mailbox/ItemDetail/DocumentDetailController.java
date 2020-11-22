package Final.controller.staffController.Mailbox.ItemDetail;

import Final.item.Document;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class DocumentDetailController {
    @FXML
    ImageView image;
    @FXML
    Text roomNumber,receiver,sender,date,time,size,staff,priority;
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
        priority.setText(currentDocument.getPrivacy());
    }

    @FXML public void handleBackButton(ActionEvent event){
        Button b = (Button) event.getSource();
        Stage stage = (Stage) b.getScene().getWindow();
        stage.close();
    }
}
