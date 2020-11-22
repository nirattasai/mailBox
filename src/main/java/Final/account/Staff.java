package Final.account;

public class Staff extends Account {

    private String status="normal";  // 1=normal,0=blocked
    private int tryBlockLogin = 0;
    private String picture;

    public Staff(String permission,String username, String password, String name, String surname) {
        super(permission,username,password,name,surname);
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

    public Staff(String name, String surname, String username, String password,
                 String email, String tel, String date, String time,String status,
                 int blockedCount,String picture) {
        super(name, surname, username, password, email, tel, date, time);
        this.status = status;
        this.tryBlockLogin = blockedCount;
        this.picture = picture;
    }
    
    public void setLog(String date, String time) {
        this.date = date;
        this.time = time;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture() {
        this.picture = "No picture";
    }
}