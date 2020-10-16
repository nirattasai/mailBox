package Final.Controller.Account;

public class Account {
    private String name;
    private String tel;
    private String email;
    private String username;
    private String surname;
    private String password;
    protected String date;
    protected String time;

    public Account(String name, String surname, String username, String password, String email, String tel, String date, String time) {
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.password = password;
        this.email = email;
        this.tel = tel;
        this.time = time;
        this.date = date;
    }

    public Account(String s, String s1, String s2, String s3) {
        this.name = s;
        this.surname = s1;
        this.username = s2;
        this.password = s3;
    }


    public void changePassword(String newPassword)
    {
        this.password = newPassword;
    }


    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getSurname() {
        return surname;
    }

    public String getTel() {
        return tel;
    }

    public String getUsername() {
        return username;
    }
}
