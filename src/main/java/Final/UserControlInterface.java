package Final;

import Final.account.Account;

import java.io.IOException;
import java.util.ArrayList;

public interface UserControlInterface {

    ArrayList<String> usernameSend();

    ArrayList<String> passwordSend();

    ArrayList<String> permissionSend();

    void getLog();

    String checkLogin();

    void addUser(Account account);

    ArrayList<Account> createAccountFromCSV() throws IOException;

    void writeAccountToCSV(ArrayList<Account> accounts) throws IOException;
}
