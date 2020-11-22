package Final.item;
public class Letter {
    String receiverName;
    String roomNumber;
    String senderName;
    String size;
    String picture;
    String date;
    String time;
    String getter;
    String paider;
    String datePaid;
    String timePaid;
    String resident;

    public Letter(String roomNumber,String senderName,String receiverName,String size,
                  String date,String time,String picture,String getter,String paider,
                  String datePaid,String timePaid,String resident){
        this.receiverName = receiverName;
        this.roomNumber = roomNumber;
        this.senderName = senderName;
        this.size = size;
        this.picture = picture;
        this.date = date;
        this.time = time;
        this.getter = getter;
        this.paider = paider;
        this.datePaid = datePaid;
        this.timePaid = timePaid;
        this.resident = resident;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public String getDatePaid() {
        return datePaid;
    }

    public String getTimePaid() {
        return timePaid;
    }

    public String getResident() {
        return resident;
    }

    public void setOut(String date,String time)
    {
        this.datePaid = date;
        this.timePaid = time;
    }

    public void setResident(String resident) {
        this.resident = resident;
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

    public String getGetter() {
        return getter;
    }

    public String getPaider() {
        return paider;
    }

    public void setPaider(String paider) {
        this.paider = paider;
    }
}

