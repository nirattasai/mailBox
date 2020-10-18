package Final.Controller;


import Final.Controller.Account.RoomOwner;
import Final.Controller.Account.Staff;
import Final.Controller.Building.Room;

import java.io.IOException;
import java.util.ArrayList;

public interface CSVControlInterface {

    ArrayList<Staff> createStaffListFromCSV() throws IOException;

    void writeStaffListToCSV(ArrayList<Staff> staff) throws IOException;

    ArrayList<Room> createRoomListFromCSV() throws IOException;

    void writeRoomListToCSV(ArrayList<Room> rooms) throws IOException;

    ArrayList<RoomOwner> createRoomOwnerListFromCSV() throws IOException;

    void writeRoomOwnerListToCSV(ArrayList<RoomOwner> roomOwners) throws IOException;

}
