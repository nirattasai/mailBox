package Final.Controller;

import Final.Controller.Account.RoomOwner;
import Final.Controller.Account.Staff;
import Final.Controller.Building.Room;
import Final.Controller.Item.Document;
import Final.Controller.Item.Letter;
import Final.Controller.Item.Package;

import java.io.*;
import java.util.ArrayList;

public class CSVControlInterfaceControl implements CSVControlInterface {

    private File file = new File("CSV/Staff.csv");

    public ArrayList<Staff> createStaffListFromCSV() throws IOException {
        new FileReader(file);
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line;
        ArrayList<Staff> staff = new ArrayList<>();
        while ((line = bufferedReader.readLine()) != null) {
            String[] staffTmp = line.split(",");
            Staff staffAdd = new Staff(staffTmp[3], staffTmp[4], staffTmp[1], staffTmp[2], staffTmp[5], staffTmp[6], staffTmp[7], staffTmp[8], staffTmp[9], Integer.parseInt(staffTmp[10]),staffTmp[11]);
            staff.add(staffAdd);
        }
        fileReader.close();
        bufferedReader.close();
        return staff;
    }

    public void writeStaffListToCSV(ArrayList<Staff> staff) throws IOException {
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert fileWriter != null;
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        String line;
        for (Staff value : staff) {
            line = "staff" + "," + value.getUsername() + "," + value.getPassword() + "," + value.getName() + "," + value.getSurname() + "," + value.getEmail() + "," + value.getTel() + "," + value.getDate() + "," + value.getTime() + "," + value.getStatus() + "," + value.getTryBlockLogin()+","+value.getPicture();
            bufferedWriter.append(line);
            bufferedWriter.newLine();
        }
        bufferedWriter.close();
        fileWriter.close();
    }

    @Override
    public ArrayList<Room> createRoomListFromCSV() throws IOException {
        file = new File("CSV/CondoRoom.csv");
        new FileReader(file);
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line;
        ArrayList<Room> rooms = new ArrayList<>();
        while ((line = bufferedReader.readLine()) != null) {
            String[] RoomTmp = line.split(",");
            Room roomAdd = new Room(RoomTmp[0], RoomTmp[1], RoomTmp[2], RoomTmp[3], RoomTmp[4],RoomTmp[5],
                    Integer.parseInt(RoomTmp[6]),Integer.parseInt(RoomTmp[7]));
            rooms.add(roomAdd);
        }
        fileReader.close();
        bufferedReader.close();
        return rooms;
    }

    @Override
    public void writeRoomListToCSV(ArrayList<Room> rooms) throws IOException {
        file = new File("CSV/CondoRoom.csv");
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert fileWriter != null;
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        String line;
        for (Room room : rooms) {
            line = room.getBuilding() + "," + room.getFloor() + "," + room.getRoomNumber() + "," + room.getType() + ","
                    + room.getStatus()+","+room.getItem()+","+room.getMaxResident()+","+room.getCurrentResident();
            bufferedWriter.append(line);
            bufferedWriter.newLine();
        }
        bufferedWriter.close();
        fileWriter.close();
    }

    @Override
    public ArrayList<RoomOwner> createRoomOwnerListFromCSV() throws IOException {
        file = new File("CSV/Resident.csv");
        new FileReader(file);
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line;
        ArrayList<RoomOwner> roomOwners = new ArrayList<>();
        while ((line = bufferedReader.readLine()) != null) {
            String[] RoomTmp = line.split(",");
            RoomOwner roomAdd = new RoomOwner(RoomTmp[0], RoomTmp[1], RoomTmp[2], RoomTmp[3]);
            roomOwners.add(roomAdd);
        }
        fileReader.close();
        bufferedReader.close();
        return roomOwners;
    }

    @Override
    public void writeRoomOwnerListToCSV(ArrayList<RoomOwner> roomOwners) throws IOException {
        file = new File("CSV/Resident.csv");
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert fileWriter != null;
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        String line;
        for (RoomOwner roomOwner : roomOwners) {
            line = roomOwner.getName() + "," + roomOwner.getSurname() + "," + roomOwner.getRoomNumber() + "," + roomOwner.getTel();
            bufferedWriter.append(line);
            bufferedWriter.newLine();
        }
        bufferedWriter.close();
        fileWriter.close();
    }

    @Override
    public ArrayList<Letter> createLetterListFromCSV() throws IOException {
        file = new File("CSV/Letter.csv");
        new FileReader(file);
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line;
        ArrayList<Letter> letters = new ArrayList<>();
        while ((line = bufferedReader.readLine()) != null) {
            String[] letterTmp = line.split(",");
            Letter letterAdd = new Letter(letterTmp[0],letterTmp[1],letterTmp[2],letterTmp[3],letterTmp[4],
                    letterTmp[5],letterTmp[6],letterTmp[7],letterTmp[8],letterTmp[9],letterTmp[10],letterTmp[11] );
            letters.add(letterAdd);
        }
        fileReader.close();
        bufferedReader.close();
        return letters;
    }

    @Override
    public void writeLetterListToCSV(ArrayList<Letter> letters) throws IOException {
        file = new File("CSV/Letter.csv");
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert fileWriter != null;
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        String line;
        for (Letter letter : letters) {
            line = letter.getRoomNumber()+","+letter.getSenderName()+","+letter.getReceiverName()+","+letter.getSize()
                    +","+letter.getDate()+","+letter.getTime()+","+letter.getPicture()+","+letter.getGetter()+","+
                    letter.getPaider()+","+letter.getDatePaid()+","+letter.getTimePaid()+","+letter.getResident();
            bufferedWriter.append(line);
            bufferedWriter.newLine();
        }
        bufferedWriter.close();
        fileWriter.close();
    }

    @Override
    public ArrayList<Document> createDocumentListFromCSV() throws IOException {
        file = new File("CSV/Document.csv");
        new FileReader(file);
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line;
        ArrayList<Document> documents = new ArrayList<>();
        while ((line = bufferedReader.readLine()) != null) {
            String[] documentTmp = line.split(",");
            Document documentAdd = new Document(documentTmp[0],documentTmp[1],documentTmp[2],documentTmp[3],documentTmp[4],
                    documentTmp[5],documentTmp[6],documentTmp[7],documentTmp[8],documentTmp[9],documentTmp[10],
                    documentTmp[11],documentTmp[12] );
            documents.add(documentAdd);
        }
        fileReader.close();
        bufferedReader.close();
        return documents;
    }

    @Override
    public void writeDocumentListToCSV(ArrayList<Document> documents) throws IOException {
        file = new File("CSV/Document.csv");
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert fileWriter != null;
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        String line;
        for (Document document : documents) {
            line = document.getRoomNumber()+","+document.getSenderName()+","+document.getReceiverName()+","+document.getSize()
                    +","+document.getPrivacy()+","+document.getDate()+","+document.getTime()+","+document.getPicture()
            +","+document.getGetter()+","+document.getPaider()+","+document.getDatePaid()+","+document.getTimePaid()
            +","+document.getResident();
            bufferedWriter.append(line);
            bufferedWriter.newLine();
        }
        bufferedWriter.close();
        fileWriter.close();
    }

    @Override
    public ArrayList<Package> createPackageListFromCSV() throws IOException {
        file = new File("CSV/Package.csv");
        new FileReader(file);
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line;
        ArrayList<Package> packages = new ArrayList<>();
        while ((line = bufferedReader.readLine()) != null) {
            String[] packageTmp = line.split(",");
            Package packageAdd = new Package(packageTmp[0],packageTmp[1],packageTmp[2],packageTmp[3],
                    packageTmp[4],packageTmp[5],packageTmp[6],packageTmp[7],packageTmp[8],packageTmp[9],packageTmp[10],
                    packageTmp[11],packageTmp[12],packageTmp[13] );
            packages.add(packageAdd);
        }
        fileReader.close();
        bufferedReader.close();
        return packages;
    }

    public void writePackageListToCSV(ArrayList<Package> packages) throws IOException {
        file = new File("CSV/Package.csv");
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert fileWriter != null;
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        String line;
        for (Package packagee : packages) {
            line = packagee.getRoomNumber()+","+packagee.getSenderName()+","+packagee.getReceiverName()+","+packagee.getSize()
                    +","+packagee.getCarrier()+","+packagee.getTrackNumber()+","+packagee.getDate()+","+packagee.getTime()+
                    ","+packagee.getPicture()+","+packagee.getGetter()+","+packagee.getPaider()+","+packagee.getDatePaid()
                    +","+packagee.getTimePaid()+","+packagee.getResident();
            bufferedWriter.append(line);
            bufferedWriter.newLine();
        }
        bufferedWriter.close();
        fileWriter.close();
    }

    @Override
    public ArrayList<Letter> createLetterHistoryListFromCSV() throws IOException {
        file = new File("CSV/letterHistory.csv");
        new FileReader(file);
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line;
        ArrayList<Letter> letters = new ArrayList<>();
        while ((line = bufferedReader.readLine()) != null) {
            String[] letterTmp = line.split(",");
            Letter letterAdd = new Letter(letterTmp[0],letterTmp[1],letterTmp[2],letterTmp[3],letterTmp[4],
                    letterTmp[5],letterTmp[6],letterTmp[7],letterTmp[8] ,letterTmp[9],letterTmp[10],letterTmp[11] );
            letters.add(letterAdd);
        }
        fileReader.close();
        bufferedReader.close();
        return letters;
    }

    @Override
    public void writeLetterHistoryListToCSV(ArrayList<Letter> letters) throws IOException {
        file = new File("CSV/letterHistory.csv");
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert fileWriter != null;
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        String line;
        for (Letter letter : letters) {
            line = letter.getRoomNumber()+","+letter.getSenderName()+","+letter.getReceiverName()+","+letter.getSize()
                    +","+letter.getDate()+","+letter.getTime()+","+letter.getPicture()+","+letter.getGetter()+","+
                    letter.getPaider()+","+letter.getDatePaid()+","+letter.getTimePaid()+","+letter.getResident();
            bufferedWriter.append(line);
            bufferedWriter.newLine();
        }
        bufferedWriter.close();
        fileWriter.close();
    }

    @Override
    public ArrayList<Document> createDocumentHistoryListFromCSV() throws IOException {
        file = new File("CSV/documentHistory.csv");
        new FileReader(file);
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line;
        ArrayList<Document> documents = new ArrayList<>();
        while ((line = bufferedReader.readLine()) != null) {
            String[] documentTmp = line.split(",");
            Document documentAdd = new Document(documentTmp[0],documentTmp[1],documentTmp[2],documentTmp[3],documentTmp[4]
                    ,documentTmp[5],documentTmp[6],documentTmp[7],documentTmp[8],documentTmp[9],documentTmp[10],documentTmp[11]
                    ,documentTmp[12] );
            documents.add(documentAdd);
        }
        fileReader.close();
        bufferedReader.close();
        return documents;
    }

    @Override
    public void writeDocumentHistoryListToCSV(ArrayList<Document> documents) throws IOException {
        file = new File("CSV/documentHistory.csv");
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert fileWriter != null;
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        String line;
        for (Document document : documents) {
            line = document.getRoomNumber()+","+document.getSenderName()+","+document.getReceiverName()+","+document.getSize()
                    +","+document.getPrivacy()+","+document.getDate()+","+document.getTime()+","+document.getPicture()
                    +","+document.getGetter()+","+document.getPaider()+","+document.getDatePaid()+","+document.getTimePaid()
                    +","+document.getResident();
            bufferedWriter.append(line);
            bufferedWriter.newLine();
        }
        bufferedWriter.close();
        fileWriter.close();
    }

    @Override
    public ArrayList<Package> createPackageHistoryListFromCSV() throws IOException {
        file = new File("CSV/packageHistory.csv");
        new FileReader(file);
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line;
        ArrayList<Package> packages = new ArrayList<>();
        while ((line = bufferedReader.readLine()) != null) {
            String[] packageTmp = line.split(",");
            Package packageAdd = new Package(packageTmp[0],packageTmp[1],packageTmp[2],packageTmp[3],
                    packageTmp[4],packageTmp[5],packageTmp[6],packageTmp[7],packageTmp[8],packageTmp[9],packageTmp[10],
                    packageTmp[11],packageTmp[12],packageTmp[13] );
            packages.add(packageAdd);
        }
        fileReader.close();
        bufferedReader.close();
        return packages;
    }

    @Override
    public void writePackageHistoryListToCSV(ArrayList<Package> packages) throws IOException {
        file = new File("CSV/packageHistory.csv");
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert fileWriter != null;
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        String line;
        for (Package packagee : packages) {
            line = packagee.getRoomNumber()+","+packagee.getSenderName()+","+packagee.getReceiverName()+","+packagee.getSize()
                    +","+packagee.getCarrier()+","+packagee.getTrackNumber()+","+packagee.getDate()+","+packagee.getTime()+
                    ","+packagee.getPicture()+","+packagee.getGetter()+","+packagee.getPaider()+","+packagee.getDatePaid()
                    +","+packagee.getTimePaid()+","+packagee.getResident();
            bufferedWriter.append(line);
            bufferedWriter.newLine();
        }
        bufferedWriter.close();
        fileWriter.close();
    }
}
