package database;

import java.io.Serializable;

class Score implements Serializable {

    private String id;
    private enum Genre {Марш, Вальс, Джаз, Эстрада, Другое}
    private Genre genre = Genre.Другое;
    private String  title;
    private String  author;
    private boolean isInLibrary;

    public Score(String title, String author, String genre, boolean isInLibrary){
        this.title = title;
        this.author = author;
        this.genre = Genre.valueOf(genre);
        this.isInLibrary = isInLibrary;
    }

    @Override
    public String toString() {
        return title + " " + author + " " + genre;
    }
}
