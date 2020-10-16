package Final.Controller.Account;

public class Staff extends Account {

    private String status="1";  // 1=normal,0=blocked

    private int tryBlockLogin = 0;

    public Staff(String s, String s1, String s2, String s3, String s4, String s5, String s6, String s7, String s8) {
        super(s,s1,s2,s3,s4,s5,s6,s7);
        this.status = s8;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void countBlock()
    {
        tryBlockLogin+=1;
    }

    public String getTryBlockLogin() {
        return String.valueOf(tryBlockLogin);
    }

    public void setTryBlockLogin(int tryBlockLogin) {
        this.tryBlockLogin = tryBlockLogin;
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

    public Staff(String name, String surname, String username, String password, String email, String tel, String date, String time,String status,int blockedCount) {
        super(name, surname, username, password, email, tel, date, time);
        this.status = status;
        this.tryBlockLogin = blockedCount;
    }


    public void setLog(String date, String time) {
        this.date = date;
        this.time = time;
    }
}