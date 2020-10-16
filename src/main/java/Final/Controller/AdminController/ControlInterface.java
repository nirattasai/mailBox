package Final.Controller.AdminController;

import Final.Controller.Account.Staff;
import Final.Controller.StaffInterface;
import Final.Controller.StaffInterfaceControl;

import java.io.*;
import java.util.ArrayList;

public class ControlInterface implements UserControlInterface{

    private File file = new File("CSV/User.csv");
    private StaffInterface staffInterface = new StaffInterfaceControl();

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
                if (!((line = bufferedReader.readLine()) != null)) break;
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
                if (!((line = bufferedReader.readLine()) != null)) break;
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
                if (!((line = bufferedReader.readLine()) != null)) break;
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
            staff = staffInterface.createStaffListFromCSV();
        } catch (IOException e) {
            e.printStackTrace();
        }
        for(int i=0;i<staff.size();i++)
        {
            if(staff.get(i).getUsername().equals(username))
            {
                String date = String.valueOf(java.time.LocalDate.now());
                String time = String.valueOf(java.time.LocalTime.now());
                staff.get(i).setLog(date,time);
            }
        }
        try {
            staffInterface.writeStaffListToCSV(staff);
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
