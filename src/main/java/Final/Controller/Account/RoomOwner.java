package Final.Controller.Account;

public class RoomOwner{
    private String roomNumber;
    private String name;
    private String surname;
    private String tel;

    public RoomOwner(String name,String surname,String roomNumber,String tel)
    {
        this.roomNumber = roomNumber;
        this.name = name;
        this.surname = surname;
        this.tel = tel;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public String getTel() {
        return tel;
    }

    public String getSurname() {
        return surname;
    }

    public String getName() {
        return name;
    }
}
