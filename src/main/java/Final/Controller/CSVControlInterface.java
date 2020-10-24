package Final.Controller;


import Final.Controller.Account.RoomOwner;
import Final.Controller.Account.Staff;
import Final.Controller.Building.Room;
import Final.Controller.Item.Document;
import Final.Controller.Item.Letter;
import Final.Controller.Item.Package;

import javax.print.Doc;
import java.io.IOException;
import java.util.ArrayList;

public interface CSVControlInterface {

    ArrayList<Staff> createStaffListFromCSV() throws IOException;

    void writeStaffListToCSV(ArrayList<Staff> staff) throws IOException;

    ArrayList<Room> createRoomListFromCSV() throws IOException;

    void writeRoomListToCSV(ArrayList<Room> rooms) throws IOException;

    ArrayList<RoomOwner> createRoomOwnerListFromCSV() throws IOException;

    void writeRoomOwnerListToCSV(ArrayList<RoomOwner> roomOwners) throws IOException;

    ArrayList<Letter> createLetterListFromCSV() throws IOException;

    void writeLetterListToCSV(ArrayList<Letter> letters) throws IOException;

    ArrayList<Document> createDocumentListFromCSV() throws IOException;

    void writeDocumentListToCSV(ArrayList<Document> documents) throws IOException;

    ArrayList<Package> createPackageListFromCSV() throws IOException;

    void writePackageListToCSV(ArrayList<Package> packages) throws IOException;

    ArrayList<Letter> createLetterHistoryListFromCSV() throws IOException;

    void writeLetterHistoryListToCSV(ArrayList<Letter> letters) throws IOException;

    ArrayList<Document> createDocumentHistoryListFromCSV() throws IOException;

    void writeDocumentHistoryListToCSV(ArrayList<Document> documents) throws IOException;

    ArrayList<Package> createPackageHistoryListFromCSV() throws IOException;

    void writePackageHistoryListToCSV(ArrayList<Package> packages) throws IOException;
}
