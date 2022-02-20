package project.cyb.quiz.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private Long id;

    private String type;
    private String name;
    private String author;
    private String language;
    private String fileType;
    private int numberOfBooks;
    private Boolean isAvailable;

    @Lob
    private String fileData;

    /**
     * Empty Constractor for book object
     */

    public Book() {
        super();
    }

    /**
     * To get isAvailable value from book object
     * 
     * @return
     */

    public boolean getIsAvailable() {
        return isAvailable;
    }

    /**
     * To set isAvailable value for book object
     * 
     * @param isAvailable
     */

    public void setIsAvailable(Boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    /**
     * To get Number of books in store for book object
     * 
     * @return
     */

    public int getNumberOfBooks() {
        return numberOfBooks;
    }

    /**
     * To set number of books in store for book object
     * 
     * @param numberOfBooks
     */

    public void setNumberOfBooks(int numberOfBooks) {
        this.numberOfBooks = numberOfBooks;
    }

    /**
     * To getType from book object
     * 
     * @return
     */
    public String getType() {
        return type;
    }

    /**
     * To setType for book object
     * 
     * @param type
     */

    public void setType(String type) {
        this.type = type;
    }

    /**
     * To get File Type from book object
     * 
     * @return
     */

    public String getFileType() {
        return fileType;
    }

    /**
     * To set file type for book object
     * 
     * @param fileData
     */

    public void setFileData(String fileData) {
        this.fileData = fileData;
    }

    /**
     * To get file base64 converted data from book
     * 
     * @return
     */

    public String getFilefileData() {
        return fileData;
    }

    /**
     * To set file base64 converted data for book object
     * 
     * @param fileType
     */

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    /**
     * To set book name for book object
     * 
     * @return
     */

    public String getName() {
        return name;
    }

    /**
     * To get book name from book object
     * 
     * @param name
     */

    public void setName(String name) {
        this.name = name;
    }

    /**
     * To get book author from book object
     * 
     * @return
     */

    public String getAuthor() {
        return author;
    }

    /**
     * To set Author for book
     * 
     * @param author
     */

    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * To get Language from book
     * 
     * @return
     */

    public String getLanguage() {
        return language;
    }

    /**
     * To set Language for book object
     * 
     * @param language
     */

    public void setLanguage(String language) {
        this.language = language;
    }

    /**
     * To get id form book object
     * 
     * @return
     */

    public Long getId() {
        return id;
    }

    /**
     * To set id for book object
     * 
     * @param id
     */

    public void setId(Long id) {
        this.id = id;
    }

}
