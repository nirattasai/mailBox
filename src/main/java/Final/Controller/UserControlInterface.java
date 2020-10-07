package Final.Controller;

import Final.Staff;

import java.io.*;
import java.util.ArrayList;

public interface UserControlInterface {

    static ArrayList<String> usernameSend() throws IOException {
        File file = new File("CSV/User.csv");
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line ="";
        ArrayList<String> username = new ArrayList<>();
        while((line = bufferedReader.readLine()) != null)
        {
            String[] tmp = line.split(",");
            username.add(tmp[1]);
        }
        fileReader.close();
        return username;
    }

    static ArrayList<String> passwordSend() throws IOException {
        File file = new File("CSV/User.csv");
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line ="";
        ArrayList<String> password = new ArrayList<>();
        while((line = bufferedReader.readLine()) != null)
        {
            String[] tmp = line.split(",");
            password.add(tmp[2]);
        }
        fileReader.close();
        return password;
    }

    static ArrayList<String> permissionSend() throws IOException {
        File file = new File("CSV/User.csv");
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line ="";
        ArrayList<String> permission = new ArrayList<>();
        while((line = bufferedReader.readLine()) != null)
        {
            String[] tmp = line.split(",");
            permission.add(tmp[0]);
        }
        fileReader.close();
        return permission;
    }

    static void getLog(String username) throws IOException {
        ArrayList<Staff> staff = StaffInterface.createStaffListFromCSV();
        for(int i=0;i<staff.size();i++)
        {
            if(staff.get(i).getUsername().equals(username))
            {
                String date = String.valueOf(java.time.LocalDate.now());
                String time = String.valueOf(java.time.LocalTime.now());
                staff.get(i).setLog(date,time);
            }
        }
        StaffInterface.writeStaffListToCSV(staff);
    }

    static String checkLogin(String username, String password) throws IOException {
        ArrayList<String> usernameList = UserControlInterface.usernameSend();
        ArrayList<String> passwordList = UserControlInterface.passwordSend();
        ArrayList<String> permissionList = UserControlInterface.permissionSend();
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

    static void addUser(String permission,String username,String password,String name,String surname,String email,String tel,String date,String time) throws IOException {
        File file = new File("CSV/User.csv");
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, true));
        bufferedWriter.newLine();
        String line = permission + "," + username + "," + password + "," + name + "," + surname + "," + email + "," + tel + "," + date + "," + time;
        bufferedWriter.append(line);
        bufferedWriter.close();
    }
}
