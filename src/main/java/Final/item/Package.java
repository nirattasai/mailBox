package Final.item;

public class Package extends Letter {
    String carrier;
    String trackNumber;

    public Package(String roomNumber, String senderName, String receiverName, String size,
                   String carrier, String trackNumber,String date,String time, String Picture,
                   String getter,String paider,String datePaid,String timePaid,String resident) {
        super(roomNumber, senderName, receiverName, size,date,time,Picture,getter,paider,datePaid,timePaid,resident);
        this.carrier = carrier;
        this.trackNumber = trackNumber;
    }

    public String getCarrier() {
        return carrier;
    }

    public String getTrackNumber() {
        return trackNumber;
    }

    @Override
    public String getType() {
        return "Package";
    }
}
