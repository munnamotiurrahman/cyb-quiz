package project.cyb.quiz.models;

import java.util.List;
import javax.persistence.CascadeType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.OneToMany;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    private String firstName;

    private String lastName;

    @Email
    @Column(unique = true)
    private String email;

    @NotEmpty
    private String password;

    @NotNull
    private Boolean isActive = false;

    @OneToMany(mappedBy = "user")
    private List<Post> posts;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cart_id", referencedColumnName = "cart_id")
    private Cart cart;

    /**
     * Empty constractor for user object
     */
    public User() {
        this.cart = new Cart();
    }

    /**
     * To get id frm user object
     * 
     * @return
     */

    public Long getId() {
        return id;
    }

    /**
     * To set Id form user object
     * 
     * @param id
     */

    public void setId(Long id) {
        this.id = id;
    }

    /**
     * To get first name from user object
     * 
     * @return
     */

    public String getFirstName() {
        return this.firstName;
    }

    /**
     * To set fist name for user object
     * 
     * @param firstName
     */

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * To get last name form user object
     * 
     * @return
     */

    public String getLastName() {
        return this.lastName;
    }

    /**
     * To set Full name for user object
     * 
     * @param lastName
     */

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * To get full name form user object
     * 
     * @return
     */

    public String getFullName() {
        return this.firstName + " " + this.lastName;
    }

    /**
     * To get email form user object
     * 
     * @return
     */

    public String getEmail() {
        return this.email;
    }

    /**
     * To set email for user object
     * 
     * @param email
     */

    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * To get password form user object
     * 
     * @return
     */

    public String getPassword() {
        return this.password;
    }

    /**
     * To set password for user object
     * 
     * @param password
     */

    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * To get isActive value from user object
     * 
     * @return
     */

    public Boolean getIsActive() {
        return this.isActive;
    }

    /**
     * To set is active value for user object
     * 
     * @param isActive
     */
    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

}
