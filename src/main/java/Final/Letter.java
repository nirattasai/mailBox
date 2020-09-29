package Final;
// i already write inheritance no commit update
public class Letter {
    String ReceiverName;
    String RoomNumber;
    String SenderName;
    String Size;
    String Picture;
    public Letter(String ReceiverName,String RoomNumber,String SenderName,String Size,String Picture){
        this.ReceiverName = ReceiverName;
        this.RoomNumber = RoomNumber;
        this.SenderName = SenderName;
        this.Size = Size;
        this.Picture = Picture;
    }
    public String getReceiverName ()
    {
        return ReceiverName;
    }
    public String getRoomNumber ()
    {
        return RoomNumber;
    }
    public String getSenderName()
    {
        return SenderName;
    }
    public String getSize()
    {
        return Size;
    }
    public String getPicture()
    {
        return Picture;
    }
}

class Document extends Letter{
    String Privacy;

    public Document(String ReceiverName, String RoomNumber, String SenderName, String Size, String Picture,String Privacy) {
        super(ReceiverName, RoomNumber, SenderName, Size, Picture);
        this.Privacy = Privacy;
    }
    public String getPrivacy()
    {
        return Privacy;
    }
}

class Package extends Letter{
    String Carrier;
    String TrackNumber;

    public Package(String ReceiverName, String RoomNumber, String SenderName, String Size, String Picture,String Carrier,String TrackNumber) {
        super(ReceiverName, RoomNumber, SenderName, Size, Picture);
        this.Carrier = Carrier;
        this.TrackNumber = TrackNumber;
    }
    public String getCarrier()
    {
        return Carrier;
    }
    public String getTrackNumber()
    {
        return TrackNumber;
    }
}
