package database;

import java.io.*;

public class Database {

    private static final File scoresFile = new File("scores.save");
    static private Object[][] data;
    Object[][] defaultData = {{"Название", "Автор", "Жанр", 1, true},{"","","",2,false}, {"", "", "",3,false}};

    public Database(){
        if (!readFromFile())
            data = getData();
    }

    static void saveToFile(Object[][] data) throws IOException {
        if (!scoresFile.exists()) scoresFile.createNewFile();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(scoresFile));
        objectOutputStream.writeObject(data);
        objectOutputStream.close();
    }

    static boolean readFromFile() {
        if (scoresFile.exists()) {
            try {
                ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(scoresFile));
                data = (Object[][]) objectInputStream.readObject();
                objectInputStream.close();
                return true;
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                return false;
            }
        }
        return false;
    }

    public void addScore(String title, String author, String genre, boolean isInLibrary) {
       // scores.add(new Score(title, author, genre, isInLibrary));
    }

    public Object[][] getData() {
        if (data != null) {
            return data;
        } else return defaultData;
    }
}
