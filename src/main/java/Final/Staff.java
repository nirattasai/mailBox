package Final;

public class Staff {
    String name;
    String tel;
    String email;
    String username;
    String surname;
    String password;
    String date;
    String time;

    public Staff (String name,String surname,String username,String password,String email,String tel,String date,String time) {
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.password = password;
        this.email = email;
        this.tel = tel;
        this.time = time;
        this.date = date;
    }

    public void setLog(String date,String time)
    {
        this.date = date;
        this.time = time;
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
