package Final.item;

import Final.*;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class ItemManage {

    CSVControlInterface csvControlInterface = new CSVControlInterfaceControl();
    ArrayList<Letter> items = new ArrayList<>();
    ArrayList<Letter> itemsHistory = new ArrayList<>();

    public ItemManage() throws IOException {
        addDocument(csvControlInterface.createDocumentListFromCSV());
        addLetter(csvControlInterface.createLetterListFromCSV());
        addPackage(csvControlInterface.createPackageListFromCSV());
        addDocumentH(csvControlInterface.createDocumentHistoryListFromCSV());
        addLetterH(csvControlInterface.createLetterHistoryListFromCSV());
        addPackageH(csvControlInterface.createPackageHistoryListFromCSV());
    }

    public void addDocument(ArrayList<Document> documents){
        for (Document x : documents){
            items.add(x);
        }
    }

    public void addPackage(ArrayList<Package> packages){
        for (Package x : packages){
            items.add(x);
        }
    }

    public void addLetter(ArrayList<Letter> letters){
        for (Letter x : letters){
            items.add(x);
        }
    }

    public void addLetterH(ArrayList<Letter> letters){
        for (Letter x : letters){
            itemsHistory.add(x);
        }
    }

    public void addDocumentH(ArrayList<Document> documents){
        for (Document x : documents){
            itemsHistory.add(x);
        }
    }

    public void addPackageH(ArrayList<Package> packages){
        for (Package x : packages){
            itemsHistory.add(x);
        }
    }



    public void show(){
        for (Letter x : items){
            System.out.println(x.getRoomNumber());
        }
    }

    public void residentReceive(String roomNumber,String resident,String Staff) throws IOException {
        for (int i=0;i<items.size();i++){
            if(items.get(i).getRoomNumber().equals(roomNumber)){
                items.get(i).setPaider(Staff);
                items.get(i).setResident(resident);
                items.get(i).setOut(LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                        LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
                itemsHistory.add(items.get(i));
                items.remove(items.get(i));
                i--;
            }
        }
        writeItemHistoryToCSV();
        writeItemToCSV();
    }

    public void writeItemToCSV() throws IOException {
        ArrayList<Letter> letters = new ArrayList<>();
        ArrayList<Document> documents = new ArrayList<>();
        ArrayList<Package> packages = new ArrayList<>();
        for (Letter x : items){
            if (x.getType().equals("Letter")){
                letters.add(x);
            }
            else if (x.getType().equals("Package")){
                packages.add((Package) x);
            }
            else if (x.getType().equals("Document")){
                documents.add((Document) x);
            }
        }
        csvControlInterface.writeLetterListToCSV(letters);
        csvControlInterface.writeDocumentListToCSV(documents);
        csvControlInterface.writePackageListToCSV(packages);
    }

    public void writeItemHistoryToCSV() throws IOException {
        ArrayList<Letter> letters = new ArrayList<>();
        ArrayList<Document> documents = new ArrayList<>();
        ArrayList<Package> packages = new ArrayList<>();
        for (Letter x : itemsHistory){
            if (x.getType().equals("Letter")){
                letters.add(x);
            }
            else if (x.getType().equals("Package")){
                packages.add((Package) x);
            }
            else if (x.getType().equals("Document")){
                documents.add((Document) x);
            }
        }
        csvControlInterface.writeLetterHistoryListToCSV(letters);
        csvControlInterface.writeDocumentHistoryListToCSV(documents);
        csvControlInterface.writePackageHistoryListToCSV(packages);
    }
}
