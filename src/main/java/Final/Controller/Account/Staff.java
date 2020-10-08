package Final.Controller.Account;

public class Staff extends Account {

    private String status="1";  // 1=normal,0=blocked

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean checkStatus()
    {
        if (status.equals("1"))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public String getStatus() {
        return status;
    }

    public Staff(String name, String surname, String username, String password, String email, String tel, String date, String time,String status) {
        super(name, surname, username, password, email, tel, date, time);
        this.status = status;
    }

    public void setLog(String date, String time) {
        this.date = date;
        this.time = time;
    }
}