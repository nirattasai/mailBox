package Final.Controller.Account;

public class Staff extends Account {

    private String status="normal";  // 1=normal,0=blocked

    private int tryBlockLogin = 0;

    public Staff(String name, String surname, String username, String password) {
        super(name,surname,username,password);
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
        return status.equals("normal");
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