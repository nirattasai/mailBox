package Final.Controller;

import Final.Controller.Account.Staff;
import Final.Controller.Building.Room;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;

public class CSVControlInterfaceControl implements CSVControlInterface {

    private File file = new File("CSV/Staff.csv");

    public ArrayList<Staff> createStaffListFromCSV() throws IOException {
        new FileReader(file);
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line = "";
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
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        String line = "";
        for (int i = 0; i < staff.size(); i++) {
            line = "staff" + "," + staff.get(i).getUsername() + "," + staff.get(i).getPassword() + "," + staff.get(i).getName() + "," + staff.get(i).getSurname() + "," + staff.get(i).getEmail() + "," + staff.get(i).getTel() + "," + staff.get(i).getDate() + "," + staff.get(i).getTime() + "," + staff.get(i).getStatus() + "," + staff.get(i).getTryBlockLogin();
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
        String line = "";
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
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        String line = "";
        for (int i = 0; i < rooms.size(); i++) {
            line = rooms.get(i).getBuilding() + "," + rooms.get(i).getFloor() + "," + rooms.get(i).getRoomNumber() + "," + rooms.get(i).getType() + "," + rooms.get(i).getStatus();
            bufferedWriter.append(line);
            bufferedWriter.newLine();
        }
        bufferedWriter.close();
        fileWriter.close();
    }
}
