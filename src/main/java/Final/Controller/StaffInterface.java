package Final.Controller;


import Final.Controller.Account.Staff;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public interface StaffInterface {

    ArrayList<Staff> createStaffListFromCSV() throws IOException;

    void writeStaffListToCSV(ArrayList<Staff> staff) throws IOException;
}
