package Final.Controller;

import java.io.*;

public class ControllerTest {
    public static void main(String[] args) throws IOException {
        File file = new File("src/main/resources/CSV/User.csv");
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line = "";
        while((line = bufferedReader.readLine()) != null)
        {
            String[] data = line.split(",");
            System.out.print(data[0]+"  "+data[1]+"  "+data[2]);
            System.out.print("\n");
        }
    }
}
