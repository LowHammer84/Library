import database.Database;
import gui.Window;

public class Program {
    
    public static void main(String[] args) {
    
        Database database = Database.getData();
        Window window = new Window(database);
        window.create("LIBRARY");
        
    }
    
}
