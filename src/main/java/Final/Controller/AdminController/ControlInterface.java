package Final.Controller.AdminController;

import Final.Controller.Account.Staff;
import Final.Controller.CSVControlInterface;
import Final.Controller.CSVControlInterfaceControl;

import java.io.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class ControlInterface implements UserControlInterface{

    private final File file = new File("CSV/User.csv");
    private final CSVControlInterface CSVControlInterface = new CSVControlInterfaceControl();

    public ArrayList<String> usernameSend() {
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line ="";
        ArrayList<String> username = new ArrayList<>();
        while(true)
        {
            try {
                if ((line = bufferedReader.readLine()) == null) break;
            } catch (IOException e) {
                e.printStackTrace();
            }
            String[] tmp = line.split(",");
            username.add(tmp[1]);
        }
        try {
            fileReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return username;
    }

    public ArrayList<String> passwordSend() {
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line ="";
        ArrayList<String> password = new ArrayList<>();
        while(true)
        {
            try {
                if ((line = bufferedReader.readLine()) == null) break;
            } catch (IOException e) {
                e.printStackTrace();
            }
            String[] tmp = line.split(",");
            password.add(tmp[2]);
        }
        try {
            fileReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return password;
    }

    public ArrayList<String> permissionSend() {
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line ="";
        ArrayList<String> permission = new ArrayList<>();
        while(true)
        {
            try {
                if ((line = bufferedReader.readLine()) == null) break;
            } catch (IOException e) {
                e.printStackTrace();
            }
            String[] tmp = line.split(",");
            permission.add(tmp[0]);
        }
        try {
            fileReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return permission;
    }

    public void getLog(String username) {
        ArrayList<Staff> staff = null;
        try {
            staff = CSVControlInterface.createStaffListFromCSV();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Staff tmp = null;
        int index = 0;
        for (Staff value : staff) {
            if (value.getUsername().equals(username)) {
                String date = java.time.LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                String time = java.time.LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
                value.setLog(date, time);
                index = staff.indexOf(value);
                tmp = value;
            }
        }
        staff.remove(index);
        staff.add(0,tmp);
        try {
            CSVControlInterface.writeStaffListToCSV(staff);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String checkLogin(String username, String password) {
        ArrayList<String> usernameList = usernameSend();
        ArrayList<String> passwordList = passwordSend();
        ArrayList<String> permissionList = permissionSend();
        String permission = "denied";
        for(int i=0;i<usernameList.size();i++)
        {
            if(username.equals(usernameList.get(i))&&password.equals(passwordList.get(i)))
            {
                return permissionList.get(i);
            }
        }
        return permission;
    }

    public void addUser(String permission, String username, String password, String name, String surname) {
        BufferedWriter bufferedWriter = null;
        try {
            bufferedWriter = new BufferedWriter(new FileWriter(file, true));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            bufferedWriter.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String line = permission + "," + username + "," + password + "," + name + "," + surname;
        try {
            bufferedWriter.append(line);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
