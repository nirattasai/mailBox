package Final;


import Final.account.RoomOwner;
import Final.account.Staff;
import Final.building.Room;
import Final.item.Document;
import Final.item.Letter;
import Final.item.Package;

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
