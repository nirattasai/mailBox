package Final.account;

public class RoomOwner extends Account{
    private String roomNumber;

    public RoomOwner(String name,String surname,String roomNumber,String tel)
    {
        super(name,surname,tel);
        this.roomNumber = roomNumber;
    }

    public String getRoomNumber() {
        return roomNumber;
    }
}
