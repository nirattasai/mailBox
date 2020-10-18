package Final.Controller.Building;

public class Room {
    private String building;
    private String floor;
    private String roomNumber;
    private String type;
    private String status;

    public Room(String building,String floor,String roomNumber,String type,String status)
    {
        this.building = building;
        this.floor = floor;
        this.roomNumber = roomNumber;
        this.type = type;
        this.status = status;
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
}
