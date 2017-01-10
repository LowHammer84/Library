import database.Database;

public class Test {

    public static void main(String[] args) {
        Database database = Database.getData();
        database.printScores();
    }

}
