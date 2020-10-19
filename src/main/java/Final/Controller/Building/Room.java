package Final.Controller.Building;

public class Room {
    private final String building;
    private final String floor;
    private final String roomNumber;
    private final String type;
    private String status;
    private String item;

    public Room(String building,String floor,String roomNumber,String type,String status,String item)
    {
        this.building = building;
        this.floor = floor;
        this.roomNumber = roomNumber;
        this.type = type;
        this.status = status;
        this.item = item;
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
}
