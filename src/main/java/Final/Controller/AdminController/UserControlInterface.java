package Final.Controller.AdminController;

import Final.Controller.Account.Account;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public interface UserControlInterface {

    ArrayList<String> usernameSend();

    ArrayList<String> passwordSend();

    ArrayList<String> permissionSend();

    void getLog();

    String checkLogin();

    void addUser(String permission, String username, String password, String name, String surname);

    ArrayList<Account> createAccountFromCSV() throws IOException;

    void writeAccountToCSV(ArrayList<Account> accounts) throws IOException;
}
