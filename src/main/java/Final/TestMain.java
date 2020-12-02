package Final;

import Final.item.ItemManage;

import java.io.IOException;

public class testMain {
    public static void main(String[] args) throws IOException {
        ItemManage itemManage = new ItemManage();
        itemManage.residentReceive("A101","TestPoly","StaffPoly");
    }
}
