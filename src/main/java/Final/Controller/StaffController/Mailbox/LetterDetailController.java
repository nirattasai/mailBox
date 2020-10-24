package Final.Controller.StaffController.Mailbox;

import Final.Controller.Item.Letter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class LetterDetailController {
    @FXML
    ImageView image;
    @FXML
    Text roomNumber,receiver,sender,date,time,size,staff;
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
    }

    @FXML public void handleBackButton(ActionEvent event){
        Button b = (Button) event.getSource();
        Stage stage = (Stage) b.getScene().getWindow();
        stage.close();
    }
}
