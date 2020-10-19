package Final.Controller.Item;

import java.util.ArrayList;

public class MailBox {
    ArrayList<Document> documents = new ArrayList<>();
    ArrayList<Letter> letters = new ArrayList<>();
    ArrayList<Package> packages = new ArrayList<>();
    ArrayList<Document> documentsShow = new ArrayList<>();
    ArrayList<Letter> lettersShow = new ArrayList<>();
    ArrayList<Package> packagesShow = new ArrayList<>();

    public void getItem(String roomNumber)
    {
        for (Document value : documents)
        {
            if(value.getRoomNumber().equals(roomNumber))
            {
                documentsShow.add(value);
            }
        }
        for (Letter value : letters)
        {
            if(value.getRoomNumber().equals(roomNumber))
            {
                lettersShow.add(value);
            }
        }
        for (Package value : packages)
        {
            if (value.getRoomNumber().equals(roomNumber))
            {
                packagesShow.add(value);
            }
        }
    }

    public ArrayList<Letter> getLettersShow() {
        return lettersShow;
    }

    public ArrayList<Package> getPackagesShow() {
        return packagesShow;
    }

    public ArrayList<Document> getDocumentsShow() {
        return documentsShow;
    }

    public void addLetter(Letter letter)
    {
        letters.add(letter);
    }

    public void addPackage(Package packagee)
    {
        packages.add(packagee);
    }

    public void addDocument(Document document)
    {
        documents.add(document);
    }

    public void removeItem(String roomNumber)
    {
        for(Document document : documents)
        {
            if(roomNumber.equals(document.getRoomNumber()))
            {
                documents.remove(document);
            }
        }
        for(Letter letter : letters)
        {
            if(roomNumber.equals(letter.getRoomNumber()))
            {
                letters.remove(letter);
            }
        }
        for (Package packagee : packages)
        {
            if (roomNumber.equals(packagee.getRoomNumber()))
            {
                packages.remove(packagee);
            }
        }
    }
}
