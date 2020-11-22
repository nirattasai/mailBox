package Final.controller.staffController.mailbox.historyItemDetail;

import Final.item.Letter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class LetterHistoryDetail {
    @FXML
    ImageView image;
    @FXML
    Text roomNumber,receiver,sender,date,time,size,staff,staffPaid,timeOut,dateOut,resident;
    @FXML
    Button backButton;
    private Letter currentLetter;
    public void initialize(Letter letter)
    {
        this.currentLetter = letter;
        Image imageShow = new Image(currentLetter.getPicture());
        image.setImage(imageShow);
        roomNumber.setText(currentLetter.getRoomNumber());
        receiver.setText(currentLetter.getReceiverName());
        sender.setText(currentLetter.getSenderName());
        date.setText(currentLetter.getDate());
        time.setText(currentLetter.getTime());
        staff.setText(currentLetter.getGetter());
        size.setText(currentLetter.getSize());
        staffPaid.setText(currentLetter.getPaider());
        timeOut.setText(currentLetter.getTimePaid());
        dateOut.setText(currentLetter.getDatePaid());
        resident.setText(currentLetter.getResident());
    }

    @FXML public void handleBackButton(ActionEvent event){
        Button b = (Button) event.getSource();
        Stage stage = (Stage) b.getScene().getWindow();
        stage.close();
    }
}
