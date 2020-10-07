package Final;

import Final.Controller.Account;

public class Staff extends Account {


    public Staff(String name, String surname, String username, String password, String email, String tel, String date, String time) {
        super(name, surname, username, password, email, tel, date, time);
    }

    public void setLog(String date, String time) {
        this.date = date;
        this.time = time;
    }
}