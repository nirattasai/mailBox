package Final.Controller;

import Final.Staff;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;

import java.io.IOException;
import java.util.ArrayList;

public class CheckStaffController {

    @FXML
    TableView<String> checkLogTableView;
    public void initialize() throws IOException {
        ArrayList<Staff> staff = StaffInterface.createStaffListFromCSV();
    }
}
