package Final.Controller.Item;

public class Document extends Letter {
    String privacy;

    public Document(String roomNumber, String senderName, String receiverName, String size, String privacy, String date,String time,String picture,String getter,String paider) {
        super(roomNumber,senderName,receiverName,size,date,time,picture,getter,paider);
        this.privacy = privacy;
    }

    public String getPrivacy() {
        return privacy;
    }

}
