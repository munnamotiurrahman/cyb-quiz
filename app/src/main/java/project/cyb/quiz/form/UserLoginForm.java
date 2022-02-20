package project.cyb.quiz.form;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class UserLoginForm {
    @NotEmpty
    @Email
    private String email;

    @NotEmpty
    @Size(min = 8, max = 40)
    private String password;

    /**
     * For getting email from user
     * 
     * @return
     */

    public String getEmail() {
        return email;
    }

    /**
     * To set email for user
     * 
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * To get password from user
     * 
     * @return
     */

    public String getPassword() {
        return password;
    }

    /**
     * To set password for user
     * 
     * @param password
     */

    public void setPassword(String password) {
        this.password = password;
    }
}
