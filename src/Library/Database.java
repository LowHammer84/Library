package Library;

import java.io.*;

public class Database {

    private static final File         scoresFile = new File("scores.save");
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

    class Score implements Serializable{

        private int     id;
        private String  genre;
        private String  title;
        private String  author;
        private boolean isInLibrary;

        public Score(String title, String author, String genre, boolean isInLibrary){
            //this.id = calcId();
            this.title = title;
            this.author = author;
            this.genre = genre;
            this.isInLibrary = isInLibrary;
        }

        public int getId() {
            return id;
        }

        /*public void setId(int id) {
            for (Score s: scores) {
                if (this.id == id)
                    break;
            } this.id = id;
        }*/



        public String getGenre() {
            return genre;
        }

        public void setGenre(String genre) {
            this.genre = genre;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public boolean isInLibrary() {
            return isInLibrary;
        }

        public void setInLibrary(boolean inLibrary) {
            isInLibrary = inLibrary;
        }

        @Override
        public String toString() {
            return title + " " + author + " " + genre;
        }

    }
}
