package ch.fhnw.workshop.domain.dto;

/**
 * Created by roman on 29.03.16.
 */
public class UserDTO {
    private String email;
    private String username;
    private String password;

    public String getEmail() {
        return email;
    }
    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
