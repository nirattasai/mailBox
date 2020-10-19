package Final.Controller.Item;

public class Package extends Letter {
    String carrier;
    String trackNumber;

    public Package(String roomNumber, String senderName, String receiverName, String size, String carrier, String trackNumber,String date,String time, String Picture) {
        super(roomNumber, senderName, receiverName, size,date,time,Picture);
        this.carrier = carrier;
        this.trackNumber = trackNumber;
    }

    public String getCarrier() {
        return carrier;
    }

    public String getTrackNumber() {
        return trackNumber;
    }

}
