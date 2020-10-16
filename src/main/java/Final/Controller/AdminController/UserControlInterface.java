package Final.Controller.AdminController;

import java.util.ArrayList;

public interface UserControlInterface {

    ArrayList<String> usernameSend();

    ArrayList<String> passwordSend();

    ArrayList<String> permissionSend();

    void getLog(String username);

    String checkLogin(String username, String password);

    void addUser(String permission, String username, String password, String name, String surname);
}
