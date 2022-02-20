package project.cyb.quiz.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "requestedBook")
public class RequestedBook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private Long id;

    private String type;
    private String name;
    private String author;
    private String language;
    private Boolean isAvaileAble = false;

    /**
     * Empty Constractor for requestedBook object
     * 
     */

    public RequestedBook() {
        super();
    }

    /**
     * To getType from requestedBook object
     * 
     * @return
     */
    public String getType() {
        return type;
    }

    /**
     * To setType for requestedBook object
     * 
     * @param type
     */

    public void setType(String type) {
        this.type = type;
    }

    /**
     * To set book name for requestedBook object
     * 
     * @return
     */

    public String getName() {
        return name;
    }

    /**
     * To get book name from requestedBook object
     * 
     * @param name
     */

    public void setName(String name) {
        this.name = name;
    }

    /**
     * To get book author from requestedBook object
     * 
     * @return
     */

    public String getAuthor() {
        return author;
    }

    /**
     * To set Author for requestedBook
     * 
     * @param author
     */

    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * To get Language from requestedBook
     * 
     * @return
     */

    public String getLanguage() {
        return language;
    }

    /**
     * To set Language for requestedBook object
     * 
     * @param language
     */

    public void setLanguage(String language) {
        this.language = language;
    }

    /**
     * To get id form requestedBook object
     * 
     * @return
     */

    public Long getId() {
        return id;
    }

    /**
     * To set id for requestedBook object
     * 
     * @param id
     */

    public void setId(Long id) {
        this.id = id;
    }

    /**
     * To get isAvailable form requestedBook object
     * 
     * @return
     */

    public Boolean getIsAvailable() {
        return isAvaileAble;
    }

    /**
     * To set isAvailable for requestedBook object
     * 
     * @param isAvailable
     */

    public void setIsAvailable(Boolean isAvailable) {
        this.isAvaileAble = isAvailable;
    }
}
