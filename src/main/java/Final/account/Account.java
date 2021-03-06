package Final.account;

public class Account {
    private final String name;
    private String tel;
    private String email;
    private String username;
    private String surname;
    private String password;
    protected String date;
    protected String time;
    private String permission;
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

    public Account(String permission,String username, String password, String name, String surname) {
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.password = password;
        this.permission = permission;
    }

    public Account(String name,String surname,String tel)
    {
        this.name = name;
        this.surname = surname;
        this.tel = tel;
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

    public String getPermission() {
        return permission;
    }
}
