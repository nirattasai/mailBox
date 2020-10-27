package Final.Controller.Building;

public class Room {
    private String building;
    private String floor;
    private String roomNumber;
    private String type;
    private String status;
    private String item;
    private int maxResident;
    private int currentResident;

    public Room(String building,String floor,String roomNumber,String type,String status,String item,int maxResident,
                int currentResident)
    {
        this.building = building;
        this.floor = floor;
        this.roomNumber = roomNumber;
        this.type = type;
        this.status = status;
        this.item = item;
        this.maxResident = maxResident;
        this.currentResident = currentResident;
    }

    public String getStatus() {
        return status;
    }

    public String getBuilding() {
        return building;
    }

    public String getFloor() {
        return floor;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public String getType() {
        return type;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRoomNumberFull()
    {
        return getBuilding()+getFloor() +getRoomNumber();
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getCurrentResident() {
        return String.valueOf(currentResident);
    }

    public String getMaxResident() {
        return String.valueOf(maxResident);
    }

    public void addResident()
    {
        this.currentResident+=1;
    }

    public void removeResident()
    {
        this.currentResident -= 1;
    }

    public boolean isFull()
    {
        if(maxResident<=currentResident)
            return true;
        return false;
    }
    public void emptyRoom()
    {
        this.status = "Room is not Owned";
    }
}
