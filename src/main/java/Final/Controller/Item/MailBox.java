package Final.Controller.Item;

public class MailBox {
    String roomNumber;
    String type;

    public MailBox(String roomNumber,String type)
    {
        this.roomNumber = roomNumber;
        this.type = type;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public String getType() {
        return type;
    }
}