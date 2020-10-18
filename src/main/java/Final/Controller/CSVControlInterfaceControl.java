package Final.Controller;

import Final.Controller.Account.RoomOwner;
import Final.Controller.Account.Staff;
import Final.Controller.Building.Room;

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
            Staff staffAdd = new Staff(staffTmp[3], staffTmp[4], staffTmp[1], staffTmp[2], staffTmp[5], staffTmp[6], staffTmp[7], staffTmp[8], staffTmp[9], Integer.parseInt(staffTmp[10]));
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
            line = "staff" + "," + value.getUsername() + "," + value.getPassword() + "," + value.getName() + "," + value.getSurname() + "," + value.getEmail() + "," + value.getTel() + "," + value.getDate() + "," + value.getTime() + "," + value.getStatus() + "," + value.getTryBlockLogin();
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
            Room roomAdd = new Room(RoomTmp[0], RoomTmp[1], RoomTmp[2], RoomTmp[3], RoomTmp[4]);
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
            line = room.getBuilding() + "," + room.getFloor() + "," + room.getRoomNumber() + "," + room.getType() + "," + room.getStatus();
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
}
