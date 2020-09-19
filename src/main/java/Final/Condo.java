package Final;

public class Condo {
    int Building;
    int Floor;
    int BedroomType;
    int RoomInFloor;

    public Condo(int Building, int Floor, int BedroomType, int RoomInFloor) {
        this.Building = Building;
        this.Floor = Floor;
        this.BedroomType = BedroomType;
        this.RoomInFloor = RoomInFloor;
    }
    public void setCondo(int Building, int Floor, int BedroomType, int RoomInFloor)
    {
        this.Building = Building;
        this.Floor = Floor;
        this.BedroomType = BedroomType;
        this.RoomInFloor = RoomInFloor;

    }
}
