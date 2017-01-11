package database;

import java.io.Serializable;

class Score implements Serializable {
    
    
    public enum Genre {Марш, Вальс, Джаз, Эcтрада, Другое,Попурри, Классика}
    private Genre   genre = Genre.Другое;
    private int     id;
    private String  title;
    private String  author;
    private boolean isInLibrary;

    public Score(String title, String author, String genre, boolean isInLibrary){
        this.setTitle(title);
        this.setAuthor(author);
        this.setGenre(Genre.valueOf(genre));
        this.setInLibrary(isInLibrary);
    }

    public String getTitle() {
        return title;
    }
    
    public int getId() {
        return id;
    }
    
    public Genre getGenre() {
        return genre;
    }
    
    public String getAuthor() {
        return author;
    }
    
    public boolean isInLibrary() {
        return isInLibrary;
    }
    
    public void setGenre(Genre genre) {
        this.genre = genre;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public void setAuthor(String author) {
        this.author = author;
    }
    
    public void setInLibrary(boolean inLibrary) {
        isInLibrary = inLibrary;
    }
    
    
    
    @Override
    public String toString() {
        return title + " " + author + " " + genre;
    }
}
