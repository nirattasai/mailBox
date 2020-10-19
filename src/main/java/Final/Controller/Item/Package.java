package Final.Controller.Item;

public class Package extends Letter {
    String Carrier;
    String TrackNumber;

    public Package(String ReceiverName, String RoomNumber, String SenderName, String Size, String Picture, String Carrier, String TrackNumber,String date,String time) {
        super(ReceiverName, RoomNumber, SenderName, Size, Picture,date,time);
        this.Carrier = Carrier;
        this.TrackNumber = TrackNumber;
    }

    public String getCarrier() {
        return Carrier;
    }

    public String getTrackNumber() {
        return TrackNumber;
    }

}
