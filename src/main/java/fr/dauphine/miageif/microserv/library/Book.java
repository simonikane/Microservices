package fr.dauphine.miageif.microserv.library;

import javax.persistence.*;

@Entity
public class Book {
    //auto generate Ids
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column
    private String ISBN;
    @Column
    private String author;
    @Column
    private String title;
    @Column
    private String editor;
    @Column
    private int edition;

    public Book(){}

    public Book(String ISBN, String author, String title, String editor, int edition) {
        this.ISBN = ISBN;
        this.author = author;
        this.title = title;
        this.editor = editor;
        this.edition = edition;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getEditor() {
        return editor;
    }

    public void setEditor(String editor) {
        this.editor = editor;
    }

    public int getEdition() {
        return edition;
    }

    public void setEdition(int edition) {
        this.edition = edition;
    }
}
