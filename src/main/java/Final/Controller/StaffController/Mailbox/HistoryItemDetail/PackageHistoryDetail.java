package Final.Controller.StaffController.Mailbox.HistoryItemDetail;

import Final.Controller.Item.Package;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class PackageHistoryDetail {
    @FXML
    ImageView image;
    @FXML
    Text roomNumber,receiver,sender,date,time,size,staff,staffPaid,timeOut,dateOut,resident;
    @FXML
    Button backButton;
    private Package currentPackage;
    public void initialize(Package packages)
    {
        this.currentPackage = packages;
        Image imageShow = new Image(currentPackage.getPicture());
        image.setImage(imageShow);
        roomNumber.setText(currentPackage.getRoomNumber());
        receiver.setText(currentPackage.getReceiverName());
        sender.setText(currentPackage.getSenderName());
        date.setText(currentPackage.getDate());
        time.setText(currentPackage.getTime());
        staff.setText(currentPackage.getGetter());
        size.setText(currentPackage.getSize());
        staffPaid.setText(currentPackage.getPaider());
        timeOut.setText(currentPackage.getTimePaid());
        dateOut.setText(currentPackage.getDatePaid());
        resident.setText(currentPackage.getResident());
    }

    @FXML public void handleBackButton(ActionEvent event){
        Button b = (Button) event.getSource();
        Stage stage = (Stage) b.getScene().getWindow();
        stage.close();
    }
}
