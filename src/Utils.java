import java.io.*;
import java.util.ArrayList;

public class Utils {

    static final File scoresFile = new File("scores.save");
    static private ObjectOutputStream objectOutputStream;
    static private ObjectInputStream objectInputStream;
    static ArrayList<Score> scores = new ArrayList<>();


    static void saveToFile() throws IOException {
        if (!scoresFile.exists()) scoresFile.createNewFile();
        objectOutputStream = new ObjectOutputStream(new FileOutputStream(scoresFile));
        objectOutputStream.writeObject(scores);
        objectOutputStream.close();
    }

    static void readFromFile() throws IOException, ClassNotFoundException {
        if (!scoresFile.exists())
            return;
        if (scoresFile.exists()) {
            objectInputStream = new ObjectInputStream(new FileInputStream(scoresFile));
            scores = (ArrayList<Score>) objectInputStream.readObject();
        }
        objectInputStream.close();
    }

    public static void add(String title, String author, String genre, boolean isInLibrary){
        scores.add(new Score(title, author, genre, isInLibrary));
    }
}
