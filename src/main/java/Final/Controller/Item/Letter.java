package Final.Controller.Item;
public class Letter {
    String receiverName;
    String roomNumber;
    String senderName;
    String size;
    String picture;
    String date;
    String time;
    public Letter(String roomNumber,String senderName,String receiverName,String size,String date,String time,String picture){
        this.receiverName = receiverName;
        this.roomNumber = roomNumber;
        this.senderName = senderName;
        this.size = size;
        this.picture = picture;
        this.date = date;
        this.time = time;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public String getRoomNumber ()
    {
        return roomNumber;
    }
    public String getSenderName()
    {
        return senderName;
    }
    public String getSize()
    {
        return size;
    }
    public String getPicture()
    {
        return picture;
    }

    public String getTime() {
        return time;
    }

    public String getDate() {
        return date;
    }
    public void setLog(String date,String time)
    {
        this.date = date;
        this.time = time;
    }
}

