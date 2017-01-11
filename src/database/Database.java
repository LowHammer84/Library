package database;


import java.io.*;
import java.util.ArrayList;

public class Database {
    
    private static final int ID = 0;
    private static final int TITLE = 1;
    private static final int AUTHOR = 2;
    private static final int GENRE = 3;
    private static final int INLIBRARY = 4;

    private static Database database;
    private File dataFile = new File("scores.save" );
    private ArrayList<Score> scores;
    private Database() {
        initialize();
    }

    public static Database getData() {
        if (database == null) {
            database = new Database();
        }
        return database;
    }

    private void initialize(){

        if (dataFile.exists()) {
            readDataFromFile();
        } else {
            System.out.println("Файл базы данных не найден - будет создан новый файл по умолчанию");
            scores = new ArrayList<>();
            scores.add(new Score("title", "author", "Другое", false));
            saveToFile();
        }
    }

    public void printScores(){
        for (Score s:
                scores) {
            System.out.println(s);
        }
    }

    public ArrayList<Score> getScores() {
        return scores;
    }
    
    public Score getScore(int index) {
        return scores.get(index);
    }
    
    public Object getValueAt(int rowIndex, int columnIndex) {
        
        Score score = getScore(rowIndex);
        
        switch (columnIndex) {
            case ID:
                return score.getId();
            case TITLE:
                return score.getTitle();
            case AUTHOR:
                return score.getAuthor();
            case GENRE:
                return score.getGenre();
            case INLIBRARY:
                return score.isInLibrary();
        }
        return null;
    }
    
    public void setValueAt(Object value, int row, int col) {
        
        Score score = getScore(row);
    
        switch (col) {
            
            case ID:
                score.setId((Integer) value);
                break;
            case TITLE:
                score.setTitle((String) value);
                break;
            case AUTHOR:
                score.setAuthor((String) value);
                break;
            case GENRE:
                score.setGenre((Score.Genre.valueOf(value.toString())));
                break;
            case INLIBRARY:
                score.setInLibrary((Boolean.parseBoolean(value.toString())));
                break;
        }
    }

    private boolean readDataFromFile() {

        if (!dataFile.exists()) {
            System.out.println("Ошибка: файл не найден!");
            return false;
        }
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(dataFile));
            scores = (ArrayList<Score>) objectInputStream.readObject();
            objectInputStream.close();
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("Ошибка: файл " + dataFile + " не найден");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Ошибка ввода - вывода");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Ошибка - не верный формат файла");
        }
        return true;
    }

    private void saveToFile() {

        if (!dataFile.exists()) try {
            dataFile.createNewFile();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(dataFile));
            objectOutputStream.writeObject(scores);
            objectOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Ошибка записи в файл");
        }
    }



}
