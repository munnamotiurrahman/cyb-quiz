package project.cyb.quiz.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "carts")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_id")
    private Long id;

    @OneToOne(mappedBy = "cart")
    private User user;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "cart_books", joinColumns = {
            @JoinColumn(name = "cart_id", nullable = false, updatable = false) }, inverseJoinColumns = {
                    @JoinColumn(name = "book_id", nullable = false, updatable = false) })
    private List<Book> books;

    /**
     * Empty Constractor for Cart model
     */
    public Cart() {
        super();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
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
