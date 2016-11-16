public class Score {

    private final static String[] instrumentsList = {"Flute", "Clarinet-1", "Clarinet-2", "Clarinet-3", "Alto Sax", "Tenor Sax",
                                               "Baritone", "Horn-1", "Horn-2", "Horn-3", "Tenor-2", "Tuba-1", "Tuba-2",
                                               "Bass-guitar", "Drums", "Cornet-1", "Cornet-2", "Trumpet-1", "Trumpet-2", "Trombone-1", "Trombone-2"};


    private int     id;
    private String  genre;
    private String  title;
    private String  author;
    private boolean isInLibrary;

    public int getId() {
        return id;
    }

    public void setId(int id) {

        this.id = id;
    }

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
        return "Score{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                '}';
    }
}
